import { UserRespGroupEntity } from "../entities/userRespGroup.entity";
import { OracleConnection } from "../config/database";

export class UserRespGroupRepository {
  private readonly tableName = "XXGS_WMS_USER_RESP_GROUPS";

  // Get all user responsible groups
  async getAllUserRepos(): Promise<UserRespGroupEntity[]> {
    const sql = `SELECT * FROM ${this.tableName} ORDER BY USER_RESP_GROUP_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as UserRespGroupEntity[];
  }

  // Insert user responsible group (single insert)
  async insertUserRepo(
    group: UserRespGroupEntity
  ): Promise<UserRespGroupEntity> {
    const sql = `
      INSERT INTO ${this.tableName} (
        USER_RESP_GROUP_ID, USER_ID, RESPONSIBILITY_ID, DESCRIPTION, START_DATE, END_DATE, ATTRIBUTE_CATEGORY,
        ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5, ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10,
        ATTRIBUTE11, ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15, LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN
      ) VALUES (
        XXGS_USER_RESP_GROUP_ID_S.NEXTVAL, :userId, :responsibilityId, :description, :startDate, :endDate, :attributeCategory,
        :attribute1, :attribute2, :attribute3, :attribute4, :attribute5, :attribute6, :attribute7, :attribute8, :attribute9, :attribute10,
        :attribute11, :attribute12, :attribute13, :attribute14, :attribute15, :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastupdatelogin
      )
    `;
    const binds = {
      userId: group.userId,
      responsibilityId: group.responsibilityId,
      description: group.description,
      startDate: group.startDate,
      endDate: group.endDate,
      attributeCategory: group.attributeCategory,
      attribute1: group.attribute1,
      attribute2: group.attribute2,
      attribute3: group.attribute3,
      attribute4: group.attribute4,
      attribute5: group.attribute5,
      attribute6: group.attribute6,
      attribute7: group.attribute7,
      attribute8: group.attribute8,
      attribute9: group.attribute9,
      attribute10: group.attribute10,
      attribute11: group.attribute11,
      attribute12: group.attribute12,
      attribute13: group.attribute13,
      attribute14: group.attribute14,
      attribute15: group.attribute15,
      lastUpdateDate: group.lastUpdateDate,
      lastUpdatedBy: group.lastUpdatedBy,
      creationDate: group.creationDate,
      createdBy: group.createdBy,
      lastupdatelogin: group.lastupdatelogin,
    };
    await OracleConnection.executeQuery(sql, binds, { autoCommit: true });
    // Fetch and return the inserted group (by userId and responsibilityId)
    const selectSql = `SELECT * FROM ${this.tableName} WHERE USER_ID = :userId AND RESPONSIBILITY_ID = :responsibilityId ORDER BY USER_RESP_GROUP_ID DESC`;
    const result = await OracleConnection.executeQuery(selectSql, {
      userId: group.userId,
      responsibilityId: group.responsibilityId,
    });
    if (!result.rows || result.rows.length === 0) {
      throw new Error("UserRespGroup insert failed");
    }
    return result.rows[0] as UserRespGroupEntity;
  }

  // Get user repository name
  async getUserRepoName(params: Record<string, unknown>): Promise<unknown> {
    const responsibilityName = params.RESPONSIBILITY_NAME || "";
    const sql = `
      SELECT * FROM XXGS_WMS_RESPONSIBILITY_V 
      WHERE (UPPER(RESPONSIBILITY_NAME) LIKE '%' || UPPER(:pRESPONSIBILITYNAME) || '%' OR :pRESPONSIBILITYNAME IS NULL)
    `;
    const binds = {
      pRESPONSIBILITYNAME: responsibilityName,
    };
    const result = await OracleConnection.executeQuery(sql, binds);
    return result.rows;
  }

  // Get user repository access
  async getUserRepoAccess(params: Record<string, unknown>): Promise<unknown> {
    const userId = params.USER_ID || "";
    const sql = `SELECT * FROM XXGS_WMS_USER_RESP_ACCESS_V WHERE USER_ID = :pUSER_ID`;
    const binds = {
      pUSER_ID: userId,
    };
    const result = await OracleConnection.executeQuery(sql, binds);
    return result.rows;
  }

  // Get user rep
  async getUserRep(params: Record<string, unknown>): Promise<unknown> {
    const userId = params.USER_ID || "";
    const sql = `SELECT * FROM XXGS_WMS_ALL_USER_RESP_V WHERE USER_ID = :pUSER_ID`;
    const binds = {
      pUSER_ID: userId,
    };
    const result = await OracleConnection.executeQuery(sql, binds);
    return result.rows;
  }

  // Get organization
  async getOrg(): Promise<unknown> {
    const sql = `SELECT * FROM XXGS_INV_ORGANIZATIONS_V`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows;
  }

  // Get user rep active
  async getUserRepActive(): Promise<unknown> {
    const sql = `SELECT DISTINCT USER_ID, USER_NAME FROM XXGS_WMS_ALL_USER_RESP_V`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows;
  }
}

export const userRespGroupRepo = new UserRespGroupRepository();
