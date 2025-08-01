// Composite entity for QuickDrop operations (not a direct table mapping)

import { BinTransferTrackHdrEntity } from "./binTransferTrackHdr.entity";
import { BinTransferTrackPickEntity } from "./binTransferTrackPick.entity";
import { BinTransferTrackDropEntity } from "./binTransferTrackDrop.entity";

export interface QuickDropEntity {
  id?: number;
  header?: BinTransferTrackHdrEntity[];
  pick?: BinTransferTrackPickEntity[];
  drop?: BinTransferTrackDropEntity[];
}
