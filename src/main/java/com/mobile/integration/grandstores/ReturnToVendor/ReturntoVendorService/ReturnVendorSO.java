package com.mobile.integration.grandstores.ReturnToVendor.ReturntoVendorService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.ReturnToVendorPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class ReturnVendorSO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    
    @Autowired
    private ReturnToVendorPackage returnvendorpkg;
    
        // 2411054414 - Param changes
        // GET_RTV_RECEIPT_NUM
        public ResponseEntity<APIResponse> getRTVRequestNumber(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_REQUEST_NUM=content.get("P_REQUEST_NUM")==null?"":content.get("P_REQUEST_NUM").toString();
           // String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=returnvendorpkg.getRTVRequestNumber(P_INVENTORY_ORG_ID, P_REQUEST_NUM);
            
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }
        
        // GET_RTV_SHIPMENT_NUM
        public ResponseEntity<APIResponse> getRTVShipmentNumber(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=returnvendorpkg.getRTVShipmentNumber(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_LIKE_COND_FLAG);
            
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }
        

       // GET_RTV_PO_NUM
       public ResponseEntity<APIResponse> getRTVPONumber(Map<String, Object> content) {
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
        String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();
        
        Map<String, Object> ls=returnvendorpkg.getRTVPONumber(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_LIKE_COND_FLAG);
        
                    APIResponse api=new APIResponse();
                    api.setData(ls);
                    api.setStatus(HttpStatus.OK.value());    
                    return ResponseEntity.ok().body(api); 
       }


      // GET_RTV_RELEASE_NUM
      public ResponseEntity<APIResponse> getRTVRelease_num(Map<String, Object> content) {
                String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
                String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
                String P_RELEASE_NUM=content.get("P_RELEASE_NUM")==null?"":content.get("P_RELEASE_NUM").toString();
                String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=returnvendorpkg.getRTVReleaseNumber(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RELEASE_NUM, P_LIKE_COND_FLAG);
                
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
      }
       
  // GET_RTV_ITEM_DTLS
  public ResponseEntity<APIResponse> getRtvItemDetail(Map<String, Object> content) {
   /* String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
    String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
    String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
    String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
    String P_RELEASE_NUM=content.get("P_RELEASE_NUM")==null?"":content.get("P_RELEASE_NUM").toString();
    String P_WITH_SUBINV_LOC=content.get("P_WITH_SUBINV_LOC")==null?"":content.get("P_WITH_SUBINV_LOC").toString();
    String p_item_id=content.get("p_item_id")==null?"":content.get("p_item_id").toString();*/ 

    String P_REQUEST_ID=content.get("P_REQUEST_ID")==null?"":content.get("P_REQUEST_ID").toString();


   // Map<String, Object> ls=returnvendorpkg.getRTVItemDetail(P_INVENTORY_ORG_ID, P_RECEIPT_NUM, P_SHIPMENT_NUM, P_PO_NUMBER, P_RELEASE_NUM, P_WITH_SUBINV_LOC, p_item_id);
    
   Map<String, Object> ls=returnvendorpkg.getRTVItemDetail(P_REQUEST_ID);
    
    APIResponse api=new APIResponse();
    api.setData(ls);
    api.setStatus(HttpStatus.OK.value());    
    return ResponseEntity.ok().body(api); 
} 

  // GET_RTV_ITEM_DTLS_CR
  public ResponseEntity<APIResponse> getRtvItemDetailCr(Map<String, Object> content) {
    String P_REQUEST_ID=content.get("P_REQUEST_ID")==null?"":content.get("P_REQUEST_ID").toString();

    Map<String, Object> ls=returnvendorpkg.getRTVItemDetailCr(P_REQUEST_ID);
    
    APIResponse api=new APIResponse();
    api.setData(ls);
    api.setStatus(HttpStatus.OK.value());    
    return ResponseEntity.ok().body(api); 
} 


  // get_rtv_item_code
  public ResponseEntity<APIResponse> get_rtv_item_code(Map<String, Object> content) {
    String p_inventory_org_id=content.get("p_inventory_org_id")==null?"":content.get("p_inventory_org_id").toString();

    Map<String, Object> ls=returnvendorpkg.get_rtv_item_code(p_inventory_org_id);
    
    APIResponse api=new APIResponse();
    api.setData(ls);
    api.setStatus(HttpStatus.OK.value());    
    return ResponseEntity.ok().body(api); 
} 






}