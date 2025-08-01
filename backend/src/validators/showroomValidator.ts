/**
 * Showroom Validation Schemas
 * Pure migration from Spring Boot Showroom module
 * Zod schemas for all 27 Showroom routes with exact parameter validation
 */

import { z } from "zod";

// =====================================================
// BASE VALIDATION HELPERS
// =====================================================

// Optional string that can be empty - matching Java null handling
const optionalString = z
  .string()
  .optional()
  .transform((val) => val || "");

// Optional object for complex JSON inputs
const optionalObject = z.any().optional();

// =====================================================
// INVENTORY & ORGANIZATION VALIDATION SCHEMAS
// =====================================================

export const getInvOrgSchema = z.object({
  P_USER_ID: optionalString,
  P_ORGANIZATION_CODE: optionalString,
  P_ORGANIZATION_NAME: optionalString,
});

export const getPhysicalInventoriesSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
});

export const getPhyInvSubInvDtlsSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PHYSICAL_INVENTORY_ID: optionalString,
});

// =====================================================
// SALES ORDER VALIDATION SCHEMAS
// =====================================================

export const getSaleOrderNumSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_RESOURCE_ID: optionalString,
});

export const getSaleOrderDetailsSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_ORDER_NUM: optionalString,
  P_MO_NUM: optionalString,
  P_PICKSLIP_NUM: optionalString,
  P_RESOURCE_ID: optionalString,
});

export const getSaleOrderDetailsCrSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_ORDER_NUM: optionalString,
  P_MO_NUM: optionalString,
  P_PICKSLIP_NUM: optionalString,
  P_RESOURCE_ID: optionalString,
});

// =====================================================
// MOVE ORDER VALIDATION SCHEMAS
// =====================================================

export const getMoDetailsSchema = z.object({
  P_ORGANIZATION_ID: optionalString,
  P_MOVE_ORDER_NUM: optionalString,
  P_DELIVERY_NUM: optionalString,
});

export const getMoItemDetailsSchema = z.object({
  P_HEADER_ID: optionalString,
});

export const getMoItemCrossRefDtlsSchema = z.object({
  P_HEADER_ID: optionalString,
});

// =====================================================
// PURCHASE ORDER VALIDATION SCHEMAS
// =====================================================

export const getPoNumberSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PO_NUMBER: optionalString,
});

export const getReleaseNumberSchema = z.object({
  P_PO_HEADER_ID: optionalString,
});

export const getPoItemDtlsSchema = z.object({
  P_PO_HEADER_ID: optionalString,
  P_PO_RELEASE_ID: optionalString,
});

export const getPoItemCrossRefSchema = z.object({
  P_PO_HEADER_ID: optionalString,
  P_PO_RELEASE_ID: optionalString,
});

// =====================================================
// RTV (RETURN TO VENDOR) VALIDATION SCHEMAS
// =====================================================

export const getRTVRequestNumSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PO_NUMBER: optionalString,
  P_RECEIPT_NUM: optionalString,
  P_ITEM_CODE: optionalString,
});

export const getRTVPoNumSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PO_NUMBER: optionalString,
  P_RECEIPT_NUM: optionalString,
});

export const getRTVItemDtlsSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PO_NUMBER: optionalString,
  P_RECEIPT_NUM: optionalString,
  P_ITEM_CODE: optionalString,
});

export const getRTVItemDtlsCrSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PO_NUMBER: optionalString,
  P_RECEIPT_NUM: optionalString,
  P_ITEM_CODE: optionalString,
});

// =====================================================
// PHYSICAL INVENTORY VALIDATION SCHEMAS
// =====================================================

export const getPhyInvQueryDtlsSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PHYSICAL_INVENTORY: optionalString,
  P_SUBINVENTORY: optionalString,
});

export const getPhyInvCntItemDtlsSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PHYSICAL_INVENTORY: optionalString,
  P_SUBINVENTORY: optionalString,
});

export const getPhyInvCntItemCrSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_PHYSICAL_INVENTORY: optionalString,
});

// =====================================================
// IO (INTERNAL ORDER) VALIDATION SCHEMAS
// =====================================================

export const getIoShipmentNoSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_SHIPMENT_NUM: optionalString,
  P_DELIVERY_NUM: optionalString,
});

export const getIoRcptItemDtlsSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_SHIPMENT_NUM: optionalString,
});

export const getIoRcptItemDtlsCrSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_SHIPMENT_NUM: optionalString,
});

// =====================================================
// CONFIRMATION OPERATIONS VALIDATION SCHEMAS
// =====================================================

// Complex JSON processing validation - matching Spring Boot P_INPUT handling
export const confirmationBaseSchema = z.object({
  P_INPUT: optionalObject, // Accepts any object structure for JSON serialization
});

export const moConfirmSchema = confirmationBaseSchema;
export const ioConfirmSchema = confirmationBaseSchema;
export const poConfirmSchema = confirmationBaseSchema;
export const rtvConfirmSchema = confirmationBaseSchema;
export const stockConfirmSchema = confirmationBaseSchema;

// =====================================================
// VALIDATION SCHEMA MAPPING
// =====================================================

/**
 * Schema mapping for all Showroom routes
 * Maps endpoint names to their corresponding Zod schemas
 */
export const showroomSchemas = {
  // Inventory & Organization Operations
  getInvOrg: getInvOrgSchema,
  getPhysicalInventories: getPhysicalInventoriesSchema,
  getPhyInvSubInvDtls: getPhyInvSubInvDtlsSchema,

  // Sales Order Operations
  getSaleOrderNum: getSaleOrderNumSchema,
  getSaleOrderDetails: getSaleOrderDetailsSchema,
  getSaleOrderDetailsCr: getSaleOrderDetailsCrSchema,

  // Move Order Operations
  getMoDetails: getMoDetailsSchema,
  getMoItemDetails: getMoItemDetailsSchema,
  getMoItemCrossRefDtls: getMoItemCrossRefDtlsSchema,

  // Purchase Order Operations
  getPoNumber: getPoNumberSchema,
  getReleaseNumber: getReleaseNumberSchema,
  getPoItemDtls: getPoItemDtlsSchema,
  getPoItemCrossRef: getPoItemCrossRefSchema,

  // RTV Operations
  getRTVRequestNum: getRTVRequestNumSchema,
  getRTVPoNum: getRTVPoNumSchema,
  getRTVItemDtls: getRTVItemDtlsSchema,
  getRTVItemDtlsCr: getRTVItemDtlsCrSchema,

  // Physical Inventory Operations
  getPhyInvQueryDtls: getPhyInvQueryDtlsSchema,
  getPhyInvCntItemDtls: getPhyInvCntItemDtlsSchema,
  getPhyInvCntItemCr: getPhyInvCntItemCrSchema,

  // IO Operations
  getIoShipmentNo: getIoShipmentNoSchema,
  getIoRcptItemDtls: getIoRcptItemDtlsSchema,
  getIoRcptItemDtlsCr: getIoRcptItemDtlsCrSchema,

  // Confirmation Operations
  moConfirm: moConfirmSchema,
  ioConfirm: ioConfirmSchema,
  poConfirm: poConfirmSchema,
  rtvConfirm: rtvConfirmSchema,
  stockConfirm: stockConfirmSchema,
} as const;

// =====================================================
// VALIDATION MIDDLEWARE HELPER
// =====================================================

/**
 * Generic validation middleware factory
 * Creates validation middleware for any Showroom schema
 */
export const createValidationMiddleware = (schema: z.ZodSchema) => {
  return (req: any, res: any, next: any) => {
    try {
      const validatedData = schema.parse(req.body);
      req.body = validatedData;
      next();
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({
          success: false,
          status: 400,
          error: "Validation Error",
          details: error.errors,
        });
      }
      next(error);
    }
  };
};

// =====================================================
// ROUTE-SPECIFIC VALIDATION MIDDLEWARE
// =====================================================

export const validateGetInvOrg = createValidationMiddleware(getInvOrgSchema);
export const validateGetPhysicalInventories = createValidationMiddleware(
  getPhysicalInventoriesSchema
);
export const validateGetPhyInvSubInvDtls = createValidationMiddleware(
  getPhyInvSubInvDtlsSchema
);
export const validateGetSaleOrderNum = createValidationMiddleware(
  getSaleOrderNumSchema
);
export const validateGetSaleOrderDetails = createValidationMiddleware(
  getSaleOrderDetailsSchema
);
export const validateGetSaleOrderDetailsCr = createValidationMiddleware(
  getSaleOrderDetailsCrSchema
);
export const validateGetMoDetails =
  createValidationMiddleware(getMoDetailsSchema);
export const validateGetMoItemDetails = createValidationMiddleware(
  getMoItemDetailsSchema
);
export const validateGetMoItemCrossRefDtls = createValidationMiddleware(
  getMoItemCrossRefDtlsSchema
);
export const validateGetPoNumber =
  createValidationMiddleware(getPoNumberSchema);
export const validateGetReleaseNumber = createValidationMiddleware(
  getReleaseNumberSchema
);
export const validateGetPoItemDtls =
  createValidationMiddleware(getPoItemDtlsSchema);
export const validateGetPoItemCrossRef = createValidationMiddleware(
  getPoItemCrossRefSchema
);
export const validateGetRTVRequestNum = createValidationMiddleware(
  getRTVRequestNumSchema
);
export const validateGetRTVPoNum =
  createValidationMiddleware(getRTVPoNumSchema);
export const validateGetRTVItemDtls =
  createValidationMiddleware(getRTVItemDtlsSchema);
export const validateGetRTVItemDtlsCr = createValidationMiddleware(
  getRTVItemDtlsCrSchema
);
export const validateGetPhyInvQueryDtls = createValidationMiddleware(
  getPhyInvQueryDtlsSchema
);
export const validateGetPhyInvCntItemDtls = createValidationMiddleware(
  getPhyInvCntItemDtlsSchema
);
export const validateGetPhyInvCntItemCr = createValidationMiddleware(
  getPhyInvCntItemCrSchema
);
export const validateGetIoShipmentNo = createValidationMiddleware(
  getIoShipmentNoSchema
);
export const validateGetIoRcptItemDtls = createValidationMiddleware(
  getIoRcptItemDtlsSchema
);
export const validateGetIoRcptItemDtlsCr = createValidationMiddleware(
  getIoRcptItemDtlsCrSchema
);
export const validateMoConfirm = createValidationMiddleware(moConfirmSchema);
export const validateIoConfirm = createValidationMiddleware(ioConfirmSchema);
export const validatePoConfirm = createValidationMiddleware(poConfirmSchema);
export const validateRtvConfirm = createValidationMiddleware(rtvConfirmSchema);
export const validateStockConfirm =
  createValidationMiddleware(stockConfirmSchema);
