# User, Org Group, and Responsible Group Migration Details (Spring Boot → Node.js)

## Overview

This document details the migration of the user, organization group, and responsible group modules from the Spring Boot (Java) backend to Node.js using node-oracledb. The migration strictly follows the .cursor rules and the unified migration plan, ensuring all endpoints, models, and business logic are preserved and mapped to the new stack.

---

## 1. Oracle Table-to-Model Mapping

### XXGS_WMS_USER (UserEO.java → wmsUser.ts)

- All fields from the Java entity are mapped 1:1 to the TypeScript interface `WmsUser`.
- Utility functions `fromDbRow` and `toDbRow` handle conversion between DB rows and TypeScript objects.

### XXGS_WMS_USER_ORG_GROUPS (UserOrgGroupEO.java → wmsUserOrgGroup.ts)

- All fields from the Java entity are mapped 1:1 to the TypeScript interface `WmsUserOrgGroup`.
- Utility functions `fromDbRow` and `toDbRow` handle conversion between DB rows and TypeScript objects.

### XXGS_WMS_USER_RESP_GROUPS (UserResponsibleGroupEO.java → wmsUserRespGroup.ts)

- All fields from the Java entity are mapped 1:1 to the TypeScript interface `WmsUserRespGroup`.
- Utility functions `fromDbRow` and `toDbRow` handle conversion between DB rows and TypeScript objects.

---

## 2. Repository & Service Layer

- Each table has a dedicated repository (`wmsUserRepo.ts`, `wmsUserOrgGroupRepo.ts`, `wmsUserRespGroupRepo.ts`) using node-oracledb for all DB access.
- All repositories provide CRUD and search methods, and use DB packages where required for business logic.
- Service classes (`wmsUserService.ts`, `wmsUserOrgGroupService.ts`, `wmsUserRespGroupService.ts`) orchestrate business logic and call the repositories.

---

## 3. Controller & Route Structure

### Endpoint Mapping (Spring Boot → Node.js)

#### XXGS_WMS_USER

- `POST /module/newuser/insertuser` → Bulk insert users (accepts array of users)
- `GET /module/newuser/getuser` → Get all users

#### XXGS_WMS_USER_ORG_GROUPS

- `POST /module/userorg/insertorg` → Bulk insert org groups (accepts array)
- `GET /module/userorg/getorg` → Get all org groups
- `POST /module/userorg/getuserorgaccess` → Get user org access (calls DB package, no body)
- `POST /module/userorg/getuserorgaccessbyid` → Get user org access by ID (body: object)
- `POST /module/userorg/getinvorg` → Get org inv name (calls DB package, no body)

#### XXGS_WMS_USER_RESP_GROUPS

- `POST /module/newuser/insertuserrepo` → Bulk insert responsible groups (accepts array)
- `GET /module/newuser/getuserrepo` → Get all responsible groups
- `POST /module/newuser/getuserreponame` → Get responsible group name (body: object)
- `POST /module/newuser/getuserrepoaccess` → Get responsible group access (body: object)
- `POST /module/newuser/getuserrep` → Get responsible group (body: object)
- `POST /module/newuser/getorg` → Get org name (calls DB package, no body)
- `POST /module/newuser/getuserrepactive` → Get active responsible groups (calls DB package, no body)

---

## 4. DB Package Usage

- Where the original Java service called an Oracle package (e.g., for access checks, org name, etc.), the Node.js service/repo uses `oraclePackageService.callPackage` with the correct package/procedure name and parameters.
- All package calls are parameterized and results are mapped to TypeScript objects or arrays as needed.

---

## 5. Error Handling & Validation

- All endpoints return appropriate HTTP status codes and error messages.
- Input validation is performed using Zod schemas where applicable.
- Errors from DB/package calls are caught and returned as JSON error responses.

---

## 6. Directory & File Structure

- Models: `backend/src/models/wmsUser.ts`, `wmsUserOrgGroup.ts`, `wmsUserRespGroup.ts`
- Repositories: `backend/src/repositories/wmsUserRepo.ts`, `wmsUserOrgGroupRepo.ts`, `wmsUserRespGroupRepo.ts`
- Services: `backend/src/services/wmsUserService.ts`, `wmsUserOrgGroupService.ts`, `wmsUserRespGroupService.ts`
- Controllers: `backend/src/controllers/wmsUserController.ts`, `wmsUserOrgGroupController.ts`, `wmsUserRespGroupController.ts`
- Routes: `backend/src/routes/userRouter.ts` (mounts all controllers at Spring Boot-compatible paths)

---

## 7. Testing & Verification

- All endpoints tested for correct request/response structure and DB integration.
- Bulk insert endpoints accept arrays and return inserted records.
- All GET and POST endpoints return data in the same structure as the original Java API.
- Error cases (invalid input, not found, DB errors) are handled and tested.

---

## 8. Migration Notes

- All business logic and DB access is now handled via node-oracledb and Oracle packages.
- No Sequelize or ORM is used; all queries and package calls are direct.
- The API is fully compatible with the original Spring Boot endpoints, supporting a seamless migration for clients.

---

## 9. Example Request/Response

### Insert Users

```
POST /module/newuser/insertuser
Body: [ { ...user fields... }, ... ]
Response: [ { ...inserted user... }, ... ]
```

### Get All Users

```
GET /module/newuser/getuser
Response: [ { ...user... }, ... ]
```

### Insert Org Groups

```
POST /module/userorg/insertorg
Body: [ { ...org group fields... }, ... ]
Response: [ { ...inserted org group... }, ... ]
```

### Get User Org Access By ID

```
POST /module/userorg/getuserorgaccessbyid
Body: { userId: 123 }
Response: { ...access info... }
```

---

## 10. Further Improvements

- Add OpenAPI/Swagger documentation for all endpoints.
- Add more granular validation and error codes.
- Implement RBAC and authentication middleware as per project standards.

---

**Migration completed on 21-07-2025. All endpoints, models, and business logic are now node-oracledb compliant and match the original Java API.**
