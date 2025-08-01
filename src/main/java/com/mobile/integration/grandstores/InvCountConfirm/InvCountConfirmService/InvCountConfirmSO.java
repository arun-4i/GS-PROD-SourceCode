package com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmService;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
// import java.util.Map;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.integration.grandstores.BinTransfer.BinTransferService.BinTransferSO;
import com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmEntity.InvCountConfirmEO;
import com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmRepository.InvCountConfirmRO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogEO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogSO;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvCountConfirmSO {

    @Autowired
    private InvCountConfirmRO invCountConfirmRo;

    @Autowired
    private DateUtils dateUtils;

    
    @Autowired
    private MobTransLogSO mobTransLogSO;
    private static final Logger logger = LoggerFactory.getLogger(InvCountConfirmSO.class);

    MobTransLogEO mobTransLog = null;
    public ResponseEntity<APIResponse> insertInvCountConfirm(Iterable<InvCountConfirmEO> bodydtl) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Bin Transfer - insertIOConLot");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(bodydtl).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog);
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        System.out.println("==>+");
        Iterable<InvCountConfirmEO> ls=invCountConfirmRo.saveAll(bodydtl);
        
        try{
            Clob clobResponse = new SerialClob(new ObjectMapper().writeValueAsString(ls).toCharArray());
            mobTransLog.setResponse(clobResponse);
            mobTransLogSO.saveMobTransLog(mobTransLog);  
        }catch(Exception e){
            logger.info("issue in updating the response in log table : "+e.getMessage());
        }
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getinvCountConfirm() {
        List<InvCountConfirmEO> ls=invCountConfirmRo.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
    
}