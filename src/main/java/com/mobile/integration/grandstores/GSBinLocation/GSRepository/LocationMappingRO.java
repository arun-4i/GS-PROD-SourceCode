package com.mobile.integration.grandstores.GSBinLocation.GSRepository;
import java.math.BigDecimal;
import java.util.List;
import com.mobile.integration.grandstores.GSBinLocation.GLEntity.LocationMappingEO;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationMappingRO extends JpaRepository<LocationMappingEO ,BigDecimal> {
    
}