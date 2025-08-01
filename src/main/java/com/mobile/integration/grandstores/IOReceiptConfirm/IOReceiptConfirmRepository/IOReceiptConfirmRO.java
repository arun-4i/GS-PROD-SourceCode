package com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmRepository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmEntity.IOReceiptConfirmEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOReceiptConfirmRO extends JpaRepository<IOReceiptConfirmEO, BigDecimal>{
    

    String rawQuery="Select count(*) from XXGS_IO_RECEIPT_CONFIRM where "+
    "LINE_NUM = :lineNum "+
    "AND ITEM_ID = :itemId "+
    "AND SHIPMENT_HEADER_ID = :shipmentHeaderId "+
    "AND STATUS = :status "+
    "AND SHIPMENT_LINE_ID = :shipmentLineId";

    @Query(nativeQuery = true, value = rawQuery)
    int recordCountIOReceiptConfirm(
    @Param("lineNum") String lineNum,
    @Param("itemId") String itemId,
    @Param("shipmentHeaderId") String shipmentHeaderId,
    @Param("status") String status,
    @Param("shipmentLineId") String shipmentLineId
    );


    String rawQuery1="Select count(*) from XXGS_IO_RECEIPT_CONFIRM where "+
    "LINE_NUM = :lineNum "+
    "AND ITEM_ID = :itemId "+
    "AND SHIPMENT_HEADER_ID = :shipmentHeaderId "+
    "AND RECEIPT_NUM = :receiptNum "+
    "AND STATUS = :status "+
    "AND DELIV_LOCATOR = :delivLocator "+
    "AND SHIPMENT_LINE_ID = :shipmentLineId";

    @Query(nativeQuery = true, value = rawQuery1)
    int recordCountIODeliveryConfirm(
        @Param("lineNum") String lineNum,
        @Param("itemId") String itemId,
        @Param("shipmentHeaderId") String shipmentHeaderId,
        @Param("receiptNum") String receiptNum,
        @Param("status") String status,
        @Param("delivLocator") String delivLocator,
        @Param("shipmentLineId") String shipmentLineId
    );
}