/**
 * RMA Delivery Router Configuration
 * Pure migration from Spring Boot RMADeliveryCO.java routing
 * Maps all 4 RMA delivery endpoints with exact Spring Boot paths
 * FOLLOWS autoRegisterRoutes pattern from existing routers
 */

import { Router } from "express";
import { rmaDeliveryController } from "../controllers/rmaDeliveryController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  rmaDelReceiptNumSchema,
  rmaDelOrderNumSchema,
  rmaDelItemDetailSchema,
  rmaDelItemCrossSchema,
  apiResponseSchema,
} from "../validators/rmaReceiptValidator";
import { END_POINTS } from "./end-points";

export const rmaDeliveryRouter = Router();

autoRegisterRoutes(
  rmaDeliveryRouter,
  [
    {
      method: "post",
      path: "/getrmadelreceiptnum",
      handler: rmaDeliveryController.getRmaDelReceiptNum,
      schemas: {
        body: rmaDelReceiptNumSchema,
        response: apiResponseSchema,
      },
      summary: "Get RMA delivery receipt numbers by inventory organization ID",
      tags: ["RMA Delivery"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getrmadelordernum",
      handler: rmaDeliveryController.getRmaDelOrderNum,
      schemas: {
        body: rmaDelOrderNumSchema,
        response: apiResponseSchema,
      },
      summary: "Get RMA delivery order numbers by inventory organization ID",
      tags: ["RMA Delivery"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getrmadelitemdtl",
      handler: rmaDeliveryController.getRmaDelItemDetail,
      schemas: {
        body: rmaDelItemDetailSchema,
        response: apiResponseSchema,
      },
      summary:
        "Get RMA delivery item details with subinventory location options",
      tags: ["RMA Delivery"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getrmadelitemcross",
      handler: rmaDeliveryController.getRmaDelItemCross,
      schemas: {
        body: rmaDelItemCrossSchema,
        response: apiResponseSchema,
      },
      summary: "Get RMA delivery item cross reference details",
      tags: ["RMA Delivery"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.RMA_DELIVERY
);
