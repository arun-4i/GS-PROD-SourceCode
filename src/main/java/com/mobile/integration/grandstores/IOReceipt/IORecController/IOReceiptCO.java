package com.mobile.integration.grandstores.IOReceipt.IORecController;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.IOReceipt.IORecService.IOReceiptSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/ioreceipt/")
@Api(
    tags = {"IO Receipt Services"}, 
    description = "IO Receipt Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class IOReceiptCO {

    @Autowired
    private IOReceiptSO ioReceiptSO;


        //PO Receipt Details for PO Delivery
        @RequestMapping(value = "/shipmentnum", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getIoShipmentNumb(@RequestBody  Map<String, Object> content) throws ParseException{
            return ioReceiptSO.getIoShipmentNumb(content);
        }


      //Get PO Shipment Details Services
      @RequestMapping(value = "/shipmentnumsrcorg", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> getIoShipmentSrcOrg(@RequestBody  Map<String, Object> content) throws ParseException{
          return ioReceiptSO.getIoShipmentSrcOrg(content);
      }


      //PO and Release Details Services
      @RequestMapping(value = "/item", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getIoReceiptItem(@RequestBody  Map<String, Object> content) throws ParseException{
            return ioReceiptSO.getIoReceiptItem(content);
      }


       //Get Item Details for PO Delivery
       @RequestMapping(value = "/itemdtlcr", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> getIoReceiptItDtlCr(@RequestBody  Map<String, Object> content) throws ParseException{
           return ioReceiptSO.getIoReceiptItDtlCr(content);
       }



        
}
