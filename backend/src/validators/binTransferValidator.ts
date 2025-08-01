import { z } from "zod";

// Oracle Package Parameters Schema (for Map<String, Object> requests)
export const oraclePackageParamsSchema = z.record(z.any()).refine(
  (data) => typeof data === "object" && data !== null,
  { message: "Must be a valid object with key-value pairs" }
);

// BinTransferTrackHdr Schema
export const binTransferTrackHdrCreateSchema = z.object({
  ref: z.string().optional(),
  invOrg: z.string().optional(),
  invOrgId: z.number().optional(),
  subInvName: z.string().optional(),
  attributeCategory: z.string().optional(),
  attribute1: z.string().optional(),
  attribute2: z.string().optional(),
  attribute3: z.string().optional(),
  attribute4: z.string().optional(),
  attribute5: z.string().optional(),
  attribute6: z.string().optional(),
  attribute7: z.string().optional(),
  attribute8: z.string().optional(),
  attribute9: z.string().optional(),
  attribute10: z.string().optional(),
  lastUpdateDate: z.union([z.date(), z.string()]).optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.union([z.date(), z.string()]).optional(),
  createdBy: z.number().optional(),
  lastUpdateLogin: z.number().optional(),
});

// BinTransferTrackPick Schema
export const binTransferTrackPickCreateSchema = z.object({
  headerId: z.number().optional(),
  invOrg: z.string().optional(),
  invOrgId: z.number().optional(),
  inventoryItemId: z.number().optional(),
  itemCode: z.string().optional(),
  description: z.string().optional(),
  uom: z.string().optional(),
  pickedQty: z.number().optional(),
  pickedSubinv: z.string().optional(),
  pickedLocator: z.string().optional(),
  pickedlocatorId: z.string().optional(),
  type: z.string().optional(),
  attributeCategory: z.string().optional(),
  attribute1: z.string().optional(),
  attribute2: z.string().optional(),
  attribute3: z.string().optional(),
  attribute4: z.string().optional(),
  attribute5: z.string().optional(),
  attribute6: z.string().optional(),
  attribute7: z.string().optional(),
  attribute8: z.string().optional(),
  attribute9: z.string().optional(),
  attribute10: z.string().optional(),
  lastUpdateDate: z.union([z.date(), z.string()]).optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.union([z.date(), z.string()]).optional(),
  createdBy: z.number().optional(),
  lastUpdateLogin: z.number().optional(),
});

// BinTransferTrackDrop Schema
export const binTransferTrackDropCreateSchema = z.object({
  lineId: z.number().optional(),
  inventoryItemId: z.number().optional(),
  itemCode: z.string().optional(),
  description: z.string().optional(),
  uom: z.string().optional(),
  dropQty: z.number().optional(),
  dropSubinv: z.string().optional(),
  dropLocator: z.string().optional(),
  dropLocatorId: z.string().optional(),
  type: z.string().optional(),
  attributeCategory: z.string().optional(),
  attribute1: z.string().optional(),
  attribute2: z.string().optional(),
  attribute3: z.string().optional(),
  attribute4: z.string().optional(),
  attribute5: z.string().optional(),
  attribute6: z.string().optional(),
  attribute7: z.string().optional(),
  attribute8: z.string().optional(),
  attribute9: z.string().optional(),
  attribute10: z.string().optional(),
  lastUpdateDate: z.union([z.date(), z.string()]).optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.union([z.date(), z.string()]).optional(),
  createdBy: z.number().optional(),
  lastUpdateLogin: z.number().optional(),
});

// QuickDrop Schema (composite entity)
export const quickDropCreateSchema = z.object({
  id: z.number().optional(),
  header: binTransferTrackHdrCreateSchema.array().optional(),
  pick: binTransferTrackPickCreateSchema.array().optional(),
  drop: binTransferTrackDropCreateSchema.array().optional(),
});

// IoConLot Schema
export const ioConLotCreateSchema = z.object({
  transactionType: z.string().optional(),
  lotNumber: z.string().optional(),
  lotExpiryDate: z.union([z.date(), z.string()]).optional(),
  lotQuantity: z.number().optional(),
  attributeCategory: z.string().optional(),
  attribute1: z.string().optional(),
  attribute2: z.string().optional(),
  attribute3: z.string().optional(),
  attribute4: z.string().optional(),
  attribute5: z.string().optional(),
  attribute6: z.string().optional(),
  attribute7: z.string().optional(),
  attribute8: z.string().optional(),
  attribute9: z.string().optional(),
  attribute10: z.string().optional(),
  attribute11: z.string().optional(),
  attribute12: z.string().optional(),
  attribute13: z.string().optional(),
  attribute14: z.string().optional(),
  attribute15: z.string().optional(),
  lastUpdateDate: z.union([z.date(), z.string()]).optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.union([z.date(), z.string()]).optional(),
  createdBy: z.number().optional(),
  lastUpdateLogin: z.number().optional(),
  transactionTypeId: z.number().optional(),
});

// InvCountConfirm Schema
export const invCountConfirmCreateSchema = z.object({
  physicalInvName: z.string().optional(),
  physicalInvId: z.number().optional(),
  subInventory: z.string().optional(),
  locators: z.string().optional(),
  locatorsId: z.number().optional(),
  tagNumber: z.string().optional(),
  tagId: z.number().optional(),
  itemCode: z.string().optional(),
  itemId: z.number().optional(),
  organizationId: z.number().optional(),
  organizationCode: z.string().optional(),
  status: z.string().optional(),
  uom: z.string().optional(),
  lotNumber: z.string().optional(),
  lotExpires: z.union([z.date(), z.string()]).optional(),
  currentOnHand: z.number().optional(),
  countQty: z.number().optional(),
  approvedVariance: z.number().optional(),
  onHandVariance: z.number().optional(),
  adjustValue: z.number().optional(),
  errorMsg: z.string().optional(),
  parentFlag: z.string().optional(),
  attributeCategory: z.string().optional(),
  attribute1: z.string().optional(),
  attribute2: z.string().optional(),
  attribute3: z.string().optional(),
  attribute4: z.string().optional(),
  attribute5: z.string().optional(),
  attribute6: z.string().optional(),
  attribute7: z.string().optional(),
  attribute8: z.string().optional(),
  attribute9: z.string().optional(),
  attribute10: z.string().optional(),
  attribute11: z.string().optional(),
  attribute12: z.string().optional(),
  attribute13: z.string().optional(),
  attribute14: z.string().optional(),
  attribute15: z.string().optional(),
  lastUpdateDate: z.union([z.date(), z.string()]).optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.union([z.date(), z.string()]).optional(),
  createdBy: z.number().optional(),
  lastUpdateLogin: z.number().optional(),
});

// Export TypeScript types
export type OraclePackageParamsInput = z.infer<typeof oraclePackageParamsSchema>;
export type BinTransferTrackHdrCreateInput = z.infer<typeof binTransferTrackHdrCreateSchema>;
export type BinTransferTrackPickCreateInput = z.infer<typeof binTransferTrackPickCreateSchema>;
export type BinTransferTrackDropCreateInput = z.infer<typeof binTransferTrackDropCreateSchema>;
export type QuickDropCreateInput = z.infer<typeof quickDropCreateSchema>;
export type IoConLotCreateInput = z.infer<typeof ioConLotCreateSchema>;
export type InvCountConfirmCreateInput = z.infer<typeof invCountConfirmCreateSchema>;