/**
 * RMA Receipt Router Configuration
 * Pure migration from Spring Boot RMAReceiptCO.java routing
 * Maps all 5 RMA receipt endpoints with exact Spring Boot paths
 * FOLLOWS autoRegisterRoutes pattern from existing routers
 */

import { Router } from "express";
import { rmaReceiptController } from "../controllers/rmaReceiptController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  rmaReceiptDetailSchema,
  rmaCustDetailSchema,
  rmaItemDetailSchema,
  rmaItemCrossRefSchema,
  bundleItemSchema,
  apiResponseSchema,
} from "../validators/rmaReceiptValidator";
import { END_POINTS } from "./end-points";

export const rmaReceiptRouter = Router();

autoRegisterRoutes(
  rmaReceiptRouter,
  [
    {
      method: "post",
      path: "/getrmadetail",
      handler: rmaReceiptController.getRmaDetail,
      schemas: {
        body: rmaReceiptDetailSchema,
        response: apiResponseSchema,
      },
      summary: "Get RMA details by inventory organization ID",
      tags: ["RMA Receipt"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/rmacustdetails",
      handler: rmaReceiptController.getRmaCustDetails,
      schemas: {
        body: rmaCustDetailSchema,
        response: apiResponseSchema,
      },
      summary: "Get RMA customer details by order number",
      tags: ["RMA Receipt"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/rmaitemdetail",
      handler: rmaReceiptController.getRmaItemDetail,
      schemas: {
        body: rmaItemDetailSchema,
        response: apiResponseSchema,
      },
      summary:
        "Get RMA item details by inventory organization and order number",
      tags: ["RMA Receipt"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/rmaitemcrossRef",
      handler: rmaReceiptController.getRmaItemCrossRef,
      schemas: {
        body: rmaItemCrossRefSchema,
        response: apiResponseSchema,
      },
      summary:
        "Get RMA item cross reference by inventory organization and order number",
      tags: ["RMA Receipt"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getbundle",
      handler: rmaReceiptController.getBundleItem,
      schemas: {
        body: bundleItemSchema,
        response: apiResponseSchema,
      },
      summary: "Get bundle item details by order number",
      tags: ["RMA Receipt"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.RMA_RECEIPT
);
