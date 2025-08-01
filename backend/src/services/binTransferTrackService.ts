import { BinTransferTrackHdrEntity } from "../entities/binTransferTrackHdr.entity";
import { BinTransferTrackPickEntity } from "../entities/binTransferTrackPick.entity";
import { BinTransferTrackDropEntity } from "../entities/binTransferTrackDrop.entity";
import { QuickDropEntity } from "../entities/quickDrop.entity";
import { binTransferTrackRepo } from "../repositories/binTransferTrackRepo";

export class BinTransferTrackService {
  // BinTransferTrack Header Operations
  async insertBinTransferHdr(
    entities: BinTransferTrackHdrEntity[]
  ): Promise<BinTransferTrackHdrEntity[]> {
    // Bulk insert: validate and insert each entity
    const insertedEntities: BinTransferTrackHdrEntity[] = [];
    for (const entity of entities) {
      const inserted = await binTransferTrackRepo.insertBinTransferHdr(entity);
      insertedEntities.push(inserted);
    }
    return insertedEntities;
  }

  async getBinTransferHdr(): Promise<BinTransferTrackHdrEntity[]> {
    return binTransferTrackRepo.getAllBinTransferHdr();
  }

  // BinTransferTrack Pick/Lines Operations
  async insertBinTransferLns(
    entities: BinTransferTrackPickEntity[]
  ): Promise<BinTransferTrackPickEntity[]> {
    // Bulk insert: validate and insert each entity
    const insertedEntities: BinTransferTrackPickEntity[] = [];
    for (const entity of entities) {
      const inserted = await binTransferTrackRepo.insertBinTransferPick(entity);
      insertedEntities.push(inserted);
    }
    return insertedEntities;
  }

  async getBinTransferLns(): Promise<BinTransferTrackPickEntity[]> {
    return binTransferTrackRepo.getAllBinTransferPick();
  }

  // BinTransferTrack Drop Operations
  async insertBinTransferDrop(
    entities: BinTransferTrackDropEntity[]
  ): Promise<BinTransferTrackDropEntity[]> {
    // Bulk insert: validate and insert each entity
    const insertedEntities: BinTransferTrackDropEntity[] = [];
    for (const entity of entities) {
      const inserted = await binTransferTrackRepo.insertBinTransferDrop(entity);
      insertedEntities.push(inserted);
    }
    return insertedEntities;
  }

  async getBinTransferDrop(): Promise<BinTransferTrackDropEntity[]> {
    return binTransferTrackRepo.getAllBinTransferDrop();
  }

  // Oracle Package Operations
  async getBinTransRefNo(): Promise<unknown> {
    return binTransferTrackRepo.getBinTransRefNo();
  }

  async getBinTransDropHdr(params: Record<string, unknown>): Promise<unknown> {
    return binTransferTrackRepo.getBinTransDropHdr(params);
  }

  async getBinTransDropDtls(params: Record<string, unknown>): Promise<unknown> {
    return binTransferTrackRepo.getBinTransDropDtls(params);
  }

  async getBinNumber(params: Record<string, unknown>): Promise<unknown> {
    return binTransferTrackRepo.getBinNumber(params);
  }

  // Quick Drop Operations
  async insertQuickDrop(entity: QuickDropEntity): Promise<unknown> {
    return binTransferTrackRepo.insertQuickDrop(entity);
  }
}

export const binTransferTrackService = new BinTransferTrackService();
