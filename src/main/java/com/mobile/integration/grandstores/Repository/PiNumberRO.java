package com.mobile.integration.grandstores.Repository;
import java.math.BigDecimal;
import java.util.List;

import com.mobile.integration.grandstores.Entity.PiNumberEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PiNumberRO   extends JpaRepository<PiNumberEO ,BigDecimal>{
    

    String rawQuery=
                "Select * from XXGS_GET_PI_NUMBER_V "
                + "where  "
                + "SHIPMENT_REF like '%'||:shipRef||'%' ";
    

    @Query(nativeQuery = true, value = rawQuery)
    List<PiNumberEO> rawpiNumberQuery(
    @Param("shipRef") String  shipRef
    );
    
}