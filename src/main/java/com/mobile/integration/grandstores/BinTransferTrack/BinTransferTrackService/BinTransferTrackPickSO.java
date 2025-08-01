package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackPickEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackRepository.BinTransferTrackPickRO;


import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BinTransferTrackPickSO {
    
    @Autowired
    private BinTransferTrackPickRO binTransPickro;

    public ResponseEntity<APIResponse> insertBinTransferLns(Iterable<BinTransferTrackPickEO> bodydtl) {
        Iterable<BinTransferTrackPickEO> ls=binTransPickro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getBinTransferLns() {
        List<BinTransferTrackPickEO> ls=binTransPickro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }

}