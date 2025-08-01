import { Request, Response } from "express";
import { asyncHandler } from "../utils/error";
import { userRespGroupService } from "../services/userRespGroupService";
import {
  createUserRespGroupSchema,
  userRespGroupQuerySchema,
} from "../validators/userValidator";
import { logger } from "../utils/logger";

export class UserRespGroupController {
  // /insertuserrepo (bulk insert responsible groups)
  public createUserRespGroup = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = createUserRespGroupSchema.array().safeParse(req.body);
      if (!parsed.success) {
        logger.warn("userAction", "Validation failed for createUserRespGroup", {
          errors: parsed.error.errors,
        });
        return res.status(400).json({
          success: false,
          message: "Validation error",
          error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
        });
      }
      const insertedGroups = await userRespGroupService.insertUserRepo(
        parsed.data
      );
      return res.status(201).json({ success: true, data: insertedGroups });
    }
  );

  // /getuserrepo (get all responsible groups)
  public getAllUserRespGroups = asyncHandler(
    async (_req: Request, res: Response) => {
      const groups = await userRespGroupService.getAllUserRepos();
      return res.status(200).json({ success: true, data: groups });
    }
  );

  // /getuserreponame (POST, body: object)
  public getUserRepoName = asyncHandler(async (req: Request, res: Response) => {
    const parsed = userRespGroupQuerySchema.safeParse(req.body);
    if (!parsed.success) {
      return res.status(400).json({
        success: false,
        message: "Validation error",
        error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
      });
    }
    const result = await userRespGroupService.getUserRepoName(parsed.data);
    return res.status(200).json({ success: true, data: result });
  });

  // /getuserrepoaccess (POST, body: object)
  public getUserRepoAccess = asyncHandler(
    async (req: Request, res: Response) => {
      const parsed = userRespGroupQuerySchema.safeParse(req.body);
      if (!parsed.success) {
        return res.status(400).json({
          success: false,
          message: "Validation error",
          error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
        });
      }
      const result = await userRespGroupService.getUserRepoAccess(parsed.data);
      return res.status(200).json({ success: true, data: result });
    }
  );

  // /getuserrep (POST, body: object)
  public getUserRep = asyncHandler(async (req: Request, res: Response) => {
    const parsed = userRespGroupQuerySchema.safeParse(req.body);
    if (!parsed.success) {
      return res.status(400).json({
        success: false,
        message: "Validation error",
        error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
      });
    }
    const result = await userRespGroupService.getUserRep(parsed.data);
    return res.status(200).json({ success: true, data: result });
  });

  // /getorg (POST, no body)
  public getOrg = asyncHandler(async (_req: Request, res: Response) => {
    const result = await userRespGroupService.getOrg();
    return res.status(200).json({ success: true, data: result });
  });

  // /getuserrepactive (POST, no body)
  public getUserRepActive = asyncHandler(
    async (_req: Request, res: Response) => {
      const result = await userRespGroupService.getUserRepActive();
      return res.status(200).json({ success: true, data: result });
    }
  );
}

export const userRespGroupController = new UserRespGroupController();
