import { Router } from "express";
import { userController } from "../controllers/userController";
import { userRespGroupController } from "../controllers/userRespGroupController";
import { autoRegisterRoutes } from "../utils/autoRegisterRoutes";
import { registry } from "../utils/swaggerRegistry";
import {
  createUserSchema,
  createUserRespGroupSchema,
} from "../validators/userValidator";
import { apiResponseSchema } from "../validators/common";
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
        response: apiResponseSchema,
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
        response: apiResponseSchema,
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
        response: apiResponseSchema,
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
        response: apiResponseSchema,
      },
      summary: "Get all user responsible groups",
      tags: ["UserRespGroup"],
    },
    {
      method: "post",
      path: "/getuserreponame",
      handler: userRespGroupController.getUserRepoName,
      schemas: {
        response: apiResponseSchema,
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
        response: apiResponseSchema,
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
        response: apiResponseSchema,
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
        response: apiResponseSchema,
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
        response: apiResponseSchema,
      },
      summary: "Get active user rep",
      tags: ["UserRespGroup"],
      contentType: "application/json",
    },
  ],
  registry,
  END_POINTS.USER
);
