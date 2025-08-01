/**
 * Oracle Database Helper Utilities
 * Single source of truth for common Oracle operations
 * Eliminates duplicate patterns across repositories
 */

import oracledb from "oracledb";
import { withDatabaseConnection } from "./databaseWrapper";

/**
 * Standard Oracle sequence value generator
 * Single source of truth for sequence handling
 * @param sequenceName - Name of the Oracle sequence
 * @returns Promise with next sequence value
 */
export async function getNextSequenceValue(
  sequenceName: string
): Promise<number> {
  return withDatabaseConnection(async (connection) => {
    const result = await connection.execute<{ NEXTVAL: number }>(
      `SELECT ${sequenceName}.NEXTVAL as NEXTVAL FROM DUAL`,
      {},
      { outFormat: oracledb.OUT_FORMAT_OBJECT }
    );
    return result.rows?.[0]?.NEXTVAL || 0;
  }, `getNextSequenceValue-${sequenceName}`);
}

/**
 * Standard count query executor
 * Single source of truth for COUNT queries
 * @param tableName - Name of the table
 * @param whereClause - WHERE clause (without WHERE keyword)
 * @param binds - Bind variables
 * @returns Promise with count result
 */
export async function executeCountQuery(
  tableName: string,
  whereClause: string,
  binds: oracledb.BindParameters
): Promise<number> {
  return withDatabaseConnection(async (connection) => {
    const sql = `SELECT COUNT(*) as COUNT FROM ${tableName} WHERE ${whereClause}`;
    const result = await connection.execute<{ COUNT: number }>(
      sql,
      binds as oracledb.BindParameters,
      {
        outFormat: oracledb.OUT_FORMAT_OBJECT,
      }
    );
    return result.rows?.[0]?.COUNT || 0;
  }, `executeCountQuery-${tableName}`);
}

/**
 * Standard bulk insert executor
 * Single source of truth for bulk insert operations
 * @param tableName - Name of the table
 * @param columns - Array of column names
 * @param values - Array of value arrays
 * @param sequenceName - Optional sequence name for ID generation
 * @returns Promise with insert result
 */
export async function executeBulkInsert(
  tableName: string,
  columns: string[],
  values: unknown[][],
  sequenceName?: string
): Promise<oracledb.Results<unknown>> {
  return withDatabaseConnection(async (connection) => {
    // Build INSERT statement
    const placeholders = columns
      .map((_, index) =>
        index === 0 && sequenceName
          ? `${sequenceName}.NEXTVAL`
          : `:${index + 1}`
      )
      .join(", ");

    const sql = `
        INSERT INTO ${tableName} (${columns.join(", ")})
        VALUES (${placeholders})
      `;

    // Execute bulk insert with executeMany for better performance
    return await connection.executeMany(sql, values, {
      autoCommit: false, // Let caller handle transaction
      bindDefs: columns.map(() => ({ type: oracledb.STRING, maxSize: 4000 })),
    });
  }, `executeBulkInsert-${tableName}`);
}

/**
 * Standard find all executor
 * Single source of truth for SELECT * operations
 * @param tableName - Name of the table
 * @param orderBy - Optional ORDER BY clause
 * @returns Promise with all records
 */
export async function executeFindAll<T>(
  tableName: string,
  orderBy?: string
): Promise<T[]> {
  return withDatabaseConnection(async (connection) => {
    const orderClause = orderBy ? ` ORDER BY ${orderBy}` : "";
    const sql = `SELECT * FROM ${tableName}${orderClause}`;
    const result = await connection.execute<T>(
      sql,
      {},
      { outFormat: oracledb.OUT_FORMAT_OBJECT }
    );
    return result.rows || [];
  }, `executeFindAll-${tableName}`);
}

/**
 * Oracle package call result processor
 * Single source of truth for processing Oracle package responses
 * @param cursor - Oracle cursor result
 * @returns Promise with processed rows
 */
export async function processCursorResult(
  cursor: oracledb.ResultSet<unknown>
): Promise<unknown[]> {
  try {
    const rows = await cursor.getRows();
    return rows || [];
  } finally {
    await cursor.close();
  }
}

/**
 * Common Oracle bind variable types
 * Single source of truth for bind variable configurations
 */
export const OracleBindTypes = {
  STRING: { type: oracledb.STRING, maxSize: 4000 },
  NUMBER: { type: oracledb.NUMBER },
  DATE: { type: oracledb.DATE },
  CLOB: { type: oracledb.CLOB },
  CURSOR_OUT: { dir: oracledb.BIND_OUT, type: oracledb.CURSOR },
  STRING_OUT: { dir: oracledb.BIND_OUT, type: oracledb.STRING, maxSize: 4000 },
  NUMBER_OUT: { dir: oracledb.BIND_OUT, type: oracledb.NUMBER },
} as const;
