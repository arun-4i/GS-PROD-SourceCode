package com.mobile.integration.grandstores.User.Repository;

import java.math.BigDecimal;
import com.mobile.integration.grandstores.User.Entity.UserResponsibleGroupEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResponsibleGroupRO   extends JpaRepository<UserResponsibleGroupEO ,BigDecimal>{
    
}