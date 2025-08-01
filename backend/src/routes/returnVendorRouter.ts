/**
 * ReturnVendor Router Configuration
 * Pure migration from Spring Boot ReturnVendorCO.java routing
 *
 * IMPORTANT: This is SEPARATE from existing Showroom RTV functionality
 * - Showroom RTV routes: /module/showroom/getRTVRequestNum, etc.
 * - ReturnToVendor routes: /module/returnvendor/rtvrequestnumber, etc.
 * - Different Oracle packages, different parameters, different business logic
 *
 * Maps all 4 ReturnToVendor endpoints with exact Spring Boot paths
 * FOLLOWS autoRegisterRoutes pattern from existing routers (MANDATORY)
 * Uses Swagger registry for documentation (MANDATORY)
 */

import { Router } from "express";
import { ReturnVendorController } from "../controllers/returnVendorController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  rtvRequestNumberSchema,
  rtvItemDetailSchema,
  rtvItemDetailCrSchema,
  rtvItemCodeSchema,
  apiResponseSchema,
} from "../validators/returnVendorValidator";

const returnVendorController = new ReturnVendorController();
export const returnVendorRouter = Router();

autoRegisterRoutes(
  returnVendorRouter,
  [
    // =====================================================
    // RETURN TO VENDOR ENDPOINTS
    // =====================================================

    /**
     * Get RTV Request Number
     * Spring Boot: POST /module/returnvendor/rtvrequestnumber
     * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM
     */
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
    },

    /**
     * Get RTV Item Detail
     * Spring Boot: POST /module/returnvendor/rtvitemdetail
     * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS
     */
    {
      method: "post",
      path: "/rtvitemdetail",
      handler: returnVendorController.getRTVItemDetail,
      schemas: {
        body: rtvItemDetailSchema,
        response: apiResponseSchema,
      },
      summary: "Get RTV Item Detail",
      tags: ["Return To Vendor"],
      contentType: "application/json",
    },

    /**
     * Get RTV Item Detail Cross Reference
     * Spring Boot: POST /module/returnvendor/rtvitemdetailcr
     * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR
     */
    {
      method: "post",
      path: "/rtvitemdetailcr",
      handler: returnVendorController.getRTVItemDetailCr,
      schemas: {
        body: rtvItemDetailCrSchema,
        response: apiResponseSchema,
      },
      summary: "Get RTV Item Detail Cross Reference",
      tags: ["Return To Vendor"],
      contentType: "application/json",
    },

    /**
     * Get RTV Item Code
     * Spring Boot: POST /module/returnvendor/getrtvitemcode
     * Oracle: XXGS_MOB_UTIL_PKG.get_rtv_item_code
     */
    {
      method: "post",
      path: "/getrtvitemcode",
      handler: returnVendorController.getRTVItemCode,
      schemas: {
        body: rtvItemCodeSchema,
        response: apiResponseSchema,
      },
      summary: "Get RTV Item Code",
      tags: ["Return To Vendor"],
      contentType: "application/json",
    },
  ],
  registry
);

/**
 * Route Summary:
 * - 4 POST endpoints migrated from Spring Boot ReturnVendorCO.java
 * - All routes use XXGS_MOB_UTIL_PKG Oracle package procedures
 * - Maintains exact Spring Boot path compatibility
 * - Full Swagger documentation with autoRegisterRoutes
 * - SEPARATE from existing Showroom RTV functionality
 */
