/**
 * ReturnVendor Validation Schemas
 * Pure migration from Spring Boot ReturnToVendor module
 *
 * IMPORTANT: This is SEPARATE from existing Showroom RTV functionality
 * - Showroom RTV uses: showroompkg.GET_RTV_* procedures with different parameters
 * - ReturnToVendor uses: XXGS_MOB_UTIL_PKG.GET_RTV_* procedures with different parameters
 * - Different business logic, different validation requirements
 *
 * FOLLOWS existing Zod schema patterns from rmaReceiptValidator.ts and showroomValidator.ts
 * Uses SSOT utilities: optionalString from validators/common.ts
 */

import { z } from "zod";
import { optionalString, apiResponseSchema } from "./common";

// =====================================================
// RETURN TO VENDOR REQUEST VALIDATION SCHEMAS
// =====================================================

/**
 * RTV Request Number Validation Schema
 * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(P_INVENTORY_ORG_ID, P_REQUEST_NUM)
 * Route: POST /module/returnvendor/rtvrequestnumber
 * Spring Boot: ReturnVendorCO.getRTVRequestNumber
 */
const rtvRequestNumberSchema = z.object({
  P_INVENTORY_ORG_ID: optionalString,
  P_REQUEST_NUM: optionalString,
});

/**
 * RTV Item Detail Validation Schema
 * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(P_REQUEST_ID)
 * Route: POST /module/returnvendor/rtvitemdetail
 * Spring Boot: ReturnVendorCO.getRtvItemDetail
 */
const rtvItemDetailSchema = z.object({
  P_REQUEST_ID: optionalString,
});

/**
 * RTV Item Detail Cross Reference Validation Schema
 * Maps: XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR(P_REQUEST_ID)
 * Route: POST /module/returnvendor/rtvitemdetailcr
 * Spring Boot: ReturnVendorCO.getRtvItemDetailCr
 */
const rtvItemDetailCrSchema = z.object({
  P_REQUEST_ID: optionalString,
});

/**
 * RTV Item Code Validation Schema
 * Maps: XXGS_MOB_UTIL_PKG.get_rtv_item_code(p_inventory_org_id)
 * Route: POST /module/returnvendor/getrtvitemcode
 * Spring Boot: ReturnVendorCO.get_rtv_item_code
 */
const rtvItemCodeSchema = z.object({
  p_inventory_org_id: optionalString,
});

// =====================================================
// RESPONSE VALIDATION SCHEMAS
// =====================================================

/**
 * Standard API Response Schema (reused from common)
 * Used for all ReturnVendor endpoints
 */
export { apiResponseSchema };

/**
 * Oracle Package Response Schema (optional - for specific cursor validation)
 * Can be used for more specific Oracle response validation if needed
 */
const oraclePackageResponseSchema = z.object({
  data: z.any().optional(),
  P_RTV_RQST_NUM_RS: z.array(z.any()).optional(),
  P_RTV_ITEM_RS: z.array(z.any()).optional(),
  P_RTV_ITEM_CR_RS: z.array(z.any()).optional(),
  p_return_itrm_dtls: z.array(z.any()).optional(),
});

// =====================================================
// VALIDATION MIDDLEWARE (if needed)
// =====================================================

/**
 * Generic validation middleware factory
 * Creates validation middleware for any ReturnVendor schema
 * FOLLOWS pattern from showroomValidator.ts
 */
const createReturnVendorValidationMiddleware = (schema: z.ZodSchema) => {
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

export const validateRtvRequestNumber = createReturnVendorValidationMiddleware(
  rtvRequestNumberSchema
);

export const validateRtvItemDetail =
  createReturnVendorValidationMiddleware(rtvItemDetailSchema);

export const validateRtvItemDetailCr = createReturnVendorValidationMiddleware(
  rtvItemDetailCrSchema
);

export const validateRtvItemCode =
  createReturnVendorValidationMiddleware(rtvItemCodeSchema);

// =====================================================
// EXPORTS FOR ROUTER CONFIGURATION
// =====================================================

export {
  // Request schemas for autoRegisterRoutes
  rtvRequestNumberSchema,
  rtvItemDetailSchema,
  rtvItemDetailCrSchema,
  rtvItemCodeSchema,

  // Response schemas for documentation
  oraclePackageResponseSchema,
};
