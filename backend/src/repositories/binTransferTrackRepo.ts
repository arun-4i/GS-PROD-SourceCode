import { BinTransferTrackHdrEntity } from "../entities/binTransferTrackHdr.entity";
import { BinTransferTrackPickEntity } from "../entities/binTransferTrackPick.entity";
import { BinTransferTrackDropEntity } from "../entities/binTransferTrackDrop.entity";
import { QuickDropEntity } from "../entities/quickDrop.entity";
import { OracleConnection } from "../config/database";
import { OraclePackageService } from "../services/oraclePackageService";

export class BinTransferTrackRepository {
  private readonly hdrTableName = "XXGS_BIN_TRNS_HEADER";
  private readonly pickTableName = "XXGS_BIN_TRNS_PICK";
  private readonly dropTableName = "XXGS_BIN_TRNS_DROP";
  private readonly oraclePackageService: OraclePackageService;

  constructor() {
    this.oraclePackageService = new OraclePackageService();
  }

  // BinTransferTrack Header Operations
  async getAllBinTransferHdr(): Promise<BinTransferTrackHdrEntity[]> {
    const sql = `SELECT * FROM ${this.hdrTableName} ORDER BY HEADER_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as BinTransferTrackHdrEntity[];
  }

  async insertBinTransferHdr(
    entity: BinTransferTrackHdrEntity
  ): Promise<BinTransferTrackHdrEntity> {
    const sql = `
      INSERT INTO ${this.hdrTableName} (
        HEADER_ID, REF, INV_ORG, INV_ORG_ID, SUB_INV_NAME,
        ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5,
        ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10,
        LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN
      ) VALUES (
        xxgs_bin_trns_header_id_s.NEXTVAL, :ref, :invOrg, :invOrgId, :subInvName,
        :attributeCategory, :attribute1, :attribute2, :attribute3, :attribute4, :attribute5,
        :attribute6, :attribute7, :attribute8, :attribute9, :attribute10,
        :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastUpdateLogin
      )
    `;

    const binds = {
      ref: entity.ref,
      invOrg: entity.invOrg,
      invOrgId: entity.invOrgId,
      subInvName: entity.subInvName,
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
      lastUpdateDate: entity.lastUpdateDate,
      lastUpdatedBy: entity.lastUpdatedBy,
      creationDate: entity.creationDate,
      createdBy: entity.createdBy,
      lastUpdateLogin: entity.lastUpdateLogin,
    };

    await OracleConnection.executeQuery(sql, binds, { autoCommit: true });

    // Fetch and return the inserted entity
    const selectSql = `SELECT * FROM ${this.hdrTableName} WHERE REF = :ref ORDER BY HEADER_ID DESC`;
    const result = await OracleConnection.executeQuery(selectSql, {
      ref: entity.ref,
    });

    if (!result.rows || result.rows.length === 0) {
      throw new Error("BinTransferTrackHdr insert failed");
    }
    return result.rows[0] as BinTransferTrackHdrEntity;
  }

  // BinTransferTrack Pick Operations
  async getAllBinTransferPick(): Promise<BinTransferTrackPickEntity[]> {
    const sql = `SELECT * FROM ${this.pickTableName} ORDER BY LINE_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as BinTransferTrackPickEntity[];
  }

  async insertBinTransferPick(
    entity: BinTransferTrackPickEntity
  ): Promise<BinTransferTrackPickEntity> {
    const sql = `
      INSERT INTO ${this.pickTableName} (
        LINE_ID, HEADER_ID, INV_ORG, INV_ORG_ID, INVENTORY_ITEM_ID, ITEM_CODE, DESCRIPTION,
        UOM, PICKED_QTY, PICKED_SUBINV, PICKED_LOCATOR, PICKED_LOCATOR_ID, TYPE,
        ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5,
        ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10,
        LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN
      ) VALUES (
        xxgs_bin_trns_pick_line_id_s.NEXTVAL, :headerId, :invOrg, :invOrgId, :inventoryItemId, :itemCode, :description,
        :uom, :pickedQty, :pickedSubinv, :pickedLocator, :pickedlocatorId, :type,
        :attributeCategory, :attribute1, :attribute2, :attribute3, :attribute4, :attribute5,
        :attribute6, :attribute7, :attribute8, :attribute9, :attribute10,
        :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastUpdateLogin
      )
    `;

    const binds = {
      headerId: entity.headerId,
      invOrg: entity.invOrg,
      invOrgId: entity.invOrgId,
      inventoryItemId: entity.inventoryItemId,
      itemCode: entity.itemCode,
      description: entity.description,
      uom: entity.uom,
      pickedQty: entity.pickedQty,
      pickedSubinv: entity.pickedSubinv,
      pickedLocator: entity.pickedLocator,
      pickedlocatorId: entity.pickedlocatorId,
      type: entity.type,
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
      lastUpdateDate: entity.lastUpdateDate,
      lastUpdatedBy: entity.lastUpdatedBy,
      creationDate: entity.creationDate,
      createdBy: entity.createdBy,
      lastUpdateLogin: entity.lastUpdateLogin,
    };

    await OracleConnection.executeQuery(sql, binds, { autoCommit: true });

    // Fetch and return the inserted entity
    const selectSql = `SELECT * FROM ${this.pickTableName} WHERE HEADER_ID = :headerId AND ITEM_CODE = :itemCode ORDER BY LINE_ID DESC`;
    const result = await OracleConnection.executeQuery(selectSql, {
      headerId: entity.headerId,
      itemCode: entity.itemCode,
    });

    if (!result.rows || result.rows.length === 0) {
      throw new Error("BinTransferTrackPick insert failed");
    }
    return result.rows[0] as BinTransferTrackPickEntity;
  }

  // BinTransferTrack Drop Operations
  async getAllBinTransferDrop(): Promise<BinTransferTrackDropEntity[]> {
    const sql = `SELECT * FROM ${this.dropTableName} ORDER BY DROP_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as BinTransferTrackDropEntity[];
  }

  async insertBinTransferDrop(
    entity: BinTransferTrackDropEntity
  ): Promise<BinTransferTrackDropEntity> {
    const sql = `
      INSERT INTO ${this.dropTableName} (
        DROP_ID, LINE_ID, INVENTORY_ITEM_ID, ITEM_CODE, DESCRIPTION, UOM, DROP_QTY,
        DROP_SUBINV, DROP_LOCATOR, DROP_LOCATOR_ID, TYPE,
        ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5,
        ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10,
        LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN
      ) VALUES (
        xxgs_bin_trns_drop_id_s.NEXTVAL, :lineId, :inventoryItemId, :itemCode, :description, :uom, :dropQty,
        :dropSubinv, :dropLocator, :dropLocatorId, :type,
        :attributeCategory, :attribute1, :attribute2, :attribute3, :attribute4, :attribute5,
        :attribute6, :attribute7, :attribute8, :attribute9, :attribute10,
        :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastUpdateLogin
      )
    `;

    const binds = {
      lineId: entity.lineId,
      inventoryItemId: entity.inventoryItemId,
      itemCode: entity.itemCode,
      description: entity.description,
      uom: entity.uom,
      dropQty: entity.dropQty,
      dropSubinv: entity.dropSubinv,
      dropLocator: entity.dropLocator,
      dropLocatorId: entity.dropLocatorId,
      type: entity.type,
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
      lastUpdateDate: entity.lastUpdateDate,
      lastUpdatedBy: entity.lastUpdatedBy,
      creationDate: entity.creationDate,
      createdBy: entity.createdBy,
      lastUpdateLogin: entity.lastUpdateLogin,
    };

    await OracleConnection.executeQuery(sql, binds, { autoCommit: true });

    // Fetch and return the inserted entity
    const selectSql = `SELECT * FROM ${this.dropTableName} WHERE LINE_ID = :lineId AND ITEM_CODE = :itemCode ORDER BY DROP_ID DESC`;
    const result = await OracleConnection.executeQuery(selectSql, {
      lineId: entity.lineId,
      itemCode: entity.itemCode,
    });

    if (!result.rows || result.rows.length === 0) {
      throw new Error("BinTransferTrackDrop insert failed");
    }
    return result.rows[0] as BinTransferTrackDropEntity;
  }

  // Oracle Package Operations
  async getBinTransRefNo(): Promise<unknown> {
    const result = await this.oraclePackageService.callPackage(
      "BinTranferTrackPkg.GET_BIN_TRANS_REF_NO"
    );
    return result.data;
  }

  async getBinTransDropHdr(params: Record<string, unknown>): Promise<unknown> {
    const packageParams = {
      P_INV_ORG: params.P_INV_ORG || "",
      P_SUB_INV: params.P_SUB_INV || "",
    };

    const result = await this.oraclePackageService.callPackage(
      "BinTransferPkg.GET_BIN_TRNS_DROP_HDR",
      packageParams
    );
    return result.data;
  }

  async getBinTransDropDtls(params: Record<string, unknown>): Promise<unknown> {
    const packageParams = {
      p_inv_org_id: params.p_inv_org_id || "",
      p_sub_code: params.p_sub_code || "",
    };

    const result = await this.oraclePackageService.callPackage(
      "BinTransferPkg.GET_BIN_TRNS_DROP_DTLS",
      packageParams
    );
    return result.data;
  }

  async getBinNumber(params: Record<string, unknown>): Promise<unknown> {
    const packageParams = {
      p_inv_org: params.p_inv_org || "",
      p_sub_inv: params.p_sub_inv || "",
    };

    const result = await this.oraclePackageService.callPackage(
      "BinTransferPkg.getBinNumber",
      packageParams
    );
    return result.data;
  }

  // Quick Drop Operations
  async insertQuickDrop(entity: QuickDropEntity): Promise<unknown> {
    // QuickDrop is a composite operation that involves multiple tables
    // For now, return the entity as-is since it's not a direct table mapping
    // This would need to be implemented based on the specific business logic
    // from the Spring Boot QuickDropSO service
    return entity;
  }
}

export const binTransferTrackRepo = new BinTransferTrackRepository();
