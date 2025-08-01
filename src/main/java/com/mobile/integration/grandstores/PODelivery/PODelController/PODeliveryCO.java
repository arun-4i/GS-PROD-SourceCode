package com.mobile.integration.grandstores.PODelivery.PODelController;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.PODelivery.PODelService.PODeliverySO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/podelivery/")
@Api(
    tags = {"PO Delivery Services"}, 
    description = "PO Delivery Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class PODeliveryCO {

    @Autowired
    private PODeliverySO pODeliverySO;


        //PO Receipt Details for PO Delivery
        @RequestMapping(value = "/receiptnum", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getPOReceiptDetails(@RequestBody  Map<String, Object> content) throws ParseException{
            return pODeliverySO.getPOReceiptDetails(content);
        }


      //Get PO Shipment Details Services
      @RequestMapping(value = "/shipmentnum", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> getShipmentNumber(@RequestBody  Map<String, Object> content) throws ParseException{
          return pODeliverySO.getShipmentNumber(content);
      }


      //PO and Release Details Services
      @RequestMapping(value = "/ponumber", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getPONumber(@RequestBody  Map<String, Object> content) throws ParseException{
            return pODeliverySO.getPONumber(content);
      }


       //Get Item Details for PO Delivery
       @RequestMapping(value = "/podeliveryitemdtl", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> getPODeliveryItem(@RequestBody  Map<String, Object> content) throws ParseException{
           return pODeliverySO.getPODeliveryItem(content);
       }


        //Get Item Cross Ref Services
        @RequestMapping(value = "/podeliveryitemdtlcr", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getPODeliveryItemCR(@RequestBody  Map<String, Object> content) throws ParseException{
            return pODeliverySO.getPODeliveryItemCR(content);
        }

        //Get Item Cross Ref Services
        @RequestMapping(value = "/poreleasenumber", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getPOReleaseNumber(@RequestBody  Map<String, Object> content) throws ParseException{
            return pODeliverySO.getPOReleaseNumber(content);
        }

        //Get Item Cross Ref Services
        @RequestMapping(value = "/ponumberrcpt", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getPONumberRCPT(@RequestBody  Map<String, Object> content) throws ParseException{
            return pODeliverySO.getPONumberRCPT(content);
        }

        
}
