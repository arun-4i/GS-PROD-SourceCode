package com.mobile.integration.grandstores.IODelivery.IODelController;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.IODelivery.IODelService.IODeliverySO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/iodelivery/")
@Api(
    tags = {"IO Delivery Services"}, 
    description = "IO Delivery Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class IODeliveryCO {

    @Autowired
    private IODeliverySO ioDeliverySO;


        //getIODelShipmentNum
        @RequestMapping(value = "/shipmentnum", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getIODelShipmentNum(@RequestBody  Map<String, Object> content) throws ParseException{
            return ioDeliverySO.getIODelShipmentNum(content);
        }


      // getIODelShipmentSrcOrg
      @RequestMapping(value = "/shipmentsrcorg", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> getIODelShipmentSrcOrg(@RequestBody  Map<String, Object> content) throws ParseException{
          return ioDeliverySO.getIODelShipmentSrcOrg(content);
      }


      // getIODelItemDtl  Services
      @RequestMapping(value = "/iodelitemdtl", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getIODelItemDtl(@RequestBody  Map<String, Object> content) throws ParseException{
            return ioDeliverySO.getIODelItemDtl(content);
      }


       // getIODelItemDtlCr
       @RequestMapping(value = "/iodelitemdtlcr", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> getIODelItemDtlCr(@RequestBody  Map<String, Object> content) throws ParseException{
           return ioDeliverySO.getIODelItemDtlCr(content);
       }


        
}
