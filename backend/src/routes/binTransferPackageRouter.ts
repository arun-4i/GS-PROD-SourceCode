import { Router } from "express";
import { binTransferPackageController } from "../controllers/binTransferPackageController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  oraclePackageParamsSchema,
  ioConLotCreateSchema,
  invCountConfirmCreateSchema,
} from "../validators/binTransferValidator";
import { z } from "zod";
import { END_POINTS } from "./end-points";

export const binTransferPackageRouter = Router();

autoRegisterRoutes(
  binTransferPackageRouter,
  [
    {
      method: "post",
      path: "/getitemdetail",
      handler: binTransferPackageController.getItemDetail,
      schemas: {
        body: oraclePackageParamsSchema,
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get item details for bin transfer",
      tags: ["BinTransfer"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getitemcrossref",
      handler: binTransferPackageController.getItemCrossref,
      schemas: {
        body: oraclePackageParamsSchema,
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get item cross reference details",
      tags: ["BinTransfer"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getconlotdetail",
      handler: binTransferPackageController.getIOConLot,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get lot details",
      tags: ["BinTransfer"],
    },
    {
      method: "post",
      path: "/insertconlotdetail",
      handler: binTransferPackageController.insertIOConLot,
      schemas: {
        body: ioConLotCreateSchema.array(),
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Bulk insert lot details",
      tags: ["BinTransfer"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getinvcountconfirm",
      handler: binTransferPackageController.getInvCountConfirm,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get inventory count confirmations",
      tags: ["BinTransfer"],
    },
    {
      method: "post",
      path: "/insertinvcountconfirm",
      handler: binTransferPackageController.insertInvCountConfirm,
      schemas: {
        body: invCountConfirmCreateSchema.array(),
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Bulk insert inventory count confirmations",
      tags: ["BinTransfer"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.BIN_TRANSFER_PACKAGE
);
