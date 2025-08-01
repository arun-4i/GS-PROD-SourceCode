// Oracle table: XXGS_BIN_TRNS_DROP

export interface BinTransferTrackDropEntity {
  dropId?: number;
  lineId?: number;
  inventoryItemId?: number;
  itemCode?: string;
  description?: string;
  uom?: string;
  dropQty?: number;
  dropSubinv?: string;
  dropLocator?: string;
  dropLocatorId?: string;
  type?: string;
  attributeCategory?: string;
  attribute1?: string;
  attribute2?: string;
  attribute3?: string;
  attribute4?: string;
  attribute5?: string;
  attribute6?: string;
  attribute7?: string;
  attribute8?: string;
  attribute9?: string;
  attribute10?: string;
  lastUpdateDate?: Date | string | null;
  lastUpdatedBy?: number;
  creationDate?: Date | string | null;
  createdBy?: number;
  lastUpdateLogin?: number;
}
