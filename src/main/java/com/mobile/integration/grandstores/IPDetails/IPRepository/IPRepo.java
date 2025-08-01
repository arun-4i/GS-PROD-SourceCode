package com.mobile.integration.grandstores.IPDetails.IPRepository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.integration.grandstores.IPDetails.IPEntity.IPEntity;

@Repository
public interface IPRepo extends JpaRepository<IPEntity, BigDecimal>{

}
