/**
 * RMA Receipt Entity Definitions
 * Pure migration from Spring Boot RMAReceipt module request/response interfaces
 * Maps to Oracle package parameters for XXGS_MOB_UTIL_PKG procedures
 *
 * Migration Source:
 * - RMAReceiptCO.java request mappings
 * - RMADeliveryCO.java request mappings
 * - RMAReceiptSO.java Oracle package method calls
 */

// REUSE existing APIResponse from rmaConfirm entity to avoid circular imports
import type { APIResponse } from "./rmaConfirm.entity";

// Common type for Oracle column values
type OracleColumnValue = string | number | boolean | null | undefined;

// =====================================================
// RMA RECEIPT REQUEST INTERFACES
// =====================================================

/**
 * RMA Detail Request Interface
 * Maps: POST /getrmadetail → GET_RMA_DETAILS(P_INVENTORY_ORG_ID)
 * Spring Boot: Map<String, Object> content → P_INVENTORY_ORG_ID extraction
 */
export interface RmaReceiptDetailRequest {
  P_INVENTORY_ORG_ID: string;
}

/**
 * RMA Customer Details Request Interface
 * Maps: POST /rmacustdetails → GET_RMA_CUST_DETAILS(P_ORDER_NUM)
 * Spring Boot: Map<String, Object> content → P_ORDER_NUM extraction
 */
export interface RmaCustDetailRequest {
  P_ORDER_NUM: string;
}

/**
 * RMA Item Detail Request Interface
 * Maps: POST /rmaitemdetail → GET_RMA_ITEM_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM)
 * Spring Boot: Map<String, Object> content → dual parameter extraction
 */
export interface RmaItemDetailRequest {
  P_INVENTORY_ORG_ID: string;
  P_ORDER_NUM: string;
}

/**
 * RMA Item Cross Reference Request Interface
 * Maps: POST /rmaitemcrossRef → GET_RMA_ITEM_CROSS_REF(P_INVENTORY_ORG_ID, P_ORDER_NUM)
 * Spring Boot: Map<String, Object> content → dual parameter extraction
 */
export interface RmaItemCrossRefRequest {
  P_INVENTORY_ORG_ID: string;
  P_ORDER_NUM: string;
}

/**
 * Bundle Item Request Interface
 * Maps: POST /getbundle → BUNDLE_ITEM(P_ORDER_NO)
 * Spring Boot: Map<String, Object> content → P_ORDER_NO extraction
 */
export interface BundleItemRequest {
  P_ORDER_NO: string;
}

// =====================================================
// RMA DELIVERY REQUEST INTERFACES
// =====================================================

/**
 * RMA Delivery Receipt Number Request Interface
 * Maps: POST /getrmadelreceiptnum → GET_RMA_DEL_RECEIPT_NUM(P_INVENTORY_ORG_ID)
 * Spring Boot: Map<String, Object> content → P_INVENTORY_ORG_ID extraction
 */
export interface RmaDelReceiptNumRequest {
  P_INVENTORY_ORG_ID: string;
}

/**
 * RMA Delivery Order Number Request Interface
 * Maps: POST /getrmadelordernum → GET_RMA_DEL_ORDER_NUM(P_INVENTORY_ORG_ID)
 * Spring Boot: Map<String, Object> content → P_INVENTORY_ORG_ID extraction
 */
export interface RmaDelOrderNumRequest {
  P_INVENTORY_ORG_ID: string;
}

/**
 * RMA Delivery Item Details Request Interface
 * Maps: POST /getrmadelitemdtl → GET_RMA_DEL_ITEM_DTLS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC)
 * Spring Boot: Map<String, Object> content → 4-parameter extraction
 */
export interface RmaDelItemDetailRequest {
  P_INVENTORY_ORG_ID: string;
  P_ORDER_NUMBER: string;
  P_RECEIPT_NUMBER: string;
  P_WITH_SUBINV_LOC: string;
}

/**
 * RMA Delivery Item Cross Reference Request Interface
 * Maps: POST /getrmadelitemcross → GET_RMA_DEL_ITEM_CROSS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER)
 * Spring Boot: Map<String, Object> content → 3-parameter extraction
 */
export interface RmaDelItemCrossRequest {
  P_INVENTORY_ORG_ID: string;
  P_ORDER_NUMBER: string;
  P_RECEIPT_NUMBER: string;
}

// =====================================================
// ORACLE PACKAGE RESPONSE INTERFACES
// =====================================================

// =====================================================
// ORACLE CURSOR RESPONSE DATA STRUCTURES
// =====================================================

/**
 * RMA Order Details Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_DETAILS
 */
export interface RmaOrderDetailsRow {
  ORDER_NUMBER?: string;
  ORDER_HEADER_ID?: number;
  ORDER_LINE_ID?: number;
  INVENTORY_ITEM_ID?: number;
  ITEM_CODE?: string;
  ITEM_DESCRIPTION?: string;
  UOM_CODE?: string;
  ORDERED_QUANTITY?: number;
  SHIPPED_QUANTITY?: number;
  ORDER_DATE?: string;
  CUSTOMER_NAME?: string;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * RMA Customer Details Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_CUST_DETAILS
 */
export interface RmaCustomerDetailsRow {
  CUSTOMER_ID?: number;
  CUSTOMER_NAME?: string;
  CUSTOMER_NUMBER?: string;
  ACCOUNT_NUMBER?: string;
  PARTY_ID?: number;
  BILL_TO_SITE_ID?: number;
  SHIP_TO_SITE_ID?: number;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * RMA Item Details Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_ITEM_DETAILS
 */
export interface RmaItemDetailsRow {
  INVENTORY_ITEM_ID?: number;
  ITEM_CODE?: string;
  ITEM_DESCRIPTION?: string;
  UOM_CODE?: string;
  AVAILABLE_QUANTITY?: number;
  RESERVED_QUANTITY?: number;
  ONHAND_QUANTITY?: number;
  SUBINVENTORY_CODE?: string;
  LOCATOR_ID?: number;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * RMA Item Cross Reference Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_ITEM_CROSS_REF
 */
export interface RmaItemCrossRefRow {
  INVENTORY_ITEM_ID?: number;
  CROSS_REFERENCE_TYPE?: string;
  CROSS_REFERENCE?: string;
  DESCRIPTION?: string;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * Bundle Item Details Row Structure
 * Maps Oracle REF_CURSOR row data from BUNDLE_ITEM
 */
export interface BundleItemDetailsRow {
  BUNDLE_ITEM_ID?: number;
  COMPONENT_ITEM_ID?: number;
  COMPONENT_CODE?: string;
  COMPONENT_DESCRIPTION?: string;
  COMPONENT_QUANTITY?: number;
  COMPONENT_UOM?: string;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * RMA Delivery Receipt Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_DEL_RECEIPT_NUM
 */
export interface RmaDeliveryReceiptRow {
  RECEIPT_NUMBER?: string;
  RECEIPT_ID?: number;
  RECEIPT_DATE?: string;
  VENDOR_NAME?: string;
  TOTAL_AMOUNT?: number;
  CURRENCY_CODE?: string;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * RMA Delivery Order Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_DEL_ORDER_NUM
 */
export interface RmaDeliveryOrderRow {
  ORDER_NUMBER?: string;
  ORDER_ID?: number;
  ORDER_DATE?: string;
  ORDER_STATUS?: string;
  TOTAL_LINES?: number;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * RMA Delivery Item Details Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_DEL_ITEM_DTLS
 */
export interface RmaDeliveryItemDetailsRow {
  ITEM_ID?: number;
  ITEM_CODE?: string;
  ITEM_DESCRIPTION?: string;
  ORDERED_QUANTITY?: number;
  DELIVERED_QUANTITY?: number;
  PENDING_QUANTITY?: number;
  UOM_CODE?: string;
  SUBINVENTORY_CODE?: string;
  LOCATOR_ID?: number;
  LOCATOR_SEGMENTS?: string;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * RMA Delivery Item Cross Reference Row Structure
 * Maps Oracle REF_CURSOR row data from GET_RMA_DEL_ITEM_CROSS
 */
export interface RmaDeliveryItemCrossRow {
  ITEM_ID?: number;
  CROSS_REFERENCE_TYPE?: string;
  CROSS_REFERENCE?: string;
  DESCRIPTION?: string;
  STATUS?: string;
  [key: string]: OracleColumnValue; // Allow additional Oracle columns
}

/**
 * Oracle Package Response Structure
 * Maps Oracle REF_CURSOR return values to properly typed arrays
 * Spring Boot: Map<String, Object> with REF_CURSOR → Array of row objects
 */
export interface RmaOraclePackageResponse {
  // RMA Receipt Package responses (REF_CURSOR → Array of typed rows)
  P_ORDER_DTLS_RS?: RmaOrderDetailsRow[]; // GET_RMA_DETAILS output
  P_CUSTOMER_DTLS_RS?: RmaCustomerDetailsRow[]; // GET_RMA_CUST_DETAILS output
  P_ITEM_DTLS_RS?: RmaItemDetailsRow[]; // GET_RMA_ITEM_DETAILS output
  P_CROSS_DTLS_RS?: RmaItemCrossRefRow[]; // GET_RMA_ITEM_CROSS_REF output
  P_BUNDLE_ITEM_DTLS_RS?: BundleItemDetailsRow[]; // BUNDLE_ITEM output

  // RMA Delivery Package responses (REF_CURSOR → Array of typed rows)
  P_RMA_RECEIPT_DTLS_RS?: RmaDeliveryReceiptRow[]; // GET_RMA_DEL_RECEIPT_NUM output
  P_RMA_ORDER_DTLS_RS?: RmaDeliveryOrderRow[]; // GET_RMA_DEL_ORDER_NUM output
  P_RMA_DEL_ITEM_DTLS_RS?: RmaDeliveryItemDetailsRow[]; // GET_RMA_DEL_ITEM_DTLS output
  P_RMA_DEL_CROSS_DTLS_RS?: RmaDeliveryItemCrossRow[]; // GET_RMA_DEL_ITEM_CROSS output
}

// Re-export APIResponse for consistency
export type { APIResponse };
