/**
 * ReturnVendor Controller Layer
 * Pure migration from Spring Boot ReturnVendorCO.java
 *
 * IMPORTANT: This is SEPARATE from existing Showroom RTV functionality
 * - Showroom RTV uses: showroompkg.GET_RTV_* procedures with different parameters
 * - ReturnToVendor uses: XXGS_MOB_UTIL_PKG.GET_RTV_* procedures with different parameters
 * - Different business logic, different routes (/module/returnvendor/ vs /module/showroom/)
 *
 * FOLLOWS existing controller patterns from showroomController.ts and rmaReceiptController.ts
 * Uses SSOT utilities: createControllerHandler from utils/controllerHelpers.ts
 */

import { ReturnVendorService } from "../services/returnVendorService";
import { createControllerHandler } from "../utils/controllerHelpers";
import {
  RTVRequestNumberRequest,
  RTVItemDetailRequest,
  RTVItemDetailCrRequest,
  RTVItemCodeRequest,
} from "../entities/returnVendor.entity";

export class ReturnVendorController {
  private returnVendorService: ReturnVendorService;

  constructor() {
    this.returnVendorService = new ReturnVendorService();
  }

  // =====================================================
  // RETURN TO VENDOR CONTROLLER OPERATIONS
  // =====================================================

  /**
   * Get RTV Request Number
   * Route: POST /module/returnvendor/rtvrequestnumber
   * Maps: ReturnVendorCO.getRTVRequestNumber method
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM
   * Spring Boot: @RequestMapping(value = "/rtvrequestnumber", method = RequestMethod.POST)
   */
  getRTVRequestNumber = createControllerHandler((body: unknown) =>
    this.returnVendorService.getRTVRequestNumber(
      body as RTVRequestNumberRequest
    )
  );

  /**
   * Get RTV Item Detail
   * Route: POST /module/returnvendor/rtvitemdetail
   * Maps: ReturnVendorCO.getRtvItemDetail method
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS
   * Spring Boot: @RequestMapping(value = "/rtvitemdetail", method = RequestMethod.POST)
   */
  getRTVItemDetail = createControllerHandler((body: unknown) =>
    this.returnVendorService.getRTVItemDetail(body as RTVItemDetailRequest)
  );

  /**
   * Get RTV Item Detail Cross Reference
   * Route: POST /module/returnvendor/rtvitemdetailcr
   * Maps: ReturnVendorCO.getRtvItemDetailCr method
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR
   * Spring Boot: @RequestMapping(value = "/rtvitemdetailcr", method = RequestMethod.POST)
   */
  getRTVItemDetailCr = createControllerHandler((body: unknown) =>
    this.returnVendorService.getRTVItemDetailCr(body as RTVItemDetailCrRequest)
  );

  /**
   * Get RTV Item Code
   * Route: POST /module/returnvendor/getrtvitemcode
   * Maps: ReturnVendorCO.get_rtv_item_code method
   * Oracle: XXGS_MOB_UTIL_PKG.get_rtv_item_code
   * Spring Boot: @RequestMapping(value = "/getrtvitemcode", method = RequestMethod.POST)
   */
  getRTVItemCode = createControllerHandler((body: unknown) =>
    this.returnVendorService.getRTVItemCode(body as RTVItemCodeRequest)
  );
}
