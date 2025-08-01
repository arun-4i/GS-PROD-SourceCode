package com.mobile.integration.grandstores.DeliveryApps.DeliveryAppsCO;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.DeliveryApps.DeliveryAppsSO.DeliveryAppsSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/Deliveryapp/")
@Api(
    tags = {"Delivery Apps Services"}, 
    description = "Delivery Apps  Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class DeliveryAppsCO {
    

    @Autowired
    private DeliveryAppsSO deliveryAppsso;


     // GET_DEL_VEH_DTL  Services
     @RequestMapping(value = "/getdelvehdtl", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_DEL_VEH_DTL() throws ParseException{
            return deliveryAppsso.GET_DEL_VEH_DTL();
     }


      // GET_DEL_INV_DTL  Services
      @RequestMapping(value = "/getdelinvdtl", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> GET_DEL_INV_DTL(@RequestBody  Map<String, Object> content) throws ParseException{
          return deliveryAppsso.GET_DEL_INV_DTL(content);
      }


      // GET_DEL_LINE_DTL  Services
      @RequestMapping(value = "/getdellinedtl", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_DEL_LINE_DTL(@RequestBody  Map<String, Object> content) throws ParseException{
            return deliveryAppsso.GET_DEL_LINE_DTL(content);
      }


       // GET_DEL_REJCODE_DTL  Services
       @RequestMapping(value = "/getdelrejcodedtl", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> GET_DEL_REJCODE_DTL() throws ParseException{
           return deliveryAppsso.GET_DEL_REJCODE_DTL();
       }


}