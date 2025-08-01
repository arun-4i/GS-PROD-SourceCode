package com.mobile.integration.grandstores.ItemCheckDispatch.ItemCheckDispatchSO;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
import com.mobile.integration.grandstores.PackageCalling.ItemCheckDispatchPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class ItemCheckDispatchSO extends NamedParameterJdbcDaoSupport{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
    
    @Autowired
    private ItemCheckDispatchPkg itemCheckDispatchPkg;

    @Autowired
    private MobTransLogSO mobTransLogSO;

    MobTransLogEO mobTransLog = null;

    private static final Logger logger = LoggerFactory.getLogger(ItemCheckDispatchSO.class);

    // GET_RTV_REQUEST_NUM
    public ResponseEntity<APIResponse> getItemCheck(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Item Check Dispatch");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String p_dep_code = content.get("P_DEP_CODE")==null?"":content.get("P_DEP_CODE").toString();
        BigDecimal p_org_id = content.get("P_ORG_ID") == null ? BigDecimal.ZERO : new BigDecimal(content.get("P_ORG_ID").toString());
        Map<String, Object> ls=itemCheckDispatchPkg.callItemCheckDispatchPkg(p_dep_code, p_org_id);
        logger.info("GetItemCheckDispatch Package call response -  "+ls);    
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value()); 
        try{
            Clob clobResponse = new SerialClob(new ObjectMapper().writeValueAsString(ls).toCharArray());
            mobTransLog.setResponse(clobResponse);
            mobTransLogSO.saveMobTransLog(mobTransLog);  
            
        }catch(Exception e){
            logger.info("issue in updating the response in log table : "+e.getMessage());
        }
            return ResponseEntity.ok().body(api); 
    }
}
