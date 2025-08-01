# ReturnToVendor Module Migration Task Plan

**Date:** 01-08-25  
**Migration Type:** Pure Spring Boot → Node.js/TypeScript  
**Source:** `src/main/java/com/mobile/integration/grandstores/ReturnToVendor/`  
**Target:** `backend/src/` (controllers, services, repositories, entities, validators, routes)

## Migration Overview

### Source Analysis Summary

- **Controller:** `ReturnVendorCO.java` - 4 active endpoints, 3 commented unused endpoints
- **Service:** `ReturnVendorSO.java` - Direct Oracle package calls with APIResponse wrapper
- **Package:** `ReturnToVendorPackage.java` - 7 Oracle procedures (4 active, 3 unused)
- **Base Route:** `/module/returnvendor/`
- **Oracle Package:** `XXGS_MOB_UTIL_PKG` - All procedures are REF_CURSOR based

### Active Endpoints to Migrate

1. `POST /module/returnvendor/rtvrequestnumber` → `getRTVRequestNumber`
2. `POST /module/returnvendor/rtvitemdetail` → `getRtvItemDetail`
3. `POST /module/returnvendor/rtvitemdetailcr` → `getRtvItemDetailCr`
4. `POST /module/returnvendor/getrtvitemcode` → `get_rtv_item_code`

### Oracle Package Methods (Active)

1. `XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(P_INVENTORY_ORG_ID, P_REQUEST_NUM, P_RTV_RQST_NUM_RS)`
2. `XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(P_REQUEST_ID, P_RTV_ITEM_RS)`
3. `XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR(P_REQUEST_ID, P_RTV_ITEM_CR_RS)`
4. `XXGS_MOB_UTIL_PKG.get_rtv_item_code(p_inventory_org_id, p_return_itrm_dtls)`

### Oracle Package Methods (Commented/Unused)

- `GET_RTV_SHIPMENT_NUM` - Will document but not implement
- `GET_RTV_PO_NUM` - Will document but not implement
- `GET_RTV_RELEASE_NUM` - Will document but not implement

## Architecture Compliance

✅ **MANDATORY:** Follow `.cursorrules` and `rules/development/migrations.mdc`  
✅ **MANDATORY:** Use SSOT utilities (withDatabaseConnection, createControllerHandler, apiResponseSchema)  
✅ **MANDATORY:** Follow autoRegisterRoutes pattern for all endpoints  
✅ **MANDATORY:** Reuse existing patterns from showroomController.ts, rmaReceiptController.ts  
✅ **MANDATORY:** No duplication - check existing implementations first

## Phase 1: Pre-Migration Setup & Analysis

### □ 1.1 Existing Pattern Analysis

- [ ] Review existing return/vendor related implementations in codebase
- [ ] Check if any Oracle procedures are already implemented
- [ ] Verify no duplicate functionality exists in other modules
- [ ] Document reusable components from existing implementations

### □ 1.2 Database Package Verification

- [ ] Confirm Oracle package `XXGS_MOB_UTIL_PKG` procedures exist and are accessible
- [ ] Test basic connection to Oracle procedures (if possible)
- [ ] Document procedure signatures and expected outputs
- [ ] Verify parameter types and cursor structures

### □ 1.3 Architecture Planning

- [ ] Plan entity structure for request/response types
- [ ] Plan repository methods with proper Oracle binding
- [ ] Plan service layer business logic (minimal - mostly pass-through)
- [ ] Plan controller handlers using createControllerHandler pattern
- [ ] Plan validation schemas using Zod patterns

## Phase 2: Entity & Type Definitions

### □ 2.1 Create Return Vendor Entity File

**File:** `backend/src/entities/returnVendor.entity.ts`

- [ ] Import existing APIResponse from common entities
- [ ] Define RTVRequestNumberRequest interface
  ```typescript
  interface RTVRequestNumberRequest {
    P_INVENTORY_ORG_ID?: string;
    P_REQUEST_NUM?: string;
  }
  ```
- [ ] Define RTVItemDetailRequest interface
  ```typescript
  interface RTVItemDetailRequest {
    P_REQUEST_ID?: string;
  }
  ```
- [ ] Define RTVItemDetailCrRequest interface
  ```typescript
  interface RTVItemDetailCrRequest {
    P_REQUEST_ID?: string;
  }
  ```
- [ ] Define RTVItemCodeRequest interface
  ```typescript
  interface RTVItemCodeRequest {
    p_inventory_org_id?: string;
  }
  ```
- [ ] Define Oracle response interfaces for each cursor type
- [ ] Define OraclePackageResponse wrapper type
- [ ] Add JSDoc comments with Oracle procedure mapping

### □ 2.2 Document Unused Procedures (Reference Only)

- [ ] Add commented interfaces for unused procedures (for future reference)
- [ ] Document why they're unused (commented in Java source)
- [ ] Add TODO comments for potential future implementation

## Phase 3: Repository Layer Implementation

### □ 3.1 Create Return Vendor Repository

**File:** `backend/src/repositories/returnVendorRepo.ts`

- [ ] Import required dependencies (oracledb, withDatabaseConnection, logger)
- [ ] Import entity interfaces from returnVendor.entity.ts
- [ ] Follow existing repository patterns from rmaReceiptRepo.ts and showroomRepo.ts

### □ 3.2 Implement Oracle Package Methods

#### □ 3.2.1 getRTVRequestNumber Method

- [ ] Implement `XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM` call
- [ ] Use proper parameter binding: P_INVENTORY_ORG_ID, P_REQUEST_NUM
- [ ] Handle REF_CURSOR output: P_RTV_RQST_NUM_RS
- [ ] Add proper error handling and logging
- [ ] Return type: Promise<OraclePackageResponse>

#### □ 3.2.2 getRTVItemDetail Method

- [ ] Implement `XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS` call
- [ ] Use proper parameter binding: P_REQUEST_ID
- [ ] Handle REF_CURSOR output: P_RTV_ITEM_RS
- [ ] Add proper error handling and logging
- [ ] Return type: Promise<OraclePackageResponse>

#### □ 3.2.3 getRTVItemDetailCr Method

- [ ] Implement `XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR` call
- [ ] Use proper parameter binding: P_REQUEST_ID
- [ ] Handle REF_CURSOR output: P_RTV_ITEM_CR_RS
- [ ] Add proper error handling and logging
- [ ] Return type: Promise<OraclePackageResponse>

#### □ 3.2.4 getRTVItemCode Method

- [ ] Implement `XXGS_MOB_UTIL_PKG.get_rtv_item_code` call
- [ ] Use proper parameter binding: p_inventory_org_id
- [ ] Handle REF_CURSOR output: p_return_itrm_dtls
- [ ] Add proper error handling and logging
- [ ] Return type: Promise<OraclePackageResponse>

### □ 3.3 Repository Testing & Validation

- [ ] Ensure all methods compile without TypeScript errors
- [ ] Verify proper Oracle bind variable usage
- [ ] Test basic repository instantiation
- [ ] Validate return types match entity definitions

## Phase 4: Service Layer Implementation

### □ 4.1 Create Return Vendor Service

**File:** `backend/src/services/returnVendorService.ts`

- [ ] Import ReturnVendorRepository
- [ ] Import entity interfaces
- [ ] Follow existing service patterns from showroomService.ts
- [ ] Keep service layer minimal (pass-through to repository)

### □ 4.2 Implement Service Methods

#### □ 4.2.1 getRTVRequestNumber Service

- [ ] Accept RTVRequestNumberRequest parameters
- [ ] Call repository.getRTVRequestNumber() method
- [ ] Handle any business logic (currently none)
- [ ] Return OraclePackageResponse
- [ ] Add service-level error handling

#### □ 4.2.2 getRTVItemDetail Service

- [ ] Accept RTVItemDetailRequest parameters
- [ ] Call repository.getRTVItemDetail() method
- [ ] Handle any business logic (currently none)
- [ ] Return OraclePackageResponse
- [ ] Add service-level error handling

#### □ 4.2.3 getRTVItemDetailCr Service

- [ ] Accept RTVItemDetailCrRequest parameters
- [ ] Call repository.getRTVItemDetailCr() method
- [ ] Handle any business logic (currently none)
- [ ] Return OraclePackageResponse
- [ ] Add service-level error handling

#### □ 4.2.4 getRTVItemCode Service

- [ ] Accept RTVItemCodeRequest parameters
- [ ] Call repository.getRTVItemCode() method
- [ ] Handle any business logic (currently none)
- [ ] Return OraclePackageResponse
- [ ] Add service-level error handling

### □ 4.3 Service Testing & Validation

- [ ] Ensure all service methods compile correctly
- [ ] Verify proper repository integration
- [ ] Test service instantiation
- [ ] Validate error handling paths

## Phase 5: Validation Layer Implementation

### □ 5.1 Create Return Vendor Validator

**File:** `backend/src/validators/returnVendorValidator.ts`

- [ ] Import Zod and common validation utilities
- [ ] Import optionalString from validators/common.ts
- [ ] Follow existing validator patterns from rmaReceiptValidator.ts

### □ 5.2 Implement Validation Schemas

#### □ 5.2.1 Request Validation Schemas

- [ ] Create rtvRequestNumberSchema
  ```typescript
  const rtvRequestNumberSchema = z.object({
    P_INVENTORY_ORG_ID: optionalString,
    P_REQUEST_NUM: optionalString,
  });
  ```
- [ ] Create rtvItemDetailSchema
  ```typescript
  const rtvItemDetailSchema = z.object({
    P_REQUEST_ID: optionalString,
  });
  ```
- [ ] Create rtvItemDetailCrSchema (same as above)
- [ ] Create rtvItemCodeSchema
  ```typescript
  const rtvItemCodeSchema = z.object({
    p_inventory_org_id: optionalString,
  });
  ```

#### □ 5.2.2 Response Validation Schemas

- [ ] Reuse apiResponseSchema from validators/common.ts
- [ ] Create specific response schemas if needed for Oracle cursor data
- [ ] Add JSDoc comments for all schemas

### □ 5.3 Export Validation Schemas

- [ ] Export all request schemas for router usage
- [ ] Export response schemas for documentation
- [ ] Export any middleware functions if needed

## Phase 6: Controller Layer Implementation

### □ 6.1 Create Return Vendor Controller

**File:** `backend/src/controllers/returnVendorController.ts`

- [ ] Import ReturnVendorService
- [ ] Import createControllerHandler from utils/controllerHelpers.ts
- [ ] Import entity interfaces for type safety
- [ ] Follow existing controller patterns from showroomController.ts and rmaReceiptController.ts

### □ 6.2 Implement Controller Methods

#### □ 6.2.1 getRTVRequestNumber Controller

- [ ] Use createControllerHandler pattern for consistency
- [ ] Map to POST /module/returnvendor/rtvrequestnumber
- [ ] Accept RTVRequestNumberRequest body
- [ ] Call service.getRTVRequestNumber()
- [ ] Return APIResponse with proper status codes
- [ ] Add JSDoc with Oracle procedure mapping

#### □ 6.2.2 getRTVItemDetail Controller

- [ ] Use createControllerHandler pattern
- [ ] Map to POST /module/returnvendor/rtvitemdetail
- [ ] Accept RTVItemDetailRequest body
- [ ] Call service.getRTVItemDetail()
- [ ] Return APIResponse with proper status codes
- [ ] Add JSDoc with Oracle procedure mapping

#### □ 6.2.3 getRTVItemDetailCr Controller

- [ ] Use createControllerHandler pattern
- [ ] Map to POST /module/returnvendor/rtvitemdetailcr
- [ ] Accept RTVItemDetailCrRequest body
- [ ] Call service.getRTVItemDetailCr()
- [ ] Return APIResponse with proper status codes
- [ ] Add JSDoc with Oracle procedure mapping

#### □ 6.2.4 getRTVItemCode Controller

- [ ] Use createControllerHandler pattern
- [ ] Map to POST /module/returnvendor/getrtvitemcode
- [ ] Accept RTVItemCodeRequest body
- [ ] Call service.getRTVItemCode()
- [ ] Return APIResponse with proper status codes
- [ ] Add JSDoc with Oracle procedure mapping

### □ 6.3 Controller Export & Structure

- [ ] Export ReturnVendorController class
- [ ] Ensure proper constructor injection of service
- [ ] Add controller-level error handling
- [ ] Follow existing controller documentation patterns

## Phase 7: Router Configuration

### □ 7.1 Update End-Points Constants

**File:** `backend/src/routes/end-points.ts`

- [ ] Add RETURN_VENDOR constant: `RETURN_VENDOR: "/returnvendor"`
- [ ] Follow existing naming patterns
- [ ] Ensure routes maintain exact Spring Boot compatibility

### □ 7.2 Create Return Vendor Router

**File:** `backend/src/routes/returnVendorRouter.ts`

- [ ] Import Router from express
- [ ] Import returnVendorController
- [ ] Import autoRegisterRoutes and registry (MANDATORY)
- [ ] Import validation schemas from returnVendorValidator.ts
- [ ] Import apiResponseSchema from validators/common.ts
- [ ] Follow exact pattern from rmaReceiptRouter.ts and showroomRouter.ts

### □ 7.3 Configure Router Routes

#### □ 7.3.1 Route Definitions Using autoRegisterRoutes

- [ ] Configure rtvrequestnumber route
  ```typescript
  {
    method: "post",
    path: "/rtvrequestnumber",
    handler: returnVendorController.getRTVRequestNumber,
    schemas: {
      body: rtvRequestNumberSchema,
      response: apiResponseSchema,
    },
    summary: "Get RTV Request Number",
    tags: ["Return To Vendor"],
    contentType: "application/json",
  }
  ```
- [ ] Configure rtvitemdetail route
- [ ] Configure rtvitemdetailcr route
- [ ] Configure getrtvitemcode route

#### □ 7.3.2 Router Registration

- [ ] Use autoRegisterRoutes with proper parameters
- [ ] Register with Swagger registry for documentation
- [ ] Export returnVendorRouter for main router integration

### □ 7.4 Update Main Router

**File:** `backend/src/routes/index.ts`

- [ ] Import returnVendorRouter
- [ ] Import END_POINTS.RETURN_VENDOR constant
- [ ] Add route registration: `router.use(END_POINTS.RETURN_VENDOR, returnVendorRouter)`
- [ ] Follow existing router registration patterns

## Phase 8: Integration & Testing

### □ 8.1 TypeScript Compilation

- [ ] Run TypeScript compilation: `npm run build`
- [ ] Fix any compilation errors
- [ ] Ensure all imports resolve correctly
- [ ] Validate all type definitions

### □ 8.2 Linting & Code Quality

- [ ] Run ESLint: `npm run lint`
- [ ] Fix any linting violations
- [ ] Ensure cognitive complexity <15 for all methods
- [ ] Remove any console.log statements

### □ 8.3 Route Registration Testing

- [ ] Start the server: `npm run dev`
- [ ] Verify all routes are registered correctly
- [ ] Test basic route accessibility (GET requests)
- [ ] Confirm no route conflicts

### □ 8.4 Swagger Documentation

- [ ] Access Swagger UI at `/module/docs`
- [ ] Verify all 4 endpoints appear in documentation
- [ ] Validate request/response schemas in Swagger
- [ ] Test example requests through Swagger UI

### □ 8.5 Basic Functionality Testing

- [ ] Test getRTVRequestNumber endpoint with sample data
- [ ] Test getRTVItemDetail endpoint with sample data
- [ ] Test getRTVItemDetailCr endpoint with sample data
- [ ] Test getRTVItemCode endpoint with sample data
- [ ] Validate Oracle package calls (if database accessible)

## Phase 9: Final Validation & Cleanup

### □ 9.1 Code Review & Standards Compliance

- [ ] Review all code against .cursorrules standards
- [ ] Ensure SSOT utilities are used consistently
- [ ] Verify proper error handling throughout
- [ ] Confirm logging standards compliance

### □ 9.2 Documentation Review

- [ ] Ensure all JSDoc comments are complete and accurate
- [ ] Verify Oracle procedure mappings are documented
- [ ] Confirm Swagger documentation is comprehensive
- [ ] Add any missing inline documentation

### □ 9.3 Architecture Verification

- [ ] Confirm clean separation of concerns (Controller→Service→Repository)
- [ ] Verify proper dependency injection patterns
- [ ] Ensure no business logic in controllers or routers
- [ ] Validate repository-only database access

### □ 9.4 File Organization Cleanup

- [ ] Ensure all files are in correct directories
- [ ] Verify consistent naming conventions
- [ ] Remove any temporary or unused files
- [ ] Update any affected import statements

## Completion Checklist

### ✅ Deliverables

- [ ] `backend/src/entities/returnVendor.entity.ts` - Complete entity definitions
- [ ] `backend/src/repositories/returnVendorRepo.ts` - Oracle package integration
- [ ] `backend/src/services/returnVendorService.ts` - Business logic layer
- [ ] `backend/src/validators/returnVendorValidator.ts` - Zod validation schemas
- [ ] `backend/src/controllers/returnVendorController.ts` - HTTP request handlers
- [ ] `backend/src/routes/returnVendorRouter.ts` - Route configuration
- [ ] Updated `backend/src/routes/index.ts` - Main router integration
- [ ] Updated `backend/src/routes/end-points.ts` - Route constants

### ✅ Quality Gates

- [ ] All TypeScript compilation passes
- [ ] All ESLint checks pass
- [ ] All 4 endpoints accessible via HTTP
- [ ] Complete Swagger documentation generated
- [ ] No console.log/console.error statements
- [ ] All methods under 15 cognitive complexity
- [ ] Proper error handling throughout

### ✅ Architecture Compliance

- [ ] SSOT utilities used consistently
- [ ] autoRegisterRoutes pattern implemented
- [ ] Existing patterns followed (showroom, rmaReceipt references)
- [ ] No code duplication identified
- [ ] Clean separation of concerns maintained

## Notes & Considerations

### Unused Endpoints Documentation

The following endpoints exist in Spring Boot but are commented out and will NOT be implemented:

- `rtvshipmentNumber` → `XXGS_MOB_UTIL_PKG.GET_RTV_SHIPMENT_NUM`
- `rtvponumber` → `XXGS_MOB_UTIL_PKG.GET_RTV_PO_NUM`
- `rtvreleasenum` → `XXGS_MOB_UTIL_PKG.GET_RTV_RELEASE_NUM`

These can be implemented in future if requirements change.

### Oracle Package Dependencies

All endpoints depend on `XXGS_MOB_UTIL_PKG` Oracle package procedures. Ensure database connectivity and package permissions are configured correctly.

### Route Compatibility

All routes maintain exact Spring Boot path compatibility:

- Spring Boot: `/module/returnvendor/{endpoint}`
- Node.js: `/returnvendor/{endpoint}` (module prefix handled by main router)

---

**Created:** 01-08-25  
**Estimated Completion Time:** 4-6 hours  
**Dependencies:** Oracle database access, existing Node.js patterns  
**Risk Level:** Low (simple CRUD operations, established patterns)
