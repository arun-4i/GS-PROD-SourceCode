package com.mobile.integration.grandstores.IODelivery.IODelService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.IODeliveryPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class IODeliverySO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    
    @Autowired
    private IODeliveryPackage ioDeliveryPkg;
    

        // GET_IO_DEL_SHIPMENT_NO
        public ResponseEntity<APIResponse> getIODelShipmentNum(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=ioDeliveryPkg.getIODelShipmentNumber(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}   


        // GET_IO_DEL_SHIP_SRC_ORG
        public ResponseEntity<APIResponse> getIODelShipmentSrcOrg(Map<String, Object> content) {
         
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=ioDeliveryPkg.getIODelShipmentScrOrg(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 

        }   


        // GET_IO_DEL_ITEM_DTLS
        public ResponseEntity<APIResponse> getIODelItemDtl(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_WITH_LOT_FLAG=content.get("P_WITH_LOT_FLAG")==null?"":content.get("P_WITH_LOT_FLAG").toString();
            String P_WITH_SUBINV_LOC=content.get("P_WITH_SUBINV_LOC")==null?"":content.get("P_WITH_SUBINV_LOC").toString();
            
            Map<String, Object> ls=ioDeliveryPkg.getIODelItemDtls(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_WITH_LOT_FLAG, P_WITH_SUBINV_LOC);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   


        // getPODeliveryItem
        public ResponseEntity<APIResponse> getIODelItemDtlCr(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_WITH_LOT_FLAG=content.get("P_WITH_LOT_FLAG")==null?"":content.get("P_WITH_LOT_FLAG").toString();

            Map<String, Object> ls=ioDeliveryPkg.getIODelItemDtlsCr(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_WITH_LOT_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}   







}