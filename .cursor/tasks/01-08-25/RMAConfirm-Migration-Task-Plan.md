# RMAConfirm Module Migration Task Plan

**Date:** 01-08-25  
**Migration Type:** Spring Boot to Node.js  
**Spring Boot Source:** `src/main/java/com/mobile/integration/grandstores/RMAConfirm/`

## Overview

Pure migration of the RMAConfirm module from Spring Boot to Node.js/TypeScript following existing patterns from showroom and moConfirm implementations. **CRITICAL: Must reuse existing implementations wherever possible to avoid code duplication.** The module handles Return Merchandise Authorization (RMA) confirmations with complex duplicate filtering, transaction logging, and Oracle package integration.

**🎯 Zero Duplication Goal:** Leverage existing entities, services, repositories, utilities, and patterns. Only create new code where genuinely needed.

## Source Files Analysis

### Spring Boot Files to Migrate:

1. **Controller:** `RMAConfirmCO.java` (65 lines, 3 endpoints)
2. **Service:** `RMAConfirmSO.java` (364 lines, complex business logic)
3. **Repository:** `RMAConfirmRO.java` (63 lines, 2 native SQL queries)
4. **Entities:**
   - `RMAConfirmEO.java` (621 lines, 35+ fields, main entity)
   - `RMAConfirmMO.java` (37 lines, wrapper entity)

### Endpoints to Migrate:

1. `POST /module/rma/confirm/insertmo` - Insert RMA confirmations
2. `POST /module/rma/confirm/rmainsertmo` - Combined RMA and MO confirmation
3. `GET /module/rma/confirm/getallmo` - Get all RMA confirmations

### Key Features:

- Complex duplicate record filtering for RMA_DELIVERY and RMA_RECEIPT types
- Oracle package integration for location validation
- Transaction logging with CLOB data
- Integration with PickConfirm module
- Comprehensive entity with 35+ database fields
- Custom native SQL queries for duplicate checking

## Target Structure (Node.js)

Following existing patterns from `backend/src/`:

- **Entity:** `entities/rmaConfirm.entity.ts`
- **Controller:** `controllers/rmaConfirmController.ts`
- **Service:** `services/rmaConfirmService.ts`
- **Repository:** `repositories/rmaConfirmRepo.ts`
- **Router:** `routes/rmaConfirmRouter.ts`
- **Validator:** `validators/rmaConfirmValidator.ts`

## Migration Task Checklist

> **🚨 CRITICAL PRINCIPLE: NO CODE DUPLICATION**  
> **Before implementing ANY component, ALWAYS check Phase 0 findings to reuse existing implementations.**  
> **Use existing patterns, extend existing services, and follow established conventions.**  
> **Mark items as ✅ SKIPPED if already implemented, ♻️ REUSED if extended/leveraged.**

### Phase 0: Pre-Migration Analysis & Existing Code Review

- [ ] 0.1 **Comprehensive Backend Code Audit**

  - [ ] Search for existing RMA-related entities, services, controllers, repositories
  - [ ] Check if `RmaConfirm` or similar entities already exist in `backend/src/entities/`
  - [ ] Check if RMA-related services exist in `backend/src/services/`
  - [ ] Check if RMA-related repositories exist in `backend/src/repositories/`
  - [ ] Check if RMA-related controllers exist in `backend/src/controllers/`
  - [ ] Check if RMA-related routers exist in `backend/src/routes/`
  - [ ] Check if RMA-related validators exist in `backend/src/validators/`

- [ ] 0.2 **Oracle Package Service Integration Review**

  - [ ] Verify if `POServicePkg.validateLocPkgCall()` equivalent already exists
  - [ ] Check existing Oracle package service implementations in backend
  - [ ] Review existing location validation patterns in `showroomRepo.ts`, `moConfirmRepo.ts`
  - [ ] Check if `XXGS_MOB_UTIL_PKG.VALIDATE_LOC` is already implemented
  - [ ] Verify existing transaction logging patterns and services

- [ ] 0.3 **Transaction Logging Service Review**

  - [ ] Check if `MobTransLogSO` equivalent exists in Node.js backend
  - [ ] Review existing transaction logging implementations
  - [ ] Check CLOB handling patterns in existing services
  - [ ] Verify if transaction log entity interfaces already exist

- [ ] 0.4 **Pick Confirmation Integration Review**

  - [ ] Check if `PickConfirmSO` equivalent is implemented in Node.js
  - [ ] Review existing pick confirmation entities and services
  - [ ] Check if pick confirmation repository methods exist
  - [ ] Verify existing integration patterns for combined operations

- [ ] 0.5 **Utility Services Review**

  - [ ] Check if `DateUtils` equivalent exists in Node.js backend
  - [ ] Review existing date handling utilities
  - [ ] Check existing Oracle sequence handling patterns
  - [ ] Verify existing error handling and response formatting utilities

- [ ] 0.6 **Endpoint Conflicts Review**

  - [ ] Check if `/module/rma/confirm/*` routes already exist
  - [ ] Verify no conflicts with existing endpoint registrations
  - [ ] Review existing router patterns and integration points
  - [ ] Check if RMA_CONFIRM constant exists in `end-points.ts`

- [ ] 0.7 **Documentation of Existing Implementations**
  - [ ] Document all existing code that can be reused
  - [ ] Note which components need to be created vs. extended/reused
  - [ ] Identify existing patterns to follow for consistency
  - [ ] Update migration plan based on findings (mark items as ✅ if already implemented)

### Phase 1: Entity & Type Definitions

- [ ] 1.1 Create `backend/src/entities/rmaConfirm.entity.ts` _(Skip if already exists from Phase 0 review)_
  - [ ] Define `RmaConfirmEntity` interface (35+ fields from RMAConfirmEO) _(or extend existing if found)_
  - [ ] Define `RmaConfirmMO` interface (wrapper with rmaConfirm and pickConfirm arrays) _(or reuse existing)_
  - [ ] **REUSE** existing `APIResponse` interface (found in other entity files)
  - [ ] **REUSE** existing `MobTransLogEntity` interface if found, otherwise create
  - [ ] Add proper JSDoc documentation for all NEW interfaces only
  - [ ] Use proper TypeScript types (string, number, Date, etc.)

### Phase 2: Repository Layer

- [ ] 2.1 Create `backend/src/repositories/rmaConfirmRepo.ts` _(Skip if exists from Phase 0)_
  - [ ] **REUSE** existing Oracle connection pool pattern from other repositories
  - [ ] Migrate `saveAll()` method for bulk insert operations _(follow existing patterns)_
  - [ ] Migrate `findAll()` method for retrieving all records _(follow existing patterns)_
  - [ ] Migrate `recordCountForDelivery()` custom native query (9 parameters)
  - [ ] Migrate `recordCountForReceipt()` custom native query (7 parameters)
  - [ ] **REUSE OR EXTEND** existing `validateLocation()` method if found, otherwise create for XXGS_MOB_UTIL_PKG.VALIDATE_LOC
  - [ ] **FOLLOW** existing node-oracledb patterns with proper bind variables (no string interpolation)
  - [ ] **REUSE** existing error handling and connection management patterns
  - [ ] **FOLLOW** existing Oracle sequence handling patterns for ID generation (XXGS_RMA_ID_S)

### Phase 3: Service Layer

- [ ] 3.1 Create `backend/src/services/rmaConfirmService.ts` _(Skip if exists from Phase 0)_
  - [ ] **FOLLOW** existing service constructor patterns with repository dependencies
  - [ ] Migrate `insertRMAConfirmRO()` method with complex duplicate filtering
  - [ ] Migrate `insertRMAConfirmMO()` method for combined operations
  - [ ] Migrate `getRMAConfirmRO()` method for retrieving all records
  - [ ] Migrate `recordCheckforDelivery()` validation method
  - [ ] Migrate `recordCheckforReceipt()` validation method
  - [ ] **REUSE OR EXTEND** existing transaction logging service (module names: "RMA - insertmo", "Item Check Dispatch")
  - [ ] **REUSE OR EXTEND** existing Oracle package integration for location validation
  - [ ] Implement location validation logic: "200" = valid, "400" = "Invalid Locator" error
  - [ ] Maintain exact duplicate filtering logic for both transaction types
  - [ ] Preserve combination key generation for duplicate checking
  - [ ] **FOLLOW** existing error handling and response formatting patterns

### Phase 4: Controller Layer

- [ ] 4.1 Create `backend/src/controllers/rmaConfirmController.ts` _(Skip if exists from Phase 0)_
  - [ ] **FOLLOW** existing class-based controller patterns (showroomController.ts, moConfirmController.ts)
  - [ ] Migrate `insertRMAConfirm()` endpoint handler _(follow existing insert patterns)_
  - [ ] Migrate `insertRMAandMOconfirm()` endpoint handler _(follow existing combined operation patterns)_
  - [ ] Migrate `getBinTransfer()` endpoint handler _(maps to getallmo, follow existing get patterns)_
  - [ ] **REUSE** existing request validation patterns in controller methods
  - [ ] **FOLLOW** existing synchronous Express handler patterns (not async/await)
  - [ ] **REUSE** existing error handling and response formatting patterns
  - [ ] **FOLLOW** existing JSDoc documentation standards

### Phase 5: Validation Layer

- [ ] 5.1 Create `backend/src/validators/rmaConfirmValidator.ts` _(Skip if exists from Phase 0)_
  - [ ] **FOLLOW** existing Zod schema patterns (userValidator.ts, etc.)
  - [ ] Create Zod schema for RMA confirmation insert request _(follow existing patterns)_
  - [ ] Create Zod schema for combined RMA-MO confirmation request _(follow existing patterns)_
  - [ ] Create Zod schema for get all confirmations response _(follow existing patterns)_
  - [ ] **REUSE** existing validation patterns for entity fields
  - [ ] **REUSE** existing required field validation patterns
  - [ ] **REUSE** existing date format validation patterns
  - [ ] **REUSE** existing numeric field validation patterns for BigDecimal equivalents

### Phase 6: Router Configuration

- [ ] 6.1 Create `backend/src/routes/rmaConfirmRouter.ts` _(Skip if exists from Phase 0)_
  - [ ] **FOLLOW** existing autoRegisterRoutes pattern (userRouter.ts, showroomRouter.ts, moConfirmRouter.ts)
  - [ ] Configure route: `POST /insertmo` _(follow existing POST route patterns)_
  - [ ] Configure route: `POST /rmainsertmo` _(follow existing POST route patterns)_
  - [ ] Configure route: `GET /getallmo` _(follow existing GET route patterns)_
  - [ ] **REUSE** existing Swagger documentation patterns for all endpoints
  - [ ] **FOLLOW** existing request/response schema integration patterns
  - [ ] Use exact same endpoint paths as Spring Boot
  - [ ] **FOLLOW** existing tags and descriptions patterns

### Phase 7: Integration & Endpoint Registration

- [ ] 7.1 Update `backend/src/routes/end-points.ts`
  - [ ] Add `RMA_CONFIRM: "/module/rma/confirm"` constant
- [ ] 7.2 Update `backend/src/routes/index.ts`
  - [ ] Import rmaConfirmRouter
  - [ ] Register router with END_POINTS.RMA_CONFIRM
- [ ] 7.3 Verify exact endpoint URLs match Spring Boot:
  - [ ] `POST /module/rma/confirm/insertmo`
  - [ ] `POST /module/rma/confirm/rmainsertmo`
  - [ ] `GET /module/rma/confirm/getallmo`

### Phase 8: Dependencies & Package Integration

- [ ] 8.1 Add necessary dependencies
  - [ ] Verify node-oracledb integration
  - [ ] Verify Oracle package calling functionality
  - [ ] Add any missing dependencies to package.json
- [ ] 8.2 Oracle Package Integration
  - [ ] Verify XXGS_MOB_UTIL_PKG.VALIDATE_LOC package is accessible
  - [ ] Test location validation with sample subinventory/locator combinations
  - [ ] Ensure proper Oracle package response handling (REF_CURSOR to "200"/"400")
  - [ ] Maintain exact validation logic from Spring Boot
  - [ ] Verify transaction logging integration works with CLOB data

### Phase 9: Testing & Validation

- [ ] 9.1 Unit Testing
  - [ ] Test entity interfaces and type definitions
  - [ ] Test repository methods with mock data
  - [ ] Test service methods with complex duplicate filtering
  - [ ] Test controller handlers with various request scenarios
- [ ] 9.2 Integration Testing
  - [ ] Test complete endpoint flows
  - [ ] Test Oracle database integration
  - [ ] Test duplicate record filtering scenarios
  - [ ] Test transaction logging functionality
- [ ] 9.3 Endpoint Verification
  - [ ] Verify exact endpoint URLs work
  - [ ] Test request/response formats match Spring Boot
  - [ ] Verify Swagger documentation displays correctly

### Phase 10: Documentation & Cleanup

- [ ] 10.1 Swagger Documentation
  - [ ] Verify all endpoints appear in Swagger UI
  - [ ] Test request/response schemas in Swagger
  - [ ] Add comprehensive endpoint descriptions
  - [ ] Add proper tags: "RMA Confirm"
- [ ] 10.2 Code Review
  - [ ] Run linter and fix any issues
  - [ ] Verify TypeScript compilation
  - [ ] Check for any remaining Spring Boot references
  - [ ] Ensure all imports use proper TypeScript aliases

## Validations & Oracle DB Packages Analysis

### Oracle Package Dependencies:

1. **XXGS_MOB_UTIL_PKG.VALIDATE_LOC**

   - **Method:** `POServicePkg.validateLocPkgCall(p_subinv, p_loc)`
   - **Input:** Subinventory code, Locator code
   - **Output:** P_LOC_RESULT (REF_CURSOR) - "200" for valid, "400" for invalid
   - **Usage:** Location validation for RMA_DELIVERY transactions
   - **Migration:** Implement via existing `oraclePackageService` pattern

2. **XXGS_MOB_UTIL_PKG.GET_PO_FOR_RCPT_GEN** (if needed)
   - **Method:** `POServicePkg.getPORCPTGen(P_PO_NUMBER, P_RELEASE_NUM)`
   - **Output:** P_PO_RCPT_GEN_RS (REF_CURSOR)
   - **Migration:** Use existing Oracle package service infrastructure

### Validation Logic Requirements:

1. **Location Validation**

   - Validate subinventory and locator combination via Oracle package
   - Only proceed if validation returns "200" status
   - Reject with "Invalid Locator" error if returns "400"

2. **Duplicate Record Validation**

   - **For RMA_DELIVERY:** Check combination of (transactionType, receiptNumber, lineNumber, orderHeaderId, orderLineId, attribute3, attribute10, itemId, status)
   - **For RMA_RECEIPT:** Check combination of (transactionType, lineNumber, orderHeaderId, orderLineId, attribute3, itemId, status)
   - Use native SQL COUNT queries to check existing records
   - Skip insertion if duplicate found (count > 0)

3. **Transaction Type Validation**

   - Support two types: "RMA_DELIVERY" and "RMA_RECEIPT"
   - Different validation paths and field requirements for each type
   - Case-insensitive comparison using `.equalsIgnoreCase()`

4. **Business Logic Validation**
   - Complex iterative filtering to remove duplicates from batch operations
   - Maintain exact Spring Boot logic for transaction processing
   - Preserve combination key generation patterns

### Dependencies Migration Requirements:

1. **POServicePkg → OraclePackageService**

   - Migrate `validateLocPkgCall()` to use existing Node.js Oracle package service
   - Follow pattern from `moConfirmRepo.ts` and `showroomRepo.ts`
   - Use proper connection management and bind variables

2. **MobTransLogSO → Transaction Logging Service**

   - Migrate CLOB-based request/response logging
   - Maintain module names: "RMA - insertmo", "Item Check Dispatch"
   - Handle JSON serialization for transaction logs

3. **PickConfirmSO Integration**

   - Maintain integration for combined RMA-MO operations
   - Preserve pick confirmation duplicate checking logic
   - Support both "Out Bound Picking" and "RMA_DELIVERY" transaction types

4. **DateUtils → Date Utility Functions**
   - Migrate date handling utilities if not already available
   - Ensure proper SQL Date formatting

## Migration Complexity Notes

### High Complexity Areas:

1. **Duplicate Filtering Logic:** Complex iterative filtering with multiple criteria for RMA_DELIVERY vs RMA_RECEIPT
2. **Transaction Logging:** CLOB handling for request/response logging
3. **Oracle Package Integration:** Location validation via XXGS_MOB_UTIL_PKG.VALIDATE_LOC
4. **Combined Operations:** RMA + MO confirmation in single transaction
5. **Large Entity:** 35+ field entity requires careful type mapping
6. **Validation Chain:** Multi-layer validation (Oracle packages → Duplicate checks → Business rules)
7. **Package Service Migration:** Converting POServicePkg to Node.js Oracle package service

### Critical Implementation Details:

- Maintain exact duplicate filtering logic from Spring Boot
- Preserve Oracle sequence handling for ID generation
- Keep exact endpoint URL patterns
- Maintain transaction logging with CLOB data
- Preserve Oracle package integration for validation
- Keep exact business logic flow for both transaction types

## Dependencies

### Oracle Package Dependencies:

- **XXGS_MOB_UTIL_PKG.VALIDATE_LOC:** Location validation (subinventory + locator)
- **Oracle Sequence:** XXGS_RMA_ID_S for primary key generation

### Service Dependencies:

- **Transaction Logging:** MobTransLogSO integration (CLOB handling)
- **Pick Confirmation:** PickConfirmSO integration for combined operations
- **Date Utilities:** DateUtils migration for date handling
- **Oracle Package Service:** Node.js service for calling Oracle stored procedures

## Success Criteria

- [ ] All 3 endpoints function identically to Spring Boot version
- [ ] Duplicate filtering logic works exactly as before
- [ ] Oracle package integration maintains validation functionality
- [ ] Transaction logging preserves CLOB data handling
- [ ] Swagger documentation fully functional
- [ ] No missing entity fields or business logic
- [ ] Performance matches or exceeds Spring Boot implementation

## Notes

- This is a **pure migration** - no enhancements or modifications
- Must preserve exact business logic and validation rules
- Oracle package dependencies must be maintained
- Complex duplicate filtering is critical functionality
- Large entity structure requires careful attention to all fields
- Combined RMA-MO operations require precise transaction handling

---

**IMPORTANT:** This plan requires approval before development begins. Review all phases and success criteria before proceeding with implementation.
