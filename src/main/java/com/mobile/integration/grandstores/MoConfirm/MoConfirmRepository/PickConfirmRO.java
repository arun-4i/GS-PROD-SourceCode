package com.mobile.integration.grandstores.MoConfirm.MoConfirmRepository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.PickConfirmEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PickConfirmRO extends JpaRepository<PickConfirmEO, BigDecimal>{

    // duplicate line filtration - 18/12/23  
String rawQuery="Select count(*) from XXGS_PICK_CONFIRMATION_SERIAL where "+
    " DELIVERY_DETAIL_ID = :delDetailId "+
    "AND  FROM_SERIAL_NUMBER = :fromSerialNum "+
    "AND ATTRIBUTE1 = :att1 "+
    "AND ATTRIBUTE2 = :att2 "+
    "AND ATTRIBUTE3 = :att3 "+
    "AND ATTRIBUTE4 = :att4" ;

    @Query(nativeQuery = true, value = rawQuery)
    int recordCountPickConfirm(
    @Param("delDetailId") String  delDetailId,
    @Param("fromSerialNum") String fromSerialNum,
    @Param("att1") String att1,
    @Param("att2") String att2,
    @Param("att3") String att3,
    @Param("att4") String att4
    );
    
    // duplicate line filtration - 26/04/24  
String rawQuery2="Select count(*) from XXGS_PICK_CONFIRMATION_SERIAL where "+
" FROM_SERIAL_NUMBER = :fromSerialNum "+
"AND ATTRIBUTE2 = :att2 "+
"AND ATTRIBUTE3 = :att3 "+
"AND TRANSACTION_TYPE = :transactionType" ;

@Query(nativeQuery = true, value = rawQuery2)
int recordCountDelivery(
@Param("fromSerialNum") String fromSerialNum,
@Param("att2") String att2,
@Param("att3") String att3,
@Param("transactionType") String transactionType
);


        // duplicate line filtration - 18/12/23  
    String rawQuery1="Select count(*) from XXGS_PICK_CONFIRMATION_SERIAL where "+
    " DELIVERY_DETAIL_ID = :delDetailId " ;

    @Query(nativeQuery = true, value = rawQuery1)
    int poMatchCount(
    @Param("delDetailId") String  delDetailId
    );


}