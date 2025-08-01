# User & UserResponsibleGroup Route-wise Migration Plan (22-07-25)

## General Principles

- **Strict migration:** Only migrate what exists in Spring Boot. No new logic, no new packages.
- **Validation:**
  - Zod validation in controller (for request shape, types, required fields).
  - DB/package validation in repository (only if used in Java code).
- **DB Access:** Use node-oracledb only. No ORM/Sequelize.
- **Structure:** Each module (User, UserResponsibleGroup, etc.) gets its own folder grouping all related files.
- **Flow:** endpoint → index.ts → routes → controller → service → repository
- **Swagger:** To be implemented after all code is migrated.

---

## User & UserResponsibleGroup Module Migration Plan

**Folder:** `backend/src/user/`

### Entities

- `user.entity.ts` (maps to `XXGS_WMS_USER` table)
- `userResponsibleGroup.entity.ts` (maps to `XXGS_WMS_USER_RESP_GROUPS` table)

### Controllers

- `userController.ts` (handles user routes)
- `userRespGroupController.ts` (handles userrespgroup routes)

### Services

- `userService.ts`
- `userRespGroupService.ts`

### Repositories

- `userRepo.ts`
- `userRespGroupRepo.ts`

### Routes

- `routes/userRoutes.ts` (exports all user and userrespgroup routes)
- `index.ts` (exports all user module routes)

### Endpoints & Migration Steps

#### User Routes

##### 1. POST `/module/newuser/insertuser` (Insert User)

- **Spring Boot Reference:** `UserCO.insertUser`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userController.ts`, implement handler:
     - Use Zod to validate required fields (match DB NOT NULL fields).
     - Call `userService.insertUser(validatedData)`.
  3. In `userService.ts`, call `userRepo.insertUser(data)`.
  4. In `userRepo.ts`, use direct SQL to insert user:
     - Use Oracle sequence `XXGS_WMS_USER_ID_S.NEXTVAL` for `USER_ID`.
     - Map all fields as per Java entity and DB schema.
  5. Return result to controller, then to client.
- **Validation:** Zod (controller) for NOT NULL fields only.
- **DB Access:** Direct SQL (no package).

##### 2. GET `/module/newuser/getuser` (Get All Users)

- **Spring Boot Reference:** `UserCO.getuser`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userController.ts`, implement handler:
     - No request body validation needed.
     - Call `userService.getAllUsers()`.
  3. In `userService.ts`, call `userRepo.getAllUsers()`.
  4. In `userRepo.ts`, use direct SQL to fetch all users from `XXGS_WMS_USER`.
  5. Return result to controller, then to client.
- **Validation:** None (no input).
- **DB Access:** Direct SQL (no package).

#### UserResponsibleGroup Routes

##### 3. POST `/module/newuser/insertuserrepo` (Insert User Responsible Group)

- **Spring Boot Reference:** `UserResponsibleGroupCO.insertuserrepo`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userRespGroupController.ts`, implement handler:
     - Use Zod to validate required fields (match DB NOT NULL fields).
     - Call `userRespGroupService.insertUserRepo(validatedData)`.
  3. In `userRespGroupService.ts`, call `userRespGroupRepo.insertUserRepo(data)`.
  4. In `userRespGroupRepo.ts`, use direct SQL to insert user responsible group:
     - Use Oracle sequence `XXGS_USER_RESP_GROUP_ID_S.NEXTVAL` for `USER_RESP_GROUP_ID`.
     - Map all fields as per Java entity and DB schema.
  5. Return result to controller, then to client.
- **Validation:** Zod (controller) for NOT NULL fields only.
- **DB Access:** Direct SQL (no package).

##### 4. GET `/module/newuser/getuserrepo` (Get All User Responsible Groups)

- **Spring Boot Reference:** `UserResponsibleGroupCO.getUserRepo`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userRespGroupController.ts`, implement handler:
     - No request body validation needed.
     - Call `userRespGroupService.getAllUserRepos()`.
  3. In `userRespGroupService.ts`, call `userRespGroupRepo.getAllUserRepos()`.
  4. In `userRespGroupRepo.ts`, use direct SQL to fetch all user responsible groups from `XXGS_WMS_USER_RESP_GROUPS`.
  5. Return result to controller, then to client.
- **Validation:** None (no input).
- **DB Access:** Direct SQL (no package).

##### 5. POST `/module/newuser/getuserreponame` (Get User Responsibility Name)

- **Spring Boot Reference:** `UserResponsibleGroupCO.getuserreponame`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userRespGroupController.ts`, implement handler:
     - Use Zod to validate required fields.
     - Call `userRespGroupService.getUserRepoName(validatedData)`.
  3. In `userRespGroupService.ts`, call `userRespGroupRepo.getUserRepoName(data)`.
  4. In `userRespGroupRepo.ts`, use direct SQL to fetch responsibility name from `XXGS_WMS_RESPONSIBILITY_V`.
  5. Return result to controller, then to client.
- **Validation:** Zod (controller) for required fields.
- **DB Access:** Direct SQL (no package).

##### 6. POST `/module/newuser/getuserrepoaccess` (Get User Repo Access)

- **Spring Boot Reference:** `UserResponsibleGroupCO.getuserrepoaccess`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userRespGroupController.ts`, implement handler:
     - Use Zod to validate required fields.
     - Call `userRespGroupService.getUserRepoAccess(validatedData)`.
  3. In `userRespGroupService.ts`, call `userRespGroupRepo.getUserRepoAccess(data)`.
  4. In `userRespGroupRepo.ts`, use direct SQL to fetch repo access from `XXGS_WMS_USER_RESP_ACCESS_V`.
  5. Return result to controller, then to client.
- **Validation:** Zod (controller) for required fields.
- **DB Access:** Direct SQL (no package).

##### 7. POST `/module/newuser/getuserrep` (Get User Rep)

- **Spring Boot Reference:** `UserResponsibleGroupCO.getuserrep`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userRespGroupController.ts`, implement handler:
     - Use Zod to validate required fields.
     - Call `userRespGroupService.getUserRep(validatedData)`.
  3. In `userRespGroupService.ts`, call `userRespGroupRepo.getUserRep(data)`.
  4. In `userRespGroupRepo.ts`, use direct SQL to fetch user rep from `XXGS_WMS_ALL_USER_RESP_V`.
  5. Return result to controller, then to client.
- **Validation:** Zod (controller) for required fields.
- **DB Access:** Direct SQL (no package).

##### 8. POST `/module/newuser/getorg` (Get Organization)

- **Spring Boot Reference:** `UserResponsibleGroupCO.getorg`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userRespGroupController.ts`, implement handler:
     - No request body validation needed.
     - Call `userRespGroupService.getOrg()`.
  3. In `userRespGroupService.ts`, call `userRespGroupRepo.getOrg()`.
  4. In `userRespGroupRepo.ts`, use direct SQL to fetch organizations from `XXGS_INV_ORGANIZATIONS_V`.
  5. Return result to controller, then to client.
- **Validation:** None (no input).
- **DB Access:** Direct SQL (no package).

##### 9. POST `/module/newuser/getuserrepactive` (Get Active User Rep)

- **Spring Boot Reference:** `UserResponsibleGroupCO.getuserrepactive`
- **Migration Steps:**
  1. Define endpoint in `userRoutes.ts` and export in `index.ts`.
  2. In `userRespGroupController.ts`, implement handler:
     - No request body validation needed.
     - Call `userRespGroupService.getUserRepActive()`.
  3. In `userRespGroupService.ts`, call `userRespGroupRepo.getUserRepActive()`.
  4. In `userRespGroupRepo.ts`, use direct SQL to fetch active user reps from `XXGS_WMS_ALL_USER_RESP_V`.
  5. Return result to controller, then to client.
- **Validation:** None (no input).
- **DB Access:** Direct SQL (no package).

### Folder Structure

```
backend/src/user/
  ├─ entities/
  │    ├─ user.entity.ts
  │    └─ userResponsibleGroup.entity.ts
  ├─ controllers/
  │    ├─ userController.ts
  │    └─ userRespGroupController.ts
  ├─ services/
  │    ├─ userService.ts
  │    └─ userRespGroupService.ts
  ├─ repositories/
  │    ├─ userRepo.ts
  │    └─ userRespGroupRepo.ts
  ├─ routes/
  │    └─ userRoutes.ts
  └─ index.ts
```
