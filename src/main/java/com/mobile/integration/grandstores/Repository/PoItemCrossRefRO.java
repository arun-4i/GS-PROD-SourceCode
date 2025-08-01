package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.PoItemCrossRefEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PoItemCrossRefRO extends JpaRepository<PoItemCrossRefEO ,BigDecimal>{

    String rawQuery="Select * from XXGS_GET_PO_ITEM_CROSS_REF_V where "+
    " PO_HEADER_ID like '%'||:poHdrId||'%' "+
    "OR  PO_RELEASE_ID like '%'||:poRelId||'%'" ;

    @Query(nativeQuery = true, value = rawQuery)
    List<PoItemCrossRefEO> rawpoItemCrossQuery(
    @Param("poHdrId") String  poHdrId,
    @Param("poRelId") String poRelId
    );
    
}