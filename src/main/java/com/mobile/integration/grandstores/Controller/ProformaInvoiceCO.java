package com.mobile.integration.grandstores.Controller;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.Services.ProformaInvoiceSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/proforma/invoice")
@Api(
    tags = {"Proforma Invoice Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class ProformaInvoiceCO {

    @Autowired
    private ProformaInvoiceSO proformainvoiceso;


    //PI Supplier Services
    @RequestMapping(value = "/pisupplier", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPISupplier(@RequestBody  Map<String, Object> content) throws ParseException{
        
        return proformainvoiceso.getPISupplier(content);
    }

    
    //Shipment References Services
    @RequestMapping(value = "/shipmentref", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getShipmentRef(@RequestBody  Map<String, Object> content) throws ParseException{
        return proformainvoiceso.getShipmentRef(content);
    }

    
    //PI Supplier Services
    @RequestMapping(value = "/pinumber", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPINumber(@RequestBody  Map<String, Object> content) throws ParseException{
        return proformainvoiceso.getPINumber(content);
    }

    // GET_PI_ITEM_DTLS
    @RequestMapping(value = "/piitemdetail", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPIItemDetail(@RequestBody  Map<String, Object> content) throws ParseException{
        return proformainvoiceso.getPIItemDetail(content);
    }

    //invoice/piitemcross
    @RequestMapping(value = "/piitemcross", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPIItemCrossRef(@RequestBody  Map<String, Object> content) throws ParseException{
        return proformainvoiceso.getPIItemCrossRef(content);
    }

}