package com.mobile.integration.grandstores.Repository;

import java.math.BigDecimal;

import com.mobile.integration.grandstores.Entity.PiLotDetailsEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiLotDetailsRO  extends JpaRepository<PiLotDetailsEO ,BigDecimal>{



    
}