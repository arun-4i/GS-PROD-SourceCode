package com.mobile.integration.grandstores.DeliveryConfirmation.DeliveryConfirmControl;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.DeliveryConfirmation.DeliveryConfirmService.DeliveryConfirmSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/cofirmation/")
@Api(
    tags = {"Delivery Confirm Services"}, 
    description = "IO Delivery Confirm Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class DeliveryConfirmCO {
    
    @Autowired
    private DeliveryConfirmSO deliveryConfirmSO;

    //getIODelShipmentNum
    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getDelivery() throws ParseException{
        return deliveryConfirmSO.getDelivery();
    }
    
    // getIODelShipmentSrcOrg
    @RequestMapping(value = "/deliverydetail", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getDeliveryDetail(@RequestBody  Map<String, Object> content) throws ParseException{
        return deliveryConfirmSO.getDeliveryDetail(content);
    }
    

    
}