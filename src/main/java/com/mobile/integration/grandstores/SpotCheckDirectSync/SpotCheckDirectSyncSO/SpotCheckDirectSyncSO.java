package com.mobile.integration.grandstores.SpotCheckDirectSync.SpotCheckDirectSyncSO;
import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.SpotCheckDirectSyncPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

@Service
public class SpotCheckDirectSyncSO extends NamedParameterJdbcDaoSupport{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
    
    @Autowired
    private SpotCheckDirectSyncPkg spotCheckDirectSyncPkg;

    // GET_SPOT_CHECK_DIRECT_SYNC
    public ResponseEntity<APIResponse> GET_SPOT_CHECK_DIRECT_SYNC(Map<String, Object> content) {
        
        System.out.println("inside spot check direct sync so");
        String P_ORGANIZATION_ID=content.get("P_ORGANIZATION_ID")==null?"":content.get("P_ORGANIZATION_ID").toString();
        String P_BARCODE=content.get("P_BARCODE")==null?"":content.get("P_BARCODE").toString();
        String P_BIN=content.get("P_BIN")==null?"":content.get("P_BIN").toString();

        System.out.println("P_ORGANIZATION_ID: " +P_ORGANIZATION_ID);
        System.out.println("P_BARCODE: " +P_BARCODE);
        System.out.println("P_BIN: " +P_BIN);

        Map<String, Object> ls=spotCheckDirectSyncPkg.getSpotCheckDirectSyncPkg(P_ORGANIZATION_ID, 
                                                                    P_BARCODE, P_BIN);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
    } 
}
