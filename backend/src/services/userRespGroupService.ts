import { UserRespGroupEntity } from "../entities/userRespGroup.entity";
import { userRespGroupRepo } from "../repositories/userRespGroupRepo";
import {
  createErrorResponse,
  createSuccessResponse,
} from "../validators/common";

interface APIResponse {
  data?: unknown;
  status: number;
  error?: string;
  success?: boolean;
}

export class UserRespGroupService {
  // Bulk insert responsible groups
  // REFACTORED: Added error handling and common response patterns
  async insertUserRepo(groups: UserRespGroupEntity[]): Promise<APIResponse> {
    try {
      const created: UserRespGroupEntity[] = [];
      for (const group of groups) {
        created.push(await userRespGroupRepo.insertUserRepo(group));
      }
      return createSuccessResponse(created);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error inserting user responsible groups",
        error
      );
    }
  }

  // Get all responsible groups
  async getAllUserRepos(): Promise<APIResponse> {
    try {
      const result = await userRespGroupRepo.getAllUserRepos();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(
        500,
        "Error getting all user responsible groups",
        error
      );
    }
  }

  // Get user repository name
  async getUserRepoName(params: Record<string, unknown>): Promise<APIResponse> {
    try {
      const result = await userRespGroupRepo.getUserRepoName(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting user repo name", error);
    }
  }

  // Get user repository access
  async getUserRepoAccess(
    params: Record<string, unknown>
  ): Promise<APIResponse> {
    try {
      const result = await userRespGroupRepo.getUserRepoAccess(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting user repo access", error);
    }
  }

  // Get user rep
  async getUserRep(params: Record<string, unknown>): Promise<APIResponse> {
    try {
      const result = await userRespGroupRepo.getUserRep(params);
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting user rep", error);
    }
  }

  // Get organization
  async getOrg(): Promise<APIResponse> {
    try {
      const result = await userRespGroupRepo.getOrg();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting organization", error);
    }
  }

  // Get user rep active
  async getUserRepActive(): Promise<APIResponse> {
    try {
      const result = await userRespGroupRepo.getUserRepActive();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting user rep active", error);
    }
  }
}

export const userRespGroupService = new UserRespGroupService();
