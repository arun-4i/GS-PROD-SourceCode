import { UserEntity } from "../entities/user.entity";
import { OracleConnection } from "../config/database";

export class UserRepository {
  private readonly tableName = "XXGS_WMS_USER";

  // Get all users
  async getAllUsers(): Promise<UserEntity[]> {
    const sql = `SELECT * FROM ${this.tableName} ORDER BY USER_ID`;
    const result = await OracleConnection.executeQuery(sql);
    return result.rows as UserEntity[];
  }

  // Insert user with direct SQL (no package)
  async insertUser(user: UserEntity): Promise<UserEntity> {
    // Prepare insert SQL and bind variables
    const sql = `
      INSERT INTO ${this.tableName} (
        USER_ID, USER_NAME, FULL_NAME, USER_PASSWORD, ENCRYPTED_USER_PASSWORD, START_DATE, END_DATE, EMAIL_ADDRESS, STATUS,
        PASSWORD_DATE, LAST_LOGON_DATE, ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5,
        ATTRIBUTE6, ATTRIBUTE7, ATTRIBUTE8, ATTRIBUTE9, ATTRIBUTE10, ATTRIBUTE11, ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15,
        LAST_UPDATE_DATE, LAST_UPDATED_BY, CREATION_DATE, CREATED_BY, LAST_UPDATE_LOGIN
      ) VALUES (
        XXGS_WMS_USER_ID_S.NEXTVAL, :userName, :fullName, :userPassword, :encryptedUserPassword, :startDate, :endDate, :emailAddress, :status,
        :passwordDate, :lastLogonDate, :attributeCategory, :attribute1, :attribute2, :attribute3, :attribute4, :attribute5,
        :attribute6, :attribute7, :attribute8, :attribute9, :attribute10, :attribute11, :attribute12, :attribute13, :attribute14, :attribute15,
        :lastUpdateDate, :lastUpdatedBy, :creationDate, :createdBy, :lastUpdateLogin
      )
    `;
    const binds = {
      userName: user.userName,
      fullName: user.fullName,
      userPassword: user.userPassword,
      encryptedUserPassword: user.encryptedUserPassword,
      startDate: user.startDate,
      endDate: user.endDate,
      emailAddress: user.emailAddress,
      status: user.status,
      passwordDate: user.passwordDate,
      lastLogonDate: user.lastLogonDate,
      attributeCategory: user.attributeCategory,
      attribute1: user.attribute1,
      attribute2: user.attribute2,
      attribute3: user.attribute3,
      attribute4: user.attribute4,
      attribute5: user.attribute5,
      attribute6: user.attribute6,
      attribute7: user.attribute7,
      attribute8: user.attribute8,
      attribute9: user.attribute9,
      attribute10: user.attribute10,
      attribute11: user.attribute11,
      attribute12: user.attribute12,
      attribute13: user.attribute13,
      attribute14: user.attribute14,
      attribute15: user.attribute15,
      lastUpdateDate: user.lastUpdateDate,
      lastUpdatedBy: user.lastUpdatedBy,
      creationDate: user.creationDate,
      createdBy: user.createdBy,
      lastUpdateLogin: user.lastUpdateLogin,
    };
    await OracleConnection.executeQuery(sql, binds, { autoCommit: true });
    // Fetch and return the inserted user (by userName)
    const selectSql = `SELECT * FROM ${this.tableName} WHERE USER_NAME = :userName`;
    const result = await OracleConnection.executeQuery(selectSql, {
      userName: user.userName,
    });
    if (!result.rows || result.rows.length === 0) {
      throw new Error("User insert failed");
    }
    return result.rows[0] as UserEntity;
  }
}

export const userRepo = new UserRepository();
