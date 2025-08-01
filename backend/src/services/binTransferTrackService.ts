import { BinTransferTrackHdrEntity } from "../entities/binTransferTrackHdr.entity";
import { BinTransferTrackPickEntity } from "../entities/binTransferTrackPick.entity";
import { BinTransferTrackDropEntity } from "../entities/binTransferTrackDrop.entity";
import { QuickDropEntity } from "../entities/quickDrop.entity";
import { binTransferTrackRepo } from "../repositories/binTransferTrackRepo";
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

export class BinTransferTrackService {
  // BinTransferTrack Header Operations
  // REFACTORED: Added error handling and common response patterns
  async insertBinTransferHdr(
    entities: BinTransferTrackHdrEntity[]
  ): Promise<APIResponse> {
    try {
      // Bulk insert: validate and insert each entity
      const insertedEntities: BinTransferTrackHdrEntity[] = [];
      for (const entity of entities) {
        const inserted =
          await binTransferTrackRepo.insertBinTransferHdr(entity);
        insertedEntities.push(inserted);
      }
      return createSuccessResponse(insertedEntities);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error inserting bin transfer headers",
        error
      );
    }
  }

  async getBinTransferHdr(): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.getAllBinTransferHdr();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting bin transfer headers",
        error
      );
    }
  }

  // BinTransferTrack Pick/Lines Operations
  // REFACTORED: Added error handling and common response patterns
  async insertBinTransferLns(
    entities: BinTransferTrackPickEntity[]
  ): Promise<APIResponse> {
    try {
      // Bulk insert: validate and insert each entity
      const insertedEntities: BinTransferTrackPickEntity[] = [];
      for (const entity of entities) {
        const inserted =
          await binTransferTrackRepo.insertBinTransferPick(entity);
        insertedEntities.push(inserted);
      }
      return createSuccessResponse(insertedEntities);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error inserting bin transfer lines",
        error
      );
    }
  }

  async getBinTransferLns(): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.getAllBinTransferPick();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting bin transfer lines",
        error
      );
    }
  }

  // BinTransferTrack Drop Operations
  // REFACTORED: Added error handling and common response patterns
  async insertBinTransferDrop(
    entities: BinTransferTrackDropEntity[]
  ): Promise<APIResponse> {
    try {
      // Bulk insert: validate and insert each entity
      const insertedEntities: BinTransferTrackDropEntity[] = [];
      for (const entity of entities) {
        const inserted =
          await binTransferTrackRepo.insertBinTransferDrop(entity);
        insertedEntities.push(inserted);
      }
      return createSuccessResponse(insertedEntities);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error inserting bin transfer drops",
        error
      );
    }
  }

  async getBinTransferDrop(): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.getAllBinTransferDrop();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting bin transfer drops",
        error
      );
    }
  }

  // Oracle Package Operations
  // REFACTORED: Added error handling and common response patterns
  async getBinTransRefNo(): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.getBinTransRefNo();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting bin transfer reference number",
        error
      );
    }
  }

  async getBinTransDropHdr(
    params: Record<string, unknown>
  ): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.getBinTransDropHdr(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting bin transfer drop header",
        error
      );
    }
  }

  async getBinTransDropDtls(
    params: Record<string, unknown>
  ): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.getBinTransDropDtls(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting bin transfer drop details",
        error
      );
    }
  }

  async getBinNumber(params: Record<string, unknown>): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.getBinNumber(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting bin number", error);
    }
  }

  // Quick Drop Operations
  // REFACTORED: Added error handling and common response patterns
  async insertQuickDrop(entity: QuickDropEntity): Promise<APIResponse> {
    try {
      const result = await binTransferTrackRepo.insertQuickDrop(entity);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error inserting quick drop", error);
    }
  }
}

export const binTransferTrackService = new BinTransferTrackService();
