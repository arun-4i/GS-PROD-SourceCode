package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
// import java.util.List;
import java.util.List;

import com.mobile.integration.grandstores.Entity.PiSupplierEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PiSupplierRO  extends JpaRepository<PiSupplierEO ,BigDecimal>{
    

    // String rawQuery="Select * from XXGS_GET_PI_ITEM_DTLS_V where HEADER_ID = NVL(:hdrId, HEADER_ID)"+
    // "OR  DOCUMENT_NUMBER = NVL(:docNum, DOCUMENT_NUMBER)"+ 
    // "OR  SUPPLIER_NUMBER like '%:supNum%' "+
    // "OR  PI_NUMBER like '%:piNum%'" ;

    // @Query(nativeQuery = true, value = rawQuery)
    // List<PIItemDetailEO> rawpiItemQuery(
    // @Param("hdrId") String  hdrId,
    // @Param("docNum") String docNum,
    // @Param("supNum") String supNum,
    // @Param("piNum") String piNum
    // );

    String rawQuery="select * from XXGS_GET_PI_SUPPLIERS_V where UPPER (SUPPLIER_NAME) LIKE '%' || UPPER (:supName) || '%'OR :supName IS NULL ";

    @Query(nativeQuery = true, value = rawQuery)
    List<PiSupplierEO> filterBySupplierName(@Param("supName") String  supName);


}