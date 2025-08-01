package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.PoNumberEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface PoNumberRO   extends JpaRepository<PoNumberEO ,BigDecimal>{
 
    // List<DocumentNumberEO> findBydocumentNumberLike(BigDecimal officeId);

    String rawQuery="select * from XXGS_GET_PO_NUMBER_V where PO_NUMBER  like '%'||:poNum||'%' ";

    @Query(nativeQuery = true, value = rawQuery)
    List<PoNumberEO> filterByPoNumber(@Param("poNum") String  poNum);


    
}