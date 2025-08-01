package com.mobile.integration.grandstores.RMAReceipt.RMAReceiptService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.RMADeliveryPkg;
import com.mobile.integration.grandstores.PackageCalling.RMAReceiptPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class RMAReceiptSO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    
    @Autowired
    private RMAReceiptPkg rmaReceiptPkg;

    @Autowired
    private RMADeliveryPkg rmaDeliveryPkg;
    

        // GET_RMA_DETAILS
        public ResponseEntity<APIResponse> getRMADetail(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            Map<String, Object> ls=rmaReceiptPkg.GET_RMA_DETAILS(P_INVENTORY_ORG_ID);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   


        // GET_RMA_CUST_DETAILS
        public ResponseEntity<APIResponse> getRMACustDetails(Map<String, Object> content) {
         
            String P_ORDER_NUM=content.get("P_ORDER_NUM")==null?"":content.get("P_ORDER_NUM").toString();

            Map<String, Object> ls=rmaReceiptPkg.GET_RMA_CUST_DETAILS(P_ORDER_NUM);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 

        }   


        // GET_RMA_ITEM_DETAILS
        public ResponseEntity<APIResponse> getRMAItemDetail(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_ORDER_NUM=content.get("P_ORDER_NUM")==null?"":content.get("P_ORDER_NUM").toString();
            Map<String, Object> ls=rmaReceiptPkg.GET_RMA_ITEM_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   


        // GET_RMA_ITEM_CROSS_REF
        public ResponseEntity<APIResponse> getRMAItemCrossRef(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_ORDER_NUM=content.get("P_ORDER_NUM")==null?"":content.get("P_ORDER_NUM").toString();
            Map<String, Object> ls=rmaReceiptPkg.GET_RMA_ITEM_CROSS_REF(P_INVENTORY_ORG_ID, P_ORDER_NUM);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   

        // GET_RMA_DEL_RECEIPT_NUM
        public ResponseEntity<APIResponse> GET_RMA_DEL_RECEIPT_NUM(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            Map<String, Object> ls=rmaDeliveryPkg.GET_RMA_DEL_RECEIPT_NUM(P_INVENTORY_ORG_ID);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }

        // GET_RMA_DEL_ORDER_NUM
        public ResponseEntity<APIResponse> GET_RMA_DEL_ORDER_NUM(Map<String, Object> content) {
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        Map<String, Object> ls=rmaDeliveryPkg.GET_RMA_DEL_ORDER_NUM(P_INVENTORY_ORG_ID);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
        }

        // GET_RMA_DEL_ITEM_DTLS
        public ResponseEntity<APIResponse> GET_RMA_DEL_ITEM_DTLS(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_ORDER_NUMBER=content.get("P_ORDER_NUMBER")==null?"":content.get("P_ORDER_NUMBER").toString();
            String P_RECEIPT_NUMBER=content.get("P_RECEIPT_NUMBER")==null?"":content.get("P_RECEIPT_NUMBER").toString();
            String P_WITH_SUBINV_LOC=content.get("P_WITH_SUBINV_LOC")==null?"":content.get("P_WITH_SUBINV_LOC").toString();
            
            Map<String, Object> ls=rmaDeliveryPkg.GET_RMA_DEL_ITEM_DTLS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER, P_WITH_SUBINV_LOC);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
            }

        // GET_RMA_DEL_ITEM_DTLS
        public ResponseEntity<APIResponse> GET_RMA_DEL_ITEM_CROSS(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_ORDER_NUMBER=content.get("P_ORDER_NUMBER")==null?"":content.get("P_ORDER_NUMBER").toString();
            String P_RECEIPT_NUMBER=content.get("P_RECEIPT_NUMBER")==null?"":content.get("P_RECEIPT_NUMBER").toString();
            
            Map<String, Object> ls=rmaDeliveryPkg.GET_RMA_DEL_ITEM_CROSS(P_INVENTORY_ORG_ID, P_ORDER_NUMBER, P_RECEIPT_NUMBER);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
            }


        // GET_RMA_DETAILS
        public ResponseEntity<APIResponse> getBUNDLE_ITEM(Map<String, Object> content) {
            String P_ORDER_NO=content.get("P_ORDER_NO")==null?"":content.get("P_ORDER_NO").toString();
            Map<String, Object> ls=rmaReceiptPkg.BUNDLE_ITEM(P_ORDER_NO);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   



}