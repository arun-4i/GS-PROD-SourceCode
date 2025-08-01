/**
 * Transaction Logger Utility
 * Single source of truth for transaction logging patterns
 * Eliminates duplicate transaction logging across services
 */

import type { MobTransLogEntity } from "../entities/rmaConfirm.entity";

/**
 * Creates a transaction log entry
 * Single source of truth for transaction log creation
 * @param moduleName - Name of the module/operation
 * @param request - Request data to log
 * @returns Transaction log entity
 */
export function createTransactionLog(
  moduleName: string,
  request: any
): MobTransLogEntity {
  try {
    return {
      MODULE_NAME: moduleName,
      REQUEST: JSON.stringify(request),
      PROCESSED_TIME: new Date(),
    };
  } catch (error) {
    // Fallback for serialization errors
    console.error("Error serializing transaction log request:", error);
    return {
      MODULE_NAME: moduleName,
      REQUEST: "Error serializing request",
      PROCESSED_TIME: new Date(),
    };
  }
}

/**
 * Updates transaction log with response data
 * Single source of truth for transaction log updates
 * @param transactionLog - Original transaction log
 * @param response - Response data to log
 */
export function updateTransactionLogResponse(
  transactionLog: MobTransLogEntity,
  response: any
): void {
  try {
    transactionLog.RESPONSE = JSON.stringify(response);
  } catch (error) {
    // Fallback for serialization errors
    console.error("Error serializing transaction log response:", error);
    transactionLog.RESPONSE = "Error serializing response";
  }
}

/**
 * Creates a transaction log for failed duplicate records
 * Single source of truth for failed record logging
 * @param moduleName - Name of the module/operation
 * @param combinationKey - Combination key or attribute category
 * @returns Transaction log entity
 */
export function createFailedRecordLog(
  moduleName: string,
  combinationKey: string
): MobTransLogEntity {
  return {
    MODULE_NAME: moduleName,
    PROCESSED_TIME: new Date(),
    REQUEST: combinationKey, // Store combination key in REQUEST field
  };
}

/**
 * Common module names for transaction logging
 * Single source of truth for module name constants
 */
export const TransactionLogModules = {
  // RMA modules
  RMA_INSERT: "RMA - insertmo",
  ITEM_CHECK_DISPATCH: "Item Check Dispatch",

  // MO modules
  MO_CONFIRM: "moConfirm",
  PICK_CONFIRM: "pickConfirm",

  // Showroom modules
  SHOWROOM_IO_CONFIRM: "Showroom - ioConfirm",
  SHOWROOM_PO_CONFIRM: "Showroom - poConfirm",
  SHOWROOM_RTV_CONFIRM: "Showroom - rtvConfirm",
  SHOWROOM_STOCK_CONFIRM: "Showroom - stockConfirm",
  SHOWROOM_PHY_INV_CONFIRM: "Showroom - phyInvConfirm",
} as const;
