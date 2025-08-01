package com.mobile.integration.grandstores.Controller;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.Services.PODetailSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/po/")
@Api(
    tags = {"PO Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class PODetailCO {
 

    @Autowired
    private PODetailSO poDetailso;

    
    //PO Number Services
    @RequestMapping(value = "/ponumber", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPONumber(@RequestBody  Map<String, Object> content) throws ParseException{
        return poDetailso.getPONumber(content);
    }


    //PO Release Number Services
    @RequestMapping(value = "/poreleasenumber", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPOReleaseNumber(@RequestBody  Map<String, Object> content) throws ParseException{
        return poDetailso.getPOReleaseNumber(content);
    }

    //PO Item Details Services
    @RequestMapping(value = "/poitemdtl", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPOItemDetails(@RequestBody  Map<String, Object> content) throws ParseException{
        return poDetailso.getPOItemDetails(content);
    }

    //PO Item Cross Ref Services
    @RequestMapping(value = "/poitemcrossref", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPOItemCrossRef(@RequestBody  Map<String, Object> content) throws ParseException{
        return poDetailso.getPOItemCrossRef(content);
    }

    //PO RCPT Gen Services
    @RequestMapping(value = "/porcptgen", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPORCPTGen(@RequestBody  Map<String, Object> content) throws ParseException{
        return poDetailso.getPORCPTGen(content);
    }


}