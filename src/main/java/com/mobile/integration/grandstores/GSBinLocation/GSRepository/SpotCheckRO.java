package com.mobile.integration.grandstores.GSBinLocation.GSRepository;
import java.math.BigDecimal;
import com.mobile.integration.grandstores.GSBinLocation.GLEntity.SpotCheckEO;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotCheckRO extends JpaRepository<SpotCheckEO ,BigDecimal> {
    
}