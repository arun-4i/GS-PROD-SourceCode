package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.SearchDetailEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchDetailRO   extends JpaRepository<SearchDetailEO ,BigDecimal>{
    
    String rawQuery=" Select distinct SHIPMENT_REF, DOCUMENT_NUMBER, PI_NUMBER from XXGS_GET_SEARCH_DETAILS_V where "+
    " (DOCUMENT_NUMBER LIKE  :docNum || '%' OR :docNum IS NULL) "+    
    " AND (UPPER (PI_NUMBER) LIKE  UPPER (:piNum) || '%'OR :piNum IS NULL) "+
    " AND (UPPER (SHIPMENT_REF) LIKE  UPPER (:shipRef) || '%' OR :shipRef IS NULL) " 
    ;   
    
    // String rawQuery=" Select distinct SHIPMENT_REF, DOCUMENT_NUMBER, PI_NUMBER from XXGS_GET_SEARCH_DETAILS_V where "+
    // " (DOCUMENT_NUMBER LIKE  :docNum || '%' OR :docNum IS NULL) "+    
    // " AND (UPPER (PI_NUMBER) LIKE  UPPER (:piNum) || '%'OR :piNum IS NULL) "+
    // " AND (UPPER (SHIPMENT_REF) LIKE  UPPER (:shipRef) || '%' OR :shipRef IS NULL) " 
    // ;   


    // '%:piNum%'
    // '%:shipRef%' 

    @Query(nativeQuery = true, value = rawQuery)
    List<SearchDetailEO> rawsearchDtlQuery(
    @Param("docNum") String  docNum,
    @Param("piNum") String piNum,
    @Param("shipRef") String shipRef
    );

    
    





}