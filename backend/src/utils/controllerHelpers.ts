/**
 * Controller Helper Utilities
 * Single source of truth for controller patterns
 * Eliminates duplicate request handling across controllers
 */

import { Request, Response, NextFunction } from "express";
import {
  createErrorResponse,
  createSuccessResponse,
  createValidationErrorResponse,
} from "../validators/common";
import type { APIResponse } from "../entities/rmaConfirm.entity";

/**
 * Higher-order function for handling async controller operations
 * Single source of truth for async error handling in controllers
 * @param operation - Async operation to execute
 * @returns Express handler function
 */
export function asyncHandler<T>(
  operation: (
    req: Request,
    res: Response,
    next: NextFunction
  ) => Promise<APIResponse>
) {
  return (req: Request, res: Response, next: NextFunction): void => {
    Promise.resolve(operation(req, res, next))
      .then((result: APIResponse) => {
        res.status(result.status).json(result);
      })
      .catch((error: any) => {
        console.error("Error in async handler:", error);
        const errorResponse = createErrorResponse(
          500,
          "Internal server error",
          error
        );
        res.status(errorResponse.status).json(errorResponse);
      });
  };
}

/**
 * Validates request body is a non-empty array
 * Single source of truth for array validation
 * @param body - Request body to validate
 * @param entityName - Name of entity for error messages
 * @returns Validation result
 */
export function validateArrayBody(
  body: any,
  entityName: string
): { isValid: boolean; error?: APIResponse } {
  if (!body || !Array.isArray(body)) {
    return {
      isValid: false,
      error: createValidationErrorResponse(
        `Invalid request body. Expected array of ${entityName}.`
      ),
    };
  }

  if (body.length === 0) {
    return {
      isValid: false,
      error: createValidationErrorResponse(
        `Empty array provided. At least one ${entityName} is required.`
      ),
    };
  }

  return { isValid: true };
}

/**
 * Validates wrapper object structure
 * Single source of truth for wrapper validation
 * @param wrapper - Wrapper object to validate
 * @param requiredFields - Array of required field names
 * @returns Validation result
 */
export function validateWrapperBody(
  wrapper: any,
  requiredFields: string[]
): { isValid: boolean; error?: APIResponse } {
  if (!wrapper || typeof wrapper !== "object") {
    return {
      isValid: false,
      error: createValidationErrorResponse(
        "Invalid request body. Expected wrapper object."
      ),
    };
  }

  for (const field of requiredFields) {
    if (!wrapper[field] || !Array.isArray(wrapper[field])) {
      return {
        isValid: false,
        error: createValidationErrorResponse(
          `Invalid wrapper structure. Missing or invalid ${field} array.`
        ),
      };
    }
  }

  return { isValid: true };
}

/**
 * Standard controller handler wrapper
 * Single source of truth for controller structure
 * @param serviceMethod - Service method to call
 * @param validationFn - Optional validation function
 * @returns Express handler function
 */
export function createControllerHandler<TInput, TOutput>(
  serviceMethod: (input: TInput) => Promise<APIResponse>,
  validationFn?: (body: any) => { isValid: boolean; error?: APIResponse }
) {
  return (req: Request, res: Response, next: NextFunction): void => {
    try {
      // Validate if validation function provided
      if (validationFn) {
        const validation = validationFn(req.body);
        if (!validation.isValid && validation.error) {
          res.status(validation.error.status).json(validation.error);
          return;
        }
      }

      // Execute service method
      serviceMethod(req.body as TInput)
        .then((result: APIResponse) => {
          res.status(result.status).json(result);
        })
        .catch((error: any) => {
          console.error("Error in controller handler:", error);
          const errorResponse = createErrorResponse(
            500,
            "Internal server error",
            error
          );
          res.status(errorResponse.status).json(errorResponse);
        });
    } catch (error: any) {
      console.error("Error in controller handler sync:", error);
      const errorResponse = createErrorResponse(
        500,
        "Internal server error",
        error
      );
      res.status(errorResponse.status).json(errorResponse);
    }
  };
}

/**
 * Standard GET handler for no-parameter endpoints
 * Single source of truth for simple GET operations
 * @param serviceMethod - Service method to call
 * @returns Express handler function
 */
export function createGetHandler(serviceMethod: () => Promise<APIResponse>) {
  return (req: Request, res: Response, next: NextFunction): void => {
    serviceMethod()
      .then((result: APIResponse) => {
        res.status(result.status).json(result);
      })
      .catch((error: any) => {
        console.error("Error in GET handler:", error);
        const errorResponse = createErrorResponse(
          500,
          "Internal server error",
          error
        );
        res.status(errorResponse.status).json(errorResponse);
      });
  };
}
