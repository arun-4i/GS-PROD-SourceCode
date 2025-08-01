package com.mobile.integration.grandstores.GSBinLocation.GSController;
import java.text.ParseException;
// import java.util.Map;

import com.mobile.integration.grandstores.GSBinLocation.GLEntity.SpotCheckEO;
import com.mobile.integration.grandstores.GSBinLocation.GSService.SpotCheckSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/spot/")
@Api(
    tags = {"GS Spot Check Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class SpotCheckCO {
    

    @Autowired
    private SpotCheckSO spotCheckco;   

        //Insert PI Count Services
    @RequestMapping(value = "/insertspot", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertspot(@RequestBody Iterable<SpotCheckEO> bodydtl) throws ParseException{
        return spotCheckco.insertspot(bodydtl);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getspot", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getSpotCheck()throws ParseException{
        return spotCheckco.getSpotCheck();
    }
}