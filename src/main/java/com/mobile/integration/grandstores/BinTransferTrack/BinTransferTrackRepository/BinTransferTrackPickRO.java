package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackRepository;
import java.math.BigDecimal;

import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackPickEO;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BinTransferTrackPickRO  extends JpaRepository<BinTransferTrackPickEO ,BigDecimal>{
    
}