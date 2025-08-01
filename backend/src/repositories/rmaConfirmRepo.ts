/**
 * RMAConfirm Repository Layer
 * Pure migration from Spring Boot RMAConfirmRO.java
 * FOLLOWS existing Oracle patterns from moConfirmRepo.ts and showroomRepo.ts
 * Implements native SQL queries with proper bind variables and connection management
 */

import oracledb from "oracledb";
import { logger } from "../utils/logger";
import { withDatabaseConnection } from "../utils/databaseWrapper";
import { executeCountQuery } from "../utils/oracleHelpers";
import {
  RmaConfirmEntity,
  LocationValidationResponse,
  RmaDeliveryDuplicateCheck,
  RmaReceiptDuplicateCheck,
} from "../entities/rmaConfirm.entity";

export class RmaConfirmRepository {
  // REUSE existing Oracle connection pool pattern

  // =====================================================
  // BULK OPERATIONS (FOLLOW existing patterns)
  // =====================================================

  /**
   * Bulk insert RMA confirmations
   * FOLLOWS saveAll() pattern from moConfirmRepo.ts
   * Maps Spring Boot JpaRepository.saveAll() method
   */
  async saveAll(entities: RmaConfirmEntity[]): Promise<RmaConfirmEntity[]> {
    if (!entities || entities.length === 0) {
      return [];
    }

    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Bulk inserting RMA confirmations", {
        count: entities.length,
      });

      const insertedEntities: RmaConfirmEntity[] = [];

      // Process each entity with Oracle sequence generation
      for (const entity of entities) {
        const result = await connection.execute<{ rma_id: number }>(
          `INSERT INTO XXGS_RMA_CONFIRMATIONS (
            RMA_ID, TRANSACTION_TYPE, PARTY_ID, CUST_ACCOUNT_ID,
            BILL_TO_SITE_USE_ID, SHIP_TO_SITE_USE_ID, INVENTORY_ORG_ID,
            LINE_NUMBER, ITEM_ID, UOM_CODE, ORDER_QUANTITY, RECEIPT_NUMBER,
            RETURN_QUANTITY, DELIVERED_QUANTITY, RECEIVED_QUANTITY,
            ITEM_CONDITION, SUGGESTED_SUBINVENTORY, DELIVERED_SUBINVENTORY,
            SUGGESTED_LOCATOR_ID, DELIVERED_LOCATOR_ID, PERSON_ID, STATUS,
            ERROR_MESSAGE, ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2,
            ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5, ATTRIBUTE6, ATTRIBUTE7,
            ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10, ATTRIBUTE11, ATTRIBUTE12,
            ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15, LAST_UPDATE_DATE,
            LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN,
            ORG_ID, ORG_CODE, ORDER_HEADER_ID, ORDER_LINE_ID,
            PARENT_TRANSACTION_ID, PRIMARY_UNIT_OF_MEASURE
          ) VALUES (
            XXGS_RMA_ID_S.NEXTVAL, :transactionType, :partyId, :custAccountId,
            :billToSitesUseId, :shipToSitesUseId, :inventoryOrgId,
            :lineNumber, :itemId, :uomCode, :orderQuantity, :receiptNumber,
            :returnQuantity, :deliveredQuantity, :receivedQuantity,
            :itemCondition, :suggestedSubinventory, :deliveredSubinventory,
            :suggestedLocatorId, :deliveredLocatorId, :personId, :status,
            :errorMessage, :attributeCategory, :attribute1, :attribute2,
            :attribute3, :attribute4, :attribute5, :attribute6, :attribute7,
            :attribute8, :attribute9, :attribute10, :attribute11, :attribute12,
            :attribute13, :attribute14, :attribute15, SYSDATE,
            :lastUpdatedBy, SYSDATE, :createdBy, :lastUpdateLogin,
            :orgId, :orgCode, :orderHeaderId, :orderlineId,
            :parentTransactionId, :primaryUnitOfMeasurement
          ) RETURNING RMA_ID INTO :rma_id`,
          {
            // Use proper bind variables (no string interpolation)
            transactionType: entity.transactionType || null,
            partyId: entity.partyId || null,
            custAccountId: entity.custAccountId || null,
            billToSitesUseId: entity.billToSitesUseId || null,
            shipToSitesUseId: entity.shipToSitesUseId || null,
            inventoryOrgId: entity.inventoryOrgId || null,
            lineNumber: entity.lineNumber || null,
            itemId: entity.itemId || null,
            uomCode: entity.uomCode || null,
            orderQuantity: entity.orderQuantity || null,
            receiptNumber: entity.receiptNumber || null,
            returnQuantity: entity.returnQuantity || null,
            deliveredQuantity: entity.deliveredQuantity || null,
            receivedQuantity: entity.receivedQuantity || null,
            itemCondition: entity.itemCondition || null,
            suggestedSubinventory: entity.suggestedSubinventory || null,
            deliveredSubinventory: entity.deliveredSubinventory || null,
            suggestedLocatorId: entity.suggestedLocatorId || null,
            deliveredLocatorId: entity.deliveredLocatorId || null,
            personId: entity.personId || null,
            status: entity.status || null,
            errorMessage: entity.errorMessage || null,
            attributeCategory: entity.attributeCategory || null,
            attribute1: entity.attribute1 || null,
            attribute2: entity.attribute2 || null,
            attribute3: entity.attribute3 || null,
            attribute4: entity.attribute4 || null,
            attribute5: entity.attribute5 || null,
            attribute6: entity.attribute6 || null,
            attribute7: entity.attribute7 || null,
            attribute8: entity.attribute8 || null,
            attribute9: entity.attribute9 || null,
            attribute10: entity.attribute10 || null,
            attribute11: entity.attribute11 || null,
            attribute12: entity.attribute12 || null,
            attribute13: entity.attribute13 || null,
            attribute14: entity.attribute14 || null,
            attribute15: entity.attribute15 || null,
            lastUpdatedBy: entity.lastUpdatedBy || null,
            createdBy: entity.createdBy || null,
            lastUpdateLogin: entity.lastUpdateLogin || null,
            orgId: entity.orgId || null,
            orgCode: entity.orgCode || null,
            orderHeaderId: entity.orderHeaderId || null,
            orderlineId: entity.orderlineId || null,
            parentTransactionId: entity.parentTransactionId || null,
            primaryUnitOfMeasurement: entity.primaryUnitOfMeasurement || null,
            rma_id: { dir: oracledb.BIND_OUT, type: oracledb.NUMBER },
          },
          { autoCommit: true }
        );

        // Add generated ID to entity
        const insertedEntity = {
          ...entity,
          rmaId: result.outBinds?.rma_id,
        };
        insertedEntities.push(insertedEntity);
      }

      logger.info("db", "RMA confirmations inserted successfully", {
        count: insertedEntities.length,
      });

      return insertedEntities;
    }, "saveAll-RmaConfirmations");
  }

  /**
   * Get all RMA confirmations
   * FOLLOWS findAll() pattern from moConfirmRepo.ts
   * Maps Spring Boot JpaRepository.findAll() method
   */
  async findAll(): Promise<RmaConfirmEntity[]> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Retrieving all RMA confirmations");

      const result = await connection.execute<RmaConfirmEntity>(
        `SELECT 
          RMA_ID as rmaId,
          TRANSACTION_TYPE as transactionType,
          PARTY_ID as partyId,
          CUST_ACCOUNT_ID as custAccountId,
          BILL_TO_SITE_USE_ID as billToSitesUseId,
          SHIP_TO_SITE_USE_ID as shipToSitesUseId,
          INVENTORY_ORG_ID as inventoryOrgId,
          LINE_NUMBER as lineNumber,
          ITEM_ID as itemId,
          UOM_CODE as uomCode,
          ORDER_QUANTITY as orderQuantity,
          RECEIPT_NUMBER as receiptNumber,
          RETURN_QUANTITY as returnQuantity,
          DELIVERED_QUANTITY as deliveredQuantity,
          RECEIVED_QUANTITY as receivedQuantity,
          ITEM_CONDITION as itemCondition,
          SUGGESTED_SUBINVENTORY as suggestedSubinventory,
          DELIVERED_SUBINVENTORY as deliveredSubinventory,
          SUGGESTED_LOCATOR_ID as suggestedLocatorId,
          DELIVERED_LOCATOR_ID as deliveredLocatorId,
          PERSON_ID as personId,
          STATUS as status,
          ERROR_MESSAGE as errorMessage,
          ATTRIBUTE_CATEGORY as attributeCategory,
          ATTRIBUTE1 as attribute1, ATTRIBUTE2 as attribute2, ATTRIBUTE3 as attribute3,
          ATTRIBUTE4 as attribute4, ATTRIBUTE5 as attribute5, ATTRIBUTE6 as attribute6,
          ATTRIBUTE7 as attribute7, ATTRIBUTE8 as attribute8, ATTRIBUTE9 as attribute9,
          ATTRIBUTE10 as attribute10, ATTRIBUTE11 as attribute11, ATTRIBUTE12 as attribute12,
          ATTRIBUTE13 as attribute13, ATTRIBUTE14 as attribute14, ATTRIBUTE15 as attribute15,
          LAST_UPDATE_DATE as lastUpdateDate,
          LAST_UPDATED_BY as lastUpdatedBy,
          CREATION_DATE as creationDate,
          CREATED_BY as createdBy,
          LAST_UPDATE_LOGIN as lastUpdateLogin,
          ORG_ID as orgId,
          ORG_CODE as orgCode,
          ORDER_HEADER_ID as orderHeaderId,
          ORDER_LINE_ID as orderlineId,
          PARENT_TRANSACTION_ID as parentTransactionId,
          PRIMARY_UNIT_OF_MEASURE as primaryUnitOfMeasurement
        FROM XXGS_RMA_CONFIRMATIONS
        ORDER BY RMA_ID DESC`,
        {},
        { outFormat: oracledb.OUT_FORMAT_OBJECT }
      );

      logger.info("db", "RMA confirmations retrieved successfully", {
        count: result.rows?.length || 0,
      });

      return result.rows || [];
    }, "findAll-RmaConfirmations");
  }

  // =====================================================
  // DUPLICATE VALIDATION QUERIES
  // =====================================================

  /**
   * Check duplicate records for RMA_DELIVERY transactions
   * MIGRATES recordCountForDelivery() native query (9 parameters)
   * Returns count of existing matching records
   */
  async recordCountForDelivery(
    params: RmaDeliveryDuplicateCheck
  ): Promise<number> {
    const whereClause = `TRANSACTION_TYPE = :transactionType
      AND RECEIPT_NUMBER = :receiptNumber
      AND LINE_NUMBER = :lineNumber
      AND ORDER_HEADER_ID = :orderHeaderId
      AND ORDER_LINE_ID = :orderLineId
      AND ATTRIBUTE3 = :attribute3
      AND ATTRIBUTE10 = :attribute10
      AND ITEM_ID = :itemId
      AND STATUS = :status`;

    const binds = {
      transactionType: params.transactionType,
      receiptNumber: params.receiptNumber,
      lineNumber: params.lineNumber,
      orderHeaderId: params.orderHeaderId,
      orderLineId: params.orderLineId,
      attribute3: params.attribute3,
      attribute10: params.attribute10,
      itemId: params.itemId,
      status: params.status,
    };

    try {
      return await executeCountQuery(
        "XXGS_RMA_CONFIRMATIONS",
        whereClause,
        binds
      );
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";
      logger.error("db", "Error checking RMA delivery duplicates", {
        error: errorMessage,
        params,
      });
      throw new Error(`Failed to check delivery duplicates: ${errorMessage}`);
    }
  }

  /**
   * Check duplicate records for RMA_RECEIPT transactions
   * MIGRATES recordCountForReceipt() native query (7 parameters)
   * Returns count of existing matching records
   */
  async recordCountForReceipt(
    params: RmaReceiptDuplicateCheck
  ): Promise<number> {
    const whereClause = `TRANSACTION_TYPE = :transactionType
      AND LINE_NUMBER = :lineNumber
      AND ORDER_HEADER_ID = :orderHeaderId
      AND ORDER_LINE_ID = :orderLineId
      AND ATTRIBUTE3 = :attribute3
      AND ITEM_ID = :itemId
      AND STATUS = :status`;

    const binds = {
      transactionType: params.transactionType,
      lineNumber: params.lineNumber,
      orderHeaderId: params.orderHeaderId,
      orderLineId: params.orderLineId,
      attribute3: params.attribute3,
      itemId: params.itemId,
      status: params.status,
    };

    try {
      return await executeCountQuery(
        "XXGS_RMA_CONFIRMATIONS",
        whereClause,
        binds
      );
    } catch (error: unknown) {
      const errorMessage =
        error instanceof Error ? error.message : "Unknown error";
      logger.error("db", "Error checking RMA receipt duplicates", {
        error: errorMessage,
        params,
      });
      throw new Error(`Failed to check receipt duplicates: ${errorMessage}`);
    }
  }

  // =====================================================
  // ORACLE PACKAGE INTEGRATION
  // =====================================================

  /**
   * Validate location using Oracle package
   * IMPLEMENTS XXGS_MOB_UTIL_PKG.VALIDATE_LOC(p_subinv, p_loc, P_LOC_RESULT)
   * FOLLOWS Oracle package pattern from showroomRepo.ts
   */
  async validateLocation(
    subinventory: string,
    locator: string
  ): Promise<LocationValidationResponse> {
    return withDatabaseConnection(async (connection) => {
      logger.info("db", "Validating location via Oracle package", {
        subinventory,
        locator,
      });

      const result = await connection.execute<{
        result: oracledb.ResultSet<unknown>;
      }>(
        `BEGIN
           :result := XXGS_MOB_UTIL_PKG.VALIDATE_LOC(:p_subinv, :p_loc);
         END;`,
        {
          result: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
          p_subinv: subinventory,
          p_loc: locator,
        }
      );

      const cursor = result.outBinds?.result as oracledb.ResultSet<unknown>;
      const rows = await cursor.getRows();
      await cursor.close();

      logger.info("db", "Location validation completed", {
        subinventory,
        locator,
        resultCount: rows.length,
      });

      return { P_LOC_RESULT: rows };
    }, `validateLocation-${subinventory}-${locator}`);
  }
}
