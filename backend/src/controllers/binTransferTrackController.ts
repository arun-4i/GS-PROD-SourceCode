import { Request, Response } from "express";
import { asyncHandler } from "../utils/error";
import { binTransferTrackService } from "../services/binTransferTrackService";
import {
  createControllerHandler,
  validateArrayBody,
  createGetHandler,
} from "../utils/controllerHelpers";
import {
  binTransferTrackHdrCreateSchema,
  binTransferTrackPickCreateSchema,
  binTransferTrackDropCreateSchema,
  quickDropCreateSchema,
  oraclePackageParamsSchema,
} from "../validators/binTransferValidator";
import { logger } from "../utils/logger";
import { BinTransferTrackHdrEntity } from "../entities/binTransferTrackHdr.entity";
import { BinTransferTrackPickEntity } from "../entities/binTransferTrackPick.entity";
import { BinTransferTrackDropEntity } from "../entities/binTransferTrackDrop.entity";
import { QuickDropEntity } from "../entities/quickDrop.entity";

export class BinTransferTrackController {
  // Insert Bin Transfer Header
  public insertBinTransferHdr = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = binTransferTrackHdrCreateSchema
        .array()
        .safeParse(req.body);
      if (!parsed.success) {
        logger.warn(
          "userAction",
          "Validation failed for insertBinTransferHdr",
          {
            errors: parsed.error.errors,
          }
        );
        return res.status(400).json({
          success: false,
          message: "Validation error",
          error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
        });
      }

      const entities: BinTransferTrackHdrEntity[] = parsed.data.map(
        (input) => ({ ...input })
      );
      const result =
        await binTransferTrackService.insertBinTransferHdr(entities);
      return res.status(201).json({ success: true, data: result });
    }
  );

  // Get All Bin Transfer Headers
  // REFACTORED: Uses centralized GET handler for consistency
  public getBinTransferHdr = createGetHandler(() =>
    binTransferTrackService.getBinTransferHdr()
  );

  // Insert Bin Transfer Lines
  public insertBinTransferLns = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = binTransferTrackPickCreateSchema
        .array()
        .safeParse(req.body);
      if (!parsed.success) {
        logger.warn(
          "userAction",
          "Validation failed for insertBinTransferLns",
          {
            errors: parsed.error.errors,
          }
        );
        return res.status(400).json({
          success: false,
          message: "Validation error",
          error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
        });
      }

      const entities: BinTransferTrackPickEntity[] = parsed.data.map(
        (input) => ({ ...input })
      );
      const result =
        await binTransferTrackService.insertBinTransferLns(entities);
      return res.status(201).json({ success: true, data: result });
    }
  );

  // Get All Bin Transfer Lines
  // REFACTORED: Uses centralized GET handler for consistency
  public getBinTransferLns = createGetHandler(() =>
    binTransferTrackService.getBinTransferLns()
  );

  // Insert Bin Transfer Drop
  public insertBinTransferDrop = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = binTransferTrackDropCreateSchema
        .array()
        .safeParse(req.body);
      if (!parsed.success) {
        logger.warn(
          "userAction",
          "Validation failed for insertBinTransferDrop",
          {
            errors: parsed.error.errors,
          }
        );
        return res.status(400).json({
          success: false,
          message: "Validation error",
          error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
        });
      }

      const entities: BinTransferTrackDropEntity[] = parsed.data.map(
        (input) => ({ ...input })
      );
      const result =
        await binTransferTrackService.insertBinTransferDrop(entities);
      return res.status(201).json({ success: true, data: result });
    }
  );

  // Get All Bin Transfer Drops
  public getBinTransferDrop = asyncHandler(
    async (_req: Request, res: Response) => {
      const result = await binTransferTrackService.getBinTransferDrop();
      return res.status(200).json({ success: true, data: result });
    }
  );

  // Get Bin Transfer Reference Number
  public getBinTransRefNo = asyncHandler(
    async (_req: Request, res: Response) => {
      const result = await binTransferTrackService.getBinTransRefNo();
      return res.status(200).json({ success: true, data: result });
    }
  );

  // Get Bin Transfer Drop Header
  public binTransferDropHdr = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = oraclePackageParamsSchema.safeParse(req.body);
      if (!parsed.success) {
        logger.warn("userAction", "Validation failed for binTransferDropHdr", {
          errors: parsed.error.errors,
        });
        return res.status(400).json({
          success: false,
          message: "Validation error",
          error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
        });
      }

      const result = await binTransferTrackService.getBinTransDropHdr(
        parsed.data
      );
      return res.status(200).json({ success: true, data: result });
    }
  );

  // Get Bin Transfer Drop Details
  public binTransferDropDtl = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = oraclePackageParamsSchema.safeParse(req.body);
      if (!parsed.success) {
        logger.warn("userAction", "Validation failed for binTransferDropDtl", {
          errors: parsed.error.errors,
        });
        return res.status(400).json({
          success: false,
          message: "Validation error",
          error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
        });
      }

      const result = await binTransferTrackService.getBinTransDropDtls(
        parsed.data
      );
      return res.status(200).json({ success: true, data: result });
    }
  );

  // Get Bin Number
  public getBinNumber = asyncHandler(async (req: Request, res: Response) => {
    const parsed = oraclePackageParamsSchema.safeParse(req.body);
    if (!parsed.success) {
      logger.warn("userAction", "Validation failed for getBinNumber", {
        errors: parsed.error.errors,
      });
      return res.status(400).json({
        success: false,
        message: "Validation error",
        error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
      });
    }

    const result = await binTransferTrackService.getBinNumber(parsed.data);
    return res.status(200).json({ success: true, data: result });
  });

  // Insert Quick Drop
  public insertQuickDrop = asyncHandler(async (req: Request, res: Response) => {
    const parsed = quickDropCreateSchema.safeParse(req.body);
    if (!parsed.success) {
      logger.warn("userAction", "Validation failed for insertQuickDrop", {
        errors: parsed.error.errors,
      });
      return res.status(400).json({
        success: false,
        message: "Validation error",
        error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
      });
    }

    const entity: QuickDropEntity = parsed.data;
    const result = await binTransferTrackService.insertQuickDrop(entity);
    return res.status(201).json({ success: true, data: result });
  });
}

export const binTransferTrackController = new BinTransferTrackController();
