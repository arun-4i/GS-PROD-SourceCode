import { config } from "@config/env";
import { CorsOptions } from "cors";

export const getCorsOptions = (): CorsOptions => {
  const isDevelopment = process.env.NODE_ENV === "development";

  return {
    origin: isDevelopment
      ? "*" // Accept all origins in development
      : (config.CORS_ORIGINS?.split(",") ?? ["http://localhost:3000"]), // Use specific origins in production
    credentials: true,
    methods: ["GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"],
    allowedHeaders: [
      "Origin",
      "X-Requested-With",
      "Content-Type",
      "Accept",
      "Authorization",
      "x-request-id",
      "x-client-version",
    ],
    exposedHeaders: ["x-request-id"],
  };
};
