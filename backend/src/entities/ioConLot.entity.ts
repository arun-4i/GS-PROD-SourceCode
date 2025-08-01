// Oracle table: XXGS_CONF_LOT_DETAILS

type DateField = Date | string | null;

export interface IoConLotEntity {
  lotId?: number;
  transactionType?: string;
  lotNumber?: string;
  lotExpiryDate?: DateField;
  lotQuantity?: number;
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
  attribute11?: string;
  attribute12?: string;
  attribute13?: string;
  attribute14?: string;
  attribute15?: string;
  lastUpdateDate?: DateField;
  lastUpdatedBy?: number;
  creationDate?: DateField;
  createdBy?: number;
  lastUpdateLogin?: number;
  transactionTypeId?: number;
}
