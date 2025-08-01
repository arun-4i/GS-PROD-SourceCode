/**
 * ReturnToVendor Entity Definitions
 * Pure migration from Spring Boot ReturnToVendor module
 *
 * IMPORTANT: This is SEPARATE from existing Showroom RTV functionality
 * - Showroom RTV uses: showroompkg.GET_RTV_* procedures
 * - ReturnToVendor uses: XXGS_MOB_UTIL_PKG.GET_RTV_* procedures
 * - Different parameters, different business logic, different routes
 */

// REUSE existing API response structure
import { APIResponse } from "./showroom.entity";

// =====================================================
// RETURN TO VENDOR REQUEST INTERFACES
// =====================================================

/**
 * RTV Request Number Request
 * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(P_INVENTORY_ORG_ID, P_REQUEST_NUM, P_RTV_RQST_NUM_RS)
 * Route: POST /module/returnvendor/rtvrequestnumber
 * Spring Boot: ReturnVendorCO.getRTVRequestNumber
 */
export interface RTVRequestNumberRequest {
  P_INVENTORY_ORG_ID?: string;
  P_REQUEST_NUM?: string;
}

/**
 * RTV Item Detail Request
 * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(P_REQUEST_ID, P_RTV_ITEM_RS)
 * Route: POST /module/returnvendor/rtvitemdetail
 * Spring Boot: ReturnVendorCO.getRtvItemDetail
 */
export interface RTVItemDetailRequest {
  P_REQUEST_ID?: string;
}

/**
 * RTV Item Detail Cross Reference Request
 * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR(P_REQUEST_ID, P_RTV_ITEM_CR_RS)
 * Route: POST /module/returnvendor/rtvitemdetailcr
 * Spring Boot: ReturnVendorCO.getRtvItemDetailCr
 */
export interface RTVItemDetailCrRequest {
  P_REQUEST_ID?: string;
}

/**
 * RTV Item Code Request
 * Maps: XXGS_MOB_UTIL_PKG.get_rtv_item_code(p_inventory_org_id, p_return_itrm_dtls)
 * Route: POST /module/returnvendor/getrtvitemcode
 * Spring Boot: ReturnVendorCO.get_rtv_item_code
 */
export interface RTVItemCodeRequest {
  p_inventory_org_id?: string;
}

// =====================================================
// ORACLE RESPONSE INTERFACES
// =====================================================

/**
 * Oracle Package Response wrapper
 * Standard response structure for all Oracle REF_CURSOR procedures
 */
export interface OraclePackageResponse {
  data?: any[];
  P_RTV_RQST_NUM_RS?: any[];
  P_RTV_ITEM_RS?: any[];
  P_RTV_ITEM_CR_RS?: any[];
  p_return_itrm_dtls?: any[];
}

/**
 * RTV Request Number Response Row
 * Represents typical cursor data from XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM
 */
export interface RTVRequestNumberRow {
  REQUEST_ID?: string | number;
  REQUEST_NUMBER?: string;
  INVENTORY_ORG_ID?: string | number;
  // Additional fields as returned by Oracle cursor
  [key: string]: any;
}

/**
 * RTV Item Detail Response Row
 * Represents typical cursor data from XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS
 */
export interface RTVItemDetailRow {
  REQUEST_ID?: string | number;
  ITEM_ID?: string | number;
  ITEM_CODE?: string;
  ITEM_DESCRIPTION?: string;
  QUANTITY?: string | number;
  // Additional fields as returned by Oracle cursor
  [key: string]: any;
}

/**
 * RTV Item Detail Cross Reference Response Row
 * Represents typical cursor data from XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR
 */
export interface RTVItemDetailCrRow {
  REQUEST_ID?: string | number;
  ITEM_ID?: string | number;
  CROSS_REF_TYPE?: string;
  CROSS_REF_VALUE?: string;
  // Additional fields as returned by Oracle cursor
  [key: string]: any;
}

/**
 * RTV Item Code Response Row
 * Represents typical cursor data from XXGS_MOB_UTIL_PKG.get_rtv_item_code
 */
export interface RTVItemCodeRow {
  INVENTORY_ORG_ID?: string | number;
  ITEM_ID?: string | number;
  ITEM_CODE?: string;
  ITEM_DESCRIPTION?: string;
  // Additional fields as returned by Oracle cursor
  [key: string]: any;
}

// =====================================================
// UNUSED PROCEDURES (FOR FUTURE REFERENCE)
// =====================================================

/**
 * COMMENTED OUT IN SPRING BOOT - NOT IMPLEMENTED
 * These procedures exist in ReturnToVendorPackage.java but are commented out in the controller
 * - GET_RTV_SHIPMENT_NUM: Maps XXGS_MOB_UTIL_PKG.GET_RTV_SHIPMENT_NUM
 * - GET_RTV_PO_NUM: Maps XXGS_MOB_UTIL_PKG.GET_RTV_PO_NUM
 * - GET_RTV_RELEASE_NUM: Maps XXGS_MOB_UTIL_PKG.GET_RTV_RELEASE_NUM
 * These can be implemented in future if business requirements change
 */

// =====================================================
// EXPORTS
// =====================================================

export {
  APIResponse, // Re-export for convenience
};
