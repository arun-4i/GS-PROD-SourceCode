import oracledb from "oracledb";
import { OracleConnection } from "@config/database";
import { config } from "@config/env";
import { logger } from "../utils/logger";

export interface BindParameters {
  [key: string]: any;
}

export interface OraclePackageOptions {
  autoCommit?: boolean;
  fetchArraySize?: number;
  maxRows?: number;
}

export interface OracleResult<T = any> {
  data: T[];
  rowsAffected?: number;
  outBinds?: any;
}

export class OraclePackageService {
  private schemaPrefix: string;

  constructor() {
    this.schemaPrefix = config.ORACLE_SCHEMA_PREFIX
      ? `${config.ORACLE_SCHEMA_PREFIX}.`
      : "";
  }

  /**
   * Execute Oracle stored procedure/package with automatic connection management
   */
  async callPackage<T = any>(
    packageProcedure: string,
    params: BindParameters = {},
    options: OraclePackageOptions = {}
  ): Promise<OracleResult<T>> {
    let connection: oracledb.Connection | undefined;
    const startTime = Date.now();

    try {
      connection = await OracleConnection.getConnection();

      const fullProcedureName = `${this.schemaPrefix}${packageProcedure}`;

      logger.info("db", "Executing Oracle package procedure", {
        procedure: fullProcedureName,
        paramCount: Object.keys(params).length,
      });

      // Set default options
      const executeOptions: oracledb.ExecuteOptions = {
        outFormat: oracledb.OUT_FORMAT_OBJECT,
        autoCommit: options.autoCommit ?? false,
        fetchArraySize: options.fetchArraySize ?? 1000,
        maxRows: options.maxRows ?? 10000,
      };

      // Execute the procedure
      const result = await connection.execute(
        `BEGIN ${fullProcedureName}(${this.buildParameterList(params)}); END;`,
        params,
        executeOptions
      );

      // Process cursor results if any
      const processedData = await this.processCursors(result.outBinds || {});

      const executionTime = Date.now() - startTime;

      logger.info("db", "Oracle package procedure executed successfully", {
        procedure: fullProcedureName,
        executionTime: `${executionTime}ms`,
        rowsAffected: result.rowsAffected,
        hasOutBinds: !!result.outBinds,
      });

      return {
        data: processedData,
        rowsAffected: result.rowsAffected,
        outBinds: result.outBinds,
      };
    } catch (error: any) {
      const executionTime = Date.now() - startTime;

      logger.error("db", "Oracle package procedure execution failed", {
        procedure: packageProcedure,
        error: error.message,
        code: error.code,
        executionTime: `${executionTime}ms`,
      });

      throw this.mapOracleError(error);
    } finally {
      if (connection) {
        try {
          await connection.close();
        } catch (closeError) {
          logger.error("db", "Error closing Oracle connection", {
            error: (closeError as Error).message,
          });
        }
      }
    }
  }

  /**
   * Execute a simple Oracle query with parameters
   */
  async executeQuery<T = any>(
    sql: string,
    binds: BindParameters = {},
    options: OraclePackageOptions = {}
  ): Promise<OracleResult<T>> {
    let connection: oracledb.Connection | undefined;

    try {
      connection = await OracleConnection.getConnection();

      const executeOptions: oracledb.ExecuteOptions = {
        outFormat: oracledb.OUT_FORMAT_OBJECT,
        autoCommit: options.autoCommit ?? false,
        fetchArraySize: options.fetchArraySize ?? 1000,
        maxRows: options.maxRows ?? 10000,
      };

      const result = await connection.execute<T>(sql, binds, executeOptions);

      return {
        data: result.rows || [],
        rowsAffected: result.rowsAffected,
        outBinds: result.outBinds,
      };
    } catch (error: any) {
      logger.error("db", "Oracle query execution failed", {
        error: error.message,
        code: error.code,
      });
      throw this.mapOracleError(error);
    } finally {
      if (connection) {
        try {
          await connection.close();
        } catch (closeError) {
          logger.error("db", "Error closing Oracle connection", {
            error: (closeError as Error).message,
          });
        }
      }
    }
  }

  /**
   * Execute multiple operations within a transaction
   */
  async executeTransaction<T = any>(
    operations: Array<{
      sql?: string;
      packageProcedure?: string;
      params: BindParameters;
      options?: OraclePackageOptions;
    }>
  ): Promise<OracleResult<T>[]> {
    let connection: oracledb.Connection | undefined;

    try {
      connection = await OracleConnection.getConnection();
      const results: OracleResult<T>[] = [];

      for (const operation of operations) {
        const executeOptions: oracledb.ExecuteOptions = {
          outFormat: oracledb.OUT_FORMAT_OBJECT,
          autoCommit: false, // Never auto-commit in transactions
          ...operation.options,
        };

        let result: oracledb.Result<any>;

        if (operation.packageProcedure) {
          const fullProcedureName = `${this.schemaPrefix}${operation.packageProcedure}`;
          result = await connection.execute(
            `BEGIN ${fullProcedureName}(${this.buildParameterList(operation.params)}); END;`,
            operation.params,
            executeOptions
          );
        } else if (operation.sql) {
          result = await connection.execute(
            operation.sql,
            operation.params,
            executeOptions
          );
        } else {
          throw new Error("Either sql or packageProcedure must be provided");
        }

        const processedData = await this.processCursors(result.outBinds || {});

        results.push({
          data: processedData,
          rowsAffected: result.rowsAffected,
          outBinds: result.outBinds,
        });
      }

      // Commit the transaction
      await connection.commit();

      logger.info("db", "Oracle transaction completed successfully", {
        operationCount: operations.length,
      });

      return results;
    } catch (error: any) {
      if (connection) {
        try {
          await connection.rollback();
          logger.info("db", "Oracle transaction rolled back due to error");
        } catch (rollbackError) {
          logger.error("db", "Error during transaction rollback", {
            error: (rollbackError as Error).message,
          });
        }
      }

      logger.error("db", "Oracle transaction failed", {
        error: error.message,
        code: error.code,
      });

      throw this.mapOracleError(error);
    } finally {
      if (connection) {
        try {
          await connection.close();
        } catch (closeError) {
          logger.error("db", "Error closing Oracle connection", {
            error: (closeError as Error).message,
          });
        }
      }
    }
  }

  /**
   * Build parameter list for stored procedure calls
   */
  private buildParameterList(params: BindParameters): string {
    return Object.keys(params)
      .map((key) => `:${key}`)
      .join(", ");
  }

  /**
   * Process cursor output parameters and fetch their results
   */
  private async processCursors(outBinds: any): Promise<any[]> {
    const results: any[] = [];

    for (const [key, value] of Object.entries(outBinds)) {
      if (value && typeof value === "object" && "getRows" in value) {
        // This is a cursor
        try {
          const cursor = value as oracledb.ResultSet<any>;
          const rows = await cursor.getRows(10000); // Fetch up to 10k rows
          await cursor.close();
          results.push(...rows);
        } catch (error) {
          logger.error("db", "Error processing cursor", {
            cursorKey: key,
            error: (error as Error).message,
          });
        }
      } else {
        // Regular output parameter
        if (Array.isArray(value)) {
          results.push(...value);
        } else if (value !== null && value !== undefined) {
          results.push(value);
        }
      }
    }

    return results;
  }

  /**
   * Map Oracle errors to application errors
   */
  private mapOracleError(error: any): Error {
    const mappedError = new Error(error.message);

    // Add Oracle-specific error mapping
    switch (error.code) {
      case "ORA-00001":
        (mappedError as any).code = "DUPLICATE_ENTRY";
        (mappedError as any).statusCode = 409;
        break;
      case "ORA-01403":
        (mappedError as any).code = "NOT_FOUND";
        (mappedError as any).statusCode = 404;
        break;
      case "ORA-20001":
        (mappedError as any).code = "BUSINESS_LOGIC_ERROR";
        (mappedError as any).statusCode = 400;
        break;
      case "ORA-12154":
      case "ORA-12505":
        (mappedError as any).code = "CONNECTION_ERROR";
        (mappedError as any).statusCode = 503;
        break;
      default:
        (mappedError as any).code = "DATABASE_ERROR";
        (mappedError as any).statusCode = 500;
    }

    (mappedError as any).originalCode = error.code;
    (mappedError as any).originalMessage = error.message;

    return mappedError;
  }
}

export const oraclePackageService = new OraclePackageService();
