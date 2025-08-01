# Spring Boot Project Routes Analysis Task

## Overview

Complete analysis of all routes in the Spring Boot project organized by folder structure with individual and total route counts.

## Route Analysis by Folder

### 1. Controller (Main Controllers)

**Location:** `src/main/java/com/mobile/integration/grandstores/Controller/`
**Routes Found:**

1. `/module/temp` - GET
2. `/module/authentication` - POST
3. `/module/authen` - POST
4. `/module/sample` - GET
5. `/module/checkdbconnection` - GET
6. `/module/pilot/detail` - POST
7. `/module/pilot/getdetail` - GET
8. `/module/pi/count` - POST
9. `/module/pi/get` - GET
10. `/module/pi/docnumber` - POST
11. `/module/pi/getTotalQty` - POST
12. `/module/pi/getsearchdetail` - POST
13. `/module/poconfirm/insert` - POST
14. `/module/poconfirm/getconfirm` - GET
15. `/module/po/ponumber` - POST
16. `/module/po/poreleasenumber` - POST
17. `/module/po/poitemdtl` - POST
18. `/module/po/poitemcrossref` - POST
19. `/module/po/porcptgen` - POST
20. `/module/updatepassword` - POST
21. `/module/getitemdepartment` - POST
22. `/module/proforma/invoice/pisupplier` - POST
23. `/module/proforma/invoice/shipmentref` - POST
24. `/module/proforma/invoice/pinumber` - POST
25. `/module/proforma/invoice/piitemdetail` - POST
26. `/module/proforma/invoice/piitemcross` - POST
27. `/module/po/lotInsertDetail` - POST
28. `/module/po/getpolotDetail` - GET

**Route Count: 28**

### 2. BinTransfer

**Location:** `src/main/java/com/mobile/integration/grandstores/BinTransfer/BinTransferController/`
**Routes Found:**

1. `/module/bt/getitemdetail` - POST
2. `/module/bt/getitemcrossref` - POST
3. `/module/bt/getconlotdetail` - POST
4. `/module/bt/insertconlotdetail` - POST
5. `/module/bt/getinvcountconfirm` - GET
6. `/module/bt/insertinvcountconfirm` - POST

**Route Count: 6**

### 3. BinTransferTrack

**Location:** `src/main/java/com/mobile/integration/grandstores/BinTransferTrack/BinTransferTrackController/`
**Routes Found:**

1. `/module/bintransfer/insert/bintrasfer/hdr` - POST
2. `/module/bintransfer/getall/bintrasfer/hdr` - GET
3. `/module/bintransfer/insert/bintrasfer/lns` - POST
4. `/module/bintransfer/getall/bintrasfer/lns` - GET
5. `/module/bintransfer/insert/bintrasfer/drop` - POST
6. `/module/bintransfer/getall/bintrasfer/drop` - GET
7. `/module/bintransfer/getrefhdr` - POST
8. `/module/bintransfer/drop/hdr` - POST
9. `/module/bintransfer/drop/dtl` - POST
10. `/module/bintransfer/bin/number` - POST
11. `/module/bintransfer/insert/bintrasfer/quickdrop` - POST

**Route Count: 11**

### 4. User

**Location:** `src/main/java/com/mobile/integration/grandstores/User/Controller/`
**Routes Found:**

1. `/module/newuser/insertuser` - POST
2. `/module/newuser/getuser` - GET
3. `/module/userorg/insertorg` - POST
4. `/module/userorg/getorg` - GET
5. `/module/userorg/getuserorgaccess` - POST
6. `/module/userorg/getuserorgaccessbyid` - POST
7. `/module/userorg/getinvorg` - POST
8. `/module/newuser/insertuserrepo` - POST
9. `/module/newuser/getuserrepo` - GET
10. `/module/newuser/getuserreponame` - POST
11. `/module/newuser/getuserrepoaccess` - POST
12. `/module/newuser/getuserrep` - POST
13. `/module/newuser/getorg` - POST
14. `/module/newuser/getuserrepactive` - POST

**Route Count: 14**

### 5. Showroom

**Location:** `src/main/java/com/mobile/integration/grandstores/Showroom/ShowroomController/`
**Routes Found:**

1. `/module/showroom/getInvOrg` - POST@
2. `/module/showroom/getSaleOrderNum` - POST@
3. `/module/showroom/getSaleOrderDetails` - POST@
4. `/module/showroom/getSaleOrderDetailsCr` - POST@
5. `/module/showroom/getMoDetails` - POST@
6. `/module/showroom/getMoItemDetails` - POST@
7. `/module/showroom/getMoItemCrossRefDtls` - POST@
8. `/module/showroom/getPoNumber` - POST@
9. `/module/showroom/getPoItemDtls` - POST@
10. `/module/showroom/getPoItemCrossRef` - POST@
11. `/module/showroom/getRTVRequestNum` - POST@
12. `/module/showroom/getRTVPoNum` - POST@
13. `/module/showroom/getRTVItemDtls` - POST@
14. `/module/showroom/getRTVItemDtlsCr` - POST@
15. `/module/showroom/getPhyInvQueryDtls` - POST@
16. `/module/showroom/getPhyInvCntItemDtls` - POST@
17. `/module/showroom/getPhyInvCntItemCr` - POST@
18. `/module/showroom/getPhysicalInventories` - POST@
19. `/module/showroom/getPhyInvSubInvDtls` - POST$
20. `/module/showroom/getIoShipmentNo` - POST@
21. `/module/showroom/getIoRcptItemDtls` - POST@
22. `/module/showroom/getIoRcptItemDtlsCr` - POST@
23. `/module/showroom/moConfirm` - POST@
24. `/module/showroom/ioConfirm` - POST@
25. `/module/showroom/poConfirm` - POST@
26. `/module/showroom/rtvConfirm` - POST@
27. `/module/showroom/stockConfirm` - POST@

**Route Count: 27**

### 6. MoConfirm

**Location:** `src/main/java/com/mobile/integration/grandstores/MoConfirm/MoConfirmController/`
**Routes Found:**

1. `/module/mo/confirm/insertmo` - POST@
2. `/module/mo/confirm/getallmo` - GET@
3. `/module/mo/confirm/insertpick` - POST@
4. `/module/mo/confirm/insertMoQuickPickJson` - POST@
5. `/module/mo/confirm/insertmopick` - POST@
6. `/module/mo/confirm/getallpick` - GET@

**Route Count: 6**

### 7. RMAConfirm

**Location:** `src/main/java/com/mobile/integration/grandstores/RMAConfirm/RMAConfirmController/`
**Routes Found:**

1. `/module/rma/confirm/insertmo` - POST@
2. `/module/rma/confirm/rmainsertmo` - POST@
3. `/module/rma/confirm/getallmo` - GET@

**Route Count: 3**

### 8. RMAReceipt

**Location:** `src/main/java/com/mobile/integration/grandstores/RMAReceipt/RMAReceiptController/`
**Routes Found:**

1. `/module/rmadelivery/getrmadelreceiptnum` - POST@
2. `/module/rmadelivery/getrmadelordernum` - POST@
3. `/module/rmadelivery/getrmadelitemdtl` - POST@
4. `/module/rmadelivery/getrmadelitemcross` - POST@
5. `/module/rmareceipt/getrmadetail` - POST@
6. `/module/rmareceipt/rmacustdetails` - POST@
7. `/module/rmareceipt/rmaitemdetail` - POST@
8. `/module/rmareceipt/rmaitemcrossRef` - POST@
9. `/module/rmareceipt/getbundle` - POST@

**Route Count: 9**

### 9. ReturnToVendor

**Location:** `src/main/java/com/mobile/integration/grandstores/ReturnToVendor/ReturntoVendorController/`
**Routes Found:**

1. `/module/returnvendor/rtvrequestnumber` - POST
2. `/module/returnvendor/rtvitemdetail` - POST
3. `/module/returnvendor/rtvitemdetailcr` - POST
4. `/module/returnvendor/getrtvitemcode` - POST

**Route Count: 4**

### 10. RTVConfirmation

**Location:** `src/main/java/com/mobile/integration/grandstores/RTVConfirmation/RTVConfirmationController/`
**Routes Found:**

1. `/module/rtvconfirmation/updateRtvLines` - POST

**Route Count: 1**

### 11. RTVProcess

**Location:** `src/main/java/com/mobile/integration/grandstores/RTVProcess/RTVProcessCO/`
**Routes Found:**

1. `/module/rtvprocess/getrtvrequestnum` - POST
2. `/module/rtvprocess/getrtcdtls` - POST
3. `/module/rtvprocess/getrtcitemdtls` - POST

**Route Count: 3**

### 12. PODelivery

**Location:** `src/main/java/com/mobile/integration/grandstores/PODelivery/PODelController/`
**Routes Found:**

1. `/module/podelivery/receiptnum` - POST
2. `/module/podelivery/shipmentnum` - POST
3. `/module/podelivery/ponumber` - POST
4. `/module/podelivery/podeliveryitemdtl` - POST
5. `/module/podelivery/podeliveryitemdtlcr` - POST
6. `/module/podelivery/poreleasenumber` - POST
7. `/module/podelivery/ponumberrcpt` - POST

**Route Count: 7**

### 13. PickOrderNumber

**Location:** `src/main/java/com/mobile/integration/grandstores/PickOrderNumber/PickOrderController/`
**Routes Found:**

1. `/module/pickorder/updatedeliverydtl` - POST
2. `/module/pickorder/pickordernumber` - POST
3. `/module/pickorder/pickmoveorder` - POST
4. `/module/pickorder/pickslipnum` - POST
5. `/module/pickorder/pickorderdtl` - POST
6. `/module/pickorder/pickorderdtlcr` - POST

**Route Count: 6**

### 14. PhysicalCounting

**Location:** `src/main/java/com/mobile/integration/grandstores/PhysicalCounting/PhysicalCountingController/`
**Routes Found:**

1. `/module/physicalcounting/getphysicalinventories` - POST
2. `/module/physicalcounting/getphyinvsubinvdtl` - POST
3. `/module/physicalcounting/getphyinvquerydtl` - POST
4. `/module/physicalcounting/getphyinvcntitemdtls` - POST
5. `/module/physicalcounting/getphyinvcntitemcr` - POST

**Route Count: 5**

### 15. UpdateDescription

**Location:** `src/main/java/com/mobile/integration/grandstores/UpdateDescription/UpdateDescriptionController/`
**Routes Found:**

1. `/module/updatedescription/getitemdtl` - POST
2. `/module/updatedescription/getitemcrosref` - POST
3. `/module/updatedescription/insertItemConfig` - POST
4. `/module/updatedescription/getitemconfig` - GET

**Route Count: 4**

### 16. StockUpdate

**Location:** `src/main/java/com/mobile/integration/grandstores/StockUpdate/StockUpdateCO/`
**Routes Found:**

1. `/module/stockupdate/binstockupdate` - POST

**Route Count: 1**

### 17. SpotCheckDirectSync

**Location:** `src/main/java/com/mobile/integration/grandstores/SpotCheckDirectSync/SpotCheckDirectSyncCO/`
**Routes Found:**

1. `/module/spotCheckDirectSync/spotcheckdirectsync` - POST

**Route Count: 1**

### 18. MoveOrderAPI

**Location:** `src/main/java/com/mobile/integration/grandstores/MoveOrderAPI/MoveOrderController/`
**Routes Found:**

1. `/module/moveorder/invorg` - POST

**Route Count: 1**

### 19. Logs

**Location:** `src/main/java/com/mobile/integration/grandstores/Logs/mobTransLog/`
**Routes Found:**

1. `/module/logs/insertMobLogs` - POST
2. `/module/logs/getAllLogs` - GET

**Route Count: 2**

### 20. IOReceiptConfirm

**Location:** `src/main/java/com/mobile/integration/grandstores/IOReceiptConfirm/IOReceiptConfirmController/`
**Routes Found:**

1. `/module/io/receiptconfirm/insertSerial` - POST
2. `/module/io/receiptconfirm/insert` - POST
3. `/module/io/receiptconfirm/insertAll` - POST
4. `/module/io/receiptconfirm/getall` - GET

**Route Count: 4**

### 21. ItemCheckDispatch

**Location:** `src/main/java/com/mobile/integration/grandstores/ItemCheckDispatch/ItemCheckDispatchCO/`
**Routes Found:**

1. `/module/itemcheck/getitemcheckdispatch` - POST

**Route Count: 1**

### 22. DeliveryApps

**Location:** `src/main/java/com/mobile/integration/grandstores/DeliveryApps/DeliveryAppsCO/`
**Routes Found:**

1. `/module/Deliveryapp/getdelvehdtl` - POST
2. `/module/Deliveryapp/getdelinvdtl` - POST
3. `/module/Deliveryapp/getdellinedtl` - POST
4. `/module/Deliveryapp/getdelrejcodedtl` - POST

**Route Count: 4**

## Summary

### Routes by Folder:

1. **Controller (Main)**: 28 routes
2. **Showroom**: 27 routes
3. **User**: 14 routes
4. **BinTransferTrack**: 11 routes
5. **RMAReceipt**: 9 routes
6. **PODelivery**: 7 routes
7. **BinTransfer**: 6 routes
8. **MoConfirm**: 6 routes
9. **PickOrderNumber**: 6 routes
10. **PhysicalCounting**: 5 routes
11. **UpdateDescription**: 4 routes
12. **IOReceiptConfirm**: 4 routes
13. **ReturnToVendor**: 4 routes
14. **DeliveryApps**: 4 routes
15. **RMAConfirm**: 3 routes
16. **RTVProcess**: 3 routes
17. **Logs**: 2 routes
18. **RTVConfirmation**: 1 route
19. **StockUpdate**: 1 route
20. **SpotCheckDirectSync**: 1 route
21. **MoveOrderAPI**: 1 route
22. **ItemCheckDispatch**: 1 route

### **TOTAL ROUTES COUNT: 148**

## Analysis Date

**Task Created:** 31-07-25
**Project:** GS-PROD-SourceCode Spring Boot Application

## Notes

- All routes are RESTful endpoints using Spring Boot @RequestMapping annotation
- Routes primarily use POST and GET methods
- Most routes follow the pattern `/module/{modulename}/{endpoint}`
- No PUT, DELETE, or PATCH endpoints found in this analysis
- Commented routes (/\* \*/) were excluded from the count
