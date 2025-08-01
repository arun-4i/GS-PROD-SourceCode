package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.ReleaseNumberEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ReleaseNumberRO   extends JpaRepository<ReleaseNumberEO ,BigDecimal>{
 
    // List<DocumentNumberEO> findBydocumentNumberLike(BigDecimal officeId);

    String rawQuery="select * from XXGS_GET_RELEASE_NUM_V where PO_HEADER_ID  like '%'||:poHdrId||'%' ";

    @Query(nativeQuery = true, value = rawQuery)
    List<ReleaseNumberEO> filterByPoHdrId(@Param("poHdrId") String  poHdrId);

    
}