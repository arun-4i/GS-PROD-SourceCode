package com.mobile.integration.grandstores.PhysicalCounting.PhysicalCountingController;
import java.text.ParseException;
import java.util.Map;
import com.mobile.integration.grandstores.PhysicalCounting.PhysicalCountingService.PhysicalCountingSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/physicalcounting/")
@Api(
    tags = {"Physical Counting Services"}, 
    description = "Physical Counting Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class PhysicalCountingCO {
 

    @Autowired
    private PhysicalCountingSO physicalCountingso;


     //GET_PHYSICAL_INVENTORIES
     @RequestMapping(value = "/getphysicalinventories", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_PHYSICAL_INVENTORIES(@RequestBody  Map<String, Object> content) throws ParseException{
            return physicalCountingso.GET_PHYSICAL_INVENTORIES(content);
     }

    //GET_PHY_INV_SUBINV_DTLS
    @RequestMapping(value = "/getphyinvsubinvdtl", method = RequestMethod.POST)
          public ResponseEntity<APIResponse> GET_PHY_INV_SUBINV_DTLS(@RequestBody  Map<String, Object> content) throws ParseException{
              return physicalCountingso.GET_PHY_INV_SUBINV_DTLS(content);
       }


    //GET_PHY_INV_QUERY_DTLS
     @RequestMapping(value = "/getphyinvquerydtl", method = RequestMethod.POST)
     public ResponseEntity<APIResponse> GET_PHY_INV_QUERY_DTLS(@RequestBody  Map<String, Object> content) throws ParseException{
         return physicalCountingso.GET_PHY_INV_QUERY_DTLS(content);
  }


    // GET_PHYINV_CNT_ITEM_DTLS
    @RequestMapping(value = "/getphyinvcntitemdtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> GET_PHYINV_CNT_ITEM_DTLS(@RequestBody  Map<String, Object> content) throws ParseException{
           return physicalCountingso.GET_PHYINV_CNT_ITEM_DTLS(content);
    }


     //GET_PHYINV_CNT_ITEM_CR
     @RequestMapping(value = "/getphyinvcntitemcr", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_PHYINV_CNT_ITEM_CR(@RequestBody  Map<String, Object> content) throws ParseException{
            return physicalCountingso.GET_PHYINV_CNT_ITEM_CR(content);
     }

        
}