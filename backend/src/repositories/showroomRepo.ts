/**
 * Showroom Repository Layer
 * Pure migration from Spring Boot Showroom module
 * Direct Oracle package calls matching showroompkg.* methods from ShowroomSO.java
 */

import oracledb from "oracledb";
import { OracleConnection } from "../config/database";
import { withDatabaseConnection } from "../utils/databaseWrapper";
import {
  OraclePackageResponse,
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

export class ShowroomRepository {
  // =====================================================
  // INVENTORY & ORGANIZATION OPERATIONS
  // =====================================================

  /**
   * GET_INV_ORG Oracle package call
   * Matches: showroompkg.GET_INV_ORG(P_USER_ID, P_ORGANIZATION_CODE, P_ORGANIZATION_NAME)
   * REFACTORED: Uses centralized database wrapper for connection management
   */
  async getInvOrg(params: GetInvOrgRequest): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_INV_ORG(:P_USER_ID, :P_ORGANIZATION_CODE, :P_ORGANIZATION_NAME);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_USER_ID: params.P_USER_ID || "",
          P_ORGANIZATION_CODE: params.P_ORGANIZATION_CODE || "",
          P_ORGANIZATION_NAME: params.P_ORGANIZATION_NAME || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, "getInvOrg");
  }

  /**
   * GET_PHYSICAL_INVENTORIES Oracle package call
   * Matches: showroompkg.GET_PHYSICAL_INVENTORIES(P_INVENTORY_ORG_ID)
   */
  // REFACTORED: Uses centralized database wrapper
  async getPhysicalInventories(
    params: GetPhysicalInventoriesRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PHYSICAL_INVENTORIES(:P_INVENTORY_ORG_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, "getPhysicalInventories");
  }

  /**
   * GET_PHY_INV_SUBINV_DTLS Oracle package call
   * Matches: showroompkg.GET_PHY_INV_SUBINV_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY_ID)
   */
  // REFACTORED: Uses centralized database wrapper
  async getPhyInvSubInvDtls(
    params: GetPhyInvSubInvDtlsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PHY_INV_SUBINV_DTLS(:P_INVENTORY_ORG_ID, :P_PHYSICAL_INVENTORY_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PHYSICAL_INVENTORY_ID: params.P_PHYSICAL_INVENTORY_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, "getPhyInvSubInvDtls");
  }

  // =====================================================
  // SALES ORDER OPERATIONS
  // =====================================================

  /**
   * GET_SALE_ORDER_NUM Oracle package call
   * Matches: showroompkg.GET_SALE_ORDER_NUM(P_INVENTORY_ORG_ID, P_RESOURCE_ID)
   */
  async getSaleOrderNum(
    params: GetSaleOrderNumRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_SALE_ORDER_NUM(:P_INVENTORY_ORG_ID, :P_RESOURCE_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_RESOURCE_ID: params.P_RESOURCE_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, "getSaleOrderNum");
  }

  /**
   * GET_SALE_ORDER_DETAILS Oracle package call
   * Matches: showroompkg.GET_SALE_ORDER_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM, P_MO_NUM, P_PICKSLIP_NUM, P_RESOURCE_ID)
   */
  // REFACTORED: Uses centralized database wrapper
  async getSaleOrderDetails(
    params: GetSaleOrderDetailsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_SALE_ORDER_DETAILS(:P_INVENTORY_ORG_ID, :P_ORDER_NUM, :P_MO_NUM, :P_PICKSLIP_NUM, :P_RESOURCE_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_ORDER_NUM: params.P_ORDER_NUM || "",
          P_MO_NUM: params.P_MO_NUM || "",
          P_PICKSLIP_NUM: params.P_PICKSLIP_NUM || "",
          P_RESOURCE_ID: params.P_RESOURCE_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getSaleOrderDetails");
  }

  /**
   * GET_SALE_ORDER_DETAILS_CR Oracle package call
   * Matches: showroompkg.GET_SALE_ORDER_DETAILS_CR(P_INVENTORY_ORG_ID, P_ORDER_NUM, P_MO_NUM, P_PICKSLIP_NUM, P_RESOURCE_ID)
   */
  async getSaleOrderDetailsCr(
    params: GetSaleOrderDetailsCrRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_SALE_ORDER_DETAILS_CR(:P_INVENTORY_ORG_ID, :P_ORDER_NUM, :P_MO_NUM, :P_PICKSLIP_NUM, :P_RESOURCE_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_ORDER_NUM: params.P_ORDER_NUM || "",
          P_MO_NUM: params.P_MO_NUM || "",
          P_PICKSLIP_NUM: params.P_PICKSLIP_NUM || "",
          P_RESOURCE_ID: params.P_RESOURCE_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getSaleOrderDetailsCr");
  }

  // =====================================================
  // MOVE ORDER OPERATIONS
  // =====================================================

  /**
   * GET_MO_DETAILS Oracle package call
   * Matches: showroompkg.GET_MO_DETAILS(P_ORGANIZATION_ID, P_MOVE_ORDER_NUM, P_DELIVERY_NUM)
   */
  async getMoDetails(
    params: GetMoDetailsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_MO_DETAILS(:P_ORGANIZATION_ID, :P_MOVE_ORDER_NUM, :P_DELIVERY_NUM);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_ORGANIZATION_ID: params.P_ORGANIZATION_ID || "",
          P_MOVE_ORDER_NUM: params.P_MOVE_ORDER_NUM || "",
          P_DELIVERY_NUM: params.P_DELIVERY_NUM || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getMoDetails");
  }

  /**
   * GET_MO_ITEM_DETAILS Oracle package call
   * Matches: showroompkg.GET_MO_ITEM_DETAILS(P_HEADER_ID)
   */
  async getMoItemDetails(
    params: GetMoItemDetailsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_MO_ITEM_DETAILS(:P_HEADER_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_HEADER_ID: params.P_HEADER_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getMoItemDetails");
  }

  /**
   * GET_MO_ITEM_CROSS_REF_DTLS Oracle package call
   * Matches: showroompkg.GET_MO_ITEM_CROSS_REF_DTLS(P_HEADER_ID)
   */
  async getMoItemCrossRefDtls(
    params: GetMoItemCrossRefDtlsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_MO_ITEM_CROSS_REF_DTLS(:P_HEADER_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_HEADER_ID: params.P_HEADER_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getMoItemCrossRefDtls");
  }

  // =====================================================
  // PURCHASE ORDER OPERATIONS
  // =====================================================

  /**
   * GET_PO_NUMBER Oracle package call
   * Matches: showroompkg.GET_PO_NUMBER(P_INVENTORY_ORG_ID, P_PO_NUMBER)
   */
  async getPoNumber(
    params: GetPoNumberRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PO_NUMBER(:P_INVENTORY_ORG_ID, :P_PO_NUMBER);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PO_NUMBER: params.P_PO_NUMBER || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getPoNumber");
  }

  /**
   * GET_RELEASE_NUM Oracle package call
   * Matches: showroompkg.GET_RELEASE_NUM(P_PO_HEADER_ID)
   * Note: This method exists in ShowroomSO.java but is commented out in ShowroomCO.java
   */
  async getReleaseNumber(
    params: GetReleaseNumberRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_RELEASE_NUM(:P_PO_HEADER_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_PO_HEADER_ID: params.P_PO_HEADER_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getReleaseNumber");
  }

  /**
   * GET_PO_ITEM_DTLS Oracle package call
   * Matches: showroompkg.GET_PO_ITEM_DTLS(P_PO_HEADER_ID, P_PO_RELEASE_ID)
   */
  async getPoItemDtls(
    params: GetPoItemDtlsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PO_ITEM_DTLS(:P_PO_HEADER_ID, :P_PO_RELEASE_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_PO_HEADER_ID: params.P_PO_HEADER_ID || "",
          P_PO_RELEASE_ID: params.P_PO_RELEASE_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getPoItemDtls");
  }

  /**
   * GET_PO_ITEM_CROSS_REF Oracle package call
   * Matches: showroompkg.GET_PO_ITEM_CROSS_REF(P_PO_HEADER_ID, P_PO_RELEASE_ID)
   */
  async getPoItemCrossRef(
    params: GetPoItemCrossRefRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PO_ITEM_CROSS_REF(:P_PO_HEADER_ID, :P_PO_RELEASE_ID);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_PO_HEADER_ID: params.P_PO_HEADER_ID || "",
          P_PO_RELEASE_ID: params.P_PO_RELEASE_ID || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getPoItemCrossRef");
  }

  // =====================================================
  // RTV (RETURN TO VENDOR) OPERATIONS
  // =====================================================

  /**
   * GET_RTV_REQUEST_NUM Oracle package call
   * Matches: showroompkg.GET_RTV_REQUEST_NUM(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM, P_ITEM_CODE)
   */
  async getRTVRequestNum(
    params: GetRTVRequestNumRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_RTV_REQUEST_NUM(:P_INVENTORY_ORG_ID, :P_PO_NUMBER, :P_RECEIPT_NUM, :P_ITEM_CODE);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PO_NUMBER: params.P_PO_NUMBER || "",
          P_RECEIPT_NUM: params.P_RECEIPT_NUM || "",
          P_ITEM_CODE: params.P_ITEM_CODE || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getRTVRequestNum");
  }

  /**
   * GET_RTV_PO_NUM Oracle package call
   * Matches: showroompkg.GET_RTV_PO_NUM(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM)
   */
  async getRTVPoNum(
    params: GetRTVPoNumRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_RTV_PO_NUM(:P_INVENTORY_ORG_ID, :P_PO_NUMBER, :P_RECEIPT_NUM);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PO_NUMBER: params.P_PO_NUMBER || "",
          P_RECEIPT_NUM: params.P_RECEIPT_NUM || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getRTVPoNum");
  }

  /**
   * GET_RTV_ITEM_DTLS Oracle package call
   * Matches: showroompkg.GET_RTV_ITEM_DTLS(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM, P_ITEM_CODE)
   */
  async getRTVItemDtls(
    params: GetRTVItemDtlsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_RTV_ITEM_DTLS(:P_INVENTORY_ORG_ID, :P_PO_NUMBER, :P_RECEIPT_NUM, :P_ITEM_CODE);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PO_NUMBER: params.P_PO_NUMBER || "",
          P_RECEIPT_NUM: params.P_RECEIPT_NUM || "",
          P_ITEM_CODE: params.P_ITEM_CODE || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getRTVItemDtls");
  }

  /**
   * GET_RTV_ITEM_DTLS_CR Oracle package call
   * Matches: showroompkg.GET_RTV_ITEM_DTLS_CR(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM, P_ITEM_CODE)
   */
  async getRTVItemDtlsCr(
    params: GetRTVItemDtlsCrRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_RTV_ITEM_DTLS_CR(:P_INVENTORY_ORG_ID, :P_PO_NUMBER, :P_RECEIPT_NUM, :P_ITEM_CODE);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PO_NUMBER: params.P_PO_NUMBER || "",
          P_RECEIPT_NUM: params.P_RECEIPT_NUM || "",
          P_ITEM_CODE: params.P_ITEM_CODE || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getRTVItemDtlsCr");
  }

  // =====================================================
  // PHYSICAL INVENTORY OPERATIONS
  // =====================================================

  /**
   * GET_PHY_INV_QUERY_DTLS Oracle package call
   * Matches: showroompkg.GET_PHY_INV_QUERY_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY, P_SUBINVENTORY)
   */
  async getPhyInvQueryDtls(
    params: GetPhyInvQueryDtlsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PHY_INV_QUERY_DTLS(:P_INVENTORY_ORG_ID, :P_PHYSICAL_INVENTORY, :P_SUBINVENTORY);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PHYSICAL_INVENTORY: params.P_PHYSICAL_INVENTORY || "",
          P_SUBINVENTORY: params.P_SUBINVENTORY || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getPhyInvQueryDtls");
  }

  /**
   * GET_PHYINV_CNT_ITEM_DTLS Oracle package call
   * Matches: showroompkg.GET_PHYINV_CNT_ITEM_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY, P_SUBINVENTORY)
   */
  async getPhyInvCntItemDtls(
    params: GetPhyInvCntItemDtlsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PHYINV_CNT_ITEM_DTLS(:P_INVENTORY_ORG_ID, :P_PHYSICAL_INVENTORY, :P_SUBINVENTORY);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PHYSICAL_INVENTORY: params.P_PHYSICAL_INVENTORY || "",
          P_SUBINVENTORY: params.P_SUBINVENTORY || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getPhyInvCntItemDtls");
  }

  /**
   * GET_PHYINV_CNT_ITEM_CR Oracle package call
   * Matches: showroompkg.GET_PHYINV_CNT_ITEM_CR(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY)
   */
  async getPhyInvCntItemCr(
    params: GetPhyInvCntItemCrRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_PHYINV_CNT_ITEM_CR(:P_INVENTORY_ORG_ID, :P_PHYSICAL_INVENTORY);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_PHYSICAL_INVENTORY: params.P_PHYSICAL_INVENTORY || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getPhyInvCntItemCr");
  }

  // =====================================================
  // IO (INTERNAL ORDER) OPERATIONS
  // =====================================================

  /**
   * GET_IO_SHIPMENT_NO Oracle package call
   * Matches: showroompkg.GET_IO_SHIPMENT_NO(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_DELIVERY_NUM)
   */
  async getIoShipmentNo(
    params: GetIoShipmentNoRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_IO_SHIPMENT_NO(:P_INVENTORY_ORG_ID, :P_SHIPMENT_NUM, :P_DELIVERY_NUM);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_SHIPMENT_NUM: params.P_SHIPMENT_NUM || "",
          P_DELIVERY_NUM: params.P_DELIVERY_NUM || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getIoShipmentNo");
  }

  /**
   * GET_IO_RCPT_ITEM_DTLS Oracle package call
   * Matches: showroompkg.GET_IO_RCPT_ITEM_DTLS(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM)
   */
  async getIoRcptItemDtls(
    params: GetIoRcptItemDtlsRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_IO_RCPT_ITEM_DTLS(:P_INVENTORY_ORG_ID, :P_SHIPMENT_NUM);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_SHIPMENT_NUM: params.P_SHIPMENT_NUM || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getIoRcptItemDtls");
  }

  /**
   * GET_IO_RCPT_ITEM_DTLS_CR Oracle package call
   * Matches: showroompkg.GET_IO_RCPT_ITEM_DTLS_CR(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM)
   */
  async getIoRcptItemDtlsCr(
    params: GetIoRcptItemDtlsCrRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.GET_IO_RCPT_ITEM_DTLS_CR(:P_INVENTORY_ORG_ID, :P_SHIPMENT_NUM);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INVENTORY_ORG_ID: params.P_INVENTORY_ORG_ID || "",
          P_SHIPMENT_NUM: params.P_SHIPMENT_NUM || "",
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { data: rows };
    }, " getIoRcptItemDtlsCr");
  }

  // =====================================================
  // CONFIRMATION OPERATIONS (Complex JSON Processing)
  // =====================================================

  /**
   * MO_CONFIRM Oracle package call with complex JSON processing
   * Matches: showroompkg.MO_CONFIRM(P_INPUT)
   * Returns P_MO_RESULT with STATUS_CODE and MESSAGE
   */
  async moConfirm(params: ConfirmationRequest): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      // Serialize P_INPUT to JSON string matching Spring Boot implementation
      const P_INPUT = params.P_INPUT ? JSON.stringify(params.P_INPUT) : "";

      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.MO_CONFIRM(:P_INPUT);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INPUT: P_INPUT,
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { P_MO_RESULT: rows };
    }, "moConfirm");
  }

  /**
   * IO_CONFIRM Oracle package call with complex JSON processing
   * Matches: showroompkg.IO_CONFIRM(P_INPUT)
   * Returns P_IO_RESULT with STATUS_CODE and MESSAGE
   */
  async ioConfirm(params: ConfirmationRequest): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      // Serialize P_INPUT to JSON string matching Spring Boot implementation
      const P_INPUT = params.P_INPUT ? JSON.stringify(params.P_INPUT) : "";

      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.IO_CONFIRM(:P_INPUT);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INPUT: P_INPUT,
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { P_IO_RESULT: rows };
    }, "ioConfirm");
  }

  /**
   * PO_CONFIRM Oracle package call with complex JSON processing
   * Matches: showroompkg.PO_CONFIRM(P_INPUT)
   * Returns P_PO_RESULT with STATUS_CODE and MESSAGE
   */
  async poConfirm(params: ConfirmationRequest): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      // Serialize P_INPUT to JSON string matching Spring Boot implementation
      const P_INPUT = params.P_INPUT ? JSON.stringify(params.P_INPUT) : "";

      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.PO_CONFIRM(:P_INPUT);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INPUT: P_INPUT,
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { P_PO_RESULT: rows };
    }, "poConfirm");
  }

  /**
   * RTV_CONFIRM Oracle package call with complex JSON processing
   * Matches: showroompkg.RTV_CONFIRM(P_INPUT)
   * Returns P_RTV_RESULT with STATUS_CODE and MESSAGE
   */
  async rtvConfirm(
    params: ConfirmationRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      // Serialize P_INPUT to JSON string matching Spring Boot implementation
      const P_INPUT = params.P_INPUT ? JSON.stringify(params.P_INPUT) : "";

      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.RTV_CONFIRM(:P_INPUT);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INPUT: P_INPUT,
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { P_RTV_RESULT: rows };
    }, "rtvConfirm");
  }

  /**
   * STOCK_CONFIRM Oracle package call with complex JSON processing
   * Matches: showroompkg.STOCK_CONFIRM(P_INPUT)
   * Returns P_STOCK_RESULT with STATUS_CODE and MESSAGE
   */
  async stockConfirm(
    params: ConfirmationRequest
  ): Promise<OraclePackageResponse> {
    return withDatabaseConnection(async (connection) => {
      // Serialize P_INPUT to JSON string matching Spring Boot implementation
      const P_INPUT = params.P_INPUT ? JSON.stringify(params.P_INPUT) : "";

      const result = await connection.execute<{
        result: oracledb.ResultSet<any>;
      }>(
        `BEGIN
           :result := showroompkg.STOCK_CONFIRM(:P_INPUT);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          P_INPUT: P_INPUT,
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<any>;
      const rows = await cursor.getRows();
      await cursor.close();

      return { P_STOCK_RESULT: rows };
    }, "stockConfirm");
  }
}
