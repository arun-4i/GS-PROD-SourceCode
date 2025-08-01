/**
 * MoConfirm Entity Definitions
 * Pure migration from Spring Boot MoConfirm entities
 * Maps all database fields and wrapper types for MO and Pick confirmations
 */

// Import existing transaction logging entity
import { MobTransLogEntity } from "./showroom.entity";

// =====================================================
// MO CONFIRMATION ENTITY
// =====================================================

/**
 * MO Confirmation Entity
 * Maps MoConfirmEO.java (37 fields) → XXGS_MO_CONFIRMATIONS table
 */
export interface MoConfirmEntity {
  // Primary Key
  moid?: number; // BigDecimal → number (auto-generated)

  // Core MO Fields
  transactionType?: string;
  moNumber?: number; // BigDecimal → number
  moLineNumber?: number; // BigDecimal → number
  pickSlipNumber?: number; // BigDecimal → number
  itemId?: number; // BigDecimal → number
  uomCode?: string;

  // Quantity Fields
  requiredQuantity?: number; // BigDecimal → number
  pickedQuantity?: number; // BigDecimal → number
  transferQuantity?: number; // BigDecimal → number

  // Location Fields
  sourceSubInventory?: string;
  destinationSubInventory?: string;
  sourceLocationId?: number; // BigDecimal → number
  destinationLocationId?: number; // BigDecimal → number (note: typo in Java: DESINATION_LOCATOR_ID)

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
  attribute10?: string;
  attribute11?: string;
  attribute12?: string;
  attribute13?: string;
  attribute14?: string;
  attribute15?: string;

  // Audit Fields
  lastUpdateDate?: Date; // sql.Date → Date
  lastUpdatedBy?: number; // BigDecimal → number
  creationDate?: Date; // sql.Date → Date
  createdBy?: number; // BigDecimal → number
  lastUpdateLogin?: number; // BigDecimal → number

  // Organization Fields
  orgId?: number; // BigDecimal → number
  orgCode?: string;
  itemCode?: string;
  orderNumber?: number; // BigDecimal → number
  deliveryDetailId?: number; // BigDecimal → number
  customerName?: string;
  customerAccountId?: number; // BigDecimal → number
}

// =====================================================
// PICK CONFIRMATION ENTITY
// =====================================================

/**
 * Pick Confirmation Entity
 * Maps PickConfirmEO.java (26 fields) → XXGS_PICK_CONFIRMATION_SERIAL table
 */
export interface PickConfirmEntity {
  // Primary Key
  serialId?: number; // BigDecimal → number (auto-generated)

  // Core Pick Fields
  deliveryDetailId?: number; // BigDecimal → number
  fromSerialNumber?: string;
  toserialNumber?: string; // Note: matches Java typo "toserialNumber"
  quantity?: number; // BigDecimal → number
  status?: string;
  transactionType?: string;

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
  attribute10?: string;
  attribute11?: string;
  attribute12?: string;
  attribute13?: string;
  attribute14?: string;
  attribute15?: string;

  // Audit Fields
  lastUpdateDate?: Date; // sql.Date → Date
  lastUpdatedBy?: number; // BigDecimal → number
  creationDate?: Date; // sql.Date → Date
  createdBy?: number; // BigDecimal → number
  lastUpdateLogin?: number; // BigDecimal → number
}

// =====================================================
// WRAPPER ENTITIES
// =====================================================

/**
 * MO Pick Confirmation Entity (List-based)
 * Maps MoPickConfirmEO.java - Uses arrays for collections
 */
export interface MoPickConfirmEntity {
  id?: number; // Long → number
  moConfirms: MoConfirmEntity[]; // List<MoConfirmEO> → array
  pickConfirmations: PickConfirmEntity[]; // List<PickConfirmEO> → array
}

/**
 * MO Pick Confirmation Wrapper (Iterable-based)
 * Maps MoPickConfirmEOT.java - Used for endpoint payloads
 */
export interface MoPickConfirmWrapper {
  id?: number; // Long → number
  moConfirms: MoConfirmEntity[]; // Iterable<MoConfirmEO> → array
  pickConfirmations: PickConfirmEntity[]; // Iterable<PickConfirmEO> → array
}

// =====================================================
// API RESPONSE TYPES
// =====================================================

/**
 * Standard API Response structure matching Spring Boot APIResponse
 */
export interface APIResponse {
  data?: any;
  status: number;
  success?: boolean;
  error?: string;
}

// =====================================================
// ORACLE PACKAGE TYPES
// =====================================================

/**
 * Oracle Package Method Names for MoConfirm operations
 */
export enum MoConfirmPackageMethods {
  // JSON Processing
  MAIN = "MAIN", // XXGS_MOB_UTIL_PKG.MAIN(jsonData, P_MES, P_MES2)
}

/**
 * Oracle Package Response structure for MAIN method
 */
export interface OraclePackageResponse {
  P_MES?: any; // REF_CURSOR
  P_MES2?: any; // REF_CURSOR
}

// Export transaction logging entity for reuse
export { MobTransLogEntity };
