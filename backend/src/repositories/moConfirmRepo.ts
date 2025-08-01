/**
 * MoConfirm Repository Layer
 * Pure migration from Spring Boot MoConfirmRO.java
 * Handles XXGS_MO_CONFIRMATIONS table operations and Oracle package calls
 */

import {
  MoConfirmEntity,
  OraclePackageResponse,
} from "../entities/moConfirm.entity";
import { OracleConnection } from "../config/database";
import { OraclePackageService } from "../services/oraclePackageService";

export class MoConfirmRepository {
  private readonly tableName = "XXGS_MO_CONFIRMATIONS";
  private readonly sequenceName = "XXGS_MO_ID_S";
  private readonly oraclePackageService: OraclePackageService;

  constructor() {
    this.oraclePackageService = new OraclePackageService();
  }

  /**
   * Insert multiple MO confirmations (saveAll pattern)
   * Maps Spring Boot JpaRepository.saveAll() method
   */
  async insertMoConfirmations(
    entities: MoConfirmEntity[]
  ): Promise<MoConfirmEntity[]> {
    if (!entities || entities.length === 0) {
      return [];
    }

    const insertSql = `
      INSERT INTO ${this.tableName} (
        MO_ID, TRANSACTION_TYPE, MO_NUMBER, MO_LINE_NUMBER, PICKSLIP_NUMBER,
        ITEM_ID, UOM_CODE, REQUIRED_QUANTITY, PICKED_QUANTITY, TRANSFER_QUANTITY,
        SOURCE_SUB_INVENTORY, DESTINATION_SUB_INVENTORY, SOURCE_LOCATOR_ID, DESINATION_LOCATOR_ID,
        PERSON_ID, STATUS, ERROR_MESSAGE, ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2,
        ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5, ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8,
        ATTRIBUTE9, ATTRIBUTE10, ATTRIBUTE11, ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14,
        ATTRIBUTE15, LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY,
        LAST_UPDATE_LOGIN, ORG_ID, ORG_CODE, ITEM_CODE, ORDER_NUMBER,
        DELIVERY_DETAIL_ID, CUSTOMER_NAME, CUST_ACCOUNT_ID
      ) VALUES (
        ${this.sequenceName}.NEXTVAL, :transactionType, :moNumber, :moLineNumber, :pickSlipNumber,
        :itemId, :uomCode, :requiredQuantity, :pickedQuantity, :transferQuantity,
        :sourceSubInventory, :destinationSubInventory, :sourceLocationId, :destinationLocationId,
        :personId, :status, :errorMessage, :attributeCategory, :attribute1, :attribute2,
        :attribute3, :attribute4, :attribute5, :attribute6, :attribute7, :attribute8,
        :attribute9, :attribute10, :attribute11, :attribute12, :attribute13, :attribute14,
        :attribute15, :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy,
        :lastUpdateLogin, :orgId, :orgCode, :itemCode, :orderNumber,
        :deliveryDetailId, :customerName, :customerAccountId
      )
    `;

    const results: MoConfirmEntity[] = [];

    for (const entity of entities) {
      const binds = {
        transactionType: entity.transactionType,
        moNumber: entity.moNumber,
        moLineNumber: entity.moLineNumber,
        pickSlipNumber: entity.pickSlipNumber,
        itemId: entity.itemId,
        uomCode: entity.uomCode,
        requiredQuantity: entity.requiredQuantity,
        pickedQuantity: entity.pickedQuantity,
        transferQuantity: entity.transferQuantity,
        sourceSubInventory: entity.sourceSubInventory,
        destinationSubInventory: entity.destinationSubInventory,
        sourceLocationId: entity.sourceLocationId,
        destinationLocationId: entity.destinationLocationId,
        personId: entity.personId,
        status: entity.status,
        errorMessage: entity.errorMessage,
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
        orgId: entity.orgId,
        orgCode: entity.orgCode,
        itemCode: entity.itemCode,
        orderNumber: entity.orderNumber,
        deliveryDetailId: entity.deliveryDetailId,
        customerName: entity.customerName,
        customerAccountId: entity.customerAccountId,
      };

      await OracleConnection.executeQuery(insertSql, binds);
      results.push(entity);
    }

    return results;
  }

  /**
   * Find all MO confirmations (findAll pattern)
   * Maps Spring Boot JpaRepository.findAll() method
   */
  async findAllMoConfirmations(): Promise<MoConfirmEntity[]> {
    const sql = `SELECT * FROM ${this.tableName} ORDER BY MO_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as MoConfirmEntity[];
  }

  /**
   * Check duplicate record count (exact Spring Boot query)
   * Maps MoConfirmRO.recordCount() method with 6-field combination
   */
  async checkDuplicateRecord(
    sourceLocId: string,
    delDetailId: string,
    itemId: string,
    moNumber: string,
    moLineNumber: string,
    status: string
  ): Promise<number> {
    // Exact query from Spring Boot MoConfirmRO.java rawQuery
    const sql = `
      SELECT count(*) as COUNT 
      FROM XXGS_MO_CONFIRMATIONS 
      WHERE SOURCE_LOCATOR_ID = :sourceLocId 
        AND DELIVERY_DETAIL_ID = :delDetailId 
        AND ITEM_ID = :itemId 
        AND MO_NUMBER = :moNumber 
        AND MO_LINE_NUMBER = :moLineNumber 
        AND STATUS = :status
    `;

    const binds = {
      sourceLocId,
      delDetailId,
      itemId,
      moNumber,
      moLineNumber,
      status,
    };

    const result = await OracleConnection.executeQuery(sql, binds);
    return result.rows?.[0]?.COUNT || 0;
  }

  /**
   * Process Quick Pick JSON via Oracle package
   * Maps MoConfirmSO.insertJsonPost() → MoveOrderPackage.insertQuickPickJson()
   * Calls XXGS_MOB_UTIL_PKG.MAIN(jsonData, P_MES, P_MES2)
   */
  async processQuickPickJson(jsonData: string): Promise<OraclePackageResponse> {
    try {
      const result =
        await this.oraclePackageService.callPackage<OraclePackageResponse>(
          "XXGS_MOB_UTIL_PKG.MAIN",
          {
            // Input parameter
            P_JSON_DATA: {
              val: jsonData,
              type: "STRING",
              dir: "IN",
            },
            // Output parameters (REF_CURSOR types)
            P_MES: {
              type: "CURSOR",
              dir: "OUT",
            },
            P_MES2: {
              type: "CURSOR",
              dir: "OUT",
            },
          }
        );

      return {
        P_MES: result.outBinds?.P_MES,
        P_MES2: result.outBinds?.P_MES2,
      };
    } catch (error) {
      throw new Error(
        `Oracle package call failed: ${error instanceof Error ? error.message : "Unknown error"}`
      );
    }
  }
}
