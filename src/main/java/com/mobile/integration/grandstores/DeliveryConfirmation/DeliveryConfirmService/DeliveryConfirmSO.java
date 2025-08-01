package com.mobile.integration.grandstores.DeliveryConfirmation.DeliveryConfirmService;

import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.IODeliveryConfirmPackage;
import com.mobile.integration.grandstores.PackageCalling.IODeliveryPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class DeliveryConfirmSO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    @Autowired
    private IODeliveryConfirmPackage ioDeliveryConfirmPkg;

    public ResponseEntity<APIResponse> getDeliveryDetail(Map<String, Object> content) {
        // String P_DELIVERY_NO=content.get("P_DELIVERY_NO")==null?"":content.get("P_DELIVERY_NO").toString();
        String p_driver_id=content.get("p_driver_id")==null?"":content.get("p_driver_id").toString();
 
        Map<String, Object> ls=ioDeliveryConfirmPkg.getIODelShipmentScrOrg(p_driver_id);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }
 
    public ResponseEntity<APIResponse> getDelivery() {
 
        Map<String, Object> ls=ioDeliveryConfirmPkg.getDelivery();

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }



}