package com.mobile.integration.grandstores.BinTransfer.BinTransferService;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogEO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogSO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmController.MoConfirmCO;
import com.mobile.integration.grandstores.PackageCalling.BinTransferPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class BinTransferSO extends NamedParameterJdbcDaoSupport {
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    @Autowired
    private BinTransferPackage binTransferPkg;

    @Autowired
    private MobTransLogSO mobTransLogSO;

    MobTransLogEO mobTransLog = null;

     private static final Logger logger = LoggerFactory.getLogger(BinTransferSO.class);
    // GET_ITEM_DETAILS Services
    public ResponseEntity<APIResponse> getItemDetail(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {

        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Bin Transfer - getItemDetail");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }


        String P_ORGANIZATION_CODE=content.get("P_ORGANIZATION_CODE")==null?"":content.get("P_ORGANIZATION_CODE").toString();
        String P_DEPARTMENT=content.get("P_DEPARTMENT")==null?"":content.get("P_DEPARTMENT").toString();
        String P_SUB_INV_CODE=content.get("P_SUB_INV_CODE")==null?"":content.get("P_SUB_INV_CODE").toString();
        String P_WITH_LOT_FLAG=content.get("P_WITH_LOT_FLAG")==null?"":content.get("P_WITH_LOT_FLAG").toString();
    
        Map<String, Object> ls=binTransferPkg.GET_ITEM_DETAILS(P_ORGANIZATION_CODE, P_DEPARTMENT, P_SUB_INV_CODE, P_WITH_LOT_FLAG);
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

        // GET_ITEM_CROSS_REF Services
    public ResponseEntity<APIResponse> getItemCrossref(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Bin Transfer - getItemCrossref");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog);
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_ORGANIZATION_CODE=content.get("P_ORGANIZATION_CODE")==null?"":content.get("P_ORGANIZATION_CODE").toString();
        String P_DEPARTMENT=content.get("P_DEPARTMENT")==null?"":content.get("P_DEPARTMENT").toString();
        String P_SUB_INV_CODE=content.get("P_SUB_INV_CODE")==null?"":content.get("P_SUB_INV_CODE").toString();
    
        Map<String, Object> ls =binTransferPkg.GET_ITEM_CROSSREF_DTLS(P_ORGANIZATION_CODE, P_DEPARTMENT, P_SUB_INV_CODE);

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
} 