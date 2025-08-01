# GrandStore Spring Boot Architecture Documentation

## Overview

GrandStore is a Warehouse Management System (WMS) integration application built with Spring Boot. This document explains the architecture, flow, and implementation details for developers familiar with Node.js but new to Spring Boot.

## Technology Stack

- **Framework**: Spring Boot 2.x with Spring MVC
- **Database**: Oracle (with extensive stored procedures/packages)
- **ORM**: JPA/Hibernate
- **Authentication**: JWT with Spring Security
- **API Documentation**: Swagger
- **Build Tool**: Maven
- **Java Version**: 8+

## Architecture Overview

### Layered Architecture (Similar to Node.js Clean Architecture)

```
┌──────────────────────────────────────────────┐
│           Controller Layer                   │  ← Like Express Routes + Controllers
│         (REST Endpoints)                     │
├──────────────────────────────────────────────┤
│            Service Layer                     │  ← Business Logic (Like Node.js Services)
│         (Business Logic)                     │
├──────────────────────────────────────────────┤
│          Repository Layer                    │  ← Data Access (Like Node.js Repositories)
│         (JPA/Custom Queries)                 │
├──────────────────────────────────────────────┤
│        Package Calling Layer                 │  ← Oracle Package Wrappers (Unique to this app)
│      (Oracle Stored Procedures)              │
├──────────────────────────────────────────────┤
│           Database Layer                     │  ← Oracle Database
│         (Tables, Views, Packages)            │
└──────────────────────────────────────────────┘
```

## Project Structure

```
src/main/java/com/mobile/integration/grandstores/
├── Controller/              # REST API endpoints (like Express routes)
├── Services/               # Business logic layer
├── Repository/             # Data access layer
├── Entity/                 # JPA entities (like Sequelize models)
├── PackageCalling/         # Oracle package/procedure wrappers
├── Utils/                  # Utility classes
├── ExceptionHandler/       # Global error handling
└── POJO/                   # Plain Old Java Objects (DTOs)
```

## Database Models (Entities)

### Core Entities (15 Total)

1. **UserEO** - User management (`XXGS_WMS_USER` table)
2. **DocumentNumberEO** - Document tracking (`XXGS_PI_DOCUMENT_V` view)
3. **PiCountDetailEO** - Physical inventory counts (`XXGS_PI_COUNT_DETAILS`)
4. **PiLotDetailsEO** - Lot tracking (`XXGS_PI_LOT_DETAILS`)
5. **PiItemCrossRefEO** - Item cross-references (`XXGS_GET_PI_ITEM_CROSS_REF_V`)
6. **PIItemDetailEO** - PI item details (`XXGS_GET_PI_ITEM_DTLS_V`)
7. **PiSupplierEO** - Supplier info (`XXGS_GET_PI_SUPPLIERS_V`)
8. **PiNumberEO** - PI numbers (`XXGS_GET_PI_NUMBER_V`)
9. **PoConfirmationEO** - PO confirmations (`XXGS_PO_CONFIRMATION`)
10. **POLotDetailEO** - PO lot details (`XXGS_PO_LOT_DETAILS`)
11. **PoItemDetailEO** - PO item details (`XXGS_GET_PO_ITEM_DTLS_V`)
12. **PoItemCrossRefEO** - PO item cross-refs (`XXGS_GET_PO_ITEM_CROSS_REF_V`)
13. **PoNumberEO** - PO numbers (`XXGS_GET_PO_NUMBER_V`)
14. **SearchDetailEO** - Search functionality (`XXGS_GET_SEARCH_DETAILS_V`)
15. **ShipmentRefEO** - Shipment references (`XXGS_GET_SHIPMENT_REF_V`)

## API Routes and Their Functions

### Authentication Module

- **POST** `/module/authentication` - User login
  - Validates credentials via Oracle package
  - Returns JWT token and user menu
  - Package: `XXGS_MOB_UTIL_PKG.GET_USER_AUTH`

### User Management

- **GET** `/module/newuser/getalluser` - Get all users
- **POST** `/module/newuser/insertuser` - Create new user
- **POST** `/module/newuser/updateuser` - Update user
- **POST** `/module/newuser/deleteuser` - Delete user
- **GET** `/module/newuser/getusermenu` - Get user menu

### Bin Transfer Module

- **POST** `/module/bt/getitemdetail` - Get item details
  - Package: `XXGS_MOB_UTIL_PKG.GET_ITEM_DETAILS`
- **POST** `/module/bt/getitemcrossref` - Get item cross-reference
  - Package: `XXGS_MOB_UTIL_PKG.GET_ITEM_CROSS_REF_DTLS`
- **POST** `/module/bt/insertbintransfer` - Create bin transfer
  - Package: `XXGS_MOB_UTIL_PKG.INSERT_BIN_TRANSFER`
- **POST** `/module/bt/getbintransfertrack` - Track bin transfers
- **POST** `/module/bt/updatebintransfer` - Update bin transfer

### Inventory Management

- **POST** `/module/gs/getbinlocation` - Get bin locations
  - Package: `XXGS_MOB_BIN_LOC_PKG.GET_BIN_LOCATION`
- **POST** `/module/gs/updatebinlocation` - Update bin location
- **POST** `/module/physicalcounting/*` - Physical counting operations
- **POST** `/module/spotcheck/*` - Spot check operations
- **POST** `/module/stockupdate/*` - Stock update operations

### Order Management

- **POST** `/module/moveorder/*` - Move order operations
- **POST** `/module/iodelivery/*` - Internal order deliveries
- **POST** `/module/ioreceipt/*` - Internal order receipts
- **POST** `/module/podelivery/*` - Purchase order deliveries

### Returns Management

- **POST** `/module/rmadelivery/*` - RMA deliveries
- **POST** `/module/rmareceipt/*` - RMA receipts
- **POST** `/module/rtvconfirm/*` - Return to vendor confirmations

## Oracle Package Dependencies (28 Total)

### Core Packages

1. **XXGS_MOB_UTIL_PKG** - Core utilities and authentication
2. **AuthenticationPackage** - User authentication
3. **UpdatePackage** - General updates

### Module-Specific Packages

Each module has dedicated Oracle packages that handle:

- Data validation
- Business logic
- Complex queries
- Transaction management

Example package structure:

```sql
PACKAGE XXGS_MOB_UTIL_PKG IS
  PROCEDURE GET_USER_AUTH(
    P_USER_NAME IN VARCHAR2,
    P_PASSWORD IN VARCHAR2,
    P_GET_USER_AUTH OUT SYS_REFCURSOR,
    P_MENU OUT SYS_REFCURSOR
  );
END XXGS_MOB_UTIL_PKG;
```

## Request/Response Flow

### Typical Request Flow:

1. **HTTP Request** → Controller
2. **Controller** → Validates request, calls Service
3. **Service** → Business logic, calls Repository/Package
4. **Package/Repository** → Database operations
5. **Response** → Formatted JSON response

### Example: Authentication Flow

```java
// 1. Controller receives POST /module/authentication
@RequestMapping(value = "/authentication", method = RequestMethod.POST)
public ResponseEntity<APIResponse> getAuthentication(@RequestBody Map<String, Object> content) {
    return authenticationso.getAuthenticationPkg(content);
}

// 2. Service processes request
public ResponseEntity<APIResponse> getAuthenticationPkg(Map<String, Object> content) {
    // Extract credentials
    String username = (String) content.get("userName");
    String password = (String) content.get("password");

    // 3. Call Oracle package
    Map<String, Object> result = mobileUtilspk.getUserAuth(username, password);

    // 4. Generate JWT token
    String token = jwtUtil.generateToken(username);

    // 5. Return response
    return ResponseEntity.ok(new APIResponse(result, 200, "Success"));
}
```

## Validation Patterns

### Three Levels of Validation:

1. **Controller Level** - Basic input validation
   - Required fields
   - Data types
   - Format validation

2. **Service Level** - Business rules
   - Cross-field validation
   - State validation

3. **Oracle Package Level** - Database constraints
   - Referential integrity
   - Business logic validation
   - Complex rules

## Security Architecture

### JWT Authentication:

- Secret Key: `4i_Mobile_Apps`
- Token Expiry: 150 hours
- Token contains: userId, userName, personId

### Public Endpoints (No Auth Required):

- `/module/authentication`
- `/module/temp`
- `/actuator/health`

### Protected Endpoints:

- All other endpoints require valid JWT token
- Token validated via `JwtRequestFilter`

## Error Handling

### Global Exception Handler:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedExcep.class)
    public ResponseEntity<APIResponse> handleAccessDenied(AccessDeniedExcep ex) {
        APIResponse response = new APIResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(401);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
```

### Standard Error Response:

```json
{
  "data": null,
  "status": 401,
  "message": "Error message"
}
```

## Best Practices Followed

1. **Separation of Concerns** - Clear layer separation
2. **Dependency Injection** - Spring's @Autowired
3. **Transaction Management** - @Transactional annotations
4. **Logging** - SLF4J with contextual information
5. **Exception Handling** - Centralized error handling
6. **Security** - JWT + Spring Security
7. **API Documentation** - Swagger annotations

## Key Differences from Node.js

| Aspect        | Spring Boot            | Node.js Equivalent                   |
| ------------- | ---------------------- | ------------------------------------ |
| DI Container  | Spring IoC             | Manual or libraries like InversifyJS |
| ORM           | JPA/Hibernate          | Sequelize                            |
| Routing       | @RequestMapping        | Express Router                       |
| Middleware    | Filters/Interceptors   | Express Middleware                   |
| Configuration | application.properties | .env files                           |
| Build         | Maven/Gradle           | npm                                  |

## Database Interaction Patterns

### Direct JPA Query:

```java
@Repository
public interface UserRepository extends JpaRepository<UserEO, BigDecimal> {
    UserEO findByUserName(String userName);
}
```

### Native Query:

```java
@Query(value = "SELECT * FROM XXGS_WMS_USER WHERE USER_NAME = ?1", nativeQuery = true)
UserEO findUserByNameNative(String userName);
```

### Oracle Package Call:

```java
SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
    .withSchemaName(schemaName)
    .withCatalogName("XXGS_MOB_UTIL_PKG")
    .withProcedureName("GET_USER_AUTH");
```

## Summary

GrandStore follows a traditional Spring Boot architecture with heavy reliance on Oracle stored procedures. The application serves as a backend API for mobile WMS operations, handling everything from user authentication to complex warehouse operations like bin transfers, inventory counts, and order management. The key to understanding this application is recognizing that most business logic resides in Oracle packages rather than in Java code, with the Spring Boot layer primarily handling HTTP concerns, security, and orchestration of database calls.
