package com.mobile.integration.grandstores.RMAReceipt.RMAReceiptController;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.IODelivery.IODelService.IODeliverySO;
import com.mobile.integration.grandstores.PODelivery.PODelService.PODeliverySO;
import com.mobile.integration.grandstores.RMAReceipt.RMAReceiptService.RMAReceiptSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/rmareceipt/")
@Api(
    tags = {"RMA Receipt Services"}, 
    description = "RMA Receipt Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class RMAReceiptCO {

    @Autowired
    private RMAReceiptSO rmaReceiptSO;


     //GET_RMA_DETAILS
     @RequestMapping(value = "/getrmadetail", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getRMADetail(@RequestBody  Map<String, Object> content) throws ParseException{
            return rmaReceiptSO.getRMADetail(content);
     }


      // getRMACustDetails
      @RequestMapping(value = "/rmacustdetails", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> getRMACustDetails(@RequestBody  Map<String, Object> content) throws ParseException{
          return rmaReceiptSO.getRMACustDetails(content);
      }


      // getRMAItemDetail  Services
      @RequestMapping(value = "/rmaitemdetail", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getRMAItemDetail(@RequestBody  Map<String, Object> content) throws ParseException{
            return rmaReceiptSO.getRMAItemDetail(content);
      }


       // getRMAItemCrossRef
       @RequestMapping(value = "/rmaitemcrossRef", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> getRMAItemCrossRef(@RequestBody  Map<String, Object> content) throws ParseException{
           return rmaReceiptSO.getRMAItemCrossRef(content);
       }

     //getBUNDLE_ITEM
     @RequestMapping(value = "/getbundle", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getBUNDLE_ITEM(@RequestBody  Map<String, Object> content) throws ParseException{
            return rmaReceiptSO.getBUNDLE_ITEM(content);
     }
        
}
