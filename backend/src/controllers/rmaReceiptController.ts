/**
 * RMA Receipt Controller Layer
 * Pure migration from Spring Boot RMAReceiptCO.java
 * Handles 5 RMA receipt endpoints with exact request/response patterns
 * FOLLOWS existing controller patterns from showroomController.ts
 */

import { RmaReceiptService } from "../services/rmaReceiptService";
import { createControllerHandler } from "../utils/controllerHelpers";
import {
  RmaReceiptDetailRequest,
  RmaCustDetailRequest,
  RmaItemDetailRequest,
  RmaItemCrossRefRequest,
  BundleItemRequest,
} from "../entities/rmaReceiptEntity";

export class RmaReceiptController {
  private rmaReceiptService: RmaReceiptService;

  constructor() {
    this.rmaReceiptService = new RmaReceiptService();
  }

  // =====================================================
  // RMA RECEIPT CONTROLLER OPERATIONS
  // =====================================================

  /**
   * Get RMA Details
   * Route: POST /module/rmareceipt/getrmadetail
   * Migrated from RMAReceiptCO.getRMADetail method
   * Spring Boot: @RequestMapping(value = "/getrmadetail", method = RequestMethod.POST)
   */
  getRmaDetail = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaDetail(body as RmaReceiptDetailRequest)
  );

  /**
   * Get RMA Customer Details
   * Route: POST /module/rmareceipt/rmacustdetails
   * Migrated from RMAReceiptCO.getRMACustDetails method
   * Spring Boot: @RequestMapping(value = "/rmacustdetails", method = RequestMethod.POST)
   */
  getRmaCustDetails = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaCustDetails(body as RmaCustDetailRequest)
  );

  /**
   * Get RMA Item Details
   * Route: POST /module/rmareceipt/rmaitemdetail
   * Migrated from RMAReceiptCO.getRMAItemDetail method
   * Spring Boot: @RequestMapping(value = "/rmaitemdetail", method = RequestMethod.POST)
   */
  getRmaItemDetail = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaItemDetail(body as RmaItemDetailRequest)
  );

  /**
   * Get RMA Item Cross Reference
   * Route: POST /module/rmareceipt/rmaitemcrossRef
   * Migrated from RMAReceiptCO.getRMAItemCrossRef method
   * Spring Boot: @RequestMapping(value = "/rmaitemcrossRef", method = RequestMethod.POST)
   */
  getRmaItemCrossRef = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaItemCrossRef(body as RmaItemCrossRefRequest)
  );

  /**
   * Get Bundle Item Details
   * Route: POST /module/rmareceipt/getbundle
   * Migrated from RMAReceiptCO.getBUNDLE_ITEM method
   * Spring Boot: @RequestMapping(value = "/getbundle", method = RequestMethod.POST)
   */
  getBundleItem = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getBundleItem(body as BundleItemRequest)
  );
}

// Export singleton instance for use in routers
export const rmaReceiptController = new RmaReceiptController();
