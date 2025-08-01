package com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionRepo;
import java.math.BigDecimal;
import com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionEntity.ItemConfigEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemConfigRO extends JpaRepository<ItemConfigEO ,BigDecimal> {

    String rawQuery="Select count(*) from XXGS_INV_ITEM_CONF where "+
    "ORGANIZATION_ID = :organizationId "+
    "AND ITEM_ID = :itemId "+
    "AND CROSS_REFERENCE = :crossReference";

    @Query(nativeQuery = true, value = rawQuery)
    int recordCountCrossRef(
    @Param("organizationId") String organizationId,
    @Param("itemId") String itemId,
    @Param("crossReference") String crossReference
    );
    
}