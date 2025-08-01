/**
 * MoConfirm Controller Layer
 * Pure migration from Spring Boot MoConfirmCO.java
 * All 6 route handlers with exact request/response patterns
 */

import { Request, Response, NextFunction } from "express";
import { MoConfirmService } from "../services/moConfirmService";
import { APIResponse } from "../entities/moConfirm.entity";
import {
  createControllerHandler,
  validateArrayBody,
  validateWrapperBody,
  createGetHandler,
} from "../utils/controllerHelpers";

export class MoConfirmController {
  private readonly moConfirmService: MoConfirmService;

  constructor() {
    this.moConfirmService = new MoConfirmService();
  }

  // =====================================================
  // MO CONFIRMATION OPERATIONS
  // =====================================================

  /**
   * Insert MO confirmations with duplicate filtering
   * Route: POST /module/mo/confirm/insertmo
   * Migrated from MoConfirmCO.insertMoConfirm method
   * USES centralized controller handler for consistency
   */
  insertMoConfirm = createControllerHandler(
    (entities: unknown) =>
      this.moConfirmService.insertMoConfirmations(entities as any),
    (body) => validateArrayBody(body, "MO confirmations")
  );

  /**
   * Get all MO confirmations
   * Route: GET /module/mo/confirm/getallmo
   * Migrated from MoConfirmCO.getBinTransfer method
   * USES centralized GET handler for consistent handling
   */
  getAllMoConfirmations = createGetHandler(() =>
    this.moConfirmService.getAllMoConfirmations()
  );

  // =====================================================
  // PICK CONFIRMATION OPERATIONS
  // =====================================================

  /**
   * Insert pick confirmations with complex logic
   * Route: POST /module/mo/confirm/insertpick
   * Migrated from MoConfirmCO.insertPickConfirm method
   * USES centralized controller handler for consistency
   */
  insertPickConfirm = createControllerHandler(
    (entities: unknown) =>
      this.moConfirmService.insertPickConfirmations(entities as any),
    (body) => validateArrayBody(body, "Pick confirmations")
  );

  /**
   * Get all pick confirmations
   * Route: GET /module/mo/confirm/getallpick
   * Migrated from MoConfirmCO.getPickConfirm method
   * USES centralized GET handler for consistent handling
   */
  getAllPickConfirmations = createGetHandler(() =>
    this.moConfirmService.getAllPickConfirmations()
  );

  // =====================================================
  // ORACLE PACKAGE OPERATIONS
  // =====================================================

  /**
   * JSON-based MO quick pick processing
   * Route: POST /module/mo/confirm/insertMoQuickPickJson
   * Migrated from MoConfirmCO.insertMoQuickPickJsonPost method
   */
  insertMoQuickPickJson = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      // Handle raw JSON string payload (matching Spring Boot @RequestBody String payload)
      const payload: string =
        typeof req.body === "string" ? req.body : JSON.stringify(req.body);

      const result: APIResponse =
        await this.moConfirmService.processQuickPickJson(payload);

      // Always return 200 with result data (matching Spring Boot ResponseEntity.ok().body(api))
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // COMBINED OPERATIONS
  // =====================================================

  /**
   * Combined MO & Pick confirmation with cross-validation
   * Route: POST /module/mo/confirm/insertmopick
   * Migrated from MoConfirmCO.insertBothMoPick method
   * USES centralized wrapper handler for consistency
   */
  insertMoPickConfirmations = createControllerHandler(
    (wrapper: unknown) =>
      this.moConfirmService.insertCombinedMoPick(wrapper as any),
    (body) => validateWrapperBody(body, ["moConfirm", "pickConfirm"])
  );
}

// Export controller instance for use in routes
export const moConfirmController = new MoConfirmController();
