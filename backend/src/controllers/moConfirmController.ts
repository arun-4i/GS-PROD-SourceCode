/**
 * MoConfirm Controller Layer
 * Pure migration from Spring Boot MoConfirmCO.java
 * All 6 route handlers with exact request/response patterns
 */

import { Request, Response, NextFunction } from "express";
import { MoConfirmService } from "../services/moConfirmService";
import {
  APIResponse,
  MoConfirmEntity,
  PickConfirmEntity,
  MoPickConfirmWrapper,
} from "../entities/moConfirm.entity";

export class MoConfirmController {
  private moConfirmService: MoConfirmService;

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
   */
  insertMoConfirm = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const entities: MoConfirmEntity[] = req.body as MoConfirmEntity[];
      const result: APIResponse =
        await this.moConfirmService.insertMoConfirmations(entities);

      // Return result with appropriate status
      res.status(result.status).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get all MO confirmations
   * Route: GET /module/mo/confirm/getallmo
   * Migrated from MoConfirmCO.getBinTransfer method
   */
  getAllMoConfirmations = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.moConfirmService.getAllMoConfirmations();
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // PICK CONFIRMATION OPERATIONS
  // =====================================================

  /**
   * Insert pick confirmations with complex logic
   * Route: POST /module/mo/confirm/insertpick
   * Migrated from MoConfirmCO.insertPickConfirm method
   */
  insertPickConfirm = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const entities: PickConfirmEntity[] = req.body as PickConfirmEntity[];
      const result: APIResponse =
        await this.moConfirmService.insertPickConfirmations(entities);

      // Handle null response case (matching Spring Boot logic)
      if (!result.data && result.status === 200 && result.data?.length === 0) {
        res.status(204).send(); // No content
        return;
      }

      res.status(result.status).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get all pick confirmations
   * Route: GET /module/mo/confirm/getallpick
   * Migrated from MoConfirmCO.getPickConfirm method
   */
  getAllPickConfirmations = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.moConfirmService.getAllPickConfirmations();
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

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
   */
  insertMoPickConfirmations = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const wrapper: MoPickConfirmWrapper = req.body as MoPickConfirmWrapper;
      const result: APIResponse =
        await this.moConfirmService.insertCombinedMoPick(wrapper);

      // Always return 200 with result data (matching Spring Boot ResponseEntity.ok().body(api))
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };
}

// Export controller instance for use in routes
export const moConfirmController = new MoConfirmController();
