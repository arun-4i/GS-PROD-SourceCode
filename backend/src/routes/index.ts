import { Router } from "express";
import { userRouter } from "./userRouter";
import { binTransferPackageRouter } from "./binTransferPackageRouter";
import { binTransferTrackRouter } from "./binTransferTrackRouter";
import showroomRouter from "./showroomRouter";
import { moConfirmRouter } from "./moConfirmRouter";
import { rmaConfirmRouter } from "./rmaConfirmRouter";
import { rmaReceiptRouter } from "./rmaReceiptRouter";
import { rmaDeliveryRouter } from "./rmaDeliveryRouter";
import { returnVendorRouter } from "./returnVendorRouter";
import { END_POINTS } from "./end-points";
import HealthRouter from "./health";

const router = Router();

router.use(END_POINTS.HEALTH, HealthRouter);
router.use(END_POINTS.USER, userRouter);
router.use(END_POINTS.BIN_TRANSFER_PACKAGE, binTransferPackageRouter);
router.use(END_POINTS.BIN_TRANSFER_TRACK, binTransferTrackRouter);
router.use(END_POINTS.SHOWROOM, showroomRouter);
router.use(END_POINTS.MO_CONFIRM, moConfirmRouter);
router.use(END_POINTS.RMA_CONFIRM, rmaConfirmRouter);
router.use(END_POINTS.RMA_RECEIPT, rmaReceiptRouter);
router.use(END_POINTS.RMA_DELIVERY, rmaDeliveryRouter);
router.use(END_POINTS.RETURN_VENDOR, returnVendorRouter);

export default router;
