package com.mobile.integration.grandstores.MoConfirm.MoConfirmRepository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.PoItemCrossRefEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoConfirmEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MoConfirmRO extends JpaRepository<MoConfirmEO, BigDecimal>{
    
      // duplicate line filtration - 18/12/23  
String rawQuery="Select count(*) from XXGS_MO_CONFIRMATIONS where "+
    " SOURCE_LOCATOR_ID = :sourceLocId "+
    "AND DELIVERY_DETAIL_ID = :delDetailId "+
    "AND ITEM_ID = :itemId "+
    "AND MO_NUMBER = :moNumber "+
    "AND MO_LINE_NUMBER = :moLineNumber "+
    "AND STATUS = :status" ;

    @Query(nativeQuery = true, value = rawQuery)
    int recordCount(
    @Param("sourceLocId") String  sourceLocId,
    @Param("delDetailId") String delDetailId,
    @Param("itemId") String itemId,
    @Param("moNumber") String moNumber,
    @Param("moLineNumber") String moLineNumber,
    @Param("status") String status
    );


}