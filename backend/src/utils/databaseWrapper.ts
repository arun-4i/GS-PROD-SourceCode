/**
 * Database Transaction Wrapper
 * Single source of truth for Oracle connection management
 * Eliminates duplicate connection handling patterns across repositories
 */

import oracledb from "oracledb";
import { OracleConnection } from "../config/database";
import { logger } from "./logger";

export type DatabaseOperation<T> = (
  connection: oracledb.Connection
) => Promise<T>;

/**
 * Executes database operation with automatic connection management
 * Handles connection acquisition, cleanup, and error logging
 * @param operation - Database operation to execute
 * @param operationName - Name for logging purposes
 * @returns Promise with operation result
 */
export async function withDatabaseConnection<T>(
  operation: DatabaseOperation<T>,
  operationName: string
): Promise<T> {
  let connection: oracledb.Connection | undefined;

  try {
    connection = await OracleConnection.getConnection();
    logger.debug("db", `Starting operation: ${operationName}`);

    const result = await operation(connection);

    logger.debug("db", `Completed operation: ${operationName}`);
    return result;
  } catch (error: any) {
    logger.error("db", `Error in operation: ${operationName}`, {
      error: error.message,
      code: error.code,
    });
    throw error;
  } finally {
    if (connection) {
      try {
        await connection.close();
        logger.debug("db", `Connection closed for: ${operationName}`);
      } catch (closeError: any) {
        logger.error("db", `Error closing connection for: ${operationName}`, {
          error: closeError.message,
        });
      }
    }
  }
}

/**
 * Executes database operation with transaction support
 * Automatically handles commit/rollback and connection cleanup
 * @param operation - Database operation to execute
 * @param operationName - Name for logging purposes
 * @returns Promise with operation result
 */
export async function withDatabaseTransaction<T>(
  operation: DatabaseOperation<T>,
  operationName: string
): Promise<T> {
  let connection: oracledb.Connection | undefined;

  try {
    connection = await OracleConnection.getConnection();
    logger.debug("db", `Starting transaction: ${operationName}`);

    const result = await operation(connection);

    await connection.commit();
    logger.debug("db", `Transaction committed: ${operationName}`);

    return result;
  } catch (error: any) {
    if (connection) {
      try {
        await connection.rollback();
        logger.debug("db", `Transaction rolled back: ${operationName}`);
      } catch (rollbackError: any) {
        logger.error("db", `Error rolling back transaction: ${operationName}`, {
          error: rollbackError.message,
        });
      }
    }

    logger.error("db", `Transaction failed: ${operationName}`, {
      error: error.message,
      code: error.code,
    });
    throw error;
  } finally {
    if (connection) {
      try {
        await connection.close();
        logger.debug(
          "db",
          `Connection closed for transaction: ${operationName}`
        );
      } catch (closeError: any) {
        logger.error(
          "db",
          `Error closing connection for transaction: ${operationName}`,
          {
            error: closeError.message,
          }
        );
      }
    }
  }
}
