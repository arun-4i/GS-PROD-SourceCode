package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackController;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackDropEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackHdrEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.BinTransferTrackPickEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity.QuickDropEO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService.BinTransferService;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService.BinTransferTrackDropSO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService.BinTransferTrackHdrSO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService.BinTransferTrackPickSO;
import com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService.QuickDropSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/bintransfer/")
@Api(
    tags = {"Bin Transfer Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    produces = "application/json"
    )
public class BinTransferTrackHdrCO {
    @Autowired
    private BinTransferTrackHdrSO binTransferHdrso;

    @Autowired
    private BinTransferTrackPickSO binTransferpickso;

    @Autowired
    private BinTransferTrackDropSO binTransferdropso;

    @Autowired
    private BinTransferService binTransferServerSO;

    @Autowired
    private QuickDropSO quickDropSO;

    //Insert Insert Bin Transfer 
    @RequestMapping(value = "/insert/bintrasfer/hdr", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertBinTransferHdr(@RequestBody Iterable<BinTransferTrackHdrEO> bodydtl) throws ParseException{
        return binTransferHdrso.insertBinTransferHdr(bodydtl);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getall/bintrasfer/hdr", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransferHdr()throws ParseException{
        return binTransferHdrso.getBinTransferHdr();
    }

    //Insert Insert Bin Transfer 
    @RequestMapping(value = "/insert/bintrasfer/lns", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertBinTransferLns(@RequestBody Iterable<BinTransferTrackPickEO> bodydtl) throws ParseException{
        return binTransferpickso.insertBinTransferLns(bodydtl);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getall/bintrasfer/lns", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransferLns()throws ParseException{
        return binTransferpickso.getBinTransferLns();
    }

    //Insert Insert Bin Transfer 
    @RequestMapping(value = "/insert/bintrasfer/drop", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertBinTransferDrop(@RequestBody Iterable<BinTransferTrackDropEO> bodydtl) throws ParseException{
        return binTransferdropso.insertBinTransferDrop(bodydtl);
    }
    
    //Get PI Count Services
    @RequestMapping(value = "/getall/bintrasfer/drop", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransferDrop()throws ParseException{
        return binTransferdropso.getBinTransferDrop();
    }

    // getrefHdr
    @RequestMapping(value = "/getrefhdr", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> GET_BIN_TRANS_REF_NO() throws ParseException{
        return binTransferHdrso.GET_BIN_TRANS_REF_NO();
    }

    // GET_BIN_TRNS_DROP_HDR 
    @RequestMapping(value = "/drop/hdr", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> binTransferDropHdr(@RequestBody  Map<String, Object> content) throws ParseException{
        return binTransferServerSO.GET_BIN_TRNS_DROP_HDR(content);
    }

    // GET_BIN_TRNS_DROP_DTLS 
    @RequestMapping(value = "/drop/dtl", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> binTransferDropDtl(@RequestBody  Map<String, Object> content) throws ParseException{
        return binTransferServerSO.GET_BIN_TRNS_DROP_DTLS(content);
    }    

    // GET_BIN_TRNS_DROP_DTLS 
    @RequestMapping(value = "/bin/number", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getBinNumber(@RequestBody  Map<String, Object> content) throws ParseException{
        return binTransferServerSO.getBinNumber(content);       
    }
        
    //Quick Pick Services
    @PostMapping("/insert/bintrasfer/quickdrop")
    public ResponseEntity<APIResponse> insertQuickDrop(@RequestBody QuickDropEO bodydtl){
        return quickDropSO.insertQuickDrop(bodydtl);
    }
}