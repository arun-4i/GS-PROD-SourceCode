import { Router } from "express";
import { moConfirmController } from "../controllers/moConfirmController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  insertMoConfirmSchema,
  insertPickConfirmSchema,
  insertMoPickConfirmSchema,
} from "../validators/moConfirmValidator";
import { apiResponseSchema } from "../validators/common";
import { END_POINTS } from "./end-points";

export const moConfirmRouter = Router();

autoRegisterRoutes(
  moConfirmRouter,
  [
    {
      method: "post",
      path: "/insertmo",
      handler: moConfirmController.insertMoConfirm,
      schemas: {
        body: insertMoConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "Insert MO confirmations with duplicate filtering",
      tags: ["MO Confirm"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getallmo",
      handler: moConfirmController.getAllMoConfirmations,
      schemas: {
        response: apiResponseSchema,
      },
      summary: "Get all MO confirmations",
      tags: ["MO Confirm"],
    },
    {
      method: "post",
      path: "/insertpick",
      handler: moConfirmController.insertPickConfirm,
      schemas: {
        body: insertPickConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "Insert pick confirmations with complex logic",
      tags: ["Pick Confirm"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getallpick",
      handler: moConfirmController.getAllPickConfirmations,
      schemas: {
        response: apiResponseSchema,
      },
      summary: "Get all pick confirmations",
      tags: ["Pick Confirm"],
    },
    {
      method: "post",
      path: "/insertmopick",
      handler: moConfirmController.insertMoPickConfirmations,
      schemas: {
        body: insertMoPickConfirmSchema,
        response: apiResponseSchema,
      },
      summary: "Combined MO & Pick confirmation with cross-validation",
      tags: ["Combined Operations"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.MO_CONFIRM
);
