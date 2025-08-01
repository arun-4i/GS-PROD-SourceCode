package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.ShipmentRefEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRefRO  extends JpaRepository<ShipmentRefEO ,BigDecimal>{
 
    // List<DocumentNumberEO> findBydocumentNumberLike(BigDecimal officeId);

    String rawQuery="select * from XXGS_GET_SHIPMENT_REF_V where SUPPLIER_ID  like '%'||:supId||'%' ";	 

    // %:supId% 

    @Query(nativeQuery = true, value = rawQuery)
    List<ShipmentRefEO> filterBySupplierRef(@Param("supId") String  supId);


    
}