/**
 * RMAConfirm Entity Definitions
 * Pure migration from Spring Boot RMAConfirm entities
 * Maps RMAConfirmEO.java (35+ fields) → XXGS_RMA_CONFIRMATIONS table
 * Maps RMAConfirmMO.java wrapper → Combined RMA and Pick confirmation operations
 */

// REUSE existing transaction logging and API response entities
import { MobTransLogEntity, APIResponse } from "./showroom.entity";
import { PickConfirmEntity } from "./moConfirm.entity";

// =====================================================
// RMA CONFIRMATION ENTITY
// =====================================================

/**
 * RMA Confirmation Entity
 * Maps RMAConfirmEO.java (621 lines, 35+ fields) → XXGS_RMA_CONFIRMATIONS table
 * Handles Return Merchandise Authorization confirmations with location validation
 */
export interface RmaConfirmEntity {
  // Primary Key - Oracle sequence XXGS_RMA_ID_S
  rmaId?: number; // BigDecimal → number (auto-generated)

  // Transaction Core Fields
  transactionType?: string; // "RMA_DELIVERY" or "RMA_RECEIPT"
  partyId?: number; // BigDecimal → number
  custAccountId?: number; // BigDecimal → number
  billToSitesUseId?: number; // BigDecimal → number
  shipToSitesUseId?: number; // BigDecimal → number
  inventoryOrgId?: number; // BigDecimal → number

  // Order & Line Information
  lineNumber?: number; // BigDecimal → number
  itemId?: number; // BigDecimal → number
  uomCode?: string;
  orderQuantity?: number; // BigDecimal → number
  receiptNumber?: string;
  returnQuantity?: number; // BigDecimal → number
  deliveredQuantity?: number; // BigDecimal → number
  receivedQuantity?: number; // BigDecimal → number

  // Location & Inventory Fields
  itemCondition?: string;
  suggestedSubinventory?: string;
  deliveredSubinventory?: string;
  suggestedLocatorId?: number; // BigDecimal → number
  deliveredLocatorId?: number; // BigDecimal → number

  // Processing Fields
  personId?: number; // BigDecimal → number
  status?: string;
  errorMessage?: string;

  // Attribute Fields (15 dynamic attributes)
  attributeCategory?: string;
  attribute1?: string;
  attribute2?: string;
  attribute3?: string;
  attribute4?: string;
  attribute5?: string;
  attribute6?: string;
  attribute7?: string;
  attribute8?: string;
  attribute9?: string;
  attribute10?: string; // Used for location validation
  attribute11?: string;
  attribute12?: string;
  attribute13?: string;
  attribute14?: string;
  attribute15?: string;

  // Audit Fields (standard Oracle audit pattern)
  lastUpdateDate?: Date; // sql.Date → Date
  lastUpdatedBy?: number; // BigDecimal → number
  creationDate?: Date; // sql.Date → Date
  createdBy?: number; // BigDecimal → number
  lastUpdateLogin?: number; // BigDecimal → number

  // Organization Fields
  orgId?: number; // BigDecimal → number
  orgCode?: string;
  orderHeaderId?: number; // BigDecimal → number
  orderlineId?: number; // BigDecimal → number
  parentTransactionId?: number; // BigDecimal → number
  primaryUnitOfMeasurement?: string;
}

// =====================================================
// RMA CONFIRMATION WRAPPER ENTITY
// =====================================================

/**
 * RMA Confirmation Wrapper (Combined Operations)
 * Maps RMAConfirmMO.java → Combines RMA confirmations with Pick confirmations
 * Used for combined RMA-MO operations in single transaction
 */
export interface RmaConfirmMO {
  id?: number; // Long → number
  rmaConfirm: RmaConfirmEntity[]; // Iterable<RMAConfirmEO> → array
  pickConfirm: PickConfirmEntity[]; // Iterable<PickConfirmEO> → array (REUSED from moConfirm.entity.ts)
}

// =====================================================
// ORACLE PACKAGE TYPES
// =====================================================

/**
 * Location Validation Package Response
 * Maps POServicePkg.validateLocPkgCall() → XXGS_MOB_UTIL_PKG.VALIDATE_LOC
 */
export interface LocationValidationResponse {
  P_LOC_RESULT?: any; // REF_CURSOR with validation result
}

/**
 * Oracle Package Method Names for RMA operations
 */
export enum RmaConfirmPackageMethods {
  // Location Validation
  VALIDATE_LOC = "VALIDATE_LOC", // XXGS_MOB_UTIL_PKG.VALIDATE_LOC(p_subinv, p_loc, P_LOC_RESULT)
}

// =====================================================
// DUPLICATE VALIDATION TYPES
// =====================================================

/**
 * Duplicate Record Check Parameters for RMA_DELIVERY
 * Maps recordCountForDelivery() method parameters (9 fields)
 */
export interface RmaDeliveryDuplicateCheck {
  transactionType: string;
  receiptNumber: string;
  lineNumber: string;
  orderHeaderId: string;
  orderLineId: string;
  attribute3: string;
  attribute10: string; // Location field
  itemId: string;
  status: string;
}

/**
 * Duplicate Record Check Parameters for RMA_RECEIPT
 * Maps recordCountForReceipt() method parameters (7 fields)
 */
export interface RmaReceiptDuplicateCheck {
  transactionType: string;
  lineNumber: string;
  orderHeaderId: string;
  orderLineId: string;
  attribute3: string;
  itemId: string;
  status: string;
}

// =====================================================
// TRANSACTION LOGGING TYPES
// =====================================================

/**
 * RMA Transaction Log Module Names
 * Maps Spring Boot module name constants
 */
export enum RmaTransactionLogModules {
  RMA_INSERT = "RMA - insertmo",
  ITEM_CHECK_DISPATCH = "Item Check Dispatch",
}

// =====================================================
// EXPORTS FOR REUSE
// =====================================================

// REUSE existing entities
export { MobTransLogEntity, APIResponse, PickConfirmEntity };
