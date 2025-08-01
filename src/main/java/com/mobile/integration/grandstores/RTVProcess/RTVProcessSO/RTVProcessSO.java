package com.mobile.integration.grandstores.RTVProcess.RTVProcessSO;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.mobile.integration.grandstores.PackageCalling.RtvProcessPkg;
import com.mobile.integration.grandstores.PackageCalling.SpotCheckDirectSyncPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class RTVProcessSO extends NamedParameterJdbcDaoSupport{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
    
    @Autowired
    private RtvProcessPkg rtvProcessPkg;

    // GET_RTV_REQUEST_NUM
    public ResponseEntity<APIResponse> getRTVRequestNum(Map<String, Object> content) {
        
        System.out.println("inside getRTVRequestNum so");
        String p_inventory_org_id = content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String p_request_num = content.get("P_REQUEST_NUM")==null?"":content.get("P_REQUEST_NUM").toString();
        
        System.out.println("P_INVENTORY_ORG_ID: " +p_inventory_org_id+" p_request_num: "+p_request_num);
        Map<String, Object> ls=rtvProcessPkg.getRtvRequestNumPkg(p_inventory_org_id, p_request_num);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
    }

    // GET_RTV_DTLS
    public ResponseEntity<APIResponse> getRTVDtls(Map<String, Object> content) {
        System.out.println("inside getRTVDtls so");
        String p_request_id=content.get("P_REQUEST_ID")==null?"":content.get("P_REQUEST_ID").toString();
        String p_inventory_org_id=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        System.out.println("p_request_id: " +p_request_id);
        System.out.println("P_INVENTORY_ORG_ID: " +p_inventory_org_id);
        Map<String, Object> ls=rtvProcessPkg.getRtvDtls(p_request_id, p_inventory_org_id);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
    }

    // GET_RTV_ITEM_DTLS
    public ResponseEntity<APIResponse> getRTVItemDtls(Map<String, Object> content) {
        System.out.println("inside getRTVItemDtls so");
        String p_request_id=content.get("P_REQUEST_ID")==null?"":content.get("P_REQUEST_ID").toString();
        String p_inventory_org_id=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        System.out.println("p_request_id: " +p_request_id);
        System.out.println("P_INVENTORY_ORG_ID: " +p_inventory_org_id);
        Map<String, Object> ls=rtvProcessPkg.getRtvItemDtls(p_request_id, p_inventory_org_id);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
    }

    }
