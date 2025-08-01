package com.mobile.integration.grandstores.DeliveryApps.DeliveryAppsSO;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.DeliveryApps;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class DeliveryAppsSO  extends NamedParameterJdbcDaoSupport{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }
 
    
    @Autowired
    private DeliveryApps deliveryAppsPkg;



        // GET_DEL_VEH_DTL
        public ResponseEntity<APIResponse> GET_DEL_VEH_DTL() {
            Map<String, Object> ls=deliveryAppsPkg.GET_DEL_VEH_DTL();
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   


        
        // GET_DEL_INV_DTL
        public ResponseEntity<APIResponse> GET_DEL_INV_DTL(Map<String, Object> content) {
            String P_VEHICLE_NO=content.get("P_VEHICLE_NO")==null?"":content.get("P_VEHICLE_NO").toString();
            Map<String, Object> ls=deliveryAppsPkg.GET_DEL_INV_DTL(P_VEHICLE_NO);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   


        
        // GET_DEL_LINE_DTL
        public ResponseEntity<APIResponse> GET_DEL_LINE_DTL(Map<String, Object> content) {
            String P_VEHICLE_NO=content.get("P_VEHICLE_NO")==null?"":content.get("P_VEHICLE_NO").toString();
            Map<String, Object> ls=deliveryAppsPkg.GET_DEL_LINE_DTL(P_VEHICLE_NO);
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   

        
        // GET_DEL_REJCODE_DTL
        public ResponseEntity<APIResponse> GET_DEL_REJCODE_DTL() {
            Map<String, Object> ls=deliveryAppsPkg.GET_DEL_REJCODE_DTL();
            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }   


}

