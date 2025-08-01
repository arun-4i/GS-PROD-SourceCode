# UserOrgGroupEO Route-wise Migration Plan (22-07-25)

## General Principles

- **Strict migration:** Only migrate what exists in Spring Boot. No new logic, no new packages.
- **Validation:** None (strict migration, only what is present in Java code).
- **DB Access:** Use node-oracledb only. No ORM/Sequelize.
- **Structure:** All files for this module are grouped in a dedicated folder.
- **Flow:** endpoint → index.ts → routes → controller → service → repository

---

## UserOrgGroupEO Module Migration Plan

**Folder:** `backend/src/userOrgGroup/`

### Entities

- `userOrgGroup.entity.ts` (maps to `XXGS_WMS_USER_ORG_GROUPS` table)

### Controllers

- `userOrgGroupController.ts`

### Services

- `userOrgGroupService.ts`

### Repositories

- `userOrgGroupRepo.ts`

### Routes

- `routes/userOrgGroupRoutes.ts` (exports all userOrgGroup routes)
- `index.ts` (exports all userOrgGroup module routes)

### Endpoints & Migration Steps

#### 1. POST `/module/userorg/insertorg` (Insert User Org Group)

- **Spring Boot Reference:** `UserOrgGroupCO.insertUserOrg`
- **Migration Steps:**
  1. Define endpoint in `userOrgGroupRoutes.ts` and export in `index.ts`.
  2. In `userOrgGroupController.ts`, implement handler:
     - Call `userOrgGroupService.insertUserOrg(data)`.
  3. In `userOrgGroupService.ts`, call `userOrgGroupRepo.insertUserOrg(data)`.
  4. In `userOrgGroupRepo.ts`, use direct SQL to insert user org group:
     - Use Oracle sequence `XXGS_USER_ORG_GROUP_ID_S.NEXTVAL` for `USER_ORG_GROUP_ID`.
     - Map all fields as per Java entity and DB schema.
  5. Return result to controller, then to client.
- **DB Access:** Direct SQL (no package).

#### 2. GET `/module/userorg/getorg` (Get All User Org Groups)

- **Spring Boot Reference:** `UserOrgGroupCO.getuserOrg`
- **Migration Steps:**
  1. Define endpoint in `userOrgGroupRoutes.ts` and export in `index.ts`.
  2. In `userOrgGroupController.ts`, implement handler:
     - Call `userOrgGroupService.getAllUserOrgs()`.
  3. In `userOrgGroupService.ts`, call `userOrgGroupRepo.getAllUserOrgs()`.
  4. In `userOrgGroupRepo.ts`, use direct SQL to fetch all user org groups from `XXGS_WMS_USER_ORG_GROUPS`.
  5. Return result to controller, then to client.
- **DB Access:** Direct SQL (no package).

#### 3. POST `/module/userorg/getuserorgaccess` (Get User Org Access)

- **Spring Boot Reference:** `UserOrgGroupCO.getUserOrgAccess`
- **Migration Steps:**
  1. Define endpoint in `userOrgGroupRoutes.ts` and export in `index.ts`.
  2. In `userOrgGroupController.ts`, implement handler:
     - Call `userOrgGroupService.getUserOrgAccess()`.
  3. In `userOrgGroupService.ts`, call `userOrgGroupRepo.getUserOrgAccess()`.
  4. In `userOrgGroupRepo.ts`, use direct SQL to fetch user org access from `XXGS_WMS_USER_ORG_ACCESS_V`.
  5. Return result to controller, then to client.
- **DB Access:** Direct SQL (no package).

#### 4. POST `/module/userorg/getuserorgaccessbyid` (Get User Org Access By ID)

- **Spring Boot Reference:** `UserOrgGroupCO.getUserOrgAccessById`
- **Migration Steps:**
  1. Define endpoint in `userOrgGroupRoutes.ts` and export in `index.ts`.
  2. In `userOrgGroupController.ts`, implement handler:
     - Call `userOrgGroupService.getUserOrgAccessById(data)`.
  3. In `userOrgGroupService.ts`, call `userOrgGroupRepo.getUserOrgAccessById(data)`.
  4. In `userOrgGroupRepo.ts`, use direct SQL to fetch user org access by ID from `XXGS_WMS_USER_ORG_ACCESS_V`.
  5. Return result to controller, then to client.
- **DB Access:** Direct SQL (no package).

#### 5. POST `/module/userorg/getinvorg` (Get Inv Org)

- **Spring Boot Reference:** `UserOrgGroupCO.getInvOrg`
- **Migration Steps:**
  1. Define endpoint in `userOrgGroupRoutes.ts` and export in `index.ts`.
  2. In `userOrgGroupController.ts`, implement handler:
     - Call `userOrgGroupService.getInvOrg()`.
  3. In `userOrgGroupService.ts`, call `userOrgGroupRepo.getInvOrg()`.
  4. In `userOrgGroupRepo.ts`, use direct SQL to fetch inv org from `XXGS_INV_ORGANIZATIONS_V`.
  5. Return result to controller, then to client.
- **DB Access:** Direct SQL (no package).

### Folder Structure

```
backend/src/userOrgGroup/
  ├─ entities/
  │    └─ userOrgGroup.entity.ts
  ├─ controllers/
  │    └─ userOrgGroupController.ts
  ├─ services/
  │    └─ userOrgGroupService.ts
  ├─ repositories/
  │    └─ userOrgGroupRepo.ts
  ├─ routes/
  │    └─ userOrgGroupRoutes.ts
  └─ index.ts
```
