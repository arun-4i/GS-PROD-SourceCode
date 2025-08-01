import { Router } from "express";
import { rmaConfirmController } from "../controllers/rmaConfirmController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  insertRmaConfirmSchema,
  insertRmaConfirmMOSchema,
} from "../validators/rmaConfirmValidator";
import { apiResponseSchema } from "../validators/common";
import { END_POINTS } from "./end-points";

export const rmaConfirmRouter = Router();

autoRegisterRoutes(
  rmaConfirmRouter,
  [
    {
      method: "post",
      path: "/insertmo",
      handler: rmaConfirmController.insertRMAConfirm,
      schemas: {
        body: insertRmaConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "Insert RMA confirmations with duplicate filtering",
      tags: ["RMA Confirm"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getallmo",
      handler: rmaConfirmController.getBinTransfer,
      schemas: {
        response: apiResponseSchema,
      },
      summary: "Get all RMA confirmations",
      tags: ["RMA Confirm"],
    },
    {
      method: "post",
      path: "/rmainsertmo",
      handler: rmaConfirmController.insertRMAandMOconfirm,
      schemas: {
        body: insertRmaConfirmMOSchema,
        response: apiResponseSchema,
      },
      summary: "Combined RMA and MO confirmation with cross-validation",
      tags: ["RMA Confirm"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.RMA_CONFIRM
);
