package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService;

import java.math.BigDecimal;
import java.util.Iterator;
// import java.util.Map;
import java.util.List;
import java.util.Map;

import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackHdrEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackPickEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.QuickDropEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackRepository.BinTransferTrackHdrRO;
import com.mobile.integration.grandstores.PackageCalling.BinTranferTrackPkg;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BinTransferTrackHdrSO {

    @Autowired
    private BinTransferTrackHdrRO binTransferTrackHdrro;

    @Autowired
    private BinTranferTrackPkg binTranferTrackPkg;

    public ResponseEntity<APIResponse> insertBinTransferHdr(Iterable<BinTransferTrackHdrEO> bodydtl) {
        Iterable<BinTransferTrackHdrEO> ls = binTransferTrackHdrro.saveAll(bodydtl);
        APIResponse api = new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getBinTransferHdr() {
        List<BinTransferTrackHdrEO> ls = binTransferTrackHdrro.findAll();
        APIResponse api = new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> GET_BIN_TRANS_REF_NO() {
        Map<String, Object> ls = binTranferTrackPkg.GET_BIN_TRANS_REF_NO();
        APIResponse api = new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> insertQuickDrop(QuickDropEO bodydtl) {
        // Header Table insertion
        BigDecimal headerId = new BigDecimal(0);
        Iterable<BinTransferTrackHdrEO> hr = bodydtl.getHeader();
        Iterable<BinTransferTrackHdrEO> lshdr = binTransferTrackHdrro.saveAll(hr);
        Iterator<BinTransferTrackHdrEO> hrIter = lshdr.iterator();
        while (hrIter.hasNext()) {
            BinTransferTrackHdrEO hdrdetail = hrIter.next();
            System.out.println("Header info saved successfully: " + hdrdetail.getHeaderId());
            headerId = hdrdetail.getHeaderId();
        }
        System.out.println("Generated Header Id: " + headerId);

        // Pick Table insertion
        Iterator<BinTransferTrackPickEO> pickItr = bodydtl.getPick().iterator();
        while (pickItr.hasNext()) {
            BinTransferTrackPickEO pickDetail = pickItr.next();
            pickDetail.setHeaderId(headerId);
        }

        APIResponse api = new APIResponse();
        api.setData(lshdr);
        api.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(api);
    }

}