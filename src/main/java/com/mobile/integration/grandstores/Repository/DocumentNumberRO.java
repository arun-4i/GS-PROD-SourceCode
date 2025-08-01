package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.DocumentNumberEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface DocumentNumberRO  extends JpaRepository<DocumentNumberEO ,BigDecimal>{
 
    // List<DocumentNumberEO> findBydocumentNumberLike(BigDecimal officeId);

    String rawQuery="select * from XXGS_PI_DOCUMENT_V where DOCUMENT_NUMBER  like %:docId% ";

    @Query(nativeQuery = true, value = rawQuery)
    List<DocumentNumberEO> filterBydocNumber(@Param("docId") String  docId);



}