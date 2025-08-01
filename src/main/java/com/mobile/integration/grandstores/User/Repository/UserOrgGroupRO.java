package com.mobile.integration.grandstores.User.Repository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.User.Entity.UserOrgGroupEO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrgGroupRO   extends JpaRepository<UserOrgGroupEO ,BigDecimal> {
    
}