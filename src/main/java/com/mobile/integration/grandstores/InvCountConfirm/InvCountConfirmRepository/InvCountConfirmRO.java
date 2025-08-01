package com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmRepository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmEntity.InvCountConfirmEO;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvCountConfirmRO extends JpaRepository<InvCountConfirmEO ,BigDecimal>{
    
}