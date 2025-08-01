package com.mobile.integration.grandstores.PhysicalCounting.PhysicalCountingService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.PhysicalCountingPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class PhysicalCountingSO extends NamedParameterJdbcDaoSupport{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    @Autowired
    private PhysicalCountingPkg physicalCountingPkg;

    
        // GET_PHYSICAL_INVENTORIES
        public ResponseEntity<APIResponse> GET_PHYSICAL_INVENTORIES(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            Map<String, Object> ls=physicalCountingPkg.GET_PHYSICAL_INVENTORIES(P_INVENTORY_ORG_ID);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   
    
        // GET_PHY_INV_SUBINV_DTLS
        public ResponseEntity<APIResponse> GET_PHY_INV_SUBINV_DTLS(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_PHYSICAL_INVENTORY_ID=content.get("P_PHYSICAL_INVENTORY_ID")==null?"":content.get("P_PHYSICAL_INVENTORY_ID").toString();
            Map<String, Object> ls=physicalCountingPkg.GET_PHY_INV_SUBINV_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY_ID);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }
        // GET_PHY_INV_QUERY_DTLS
        public ResponseEntity<APIResponse> GET_PHY_INV_QUERY_DTLS(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_PHYSICAL_INVENTORY_ID=content.get("P_PHYSICAL_INVENTORY_ID")==null?"":content.get("P_PHYSICAL_INVENTORY_ID").toString();
            Map<String, Object> ls=physicalCountingPkg.GET_PHY_INV_QUERY_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY_ID);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }        
        // GET_PHYINV_CNT_ITEM_DTLS
        public ResponseEntity<APIResponse> GET_PHYINV_CNT_ITEM_DTLS(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_PHYSICAL_INVENTORY_ID=content.get("P_PHYSICAL_INVENTORY_ID")==null?"":content.get("P_PHYSICAL_INVENTORY_ID").toString();
            String P_WITH_LOT_DTLS_FLAG=content.get("P_WITH_LOT_DTLS_FLAG")==null?"":content.get("P_WITH_LOT_DTLS_FLAG").toString();
            String P_WITH_SERIAL_DTLS_FLAG=content.get("P_WITH_SERIAL_DTLS_FLAG")==null?"":content.get("P_WITH_SERIAL_DTLS_FLAG").toString();

            Map<String, Object> ls=physicalCountingPkg.GET_PHYINV_CNT_ITEM_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY_ID, P_WITH_LOT_DTLS_FLAG, P_WITH_SERIAL_DTLS_FLAG);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }

        // GET_PHYINV_CNT_ITEM_CR
        public ResponseEntity<APIResponse> GET_PHYINV_CNT_ITEM_CR(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_PHYSICAL_INVENTORY_ID=content.get("P_PHYSICAL_INVENTORY_ID")==null?"":content.get("P_PHYSICAL_INVENTORY_ID").toString();
            Map<String, Object> ls=physicalCountingPkg.GET_PHYINV_CNT_ITEM_CR(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY_ID);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }














}

