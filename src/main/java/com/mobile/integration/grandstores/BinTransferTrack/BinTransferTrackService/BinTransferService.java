package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.BinTransferPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class BinTransferService {
    
    @Autowired
    private BinTransferPkg binTransferPkg;
     
    // GET_BIN_TRNS_DROP_HDR
    public ResponseEntity<APIResponse> GET_BIN_TRNS_DROP_HDR(Map<String, Object> content) {
        String P_INV_ORG=content.get("P_INV_ORG")==null?"":content.get("P_INV_ORG").toString();
        String P_SUB_INV=content.get("P_SUB_INV")==null?"":content.get("P_SUB_INV").toString();
                    
        Map<String, Object> ls=binTransferPkg.GET_BIN_TRNS_DROP_HDR(P_INV_ORG, P_SUB_INV);
        
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    
    }

    // GET_BIN_TRNS_DROP_DTLS
    public ResponseEntity<APIResponse> GET_BIN_TRNS_DROP_DTLS(Map<String, Object> content) {
        String p_inv_org_id=content.get("p_inv_org_id")==null?"":content.get("p_inv_org_id").toString();
        String p_sub_code=content.get("p_sub_code")==null?"":content.get("p_sub_code").toString();

        Map<String, Object> ls=binTransferPkg.GET_BIN_TRNS_DROP_DTLS(p_inv_org_id, p_sub_code);
        
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);     
    }

    // getBinNumber
    public ResponseEntity<APIResponse> getBinNumber(Map<String, Object> content) {
        String p_inv_org=content.get("p_inv_org")==null?"":content.get("p_inv_org").toString();
        String p_sub_inv=content.get("p_sub_inv")==null?"":content.get("p_sub_inv").toString();
                    
        Map<String, Object> ls=binTransferPkg.getBinNumber(p_inv_org, p_sub_inv);    
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }
}