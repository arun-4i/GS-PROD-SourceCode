# MoConfirm Module Migration Plan - Spring Boot to Node.js TypeScript

**Migration Target**: Java Spring Boot MoConfirm functionality → Node.js TypeScript backend  
**Date Created**: 31-07-25  
**Base URL**: `/module` (included in env)  
**Migration Type**: Pure migration only - using existing packages, no new packages

## 📋 Pre-Migration Analysis Complete

### Java Spring Boot Module Structure Analyzed

- **Controller**: `MoConfirmCO.java` (5 endpoints)
- **Services**: `MoConfirmSO.java`, `PickConfirmSO.java`
- **Repositories**: `MoConfirmRO.java`, `PickConfirmRO.java`
- **Entities**: `MoConfirmEO.java`, `PickConfirmEO.java`, `MoPickConfirmEO.java`, `MoPickConfirmEOT.java`
- **Dependencies**: `MoveOrderPackage.java`, `MobTransLogSO.java`

### Database Tables Identified

- **Primary**: `XXGS_MO_CONFIRMATIONS` (MoConfirm data)
- **Secondary**: `XXGS_PICK_CONFIRMATION_SERIAL` (PickConfirm data)
- **Logging**: `XXGS_MOB_TRANSACTION_LOGS` (Transaction logs)
- **Sequences**: `XXGS_MO_ID_S`, `XXGS_PICK_CONF_SERIAL_ID_S`, `XXGS_MOB_TRANSACTION_ID_S`

### Oracle Package Methods Required

- `XXGS_MOB_UTIL_PKG.MAIN(jsonData, P_MES, P_MES2)` - For JSON processing
- Standard CRUD operations via repositories
- Complex duplicate filtering logic

## 🎯 Migration Target: 5 API Endpoints

### Base Route: `/module/mo/confirm`

1. **POST** `/module/mo/confirm/insertmo` - Insert MO confirmations with duplicate filtering
2. **GET** `/module/mo/confirm/getallmo` - Get all MO confirmations
3. **POST** `/module/mo/confirm/insertpick` - Insert pick confirmations with complex logic
4. **POST** `/module/mo/confirm/insertMoQuickPickJson` - JSON-based MO quick pick processing
5. **POST** `/module/mo/confirm/insertmopick` - Combined MO & Pick confirmation
6. **GET** `/module/mo/confirm/getallpick` - Get all pick confirmations

## 📁 Migration Plan Structure

### Phase 1: Data Layer Foundation

- [ ] **1.1** Create TypeScript interfaces for entities

  - [ ] `MoConfirmEntity` - Mapping `MoConfirmEO.java` (37 fields)
  - [ ] `PickConfirmEntity` - Mapping `PickConfirmEO.java` (28 fields)
  - [ ] `MoPickConfirmEntity` - Mapping `MoPickConfirmEO.java` (List-based wrapper)
  - [ ] `MoPickConfirmWrapper` - Mapping `MoPickConfirmEOT.java` (Iterable-based wrapper)
  - [ ] **Import existing** `MobTransLogEntity` - Already exists in showroom.entity.ts

- [ ] **1.2** Create repository layer (`moConfirmRepo.ts` & `pickConfirmRepo.ts`)

  **MoConfirmRepository (`moConfirmRepo.ts`)**:

  - [ ] Implement `insertMoConfirmations()` - Multi-record insert with `saveAll` pattern
  - [ ] Implement `findAllMoConfirmations()` - Simple findAll query
  - [ ] Implement `checkDuplicateRecord()` - Complex 6-field duplicate check query
  - [ ] **Oracle Package**: Implement `processQuickPickJson()` - Call `XXGS_MOB_UTIL_PKG.MAIN`

  **PickConfirmRepository (`pickConfirmRepo.ts`)**:

  - [ ] Implement `insertPickConfirmations()` - Multi-record insert with `saveAll` pattern
  - [ ] Implement `findAllPickConfirmations()` - Simple findAll query
  - [ ] Implement `checkDuplicatePickRecord()` - 6-field duplicate check (`recordCountPickConfirm`)
  - [ ] Implement `checkDeliveryRecord()` - 4-field RMA delivery check (`recordCountDelivery`)
  - [ ] Implement `getPickMatchCount()` - Delivery detail ID count query (`poMatchCount`)

### Phase 2: Business Logic Layer

- [ ] **2.1** Create service layer (`moConfirmService.ts`)

  - [ ] `insertMoConfirmations()` - Implement complex duplicate filtering logic
    - [ ] Iterator-based duplicate checking with combination keys
    - [ ] Dynamic list building with exclusion logic
    - [ ] Transaction logging for failed records
    - [ ] Return filtered list for insertion
  - [ ] `getAllMoConfirmations()` - Simple retrieval service
  - [ ] `insertPickConfirmations()` - Complex pick confirmation logic
    - [ ] Handle "Out Bound Picking" transaction type filtering
    - [ ] Handle "RMA_DELIVERY" transaction type filtering
    - [ ] Duplicate detection with different field combinations per type
    - [ ] Failed record logging and tracking
  - [ ] `processQuickPickJson()` - JSON payload processing
    - [ ] Call Oracle package `XXGS_MOB_UTIL_PKG.MAIN`
    - [ ] Complex response parsing with error message extraction
    - [ ] Status code mapping (200/400) based on response content
    - [ ] Error message formatting with ~ delimiters
  - [ ] `insertCombinedMoPick()` - Complex combined processing
    - [ ] Process MO confirmations with quantity mapping
    - [ ] Process Pick confirmations with count mapping
    - [ ] Cross-validation between MO picked quantities and pick counts
    - [ ] Return appropriate status based on validation results
  - [ ] `getAllPickConfirmations()` - Simple retrieval service

- [ ] **2.2** Integrate transaction logging
  - [ ] **Import existing** `MobTransLogEntity` from `showroom.entity.ts`
  - [ ] Log failed duplicate records with module name identification
  - [ ] Log processing timestamps and attribute categories

### Phase 3: API Controller Layer

- [ ] **3.1** Create controller (`moConfirmController.ts`)
  - [ ] **POST** `/insertmo` - MO confirmation endpoint
    - [ ] Accept `Iterable<MoConfirmEntity>` request body
    - [ ] Implement complex duplicate filtering workflow
    - [ ] Return `APIResponse` with processed data
  - [ ] **GET** `/getallmo` - Retrieve all MO confirmations
    - [ ] Simple retrieval with `APIResponse` format
  - [ ] **POST** `/insertpick` - Pick confirmation endpoint
    - [ ] Accept `Iterable<PickConfirmEntity>` request body
    - [ ] Handle dual transaction type logic (Out Bound Picking / RMA_DELIVERY)
    - [ ] Complex duplicate checking with different field combinations
  - [ ] **POST** `/insertMoQuickPickJson` - JSON processing endpoint
    - [ ] Accept raw JSON string payload
    - [ ] Process through Oracle package call
    - [ ] Complex response parsing and status determination
  - [ ] **POST** `/insertmopick` - Combined processing endpoint
    - [ ] Accept `MoPickConfirmWrapper` request body
    - [ ] Process both MO and Pick confirmations
    - [ ] Perform cross-validation between quantities and counts
  - [ ] **GET** `/getallpick` - Retrieve all pick confirmations

### Phase 4: Router Configuration & Integration

- [ ] **4.1** Create router (`moConfirmRouter.ts`)

  - [ ] **CRITICAL**: Use `autoRegisterRoutes` pattern (mandatory)
  - [ ] **CRITICAL**: Use `registry` from swagger registry (mandatory)
  - [ ] Route structure: `/module/mo/confirm/*`
  - [ ] Map all 6 endpoints with proper HTTP methods

- [ ] **4.2** Swagger documentation
  - [ ] Auto-generate API documentation via swagger registry
  - [ ] Document complex request/response schemas
  - [ ] Include validation requirements for each endpoint

## 🔧 Technical Implementation Details

### Critical Duplicate Filtering Logic

```typescript
// MO Confirmation - 6-field combination key
const combKey = `${sourceLocationId}-${deliveryDetailId}-${itemId}-${moNumber}-${moLineNumber}-${status}`;

// Pick Confirmation - Out Bound Picking (6-field)
const pickComb = `${deliveryDetailId}-${fromSerialNumber}-${attribute1}-${attribute2}-${attribute3}-${attribute4}`;

// Pick Confirmation - RMA_DELIVERY (4-field)
const rmaComb = `${fromSerialNumber}-${attribute2}-${attribute3}-${transactionType}`;
```

### Oracle Package Integration

```typescript
// Package Call: XXGS_MOB_UTIL_PKG.MAIN(jsonData, P_MES, P_MES2)
const result = await this.executeOraclePackage(
  "XXGS_MOB_UTIL_PKG.MAIN",
  [jsonData],
  ["P_MES", "P_MES2"]
);
```

### Complex Response Processing

```typescript
// Extract error descriptions from package response
const descriptions = value
  .substring(20, value.length - 1)
  .split("\\}, \\{ERROR_DESCRIPTION=");
const errorMsg = descriptions.join("~");
```

## 📋 Quality Checkpoints

### After Phase 1 (Data Layer)

- [ ] All TypeScript interfaces compile without errors
- [ ] Repository methods properly typed
- [ ] Oracle package connection tested
- [ ] Database queries validated

### After Phase 2 (Business Logic)

- [ ] Complex duplicate filtering logic implemented correctly
- [ ] Transaction logging working
- [ ] Oracle package integration functional
- [ ] All service methods under 15 cognitive complexity

### After Phase 3 (API Layer)

- [ ] All endpoints handle request/response correctly
- [ ] Error handling implemented
- [ ] Input validation added
- [ ] Controller methods under 15 cognitive complexity

### After Phase 4 (Integration)

- [ ] `autoRegisterRoutes` pattern used correctly
- [ ] Swagger documentation generated
- [ ] All routes accessible via HTTP
- [ ] Integration tests pass

## 🚫 Critical Migration Requirements

### Mandatory Patterns (From Migration Rules)

- [ ] **MUST** use `import { OracleConnection } from "../config/database"`
- [ ] **MUST** use `autoRegisterRoutes` pattern for all routes
- [ ] **MUST** use `registry` from swagger registry
- [ ] **MUST** keep all methods under 15 cognitive complexity
- [ ] **MUST** use existing `MobTransLogEntity` for transaction logging
- [ ] **NO** console.log statements allowed (use proper logging)
- [ ] **NO** manual route definitions (use autoRegisterRoutes)

### Forbidden Patterns

- [ ] ❌ NO `getOracleConnection` imports
- [ ] ❌ NO console.log/console.error statements
- [ ] ❌ NO manual route definitions
- [ ] ❌ NO naked `any` types without justification
- [ ] ❌ NO new packages beyond existing ones

## 📊 Expected Outcomes

### File Structure Created

```
backend/src/
├── entities/moConfirm.entity.ts          (4 new interfaces + import MobTransLogEntity)
├── repositories/
│   ├── moConfirmRepo.ts                   (4 methods + Oracle package)
│   └── pickConfirmRepo.ts                 (5 methods)
├── services/moConfirmService.ts           (6 service methods)
├── controllers/moConfirmController.ts     (6 route handlers)
└── routes/moConfirmRouter.ts              (autoRegisterRoutes setup)
```

### Database Integration

- Direct Oracle package calls to `XXGS_MOB_UTIL_PKG.MAIN`
- CRUD operations on 2 primary tables + logging
- Complex multi-field duplicate checking
- Transaction logging for audit trail

### API Endpoints Ready

- 6 fully functional endpoints matching Spring Boot exactly
- Complex JSON processing capabilities
- Duplicate filtering with multiple business rules
- Cross-validation between related data sets

## 🔄 Retrospective & Feedback

_To be filled after implementation completion_

### Implementation Results

- [ ] What errors or blockers were encountered?
- [ ] Was the Spring Boot module analysis complete?
- [ ] How effective was the phase-by-phase approach?
- [ ] Were there any missing dependencies identified late?
- [ ] What Oracle package integration challenges occurred?

### Migration Quality Assessment

- [ ] Did all endpoints match Spring Boot behavior exactly?
- [ ] Was duplicate filtering logic correctly implemented?
- [ ] Were complex business rules preserved accurately?
- [ ] Did transaction logging work as expected?
- [ ] Was error handling comprehensive?

### Process Improvements

- [ ] What could be improved in future MoConfirm-style migrations?
- [ ] Were there any Spring Boot patterns not captured in initial analysis?
- [ ] How could Oracle package integration be streamlined?
- [ ] What would make complex business logic migration easier?

---

**Migration Complexity**: High (Complex duplicate filtering, Oracle package integration, multi-entity workflows)  
**Estimated Effort**: 2-3 days for complete migration  
**Dependencies**: Oracle database access, existing transaction logging infrastructure  
**Risk Level**: Medium (Complex business logic and package integration)
