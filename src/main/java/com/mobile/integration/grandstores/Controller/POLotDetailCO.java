package com.mobile.integration.grandstores.Controller;
import java.text.ParseException;
// import java.util.Map;

import com.mobile.integration.grandstores.Entity.POLotDetailEO;
import com.mobile.integration.grandstores.Services.POLotDetailSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/po")
@Api(
    tags = {"Po Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class POLotDetailCO {
    
    @Autowired
    private POLotDetailSO pOLotDetailso;   

        //Insert PI Count Services
    @RequestMapping(value = "/lotInsertDetail", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertpoConfirm(@RequestBody Iterable<POLotDetailEO> bodyCountDetail) throws ParseException{
        return pOLotDetailso.insertpoConfirm(bodyCountDetail);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getpolotDetail", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getPolotDetail()throws ParseException{
        return pOLotDetailso.getPolotDetail();
    }


    
}