import { z } from "zod";

export const createUserSchema = z.object({
  email: z.string().email(),
  password: z.string().min(8),
  firstName: z.string().min(1),
  lastName: z.string().min(1),
});

export const updateNameSchema = z.object({
  firstName: z.string().min(3, "First name must be at least 3 characters long"),
  lastName: z.string().min(3, "Last name must be at least 3 characters long"),
});

export const updateUserSchema = z
  .object({
    email: z.string().email().optional(),
    password: z.string().min(8).optional(),
    firstName: z.string().min(1).optional(),
    lastName: z.string().min(1).optional(),
    isActive: z.boolean().optional(),
  })
  .strict();

// UserRespGroup validation schemas as per migration plan
export const createUserRespGroupSchema = z.object({
  userId: z.number().optional(),
  responsibilityId: z.number().optional(),
  description: z.string().optional(),
  startDate: z.union([z.date(), z.string()]).optional(),
  endDate: z.union([z.date(), z.string()]).optional(),
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
  lastUpdateDate: z.union([z.date(), z.string()]).optional(),
  lastUpdatedBy: z.number().optional(),
  creationDate: z.union([z.date(), z.string()]).optional(),
  createdBy: z.number().optional(),
  lastupdatelogin: z.number().optional(),
});

export const userRespGroupQuerySchema = z.object({
  USER_ID: z.string().optional(),
  RESPONSIBILITY_NAME: z.string().optional(),
});

export type CreateUserInput = z.infer<typeof createUserSchema>;
export type UpdateNameInput = z.infer<typeof updateNameSchema>;
export type UpdateUserInput = z.infer<typeof updateUserSchema>;
export type CreateUserRespGroupInput = z.infer<
  typeof createUserRespGroupSchema
>;
export type UserRespGroupQueryInput = z.infer<typeof userRespGroupQuerySchema>;
