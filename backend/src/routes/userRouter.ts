import { Router } from "express";
import { userController } from "../controllers/userController";
import { userRespGroupController } from "../controllers/userRespGroupController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  createUserSchema,
  createUserRespGroupSchema,
} from "../validators/userValidator";
import { z } from "zod";
import { END_POINTS } from "./end-points";

export const userRouter = Router();

autoRegisterRoutes(
  userRouter,
  [
    {
      method: "post",
      path: "/insertuser",
      handler: userController.createUser,
      schemas: {
        body: createUserSchema.array(),
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Bulk insert users",
      tags: ["User"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getuser",
      handler: userController.getAllUsers,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get all users",
      tags: ["User"],
    },
    // UserRespGroup routes
    {
      method: "post",
      path: "/insertuserrepo",
      handler: userRespGroupController.createUserRespGroup,
      schemas: {
        body: createUserRespGroupSchema.array(),
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Bulk insert user responsible groups",
      tags: ["UserRespGroup"],
      contentType: "application/json",
    },
    {
      method: "get",
      path: "/getuserrepo",
      handler: userRespGroupController.getAllUserRespGroups,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get all user responsible groups",
      tags: ["UserRespGroup"],
    },
    {
      method: "post",
      path: "/getuserreponame",
      handler: userRespGroupController.getUserRepoName,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get user responsibility name",
      tags: ["UserRespGroup"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getuserrepoaccess",
      handler: userRespGroupController.getUserRepoAccess,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get user repo access",
      tags: ["UserRespGroup"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getuserrep",
      handler: userRespGroupController.getUserRep,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get user rep",
      tags: ["UserRespGroup"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getorg",
      handler: userRespGroupController.getOrg,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get organization",
      tags: ["UserRespGroup"],
      contentType: "application/json",
    },
    {
      method: "post",
      path: "/getuserrepactive",
      handler: userRespGroupController.getUserRepActive,
      schemas: {
        response: z.object({
          success: z.literal(true),
          data: z.any(),
        }),
      },
      summary: "Get active user rep",
      tags: ["UserRespGroup"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.USER
);
