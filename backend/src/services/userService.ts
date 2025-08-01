import { UserEntity } from "../entities/user.entity";
import { userRepo } from "../repositories/userRepo";

export class UserService {
  async createUser(input: UserEntity[]): Promise<UserEntity[]> {
    // Bulk insert: validate and insert each user
    const insertedUsers: UserEntity[] = [];
    for (const user of input) {
      const inserted = await userRepo.insertUser(user);
      insertedUsers.push(inserted);
    }
    return insertedUsers;
  }

  async getAllUsers(): Promise<UserEntity[]> {
    return userRepo.getAllUsers();
  }
}

export const userService = new UserService();
