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
@RequestMapping(value = "/module/rmadelivery/")
@Api(
    tags = {"RMA Delivery Services"}, 
    description = "RMA Receipt Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class RMADeliveryCO {

    @Autowired
    private RMAReceiptSO rmaReceiptSO;


     //GET_RMA_DEL_RECEIPT_NUM
     @RequestMapping(value = "/getrmadelreceiptnum", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_RMA_DEL_RECEIPT_NUM(@RequestBody  Map<String, Object> content) throws ParseException{
            return rmaReceiptSO.GET_RMA_DEL_RECEIPT_NUM(content);
     }


      // getRMACustDetails
      @RequestMapping(value = "/getrmadelordernum", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> GET_RMA_DEL_ORDER_NUM(@RequestBody  Map<String, Object> content) throws ParseException{
          return rmaReceiptSO.GET_RMA_DEL_ORDER_NUM(content);
      }


      // GET_RMA_DEL_ITEM_DTLS  Services
      @RequestMapping(value = "/getrmadelitemdtl", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_RMA_DEL_ITEM_DTLS(@RequestBody  Map<String, Object> content) throws ParseException{
            return rmaReceiptSO.GET_RMA_DEL_ITEM_DTLS(content);
      }


       // GET_RMA_DEL_ITEM_CROSS
       @RequestMapping(value = "/getrmadelitemcross", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> GET_RMA_DEL_ITEM_CROSS(@RequestBody  Map<String, Object> content) throws ParseException{
           return rmaReceiptSO.GET_RMA_DEL_ITEM_CROSS(content);
       }
}