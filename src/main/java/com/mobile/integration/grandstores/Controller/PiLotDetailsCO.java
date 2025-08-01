package com.mobile.integration.grandstores.Controller;
import java.text.ParseException;
// import java.util.Map;

import com.mobile.integration.grandstores.Entity.PiLotDetailsEO;
import com.mobile.integration.grandstores.Services.PiLotDetailsSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/pilot")
@Api(
    tags = {"Proforma Invoice Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class PiLotDetailsCO {
    
    @Autowired
    private PiLotDetailsSO piLotDetailsso;   

        //Insert PI Count Services
        @RequestMapping(value = "/detail", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> insertpiDetails(@RequestBody Iterable<PiLotDetailsEO> bodyCountDetail) throws ParseException{
            return piLotDetailsso.insertpiLotDetails(bodyCountDetail);
        }

        //Get PI Count Services
        @RequestMapping(value = "/getdetail", method = RequestMethod.GET)
        public ResponseEntity<APIResponse> getpiDetails()throws ParseException{
            return piLotDetailsso.getpiLotDetails();
        }

}