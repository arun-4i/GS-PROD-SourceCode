import { logger } from "./logger";
import type { Request, Response, NextFunction } from "express";
import { config } from "../config/env";

// BASE ERROR CLASS
export class AppError extends Error {
  public readonly statusCode: number;
  public readonly code: string;
  public readonly isOperational: boolean;
  public readonly details?: unknown;

  constructor(
    message: string,
    statusCode: number = 500,
    code: string = "INTERNAL_ERROR",
    isOperational: boolean = true,
    details?: unknown
  ) {
    super(message);
    this.name = this.constructor.name;
    this.statusCode = statusCode;
    this.code = code;
    this.isOperational = isOperational;
    this.details = details;
    Error.captureStackTrace(this, this.constructor);
  }
}

export class ValidationError extends AppError {
  constructor(message: string, field?: string, value?: unknown) {
    super(message, 400, "VALIDATION_ERROR", true, { field, value });
  }
}

export class AuthenticationError extends AppError {
  constructor(message: string = "Authentication failed") {
    super(message, 401, "AUTHENTICATION_ERROR", true);
  }
}

export class AuthorizationError extends AppError {
  constructor(message: string = "Access denied") {
    super(message, 403, "AUTHORIZATION_ERROR", true);
  }
}

export class NotFoundError extends AppError {
  constructor(resource: string = "Resource") {
    super(`${resource} not found`, 404, "NOT_FOUND_ERROR", true, { resource });
  }
}

export class ConflictError extends AppError {
  constructor(message: string, conflictField?: string) {
    super(message, 409, "CONFLICT_ERROR", true, { field: conflictField });
  }
}

export class DatabaseError extends AppError {
  constructor(
    message: string = "Database operation failed",
    originalError?: unknown
  ) {
    super(message, 500, "DATABASE_ERROR", true, {
      originalError: (originalError as Error)?.message ?? originalError,
      errorCode: (originalError as { code?: string })?.code,
      sqlState: (originalError as { sqlState?: string })?.sqlState,
    });
  }
}

export class ExternalServiceError extends AppError {
  constructor(service: string, message?: string, statusCode: number = 503) {
    super(
      message ?? `${service} service unavailable`,
      statusCode,
      "EXTERNAL_SERVICE_ERROR",
      true,
      { service }
    );
  }
}

export class RateLimitError extends AppError {
  constructor(message: string = "Rate limit exceeded") {
    super(message, 429, "RATE_LIMIT_ERROR", true);
  }
}

export class BusinessLogicError extends AppError {
  constructor(message: string, businessRule?: string) {
    super(message, 422, "BUSINESS_LOGIC_ERROR", true, { businessRule });
  }
}

interface ErrorResponse {
  success: false;
  error: string;
  code: string;
  requestId?: string;
  timestamp: string;
  path?: string;
  details?: unknown;
  stack?: string;
}

export const errorHandler = (
  error: AppError | Error,
  req: Request,
  res: Response
): void => {
  const requestId = (req as { requestId?: string }).requestId ?? "unknown";
  const isProduction = config.NODE_ENV === "production";

  let appError: AppError;
  if (error instanceof AppError) {
    appError = error;
  } else {
    appError = new AppError(error.message);
  }

  const logLevel = appError.statusCode >= 500 ? "error" : "warn";
  logger[logLevel]("api", "Error handled by middleware", {
    requestId,
    userId: (req as { user?: { userId?: string } }).user?.userId,
    errorName: appError.name,
    errorCode: appError.code,
    message: appError.message,
    statusCode: appError.statusCode,
    isOperational: appError.isOperational,
    path: req.originalUrl,
    method: req.method,
    ip: req.ip,
    userAgent: req.get("User-Agent"),
    stack: appError.stack,
    details: appError.details,
  });

  const errorResponse: ErrorResponse = {
    success: false,
    error: sanitizeErrorMessage(appError, isProduction),
    code: appError.code,
    requestId,
    timestamp: new Date().toISOString(),
    path: req.originalUrl,
  };

  if (!isProduction) {
    errorResponse.details = {
      originalMessage: appError.message,
      errorName: appError.name,
      isOperational: appError.isOperational,
      ...(typeof appError.details === "object" && appError.details !== null
        ? { errorDetails: appError.details }
        : {}),
    };
    if (appError.statusCode >= 500) {
      errorResponse.stack = appError.stack;
    }
  }

  res.status(appError.statusCode).json(errorResponse);
};

function sanitizeErrorMessage(error: AppError, isProduction: boolean): string {
  if (isProduction && !error.isOperational) {
    return "An unexpected error occurred. Please try again later.";
  }
  let message = error.message;
  message = message.replace(/\/[^\s]*\/[^\s]*/g, "[FILE_PATH]");
  if (isProduction && message.toLowerCase().includes("sql")) {
    return "Database operation failed";
  }
  return message;
}

export const notFoundHandler = (req: Request): void => {
  const error = new NotFoundError(`Route ${req.originalUrl}`);
  throw error;
};

export const asyncHandler = <T extends Request, U extends Response>(
  fn: (req: T, res: U, next: NextFunction) => Promise<unknown>
) => {
  return (req: T, res: U, next: NextFunction) => {
    Promise.resolve(fn(req, res, next)).catch(next);
  };
};

export const setupGlobalErrorHandlers = (): void => {
  process.on("unhandledRejection", (reason: unknown) => {
    logger.error("system", "Unhandled Promise Rejection", {
      reason: reason instanceof Error ? reason.message : String(reason),
      stack: reason instanceof Error ? reason.stack : undefined,
    });
    if (config.NODE_ENV === "production") {
      logger.error(
        "system",
        "Shutting down due to unhandled promise rejection"
      );
      process.exit(1);
    }
  });
  process.on("uncaughtException", (error: Error) => {
    logger.error("system", "Uncaught Exception", {
      error: error.message,
      stack: error.stack,
    });
    logger.error("system", "Shutting down due to uncaught exception");
    process.exit(1);
  });
};

export default {
  AppError,
  ValidationError,
  AuthenticationError,
  AuthorizationError,
  NotFoundError,
  ConflictError,
  DatabaseError,
  ExternalServiceError,
  RateLimitError,
  BusinessLogicError,
  errorHandler,
  notFoundHandler,
  asyncHandler,
  setupGlobalErrorHandlers,
};
