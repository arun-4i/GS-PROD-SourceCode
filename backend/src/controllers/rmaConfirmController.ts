/**
 * RMAConfirm Controller Layer
 * Pure migration from Spring Boot RMAConfirmCO.java
 * FOLLOWS existing controller patterns from showroomController.ts and moConfirmController.ts
 * All 3 route handlers with exact request/response patterns
 */

import { RmaConfirmService } from "../services/rmaConfirmService";
import {
  createControllerHandler,
  validateArrayBody,
  validateWrapperBody,
} from "../utils/controllerHelpers";

export class RmaConfirmController {
  private readonly rmaConfirmService: RmaConfirmService;

  constructor() {
    // FOLLOW existing constructor pattern
    this.rmaConfirmService = new RmaConfirmService();
  }

  // =====================================================
  // RMA CONFIRMATION OPERATIONS
  // =====================================================

  /**
   * Insert RMA confirmations with duplicate filtering
   * Route: POST /module/rma/confirm/insertmo
   * MIGRATES insertRMAConfirm() method from RMAConfirmCO.java
   * USES centralized controller helpers for consistent handling
   */
  insertRMAConfirm = createControllerHandler(
    (entities: unknown) =>
      this.rmaConfirmService.insertRMAConfirmRO(entities as any),
    (body) => validateArrayBody(body, "RMA confirmations")
  );

  /**
   * Combined RMA and MO confirmation processing
   * Route: POST /module/rma/confirm/rmainsertmo
   * MIGRATES insertRMAandMOconfirm() method from RMAConfirmCO.java
   * USES centralized controller helpers for consistent handling
   */
  insertRMAandMOconfirm = createControllerHandler(
    (wrapper: unknown) =>
      this.rmaConfirmService.insertRMAConfirmMO(wrapper as any),
    (body) => validateWrapperBody(body, ["rmaConfirm", "pickConfirm"])
  );

  /**
   * Get all RMA confirmations
   * Route: GET /module/rma/confirm/getallmo
   * MIGRATES getBinTransfer() method from RMAConfirmCO.java (maps to getallmo)
   * USES centralized GET handler for consistent handling
   */
  getBinTransfer = createControllerHandler(() =>
    this.rmaConfirmService.getRMAConfirmRO()
  );
}

// Export for router configuration
export const rmaConfirmController = new RmaConfirmController();
