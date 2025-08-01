package com.mobile.integration.grandstores.PODelivery.PODelService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.PODeliveryPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class PODeliverySO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    
    @Autowired
    private PODeliveryPackage poDeliveryPkg;
    

        // getPOReceiptDetails
        public ResponseEntity<APIResponse> getPOReceiptDetails(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=poDeliveryPkg.poReceiptDetails(P_INVENTORY_ORG_ID, P_RECEIPT_NUM, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}   


        // getShipmentNumber
        public ResponseEntity<APIResponse> getShipmentNumber(Map<String, Object> content) {
         
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=poDeliveryPkg.poShipmentDetails(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 

        }   


        // getPONumber
        public ResponseEntity<APIResponse> getPONumber(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=poDeliveryPkg.poReleaseDetails(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   


        // getPODeliveryItem
        public ResponseEntity<APIResponse> getPODeliveryItem(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
            String P_RELEASE_NUM=content.get("P_RELEASE_NUM")==null?"":content.get("P_RELEASE_NUM").toString();
            String P_WITH_SUBINV_LOC=content.get("P_WITH_SUBINV_LOC")==null?"":content.get("P_WITH_SUBINV_LOC").toString();

            Map<String, Object> ls=poDeliveryPkg.getPOItemDetails(P_INVENTORY_ORG_ID, P_RECEIPT_NUM, P_SHIPMENT_NUM, P_PO_NUMBER, P_RELEASE_NUM, P_WITH_SUBINV_LOC);
            
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api);
}   


        // getPODeliveryItemCR
        public ResponseEntity<APIResponse> getPODeliveryItemCR(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
            String P_RELEASE_NUM=content.get("P_RELEASE_NUM")==null?"":content.get("P_RELEASE_NUM").toString();
            
            Map<String, Object> ls=poDeliveryPkg.
            getPOItemCrossRef(P_INVENTORY_ORG_ID, P_RECEIPT_NUM, P_SHIPMENT_NUM, P_PO_NUMBER, P_RELEASE_NUM);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api);  
}   



        // getPOreleaseNumber
        public ResponseEntity<APIResponse> getPOReleaseNumber(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
            String P_RELEASE_NUM=content.get("P_RELEASE_NUM")==null?"":content.get("P_RELEASE_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();


            Map<String, Object> ls=poDeliveryPkg.
            getPOReleaseNumber(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RELEASE_NUM, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api);  
        }

        // getPONumberRCPT
        public ResponseEntity<APIResponse> getPONumberRCPT(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();


            Map<String, Object> ls=poDeliveryPkg.getPONumberRCPT(P_INVENTORY_ORG_ID, P_PO_NUMBER,  P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api);  
        }   







}