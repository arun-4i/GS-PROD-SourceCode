package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.PIItemDetailEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PIItemDetailRO  extends JpaRepository<PIItemDetailEO ,BigDecimal>{
    
    // List<DocumentNumberEO> findBydocumentNumber(BigDecimal officeId);

    // List<PIItemDetailEO> findByheaderIdOrDocumentnumber(BigDecimal PheaderId,BigDecimal PdocNumber);


	// List<Laptop> findByNameOrBrandOrPrice(String name, String brand, BigDecimal price);

    // findByLastnameOrFirstname
    // List<PIItemDetailEO> findByheaderIdOrdocumentNumberOrsupplierNumberOrpiNumber(
    //         BigDecimal PheaderId, BigDecimal PdocumentNumber,String Pshipmentref, String PpiNumber);
    

    // String rawQuery="select HEADER_ID, INVENTORY_ORG_ID, DOCUMENT_NUMBER, SUPPLIER_ID, SUPPLIER_NUMBER, SUPPLIER_NAME, SHIPMENT_REF, LINE_ID, PI_NUMBER, ITEM_ID, ITEM_CODE, ITEM_DESCRIPTION, LOT_STATUS_ENABLED, PI_QTY, PI_UNIT_PRICE"+
    // " from XXGS_GET_PI_ITEM_DTLS_V where HEADER_ID = NVL(:hdrId, HEADER_ID)"+
    // "OR  DOCUMENT_NUMBER = NVL(:docNum, DOCUMENT_NUMBER)"+ 
    // "OR  SUPPLIER_NUMBER like '%:supNum%' "+
    // "OR  PI_NUMBER like '%:piNum%'" ;

    String rawQuery="Select * from XXGS_GET_PI_ITEM_DTLS_V where "+
    " ( HEADER_ID LIKE  :hdrId || '%' OR :hdrId IS NULL )"+
    " AND (UPPER (PI_NUMBER) LIKE  UPPER (:piNum) || '%'OR :piNum IS NULL) "+
    " AND (UPPER (SHIPMENT_REF) LIKE  UPPER (:shipNum) || '%' OR :shipNum IS NULL) "+    
    " AND (DOCUMENT_NUMBER LIKE  :docNum || '%' OR :docNum IS NULL) ";    


    @Query(nativeQuery = true, value = rawQuery)
    List<PIItemDetailEO> rawpiItemQuery(
    @Param("hdrId") String  hdrId,
    @Param("docNum") String docNum,
    @Param("shipNum") String supNum,
    @Param("piNum") String piNum
    );


}