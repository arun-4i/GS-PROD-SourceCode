import { Request, Response } from "express";
import { asyncHandler } from "../utils/error";
import { userService } from "../services/userService";
import { createUserSchema } from "../validators/userValidator";
import { logger } from "../utils/logger";
import { UserEntity } from "../entities/user.entity";

export class UserController {
  // /insertuser (bulk insert users)
  public createUser = asyncHandler(async (req: Request, res: Response) => {
    const parsed = createUserSchema.array().safeParse(req.body);
    if (!parsed.success) {
      logger.warn("userAction", "Validation failed for createUser", {
        errors: parsed.error.errors,
      });
      return res.status(400).json({
        success: false,
        message: "Validation error",
        error: { code: "VALIDATION_ERROR", details: parsed.error.errors },
      });
    }
    // Map Zod input to UserEntity structure
    const users: UserEntity[] = parsed.data.map((input) => ({
      userName: input.email,
      fullName: `${input.firstName} ${input.lastName}`.trim(),
      emailAddress: input.email,
      userPassword: input.password,
      // Add other fields as needed
    }));
    const insertedUsers = await userService.createUser(users);
    return res.status(201).json({ success: true, data: insertedUsers });
  });

  // /getuser (get all users)
  public getAllUsers = asyncHandler(async (_req: Request, res: Response) => {
    const users = await userService.getAllUsers();
    return res.status(200).json({ success: true, data: users });
  });
}

export const userController = new UserController();
