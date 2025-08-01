package com.mobile.integration.grandstores.SpotCheckDirectSync.SpotCheckDirectSyncCO;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import com.mobile.integration.grandstores.SpotCheckDirectSync.SpotCheckDirectSyncSO.SpotCheckDirectSyncSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

@RestController
@RequestMapping(value = "/module/spotCheckDirectSync/")
@Api(
    tags = {"Spot Check Direct Sync"}, 
    description = "Spot Check Direct Sync services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class SpotCheckDirectSyncCO {

    @Autowired
    private SpotCheckDirectSyncSO spotCheckDirectSyncSO;

    //GET_SPOT_CHECK_DIRECT_SYNC
     @RequestMapping(value = "/spotcheckdirectsync", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_SPOT_CHECK_DIRECT_SYNC(@RequestBody  Map<String, Object> content) throws ParseException{
            System.out.println("inside spot check direct sync co");
            return spotCheckDirectSyncSO.GET_SPOT_CHECK_DIRECT_SYNC(content);
     }
    
}
