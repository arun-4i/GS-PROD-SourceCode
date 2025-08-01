# Node.js GrandStore Migration Mapping Guide

## Overview

This document provides a detailed mapping strategy for migrating the GrandStore Spring Boot application to Node.js/TypeScript, following the established .cursorrules standards. **All code will be placed in the `backend/` folder**.

## Key Migration Principles

### 1. Database Strategy

- **NO MIGRATIONS**: Use existing Oracle tables as-is
- **Sequelize Models**: Map to existing tables with `freezeTableName: true` and `timestamps: false`
- **Oracle Packages**: Reuse all existing stored procedures/packages

### 2. Architecture Mapping

```
Spring Boot                    →    Node.js/TypeScript
─────────────────────────────       ─────────────────────────────────
Controller (@RestController)   →    Controller + Express Router
Service (@Service)            →    Service (class with DI)
Repository (@Repository)      →    Repository (Sequelize + custom)
Entity (@Entity)              →    Sequelize Model
PackageCalling               →    OraclePackageService
```

## Answering Your Questions

### 1. Is it needed to have a Sequelize model?

**YES**, Sequelize models are necessary for:

- Type safety with TypeScript
- Basic CRUD operations
- Relationship definitions
- Query building
- Data validation at the application level

However, models will be **minimal** since most business logic stays in Oracle packages.

### 2. How does the insert into DB happen?

Inserts happen through **TWO methods**:

**Method 1: Oracle Packages (Primary - 90% of cases)**

```typescript
// Most inserts go through Oracle packages
const result = await oraclePackageService.callPackage(
  "XXGS_MOB_UTIL_PKG.INSERT_BIN_TRANSFER",
  {
    itemId: "ITEM001",
    fromBin: "A1",
    toBin: "B2",
    quantity: 100,
  }
);
```

**Method 2: Sequelize (Secondary - 10% of cases)**

```typescript
// Only for simple tables without complex business logic
const user = await User.create({
  userName: "john_doe",
  emailAddress: "john@example.com",
});
```

### 3. Do we need to do anything extra?

Yes, to maintain modularity and scalability:

**Additional Requirements:**

- **Package Service Layer**: Wrapper for all Oracle package calls
- **Type Definitions**: TypeScript interfaces for all Oracle responses
- **Error Mapping**: Convert Oracle errors to Node.js AppError
- **Transaction Wrapper**: For multi-step operations
- **Response Formatter**: Consistent API responses

## Detailed Migration Mapping

### Project Structure

```
backend/
├── src/
│   ├── config/
│   │   ├── database.ts          # Oracle + Sequelize config
│   │   ├── env.ts              # Environment variables
│   │   └── swagger.ts          # API documentation
│   │
│   ├── controllers/
│   │   ├── base/
│   │   │   └── BaseController.ts
│   │   ├── authenticationController.ts
│   │   ├── binTransferController.ts
│   │   └── [module]Controller.ts
│   │
│   ├── services/
│   │   ├── oracle/
│   │   │   └── oraclePackageService.ts  # Core Oracle wrapper
│   │   ├── authenticationService.ts
│   │   ├── binTransferService.ts
│   │   └── [module]Service.ts
│   │
│   ├── repositories/
│   │   ├── base/
│   │   │   └── BaseRepository.ts
│   │   ├── userRepository.ts
│   │   └── [module]Repository.ts
│   │
│   ├── models/
│   │   ├── index.ts            # Model associations
│   │   ├── User.ts
│   │   └── [Entity].ts
│   │
│   ├── packages/              # Oracle package wrappers
│   │   ├── authenticationPackage.ts
│   │   ├── mobileUtilsPackage.ts
│   │   └── [package]Package.ts
│   │
│   ├── validators/
│   │   ├── authSchemas.ts
│   │   └── [module]Schemas.ts
│   │
│   ├── routes/
│   │   ├── index.ts
│   │   ├── authRoutes.ts
│   │   └── [module]Routes.ts
│   │
│   ├── middleware/
│   │   ├── auth.ts
│   │   ├── validation.ts
│   │   └── errorHandler.ts
│   │
│   ├── types/
│   │   ├── oracle.types.ts
│   │   ├── api.types.ts
│   │   └── [module].types.ts
│   │
│   └── utils/
│       ├── logger.ts
│       ├── errors.ts
│       └── jwt.ts
│
├── tests/
├── docs/
└── package.json
```

### Model Implementation Pattern

```typescript
// backend/src/models/User.ts
import { Model, DataTypes, Sequelize } from "sequelize";

interface UserAttributes {
  userId: number;
  userName: string;
  personId: number;
  fullName: string;
  emailAddress: string;
  status: string;
  lastLogonDate?: Date;
}

export class User extends Model<UserAttributes> implements UserAttributes {
  public userId!: number;
  public userName!: string;
  public personId!: number;
  public fullName!: string;
  public emailAddress!: string;
  public status!: string;
  public lastLogonDate?: Date;
}

export const initUserModel = (sequelize: Sequelize): typeof User => {
  User.init(
    {
      userId: {
        type: DataTypes.NUMBER,
        primaryKey: true,
        field: "USER_ID",
      },
      userName: {
        type: DataTypes.STRING(50),
        allowNull: false,
        unique: true,
        field: "USER_NAME",
      },
      personId: {
        type: DataTypes.NUMBER,
        allowNull: false,
        field: "PERSON_ID",
      },
      fullName: {
        type: DataTypes.STRING(100),
        allowNull: false,
        field: "FULL_NAME",
      },
      emailAddress: {
        type: DataTypes.STRING(100),
        field: "EMAIL_ADDRESS",
      },
      status: {
        type: DataTypes.STRING(20),
        allowNull: false,
        defaultValue: "ACTIVE",
        field: "STATUS",
      },
      lastLogonDate: {
        type: DataTypes.DATE,
        field: "LAST_LOGON_DATE",
      },
    },
    {
      sequelize,
      modelName: "User",
      tableName: "XXGS_WMS_USER",
      freezeTableName: true,
      timestamps: false,
      underscored: false,
    }
  );

  return User;
};
```

### Oracle Package Service Pattern

```typescript
// backend/src/services/oracle/oraclePackageService.ts
import oracledb, { Connection, BindParameters } from "oracledb";
import { DatabaseConnection } from "@config/database";
import { logger } from "@utils/logger";
import { AppError } from "@utils/errors";

export class OraclePackageService {
  private schemaName: string;

  constructor() {
    this.schemaName = process.env.DB_SCHEMA || "XXGS";

    // Initialize Oracle client
    oracledb.outFormat = oracledb.OUT_FORMAT_OBJECT;
    oracledb.fetchAsString = [oracledb.CLOB];
  }

  async callPackage<T = any>(
    packageProcedure: string,
    params: BindParameters,
    options?: {
      autoCommit?: boolean;
      transaction?: Connection;
    }
  ): Promise<T> {
    let connection = options?.transaction;
    let shouldClose = false;

    try {
      if (!connection) {
        connection = await DatabaseConnection.getOracleConnection();
        shouldClose = true;
      }

      logger.debug("oracle-package", `Calling ${packageProcedure}`, { params });

      const result = await connection.execute<T>(
        `BEGIN ${this.schemaName}.${packageProcedure}; END;`,
        params,
        {
          autoCommit: options?.autoCommit ?? true,
        }
      );

      logger.debug(
        "oracle-package",
        `${packageProcedure} completed successfully`
      );

      return result.outBinds as T;
    } catch (error) {
      logger.error("oracle-package", `Error in ${packageProcedure}`, {
        error: error.message,
        params,
      });

      // Map Oracle errors to application errors
      throw this.mapOracleError(error);
    } finally {
      if (shouldClose && connection) {
        try {
          await connection.close();
        } catch (err) {
          logger.error("oracle-package", "Error closing connection", {
            error: err,
          });
        }
      }
    }
  }

  private mapOracleError(error: any): AppError {
    // Map common Oracle errors
    if (error.errorNum === 1) {
      return new AppError("Unique constraint violation", 409);
    }
    if (error.errorNum === 1403) {
      return new AppError("No data found", 404);
    }
    if (error.errorNum === 20001) {
      // Custom application errors from Oracle
      return new AppError(error.message, 400);
    }

    return new AppError("Database operation failed", 500);
  }
}
```

### Service Implementation Pattern

```typescript
// backend/src/services/binTransferService.ts
import { OraclePackageService } from "./oracle/oraclePackageService";
import { BinTransferRepository } from "@repositories/binTransferRepository";
import { BinTransferValidator } from "@validators/binTransferValidator";
import { AppError } from "@utils/errors";
import { logger } from "@utils/logger";
import oracledb from "oracledb";

export class BinTransferService {
  constructor(
    private oraclePackageService: OraclePackageService,
    private binTransferRepository: BinTransferRepository
  ) {}

  async getItemDetails(itemId: string, subInventory: string) {
    try {
      // Call Oracle package for item details
      const result = await this.oraclePackageService.callPackage(
        "XXGS_MOB_UTIL_PKG.GET_ITEM_DETAILS",
        {
          P_ITEM_ID: itemId,
          P_SUB_INVENTORY: subInventory,
          P_ITEM_DETAILS: { type: oracledb.CURSOR, dir: oracledb.BIND_OUT },
        }
      );

      // Process cursor results
      const cursor = result.P_ITEM_DETAILS;
      const items = await cursor.getRows();
      await cursor.close();

      return items;
    } catch (error) {
      logger.error("bin-transfer", "Failed to get item details", {
        itemId,
        error,
      });
      throw error;
    }
  }

  async createBinTransfer(transferData: any) {
    try {
      // Validate at service level
      const validated = BinTransferValidator.validate(transferData);

      // Call Oracle package for business logic and insert
      const result = await this.oraclePackageService.callPackage(
        "XXGS_MOB_UTIL_PKG.INSERT_BIN_TRANSFER",
        {
          P_ITEM_ID: validated.itemId,
          P_FROM_BIN: validated.fromBin,
          P_TO_BIN: validated.toBin,
          P_QUANTITY: validated.quantity,
          P_USER_ID: validated.userId,
          P_STATUS: {
            dir: oracledb.BIND_OUT,
            type: oracledb.STRING,
            maxSize: 10,
          },
          P_MESSAGE: {
            dir: oracledb.BIND_OUT,
            type: oracledb.STRING,
            maxSize: 500,
          },
        }
      );

      if (result.P_STATUS !== "SUCCESS") {
        throw new AppError(result.P_MESSAGE || "Transfer failed", 400);
      }

      // Optional: Save to local table for tracking
      await this.binTransferRepository.createTransferRecord({
        ...validated,
        status: "COMPLETED",
        transferDate: new Date(),
      });

      return {
        success: true,
        message: result.P_MESSAGE,
      };
    } catch (error) {
      logger.error("bin-transfer", "Failed to create bin transfer", { error });
      throw error;
    }
  }
}
```

### Controller Pattern

```typescript
// backend/src/controllers/binTransferController.ts
import { Request, Response, NextFunction } from "express";
import { BinTransferService } from "@services/binTransferService";
import { ApiResponse } from "@types/api.types";

export class BinTransferController {
  constructor(private binTransferService: BinTransferService) {}

  async getItemDetails(req: Request, res: Response, next: NextFunction) {
    try {
      const { itemId, subInventory } = req.body;

      const items = await this.binTransferService.getItemDetails(
        itemId,
        subInventory
      );

      const response: ApiResponse = {
        success: true,
        data: items,
        message: "Item details retrieved successfully",
      };

      res.json(response);
    } catch (error) {
      next(error);
    }
  }

  async createBinTransfer(req: Request, res: Response, next: NextFunction) {
    try {
      const result = await this.binTransferService.createBinTransfer({
        ...req.body,
        userId: req.user?.userId, // From JWT
      });

      const response: ApiResponse = {
        success: true,
        data: result,
        message: "Bin transfer created successfully",
      };

      res.status(201).json(response);
    } catch (error) {
      next(error);
    }
  }
}
```

### Route Configuration

```typescript
// backend/src/routes/binTransferRoutes.ts
import { Router } from "express";
import { BinTransferController } from "@controllers/binTransferController";
import { validateRequest } from "@middleware/validation";
import {
  getItemDetailsSchema,
  createBinTransferSchema,
} from "@validators/binTransferSchemas";

export const createBinTransferRoutes = (
  controller: BinTransferController
): Router => {
  const router = Router();

  // Map Spring Boot endpoints to RESTful routes
  router.post(
    "/item-details", // was: /module/bt/getitemdetail
    validateRequest(getItemDetailsSchema),
    controller.getItemDetails.bind(controller)
  );

  router.post(
    "/", // was: /module/bt/insertbintransfer
    validateRequest(createBinTransferSchema),
    controller.createBinTransfer.bind(controller)
  );

  return router;
};

// In main routes file
app.use("/api/v1/bin-transfer", authenticate, binTransferRoutes);
```

### Validation Strategy (Dual-Layer)

```typescript
// backend/src/validators/binTransferSchemas.ts
import { z } from "zod";

// API-level validation (Zod)
export const createBinTransferSchema = z.object({
  body: z.object({
    itemId: z.string().min(1).max(50),
    fromBin: z.string().min(1).max(20),
    toBin: z.string().min(1).max(20),
    quantity: z.number().positive().int(),
    reason: z.string().optional(),
  }),
});

// Business logic validation happens in Oracle package
// XXGS_MOB_UTIL_PKG.INSERT_BIN_TRANSFER will validate:
// - Item exists and is active
// - Bins exist and are valid
// - Sufficient quantity in source bin
// - User has permission
// - Business rules (e.g., restricted bins)
```

## Migration Phases

### Phase 1: Foundation (Week 1)

- [ ] Set up project structure
- [ ] Configure Oracle connection
- [ ] Create base classes (BaseController, BaseService, BaseRepository)
- [ ] Implement OraclePackageService
- [ ] Set up authentication

### Phase 2: Models & Packages (Week 2)

- [ ] Create all Sequelize models (15 entities)
- [ ] Implement Oracle package wrappers (28 packages)
- [ ] Set up model associations
- [ ] Test database connectivity

### Phase 3: Core Modules (Week 3-4)

- [ ] Authentication module
- [ ] User management module
- [ ] Bin transfer module
- [ ] Inventory management basics

### Phase 4: Business Modules (Week 5-6)

- [ ] Order management (Move Order, IO, PO)
- [ ] Physical counting
- [ ] Returns management (RMA, RTV)
- [ ] Specialized modules (Showroom, Spot Check)

### Phase 5: Integration & Testing (Week 7-8)

- [ ] API documentation (Swagger)
- [ ] Integration testing
- [ ] Performance testing
- [ ] Security audit

## Key Architectural Decisions

### 1. Modular Structure

Each module has its own:

- Controller
- Service
- Repository (if needed)
- Validator
- Types
- Routes

### 2. Dependency Injection Pattern

```typescript
// Dependency injection container
const container = {
  // Singletons
  oraclePackageService: new OraclePackageService(),

  // Per-module instances
  binTransfer: {
    repository: new BinTransferRepository(),
    service: null as BinTransferService,
    controller: null as BinTransferController,
  },
};

// Wire dependencies
container.binTransfer.service = new BinTransferService(
  container.oraclePackageService,
  container.binTransfer.repository
);

container.binTransfer.controller = new BinTransferController(
  container.binTransfer.service
);
```

### 3. Error Handling Hierarchy

```typescript
// Custom error classes
export class AppError extends Error {
  constructor(
    message: string,
    public statusCode: number
  ) {
    super(message);
  }
}

export class ValidationError extends AppError {
  constructor(message: string) {
    super(message, 400);
  }
}

export class AuthenticationError extends AppError {
  constructor(message: string = "Authentication failed") {
    super(message, 401);
  }
}

export class NotFoundError extends AppError {
  constructor(resource: string) {
    super(`${resource} not found`, 404);
  }
}
```

### 4. Transaction Management

```typescript
// For complex operations spanning multiple packages
export class TransactionManager {
  async executeInTransaction<T>(
    operation: (connection: Connection) => Promise<T>
  ): Promise<T> {
    const connection = await DatabaseConnection.getOracleConnection();

    try {
      await connection.execute("SET TRANSACTION READ WRITE");
      const result = await operation(connection);
      await connection.commit();
      return result;
    } catch (error) {
      await connection.rollback();
      throw error;
    } finally {
      await connection.close();
    }
  }
}
```

## Best Practices Summary

1. **Reuse Oracle Packages**: Don't rewrite business logic
2. **Type Everything**: Full TypeScript coverage
3. **Validate Twice**: Zod for API, Oracle for business rules
4. **Log Extensively**: Include context in all logs
5. **Handle Errors Gracefully**: Map Oracle errors to HTTP codes
6. **Keep It Modular**: Clear separation between modules
7. **Test Thoroughly**: Unit, integration, and Oracle package tests
8. **Document APIs**: Swagger/OpenAPI for all endpoints

## Final Architecture Flow

```
Client Request
    ↓
Express Router → Validation Middleware (Zod)
    ↓
Controller → Format request/response
    ↓
Service → Business orchestration
    ↓
Oracle Package Service → Execute stored procedures
    ↓
Oracle Database → Business logic + data
    ↓
Response → Formatted JSON to client
```

This architecture ensures:

- Maximum reuse of existing Oracle code [[memory:3434991]]
- Clean separation of concerns
- Type safety throughout
- Scalable and maintainable structure
- Compliance with your Node.js standards
