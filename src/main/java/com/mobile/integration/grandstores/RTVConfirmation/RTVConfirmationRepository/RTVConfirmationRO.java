package com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationRepository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationEntity.RTVConfirmationEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RTVConfirmationRO extends JpaRepository<RTVConfirmationEO, BigDecimal>{

        
    String deleteQuery="Delete from XXGS_RTV_REQ_LINES where "+
    " LINE_ID = :lineId ";

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = deleteQuery)
    void delete(
    @Param("lineId") String  lineId
    );

    String rawQuery="Select count(*) from XXGS_RTV_REQ_LINES where "+
    " LINE_ID = :lineId ";

    @Query(nativeQuery = true, value = rawQuery)
    int recordCountt(
    @Param("lineId") String  lineId
    );

}