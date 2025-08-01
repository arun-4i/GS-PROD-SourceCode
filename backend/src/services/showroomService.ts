/**
 * Showroom Service Layer
 * Pure migration from Spring Boot ShowroomSO.java
 * Implements business logic, transaction logging, and Oracle package integration
 */

import {
  createErrorResponse,
  createSuccessResponse,
} from "../validators/common";
import {
  APIResponse,
  OraclePackageResponse,
  MobTransLogEntity,
  GetInvOrgRequest,
  GetSaleOrderNumRequest,
  GetSaleOrderDetailsRequest,
  GetSaleOrderDetailsCrRequest,
  GetMoDetailsRequest,
  GetMoItemDetailsRequest,
  GetMoItemCrossRefDtlsRequest,
  GetPoNumberRequest,
  GetReleaseNumberRequest,
  GetPoItemDtlsRequest,
  GetPoItemCrossRefRequest,
  GetRTVRequestNumRequest,
  GetRTVPoNumRequest,
  GetRTVItemDtlsRequest,
  GetRTVItemDtlsCrRequest,
  GetPhyInvQueryDtlsRequest,
  GetPhyInvCntItemDtlsRequest,
  GetPhyInvCntItemCrRequest,
  GetPhysicalInventoriesRequest,
  GetPhyInvSubInvDtlsRequest,
  GetIoShipmentNoRequest,
  GetIoRcptItemDtlsRequest,
  GetIoRcptItemDtlsCrRequest,
  ConfirmationRequest,
} from "../entities/showroom.entity";
import { ShowroomRepository } from "../repositories/showroomRepo";

export class ShowroomService {
  private showroomRepo: ShowroomRepository;

  constructor() {
    this.showroomRepo = new ShowroomRepository();
  }

  // =====================================================
  // TRANSACTION LOGGING UTILITIES
  // =====================================================

  /**
   * Create transaction log entry matching mobTransLogEO functionality
   * Migrated from ShowroomSO.java mobTransLog creation pattern
   */
  private createTransactionLog(
    moduleName: string,
    request: any
  ): MobTransLogEntity {
    try {
      return {
        MODULE_NAME: moduleName,
        REQUEST: JSON.stringify(request),
        PROCESSED_TIME: new Date(),
      };
    } catch {
      // Error creating transaction log
      return {
        MODULE_NAME: moduleName,
        REQUEST: "Error serializing request",
        PROCESSED_TIME: new Date(),
      };
    }
  }

  /**
   * Update transaction log with response
   * Matches the pattern in ShowroomSO.java where response is logged after processing
   */
  private updateTransactionLogResponse(
    log: MobTransLogEntity,
    response: any
  ): void {
    try {
      log.RESPONSE = JSON.stringify(response);
    } catch {
      // Error updating transaction log response
      log.RESPONSE = "Error serializing response";
    }
  }

  /**
   * Extract and process confirmation response matching Spring Boot pattern
   * Handles BigDecimal to integer conversion and status code extraction
   */
  private processConfirmationResponse(
    result: OraclePackageResponse,
    resultKey: string
  ): APIResponse {
    const api: APIResponse = { status: 200 };

    const resultObj = result[resultKey];
    if (!resultObj || !Array.isArray(resultObj) || resultObj.length === 0) {
      return api;
    }

    const resultMap = resultObj[0];
    if (!resultMap || typeof resultMap !== "object") {
      return api;
    }

    const { statusCode, message } = this.extractStatusAndMessage(resultMap);
    api.status = statusCode;
    api.error = message;

    return api;
  }

  /**
   * Extract status code and message from result map
   * Separated to reduce cognitive complexity
   */
  private extractStatusAndMessage(resultMap: any): {
    statusCode: number;
    message: string;
  } {
    const statusCodeObj = resultMap.STATUS_CODE;
    const messageObj = resultMap.MESSAGE;

    // Handle BigDecimal to integer conversion matching Spring Boot
    let statusCode = 0;
    if (typeof statusCodeObj === "number") {
      statusCode = Math.floor(statusCodeObj);
    } else if (statusCodeObj && typeof statusCodeObj.toString === "function") {
      statusCode = parseInt(statusCodeObj.toString(), 10) || 0;
    }

    const message = messageObj ? messageObj.toString() : "";
    return { statusCode, message };
  }

  // =====================================================
  // INVENTORY & ORGANIZATION OPERATIONS
  // =====================================================

  /**
   * Get Inventory Organizations
   * Migrated from ShowroomSO.getInvOrg method
   */
  async getInvOrg(content: GetInvOrgRequest): Promise<APIResponse> {
    // Transaction logging - matching Spring Boot pattern
    const mobTransLog = this.createTransactionLog(
      "Showroom - getInvOrg",
      content
    );

    try {
      // Extract parameters with null safety matching Spring Boot
      const P_USER_ID = content.P_USER_ID || "";
      const P_ORGANIZATION_CODE = content.P_ORGANIZATION_CODE || "";
      const P_ORGANIZATION_NAME = content.P_ORGANIZATION_NAME || "";

      // Call Oracle package
      const result = await this.showroomRepo.getInvOrg({
        P_USER_ID,
        P_ORGANIZATION_CODE,
        P_ORGANIZATION_NAME,
      });

      // Update transaction log with response
      this.updateTransactionLogResponse(mobTransLog, result);

      // REFACTORED: Uses centralized response utility
      return createSuccessResponse(result);
    } catch (error) {
      console.error("Error in getInvOrg service:", error);
      return createErrorResponse(500, "Error in getInvOrg service", error);
    }
  }

  /**
   * Get Physical Inventories
   * Migrated from ShowroomSO.getPhysicalInventories method
   */
  async getPhysicalInventories(
    content: GetPhysicalInventoriesRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPhysicalInventories",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";

      const result = await this.showroomRepo.getPhysicalInventories({
        P_INVENTORY_ORG_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPhysicalInventories service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Physical Inventory Sub-Inventory Details
   * Migrated from ShowroomSO.getPhyInvSubInvDtls method
   */
  async getPhyInvSubInvDtls(
    content: GetPhyInvSubInvDtlsRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPhyInvSubInvDtls",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PHYSICAL_INVENTORY_ID = content.P_PHYSICAL_INVENTORY_ID || "";

      const result = await this.showroomRepo.getPhyInvSubInvDtls({
        P_INVENTORY_ORG_ID,
        P_PHYSICAL_INVENTORY_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPhyInvSubInvDtls service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  // =====================================================
  // SALES ORDER OPERATIONS
  // =====================================================

  /**
   * Get Sale Order Numbers
   * Migrated from ShowroomSO.getSaleOrderNum method
   */
  async getSaleOrderNum(content: GetSaleOrderNumRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getSaleOrderNum",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_RESOURCE_ID = content.P_RESOURCE_ID || "";

      const result = await this.showroomRepo.getSaleOrderNum({
        P_INVENTORY_ORG_ID,
        P_RESOURCE_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getSaleOrderNum service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Sale Order Details
   * Migrated from ShowroomSO.getSaleOrderDetails method
   */
  async getSaleOrderDetails(
    content: GetSaleOrderDetailsRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getSaleOrderDetails",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_ORDER_NUM = content.P_ORDER_NUM || "";
      const P_MO_NUM = content.P_MO_NUM || "";
      const P_PICKSLIP_NUM = content.P_PICKSLIP_NUM || "";
      const P_RESOURCE_ID = content.P_RESOURCE_ID || "";

      const result = await this.showroomRepo.getSaleOrderDetails({
        P_INVENTORY_ORG_ID,
        P_ORDER_NUM,
        P_MO_NUM,
        P_PICKSLIP_NUM,
        P_RESOURCE_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getSaleOrderDetails service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Sale Order Details Cross Reference
   * Migrated from ShowroomSO.getSaleOrderDetailsCr method
   */
  async getSaleOrderDetailsCr(
    content: GetSaleOrderDetailsCrRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getSaleOrderDetailsCr",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_ORDER_NUM = content.P_ORDER_NUM || "";
      const P_MO_NUM = content.P_MO_NUM || "";
      const P_PICKSLIP_NUM = content.P_PICKSLIP_NUM || "";
      const P_RESOURCE_ID = content.P_RESOURCE_ID || "";

      const result = await this.showroomRepo.getSaleOrderDetailsCr({
        P_INVENTORY_ORG_ID,
        P_ORDER_NUM,
        P_MO_NUM,
        P_PICKSLIP_NUM,
        P_RESOURCE_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getSaleOrderDetailsCr service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  // =====================================================
  // MOVE ORDER OPERATIONS
  // =====================================================

  /**
   * Get Move Order Details
   * Migrated from ShowroomSO.getMoDetails method
   */
  async getMoDetails(content: GetMoDetailsRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getMoDetails",
      content
    );

    try {
      const P_ORGANIZATION_ID = content.P_ORGANIZATION_ID || "";
      const P_MOVE_ORDER_NUM = content.P_MOVE_ORDER_NUM || "";
      const P_DELIVERY_NUM = content.P_DELIVERY_NUM || "";

      const result = await this.showroomRepo.getMoDetails({
        P_ORGANIZATION_ID,
        P_MOVE_ORDER_NUM,
        P_DELIVERY_NUM,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getMoDetails service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Move Order Item Details
   * Migrated from ShowroomSO.getMoItemDetails method
   */
  async getMoItemDetails(
    content: GetMoItemDetailsRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getMoItemDetails",
      content
    );

    try {
      const P_HEADER_ID = content.P_HEADER_ID || "";

      const result = await this.showroomRepo.getMoItemDetails({
        P_HEADER_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getMoItemDetails service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Move Order Item Cross Reference Details
   * Migrated from ShowroomSO.getMoItemCrossRefDtls method
   */
  async getMoItemCrossRefDtls(
    content: GetMoItemCrossRefDtlsRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getMoItemCrossRefDtls",
      content
    );

    try {
      const P_HEADER_ID = content.P_HEADER_ID || "";

      const result = await this.showroomRepo.getMoItemCrossRefDtls({
        P_HEADER_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getMoItemCrossRefDtls service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  // =====================================================
  // PURCHASE ORDER OPERATIONS
  // =====================================================

  /**
   * Get Purchase Order Number
   * Migrated from ShowroomSO.getPoNumber method
   */
  async getPoNumber(content: GetPoNumberRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPoNumber",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PO_NUMBER = content.P_PO_NUMBER || "";

      const result = await this.showroomRepo.getPoNumber({
        P_INVENTORY_ORG_ID,
        P_PO_NUMBER,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPoNumber service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Release Number
   * Migrated from ShowroomSO.getReleaseNumber method
   * Note: This method exists in service but is commented in controller
   */
  async getReleaseNumber(
    content: GetReleaseNumberRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getReleaseNumber",
      content
    );

    try {
      const P_PO_HEADER_ID = content.P_PO_HEADER_ID || "";

      const result = await this.showroomRepo.getReleaseNumber({
        P_PO_HEADER_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getReleaseNumber service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Purchase Order Item Details
   * Migrated from ShowroomSO.getPoItemDtls method
   */
  async getPoItemDtls(content: GetPoItemDtlsRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPoItemDtls",
      content
    );

    try {
      const P_PO_HEADER_ID = content.P_PO_HEADER_ID || "";
      const P_PO_RELEASE_ID = content.P_PO_RELEASE_ID || "";

      const result = await this.showroomRepo.getPoItemDtls({
        P_PO_HEADER_ID,
        P_PO_RELEASE_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPoItemDtls service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Purchase Order Item Cross Reference
   * Migrated from ShowroomSO.getPoItemCrossRef method
   */
  async getPoItemCrossRef(
    content: GetPoItemCrossRefRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPoItemCrossRef",
      content
    );

    try {
      const P_PO_HEADER_ID = content.P_PO_HEADER_ID || "";
      const P_PO_RELEASE_ID = content.P_PO_RELEASE_ID || "";

      const result = await this.showroomRepo.getPoItemCrossRef({
        P_PO_HEADER_ID,
        P_PO_RELEASE_ID,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPoItemCrossRef service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  // =====================================================
  // RTV (RETURN TO VENDOR) OPERATIONS
  // =====================================================

  /**
   * Get RTV Request Number
   * Migrated from ShowroomSO.getRTVRequestNum method
   */
  async getRTVRequestNum(
    content: GetRTVRequestNumRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getRTVRequestNum",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PO_NUMBER = content.P_PO_NUMBER || "";
      const P_RECEIPT_NUM = content.P_RECEIPT_NUM || "";
      const P_ITEM_CODE = content.P_ITEM_CODE || "";

      const result = await this.showroomRepo.getRTVRequestNum({
        P_INVENTORY_ORG_ID,
        P_PO_NUMBER,
        P_RECEIPT_NUM,
        P_ITEM_CODE,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getRTVRequestNum service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get RTV Purchase Order Number
   * Migrated from ShowroomSO.getRTVPoNum method
   */
  async getRTVPoNum(content: GetRTVPoNumRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getRTVPoNum",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PO_NUMBER = content.P_PO_NUMBER || "";
      const P_RECEIPT_NUM = content.P_RECEIPT_NUM || "";

      const result = await this.showroomRepo.getRTVPoNum({
        P_INVENTORY_ORG_ID,
        P_PO_NUMBER,
        P_RECEIPT_NUM,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getRTVPoNum service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get RTV Item Details
   * Migrated from ShowroomSO.getRTVItemDtls method
   */
  async getRTVItemDtls(content: GetRTVItemDtlsRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getRTVItemDtls",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PO_NUMBER = content.P_PO_NUMBER || "";
      const P_RECEIPT_NUM = content.P_RECEIPT_NUM || "";
      const P_ITEM_CODE = content.P_ITEM_CODE || "";

      const result = await this.showroomRepo.getRTVItemDtls({
        P_INVENTORY_ORG_ID,
        P_PO_NUMBER,
        P_RECEIPT_NUM,
        P_ITEM_CODE,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getRTVItemDtls service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get RTV Item Details Cross Reference
   * Migrated from ShowroomSO.getRTVItemDtlsCr method
   */
  async getRTVItemDtlsCr(
    content: GetRTVItemDtlsCrRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getRTVItemDtlsCr",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PO_NUMBER = content.P_PO_NUMBER || "";
      const P_RECEIPT_NUM = content.P_RECEIPT_NUM || "";
      const P_ITEM_CODE = content.P_ITEM_CODE || "";

      const result = await this.showroomRepo.getRTVItemDtlsCr({
        P_INVENTORY_ORG_ID,
        P_PO_NUMBER,
        P_RECEIPT_NUM,
        P_ITEM_CODE,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getRTVItemDtlsCr service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  // =====================================================
  // PHYSICAL INVENTORY OPERATIONS
  // =====================================================

  /**
   * Get Physical Inventory Query Details
   * Migrated from ShowroomSO.getPhyInvQueryDtls method
   */
  async getPhyInvQueryDtls(
    content: GetPhyInvQueryDtlsRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPhyInvQueryDtls",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PHYSICAL_INVENTORY = content.P_PHYSICAL_INVENTORY || "";
      const P_SUBINVENTORY = content.P_SUBINVENTORY || "";

      const result = await this.showroomRepo.getPhyInvQueryDtls({
        P_INVENTORY_ORG_ID,
        P_PHYSICAL_INVENTORY,
        P_SUBINVENTORY,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPhyInvQueryDtls service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Physical Inventory Count Item Details
   * Migrated from ShowroomSO.getPhyInvCntItemDtls method
   */
  async getPhyInvCntItemDtls(
    content: GetPhyInvCntItemDtlsRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPhyInvCntItemDtls",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PHYSICAL_INVENTORY = content.P_PHYSICAL_INVENTORY || "";
      const P_SUBINVENTORY = content.P_SUBINVENTORY || "";

      const result = await this.showroomRepo.getPhyInvCntItemDtls({
        P_INVENTORY_ORG_ID,
        P_PHYSICAL_INVENTORY,
        P_SUBINVENTORY,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPhyInvCntItemDtls service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get Physical Inventory Count Item Cross Reference
   * Migrated from ShowroomSO.getPhyInvCntItemCr method
   */
  async getPhyInvCntItemCr(
    content: GetPhyInvCntItemCrRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getPhyInvCntItemCr",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_PHYSICAL_INVENTORY = content.P_PHYSICAL_INVENTORY || "";

      const result = await this.showroomRepo.getPhyInvCntItemCr({
        P_INVENTORY_ORG_ID,
        P_PHYSICAL_INVENTORY,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getPhyInvCntItemCr service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  // =====================================================
  // IO (INTERNAL ORDER) OPERATIONS
  // =====================================================

  /**
   * Get IO Shipment Number
   * Migrated from ShowroomSO.getIoShipmentNo method
   */
  async getIoShipmentNo(content: GetIoShipmentNoRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getIoShipmentNo",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_SHIPMENT_NUM = content.P_SHIPMENT_NUM || "";
      const P_DELIVERY_NUM = content.P_DELIVERY_NUM || "";

      const result = await this.showroomRepo.getIoShipmentNo({
        P_INVENTORY_ORG_ID,
        P_SHIPMENT_NUM,
        P_DELIVERY_NUM,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getIoShipmentNo service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get IO Receipt Item Details
   * Migrated from ShowroomSO.getIoRcptItemDtls method
   */
  async getIoRcptItemDtls(
    content: GetIoRcptItemDtlsRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getIoRcptItemDtls",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_SHIPMENT_NUM = content.P_SHIPMENT_NUM || "";

      const result = await this.showroomRepo.getIoRcptItemDtls({
        P_INVENTORY_ORG_ID,
        P_SHIPMENT_NUM,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getIoRcptItemDtls service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Get IO Receipt Item Details Cross Reference
   * Migrated from ShowroomSO.getIoRcptItemDtlsCr method
   */
  async getIoRcptItemDtlsCr(
    content: GetIoRcptItemDtlsCrRequest
  ): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - getIoRcptItemDtlsCr",
      content
    );

    try {
      const P_INVENTORY_ORG_ID = content.P_INVENTORY_ORG_ID || "";
      const P_SHIPMENT_NUM = content.P_SHIPMENT_NUM || "";

      const result = await this.showroomRepo.getIoRcptItemDtlsCr({
        P_INVENTORY_ORG_ID,
        P_SHIPMENT_NUM,
      });

      this.updateTransactionLogResponse(mobTransLog, result);

      return {
        data: result,
        status: 200,
        success: true,
      };
    } catch (error) {
      console.error("Error in getIoRcptItemDtlsCr service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  // =====================================================
  // CONFIRMATION OPERATIONS (Complex JSON Processing)
  // =====================================================

  /**
   * Move Order Confirmation with complex JSON processing
   * Migrated from ShowroomSO.moConfirm method with exact pattern matching
   */
  async moConfirm(content: ConfirmationRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - moConfirm",
      content
    );

    try {
      // Complex JSON processing matching Spring Boot ObjectMapper pattern
      let P_INPUT = "";
      if (content.P_INPUT !== null && content.P_INPUT !== undefined) {
        try {
          // Proper JSON serialization matching Spring Boot
          P_INPUT = JSON.stringify(content.P_INPUT);
        } catch (error) {
          console.error(
            "Failed to convert P_INPUT to JSON: " +
              (error instanceof Error ? error.message : "Unknown error")
          );
        }
      } else {
        // P_INPUT is null - no processing needed
      }

      const result = await this.showroomRepo.moConfirm({ P_INPUT });

      this.updateTransactionLogResponse(mobTransLog, result);

      // Process confirmation response matching Spring Boot pattern
      return this.processConfirmationResponse(result, "P_MO_RESULT");
    } catch (error) {
      console.error("Error in moConfirm service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Stock Confirmation with complex JSON processing
   * Migrated from ShowroomSO.stockConfirm method with exact pattern matching
   */
  async stockConfirm(content: ConfirmationRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - stockConfirm",
      content
    );

    try {
      // Complex JSON processing matching Spring Boot ObjectMapper pattern
      let P_INPUT = "";
      if (content.P_INPUT !== null && content.P_INPUT !== undefined) {
        try {
          // Proper JSON serialization matching Spring Boot
          P_INPUT = JSON.stringify(content.P_INPUT);
        } catch (error) {
          console.error(
            "Failed to convert P_INPUT to JSON: " +
              (error instanceof Error ? error.message : "Unknown error")
          );
        }
      } else {
        // P_INPUT is null - no processing needed
      }

      const result = await this.showroomRepo.stockConfirm({ P_INPUT });

      this.updateTransactionLogResponse(mobTransLog, result);

      // Process confirmation response matching Spring Boot pattern
      return this.processConfirmationResponse(result, "P_STOCK_RESULT");
    } catch (error) {
      console.error("Error in stockConfirm service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * RTV Confirmation with complex JSON processing
   * Migrated from ShowroomSO.rtvConfirm method with exact pattern matching
   */
  async rtvConfirm(content: ConfirmationRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - rtvConfirm",
      content
    );

    try {
      // Complex JSON processing matching Spring Boot ObjectMapper pattern
      let P_INPUT = "";
      if (content.P_INPUT !== null && content.P_INPUT !== undefined) {
        try {
          // Proper JSON serialization matching Spring Boot
          P_INPUT = JSON.stringify(content.P_INPUT);
        } catch (error) {
          console.error(
            "Failed to convert P_INPUT to JSON: " +
              (error instanceof Error ? error.message : "Unknown error")
          );
        }
      } else {
        // P_INPUT is null - no processing needed
      }

      const result = await this.showroomRepo.rtvConfirm({ P_INPUT });

      this.updateTransactionLogResponse(mobTransLog, result);

      // Process confirmation response matching Spring Boot pattern
      return this.processConfirmationResponse(result, "P_RTV_RESULT");
    } catch (error) {
      console.error("Error in rtvConfirm service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * Purchase Order Confirmation with complex JSON processing
   * Migrated from ShowroomSO.poConfirm method with exact pattern matching
   */
  async poConfirm(content: ConfirmationRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - poConfirm",
      content
    );

    try {
      // Complex JSON processing matching Spring Boot ObjectMapper pattern
      let P_INPUT = "";
      if (content.P_INPUT !== null && content.P_INPUT !== undefined) {
        try {
          // Proper JSON serialization matching Spring Boot
          P_INPUT = JSON.stringify(content.P_INPUT);
        } catch (error) {
          console.error(
            "Failed to convert P_INPUT to JSON: " +
              (error instanceof Error ? error.message : "Unknown error")
          );
        }
      } else {
        // P_INPUT is null - no processing needed
      }

      const result = await this.showroomRepo.poConfirm({ P_INPUT });

      this.updateTransactionLogResponse(mobTransLog, result);

      // Process confirmation response matching Spring Boot pattern
      return this.processConfirmationResponse(result, "P_PO_RESULT");
    } catch (error) {
      console.error("Error in poConfirm service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }

  /**
   * IO Confirmation with complex JSON processing
   * Migrated from ShowroomSO.ioConfirm method with exact pattern matching
   */
  async ioConfirm(content: ConfirmationRequest): Promise<APIResponse> {
    // Transaction logging
    const mobTransLog = this.createTransactionLog(
      "Showroom - ioConfirm",
      content
    );

    try {
      // Complex JSON processing matching Spring Boot ObjectMapper pattern
      let P_INPUT = "";
      if (content.P_INPUT !== null && content.P_INPUT !== undefined) {
        try {
          // Proper JSON serialization matching Spring Boot
          P_INPUT = JSON.stringify(content.P_INPUT);
        } catch (error) {
          console.error(
            "Failed to convert P_INPUT to JSON: " +
              (error instanceof Error ? error.message : "Unknown error")
          );
        }
      } else {
        // P_INPUT is null - no processing needed
      }

      const result = await this.showroomRepo.ioConfirm({ P_INPUT });

      this.updateTransactionLogResponse(mobTransLog, result);

      // Process confirmation response matching Spring Boot pattern
      return this.processConfirmationResponse(result, "P_IO_RESULT");
    } catch (error) {
      console.error("Error in ioConfirm service:", error);
      return {
        error: error instanceof Error ? error.message : "Unknown error",
        status: 500,
        success: false,
      };
    }
  }
}
