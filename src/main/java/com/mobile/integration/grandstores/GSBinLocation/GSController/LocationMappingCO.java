package com.mobile.integration.grandstores.GSBinLocation.GSController;
import java.text.ParseException;
// import java.util.Map;

import com.mobile.integration.grandstores.GSBinLocation.GLEntity.LocationMappingEO;
import com.mobile.integration.grandstores.GSBinLocation.GSService.LocationMappingSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/gsloc/")
@Api(
    tags = {"GS Location Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class LocationMappingCO {

        
    @Autowired
    private LocationMappingSO locationMappingSO;   

        //Insert PI Count Services
    @RequestMapping(value = "/insertdtl", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertpoConfirm(@RequestBody Iterable<LocationMappingEO> bodydtl) throws ParseException{
        return locationMappingSO.insertLocationMap(bodydtl);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getdtl", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getPolotDetail()throws ParseException{
        return locationMappingSO.getLocationMap();
    }


    
}