package com.mobile.integration.grandstores.PickOrderNumber.PickOrderService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.PickOrderNumberPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class PickOrderNumberSO  extends NamedParameterJdbcDaoSupport{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
    
    @Autowired
    private PickOrderNumberPkg pickOrderNumberPkg;

    // UPDATE_DELIVERY_DETAILS
    public ResponseEntity<APIResponse> UPDATE_DELIVERY_DETAILS(Map<String, Object> content) {
        String P_DEL_LINE_ID=content.get("P_DEL_LINE_ID")==null?"":content.get("P_DEL_LINE_ID").toString();
        String P_ACTUAL_DATE=content.get("P_ACTUAL_DATE")==null?"":content.get("P_ACTUAL_DATE").toString();
        String P_ACTUAL_TIME=content.get("P_ACTUAL_TIME")==null?"":content.get("P_ACTUAL_TIME").toString();
        String P_STATUS=content.get("P_STATUS")==null?"":content.get("P_STATUS").toString();
        String P_REJ_CODE=content.get("P_REJ_CODE")==null?"":content.get("P_REJ_CODE").toString();
        String P_REJ_MARKS=content.get("P_REJ_MARKS")==null?"":content.get("P_REJ_MARKS").toString();
        Map<String, Object> ls=pickOrderNumberPkg.UPDATE_DELIVERY_DETAILS(P_DEL_LINE_ID, P_ACTUAL_DATE, P_ACTUAL_TIME, P_STATUS, P_REJ_CODE, P_REJ_MARKS);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
    }   

        // GET_PICK_ORDER_NUM
        public ResponseEntity<APIResponse> GET_PICK_ORDER_NUM(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();

            Map<String, Object> ls=pickOrderNumberPkg.GET_PICK_ORDER_NUM(P_INVENTORY_ORG_ID, P_RESOURCE_ID);
                APIResponse api=new APIResponse();
                api.setData(ls);
                api.setStatus(HttpStatus.OK.value());    
                return ResponseEntity.ok().body(api); 
        } 

                // GET_PICK_MOVE_ORDER
        public ResponseEntity<APIResponse> GET_PICK_MOVE_ORDER(Map<String, Object> content) {
            String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
            String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();
        
                    Map<String, Object> ls=pickOrderNumberPkg.GET_PICK_MOVE_ORDER(P_INVENTORY_ORG_ID, P_RESOURCE_ID);
                        APIResponse api=new APIResponse();
                        api.setData(ls);
                        api.setStatus(HttpStatus.OK.value());    
                        return ResponseEntity.ok().body(api); 
        } 


            // GET_PICK_PICKSLIP_NUM
            public ResponseEntity<APIResponse> GET_PICK_PICKSLIP_NUM(Map<String, Object> content) {
                String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
                String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();
                        
                Map<String, Object> ls=pickOrderNumberPkg.GET_PICK_PICKSLIP_NUM(P_INVENTORY_ORG_ID, P_RESOURCE_ID);
                                        APIResponse api=new APIResponse();
                                        api.setData(ls);
                                        api.setStatus(HttpStatus.OK.value());    
                                        return ResponseEntity.ok().body(api); 
            } 


            // GET_PICK_ORDER_DETAILS
            public ResponseEntity<APIResponse> GET_PICK_ORDER_DETAILS(Map<String, Object> content) {
                String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
                String P_ORDER_NUM=content.get("P_ORDER_NUM")==null?"":content.get("P_ORDER_NUM").toString();
                String P_MO_NUM=content.get("P_MO_NUM")==null?"":content.get("P_MO_NUM").toString();
                String P_PICKSLIP_NUM=content.get("P_PICKSLIP_NUM")==null?"":content.get("P_PICKSLIP_NUM").toString();
                String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();
                               
                Map<String, Object> ls=
                pickOrderNumberPkg.GET_PICK_ORDER_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM, P_MO_NUM, P_PICKSLIP_NUM, P_RESOURCE_ID);
                APIResponse api=new APIResponse();
                api.setData(ls);
                api.setStatus(HttpStatus.OK.value());    
                return ResponseEntity.ok().body(api); 
            }

            // GET_PICK_DETAIL_CR
            public ResponseEntity<APIResponse> GET_PICK_DETAIL_CR(Map<String, Object> content) {
                String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
                String P_ORDER_NUM=content.get("P_ORDER_NUM")==null?"":content.get("P_ORDER_NUM").toString();
                String P_MO_NUM=content.get("P_MO_NUM")==null?"":content.get("P_MO_NUM").toString();
                String P_PICKSLIP_NUM=content.get("P_PICKSLIP_NUM")==null?"":content.get("P_PICKSLIP_NUM").toString();
                String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();
                Map<String, Object> ls=
                    pickOrderNumberPkg.GET_PICK_DETAIL_CR(P_INVENTORY_ORG_ID, P_ORDER_NUM, P_MO_NUM, P_PICKSLIP_NUM, P_RESOURCE_ID);
                APIResponse api=new APIResponse();
                api.setData(ls);
                api.setStatus(HttpStatus.OK.value());    
                return ResponseEntity.ok().body(api); 
            }





}