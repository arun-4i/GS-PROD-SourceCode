/**
 * RMA Delivery Controller Layer
 * Pure migration from Spring Boot RMADeliveryCO.java
 * Handles 4 RMA delivery endpoints with exact request/response patterns
 * FOLLOWS existing controller patterns from showroomController.ts
 */

import { RmaReceiptService } from "../services/rmaReceiptService";
import { createControllerHandler } from "../utils/controllerHelpers";
import {
  RmaDelReceiptNumRequest,
  RmaDelOrderNumRequest,
  RmaDelItemDetailRequest,
  RmaDelItemCrossRequest,
} from "../entities/rmaReceiptEntity";

export class RmaDeliveryController {
  private rmaReceiptService: RmaReceiptService;

  constructor() {
    this.rmaReceiptService = new RmaReceiptService();
  }

  // =====================================================
  // RMA DELIVERY CONTROLLER OPERATIONS
  // =====================================================

  /**
   * Get RMA Delivery Receipt Numbers
   * Route: POST /module/rmadelivery/getrmadelreceiptnum
   * Migrated from RMADeliveryCO.GET_RMA_DEL_RECEIPT_NUM method
   * Spring Boot: @RequestMapping(value = "/getrmadelreceiptnum", method = RequestMethod.POST)
   */
  getRmaDelReceiptNum = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaDelReceiptNum(body as RmaDelReceiptNumRequest)
  );

  /**
   * Get RMA Delivery Order Numbers
   * Route: POST /module/rmadelivery/getrmadelordernum
   * Migrated from RMADeliveryCO.GET_RMA_DEL_ORDER_NUM method
   * Spring Boot: @RequestMapping(value = "/getrmadelordernum", method = RequestMethod.POST)
   */
  getRmaDelOrderNum = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaDelOrderNum(body as RmaDelOrderNumRequest)
  );

  /**
   * Get RMA Delivery Item Details
   * Route: POST /module/rmadelivery/getrmadelitemdtl
   * Migrated from RMADeliveryCO.GET_RMA_DEL_ITEM_DTLS method
   * Spring Boot: @RequestMapping(value = "/getrmadelitemdtl", method = RequestMethod.POST)
   */
  getRmaDelItemDetail = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaDelItemDetail(body as RmaDelItemDetailRequest)
  );

  /**
   * Get RMA Delivery Item Cross Reference
   * Route: POST /module/rmadelivery/getrmadelitemcross
   * Migrated from RMADeliveryCO.GET_RMA_DEL_ITEM_CROSS method
   * Spring Boot: @RequestMapping(value = "/getrmadelitemcross", method = RequestMethod.POST)
   */
  getRmaDelItemCross = createControllerHandler((body: unknown) =>
    this.rmaReceiptService.getRmaDelItemCross(body as RmaDelItemCrossRequest)
  );
}

// Export singleton instance for use in routers
export const rmaDeliveryController = new RmaDeliveryController();
