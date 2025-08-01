# GrandStore Spring Boot to Node.js Unified Migration Plan

## Executive Summary

This unified plan consolidates all migration tasks for converting the GrandStore WMS Integration from Spring Boot to Node.js/TypeScript. **Key principle: NO database migrations - use existing Oracle database as-is**.

## Project Overview

**Current Stack:**

- Spring Boot 2.x with Maven
- Oracle Database with 28+ stored procedures/packages
- JWT Authentication
- 30+ REST endpoints
- 15 JPA entities

**Target Stack:**

- Node.js with TypeScript
- Express.js framework
- node-oracledb (direct Oracle access, no ORM)
- Oracle packages (reused as-is)
- JWT with same secret key
- All code in `backend/` folder

## Core Architectural Decisions

### 1. Database Strategy

- **NO MIGRATIONS**: Use existing Oracle tables/views/packages
- **Direct Oracle Access**: Use node-oracledb for all database operations, mapping to existing schema
- **Business Logic**: Keep in Oracle packages (90% of operations)
- **Direct Inserts**: Only for simple tables without business rules (10%)

### 2. Validation Strategy

- **API Level**: Zod schemas for request validation (shape, types, constraints)
- **Business Level**: Oracle packages for business rules and complex validation
- **Dual-layer approach**: Clean separation between API and business validation

### 3. Architecture Pattern

```
Controller → Service → OraclePackageService → Oracle DB
                 ↓
            Repository (optional, for simple CRUD)
```

## Detailed Migration Tasks

### Phase 1: Foundation Setup (Week 1)

#### 1.1 Project Initialization

- [ ] Create `backend/` folder structure following .cursorrules standards
- [ ] Initialize Node.js project with TypeScript
- [ ] Install core dependencies:
  ```json
  {
    "express": "^4.18.0",
    "oracledb": "^6.0.0",
    "zod": "^3.22.0",
    "jsonwebtoken": "^9.0.0",
    "winston": "^3.10.0",
    "dotenv": "^16.3.0"
  }
  ```
- [ ] Configure TypeScript with path aliases (@config, @controllers, etc.)
- [ ] Set up ESLint and Prettier

#### 1.2 Core Infrastructure

- [ ] Create `src/config/database.ts` with Oracle connection pool using node-oracledb
- [ ] Create `src/config/env.ts` for environment variables
- [ ] Create `src/utils/logger.ts` with Winston configuration
- [ ] Create `src/utils/errors.ts` with error hierarchy:
  - AppError (base)
  - ValidationError
  - AuthenticationError
  - NotFoundError
  - DatabaseError
- [ ] Create `src/middleware/errorHandler.ts` for global error handling

#### 1.3 Oracle Package Service

- [ ] Create `src/services/oracle/oraclePackageService.ts`:
  ```typescript
  export class OraclePackageService {
    async callPackage<T>(
      packageProcedure: string,
      params: BindParameters,
      options?: { autoCommit?: boolean; transaction?: Connection }
    ): Promise<T>;
  }
  ```
- [ ] Implement Oracle error mapping
- [ ] Add connection pooling
- [ ] Add transaction support

### Phase 2: Database Models & Package Wrappers (Week 2)

#### 2.1 Model Creation (15 Models)

**User & Authentication:**

- [ ] `User.ts` → XXGS_WMS_USER table
  - Fields: userId, userName, personId, fullName, emailAddress, status, lastLogonDate

**PI (Proforma Invoice) Module:**

- [ ] `PiCountDetail.ts` → XXGS_PI_COUNT_DETAILS table
- [ ] `PiLotDetails.ts` → XXGS_PI_LOT_DETAILS table
- [ ] `PiItemCrossRef.ts` → XXGS_GET_PI_ITEM_CROSS_REF_V view
- [ ] `PIItemDetail.ts` → XXGS_GET_PI_ITEM_DTLS_V view
- [ ] `PiSupplier.ts` → XXGS_GET_PI_SUPPLIERS_V view
- [ ] `PiNumber.ts` → XXGS_GET_PI_NUMBER_V view

**PO (Purchase Order) Module:**

- [ ] `PoConfirmation.ts` → XXGS_PO_CONFIRMATION table
- [ ] `POLotDetail.ts` → XXGS_PO_LOT_DETAILS table
- [ ] `PoItemDetail.ts` → XXGS_GET_PO_ITEM_DTLS_V view
- [ ] `PoItemCrossRef.ts` → XXGS_GET_PO_ITEM_CROSS_REF_V view
- [ ] `PoNumber.ts` → XXGS_GET_PO_NUMBER_V view

**Supporting Entities:**

- [ ] `DocumentNumber.ts` → XXGS_PI_DOCUMENT_V view
- [ ] `SearchDetail.ts` → XXGS_GET_SEARCH_DETAILS_V view
- [ ] `ShipmentRef.ts` → XXGS_GET_SHIPMENT_REF_V view

#### 2.2 Oracle Package Wrappers (28 Packages)

**Core Packages:**

- [ ] `authenticationPackage.ts` → XXGS_MOB_UTIL_PKG.GET_USER_AUTH
- [ ] `mobileUtilsPackage.ts` → General utility procedures
- [ ] `updatePackage.ts` → Update operations

**Module-Specific Packages:**

- [ ] `binTransferPackage.ts` → Bin transfer operations
- [ ] `deliveryAppsPackage.ts` → Delivery management
- [ ] `gsLocationPackage.ts` → Location management
- [ ] `inventoryPackage.ts` → Inventory operations
- [ ] `moveOrderPackage.ts` → Move order processing
- [ ] `physicalCountingPackage.ts` → Physical count operations
- [ ] `poDeliveryPackage.ts` → PO delivery operations
- [ ] `rmaPackage.ts` → RMA operations
- [ ] `rtvPackage.ts` → Return to vendor operations
- [ ] `showroomPackage.ts` → Showroom operations
- [ ] `spotCheckPackage.ts` → Spot check operations
- [ ] `stockUpdatePackage.ts` → Stock update operations
- [ ] (Continue for all 28 packages...)

### Phase 3: API Implementation (Week 3-4)

#### 3.1 Authentication Module

- [ ] Create `authenticationController.ts`:
  - POST `/api/v1/auth/login` (was: /module/authentication)
  - POST `/api/v1/auth/validate` (was: /module/authen)
  - GET `/api/v1/auth/health` (was: /module/temp)
- [ ] Create `authenticationService.ts` with JWT generation
- [ ] Create `authenticationValidator.ts` with Zod schemas
- [ ] Create JWT middleware for protected routes

#### 3.2 User Management Module

- [ ] Create `userController.ts`:
  - GET `/api/v1/users` (was: /module/newuser/getalluser)
  - POST `/api/v1/users` (was: /module/newuser/insertuser)
  - PUT `/api/v1/users/:id` (was: /module/newuser/updateuser)
  - DELETE `/api/v1/users/:id` (was: /module/newuser/deleteuser)
  - GET `/api/v1/users/:id/menu` (was: /module/newuser/getusermenu)
- [ ] Create `userService.ts` and `userRepository.ts`
- [ ] Create user validation schemas

#### 3.3 Bin Transfer Module

- [ ] Create `binTransferController.ts`:
  - POST `/api/v1/bin-transfer/item-details` (was: /module/bt/getitemdetail)
  - POST `/api/v1/bin-transfer/item-cross-ref` (was: /module/bt/getitemcrossref)
  - POST `/api/v1/bin-transfer` (was: /module/bt/insertbintransfer)
  - GET `/api/v1/bin-transfer/track` (was: /module/bt/getbintransfertrack)
  - PUT `/api/v1/bin-transfer/:id` (was: /module/bt/updatebintransfer)
- [ ] Create service, repository, and validators

#### 3.4 Inventory Management Modules

- [ ] **GS Location Module:**
  - POST `/api/v1/inventory/bin-location` (was: /module/gs/getbinlocation)
  - PUT `/api/v1/inventory/bin-location` (was: /module/gs/updatebinlocation)
- [ ] **Physical Counting Module:**
  - All `/api/v1/physical-count/*` endpoints
- [ ] **Spot Check Module:**
  - All `/api/v1/spot-check/*` endpoints
- [ ] **Stock Update Module:**
  - All `/api/v1/stock-update/*` endpoints

#### 3.5 Order Management Modules

- [ ] **Move Order Module:**
  - All `/api/v1/move-order/*` endpoints
- [ ] **IO Delivery Module:**
  - All `/api/v1/io-delivery/*` endpoints
- [ ] **IO Receipt Module:**
  - All `/api/v1/io-receipt/*` endpoints
- [ ] **PO Delivery Module:**
  - All `/api/v1/po-delivery/*` endpoints

#### 3.6 Returns Management Modules

- [ ] **RMA Module:**
  - All `/api/v1/rma/*` endpoints
- [ ] **RTV Module:**
  - All `/api/v1/rtv/*` endpoints

### Phase 4: Middleware & Security (Week 5)

#### 4.1 Authentication & Authorization

- [ ] Implement JWT validation middleware
- [ ] Create role-based access control (RBAC)
- [ ] Implement rate limiting
- [ ] Configure CORS

#### 4.2 Request Processing

- [ ] Create validation middleware using Zod
- [ ] Implement request sanitization
- [ ] Add request logging middleware
- [ ] Create response formatting middleware

### Phase 5: Testing & Documentation (Week 6-7)

#### 5.1 Testing Strategy

- [ ] **Unit Tests:**
  - Service layer tests with mocked Oracle packages
  - Controller tests with mocked services
  - Utility function tests
- [ ] **Integration Tests:**
  - API endpoint tests
  - Oracle package integration tests
  - Authentication flow tests
- [ ] **E2E Tests:**
  - Critical business flows
  - Multi-step transactions

#### 5.2 Documentation

- [ ] Generate Swagger/OpenAPI documentation
- [ ] Create API migration guide for clients
- [ ] Document Oracle package interfaces
- [ ] Create deployment documentation

### Phase 6: Deployment Preparation (Week 8)

#### 6.1 Environment Setup

- [ ] Create `.env.example` with all required variables
- [ ] Set up environment-specific configurations
- [ ] Configure logging for production
- [ ] Set up health check endpoints

#### 6.2 Performance & Monitoring

- [ ] Implement connection pooling optimization
- [ ] Add performance monitoring
- [ ] Set up error tracking
- [ ] Create database connection health checks

## Implementation Examples

### Example 1: Authentication Flow

```typescript
// Controller
async login(req: Request, res: Response) {
  const { username, password } = req.body;
  const result = await this.authService.authenticate(username, password);
  res.json({ success: true, data: result });
}

// Service
async authenticate(username: string, password: string) {
  // Call Oracle package
  const authResult = await this.authPackage.getUserAuth(username, password);

  if (!authResult.P_GET_USER_AUTH?.length) {
    throw new AuthenticationError('Invalid credentials');
  }

  // Generate JWT
  const token = this.jwtService.generateToken({
    userId: authResult.P_GET_USER_AUTH[0].USER_ID,
    userName: authResult.P_GET_USER_AUTH[0].USER_NAME
  });

  return { token, user: authResult.P_GET_USER_AUTH[0], menu: authResult.P_MENU };
}

// Oracle Package Wrapper
async getUserAuth(username: string, password: string) {
  return this.oraclePackageService.callPackage(
    'XXGS_MOB_UTIL_PKG.GET_USER_AUTH',
    {
      P_USER_NAME: username,
      P_PASSWORD: password,
      P_GET_USER_AUTH: { type: oracledb.CURSOR, dir: oracledb.BIND_OUT },
      P_MENU: { type: oracledb.CURSOR, dir: oracledb.BIND_OUT }
    }
  );
}
```

### Example 2: Bin Transfer Creation

```typescript
// Zod Validation (API Level)
const createBinTransferSchema = z.object({
  itemId: z.string().min(1).max(50),
  fromBin: z.string().min(1).max(20),
  toBin: z.string().min(1).max(20),
  quantity: z.number().positive().int()
});

// Service (Orchestration)
async createBinTransfer(data: CreateBinTransferDto) {
  // API validation already done by middleware

  // Call Oracle package (business validation + insert)
  const result = await this.oraclePackageService.callPackage(
    'XXGS_MOB_UTIL_PKG.INSERT_BIN_TRANSFER',
    {
      P_ITEM_ID: data.itemId,
      P_FROM_BIN: data.fromBin,
      P_TO_BIN: data.toBin,
      P_QUANTITY: data.quantity,
      P_USER_ID: data.userId,
      P_STATUS: { dir: oracledb.BIND_OUT, type: oracledb.STRING },
      P_MESSAGE: { dir: oracledb.BIND_OUT, type: oracledb.STRING }
    }
  );

  if (result.P_STATUS !== 'SUCCESS') {
    throw new AppError(result.P_MESSAGE, 400);
  }

  return { success: true, message: result.P_MESSAGE };
}
```

## Migration Checklist

### Pre-Migration

- [ ] Document all Oracle packages and their parameters
- [ ] Map all Spring Boot endpoints to new REST endpoints
- [ ] Identify tables that need models
- [ ] Review JWT implementation for compatibility

### During Migration

- [ ] Test each Oracle package call
- [ ] Verify JWT tokens work with existing clients
- [ ] Ensure error messages match Spring Boot responses
- [ ] Validate all Zod schemas against actual requests

### Post-Migration

- [ ] Performance testing with production-like data
- [ ] Security audit of all endpoints
- [ ] Load testing for concurrent users
- [ ] Rollback plan if issues arise

## Risk Mitigation

1. **Oracle Package Changes**: Document all package interfaces thoroughly
2. **JWT Compatibility**: Use same secret key and token structure
3. **Performance**: Monitor Oracle connection pool usage
4. **Data Types**: Carefully map Oracle types to JavaScript types
5. **Error Handling**: Maintain consistent error codes and messages

## Success Criteria

- All 30+ endpoints migrated and tested
- JWT authentication working with existing mobile apps
- Oracle package calls performing within acceptable limits
- Zero data loss or corruption
- API response times comparable to Spring Boot version

## Timeline Summary

- **Week 1**: Foundation and infrastructure
- **Week 2**: Models and Oracle package wrappers
- **Week 3-4**: API implementation (controllers, services)
- **Week 5**: Security and middleware
- **Week 6-7**: Testing and documentation
- **Week 8**: Deployment preparation

Total: **8 weeks** for complete migration with thorough testing

---

This unified plan combines all aspects from the previous 4 task files into a single, comprehensive migration guide. Follow this plan sequentially for a successful migration from Spring Boot to Node.js.
