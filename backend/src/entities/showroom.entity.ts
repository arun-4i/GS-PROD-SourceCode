/**
 * Showroom Entity Definitions
 * Pure migration from Spring Boot Showroom module
 * Defines all interfaces for Showroom operations matching Oracle package parameters
 */

// Base API Response structure matching Spring Boot APIResponse
export interface APIResponse {
  data?: any;
  status: number;
  error?: string;
  success?: boolean;
}

// =====================================================
// INVENTORY & ORGANIZATION OPERATIONS
// =====================================================

export interface GetInvOrgRequest {
  P_USER_ID?: string;
  P_ORGANIZATION_CODE?: string;
  P_ORGANIZATION_NAME?: string;
}

export interface GetPhysicalInventoriesRequest {
  P_INVENTORY_ORG_ID?: string;
}

export interface GetPhyInvSubInvDtlsRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PHYSICAL_INVENTORY_ID?: string;
}

// =====================================================
// SALES ORDER OPERATIONS
// =====================================================

export interface GetSaleOrderNumRequest {
  P_INVENTORY_ORG_ID?: string;
  P_RESOURCE_ID?: string;
}

export interface GetSaleOrderDetailsRequest {
  P_INVENTORY_ORG_ID?: string;
  P_ORDER_NUM?: string;
  P_MO_NUM?: string;
  P_PICKSLIP_NUM?: string;
  P_RESOURCE_ID?: string;
}

export interface GetSaleOrderDetailsCrRequest {
  P_INVENTORY_ORG_ID?: string;
  P_ORDER_NUM?: string;
  P_MO_NUM?: string;
  P_PICKSLIP_NUM?: string;
  P_RESOURCE_ID?: string;
}

// =====================================================
// MOVE ORDER OPERATIONS
// =====================================================

export interface GetMoDetailsRequest {
  P_ORGANIZATION_ID?: string;
  P_MOVE_ORDER_NUM?: string;
  P_DELIVERY_NUM?: string;
}

export interface GetMoItemDetailsRequest {
  P_HEADER_ID?: string;
}

export interface GetMoItemCrossRefDtlsRequest {
  P_HEADER_ID?: string;
}

// =====================================================
// PURCHASE ORDER OPERATIONS
// =====================================================

export interface GetPoNumberRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PO_NUMBER?: string;
}

export interface GetReleaseNumberRequest {
  P_PO_HEADER_ID?: string;
}

export interface GetPoItemDtlsRequest {
  P_PO_HEADER_ID?: string;
  P_PO_RELEASE_ID?: string;
}

export interface GetPoItemCrossRefRequest {
  P_PO_HEADER_ID?: string;
  P_PO_RELEASE_ID?: string;
}

// =====================================================
// RTV (RETURN TO VENDOR) OPERATIONS
// =====================================================

export interface GetRTVRequestNumRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PO_NUMBER?: string;
  P_RECEIPT_NUM?: string;
  P_ITEM_CODE?: string;
}

export interface GetRTVPoNumRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PO_NUMBER?: string;
  P_RECEIPT_NUM?: string;
}

export interface GetRTVItemDtlsRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PO_NUMBER?: string;
  P_RECEIPT_NUM?: string;
  P_ITEM_CODE?: string;
}

export interface GetRTVItemDtlsCrRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PO_NUMBER?: string;
  P_RECEIPT_NUM?: string;
  P_ITEM_CODE?: string;
}

// =====================================================
// PHYSICAL INVENTORY OPERATIONS
// =====================================================

export interface GetPhyInvQueryDtlsRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PHYSICAL_INVENTORY?: string;
  P_SUBINVENTORY?: string;
}

export interface GetPhyInvCntItemDtlsRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PHYSICAL_INVENTORY?: string;
  P_SUBINVENTORY?: string;
}

export interface GetPhyInvCntItemCrRequest {
  P_INVENTORY_ORG_ID?: string;
  P_PHYSICAL_INVENTORY?: string;
}

// =====================================================
// IO (INTERNAL ORDER) OPERATIONS
// =====================================================

export interface GetIoShipmentNoRequest {
  P_INVENTORY_ORG_ID?: string;
  P_SHIPMENT_NUM?: string;
  P_DELIVERY_NUM?: string;
}

export interface GetIoRcptItemDtlsRequest {
  P_INVENTORY_ORG_ID?: string;
  P_SHIPMENT_NUM?: string;
}

export interface GetIoRcptItemDtlsCrRequest {
  P_INVENTORY_ORG_ID?: string;
  P_SHIPMENT_NUM?: string;
}

// =====================================================
// CONFIRMATION OPERATIONS (Complex JSON Processing)
// =====================================================

export interface ConfirmationRequest {
  P_INPUT?: any; // Complex JSON object - will be serialized to string
}

export interface MoConfirmRequest extends ConfirmationRequest {}
export interface IoConfirmRequest extends ConfirmationRequest {}
export interface PoConfirmRequest extends ConfirmationRequest {}
export interface RtvConfirmRequest extends ConfirmationRequest {}
export interface StockConfirmRequest extends ConfirmationRequest {}

// =====================================================
// ORACLE PACKAGE RESPONSE STRUCTURES
// =====================================================

export interface OraclePackageResponse {
  [key: string]: any;
}

// Confirmation operation response structure
export interface ConfirmationResponse {
  STATUS_CODE?: number;
  MESSAGE?: string;
}

// Transaction logging entity matching mobTransLogEO
export interface MobTransLogEntity {
  MODULE_NAME?: string;
  REQUEST?: string; // CLOB content as string
  RESPONSE?: string; // CLOB content as string
  PROCESSED_TIME?: Date;
}

// =====================================================
// SHOWROOM PACKAGE CALLS MAPPING
// =====================================================

/**
 * Oracle Package Method Names (matching Java implementation)
 * These correspond to the showroompkg.* calls in ShowroomSO.java
 */
export enum ShowroomPackageMethods {
  // Query Operations
  GET_INV_ORG = "GET_INV_ORG",
  GET_SALE_ORDER_NUM = "GET_SALE_ORDER_NUM",
  GET_SALE_ORDER_DETAILS = "GET_SALE_ORDER_DETAILS",
  GET_SALE_ORDER_DETAILS_CR = "GET_SALE_ORDER_DETAILS_CR",
  GET_MO_DETAILS = "GET_MO_DETAILS",
  GET_MO_ITEM_DETAILS = "GET_MO_ITEM_DETAILS",
  GET_MO_ITEM_CROSS_REF_DTLS = "GET_MO_ITEM_CROSS_REF_DTLS",
  GET_PO_NUMBER = "GET_PO_NUMBER",
  GET_RELEASE_NUM = "GET_RELEASE_NUM",
  GET_PO_ITEM_DTLS = "GET_PO_ITEM_DTLS",
  GET_PO_ITEM_CROSS_REF = "GET_PO_ITEM_CROSS_REF",
  GET_RTV_REQUEST_NUM = "GET_RTV_REQUEST_NUM",
  GET_RTV_PO_NUM = "GET_RTV_PO_NUM",
  GET_RTV_ITEM_DTLS = "GET_RTV_ITEM_DTLS",
  GET_RTV_ITEM_DTLS_CR = "GET_RTV_ITEM_DTLS_CR",
  GET_PHY_INV_QUERY_DTLS = "GET_PHY_INV_QUERY_DTLS",
  GET_PHYINV_CNT_ITEM_DTLS = "GET_PHYINV_CNT_ITEM_DTLS",
  GET_PHYINV_CNT_ITEM_CR = "GET_PHYINV_CNT_ITEM_CR",
  GET_PHYSICAL_INVENTORIES = "GET_PHYSICAL_INVENTORIES",
  GET_PHY_INV_SUBINV_DTLS = "GET_PHY_INV_SUBINV_DTLS",
  GET_IO_SHIPMENT_NO = "GET_IO_SHIPMENT_NO",
  GET_IO_RCPT_ITEM_DTLS = "GET_IO_RCPT_ITEM_DTLS",
  GET_IO_RCPT_ITEM_DTLS_CR = "GET_IO_RCPT_ITEM_DTLS_CR",

  // Confirmation Operations
  MO_CONFIRM = "MO_CONFIRM",
  IO_CONFIRM = "IO_CONFIRM",
  PO_CONFIRM = "PO_CONFIRM",
  RTV_CONFIRM = "RTV_CONFIRM",
  STOCK_CONFIRM = "STOCK_CONFIRM",
}
