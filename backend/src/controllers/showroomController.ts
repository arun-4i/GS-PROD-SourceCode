/**
 * Showroom Controller Layer
 * Pure migration from Spring Boot ShowroomCO.java
 * All 27 route handlers with exact request/response patterns
 */

import { Request, Response, NextFunction } from "express";
import { ShowroomService } from "../services/showroomService";
import { APIResponse } from "../entities/showroom.entity";

export class ShowroomController {
  private showroomService: ShowroomService;

  constructor() {
    this.showroomService = new ShowroomService();
  }

  // =====================================================
  // INVENTORY & ORGANIZATION OPERATIONS
  // =====================================================

  /**
   * GET_ITEM_DETAILS Services - Get Inventory Organizations
   * Route: POST /module/showroom/getInvOrg
   * Migrated from ShowroomCO.getInvOrg method
   */
  getInvOrg = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getInvOrg(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Physical Inventories
   * Route: POST /module/showroom/getPhysicalInventories
   * Migrated from ShowroomCO.getPhysicalInventories method
   */
  getPhysicalInventories = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.showroomService.getPhysicalInventories(req.body);
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Physical Inventory Sub-Inventory Details
   * Route: POST /module/showroom/getPhyInvSubInvDtls
   * Migrated from ShowroomCO.getPhyInvSubInvDtls method
   */
  getPhyInvSubInvDtls = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.showroomService.getPhyInvSubInvDtls(req.body);
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // SALES ORDER OPERATIONS
  // =====================================================

  /**
   * Get Sale Order Numbers
   * Route: POST /module/showroom/getSaleOrderNum
   * Migrated from ShowroomCO.getSaleOrderNum method
   */
  getSaleOrderNum = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getSaleOrderNum(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Sale Order Details
   * Route: POST /module/showroom/getSaleOrderDetails
   * Migrated from ShowroomCO.getSaleOrderDetails method
   */
  getSaleOrderDetails = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.showroomService.getSaleOrderDetails(req.body);
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Sale Order Details Cross Reference
   * Route: POST /module/showroom/getSaleOrderDetailsCr
   * Migrated from ShowroomCO.getSaleOrderDetailsCr method
   */
  getSaleOrderDetailsCr = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.showroomService.getSaleOrderDetailsCr(req.body);
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // MOVE ORDER OPERATIONS
  // =====================================================

  /**
   * Get Move Order Details
   * Route: POST /module/showroom/getMoDetails
   * Migrated from ShowroomCO.getMoDetails method
   */
  getMoDetails = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getMoDetails(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Move Order Item Details
   * Route: POST /module/showroom/getMoItemDetails
   * Migrated from ShowroomCO.getMoItemDetails method
   */
  getMoItemDetails = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getMoItemDetails(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Move Order Item Cross Reference Details
   * Route: POST /module/showroom/getMoItemCrossRefDtls
   * Migrated from ShowroomCO.getMoItemCrossRefDtls method
   */
  getMoItemCrossRefDtls = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.showroomService.getMoItemCrossRefDtls(req.body);
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // PURCHASE ORDER OPERATIONS
  // =====================================================

  /**
   * Get Purchase Order Number
   * Route: POST /module/showroom/getPoNumber
   * Migrated from ShowroomCO.getPoNumber method
   */
  getPoNumber = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getPoNumber(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Purchase Order Item Details
   * Route: POST /module/showroom/getPoItemDtls
   * Migrated from ShowroomCO.getPoItemDtls method
   */
  getPoItemDtls = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getPoItemDtls(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Purchase Order Item Cross Reference
   * Route: POST /module/showroom/getPoItemCrossRef
   * Migrated from ShowroomCO.getPoItemCrossRef method
   */
  getPoItemCrossRef = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getPoItemCrossRef(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // RTV (RETURN TO VENDOR) OPERATIONS
  // =====================================================

  /**
   * Get RTV Request Number
   * Route: POST /module/showroom/getRTVRequestNum
   * Migrated from ShowroomCO.getRTVRequestNum method
   */
  getRTVRequestNum = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getRTVRequestNum(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get RTV Purchase Order Number
   * Route: POST /module/showroom/getRTVPoNum
   * Migrated from ShowroomCO.getRTVPoNum method
   */
  getRTVPoNum = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getRTVPoNum(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get RTV Item Details
   * Route: POST /module/showroom/getRTVItemDtls
   * Migrated from ShowroomCO.getRTVItemDtls method
   */
  getRTVItemDtls = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getRTVItemDtls(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get RTV Item Details Cross Reference
   * Route: POST /module/showroom/getRTVItemDtlsCr
   * Migrated from ShowroomCO.getRTVItemDtlsCr method
   */
  getRTVItemDtlsCr = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getRTVItemDtlsCr(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // PHYSICAL INVENTORY OPERATIONS
  // =====================================================

  /**
   * Get Physical Inventory Query Details
   * Route: POST /module/showroom/getPhyInvQueryDtls
   * Migrated from ShowroomCO.getPhyInvQueryDtls method
   */
  getPhyInvQueryDtls = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getPhyInvQueryDtls(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Physical Inventory Count Item Details
   * Route: POST /module/showroom/getPhyInvCntItemDtls
   * Migrated from ShowroomCO.getPhyInvCntItemDtls method
   */
  getPhyInvCntItemDtls = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.showroomService.getPhyInvCntItemDtls(req.body);
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get Physical Inventory Count Item Cross Reference
   * Route: POST /module/showroom/getPhyInvCntItemCr
   * Migrated from ShowroomCO.getPhyInvCntItemCr method
   */
  getPhyInvCntItemCr = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getPhyInvCntItemCr(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // IO (INTERNAL ORDER) OPERATIONS
  // =====================================================

  /**
   * Get IO Shipment Number
   * Route: POST /module/showroom/getIoShipmentNo
   * Migrated from ShowroomCO.getIoShipmentNo method
   * Note: Spring Boot comment shows "getIoShipmentNumber" but route is "getIoShipmentNo"
   */
  getIoShipmentNo = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getIoShipmentNo(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get IO Receipt Item Details
   * Route: POST /module/showroom/getIoRcptItemDtls
   * Migrated from ShowroomCO.getIoRcptItemDtls method
   */
  getIoRcptItemDtls = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.getIoRcptItemDtls(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Get IO Receipt Item Details Cross Reference
   * Route: POST /module/showroom/getIoRcptItemDtlsCr
   * Migrated from ShowroomCO.getIoRcptItemDtlsCr method
   */
  getIoRcptItemDtlsCr = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse =
        await this.showroomService.getIoRcptItemDtlsCr(req.body);
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  // =====================================================
  // CONFIRMATION OPERATIONS (Complex JSON Processing)
  // =====================================================

  /**
   * Move Order Confirmation
   * Route: POST /module/showroom/moConfirm
   * Migrated from ShowroomCO.moConfirm method with complex JSON processing
   */
  moConfirm = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.moConfirm(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * IO Confirmation
   * Route: POST /module/showroom/ioConfirm
   * Migrated from ShowroomCO.ioConfirm method with complex JSON processing
   */
  ioConfirm = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.ioConfirm(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Purchase Order Confirmation
   * Route: POST /module/showroom/poConfirm
   * Migrated from ShowroomCO.poConfirm method with complex JSON processing
   */
  poConfirm = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.poConfirm(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * RTV Confirmation
   * Route: POST /module/showroom/rtvConfirm
   * Migrated from ShowroomCO.rtvConfirm method with complex JSON processing
   */
  rtvConfirm = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.rtvConfirm(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };

  /**
   * Stock Confirmation
   * Route: POST /module/showroom/stockConfirm
   * Migrated from ShowroomCO.stockConfirm method with complex JSON processing
   */
  stockConfirm = async (
    req: Request,
    res: Response,
    next: NextFunction
  ): Promise<void> => {
    try {
      const result: APIResponse = await this.showroomService.stockConfirm(
        req.body
      );
      res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  };
}

// Export controller instance for use in routes
export const showroomController = new ShowroomController();
