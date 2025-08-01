import { Router } from "express";
import { userRouter } from "./userRouter";
import { binTransferPackageRouter } from "./binTransferPackageRouter";
import { binTransferTrackRouter } from "./binTransferTrackRouter";
import showroomRouter from "./showroomRouter";
import { moConfirmRouter } from "./moConfirmRouter";
import { END_POINTS } from "./end-points";
import HealthRouter from "./health";

const router = Router();

router.use(END_POINTS.HEALTH, HealthRouter);
router.use(END_POINTS.USER, userRouter);
router.use(END_POINTS.BIN_TRANSFER_PACKAGE, binTransferPackageRouter);
router.use(END_POINTS.BIN_TRANSFER_TRACK, binTransferTrackRouter);
router.use(END_POINTS.SHOWROOM, showroomRouter);
router.use(END_POINTS.MO_CONFIRM, moConfirmRouter);

export default router;
