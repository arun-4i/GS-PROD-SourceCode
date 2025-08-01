import { UserRespGroupEntity } from "../entities/userRespGroup.entity";
import { withDatabaseConnection } from "../utils/databaseWrapper";

export class UserRespGroupRepository {
  private readonly tableName = "XXGS_WMS_USER_RESP_GROUPS";

  // Get all user responsible groups
  // REFACTORED: Uses centralized database wrapper
  async getAllUserRepos(): Promise<UserRespGroupEntity[]> {
    return withDatabaseConnection(async (connection) => {
      const sql = `SELECT * FROM ${this.tableName} ORDER BY USER_RESP_GROUP_ID`;
      const result = await connection.execute(sql, [], {
        outFormat: require("oracledb").OUT_FORMAT_OBJECT,
      });
      return result.rows as UserRespGroupEntity[];
    }, "getAllUserRepos");
  }

  // Insert user responsible group (single insert)
  // REFACTORED: Uses centralized database wrapper
  async insertUserRepo(
    group: UserRespGroupEntity
  ): Promise<UserRespGroupEntity> {
    return withDatabaseConnection(async (connection) => {
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
      await connection.execute(sql, binds, { autoCommit: true });
      // Fetch and return the inserted group (by userId and responsibilityId)
      const selectSql = `SELECT * FROM ${this.tableName} WHERE USER_ID = :userId AND RESPONSIBILITY_ID = :responsibilityId ORDER BY USER_RESP_GROUP_ID DESC`;
      const result = await connection.execute(
        selectSql,
        {
          userId: group.userId,
          responsibilityId: group.responsibilityId,
        },
        {
          outFormat: require("oracledb").OUT_FORMAT_OBJECT,
        }
      );
      if (!result.rows || result.rows.length === 0) {
        throw new Error("UserRespGroup insert failed");
      }
      return result.rows[0] as UserRespGroupEntity;
    }, "insertUserRepo");
  }

  // Get user repository name
  // REFACTORED: Uses centralized database wrapper
  async getUserRepoName(params: Record<string, unknown>): Promise<unknown> {
    return withDatabaseConnection(async (connection) => {
      const responsibilityName = params.RESPONSIBILITY_NAME || "";
      const sql = `
        SELECT * FROM XXGS_WMS_RESPONSIBILITY_V 
        WHERE (UPPER(RESPONSIBILITY_NAME) LIKE '%' || UPPER(:pRESPONSIBILITYNAME) || '%' OR :pRESPONSIBILITYNAME IS NULL)
      `;
      const binds = {
        pRESPONSIBILITYNAME: responsibilityName,
      };
      const result = await connection.execute(sql, binds, {
        outFormat: require("oracledb").OUT_FORMAT_OBJECT,
      });
      return result.rows;
    }, "getUserRepoName");
  }

  // Get user repository access
  // REFACTORED: Uses centralized database wrapper
  async getUserRepoAccess(params: Record<string, unknown>): Promise<unknown> {
    return withDatabaseConnection(async (connection) => {
      const userId = params.USER_ID || "";
      const sql = `SELECT * FROM XXGS_WMS_USER_RESP_ACCESS_V WHERE USER_ID = :pUSER_ID`;
      const binds = {
        pUSER_ID: userId,
      };
      const result = await connection.execute(sql, binds, {
        outFormat: require("oracledb").OUT_FORMAT_OBJECT,
      });
      return result.rows;
    }, "getUserRepoAccess");
  }

  // Get user rep
  // REFACTORED: Uses centralized database wrapper
  async getUserRep(params: Record<string, unknown>): Promise<unknown> {
    return withDatabaseConnection(async (connection) => {
      const userId = params.USER_ID || "";
      const sql = `SELECT * FROM XXGS_WMS_ALL_USER_RESP_V WHERE USER_ID = :pUSER_ID`;
      const binds = {
        pUSER_ID: userId,
      };
      const result = await connection.execute(sql, binds, {
        outFormat: require("oracledb").OUT_FORMAT_OBJECT,
      });
      return result.rows;
    }, "getUserRep");
  }

  // Get organization
  // REFACTORED: Uses centralized database wrapper
  async getOrg(): Promise<unknown> {
    return withDatabaseConnection(async (connection) => {
      const sql = `SELECT * FROM XXGS_INV_ORGANIZATIONS_V`;
      const result = await connection.execute(sql, [], {
        outFormat: require("oracledb").OUT_FORMAT_OBJECT,
      });
      return result.rows;
    }, "getOrg");
  }

  // Get user rep active
  // REFACTORED: Uses centralized database wrapper
  async getUserRepActive(): Promise<unknown> {
    return withDatabaseConnection(async (connection) => {
      const sql = `SELECT DISTINCT USER_ID, USER_NAME FROM XXGS_WMS_ALL_USER_RESP_V`;
      const result = await connection.execute(sql, [], {
        outFormat: require("oracledb").OUT_FORMAT_OBJECT,
      });
      return result.rows;
    }, "getUserRepActive");
  }
}

export const userRespGroupRepo = new UserRespGroupRepository();
