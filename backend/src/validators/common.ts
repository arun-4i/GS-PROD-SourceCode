/**
 * Common Validation Schemas
 * Shared schemas used across all route validators
 * Ensures consistency and eliminates duplication
 */

import { z } from "zod";

// =====================================================
// COMMON API RESPONSE SCHEMAS
// =====================================================

/**
 * Standard API Response Schema
 * Used for all API endpoints across the application
 * Matches Spring Boot APIResponse structure
 */
export const apiResponseSchema = z.object({
  data: z.any().optional(),
  status: z.number(),
  error: z.string().optional(),
  success: z.boolean().optional(),
});

// =====================================================
// COMMON BASE VALIDATION HELPERS
// =====================================================

/**
 * Optional string that can be empty - matching Java null handling
 */
export const optionalString = z
  .string()
  .optional()
  .transform((val) => val || "");

/**
 * Optional number for BigDecimal fields - matching Java null handling
 */
export const optionalNumber = z.number().optional().nullable();

/**
 * Optional date for Java Date fields
 */
export const optionalDate = z.date().optional().nullable();

/**
 * Required string validation
 */
export const requiredString = z.string().min(1, "Field is required");

/**
 * Required number validation
 */
export const requiredNumber = z.number();

// =====================================================
// COMMON ERROR RESPONSE UTILITIES
// =====================================================

/**
 * Standard error response creator
 * Single source of truth for error responses across controllers
 */
export function createErrorResponse(
  status: number,
  message: string,
  error?: unknown
): APIResponse {
  const errorMessage = error instanceof Error ? error.message : message;
  return {
    status,
    success: false,
    error: errorMessage,
  };
}

/**
 * Standard success response creator
 * Single source of truth for success responses across controllers
 */
export function createSuccessResponse<T>(
  data: T,
  status: number = 200
): APIResponse {
  return {
    data,
    status,
    success: true,
  };
}

/**
 * Validation error response creator
 * Single source of truth for validation error responses
 */
export function createValidationErrorResponse(message: string): APIResponse {
  return {
    status: 400,
    success: false,
    error: message,
  };
}

// Import the APIResponse type for consistency
import type { APIResponse } from "../entities/rmaConfirm.entity";
