/**
 * ReturnVendor Repository Layer
 * Pure migration from Spring Boot ReturnToVendorPackage.java
 *
 * IMPORTANT: This is SEPARATE from existing Showroom RTV functionality
 * - Showroom RTV uses: showroompkg.GET_RTV_* procedures
 * - ReturnToVendor uses: XXGS_MOB_UTIL_PKG.GET_RTV_* procedures
 * - Different parameters, different business logic, different Oracle packages
 *
 * FOLLOWS existing Oracle patterns from rmaReceiptRepo.ts and showroomRepo.ts
 * Uses SSOT utilities: withDatabaseConnection, logger
 */

import oracledb from "oracledb";
import { logger } from "../utils/logger";
import { withDatabaseConnection } from "../utils/databaseWrapper";
import {
  RTVRequestNumberRequest,
  RTVItemDetailRequest,
  RTVItemDetailCrRequest,
  RTVItemCodeRequest,
  OraclePackageResponse,
} from "../entities/returnVendor.entity";

export class ReturnVendorRepository {
  // =====================================================
  // RTV ORACLE PACKAGE OPERATIONS
  // =====================================================

  /**
   * GET_RTV_REQUEST_NUM Oracle package call
   * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(P_INVENTORY_ORG_ID, P_REQUEST_NUM, P_RTV_RQST_NUM_RS)
   * Spring Boot: ReturnToVendorPackage.getRTVRequestNumber
   * Returns: P_RTV_RQST_NUM_RS (REF_CURSOR)
   */
  async getRTVRequestNumber(
    params: RTVRequestNumberRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RTV_REQUEST_NUM Oracle package", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        requestNum: params.P_REQUEST_NUM,
      });

      const result = await connection.execute<{
        P_RTV_RQST_NUM_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(:p_inventory_org_id, :p_request_num, :result); 
         END;`,
        {
          p_inventory_org_id: params.P_INVENTORY_ORG_ID || "",
          p_request_num: params.P_REQUEST_NUM || "",
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_RTV_RQST_NUM_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RTV_REQUEST_NUM completed", {
        inventoryOrgId: params.P_INVENTORY_ORG_ID,
        requestNum: params.P_REQUEST_NUM,
        resultCount: rows.length,
      });

      return { P_RTV_RQST_NUM_RS: rows };
    }, `getRTVRequestNumber-${params.P_INVENTORY_ORG_ID}-${params.P_REQUEST_NUM}`);
  }

  /**
   * GET_RTV_ITEM_DTLS Oracle package call
   * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(P_REQUEST_ID, P_RTV_ITEM_RS)
   * Spring Boot: ReturnToVendorPackage.getRTVItemDetail
   * Returns: P_RTV_ITEM_RS (REF_CURSOR)
   */
  async getRTVItemDetail(
    params: RTVItemDetailRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RTV_ITEM_DTLS Oracle package", {
        requestId: params.P_REQUEST_ID,
      });

      const result = await connection.execute<{
        P_RTV_ITEM_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(:p_request_id, :result); 
         END;`,
        {
          p_request_id: params.P_REQUEST_ID || "",
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_RTV_ITEM_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RTV_ITEM_DTLS completed", {
        requestId: params.P_REQUEST_ID,
        resultCount: rows.length,
      });

      return { P_RTV_ITEM_RS: rows };
    }, `getRTVItemDetail-${params.P_REQUEST_ID}`);
  }

  /**
   * GET_RTV_ITEM_DTLS_CR Oracle package call
   * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR(P_REQUEST_ID, P_RTV_ITEM_CR_RS)
   * Spring Boot: ReturnToVendorPackage.getRTVItemDetailCr
   * Returns: P_RTV_ITEM_CR_RS (REF_CURSOR)
   */
  async getRTVItemDetailCr(
    params: RTVItemDetailCrRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing GET_RTV_ITEM_DTLS_CR Oracle package", {
        requestId: params.P_REQUEST_ID,
      });

      const result = await connection.execute<{
        P_RTV_ITEM_CR_RS: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR(:p_request_id, :result); 
         END;`,
        {
          p_request_id: params.P_REQUEST_ID || "",
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.P_RTV_ITEM_CR_RS as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "GET_RTV_ITEM_DTLS_CR completed", {
        requestId: params.P_REQUEST_ID,
        resultCount: rows.length,
      });

      return { P_RTV_ITEM_CR_RS: rows };
    }, `getRTVItemDetailCr-${params.P_REQUEST_ID}`);
  }

  /**
   * get_rtv_item_code Oracle package call
   * Maps: XXGS_MOB_UTIL_PKG.get_rtv_item_code(p_inventory_org_id, p_return_itrm_dtls)
   * Spring Boot: ReturnToVendorPackage.get_rtv_item_code
   * Returns: p_return_itrm_dtls (REF_CURSOR)
   */
  async getRTVItemCode(
    params: RTVItemCodeRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Executing get_rtv_item_code Oracle package", {
        inventoryOrgId: params.p_inventory_org_id,
      });

      const result = await connection.execute<{
        p_return_itrm_dtls: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN 
           XXGS_MOB_UTIL_PKG.get_rtv_item_code(:p_inventory_org_id, :result); 
         END;`,
        {
          p_inventory_org_id: params.p_inventory_org_id || "",
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
        }
      );

      const cursor = result.outBinds
        ?.p_return_itrm_dtls as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "get_rtv_item_code completed", {
        inventoryOrgId: params.p_inventory_org_id,
        resultCount: rows.length,
      });

      return { p_return_itrm_dtls: rows };
    }, `getRTVItemCode-${params.p_inventory_org_id}`);
  }
}
