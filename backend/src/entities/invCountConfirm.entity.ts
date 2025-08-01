// Oracle table: xxgs_inv_count_confirmations

type DateField = Date | string | null;

export interface InvCountConfirmEntity {
  invCountId?: number;
  physicalInvName?: string;
  physicalInvId?: number;
  subInventory?: string;
  locators?: string;
  locatorsId?: number;
  tagNumber?: string;
  tagId?: number;
  itemCode?: string;
  itemId?: number;
  organizationId?: number;
  organizationCode?: string;
  status?: string;
  uom?: string;
  lotNumber?: string;
  lotExpires?: DateField;
  currentOnHand?: number;
  countQty?: number;
  approvedVariance?: number;
  onHandVariance?: number;
  adjustValue?: number;
  errorMsg?: string;
  parentFlag?: string;
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
}
