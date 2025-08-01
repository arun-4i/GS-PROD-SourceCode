// Oracle table: XXGS_BIN_TRNS_PICK

export interface BinTransferTrackPickEntity {
  lineId?: number;
  headerId?: number;
  invOrg?: string;
  invOrgId?: number;
  inventoryItemId?: number;
  itemCode?: string;
  description?: string;
  uom?: string;
  pickedQty?: number;
  pickedSubinv?: string;
  pickedLocator?: string;
  pickedlocatorId?: string;
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
