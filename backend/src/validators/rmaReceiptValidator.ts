/**
 * RMA Receipt Validation Schemas
 * Pure migration from Spring Boot RMAReceipt module request validation
 * Uses Zod for type-safe validation matching Oracle package parameter requirements
 *
 * Migration Source:
 * - RMAReceiptCO.java @RequestBody Map<String, Object> validations
 * - RMADeliveryCO.java @RequestBody Map<String, Object> validations
 * - Oracle package parameter types from RMAReceiptPkg.java and RMADeliveryPkg.java
 */

import { z } from "zod";
import { apiResponseSchema, requiredString } from "./common";

// =====================================================
// RMA RECEIPT REQUEST VALIDATION SCHEMAS
// =====================================================

/**
 * RMA Receipt Detail Request Validation
 * Route: POST /module/rmareceipt/getrmadetail
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DETAILS(P_INVENTORY_ORG_ID)
 * Spring Boot: Map<String, Object> content → P_INVENTORY_ORG_ID extraction
 */
export const rmaReceiptDetailSchema = z.object({
  P_INVENTORY_ORG_ID: requiredString.describe(
    "Inventory Organization ID - Required for RMA details lookup"
  ),
});

/**
 * RMA Customer Details Request Validation
 * Route: POST /module/rmareceipt/rmacustdetails
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_CUST_DETAILS(P_ORDER_NUM)
 * Spring Boot: Map<String, Object> content → P_ORDER_NUM extraction
 */
export const rmaCustDetailSchema = z.object({
  P_ORDER_NUM: requiredString.describe(
    "Order Number - Required for RMA customer details lookup"
  ),
});

/**
 * RMA Item Detail Request Validation
 * Route: POST /module/rmareceipt/rmaitemdetail
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM)
 * Spring Boot: Map<String, Object> content → dual parameter extraction
 */
export const rmaItemDetailSchema = z.object({
  P_INVENTORY_ORG_ID: requiredString.describe(
    "Inventory Organization ID - Required for RMA item details"
  ),
  P_ORDER_NUM: requiredString.describe(
    "Order Number - Required for RMA item details"
  ),
});

/**
 * RMA Item Cross Reference Request Validation
 * Route: POST /module/rmareceipt/rmaitemcrossRef
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_CROSS_REF(P_INVENTORY_ORG_ID, P_ORDER_NUM)
 * Spring Boot: Map<String, Object> content → dual parameter extraction
 */
export const rmaItemCrossRefSchema = z.object({
  P_INVENTORY_ORG_ID: requiredString.describe(
    "Inventory Organization ID - Required for RMA item cross reference"
  ),
  P_ORDER_NUM: requiredString.describe(
    "Order Number - Required for RMA item cross reference"
  ),
});

/**
 * Bundle Item Request Validation
 * Route: POST /module/rmareceipt/getbundle
 * Oracle: XXGS_MOB_UTIL_PKG.BUNDLE_ITEM(P_ORDER_NO)
 * Spring Boot: Map<String, Object> content → P_ORDER_NO extraction
 */
export const bundleItemSchema = z.object({
  P_ORDER_NO: requiredString.describe(
    "Order Number - Required for bundle item details lookup"
  ),
});

// =====================================================
// RMA DELIVERY REQUEST VALIDATION SCHEMAS
// =====================================================

/**
 * RMA Delivery Receipt Number Request Validation
 * Route: POST /module/rmadelivery/getrmadelreceiptnum
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_RECEIPT_NUM(P_INVENTORY_ORG_ID)
 * Spring Boot: Map<String, Object> content → P_INVENTORY_ORG_ID extraction
 */
export const rmaDelReceiptNumSchema = z.object({
  P_INVENTORY_ORG_ID: requiredString.describe(
    "Inventory Organization ID - Required for RMA delivery receipt numbers"
  ),
});

/**
 * RMA Delivery Order Number Request Validation
 * Route: POST /module/rmadelivery/getrmadelordernum
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ORDER_NUM(P_INVENTORY_ORG_ID)
 * Spring Boot: Map<String, Object> content → P_INVENTORY_ORG_ID extraction
 */
export const rmaDelOrderNumSchema = z.object({
  P_INVENTORY_ORG_ID: requiredString.describe(
    "Inventory Organization ID - Required for RMA delivery order numbers"
  ),
});

/**
 * RMA Delivery Item Details Request Validation
 * Route: POST /module/rmadelivery/getrmadelitemdtl
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_DTLS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC)
 * Spring Boot: Map<String, Object> content → 4-parameter extraction
 */
export const rmaDelItemDetailSchema = z.object({
  P_INVENTORY_ORG_ID: requiredString.describe(
    "Inventory Organization ID - Required for RMA delivery item details"
  ),
  P_ORDER_NUMBER: requiredString.describe(
    "Order Number - Required for RMA delivery item details"
  ),
  P_RECEIPT_NUMBER: requiredString.describe(
    "Receipt Number - Required for RMA delivery item details"
  ),
  P_WITH_SUBINV_LOC: requiredString.describe(
    "Subinventory Location Flag - Required for RMA delivery item details"
  ),
});

/**
 * RMA Delivery Item Cross Reference Request Validation
 * Route: POST /module/rmadelivery/getrmadelitemcross
 * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_CROSS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER)
 * Spring Boot: Map<String, Object> content → 3-parameter extraction
 */
export const rmaDelItemCrossSchema = z.object({
  P_INVENTORY_ORG_ID: requiredString.describe(
    "Inventory Organization ID - Required for RMA delivery item cross reference"
  ),
  P_ORDER_NUMBER: requiredString.describe(
    "Order Number - Required for RMA delivery item cross reference"
  ),
  P_RECEIPT_NUMBER: requiredString.describe(
    "Receipt Number - Required for RMA delivery item cross reference"
  ),
});

// =====================================================
// TYPE EXPORTS FOR CONTROLLERS
// =====================================================

// Export inferred types for use in controllers and services
export type RmaReceiptDetailRequest = z.infer<typeof rmaReceiptDetailSchema>;
export type RmaCustDetailRequest = z.infer<typeof rmaCustDetailSchema>;
export type RmaItemDetailRequest = z.infer<typeof rmaItemDetailSchema>;
export type RmaItemCrossRefRequest = z.infer<typeof rmaItemCrossRefSchema>;
export type BundleItemRequest = z.infer<typeof bundleItemSchema>;
export type RmaDelReceiptNumRequest = z.infer<typeof rmaDelReceiptNumSchema>;
export type RmaDelOrderNumRequest = z.infer<typeof rmaDelOrderNumSchema>;
export type RmaDelItemDetailRequest = z.infer<typeof rmaDelItemDetailSchema>;
export type RmaDelItemCrossRequest = z.infer<typeof rmaDelItemCrossSchema>;

// Re-export common response schema for router configuration
export { apiResponseSchema };
