package com.mobile.integration.grandstores.Controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.mobile.integration.grandstores.Entity.PiCountDetailEO;
import com.mobile.integration.grandstores.Services.PiCountDetailSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/pi")
@Api(
    tags = {"Proforma Invoice Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class PiCountDetailCO {

    @Autowired
    private PiCountDetailSO picountdetailso;   


        //Insert PI Count Services
        @RequestMapping(value = "/count", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> insertpiDetails(@RequestBody Iterable<PiCountDetailEO> bodyCountDetail) throws ParseException{
            return picountdetailso.insertpiDetails(bodyCountDetail);
        }

        //Get PI Count Services
        @RequestMapping(value = "/get", method = RequestMethod.GET)
        public ResponseEntity<APIResponse> getpiDetails()throws ParseException{
            return picountdetailso.getpiDetails();
        }

        //PI Supplier Services
        @RequestMapping(value = "/docnumber", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getDocNumber(@RequestBody  Map<String, Object> content) throws ParseException{
            
            return picountdetailso.getDocNumber(content);
        }


    //Shipment References Services
    @RequestMapping(value = "/getTotalQty", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getTotalQty(@RequestBody  Map<String, Object> content) throws ParseException{
        return picountdetailso.getTotalQty(content);
    }

        // //Shipment References Services
        // @RequestMapping(value = "/getsearchdetail", method = RequestMethod.POST)
        // public ResponseEntity<APIResponse> getSearchDetail(@RequestBody  Map<String, Object> content) throws ParseException{
        //     return picountdetailso.getSearchDetail(content);
        // }


        @RequestMapping(value = "/getsearchdetail" ,method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getSearchDetail(@RequestBody  Map<String, Object> content){ 
            return picountdetailso.getSearchDetail(content);
        }
    


    }