/**
 * MoConfirm Service Layer
 * Pure migration from Spring Boot MoConfirmSO.java & PickConfirmSO.java
 * Implements complex duplicate filtering, transaction logging, and Oracle package integration
 */

import {
  MoConfirmEntity,
  PickConfirmEntity,
  MoPickConfirmWrapper,
  APIResponse,
} from "../entities/moConfirm.entity";
import { MoConfirmRepository } from "../repositories/moConfirmRepo";
import { createFailedRecordLog } from "../utils/transactionLogger";
import {
  createErrorResponse,
  createSuccessResponse,
} from "../validators/common";
import { PickConfirmRepository } from "../repositories/pickConfirmRepo";

export class MoConfirmService {
  private readonly moConfirmRepo: MoConfirmRepository;
  private readonly pickConfirmRepo: PickConfirmRepository;

  constructor() {
    this.moConfirmRepo = new MoConfirmRepository();
    this.pickConfirmRepo = new PickConfirmRepository();
  }

  // =====================================================
  // TRANSACTION LOGGING UTILITIES (REUSED FROM COMMON)
  // =====================================================

  // Transaction logging now handled by common utilities
  // createTransactionLog, updateTransactionLogResponse, and createFailedRecordLog imported from utils

  // =====================================================
  // MO CONFIRMATION OPERATIONS
  // =====================================================

  /**
   * Insert MO confirmations with complex duplicate filtering logic
   * Migrated from MoConfirmCO.insertMoConfirm() method
   */
  async insertMoConfirmations(
    entities: MoConfirmEntity[]
  ): Promise<APIResponse> {
    const myList: string[] = [];
    const listFromIterator: MoConfirmEntity[] = [];

    for (const current of entities) {
      const processResult = await this.processMoConfirmEntity(current, myList);

      if (processResult.shouldAdd) {
        listFromIterator.push(current);
        myList.push(processResult.combination);
      }
    }

    return this.insertValidMoConfirmations(listFromIterator);
  }

  /**
   * Process individual MO confirm entity (extracted for complexity reduction)
   */
  private async processMoConfirmEntity(
    entity: MoConfirmEntity,
    existingList: string[]
  ): Promise<{ shouldAdd: boolean; combination: string }> {
    // 6-field combination key matching Spring Boot logic
    const combination = `${entity.sourceLocationId}-${entity.deliveryDetailId}-${entity.itemId}-${entity.moNumber}-${entity.moLineNumber}-${entity.status}`;

    if (existingList.includes(combination)) {
      return { shouldAdd: false, combination };
    }

    // Check database for existing records
    const countR = await this.moConfirmRepo.checkDuplicateRecord(
      entity.sourceLocationId?.toString() || "",
      entity.deliveryDetailId?.toString() || "",
      entity.itemId?.toString() || "",
      entity.moNumber?.toString() || "",
      entity.moLineNumber?.toString() || "",
      entity.status || ""
    );

    if (countR === 0) {
      return { shouldAdd: true, combination };
    }

    // Duplicate found - log failed record
    createFailedRecordLog("moConfirm", combination);
    return { shouldAdd: false, combination };
  }

  /**
   * Insert valid MO confirmations (extracted method)
   */
  private async insertValidMoConfirmations(
    entities: MoConfirmEntity[]
  ): Promise<APIResponse> {
    if (entities.length > 0) {
      const result = await this.moConfirmRepo.insertMoConfirmations(entities);

      return createSuccessResponse(result);
    }

    return createSuccessResponse([]);
  }

  /**
   * Get all MO confirmations
   * Migrated from MoConfirmSO.getReceiptConfirm() method
   */
  async getAllMoConfirmations(): Promise<APIResponse> {
    try {
      const result = await this.moConfirmRepo.findAllMoConfirmations();

      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error in getAllMoConfirmations", error);
    }
  }

  // =====================================================
  // PICK CONFIRMATION OPERATIONS
  // =====================================================

  /**
   * Insert pick confirmations with complex transaction type handling
   * Migrated from MoConfirmCO.insertPickConfirm() method
   */
  async insertPickConfirmations(
    entities: PickConfirmEntity[]
  ): Promise<APIResponse> {
    const myPoList: string[] = [];
    const poListFromIterator: PickConfirmEntity[] = [];

    for (const poCurrent of entities) {
      const processResult = await this.processPickConfirmEntity(
        poCurrent,
        myPoList
      );

      if (processResult.shouldAdd) {
        poListFromIterator.push(poCurrent);
        myPoList.push(processResult.combination);
      }
    }

    return this.insertValidPickConfirmations(poListFromIterator);
  }

  /**
   * Process individual pick confirm entity (extracted for complexity reduction)
   */
  private async processPickConfirmEntity(
    entity: PickConfirmEntity,
    existingList: string[]
  ): Promise<{ shouldAdd: boolean; combination: string }> {
    const transactionType = entity.transactionType?.toLowerCase();

    if (transactionType === "out bound picking") {
      return this.processOutBoundPicking(entity, existingList);
    }

    if (transactionType === "rma_delivery") {
      return this.processRmaDelivery(entity, existingList);
    }

    return { shouldAdd: false, combination: "" };
  }

  /**
   * Process Out Bound Picking transaction type (extracted method)
   */
  private async processOutBoundPicking(
    entity: PickConfirmEntity,
    existingList: string[]
  ): Promise<{ shouldAdd: boolean; combination: string }> {
    const combination = `${entity.deliveryDetailId}-${entity.fromSerialNumber}-${entity.attribute1}-${entity.attribute2}-${entity.attribute3}-${entity.attribute4}`;

    if (existingList.includes(combination)) {
      return { shouldAdd: false, combination };
    }

    const countR = await this.pickConfirmRepo.checkDuplicatePickRecord(
      entity.deliveryDetailId?.toString() || "",
      entity.fromSerialNumber || "",
      entity.attribute1 || "",
      entity.attribute2 || "",
      entity.attribute3 || "",
      entity.attribute4 || ""
    );

    if (countR === 0) {
      return { shouldAdd: true, combination };
    }

    createFailedRecordLog("poConfirm", combination);
    return { shouldAdd: false, combination };
  }

  /**
   * Process RMA_DELIVERY transaction type (extracted method)
   */
  private async processRmaDelivery(
    entity: PickConfirmEntity,
    existingList: string[]
  ): Promise<{ shouldAdd: boolean; combination: string }> {
    const combination = `${entity.fromSerialNumber}-${entity.attribute2}-${entity.attribute3}-${entity.transactionType}`;

    if (existingList.includes(combination)) {
      return { shouldAdd: false, combination };
    }

    const countR = await this.pickConfirmRepo.checkDeliveryRecord(
      entity.fromSerialNumber || "",
      entity.attribute2 || "",
      entity.attribute3 || "",
      entity.transactionType || ""
    );

    if (countR === 0) {
      return { shouldAdd: true, combination };
    }

    createFailedRecordLog("poConfirm", combination);
    return { shouldAdd: false, combination };
  }

  /**
   * Insert valid pick confirmations (extracted method)
   */
  private async insertValidPickConfirmations(
    entities: PickConfirmEntity[]
  ): Promise<APIResponse> {
    if (entities.length > 0) {
      const result =
        await this.pickConfirmRepo.insertPickConfirmations(entities);

      return createSuccessResponse(result);
    }

    return createSuccessResponse([]);
  }

  /**
   * Get all pick confirmations
   * Migrated from PickConfirmSO.getPickConfirm() method
   */
  async getAllPickConfirmations(): Promise<APIResponse> {
    try {
      const result = await this.pickConfirmRepo.findAllPickConfirmations();

      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error in getAllPickConfirmations",
        error
      );
    }
  }

  // =====================================================
  // ORACLE PACKAGE OPERATIONS
  // =====================================================

  /**
   * Process Quick Pick JSON via Oracle package
   * Migrated from MoConfirmSO.insertJsonPost() method with complex response parsing
   */
  async processQuickPickJson(payload: string): Promise<APIResponse> {
    try {
      const packageResult =
        await this.moConfirmRepo.processQuickPickJson(payload);

      return this.parseOraclePackageResponse(packageResult);
    } catch (error) {
      return createErrorResponse(500, "Error in processQuickPickJson", error);
    }
  }

  /**
   * Parse Oracle package response (extracted for complexity reduction)
   */
  private parseOraclePackageResponse(packageResult: unknown): APIResponse {
    const api: APIResponse = {
      data: packageResult,
      status: 200,
      success: true,
    };

    // Cast to expected Oracle package response type
    const typedResult = packageResult as { P_MES?: any; P_MES2?: any };

    let co = 0;
    const responses = [typedResult.P_MES, typedResult.P_MES2];

    for (const entry of responses) {
      if (!entry) {
        continue;
      }

      const value = entry.toString();
      co++;

      const finalString = this.extractErrorMessages(value, co);
      this.setResponseStatus(api, value, finalString, co);
    }

    return api;
  }

  /**
   * Extract error messages from response (extracted method)
   */
  private extractErrorMessages(value: string, co: number): string | null {
    if (co === 2 && value.length > 20) {
      try {
        const descriptions = value
          .substring(20, value.length - 1)
          .split("\\}, \\{ERROR_DESCRIPTION=");
        let finalString: string | null = null;

        for (const item of descriptions) {
          finalString = item + "~" + finalString;
        }

        return finalString;
      } catch {
        return null;
      }
    }
    return null;
  }

  /**
   * Set response status based on content (extracted method)
   */
  private setResponseStatus(
    api: APIResponse,
    value: string,
    finalString: string | null,
    co: number
  ): void {
    if (value.includes("200")) {
      api.status = 200;
      api.error = "Created";
    } else if (value.includes("400")) {
      api.status = 400;
    }

    if (api.status === 400 && co === 2 && finalString) {
      const f = finalString
        .replace("}", "")
        .substring(0, finalString.length - 6);
      api.error = "BAD REQUEST~" + f;
    }
  }

  // =====================================================
  // COMBINED OPERATIONS
  // =====================================================

  /**
   * Insert combined MO and Pick confirmations with cross-validation
   * Migrated from MoConfirmCO.insertBothMoPick() method
   */
  async insertCombinedMoPick(
    wrapper: MoPickConfirmWrapper
  ): Promise<APIResponse> {
    const moMap = new Map<string, number>();
    const poMap = new Map<string, number>();

    // Process MO confirmations
    await this.processCombinedMoConfirmations(wrapper.moConfirms, moMap);

    // Process Pick confirmations
    await this.processCombinedPickConfirmations(
      wrapper.pickConfirmations,
      poMap
    );

    // Cross-validation
    return this.validateCombinedOperations(moMap, poMap);
  }

  /**
   * Process MO confirmations for combined operation (extracted method)
   */
  private async processCombinedMoConfirmations(
    moConfirms: MoConfirmEntity[],
    moMap: Map<string, number>
  ): Promise<void> {
    const myList: string[] = [];
    const listFromIterator: MoConfirmEntity[] = [];

    for (const current of moConfirms) {
      // Quantity mapping for validation
      if (current.attribute12?.toLowerCase() === "y") {
        const deliveryDetailId = current.deliveryDetailId?.toString() || "";
        const currentQuantity = moMap.get(deliveryDetailId) || 0;
        moMap.set(
          deliveryDetailId,
          currentQuantity + (current.pickedQuantity || 0)
        );
      }

      const processResult = await this.processMoConfirmEntity(current, myList);

      if (processResult.shouldAdd) {
        listFromIterator.push(current);
        myList.push(processResult.combination);
      } else {
        createFailedRecordLog("moQuickConfirm", processResult.combination);
      }
    }

    // Insert valid MO confirmations
    if (listFromIterator.length > 0) {
      await this.moConfirmRepo.insertMoConfirmations(listFromIterator);
    }
  }

  /**
   * Process Pick confirmations for combined operation (extracted method)
   */
  private async processCombinedPickConfirmations(
    pickConfirmations: PickConfirmEntity[],
    poMap: Map<string, number>
  ): Promise<void> {
    const myPoList: string[] = [];
    const poListFromIterator: PickConfirmEntity[] = [];

    for (const poCurrent of pickConfirmations) {
      // Count mapping for validation
      const deliveryDetailId = poCurrent.deliveryDetailId?.toString() || "";
      const currentCount = poMap.get(deliveryDetailId) || 0;
      poMap.set(deliveryDetailId, currentCount + 1);

      const processResult = await this.processPickConfirmEntity(
        poCurrent,
        myPoList
      );

      if (processResult.shouldAdd) {
        poListFromIterator.push(poCurrent);
        myPoList.push(processResult.combination);
      } else {
        createFailedRecordLog("poQuickConfirm", processResult.combination);
      }
    }

    // Insert valid Pick confirmations
    if (poListFromIterator.length > 0) {
      await this.pickConfirmRepo.insertPickConfirmations(poListFromIterator);
    }
  }

  /**
   * Validate combined operations with cross-validation (extracted method)
   */
  private async validateCombinedOperations(
    moMap: Map<string, number>,
    poMap: Map<string, number>
  ): Promise<APIResponse> {
    const api: APIResponse = { data: null, status: 200, success: true };
    let badCount = 0;

    if (moMap.size > 0) {
      if (poMap.size === 0) {
        api.status = 400;
        api.error = "Data insertion issue";
      } else {
        for (const [moKey, moQuantity] of moMap) {
          const matchCount =
            await this.pickConfirmRepo.getPickMatchCount(moKey);

          if (moQuantity === matchCount) {
            api.status = 200;
            api.error = "Created";
          } else {
            api.status = 400;
            api.error = "Data insertion issue";
            badCount++;
          }
        }
      }

      if (badCount > 0) {
        api.status = 400;
        api.error = "Data insertion issue";
      }
    } else {
      api.status = 200;
      api.error = "Created";
    }

    return api;
  }
}
