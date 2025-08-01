/**
 * Showroom Router Configuration
 * Pure migration from Spring Boot ShowroomCO.java route mappings
 * Maintains exact route paths: /module/showroom/{endpoint}
 * All 27 routes with proper HTTP methods and validation middleware
 */

import { Router } from "express";
import { showroomController } from "../controllers/showroomController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  getInvOrgSchema,
  getPhysicalInventoriesSchema,
  getPhyInvSubInvDtlsSchema,
  getSaleOrderNumSchema,
  getSaleOrderDetailsSchema,
  getSaleOrderDetailsCrSchema,
  getMoDetailsSchema,
  getMoItemDetailsSchema,
  getMoItemCrossRefDtlsSchema,
  getPoNumberSchema,
  getPoItemDtlsSchema,
  getPoItemCrossRefSchema,
  getRTVRequestNumSchema,
  getRTVPoNumSchema,
  getRTVItemDtlsSchema,
  getRTVItemDtlsCrSchema,
  getPhyInvQueryDtlsSchema,
  getPhyInvCntItemDtlsSchema,
  getPhyInvCntItemCrSchema,
  getIoShipmentNoSchema,
  getIoRcptItemDtlsSchema,
  getIoRcptItemDtlsCrSchema,
  moConfirmSchema,
  ioConfirmSchema,
  poConfirmSchema,
  rtvConfirmSchema,
  stockConfirmSchema,
} from "../validators/showroomValidator";
import { z } from "zod";
import { END_POINTS } from "./end-points";

export const showroomRouter = Router();

// Standard API response schema
const apiResponseSchema = z.object({
  data: z.any().optional(),
  status: z.number(),
  error: z.string().optional(),
  success: z.boolean().optional(),
});

// Auto-register all Showroom routes with Swagger documentation
autoRegisterRoutes(
  showroomRouter,
  [
    // =====================================================
    // INVENTORY & ORGANIZATION ROUTES
    // =====================================================
    {
      method: "post",
      path: "/getInvOrg",
      handler: showroomController.getInvOrg,
      schemas: {
        body: getInvOrgSchema,
        response: apiResponseSchema,
      },
      summary: "Get inventory organizations",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getPhysicalInventories",
      handler: showroomController.getPhysicalInventories,
      schemas: {
        body: getPhysicalInventoriesSchema,
        response: apiResponseSchema,
      },
      summary: "Get physical inventories",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getPhyInvSubInvDtls",
      handler: showroomController.getPhyInvSubInvDtls,
      schemas: {
        body: getPhyInvSubInvDtlsSchema,
        response: apiResponseSchema,
      },
      summary: "Get physical inventory sub-inventory details",
      tags: ["Showroom"],
      contentType: "application/json",
    },

    // =====================================================
    // SALES ORDER ROUTES
    // =====================================================
    {
      method: "post",
      path: "/getSaleOrderNum",
      handler: showroomController.getSaleOrderNum,
      schemas: {
        body: getSaleOrderNumSchema,
        response: apiResponseSchema,
      },
      summary: "Get sale order numbers",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getSaleOrderDetails",
      handler: showroomController.getSaleOrderDetails,
      schemas: {
        body: getSaleOrderDetailsSchema,
        response: apiResponseSchema,
      },
      summary: "Get sale order details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getSaleOrderDetailsCr",
      handler: showroomController.getSaleOrderDetailsCr,
      schemas: {
        body: getSaleOrderDetailsCrSchema,
        response: apiResponseSchema,
      },
      summary: "Get sale order details cross reference",
      tags: ["Showroom"],
      contentType: "application/json",
    },

    // =====================================================
    // MOVE ORDER ROUTES
    // =====================================================
    {
      method: "post",
      path: "/getMoDetails",
      handler: showroomController.getMoDetails,
      schemas: {
        body: getMoDetailsSchema,
        response: apiResponseSchema,
      },
      summary: "Get move order details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getMoItemDetails",
      handler: showroomController.getMoItemDetails,
      schemas: {
        body: getMoItemDetailsSchema,
        response: apiResponseSchema,
      },
      summary: "Get move order item details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getMoItemCrossRefDtls",
      handler: showroomController.getMoItemCrossRefDtls,
      schemas: {
        body: getMoItemCrossRefDtlsSchema,
        response: apiResponseSchema,
      },
      summary: "Get move order item cross reference details",
      tags: ["Showroom"],
      contentType: "application/json",
    },

    // =====================================================
    // PURCHASE ORDER ROUTES
    // =====================================================
    {
      method: "post",
      path: "/getPoNumber",
      handler: showroomController.getPoNumber,
      schemas: {
        body: getPoNumberSchema,
        response: apiResponseSchema,
      },
      summary: "Get purchase order number",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getPoItemDtls",
      handler: showroomController.getPoItemDtls,
      schemas: {
        body: getPoItemDtlsSchema,
        response: apiResponseSchema,
      },
      summary: "Get purchase order item details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getPoItemCrossRef",
      handler: showroomController.getPoItemCrossRef,
      schemas: {
        body: getPoItemCrossRefSchema,
        response: apiResponseSchema,
      },
      summary: "Get purchase order item cross reference",
      tags: ["Showroom"],
      contentType: "application/json",
    },

    // =====================================================
    // RTV (RETURN TO VENDOR) ROUTES
    // =====================================================
    {
      method: "post",
      path: "/getRTVRequestNum",
      handler: showroomController.getRTVRequestNum,
      schemas: {
        body: getRTVRequestNumSchema,
        response: apiResponseSchema,
      },
      summary: "Get RTV request number",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getRTVPoNum",
      handler: showroomController.getRTVPoNum,
      schemas: {
        body: getRTVPoNumSchema,
        response: apiResponseSchema,
      },
      summary: "Get RTV purchase order number",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getRTVItemDtls",
      handler: showroomController.getRTVItemDtls,
      schemas: {
        body: getRTVItemDtlsSchema,
        response: apiResponseSchema,
      },
      summary: "Get RTV item details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getRTVItemDtlsCr",
      handler: showroomController.getRTVItemDtlsCr,
      schemas: {
        body: getRTVItemDtlsCrSchema,
        response: apiResponseSchema,
      },
      summary: "Get RTV item details cross reference",
      tags: ["Showroom"],
      contentType: "application/json",
    },

    // =====================================================
    // PHYSICAL INVENTORY ROUTES
    // =====================================================
    {
      method: "post",
      path: "/getPhyInvQueryDtls",
      handler: showroomController.getPhyInvQueryDtls,
      schemas: {
        body: getPhyInvQueryDtlsSchema,
        response: apiResponseSchema,
      },
      summary: "Get physical inventory query details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getPhyInvCntItemDtls",
      handler: showroomController.getPhyInvCntItemDtls,
      schemas: {
        body: getPhyInvCntItemDtlsSchema,
        response: apiResponseSchema,
      },
      summary: "Get physical inventory count item details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getPhyInvCntItemCr",
      handler: showroomController.getPhyInvCntItemCr,
      schemas: {
        body: getPhyInvCntItemCrSchema,
        response: apiResponseSchema,
      },
      summary: "Get physical inventory count item cross reference",
      tags: ["Showroom"],
      contentType: "application/json",
    },

    // =====================================================
    // IO (INTERNAL ORDER) ROUTES
    // =====================================================
    {
      method: "post",
      path: "/getIoShipmentNo",
      handler: showroomController.getIoShipmentNo,
      schemas: {
        body: getIoShipmentNoSchema,
        response: apiResponseSchema,
      },
      summary: "Get IO shipment number",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getIoRcptItemDtls",
      handler: showroomController.getIoRcptItemDtls,
      schemas: {
        body: getIoRcptItemDtlsSchema,
        response: apiResponseSchema,
      },
      summary: "Get IO receipt item details",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getIoRcptItemDtlsCr",
      handler: showroomController.getIoRcptItemDtlsCr,
      schemas: {
        body: getIoRcptItemDtlsCrSchema,
        response: apiResponseSchema,
      },
      summary: "Get IO receipt item details cross reference",
      tags: ["Showroom"],
      contentType: "application/json",
    },

    // =====================================================
    // CONFIRMATION ROUTES (Complex JSON Processing)
    // =====================================================
    {
      method: "post",
      path: "/moConfirm",
      handler: showroomController.moConfirm,
      schemas: {
        body: moConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "Move order confirmation with complex JSON processing",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/ioConfirm",
      handler: showroomController.ioConfirm,
      schemas: {
        body: ioConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "IO confirmation with complex JSON processing",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/poConfirm",
      handler: showroomController.poConfirm,
      schemas: {
        body: poConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "Purchase order confirmation with complex JSON processing",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/rtvConfirm",
      handler: showroomController.rtvConfirm,
      schemas: {
        body: rtvConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "RTV confirmation with complex JSON processing",
      tags: ["Showroom"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/stockConfirm",
      handler: showroomController.stockConfirm,
      schemas: {
        body: stockConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "Stock confirmation with complex JSON processing",
      tags: ["Showroom"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.SHOWROOM
);

// =====================================================
// ROUTE SUMMARY
// =====================================================
/**
 * TOTAL ROUTES: 27
 *
 * Route Distribution:
 * - Inventory & Organization: 3 routes
 * - Sales Order Operations: 3 routes
 * - Move Order Operations: 3 routes
 * - Purchase Order Operations: 3 routes
 * - RTV Operations: 4 routes
 * - Physical Inventory Operations: 3 routes
 * - IO Operations: 3 routes
 * - Confirmation Operations: 5 routes
 *
 * All routes use POST method matching Spring Boot implementation
 * All routes include validation middleware using Zod schemas
 * Route base path: /module/showroom/ (configured at app level)
 */

export default showroomRouter;
