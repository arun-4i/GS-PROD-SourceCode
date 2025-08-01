/**
 * MoConfirm Validation Schemas
 * Pure migration from Spring Boot MoConfirm module
 * FOLLOWS existing Zod schema patterns from userValidator.ts
 * Zod schemas for all MO confirmation routes with exact field validation
 */

import { z } from "zod";
import { optionalString, optionalNumber, optionalDate } from "./common";

// =====================================================
// MO CONFIRMATION ENTITY VALIDATION
// =====================================================

/**
 * MO Confirmation Entity Schema
 * Maps MoConfirmEO.java fields validation
 */
const moConfirmEntitySchema = z.object({
  moid: optionalNumber,
  transactionType: optionalString,
  moNumber: optionalNumber,
  moLineNumber: optionalNumber,
  pickSlipNumber: optionalNumber,
  itemId: optionalNumber,
  uomCode: optionalString,
  requiredQuantity: optionalNumber,
  pickedQuantity: optionalNumber,
  transferQuantity: optionalNumber,
  sourceSubInventory: optionalString,
  destinationSubInventory: optionalString,
  sourceLocationId: optionalNumber,
  destinationLocationId: optionalNumber,
  personId: optionalNumber,
  status: optionalString,
  errorMessage: optionalString,
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
  lastUpdateDate: optionalDate,
  lastUpdatedBy: optionalNumber,
  creationDate: optionalDate,
  createdBy: optionalNumber,
  lastUpdateLogin: optionalNumber,
  orgId: optionalNumber,
  orgCode: optionalString,
  itemCode: optionalString,
  orderNumber: optionalNumber,
  deliveryDetailId: optionalNumber,
  customerName: optionalString,
  customerAccountId: optionalNumber,
});

// =====================================================
// PICK CONFIRMATION ENTITY VALIDATION
// =====================================================

/**
 * Pick Confirmation Entity Schema
 * Maps PickConfirmEO.java fields validation
 */
const pickConfirmEntitySchema = z.object({
  serialId: optionalNumber,
  deliveryDetailId: optionalNumber,
  fromSerialNumber: optionalString,
  toserialNumber: optionalString, // Note: matches Java typo
  quantity: optionalNumber,
  status: optionalString,
  transactionType: optionalString,
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
 * MO Pick Confirmation Wrapper Schema
 * Maps MoPickConfirmEOT.java for combined operations
 */
const moPickConfirmWrapperSchema = z.object({
  id: optionalNumber,
  moConfirms: moConfirmEntitySchema.array(),
  pickConfirmations: pickConfirmEntitySchema.array(),
});

// =====================================================
// REQUEST VALIDATION SCHEMAS
// =====================================================

/**
 * Insert MO Confirmations Request Schema
 * Route: POST /mo/confirm/insertmo
 */
export const insertMoConfirmSchema = moConfirmEntitySchema.array();

/**
 * Insert Pick Confirmations Request Schema
 * Route: POST /mo/confirm/insertpick
 */
export const insertPickConfirmSchema = pickConfirmEntitySchema.array();

/**
 * Combined MO-Pick Confirmation Request Schema
 * Route: POST /mo/confirm/insertmopick
 */
export const insertMoPickConfirmSchema = moPickConfirmWrapperSchema;
