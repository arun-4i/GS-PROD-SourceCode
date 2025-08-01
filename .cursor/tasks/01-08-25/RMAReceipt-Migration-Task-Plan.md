# RMAReceipt Module Migration Task Plan

**Date**: 01-08-25
**Source**: `src/main/java/com/mobile/integration/grandstores/RMAReceipt/`
**Target**: `backend/src/` (Node.js/TypeScript)
**Type**: Pure Spring Boot to Node.js Migration

## Requirements Analysis

### Spring Boot Components Found

1. **Controllers**:

   - `RMAReceiptCO.java` - 5 endpoints under `/module/rmareceipt/`
   - `RMADeliveryCO.java` - 4 endpoints under `/module/rmadelivery/`

2. **Service**:

   - `RMAReceiptSO.java` - 9 Oracle package method calls

3. **Oracle Package Dependencies**:
   - `RMAReceiptPkg.java` - 5 procedures from `XXGS_MOB_UTIL_PKG`
   - `RMADeliveryPkg.java` - 4 procedures from `XXGS_MOB_UTIL_PKG`

### Exact Route Requirements

**RMA Receipt Routes** (Base: `/module/rmareceipt/`):

1. `POST /getrmadetail` - Get RMA details by inventory org
2. `POST /rmacustdetails` - Get RMA customer details by order number
3. `POST /rmaitemdetail` - Get RMA item details
4. `POST /rmaitemcrossRef` - Get RMA item cross reference
5. `POST /getbundle` - Get bundle item details

**RMA Delivery Routes** (Base: `/module/rmadelivery/`):

1. `POST /getrmadelreceiptnum` - Get RMA delivery receipt numbers
2. `POST /getrmadelordernum` - Get RMA delivery order numbers
3. `POST /getrmadelitemdtl` - Get RMA delivery item details
4. `POST /getrmadelitemcross` - Get RMA delivery item cross reference

### Oracle Package Methods to Migrate

**From XXGS_MOB_UTIL_PKG**:

1. `GET_RMA_DETAILS(P_INVENTORY_ORG_ID)` → Returns P_ORDER_DTLS_RS
2. `GET_RMA_CUST_DETAILS(P_ORDER_NUM)` → Returns P_CUSTOMER_DTLS_RS
3. `GET_RMA_ITEM_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM)` → Returns P_ITEM_DTLS_RS
4. `GET_RMA_ITEM_CROSS_REF(P_INVENTORY_ORG_ID, P_ORDER_NUM)` → Returns P_CROSS_DTLS_RS
5. `BUNDLE_ITEM(P_ORDER_NO)` → Returns P_BUNDLE_ITEM_DTLS_RS
6. `GET_RMA_DEL_RECEIPT_NUM(P_INVENTORY_ORG_ID)` → Returns P_RMA_RECEIPT_DTLS_RS
7. `GET_RMA_DEL_ORDER_NUM(P_INVENTORY_ORG_ID)` → Returns P_RMA_ORDER_DTLS_RS
8. `GET_RMA_DEL_ITEM_DTLS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC)` → Returns P_RMA_DEL_ITEM_DTLS_RS
9. `GET_RMA_DEL_ITEM_CROSS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER)` → Returns P_RMA_DEL_CROSS_DTLS_RS

## Pre-Migration Assessment

### Already Implemented Components ✅

- [ ] **RESEARCH**: Check if RMA-related entities already exist in `backend/src/entities/`
- [ ] **RESEARCH**: Check if Oracle helpers for `XXGS_MOB_UTIL_PKG` calls exist
- [ ] **RESEARCH**: Check if response schemas are available in `validators/common.ts`
- [ ] **RESEARCH**: Verify `END_POINTS` constants for routing patterns

### Dependencies Required

- [ ] **DB Package**: All methods use `XXGS_MOB_UTIL_PKG` (existing package)
- [ ] **Response Types**: Standard `Map<String, Object>` → `APIResponse` pattern
- [ ] **Validation**: Input parameter validation for all endpoints
- [ ] **Connection**: Oracle connection pooling (REUSE existing patterns)

## Migration Implementation Plan

### Phase 1: Entity & Validation Layer

- [ ] **Entity Creation**: Create `rmaReceiptEntity.ts` for request/response interfaces

  - [ ] Define `RmaReceiptDetailRequest` interface (P_INVENTORY_ORG_ID)
  - [ ] Define `RmaCustDetailRequest` interface (P_ORDER_NUM)
  - [ ] Define `RmaItemDetailRequest` interface (P_INVENTORY_ORG_ID, P_ORDER_NUM)
  - [ ] Define `RmaItemCrossRefRequest` interface (P_INVENTORY_ORG_ID, P_ORDER_NUM)
  - [ ] Define `BundleItemRequest` interface (P_ORDER_NO)
  - [ ] Define `RmaDelReceiptNumRequest` interface (P_INVENTORY_ORG_ID)
  - [ ] Define `RmaDelOrderNumRequest` interface (P_INVENTORY_ORG_ID)
  - [ ] Define `RmaDelItemDetailRequest` interface (P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC)
  - [ ] Define `RmaDelItemCrossRequest` interface (P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER)

- [ ] **Validation Schemas**: Create `rmaReceiptValidator.ts` using Zod
  - [ ] `rmaReceiptDetailSchema` - Required: P_INVENTORY_ORG_ID (string)
  - [ ] `rmaCustDetailSchema` - Required: P_ORDER_NUM (string)
  - [ ] `rmaItemDetailSchema` - Required: P_INVENTORY_ORG_ID, P_ORDER_NUM (strings)
  - [ ] `rmaItemCrossRefSchema` - Required: P_INVENTORY_ORG_ID, P_ORDER_NUM (strings)
  - [ ] `bundleItemSchema` - Required: P_ORDER_NO (string)
  - [ ] `rmaDelReceiptNumSchema` - Required: P_INVENTORY_ORG_ID (string)
  - [ ] `rmaDelOrderNumSchema` - Required: P_INVENTORY_ORG_ID (string)
  - [ ] `rmaDelItemDetailSchema` - Required: P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC (strings)
  - [ ] `rmaDelItemCrossSchema` - Required: P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER (strings)

### Phase 2: Repository Layer

- [ ] **Repository Creation**: Create `rmaReceiptRepository.ts`
  - [ ] Implement `getRmaDetail(inventoryOrgId: string)` → Oracle package call
  - [ ] Implement `getRmaCustDetails(orderNum: string)` → Oracle package call
  - [ ] Implement `getRmaItemDetail(inventoryOrgId: string, orderNum: string)` → Oracle package call
  - [ ] Implement `getRmaItemCrossRef(inventoryOrgId: string, orderNum: string)` → Oracle package call
  - [ ] Implement `getBundleItem(orderNo: string)` → Oracle package call
  - [ ] Implement `getRmaDelReceiptNum(inventoryOrgId: string)` → Oracle package call
  - [ ] Implement `getRmaDelOrderNum(inventoryOrgId: string)` → Oracle package call
  - [ ] Implement `getRmaDelItemDetail(params: RmaDelItemDetailRequest)` → Oracle package call
  - [ ] Implement `getRmaDelItemCross(inventoryOrgId: string, orderNumber: string, receiptNumber: string)` → Oracle package call
  - [ ] **MANDATORY**: Use `withDatabaseConnection()` for all Oracle calls
  - [ ] **MANDATORY**: Use proper bind variables and error handling
  - [ ] **MANDATORY**: Follow existing Oracle package call patterns from `showroomRepo.ts`

### Phase 3: Service Layer

- [ ] **Service Creation**: Create `rmaReceiptService.ts`
  - [ ] Implement `getRmaDetail(request: RmaReceiptDetailRequest)` → Repository + response formatting
  - [ ] Implement `getRmaCustDetails(request: RmaCustDetailRequest)` → Repository + response formatting
  - [ ] Implement `getRmaItemDetail(request: RmaItemDetailRequest)` → Repository + response formatting
  - [ ] Implement `getRmaItemCrossRef(request: RmaItemCrossRefRequest)` → Repository + response formatting
  - [ ] Implement `getBundleItem(request: BundleItemRequest)` → Repository + response formatting
  - [ ] Implement `getRmaDelReceiptNum(request: RmaDelReceiptNumRequest)` → Repository + response formatting
  - [ ] Implement `getRmaDelOrderNum(request: RmaDelOrderNumRequest)` → Repository + response formatting
  - [ ] Implement `getRmaDelItemDetail(request: RmaDelItemDetailRequest)` → Repository + response formatting
  - [ ] Implement `getRmaDelItemCross(request: RmaDelItemCrossRequest)` → Repository + response formatting
  - [ ] **MANDATORY**: Use `createSuccessResponse()` and `createErrorResponse()` from `validators/common.ts`
  - [ ] **MANDATORY**: Implement proper error handling for all methods
  - [ ] **MANDATORY**: Keep methods under 15 cognitive complexity

### Phase 4: Controller Layer

- [ ] **Controller Creation**: Create `rmaReceiptController.ts`

  - [ ] Implement `getRmaDetail` handler using `createControllerHandler()`
  - [ ] Implement `getRmaCustDetails` handler using `createControllerHandler()`
  - [ ] Implement `getRmaItemDetail` handler using `createControllerHandler()`
  - [ ] Implement `getRmaItemCrossRef` handler using `createControllerHandler()`
  - [ ] Implement `getBundleItem` handler using `createControllerHandler()`
  - [ ] **MANDATORY**: Use SSOT utilities from `utils/controllerHelpers.ts`
  - [ ] **MANDATORY**: No validation in controller (validation in routes via schemas)

- [ ] **RMA Delivery Controller**: Create `rmaDeliveryController.ts`
  - [ ] Implement `getRmaDelReceiptNum` handler using `createControllerHandler()`
  - [ ] Implement `getRmaDelOrderNum` handler using `createControllerHandler()`
  - [ ] Implement `getRmaDelItemDetail` handler using `createControllerHandler()`
  - [ ] Implement `getRmaDelItemCross` handler using `createControllerHandler()`
  - [ ] **MANDATORY**: Use SSOT utilities from `utils/controllerHelpers.ts`

### Phase 5: Routing Layer

- [ ] **Endpoint Constants**: Add to `routes/end-points.ts`

  - [ ] Add `RMA_RECEIPT: "/rmareceipt"` - **EXACT SPRING BOOT PATH**
  - [ ] Add `RMA_DELIVERY: "/rmadelivery"` - **EXACT SPRING BOOT PATH**

- [ ] **RMA Receipt Router**: Create `rmaReceiptRouter.ts`

  - [ ] Configure route: `POST /getrmadetail` ✅ **EXACT SPRING BOOT PATH**
  - [ ] Configure route: `POST /rmacustdetails` ✅ **EXACT SPRING BOOT PATH**
  - [ ] Configure route: `POST /rmaitemdetail` ✅ **EXACT SPRING BOOT PATH**
  - [ ] Configure route: `POST /rmaitemcrossRef` ✅ **EXACT SPRING BOOT PATH**
  - [ ] Configure route: `POST /getbundle` ✅ **EXACT SPRING BOOT PATH**
  - [ ] **MANDATORY**: Use `autoRegisterRoutes()` pattern with proper schemas
  - [ ] **MANDATORY**: Use `END_POINTS.RMA_RECEIPT` as base path
  - [ ] **MANDATORY**: Include Swagger tags: ["RMA Receipt"]

- [ ] **RMA Delivery Router**: Create `rmaDeliveryRouter.ts`

  - [ ] Configure route: `POST /getrmadelreceiptnum` ✅ **EXACT SPRING BOOT PATH**
  - [ ] Configure route: `POST /getrmadelordernum` ✅ **EXACT SPRING BOOT PATH**
  - [ ] Configure route: `POST /getrmadelitemdtl` ✅ **EXACT SPRING BOOT PATH**
  - [ ] Configure route: `POST /getrmadelitemcross` ✅ **EXACT SPRING BOOT PATH**
  - [ ] **MANDATORY**: Use `autoRegisterRoutes()` pattern with proper schemas
  - [ ] **MANDATORY**: Use `END_POINTS.RMA_DELIVERY` as base path
  - [ ] **MANDATORY**: Include Swagger tags: ["RMA Delivery"]

- [ ] **Main Router Integration**: Update `routes/index.ts`
  - [ ] Add `router.use(END_POINTS.RMA_RECEIPT, rmaReceiptRouter);`
  - [ ] Add `router.use(END_POINTS.RMA_DELIVERY, rmaDeliveryRouter);`

### Phase 6: Final Integration & Validation

- [ ] **Route Verification**: Test all endpoints match Spring Boot exactly

  - [ ] `POST /module/rmareceipt/getrmadetail` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmareceipt/rmacustdetails` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmareceipt/rmaitemdetail` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmareceipt/rmaitemcrossRef` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmareceipt/getbundle` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmadelivery/getrmadelreceiptnum` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmadelivery/getrmadelordernum` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmadelivery/getrmadelitemdtl` ✅ **EXACT MATCH**
  - [ ] `POST /module/rmadelivery/getrmadelitemcross` ✅ **EXACT MATCH**

- [ ] **Swagger Documentation**: Verify auto-generated OpenAPI docs

  - [ ] All endpoints appear in Swagger UI with correct tags
  - [ ] Request/response schemas properly generated
  - [ ] Parameter validation documented

- [ ] **Linting & Type Checking**: Run final code quality checks

  - [ ] `npm run lint` - No ESLint errors
  - [ ] `npm run type-check` - No TypeScript errors
  - [ ] All imports use correct path aliases (@utils, @config, etc.)

- [ ] **Integration Testing**: Verify Oracle package connectivity
  - [ ] Test database connections to `XXGS_MOB_UTIL_PKG`
  - [ ] Verify all procedure calls return expected cursors
  - [ ] Test error handling for invalid parameters

## Implementation Notes

### Oracle Package Integration

- **Package**: `XXGS_MOB_UTIL_PKG` (Reuse existing utilities)
- **Connection**: Use existing Oracle connection pool patterns
- **Error Handling**: Follow `showroomRepo.ts` patterns for procedure calls
- **Cursor Processing**: Standard REF_CURSOR → Array mapping

### Response Pattern Consistency

```typescript
// Standard success response
return createSuccessResponse(data, 200);

// Standard error response
return createErrorResponse(500, "Error message", error);
```

### Validation Strategy

- **Input Validation**: All request parameters validated via Zod schemas
- **Required Fields**: Enforce required parameters (P_INVENTORY_ORG_ID, P_ORDER_NUM, etc.)
- **Type Safety**: String parameters for all Oracle procedure inputs
- **Error Messages**: Clear validation failure messages

### Swagger Integration

- **Auto-Discovery**: Use `autoRegisterRoutes` for automatic OpenAPI generation
- **Tags**: Separate tags for "RMA Receipt" and "RMA Delivery" modules
- **Descriptions**: Clear endpoint descriptions matching Spring Boot functionality
- **Schemas**: Request/response schemas auto-generated from Zod validators

## Testing Strategy

### Unit Testing

- [ ] Repository layer: Mock Oracle procedure calls
- [ ] Service layer: Mock repository responses
- [ ] Controller layer: Mock service responses
- [ ] Validation: Test all Zod schemas with valid/invalid inputs

### Integration Testing

- [ ] Oracle connectivity: Test actual procedure calls
- [ ] Route testing: Verify endpoint responses
- [ ] Error scenarios: Test parameter validation and error handling

### Migration Validation

- [ ] **Endpoint Parity**: All Spring Boot endpoints migrated exactly
- [ ] **Parameter Mapping**: All Oracle procedure parameters preserved
- [ ] **Response Format**: APIResponse structure maintained
- [ ] **Error Handling**: Consistent error response patterns

## Risk Mitigation

### Oracle Package Dependencies

- **Risk**: `XXGS_MOB_UTIL_PKG` procedures may not exist or have changed
- **Mitigation**: Verify all procedures exist and are accessible before migration
- **Fallback**: Document any missing procedures for DBA team

### Parameter Validation

- **Risk**: Spring Boot allowed looser parameter validation
- **Mitigation**: Follow exact parameter patterns from Java code
- **Testing**: Validate all parameter combinations work with Oracle procedures

### Response Structure Changes

- **Risk**: Node.js response format may differ from Spring Boot
- **Mitigation**: Use consistent `APIResponse` pattern from existing code
- **Validation**: Compare response structures with Spring Boot endpoints

## Retrospective & Feedback

_To be completed after implementation_

### What Worked Well

- [ ] _Post-implementation feedback on successful patterns_

### Challenges Encountered

- [ ] _Document any Oracle connectivity issues_
- [ ] _Note any parameter mapping difficulties_

### Improvements for Future Migrations

- [ ] _Lessons learned for next migration_
- [ ] _Process improvements identified_

### Rule Updates Required

- [ ] _Any .cursorrules updates needed_
- [ ] _New patterns to document_

---

**Total Estimated Effort**: 9 endpoints across 2 controllers, 9 Oracle procedures
**Key Success Criteria**:

1. All endpoints match Spring Boot exactly
2. All Oracle procedures work correctly
3. Full Swagger documentation
4. Zero linting/type errors
5. Complete test coverage
