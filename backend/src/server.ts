import { OracleConnection } from "@config/database";
import { config } from "@config/env";
import { createApp } from "./app";
import { logger } from "./utils/logger";

const startServer = async (): Promise<void> => {
  try {
    // Initialize Oracle database connection
    await OracleConnection.initialize();
    logger.info("system", "Oracle database connection pool initialized");

    // Test database connection
    await OracleConnection.testConnection();
    logger.info("system", "Oracle database connection test successful");

    // Create Express app
    const app = createApp();

    // Start HTTP server
    const server = app.listen(config.PORT, () => {
      logger.info("system", `🚀 Server running on port ${config.PORT}`);
      logger.info("system", `📊 Environment: ${config.NODE_ENV}`);
      logger.info(
        "system",
        `🔗 Base URL: localhost:${config.PORT}${config.BASE_URL}`
      );
      console.info(`🚀 Server running on port ${config.PORT}`);
      console.info(`📊 Environment: ${config.NODE_ENV}`);
      console.info(`🔗 Base URL: localhost:${config.PORT}${config.BASE_URL}`);

      // Log Oracle connection pool stats
      OracleConnection.getPoolStatistics()
        .then((stats) => {
          if (stats) {
            logger.info("db", "Oracle connection pool statistics", stats);
          }
        })
        .catch((err) => {
          logger.error("db", "Error getting pool statistics", {
            error: err.message,
          });
          console.info("Error getting pool statistics:", err.message);
        });
    });

    // Graceful shutdown
    const gracefulShutdown = async (signal: string): Promise<void> => {
      logger.info(
        "system",
        `\n🛑 ${signal} received, shutting down gracefully...`
      );
      console.info(`\n🛑 ${signal} received, shutting down gracefully...`);

      server.close(async () => {
        logger.info("system", "📡 HTTP server closed");
        console.info("📡 HTTP server closed");

        try {
          await OracleConnection.closePool();
          logger.info("system", "✅ Shutdown complete");
          console.info("✅ Shutdown complete");
          process.exit(0);
        } catch (error: any) {
          logger.error("system", "❌ Error during shutdown", {
            error: error.message,
          });
          console.info("❌ Error during shutdown", error.message);
          process.exit(1);
        }
      });

      // Force close after 10 seconds
      setTimeout(() => {
        logger.error("system", "❌ Forced shutdown due to timeout");
        console.info("❌ Forced shutdown due to timeout");
        process.exit(1);
      }, 10000);
    };

    process.on("SIGTERM", () => gracefulShutdown("SIGTERM"));
    process.on("SIGINT", () => gracefulShutdown("SIGINT"));
  } catch (error: any) {
    logger.error("system", "❌ Failed to start server", {
      error: error.message,
    });
    console.info("❌ Failed to start server", error.message);
    process.exit(1);
  }
};

startServer();
