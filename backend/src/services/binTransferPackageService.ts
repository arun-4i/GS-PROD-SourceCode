import { IoConLotEntity } from "../entities/ioConLot.entity";
import { InvCountConfirmEntity } from "../entities/invCountConfirm.entity";
import { binTransferPackageRepo } from "../repositories/binTransferPackageRepo";
import {
  createErrorResponse,
  createSuccessResponse,
} from "../validators/common";

// Define APIResponse interface for this service
interface APIResponse {
  data?: unknown;
  status: number;
  error?: string;
  success?: boolean;
}

export class BinTransferPackageService {
  // GET_ITEM_DETAILS Oracle Package Call
  // REFACTORED: Added error handling and common response patterns
  async getItemDetail(params: Record<string, any>): Promise<APIResponse> {
    try {
      const result = await binTransferPackageRepo.getItemDetail(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting item details", error);
    }
  }

  // GET_ITEM_CROSSREF_DTLS Oracle Package Call
  // REFACTORED: Added error handling and common response patterns
  async getItemCrossref(params: Record<string, any>): Promise<APIResponse> {
    try {
      const result = await binTransferPackageRepo.getItemCrossref(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting item crossref", error);
    }
  }

  // IOConLot Operations
  // REFACTORED: Added error handling and common response patterns
  async getIOConLot(): Promise<APIResponse> {
    try {
      const result = await binTransferPackageRepo.getAllIOConLot();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting IO con lot", error);
    }
  }

  async insertIOConLot(entities: IoConLotEntity[]): Promise<APIResponse> {
    try {
      // Bulk insert: validate and insert each entity
      const insertedEntities: IoConLotEntity[] = [];
      for (const entity of entities) {
        const inserted = await binTransferPackageRepo.insertIOConLot(entity);
        insertedEntities.push(inserted);
      }
      return createSuccessResponse(insertedEntities);
    } catch (error) {
      return createErrorResponse(500, "Error inserting IO con lot", error);
    }
  }

  // InvCountConfirm Operations
  // REFACTORED: Added error handling and common response patterns
  async getInvCountConfirm(): Promise<APIResponse> {
    try {
      const result = await binTransferPackageRepo.getAllInvCountConfirm();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting inventory count confirmations",
        error
      );
    }
  }

  async insertInvCountConfirm(
    entities: InvCountConfirmEntity[]
  ): Promise<APIResponse> {
    try {
      // Bulk insert: validate and insert each entity
      const insertedEntities: InvCountConfirmEntity[] = [];
      for (const entity of entities) {
        const inserted =
          await binTransferPackageRepo.insertInvCountConfirm(entity);
        insertedEntities.push(inserted);
      }
      return createSuccessResponse(insertedEntities);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error inserting inventory count confirmations",
        error
      );
    }
  }
}

export const binTransferPackageService = new BinTransferPackageService();
