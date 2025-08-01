/**
 * MoConfirm Router Configuration
 * Pure migration from Spring Boot MoConfirmCO.java endpoints
 * Uses autoRegisterRoutes pattern for automatic Swagger documentation
 */

import { Router } from "express";
import { moConfirmController } from "../controllers/moConfirmController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import { z } from "zod";
import { END_POINTS } from "./end-points";

export const moConfirmRouter = Router();

// =====================================================
// VALIDATION SCHEMAS
// =====================================================

// MoConfirm entity schema
const moConfirmEntitySchema = z.object({
  moid: z.number().optional(),
  transactionType: z.string().optional(),
  moNumber: z.number().optional(),
  moLineNumber: z.number().optional(),
  pickSlipNumber: z.number().optional(),
  itemId: z.number().optional(),
  uomCode: z.string().optional(),
  requiredQuantity: z.number().optional(),
  pickedQuantity: z.number().optional(),
  transferQuantity: z.number().optional(),
  sourceSubInventory: z.string().optional(),
  destinationSubInventory: z.string().optional(),
  sourceLocationId: z.number().optional(),
  destinationLocationId: z.number().optional(),
  personId: z.number().optional(),
  status: z.string().optional(),
  errorMessage: z.string().optional(),
  attributeCategory: z.string().optional(),
  attribute1: z.string().optional(),
  attribute2: z.string().optional(),
  attribute3: z.string().optional(),
  attribute4: z.string().optional(),
  attribute5: z.string().optional(),
  attribute6: z.string().optional(),
  attribute7: z.string().optional(),
  attribute8: z.string().optional(),
  attribute9: z.string().optional(),
  attribute10: z.string().optional(),
  attribute11: z.string().optional(),
  attribute12: z.string().optional(),
  attribute13: z.string().optional(),
  attribute14: z.string().optional(),
  attribute15: z.string().optional(),
  lastUpdateDate: z.date().optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.date().optional(),
  createdBy: z.number().optional(),
  lastUpdateLogin: z.number().optional(),
  orgId: z.number().optional(),
  orgCode: z.string().optional(),
  itemCode: z.string().optional(),
  orderNumber: z.number().optional(),
  deliveryDetailId: z.number().optional(),
  customerName: z.string().optional(),
  customerAccountId: z.number().optional(),
});

// PickConfirm entity schema
const pickConfirmEntitySchema = z.object({
  serialId: z.number().optional(),
  deliveryDetailId: z.number().optional(),
  fromSerialNumber: z.string().optional(),
  toserialNumber: z.string().optional(),
  quantity: z.number().optional(),
  status: z.string().optional(),
  transactionType: z.string().optional(),
  attributeCategory: z.string().optional(),
  attribute1: z.string().optional(),
  attribute2: z.string().optional(),
  attribute3: z.string().optional(),
  attribute4: z.string().optional(),
  attribute5: z.string().optional(),
  attribute6: z.string().optional(),
  attribute7: z.string().optional(),
  attribute8: z.string().optional(),
  attribute9: z.string().optional(),
  attribute10: z.string().optional(),
  attribute11: z.string().optional(),
  attribute12: z.string().optional(),
  attribute13: z.string().optional(),
  attribute14: z.string().optional(),
  attribute15: z.string().optional(),
  lastUpdateDate: z.date().optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.date().optional(),
  createdBy: z.number().optional(),
  lastUpdateLogin: z.number().optional(),
});

// MoPickConfirm wrapper schema
const moPickConfirmWrapperSchema = z.object({
  id: z.number().optional(),
  moConfirms: moConfirmEntitySchema.array(),
  pickConfirmations: pickConfirmEntitySchema.array(),
});

// Standard API response schema
const apiResponseSchema = z.object({
  data: z.any().optional(),
  status: z.number(),
  success: z.boolean().optional(),
  error: z.string().optional(),
});

// =====================================================
// ROUTE REGISTRATION
// =====================================================

autoRegisterRoutes(
  moConfirmRouter,
  [
    // =====================================================
    // MO CONFIRMATION ROUTES
    // =====================================================
    {
      method: "post",
      path: "/insertmo",
      handler: moConfirmController.insertMoConfirm,
      schemas: {
        body: moConfirmEntitySchema.array(),
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

    // =====================================================
    // PICK CONFIRMATION ROUTES
    // =====================================================
    {
      method: "post",
      path: "/insertpick",
      handler: moConfirmController.insertPickConfirm,
      schemas: {
        body: pickConfirmEntitySchema.array(),
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

    // =====================================================
    // ORACLE PACKAGE ROUTES
    // =====================================================
    {
      method: "post",
      path: "/insertMoQuickPickJson",
      handler: moConfirmController.insertMoQuickPickJson,
      schemas: {
        body: z.union([z.string(), z.any()]), // Accept raw JSON string or object
        response: apiResponseSchema,
      },
      summary: "JSON-based MO quick pick processing",
      tags: ["Oracle Package"],
      contentType: "application/json",
    },

    // =====================================================
    // COMBINED OPERATION ROUTES
    // =====================================================
    {
      method: "post",
      path: "/insertmopick",
      handler: moConfirmController.insertMoPickConfirmations,
      schemas: {
        body: moPickConfirmWrapperSchema,
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
