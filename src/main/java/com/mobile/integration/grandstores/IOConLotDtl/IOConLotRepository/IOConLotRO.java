package com.mobile.integration.grandstores.IOConLotDtl.IOConLotRepository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.IOConLotDtl.IOConLotEntity.IOConLotEO;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOConLotRO extends JpaRepository<IOConLotEO ,BigDecimal>{
    
}