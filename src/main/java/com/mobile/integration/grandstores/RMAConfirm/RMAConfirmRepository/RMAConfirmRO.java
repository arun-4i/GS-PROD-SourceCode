package com.mobile.integration.grandstores.RMAConfirm.RMAConfirmRepository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmEntity.RMAConfirmEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RMAConfirmRO extends JpaRepository<RMAConfirmEO, BigDecimal>{

    // duplicate line filtration - 25/04/24  
String rawQuery="Select count(*) from XXGS_RMA_CONFIRMATIONS where "+
"TRANSACTION_TYPE = :transactionType "+
"AND RECEIPT_NUMBER = :receiptNumber "+
"AND LINE_NUMBER = :lineNumber "+
"AND ORDER_HEADER_ID = :orderHeaderId "+
"AND ORDER_LINE_ID = :orderLineId "+
"AND ATTRIBUTE3 = :attribute3 "+
"AND ATTRIBUTE10 = :attribute10 "+
"AND ITEM_ID = :itemId "+
"AND STATUS = :status";

@Query(nativeQuery = true, value = rawQuery)
int recordCountForDelivery(
@Param("transactionType") String  transactionType,
@Param("receiptNumber") String receiptNumber,
@Param("lineNumber") String lineNumber,
@Param("orderHeaderId") String orderHeaderId,
@Param("orderLineId") String orderLineId,
@Param("attribute3") String attribute3,
@Param("attribute10") String attribute10,
@Param("itemId") String itemId,
@Param("status") String status
);


  // duplicate line filtration - 25/04/24  
  String rawQuery1="Select count(*) from XXGS_RMA_CONFIRMATIONS where "+
  "TRANSACTION_TYPE = :transactionType "+
  "AND LINE_NUMBER = :lineNumber "+
  "AND ORDER_HEADER_ID = :orderHeaderId "+
  "AND ORDER_LINE_ID = :orderLineId "+
  "AND ATTRIBUTE3 = :attribute3 "+
  "AND ITEM_ID = :itemId "+
  "AND STATUS = :status";
  
  @Query(nativeQuery = true, value = rawQuery1)
  int recordCountForReceipt(
  @Param("transactionType") String  transactionType,
  @Param("lineNumber") String lineNumber,
  @Param("orderHeaderId") String orderHeaderId,
  @Param("orderLineId") String orderLineId,
  @Param("attribute3") String attribute3,
  @Param("itemId") String itemId,
  @Param("status") String status
  );
    
}