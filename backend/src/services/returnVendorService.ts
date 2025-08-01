/**
 * ReturnVendor Service Layer
 * Pure migration from Spring Boot ReturnVendorSO.java
 *
 * IMPORTANT: This is SEPARATE from existing Showroom RTV functionality
 * - Showroom RTV uses: showroompkg.GET_RTV_* procedures
 * - ReturnToVendor uses: XXGS_MOB_UTIL_PKG.GET_RTV_* procedures
 * - Different parameters, different business logic, different Oracle packages
 *
 * FOLLOWS existing service patterns from showroomService.ts
 * Keep service layer minimal (pass-through to repository) as per Spring Boot implementation
 */

import { ReturnVendorRepository } from "../repositories/returnVendorRepo";
import { logger } from "../utils/logger";
import {
  RTVRequestNumberRequest,
  RTVItemDetailRequest,
  RTVItemDetailCrRequest,
  RTVItemCodeRequest,
  APIResponse,
} from "../entities/returnVendor.entity";

export class ReturnVendorService {
  private readonly returnVendorRepo: ReturnVendorRepository;

  constructor() {
    this.returnVendorRepo = new ReturnVendorRepository();
  }

  // =====================================================
  // RETURN TO VENDOR SERVICE OPERATIONS
  // =====================================================

  /**
   * Get RTV Request Number
   * Maps: ReturnVendorSO.getRTVRequestNumber method
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM
   * Route: POST /module/returnvendor/rtvrequestnumber
   */
  async getRTVRequestNumber(
    params: RTVRequestNumberRequest
  ): Promise<APIResponse> {
    try {
      logger.info("system", "ReturnVendor - getRTVRequestNumber", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        requestNum: params.P_REQUEST_NUM,
      });

      const result = await this.returnVendorRepo.getRTVRequestNumber(params);

      logger.info("system", "ReturnVendor - getRTVRequestNumber completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        requestNum: params.P_REQUEST_NUM,
        success: true,
      });

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      logger.error("system", "Error in getRTVRequestNumber service", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        requestNum: params.P_REQUEST_NUM,
        error: error instanceof Error ? error.message : "Unknown error",
      });

      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get RTV Item Detail
   * Maps: ReturnVendorSO.getRtvItemDetail method
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS
   * Route: POST /module/returnvendor/rtvitemdetail
   */
  async getRTVItemDetail(params: RTVItemDetailRequest): Promise<APIResponse> {
    try {
      logger.info("system", "ReturnVendor - getRTVItemDetail", {
        requestId: params.P_REQUEST_ID,
      });

      const result = await this.returnVendorRepo.getRTVItemDetail(params);

      logger.info("system", "ReturnVendor - getRTVItemDetail completed", {
        requestId: params.P_REQUEST_ID,
        success: true,
      });

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      logger.error("system", "Error in getRTVItemDetail service", {
        requestId: params.P_REQUEST_ID,
        error: error instanceof Error ? error.message : "Unknown error",
      });

      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get RTV Item Detail Cross Reference
   * Maps: ReturnVendorSO.getRtvItemDetailCr method
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR
   * Route: POST /module/returnvendor/rtvitemdetailcr
   */
  async getRTVItemDetailCr(
    params: RTVItemDetailCrRequest
  ): Promise<APIResponse> {
    try {
      logger.info("system", "ReturnVendor - getRTVItemDetailCr", {
        requestId: params.P_REQUEST_ID,
      });

      const result = await this.returnVendorRepo.getRTVItemDetailCr(params);

      logger.info("system", "ReturnVendor - getRTVItemDetailCr completed", {
        requestId: params.P_REQUEST_ID,
        success: true,
      });

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      logger.error("system", "Error in getRTVItemDetailCr service", {
        requestId: params.P_REQUEST_ID,
        error: error instanceof Error ? error.message : "Unknown error",
      });

      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get RTV Item Code
   * Maps: ReturnVendorSO.get_rtv_item_code method
   * Oracle: XXGS_MOB_UTIL_PKG.get_rtv_item_code
   * Route: POST /module/returnvendor/getrtvitemcode
   */
  async getRTVItemCode(params: RTVItemCodeRequest): Promise<APIResponse> {
    try {
      logger.info("system", "ReturnVendor - getRTVItemCode", {
        inventoryOrgId: params.p_inventory_org_id,
      });

      const result = await this.returnVendorRepo.getRTVItemCode(params);

      logger.info("system", "ReturnVendor - getRTVItemCode completed", {
        inventoryOrgId: params.p_inventory_org_id,
        success: true,
      });

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      logger.error("system", "Error in getRTVItemCode service", {
        inventoryOrgId: params.p_inventory_org_id,
        error: error instanceof Error ? error.message : "Unknown error",
      });

      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }
}
