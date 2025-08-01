import { IoConLotEntity } from "../entities/ioConLot.entity";
import { InvCountConfirmEntity } from "../entities/invCountConfirm.entity";
import { binTransferPackageRepo } from "../repositories/binTransferPackageRepo";

export class BinTransferPackageService {
  // GET_ITEM_DETAILS Oracle Package Call
  async getItemDetail(params: Record<string, any>): Promise<any> {
    return binTransferPackageRepo.getItemDetail(params);
  }

  // GET_ITEM_CROSSREF_DTLS Oracle Package Call
  async getItemCrossref(params: Record<string, any>): Promise<any> {
    return binTransferPackageRepo.getItemCrossref(params);
  }

  // IOConLot Operations
  async getIOConLot(): Promise<IoConLotEntity[]> {
    return binTransferPackageRepo.getAllIOConLot();
  }

  async insertIOConLot(entities: IoConLotEntity[]): Promise<IoConLotEntity[]> {
    // Bulk insert: validate and insert each entity
    const insertedEntities: IoConLotEntity[] = [];
    for (const entity of entities) {
      const inserted = await binTransferPackageRepo.insertIOConLot(entity);
      insertedEntities.push(inserted);
    }
    return insertedEntities;
  }

  // InvCountConfirm Operations
  async getInvCountConfirm(): Promise<InvCountConfirmEntity[]> {
    return binTransferPackageRepo.getAllInvCountConfirm();
  }

  async insertInvCountConfirm(entities: InvCountConfirmEntity[]): Promise<InvCountConfirmEntity[]> {
    // Bulk insert: validate and insert each entity
    const insertedEntities: InvCountConfirmEntity[] = [];
    for (const entity of entities) {
      const inserted = await binTransferPackageRepo.insertInvCountConfirm(entity);
      insertedEntities.push(inserted);
    }
    return insertedEntities;
  }
}

export const binTransferPackageService = new BinTransferPackageService();