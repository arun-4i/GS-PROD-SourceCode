import { Router } from "express";
import { binTransferTrackController } from "../controllers/binTransferTrackController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  binTransferTrackHdrCreateSchema,
  binTransferTrackPickCreateSchema,
  binTransferTrackDropCreateSchema,
  quickDropCreateSchema,
  oraclePackageParamsSchema,
} from "../validators/binTransferValidator";
import { z } from "zod";
import { END_POINTS } from "./end-points";

export const binTransferTrackRouter = Router();

autoRegisterRoutes(
  binTransferTrackRouter,
  [
    {
      method: "post",
      path: "/insert/bintrasfer/hdr",
      handler: binTransferTrackController.insertBinTransferHdr,
      schemas: {
        body: binTransferTrackHdrCreateSchema.array(),
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Bulk insert bin transfer headers",
      tags: ["BinTransferTrack"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getall/bintrasfer/hdr",
      handler: binTransferTrackController.getBinTransferHdr,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get all bin transfer headers",
      tags: ["BinTransferTrack"],
    },
    {
      method: "post",
      path: "/insert/bintrasfer/lns",
      handler: binTransferTrackController.insertBinTransferLns,
      schemas: {
        body: binTransferTrackPickCreateSchema.array(),
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Bulk insert bin transfer lines",
      tags: ["BinTransferTrack"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getall/bintrasfer/lns",
      handler: binTransferTrackController.getBinTransferLns,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get all bin transfer lines",
      tags: ["BinTransferTrack"],
    },
    {
      method: "post",
      path: "/insert/bintrasfer/drop",
      handler: binTransferTrackController.insertBinTransferDrop,
      schemas: {
        body: binTransferTrackDropCreateSchema.array(),
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Bulk insert bin transfer drops",
      tags: ["BinTransferTrack"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getall/bintrasfer/drop",
      handler: binTransferTrackController.getBinTransferDrop,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get all bin transfer drops",
      tags: ["BinTransferTrack"],
    },
    {
      method: "post",
      path: "/getrefhdr",
      handler: binTransferTrackController.getBinTransRefNo,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get bin transfer reference number",
      tags: ["BinTransferTrack"],
    },
    {
      method: "post",
      path: "/drop/hdr",
      handler: binTransferTrackController.binTransferDropHdr,
      schemas: {
        body: oraclePackageParamsSchema,
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get bin transfer drop header",
      tags: ["BinTransferTrack"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/drop/dtl",
      handler: binTransferTrackController.binTransferDropDtl,
      schemas: {
        body: oraclePackageParamsSchema,
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get bin transfer drop details",
      tags: ["BinTransferTrack"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/bin/number",
      handler: binTransferTrackController.getBinNumber,
      schemas: {
        body: oraclePackageParamsSchema,
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get bin number",
      tags: ["BinTransferTrack"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/insert/bintrasfer/quickdrop",
      handler: binTransferTrackController.insertQuickDrop,
      schemas: {
        body: quickDropCreateSchema,
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Insert quick drop",
      tags: ["BinTransferTrack"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.BIN_TRANSFER_TRACK
);
