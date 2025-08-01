package com.mobile.integration.grandstores.StockUpdate.StockUpdateSO;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.StockUpdate;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class StockUpdateSO  extends NamedParameterJdbcDaoSupport{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
    
    @Autowired
    private StockUpdate StockUpdatePkg;

    // GET_BIN_STOCK_UPDATE
    public ResponseEntity<APIResponse> GET_BIN_STOCK_UPDATE(Map<String, Object> content) {
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_SUB_INVENTORY=content.get("P_SUB_INVENTORY")==null?"":content.get("P_SUB_INVENTORY").toString();

        Map<String, Object> ls=StockUpdatePkg.GET_BIN_STOCK_UPDATE(P_INVENTORY_ORG_ID, P_SUB_INVENTORY);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
    }   


}