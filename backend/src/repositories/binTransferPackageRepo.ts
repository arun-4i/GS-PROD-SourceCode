import { IoConLotEntity } from "../entities/ioConLot.entity";
import { InvCountConfirmEntity } from "../entities/invCountConfirm.entity";
import { OracleConnection } from "../config/database";
import { OraclePackageService } from "../services/oraclePackageService";

export class BinTransferPackageRepository {
  private readonly ioConLotTableName = "XXGS_CONF_LOT_DETAILS";
  private readonly invCountConfirmTableName = "xxgs_inv_count_confirmations";
  private readonly oraclePackageService: OraclePackageService;

  constructor() {
    this.oraclePackageService = new OraclePackageService();
  }

  // Oracle Package Calls
  async getItemDetail(params: Record<string, unknown>): Promise<unknown> {
    const packageParams = {
      P_ORGANIZATION_CODE: params.P_ORGANIZATION_CODE || "",
      P_DEPARTMENT: params.P_DEPARTMENT || "",
      P_SUB_INV_CODE: params.P_SUB_INV_CODE || "",
      P_WITH_LOT_FLAG: params.P_WITH_LOT_FLAG || "",
    };

    const result = await this.oraclePackageService.callPackage(
      "BinTransferPackage.GET_ITEM_DETAILS",
      packageParams
    );
    return result.data;
  }

  async getItemCrossref(params: Record<string, unknown>): Promise<unknown> {
    const packageParams = {
      P_ORGANIZATION_CODE: params.P_ORGANIZATION_CODE || "",
      P_DEPARTMENT: params.P_DEPARTMENT || "",
      P_SUB_INV_CODE: params.P_SUB_INV_CODE || "",
    };

    const result = await this.oraclePackageService.callPackage(
      "BinTransferPackage.GET_ITEM_CROSSREF_DTLS",
      packageParams
    );
    return result.data;
  }

  // IOConLot Operations
  async getAllIOConLot(): Promise<IoConLotEntity[]> {
    const sql = `SELECT * FROM ${this.ioConLotTableName} ORDER BY LOT_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as IoConLotEntity[];
  }

  async insertIOConLot(entity: IoConLotEntity): Promise<IoConLotEntity> {
    const sql = `
      INSERT INTO ${this.ioConLotTableName} (
        LOT_ID, TRANSACTION_TYPE, LOT_NUMBER, LOT_EXPIRY_DATE, LOT_QUANTITY,
        ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5,
        ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10, ATTRIBUTE11,
        ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15,
        LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN, TRANSACTION_TYPE_ID
      ) VALUES (
        XXGS_LOT_ID_S.NEXTVAL, :transactionType, :lotNumber, :lotExpiryDate, :lotQuantity,
        :attributeCategory, :attribute1, :attribute2, :attribute3, :attribute4, :attribute5,
        :attribute6, :attribute7, :attribute8, :attribute9, :attribute10, :attribute11,
        :attribute12, :attribute13, :attribute14, :attribute15,
        :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastUpdateLogin, :transactionTypeId
      )
    `;

    const binds = {
      transactionType: entity.transactionType,
      lotNumber: entity.lotNumber,
      lotExpiryDate: entity.lotExpiryDate,
      lotQuantity: entity.lotQuantity,
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
      lastUpdateDate: entity.lastUpdateDate,
      lastUpdatedBy: entity.lastUpdatedBy,
      creationDate: entity.creationDate,
      createdBy: entity.createdBy,
      lastUpdateLogin: entity.lastUpdateLogin,
      transactionTypeId: entity.transactionTypeId,
    };

    await OracleConnection.executeQuery(sql, binds, { autoCommit: true });

    // Fetch and return the inserted entity
    const selectSql = `SELECT * FROM ${this.ioConLotTableName} WHERE TRANSACTION_TYPE = :transactionType AND LOT_NUMBER = :lotNumber`;
    const result = await OracleConnection.executeQuery(selectSql, {
      transactionType: entity.transactionType,
      lotNumber: entity.lotNumber,
    });

    if (!result.rows || result.rows.length === 0) {
      throw new Error("IOConLot insert failed");
    }
    return result.rows[0] as IoConLotEntity;
  }

  // InvCountConfirm Operations
  async getAllInvCountConfirm(): Promise<InvCountConfirmEntity[]> {
    const sql = `SELECT * FROM ${this.invCountConfirmTableName} ORDER BY INV_COUNT_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as InvCountConfirmEntity[];
  }

  async insertInvCountConfirm(
    entity: InvCountConfirmEntity
  ): Promise<InvCountConfirmEntity> {
    const sql = `
      INSERT INTO ${this.invCountConfirmTableName} (
        INV_COUNT_ID, PHYSICAL_INV_NAME, PHYSICAL_INV_ID, SUB_INVENTORY, LOCATORS, LOCATORS_ID,
        TAG_NUMBER, TAG_ID, ITEM_CODE, ITEM_ID, ORGANIZATION_ID, ORGANIZATION_CODE, STATUS,
        UOM, LOT_NUMBER, LOT_EXPIRY, CURRENT_ON_HAND, COUNT_QTY, APPROVED_VARIANCE,
        ON_HAND_VARIANCE, ADJUST_VALUE, ERROR_MSG, PARENT_FLAG,
        ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5,
        ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10, ATTRIBUTE11,
        ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15,
        LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN
      ) VALUES (
        XXGS_INV_COUNT_ID_S.NEXTVAL, :physicalInvName, :physicalInvId, :subInventory, :locators, :locatorsId,
        :tagNumber, :tagId, :itemCode, :itemId, :organizationId, :organizationCode, :status,
        :uom, :lotNumber, :lotExpires, :currentOnHand, :countQty, :approvedVariance,
        :onHandVariance, :adjustValue, :errorMsg, :parentFlag,
        :attributeCategory, :attribute1, :attribute2, :attribute3, :attribute4, :attribute5,
        :attribute6, :attribute7, :attribute8, :attribute9, :attribute10, :attribute11,
        :attribute12, :attribute13, :attribute14, :attribute15,
        :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastUpdateLogin
      )
    `;

    const binds = {
      physicalInvName: entity.physicalInvName,
      physicalInvId: entity.physicalInvId,
      subInventory: entity.subInventory,
      locators: entity.locators,
      locatorsId: entity.locatorsId,
      tagNumber: entity.tagNumber,
      tagId: entity.tagId,
      itemCode: entity.itemCode,
      itemId: entity.itemId,
      organizationId: entity.organizationId,
      organizationCode: entity.organizationCode,
      status: entity.status,
      uom: entity.uom,
      lotNumber: entity.lotNumber,
      lotExpires: entity.lotExpires,
      currentOnHand: entity.currentOnHand,
      countQty: entity.countQty,
      approvedVariance: entity.approvedVariance,
      onHandVariance: entity.onHandVariance,
      adjustValue: entity.adjustValue,
      errorMsg: entity.errorMsg,
      parentFlag: entity.parentFlag,
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
      lastUpdateDate: entity.lastUpdateDate,
      lastUpdatedBy: entity.lastUpdatedBy,
      creationDate: entity.creationDate,
      createdBy: entity.createdBy,
      lastUpdateLogin: entity.lastUpdateLogin,
    };

    await OracleConnection.executeQuery(sql, binds, { autoCommit: true });

    // Fetch and return the inserted entity
    const selectSql = `SELECT * FROM ${this.invCountConfirmTableName} WHERE ITEM_CODE = :itemCode AND ORGANIZATION_CODE = :organizationCode`;
    const result = await OracleConnection.executeQuery(selectSql, {
      itemCode: entity.itemCode,
      organizationCode: entity.organizationCode,
    });

    if (!result.rows || result.rows.length === 0) {
      throw new Error("InvCountConfirm insert failed");
    }
    return result.rows[0] as InvCountConfirmEntity;
  }
}

export const binTransferPackageRepo = new BinTransferPackageRepository();
