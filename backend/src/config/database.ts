import oracledb from "oracledb";
import { config } from "./env";
import { logger } from "../utils/logger";

export class OracleConnection {
  private static pool: oracledb.Pool;
  private static isInitialized = false;

  public static async initialize(): Promise<void> {
    if (OracleConnection.isInitialized) {
      return;
    }

    try {
      // Initialize Oracle client in thick mode
      if (config.ORACLE_CLIENT_LIB_DIR) {
        oracledb.initOracleClient({ libDir: config.ORACLE_CLIENT_LIB_DIR });
        logger.info("db", "Oracle thick client initialized", {
          libDir: config.ORACLE_CLIENT_LIB_DIR,
        });
      } else {
        oracledb.initOracleClient();
        logger.info(
          "db",
          "Oracle thick client initialized with default settings"
        );
      }

      // Set global Oracle DB settings
      oracledb.outFormat = oracledb.OUT_FORMAT_OBJECT;
      oracledb.autoCommit = false; // Explicit transaction control

      // Create connection pool
      OracleConnection.pool = await oracledb.createPool({
        user: config.ORACLE_USER,
        password: config.ORACLE_PASSWORD,
        connectString: config.ORACLE_CONNECTION_STRING,
        poolMin: config.ORACLE_POOL_MIN,
        poolMax: config.ORACLE_POOL_MAX,
        poolIncrement: config.ORACLE_POOL_INCREMENT,
        poolTimeout: config.ORACLE_POOL_TIMEOUT,
        connectTimeout: config.ORACLE_CONNECT_TIMEOUT * 1000, // Convert to milliseconds
        stmtCacheSize: 40,
        enableStatistics: config.NODE_ENV === "development",
      });

      OracleConnection.isInitialized = true;
      logger.info("db", "✅ Oracle connection pool created successfully", {
        poolMin: config.ORACLE_POOL_MIN,
        poolMax: config.ORACLE_POOL_MAX,
        connectString: config.ORACLE_CONNECTION_STRING.replace(
          /:\/\/.*@/,
          "://***@"
        ), // Hide credentials in logs
      });
    } catch (error: any) {
      logger.error("db", "❌ Failed to initialize Oracle connection", {
        error: error.message,
        code: error.code,
      });
      throw error;
    }
  }

  public static async getConnection(): Promise<oracledb.Connection> {
    if (!OracleConnection.isInitialized || !OracleConnection.pool) {
      await OracleConnection.initialize();
    }

    const timeoutMs = 10000; // 10 seconds
    const timeoutPromise = new Promise<never>((_, reject) => {
      setTimeout(() => {
        reject(new Error("Database connection timeout after 10 seconds"));
      }, timeoutMs);
    });

    try {
      const connectionPromise = OracleConnection.pool.getConnection();
      const connection = await Promise.race([
        connectionPromise,
        timeoutPromise,
      ]);
      logger.debug("db", "Connection acquired from pool", {
        poolConnectionsOpen: OracleConnection.pool.connectionsOpen,
        poolConnectionsInUse: OracleConnection.pool.connectionsInUse,
      });
      return connection;
    } catch (error) {
      logger.error("db", "Failed to get connection from pool", {
        error: (error as Error).message,
        code: (error as { code?: string }).code,
      });
      throw error;
    }
  }

  public static async testConnection(): Promise<void> {
    let connection: oracledb.Connection | undefined;

    try {
      connection = await OracleConnection.getConnection();

      // Test query
      const result = await connection.execute(
        "SELECT 1 AS test_value FROM DUAL"
      );

      if (result.rows && result.rows.length > 0) {
        logger.info("db", "✅ Oracle database connection test successful");
      } else {
        throw new Error("Test query returned no results");
      }
    } catch (error: any) {
      logger.error("db", "❌ Oracle database connection test failed", {
        error: error.message,
        code: error.code,
      });
      throw error;
    } finally {
      if (connection) {
        try {
          await connection.close();
        } catch (closeError) {
          logger.error("db", "Error closing test connection", {
            error: (closeError as Error).message,
          });
        }
      }
    }
  }

  public static async getPoolStatistics(): Promise<any> {
    if (!OracleConnection.pool) {
      return null;
    }

    return {
      connectionsOpen: OracleConnection.pool.connectionsOpen,
      connectionsInUse: OracleConnection.pool.connectionsInUse,
      poolMin: OracleConnection.pool.poolMin,
      poolMax: OracleConnection.pool.poolMax,
      poolIncrement: OracleConnection.pool.poolIncrement,
      poolTimeout: OracleConnection.pool.poolTimeout,
      queueTimeout: OracleConnection.pool.queueTimeout,
    };
  }

  public static async closePool(): Promise<void> {
    if (OracleConnection.pool) {
      try {
        await OracleConnection.pool.close(10); // 10 seconds to close gracefully
        logger.info("db", "🔒 Oracle connection pool closed successfully");
        OracleConnection.isInitialized = false;
      } catch (error: any) {
        logger.error("db", "Error closing Oracle connection pool", {
          error: error.message,
          code: error.code,
        });
        throw error;
      }
    }
  }

  // Helper method to execute queries with automatic connection management
  public static async executeQuery<T = any>(
    sql: string,
    binds: any = {},
    options: oracledb.ExecuteOptions = {}
  ): Promise<oracledb.Result<T>> {
    let connection: oracledb.Connection | undefined;

    try {
      connection = await OracleConnection.getConnection();

      const defaultOptions: oracledb.ExecuteOptions = {
        outFormat: oracledb.OUT_FORMAT_OBJECT,
        autoCommit: false,
        ...options,
      };

      const result = await connection.execute<T>(sql, binds, defaultOptions);

      if (defaultOptions.autoCommit) {
        await connection.commit();
      }

      return result;
    } catch (error: any) {
      if (connection && !options.autoCommit) {
        try {
          await connection.rollback();
        } catch (rollbackError) {
          logger.error("db", "Error during rollback", {
            error: (rollbackError as Error).message,
          });
        }
      }
      throw error;
    } finally {
      if (connection) {
        try {
          await connection.close();
        } catch (closeError) {
          logger.error("db", "Error closing connection", {
            error: (closeError as Error).message,
          });
        }
      }
    }
  }
}

// Legacy alias for backward compatibility during migration
export const DatabaseConnection = OracleConnection;
