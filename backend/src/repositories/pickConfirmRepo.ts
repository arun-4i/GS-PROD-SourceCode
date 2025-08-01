/**
 * PickConfirm Repository Layer
 * Pure migration from Spring Boot PickConfirmRO.java
 * Handles XXGS_PICK_CONFIRMATION_SERIAL table operations
 */

import { PickConfirmEntity } from "../entities/moConfirm.entity";
import { withDatabaseConnection } from "../utils/databaseWrapper";
import { executeCountQuery } from "../utils/oracleHelpers";

export class PickConfirmRepository {
  private readonly tableName = "XXGS_PICK_CONFIRMATION_SERIAL";
  private readonly sequenceName = "XXGS_PICK_CONF_SERIAL_ID_S";

  /**
   * Insert multiple pick confirmations (saveAll pattern)
   * Maps Spring Boot JpaRepository.saveAll() method
   * REFACTORED: Uses centralized database wrapper for connection management
   */
  async insertPickConfirmations(
    entities: PickConfirmEntity[]
  ): Promise<PickConfirmEntity[]> {
    if (!entities || entities.length === 0) {
      return [];
    }

    return withDatabaseConnection(async (connection) => {
      const insertSql = `
        INSERT INTO ${this.tableName} (
          SERIAL_ID, DELIVERY_DETAIL_ID, FROM_SERIAL_NUMBER, TO_SERIAL_NUMBER, QUANTITY,
          STATUS, ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4,
          ATTRIBUTE5, ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10,
          ATTRIBUTE11, ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15,
          LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN,
          TRANSACTION_TYPE
        ) VALUES (
          ${this.sequenceName}.NEXTVAL, :deliveryDetailId, :fromSerialNumber, :toserialNumber, :quantity,
          :status, :attributeCategory, :attribute1, :attribute2, :attribute3, :attribute4,
          :attribute5, :attribute6, :attribute7, :attribute8, :attribute9, :attribute10,
          :attribute11, :attribute12, :attribute13, :attribute14, :attribute15,
          :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastUpdateLogin,
          :transactionType
        )
      `;

      const results: PickConfirmEntity[] = [];

      for (const entity of entities) {
        const binds = {
          deliveryDetailId: entity.deliveryDetailId,
          fromSerialNumber: entity.fromSerialNumber,
          toserialNumber: entity.toserialNumber, // Note: matches Java typo
          quantity: entity.quantity,
          status: entity.status,
          attributeCategory: entity.attributeCategory,
          attribute1: entity.attribute1,
          attribute2: entity.attribute2,
          attribute3: entity.attribute3,
          attribute4: entity.attribute4,
          attribute5: entity.attribute5,
          attribute6: entity.attribute6,
          attribute7: entity.attribute7,
          attribute8: entity.attribute8,
          attribute9: entity.attribute9,
          attribute10: entity.attribute10,
          attribute11: entity.attribute11,
          attribute12: entity.attribute12,
          attribute13: entity.attribute13,
          attribute14: entity.attribute14,
          attribute15: entity.attribute15,
          lastUpdateDate: entity.lastUpdateDate || new Date(),
          lastUpdatedBy: entity.lastUpdatedBy,
          creationDate: entity.creationDate || new Date(),
          createdBy: entity.createdBy,
          lastUpdateLogin: entity.lastUpdateLogin,
          transactionType: entity.transactionType,
        };

        await connection.execute(insertSql, binds);
        results.push(entity);
      }

      return results;
    }, "insertPickConfirmations");
  }

  /**
   * Find all pick confirmations (findAll pattern)
   * Maps Spring Boot JpaRepository.findAll() method
   * REFACTORED: Uses centralized database wrapper for connection management
   */
  async findAllPickConfirmations(): Promise<PickConfirmEntity[]> {
    return withDatabaseConnection(async (connection) => {
      const sql = `SELECT * FROM ${this.tableName} ORDER BY SERIAL_ID`;
      const result = await connection.execute(sql, [], {
        outFormat: require("oracledb").OUT_FORMAT_OBJECT,
      });
      return result.rows as PickConfirmEntity[];
    }, "findAllPickConfirmations");
  }

  /**
   * Check duplicate pick record count (Out Bound Picking - 6-field)
   * Maps PickConfirmRO.recordCountPickConfirm() method
   * REFACTORED: Uses centralized count query helper
   */
  async checkDuplicatePickRecord(
    delDetailId: string,
    fromSerialNum: string,
    att1: string,
    att2: string,
    att3: string,
    att4: string
  ): Promise<number> {
    const whereClause = `DELIVERY_DETAIL_ID = :delDetailId 
      AND FROM_SERIAL_NUMBER = :fromSerialNum 
      AND ATTRIBUTE1 = :att1 
      AND ATTRIBUTE2 = :att2 
      AND ATTRIBUTE3 = :att3 
      AND ATTRIBUTE4 = :att4`;

    const binds = {
      delDetailId,
      fromSerialNum,
      att1,
      att2,
      att3,
      att4,
    };

    return executeCountQuery(this.tableName, whereClause, binds);
  }

  /**
   * Check delivery record count (RMA_DELIVERY - 4-field)
   * Maps PickConfirmRO.recordCountDelivery() method
   * REFACTORED: Uses centralized count query helper
   */
  async checkDeliveryRecord(
    fromSerialNum: string,
    att2: string,
    att3: string,
    transactionType: string
  ): Promise<number> {
    const whereClause = `FROM_SERIAL_NUMBER = :fromSerialNum 
      AND ATTRIBUTE2 = :att2 
      AND ATTRIBUTE3 = :att3 
      AND TRANSACTION_TYPE = :transactionType`;

    const binds = {
      fromSerialNum,
      att2,
      att3,
      transactionType,
    };

    return executeCountQuery(this.tableName, whereClause, binds);
  }

  /**
   * Get pick match count by delivery detail ID
   * Maps PickConfirmRO.poMatchCount() method
   * REFACTORED: Uses centralized count query helper
   */
  async getPickMatchCount(delDetailId: string): Promise<number> {
    const whereClause = `DELIVERY_DETAIL_ID = :delDetailId`;
    const binds = { delDetailId };
    return executeCountQuery(this.tableName, whereClause, binds);
  }
}
