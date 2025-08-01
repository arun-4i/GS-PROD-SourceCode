import { UserEntity } from "../entities/user.entity";
import { userRepo } from "../repositories/userRepo";
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

export class UserService {
  // REFACTORED: Added error handling and common response patterns
  async createUser(input: UserEntity[]): Promise<APIResponse> {
    try {
      // Bulk insert: validate and insert each user
      const insertedUsers: UserEntity[] = [];
      for (const user of input) {
        const inserted = await userRepo.insertUser(user);
        insertedUsers.push(inserted);
      }
      return createSuccessResponse(insertedUsers);
    } catch (error) {
      return createErrorResponse(500, "Error creating users", error);
    }
  }

  async getAllUsers(): Promise<APIResponse> {
    try {
      const result = await userRepo.getAllUsers();
      return createSuccessResponse(result);
    } catch (error) {
      return createErrorResponse(500, "Error getting all users", error);
    }
  }
}

export const userService = new UserService();
