/**
 * RMAConfirm Validation Schemas
 * Pure migration from Spring Boot RMAConfirm module
 * FOLLOWS existing Zod schema patterns from showroomValidator.ts and other validators
 * Zod schemas for all 3 RMA confirmation routes with exact field validation
 */

import { z } from "zod";
import { optionalString, optionalNumber, optionalDate } from "./common";

// =====================================================
// RMA CONFIRMATION ENTITY VALIDATION
// =====================================================

/**
 * RMA Confirmation Entity Schema
 * Maps RMAConfirmEO.java (35+ fields) validation
 * FOLLOWS existing entity validation patterns
 */
const rmaConfirmEntitySchema = z.object({
  // Primary Key - Oracle sequence generated
  rmaId: optionalNumber,

  // Transaction Core Fields
  transactionType: optionalString, // "RMA_DELIVERY" or "RMA_RECEIPT"
  partyId: optionalNumber,
  custAccountId: optionalNumber,
  billToSitesUseId: optionalNumber,
  shipToSitesUseId: optionalNumber,
  inventoryOrgId: optionalNumber,

  // Order & Line Information
  lineNumber: optionalNumber,
  itemId: optionalNumber,
  uomCode: optionalString,
  orderQuantity: optionalNumber,
  receiptNumber: optionalString,
  returnQuantity: optionalNumber,
  deliveredQuantity: optionalNumber,
  receivedQuantity: optionalNumber,

  // Location & Inventory Fields
  itemCondition: optionalString,
  suggestedSubinventory: optionalString,
  deliveredSubinventory: optionalString,
  suggestedLocatorId: optionalNumber,
  deliveredLocatorId: optionalNumber,

  // Processing Fields
  personId: optionalNumber,
  status: optionalString,
  errorMessage: optionalString,

  // Attribute Fields (15 dynamic attributes)
  attributeCategory: optionalString,
  attribute1: optionalString,
  attribute2: optionalString,
  attribute3: optionalString,
  attribute4: optionalString,
  attribute5: optionalString,
  attribute6: optionalString,
  attribute7: optionalString,
  attribute8: optionalString,
  attribute9: optionalString,
  attribute10: optionalString, // Used for location validation
  attribute11: optionalString,
  attribute12: optionalString,
  attribute13: optionalString,
  attribute14: optionalString,
  attribute15: optionalString,

  // Audit Fields (standard Oracle audit pattern)
  lastUpdateDate: optionalDate,
  lastUpdatedBy: optionalNumber,
  creationDate: optionalDate,
  createdBy: optionalNumber,
  lastUpdateLogin: optionalNumber,

  // Organization Fields
  orgId: optionalNumber,
  orgCode: optionalString,
  orderHeaderId: optionalNumber,
  orderlineId: optionalNumber,
  parentTransactionId: optionalNumber,
  primaryUnitOfMeasurement: optionalString,
});

// =====================================================
// PICK CONFIRMATION ENTITY VALIDATION (REUSE FROM MO CONFIRM)
// =====================================================

/**
 * Pick Confirmation Entity Schema (REUSED from moConfirm patterns)
 * Used for combined RMA-MO operations
 */
const pickConfirmEntitySchema = z.object({
  // Primary Key
  serialId: optionalNumber,

  // Core Pick Fields
  deliveryDetailId: optionalNumber,
  fromSerialNumber: optionalString,
  toserialNumber: optionalString, // Note: matches Java typo
  quantity: optionalNumber,
  status: optionalString,
  transactionType: optionalString,

  // Attribute Fields (15 dynamic attributes)
  attributeCategory: optionalString,
  attribute1: optionalString,
  attribute2: optionalString,
  attribute3: optionalString,
  attribute4: optionalString,
  attribute5: optionalString,
  attribute6: optionalString,
  attribute7: optionalString,
  attribute8: optionalString,
  attribute9: optionalString,
  attribute10: optionalString,
  attribute11: optionalString,
  attribute12: optionalString,
  attribute13: optionalString,
  attribute14: optionalString,
  attribute15: optionalString,

  // Audit Fields
  lastUpdateDate: optionalDate,
  lastUpdatedBy: optionalNumber,
  creationDate: optionalDate,
  createdBy: optionalNumber,
  lastUpdateLogin: optionalNumber,
});

// =====================================================
// WRAPPER ENTITY VALIDATION
// =====================================================

/**
 * RMA Confirmation Wrapper Schema
 * Maps RMAConfirmMO.java for combined operations
 * FOLLOWS existing wrapper validation patterns
 */
const rmaConfirmMOSchema = z.object({
  id: optionalNumber,
  rmaConfirm: rmaConfirmEntitySchema.array(),
  pickConfirm: pickConfirmEntitySchema.array(),
});

// =====================================================
// REQUEST VALIDATION SCHEMAS
// =====================================================

/**
 * Insert RMA Confirmations Request Schema
 * Route: POST /module/rma/confirm/insertmo
 * FOLLOWS existing array validation patterns
 */
const insertRmaConfirmSchema = rmaConfirmEntitySchema.array();

/**
 * Combined RMA-MO Confirmation Request Schema
 * Route: POST /module/rma/confirm/rmainsertmo
 * FOLLOWS existing wrapper validation patterns
 */
const insertRmaConfirmMOSchema = rmaConfirmMOSchema;

// =====================================================
// RESPONSE VALIDATION SCHEMAS (REUSE EXISTING)
// =====================================================

/**
 * Standard API Response Schema (REUSED from existing patterns)
 * Used for all RMA confirmation endpoints
 */
const apiResponseSchema = z.object({
  data: z.any().optional(),
  status: z.number(),
  success: z.boolean().optional(),
  error: z.string().optional(),
});

/**
 * Get All RMA Confirmations Response Schema
 * Route: GET /module/rma/confirm/getallmo
 * FOLLOWS existing array response patterns
 */
const getAllRmaConfirmResponseSchema = z.object({
  data: rmaConfirmEntitySchema.array().optional(),
  status: z.number(),
  success: z.boolean().optional(),
  error: z.string().optional(),
});

// =====================================================
// DUPLICATE VALIDATION SCHEMAS
// =====================================================

/**
 * RMA Delivery Duplicate Check Schema
 * Used for recordCountForDelivery validation (9 parameters)
 */
const rmaDeliveryDuplicateCheckSchema = z.object({
  transactionType: z.string(),
  receiptNumber: z.string(),
  lineNumber: z.string(),
  orderHeaderId: z.string(),
  orderLineId: z.string(),
  attribute3: z.string(),
  attribute10: z.string(),
  itemId: z.string(),
  status: z.string(),
});

/**
 * RMA Receipt Duplicate Check Schema
 * Used for recordCountForReceipt validation (7 parameters)
 */
const rmaReceiptDuplicateCheckSchema = z.object({
  transactionType: z.string(),
  lineNumber: z.string(),
  orderHeaderId: z.string(),
  orderLineId: z.string(),
  attribute3: z.string(),
  itemId: z.string(),
  status: z.string(),
});

// =====================================================
// ORACLE PACKAGE VALIDATION SCHEMAS
// =====================================================

/**
 * Location Validation Request Schema
 * Used for XXGS_MOB_UTIL_PKG.VALIDATE_LOC package calls
 */
const locationValidationSchema = z.object({
  subinventory: z.string().min(1, "Subinventory is required"),
  locator: z.string().min(1, "Locator is required"),
});

/**
 * Location Validation Response Schema
 * Maps Oracle package REF_CURSOR response
 */
const locationValidationResponseSchema = z.object({
  P_LOC_RESULT: z.any().optional(),
});

// =====================================================
// EXPORTS FOR ROUTER CONFIGURATION
// =====================================================

export {
  rmaConfirmEntitySchema,
  pickConfirmEntitySchema,
  rmaConfirmMOSchema,
  insertRmaConfirmSchema,
  insertRmaConfirmMOSchema,
  apiResponseSchema,
  getAllRmaConfirmResponseSchema,
  rmaDeliveryDuplicateCheckSchema,
  rmaReceiptDuplicateCheckSchema,
  locationValidationSchema,
  locationValidationResponseSchema,
};
