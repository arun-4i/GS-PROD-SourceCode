/**
 * RMA Receipt Service Layer
 * Pure migration from Spring Boot RMAReceiptSO.java
 * Business logic layer with Oracle package integration and response formatting
 * FOLLOWS existing service patterns from showroomService.ts and moConfirmService.ts
 */

import {
  RmaReceiptDetailRequest,
  RmaCustDetailRequest,
  RmaItemDetailRequest,
  RmaItemCrossRefRequest,
  BundleItemRequest,
  RmaDelReceiptNumRequest,
  RmaDelOrderNumRequest,
  RmaDelItemDetailRequest,
  RmaDelItemCrossRequest,
  APIResponse,
} from "../entities/rmaReceiptEntity";
import { RmaReceiptRepository } from "../repositories/rmaReceiptRepo";
import {
  createErrorResponse,
  createSuccessResponse,
} from "../validators/common";
import { logger } from "../utils/logger";

export class RmaReceiptService {
  private rmaReceiptRepo: RmaReceiptRepository;

  constructor() {
    this.rmaReceiptRepo = new RmaReceiptRepository();
  }

  // =====================================================
  // RMA RECEIPT SERVICE OPERATIONS
  // =====================================================

  /**
   * Get RMA details by inventory organization
   * Maps: RMAReceiptSO.getRMADetail(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DETAILS(P_INVENTORY_ORG_ID)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaDetail(request: RmaReceiptDetailRequest): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaDetail request", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
      });

      const result = await this.rmaReceiptRepo.getRmaDetail(request);

      logger.info("system", "getRmaDetail completed successfully", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        hasData: !!result.P_ORDER_DTLS_RS,
      });

      return createSuccessResponse(result.P_ORDER_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaDetail", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        error: errorMessage,
      });

      return createErrorResponse(500, "Error retrieving RMA details", error);
    }
  }

  /**
   * Get RMA customer details by order number
   * Maps: RMAReceiptSO.getRMACustDetails(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_CUST_DETAILS(P_ORDER_NUM)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaCustDetails(request: RmaCustDetailRequest): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaCustDetails request", {
        orderNum: request.P_ORDER_NUM,
      });

      const result = await this.rmaReceiptRepo.getRmaCustDetails(request);

      logger.info("system", "getRmaCustDetails completed successfully", {
        orderNum: request.P_ORDER_NUM,
        hasData: !!result.P_CUSTOMER_DTLS_RS,
      });

      return createSuccessResponse(result.P_CUSTOMER_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaCustDetails", {
        orderNum: request.P_ORDER_NUM,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving RMA customer details",
        error
      );
    }
  }

  /**
   * Get RMA item details by inventory organization and order number
   * Maps: RMAReceiptSO.getRMAItemDetail(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaItemDetail(request: RmaItemDetailRequest): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaItemDetail request", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNum: request.P_ORDER_NUM,
      });

      const result = await this.rmaReceiptRepo.getRmaItemDetail(request);

      logger.info("system", "getRmaItemDetail completed successfully", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNum: request.P_ORDER_NUM,
        hasData: !!result.P_ITEM_DTLS_RS,
      });

      return createSuccessResponse(result.P_ITEM_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaItemDetail", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNum: request.P_ORDER_NUM,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving RMA item details",
        error
      );
    }
  }

  /**
   * Get RMA item cross reference by inventory organization and order number
   * Maps: RMAReceiptSO.getRMAItemCrossRef(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_CROSS_REF(P_INVENTORY_ORG_ID, P_ORDER_NUM)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaItemCrossRef(
    request: RmaItemCrossRefRequest
  ): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaItemCrossRef request", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNum: request.P_ORDER_NUM,
      });

      const result = await this.rmaReceiptRepo.getRmaItemCrossRef(request);

      logger.info("system", "getRmaItemCrossRef completed successfully", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNum: request.P_ORDER_NUM,
        hasData: !!result.P_CROSS_DTLS_RS,
      });

      return createSuccessResponse(result.P_CROSS_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaItemCrossRef", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNum: request.P_ORDER_NUM,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving RMA item cross reference",
        error
      );
    }
  }

  /**
   * Get bundle item details by order number
   * Maps: RMAReceiptSO.getBUNDLE_ITEM(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.BUNDLE_ITEM(P_ORDER_NO)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getBundleItem(request: BundleItemRequest): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getBundleItem request", {
        orderNo: request.P_ORDER_NO,
      });

      const result = await this.rmaReceiptRepo.getBundleItem(request);

      logger.info("system", "getBundleItem completed successfully", {
        orderNo: request.P_ORDER_NO,
        hasData: !!result.P_BUNDLE_ITEM_DTLS_RS,
      });

      return createSuccessResponse(result.P_BUNDLE_ITEM_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getBundleItem", {
        orderNo: request.P_ORDER_NO,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving bundle item details",
        error
      );
    }
  }

  // =====================================================
  // RMA DELIVERY SERVICE OPERATIONS
  // =====================================================

  /**
   * Get RMA delivery receipt numbers by inventory organization
   * Maps: RMAReceiptSO.GET_RMA_DEL_RECEIPT_NUM(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_RECEIPT_NUM(P_INVENTORY_ORG_ID)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaDelReceiptNum(
    request: RmaDelReceiptNumRequest
  ): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaDelReceiptNum request", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
      });

      const result = await this.rmaReceiptRepo.getRmaDelReceiptNum(request);

      logger.info("system", "getRmaDelReceiptNum completed successfully", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        hasData: !!result.P_RMA_RECEIPT_DTLS_RS,
      });

      return createSuccessResponse(result.P_RMA_RECEIPT_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaDelReceiptNum", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving RMA delivery receipt numbers",
        error
      );
    }
  }

  /**
   * Get RMA delivery order numbers by inventory organization
   * Maps: RMAReceiptSO.GET_RMA_DEL_ORDER_NUM(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ORDER_NUM(P_INVENTORY_ORG_ID)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaDelOrderNum(
    request: RmaDelOrderNumRequest
  ): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaDelOrderNum request", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
      });

      const result = await this.rmaReceiptRepo.getRmaDelOrderNum(request);

      logger.info("system", "getRmaDelOrderNum completed successfully", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        hasData: !!result.P_RMA_ORDER_DTLS_RS,
      });

      return createSuccessResponse(result.P_RMA_ORDER_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaDelOrderNum", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving RMA delivery order numbers",
        error
      );
    }
  }

  /**
   * Get RMA delivery item details
   * Maps: RMAReceiptSO.GET_RMA_DEL_ITEM_DTLS(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_DTLS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaDelItemDetail(
    request: RmaDelItemDetailRequest
  ): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaDelItemDetail request", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNumber: request.P_ORDER_NUMBER,
        receiptNumber: request.P_RECEIPT_NUMBER,
        withSubinvLoc: request.P_WITH_SUBINV_LOC,
      });

      const result = await this.rmaReceiptRepo.getRmaDelItemDetail(request);

      logger.info("system", "getRmaDelItemDetail completed successfully", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNumber: request.P_ORDER_NUMBER,
        receiptNumber: request.P_RECEIPT_NUMBER,
        withSubinvLoc: request.P_WITH_SUBINV_LOC,
        hasData: !!result.P_RMA_DEL_ITEM_DTLS_RS,
      });

      return createSuccessResponse(result.P_RMA_DEL_ITEM_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaDelItemDetail", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNumber: request.P_ORDER_NUMBER,
        receiptNumber: request.P_RECEIPT_NUMBER,
        withSubinvLoc: request.P_WITH_SUBINV_LOC,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving RMA delivery item details",
        error
      );
    }
  }

  /**
   * Get RMA delivery item cross reference
   * Maps: RMAReceiptSO.GET_RMA_DEL_ITEM_CROSS(Map<String, Object> content)
   * Oracle: XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_CROSS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER)
   * Spring Boot: Returns ResponseEntity<APIResponse> with ls data
   */
  async getRmaDelItemCross(
    request: RmaDelItemCrossRequest
  ): Promise<APIResponse> {
    try {
      logger.info("system", "Processing getRmaDelItemCross request", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNumber: request.P_ORDER_NUMBER,
        receiptNumber: request.P_RECEIPT_NUMBER,
      });

      const result = await this.rmaReceiptRepo.getRmaDelItemCross(request);

      logger.info("system", "getRmaDelItemCross completed successfully", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNumber: request.P_ORDER_NUMBER,
        receiptNumber: request.P_RECEIPT_NUMBER,
        hasData: !!result.P_RMA_DEL_CROSS_DTLS_RS,
      });

      return createSuccessResponse(result.P_RMA_DEL_CROSS_DTLS_RS, 200);
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";

      logger.error("system", "Error in getRmaDelItemCross", {
        inventoryOrgId: request.P_INVENTORY_ORG_ID,
        orderNumber: request.P_ORDER_NUMBER,
        receiptNumber: request.P_RECEIPT_NUMBER,
        error: errorMessage,
      });

      return createErrorResponse(
        500,
        "Error retrieving RMA delivery item cross reference",
        error
      );
    }
  }
}
