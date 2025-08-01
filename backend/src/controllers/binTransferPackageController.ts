import { Request, Response } from "express";
import { asyncHandler } from "../utils/error";
import { binTransferPackageService } from "../services/binTransferPackageService";
import { createGetHandler } from "../utils/controllerHelpers";
import {
  oraclePackageParamsSchema,
  ioConLotCreateSchema,
  invCountConfirmCreateSchema,
} from "../validators/binTransferValidator";
import { logger } from "../utils/logger";
import { IoConLotEntity } from "../entities/ioConLot.entity";
import { InvCountConfirmEntity } from "../entities/invCountConfirm.entity";

export class BinTransferPackageController {
  // GET_ITEM_DETAILS Services
  public getItemDetail = asyncHandler(async (req: Request, res: Response) => {
    const parsed = oraclePackageParamsSchema.safeParse(req.body);
    if (!parsed.success) {
      logger.warn("binTransferAction", "Validation failed for getItemDetail", {
        errors: parsed.error.errors,
      });
      return res.status(400).json({
        success: false,
        message: "Validation error",
        error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
      });
    }

    const result = await binTransferPackageService.getItemDetail(parsed.data);
    return res.status(200).json({ success: true, data: result });
  });

  // GET_ITEM_CROSSREF_DTLS Services
  public getItemCrossref = asyncHandler(async (req: Request, res: Response) => {
    const parsed = oraclePackageParamsSchema.safeParse(req.body);
    if (!parsed.success) {
      logger.warn(
        "binTransferAction",
        "Validation failed for getItemCrossref",
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

    const result = await binTransferPackageService.getItemCrossref(parsed.data);
    return res.status(200).json({ success: true, data: result });
  });

  // XXGS_CONF_LOT_DETAILS Services - Get
  // REFACTORED: Uses centralized GET handler for consistency
  public getIOConLot = createGetHandler(() =>
    binTransferPackageService.getIOConLot()
  );

  // XXGS_CONF_LOT_DETAILS Services - Insert
  public insertIOConLot = asyncHandler(async (req: Request, res: Response) => {
    const parsed = ioConLotCreateSchema.array().safeParse(req.body);
    if (!parsed.success) {
      logger.warn("binTransferAction", "Validation failed for insertIOConLot", {
        errors: parsed.error.errors,
      });
      return res.status(400).json({
        success: false,
        message: "Validation error",
        error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
      });
    }

    // Map to entity structure
    const ioConLotEntities: IoConLotEntity[] = parsed.data.map((input) => ({
      ...input,
    }));

    const result =
      await binTransferPackageService.insertIOConLot(ioConLotEntities);
    return res.status(201).json({ success: true, data: result });
  });

  // InvCountConfirm Services - Get
  // REFACTORED: Uses centralized GET handler for consistency
  public getInvCountConfirm = createGetHandler(() =>
    binTransferPackageService.getInvCountConfirm()
  );

  // InvCountConfirm Services - Insert
  public insertInvCountConfirm = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = invCountConfirmCreateSchema.array().safeParse(req.body);
      if (!parsed.success) {
        logger.warn(
          "binTransferAction",
          "Validation failed for insertInvCountConfirm",
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

      // Map to entity structure
      const invCountConfirmEntities: InvCountConfirmEntity[] = parsed.data.map(
        (input) => ({
          ...input,
        })
      );

      const result = await binTransferPackageService.insertInvCountConfirm(
        invCountConfirmEntities
      );
      return res.status(201).json({ success: true, data: result });
    }
  );
}

export const binTransferPackageController = new BinTransferPackageController();
