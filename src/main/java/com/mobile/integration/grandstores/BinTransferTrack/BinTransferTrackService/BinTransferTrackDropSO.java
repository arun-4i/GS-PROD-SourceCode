package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService;

import java.util.List;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackDropEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackRepository.BinTransferTrackDropRO;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BinTransferTrackDropSO {
    
    @Autowired
    private BinTransferTrackDropRO binTranTrackDropro;

    public ResponseEntity<APIResponse> insertBinTransferDrop(Iterable<BinTransferTrackDropEO> bodydtl) {
        Iterable<BinTransferTrackDropEO> ls=binTranTrackDropro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getBinTransferDrop() {
        List<BinTransferTrackDropEO> ls=binTranTrackDropro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
}