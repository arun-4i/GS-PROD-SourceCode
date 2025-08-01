package com.mobile.integration.grandstores.IOReceipt.IORecService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.IOReceiptPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class IOReceiptSO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    
    @Autowired
    private IOReceiptPackage ioReceiptPkg;
    

        // GET_IO_SHIPMENT_NO
        public ResponseEntity<APIResponse> getIoShipmentNumb(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=ioReceiptPkg.getIoShipmentNumber(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }
        
        // GET_IO_SHIP_SRC_ORG
        public ResponseEntity<APIResponse> getIoShipmentSrcOrg(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
            String P_LIKE_COND_FLAG=content.get("P_LIKE_COND_FLAG")==null?"":content.get("P_LIKE_COND_FLAG").toString();

            Map<String, Object> ls=ioReceiptPkg.getIoShipSrc(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_LIKE_COND_FLAG);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }
        

       // GET_IO_RCPT_ITEM_DTLS
       public ResponseEntity<APIResponse> getIoReceiptItem(Map<String, Object> content) {
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
        String P_WITH_LOT_FLAG=content.get("P_WITH_LOT_FLAG")==null?"":content.get("P_WITH_LOT_FLAG").toString();
        
        Map<String, Object> ls=ioReceiptPkg.getIoReceiptItem(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_WITH_LOT_FLAG);
        
                    APIResponse api=new APIResponse();
                    api.setData(ls);
                    api.setStatus(HttpStatus.OK.value());    
                    return ResponseEntity.ok().body(api); 
       }


      // GET_IO_RCPT_ITEM_DTLS
      public ResponseEntity<APIResponse> getIoReceiptItDtlCr(Map<String, Object> content) {
                String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
                String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
                String P_WITH_LOT_FLAG=content.get("P_WITH_LOT_FLAG")==null?"":content.get("P_WITH_LOT_FLAG").toString();
                
        Map<String, Object> ls=ioReceiptPkg.getIoReceiptItemDtlCr(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_WITH_LOT_FLAG);
                
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
      }
       
  // GET_LOCATOR_SPOT_CHK
  public ResponseEntity<APIResponse> getlocationSpot(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
                        
    Map<String, Object> ls=ioReceiptPkg.getLocatorSpotChk(P_INVENTORY_ORG_ID);
            
    APIResponse api=new APIResponse();
    api.setData(ls);
    api.setStatus(HttpStatus.OK.value());    
    return ResponseEntity.ok().body(api); 
  } 





}