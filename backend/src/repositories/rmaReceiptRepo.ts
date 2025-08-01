/**
 * RMA Receipt Repository Layer
 * Pure migration from Spring Boot RMAReceiptPkg.java and RMADeliveryPkg.java
 * Direct Oracle package calls to XXGS_MOB_UTIL_PKG procedures
 * FOLLOWS existing Oracle patterns from showroomRepo.ts and oraclePackageService.ts
 */

import oracledb from "oracledb";
import { withDatabaseConnection } from "../utils/databaseWrapper";
import { logger } from "../utils/logger";
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
  RmaOraclePackageResponse,
  RmaOrderDetailsRow,
  RmaCustomerDetailsRow,
  RmaItemDetailsRow,
  RmaItemCrossRefRow,
  BundleItemDetailsRow,
  RmaDeliveryReceiptRow,
  RmaDeliveryOrderRow,
  RmaDeliveryItemDetailsRow,
  RmaDeliveryItemCrossRow,
} from "../entities/rmaReceiptEntity";

export class RmaReceiptRepository {
  // =====================================================
  // RMA RECEIPT ORACLE PACKAGE OPERATIONS
  // =====================================================

  /**
   * GET_RMA_DETAILS Oracle package call
   * Maps: RMAReceiptPkg.GET_RMA_DETAILS(P_INVENTORY_ORG_ID)
   * Returns: P_ORDER_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaReceiptPkg.GET_RMA_DETAILS(P_INVENTORY_ORG_ID)
   */
  async getRmaDetail(
    params: RmaReceiptDetailRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_DETAILS Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
      });

      const result = await connection.execute<{
        P_ORDER_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_DETAILS(:p_inventory_org_id, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_ORDER_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_DETAILS completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        resultCount: rows.length,
      });

      return { P_ORDER_DTLS_RS: rows as RmaOrderDetailsRow[] };
    }, `getRmaDetail-${params.P_INVENTORY_ORG_ID}`);
  }

  /**
   * GET_RMA_CUST_DETAILS Oracle package call
   * Maps: RMAReceiptPkg.GET_RMA_CUST_DETAILS(P_ORDER_NUM)
   * Returns: P_CUSTOMER_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaReceiptPkg.GET_RMA_CUST_DETAILS(P_ORDER_NUM)
   */
  async getRmaCustDetails(
    params: RmaCustDetailRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_CUST_DETAILS Oracle package", {
        orderNum: params.P_ORDER_NUM,
      });

      const result = await connection.execute<{
        P_CUSTOMER_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_CUST_DETAILS(:p_order_num, :result); 
         END;`,
        {
          p_order_num: params.P_ORDER_NUM,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_CUSTOMER_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_CUST_DETAILS completed", {
        orderNum: params.P_ORDER_NUM,
        resultCount: rows.length,
      });

      return { P_CUSTOMER_DTLS_RS: rows as RmaCustomerDetailsRow[] };
    }, `getRmaCustDetails-${params.P_ORDER_NUM}`);
  }

  /**
   * GET_RMA_ITEM_DETAILS Oracle package call
   * Maps: RMAReceiptPkg.GET_RMA_ITEM_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM)
   * Returns: P_ITEM_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaReceiptPkg.GET_RMA_ITEM_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM)
   */
  async getRmaItemDetail(
    params: RmaItemDetailRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_ITEM_DETAILS Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNum: params.P_ORDER_NUM,
      });

      const result = await connection.execute<{
        P_ITEM_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_DETAILS(:p_inventory_org_id, :p_order_num, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID,
          p_order_num: params.P_ORDER_NUM,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_ITEM_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_ITEM_DETAILS completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNum: params.P_ORDER_NUM,
        resultCount: rows.length,
      });

      return { P_ITEM_DTLS_RS: rows as RmaItemDetailsRow[] };
    }, `getRmaItemDetail-${params.P_INVENTORY_ORG_ID}-${params.P_ORDER_NUM}`);
  }

  /**
   * GET_RMA_ITEM_CROSS_REF Oracle package call
   * Maps: RMAReceiptPkg.GET_RMA_ITEM_CROSS_REF(P_INVENTORY_ORG_ID, P_ORDER_NUM)
   * Returns: P_CROSS_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaReceiptPkg.GET_RMA_ITEM_CROSS_REF(P_INVENTORY_ORG_ID, P_ORDER_NUM)
   */
  async getRmaItemCrossRef(
    params: RmaItemCrossRefRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_ITEM_CROSS_REF Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNum: params.P_ORDER_NUM,
      });

      const result = await connection.execute<{
        P_CROSS_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_CROSS_REF(:p_inventory_org_id, :p_order_num, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID,
          p_order_num: params.P_ORDER_NUM,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_CROSS_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_ITEM_CROSS_REF completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNum: params.P_ORDER_NUM,
        resultCount: rows.length,
      });

      return { P_CROSS_DTLS_RS: rows as RmaItemCrossRefRow[] };
    }, `getRmaItemCrossRef-${params.P_INVENTORY_ORG_ID}-${params.P_ORDER_NUM}`);
  }

  /**
   * BUNDLE_ITEM Oracle package call
   * Maps: RMAReceiptPkg.BUNDLE_ITEM(P_ORDER_NO)
   * Returns: P_BUNDLE_ITEM_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaReceiptPkg.BUNDLE_ITEM(P_ORDER_NO)
   */
  async getBundleItem(
    params: BundleItemRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing BUNDLE_ITEM Oracle package", {
        orderNo: params.P_ORDER_NO,
      });

      const result = await connection.execute<{
        P_BUNDLE_ITEM_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.BUNDLE_ITEM(:p_order_no, :result); 
         END;`,
        {
          p_order_no: params.P_ORDER_NO,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_BUNDLE_ITEM_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "BUNDLE_ITEM completed", {
        orderNo: params.P_ORDER_NO,
        resultCount: rows.length,
      });

      return { P_BUNDLE_ITEM_DTLS_RS: rows as BundleItemDetailsRow[] };
    }, `getBundleItem-${params.P_ORDER_NO}`);
  }

  // =====================================================
  // RMA DELIVERY ORACLE PACKAGE OPERATIONS
  // =====================================================

  /**
   * GET_RMA_DEL_RECEIPT_NUM Oracle package call
   * Maps: RMADeliveryPkg.GET_RMA_DEL_RECEIPT_NUM(P_INVENTORY_ORG_ID)
   * Returns: P_RMA_RECEIPT_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaDeliveryPkg.GET_RMA_DEL_RECEIPT_NUM(P_INVENTORY_ORG_ID)
   */
  async getRmaDelReceiptNum(
    params: RmaDelReceiptNumRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_DEL_RECEIPT_NUM Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
      });

      const result = await connection.execute<{
        P_RMA_RECEIPT_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_DEL_RECEIPT_NUM(:p_inventory_org_id, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_RMA_RECEIPT_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_DEL_RECEIPT_NUM completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        resultCount: rows.length,
      });

      return { P_RMA_RECEIPT_DTLS_RS: rows as RmaDeliveryReceiptRow[] };
    }, `getRmaDelReceiptNum-${params.P_INVENTORY_ORG_ID}`);
  }

  /**
   * GET_RMA_DEL_ORDER_NUM Oracle package call
   * Maps: RMADeliveryPkg.GET_RMA_DEL_ORDER_NUM(P_INVENTORY_ORG_ID)
   * Returns: P_RMA_ORDER_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaDeliveryPkg.GET_RMA_DEL_ORDER_NUM(P_INVENTORY_ORG_ID)
   */
  async getRmaDelOrderNum(
    params: RmaDelOrderNumRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_DEL_ORDER_NUM Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
      });

      const result = await connection.execute<{
        P_RMA_ORDER_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ORDER_NUM(:p_inventory_org_id, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_RMA_ORDER_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_DEL_ORDER_NUM completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        resultCount: rows.length,
      });

      return { P_RMA_ORDER_DTLS_RS: rows as RmaDeliveryOrderRow[] };
    }, `getRmaDelOrderNum-${params.P_INVENTORY_ORG_ID}`);
  }

  /**
   * GET_RMA_DEL_ITEM_DTLS Oracle package call
   * Maps: RMADeliveryPkg.GET_RMA_DEL_ITEM_DTLS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC)
   * Returns: P_RMA_DEL_ITEM_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaDeliveryPkg.GET_RMA_DEL_ITEM_DTLS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC)
   */
  async getRmaDelItemDetail(
    params: RmaDelItemDetailRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_DEL_ITEM_DTLS Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNumber: params.P_ORDER_NUMBER,
        receiptNumber: params.P_RECEIPT_NUMBER,
        withSubinvLoc: params.P_WITH_SUBINV_LOC,
      });

      const result = await connection.execute<{
        P_RMA_DEL_ITEM_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_DTLS(:p_inventory_org_id, :p_order_number, :p_receipt_number, :p_with_subinv_loc, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID,
          p_order_number: params.P_ORDER_NUMBER,
          p_receipt_number: params.P_RECEIPT_NUMBER,
          p_with_subinv_loc: params.P_WITH_SUBINV_LOC,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_RMA_DEL_ITEM_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_DEL_ITEM_DTLS completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNumber: params.P_ORDER_NUMBER,
        receiptNumber: params.P_RECEIPT_NUMBER,
        withSubinvLoc: params.P_WITH_SUBINV_LOC,
        resultCount: rows.length,
      });

      return { P_RMA_DEL_ITEM_DTLS_RS: rows as RmaDeliveryItemDetailsRow[] };
    }, `getRmaDelItemDetail-${params.P_INVENTORY_ORG_ID}-${params.P_ORDER_NUMBER}-${params.P_RECEIPT_NUMBER}`);
  }

  /**
   * GET_RMA_DEL_ITEM_CROSS Oracle package call
   * Maps: RMADeliveryPkg.GET_RMA_DEL_ITEM_CROSS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER)
   * Returns: P_RMA_DEL_CROSS_DTLS_RS (REF_CURSOR)
   * Spring Boot: rmaDeliveryPkg.GET_RMA_DEL_ITEM_CROSS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER)
   */
  async getRmaDelItemCross(
    params: RmaDelItemCrossRequest
  ): Promise<RmaOraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RMA_DEL_ITEM_CROSS Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNumber: params.P_ORDER_NUMBER,
        receiptNumber: params.P_RECEIPT_NUMBER,
      });

      const result = await connection.execute<{
        P_RMA_DEL_CROSS_DTLS_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_CROSS(:p_inventory_org_id, :p_order_number, :p_receipt_number, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID,
          p_order_number: params.P_ORDER_NUMBER,
          p_receipt_number: params.P_RECEIPT_NUMBER,
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_RMA_DEL_CROSS_DTLS_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RMA_DEL_ITEM_CROSS completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        orderNumber: params.P_ORDER_NUMBER,
        receiptNumber: params.P_RECEIPT_NUMBER,
        resultCount: rows.length,
      });

      return { P_RMA_DEL_CROSS_DTLS_RS: rows as RmaDeliveryItemCrossRow[] };
    }, `getRmaDelItemCross-${params.P_INVENTORY_ORG_ID}-${params.P_ORDER_NUMBER}-${params.P_RECEIPT_NUMBER}`);
  }
}
