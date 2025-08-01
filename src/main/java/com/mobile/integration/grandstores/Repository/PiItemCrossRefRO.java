package com.mobile.integration.grandstores.Repository;

import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.PiItemCrossRefEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface PiItemCrossRefRO  extends JpaRepository<PiItemCrossRefEO ,BigDecimal>{
 
    String rawQuery="Select * from XXGS_GET_PI_ITEM_CROSS_REF_V where "+
    " ( HEADER_ID LIKE  :hdrId || '%' OR :hdrId IS NULL )"+
    " AND (UPPER (PI_NUMBER) LIKE  UPPER (:piNum) || '%'OR :piNum IS NULL) "+
    " AND (UPPER (SHIPMENT_REF) LIKE  UPPER (:shipRef) || '%' OR :shipRef IS NULL) "+    
    " AND (DOCUMENT_NUMBER LIKE  :docNum || '%' OR :docNum IS NULL) ";    


    @Query(nativeQuery = true, value = rawQuery)
    List<PiItemCrossRefEO> rawpiItemCrossRefQuery(
    @Param("hdrId") String  hdrId,
    @Param("docNum") String docNum,
    @Param("shipRef") String supNum,
    @Param("piNum") String piNum
    );


}