import { UserRespGroupEntity } from "../entities/userRespGroup.entity";
import { userRespGroupRepo } from "../repositories/userRespGroupRepo";

export class UserRespGroupService {
  // Bulk insert responsible groups
  async insertUserRepo(
    groups: UserRespGroupEntity[]
  ): Promise<UserRespGroupEntity[]> {
    const created: UserRespGroupEntity[] = [];
    for (const group of groups) {
      created.push(await userRespGroupRepo.insertUserRepo(group));
    }
    return created;
  }

  // Get all responsible groups
  async getAllUserRepos(): Promise<UserRespGroupEntity[]> {
    return userRespGroupRepo.getAllUserRepos();
  }

  // Get user repository name
  async getUserRepoName(params: Record<string, unknown>): Promise<unknown> {
    return userRespGroupRepo.getUserRepoName(params);
  }

  // Get user repository access
  async getUserRepoAccess(params: Record<string, unknown>): Promise<unknown> {
    return userRespGroupRepo.getUserRepoAccess(params);
  }

  // Get user rep
  async getUserRep(params: Record<string, unknown>): Promise<unknown> {
    return userRespGroupRepo.getUserRep(params);
  }

  // Get organization
  async getOrg(): Promise<unknown> {
    return userRespGroupRepo.getOrg();
  }

  // Get user rep active
  async getUserRepActive(): Promise<unknown> {
    return userRespGroupRepo.getUserRepActive();
  }
}

export const userRespGroupService = new UserRespGroupService();
