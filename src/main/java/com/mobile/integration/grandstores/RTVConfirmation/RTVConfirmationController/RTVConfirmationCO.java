package com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationController;
import java.text.ParseException;

import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationDto.RTVConfirmationDto;
import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationService.RTVConfirmationSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/rtvconfirmation")
@Api(
    tags = {"RTV Confirmation"}, 
    description = "RTV Confirmation", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class RTVConfirmationCO {
 
    private static final Logger logger = LoggerFactory.getLogger(RTVConfirmationCO.class);

    @Autowired
    private RTVConfirmationSO rtvconfirmationso;   

    //UPDATE_RTV_REQ_LINES
    @RequestMapping(value = "/updateRtvLines", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> UPDATE_RTV_REQ_LINES(@RequestBody Iterable<RTVConfirmationDto> bodydtl) throws ParseException{ 
        logger.debug("Entering to RTV lines Update method; "+bodydtl.toString());
        return rtvconfirmationso.updateRtvReqLines(bodydtl);
    }

    //insertRTVConfirmation
    /*@RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertRTVConfirmation(@RequestBody Iterable<RTVConfirmationEO> bodydtl) throws ParseException{
        System.out.println("hello RTV CO: ");
        return rtvconfirmationso.insertRTVConfirmation(bodydtl);
    }*/

    //UPDATE_RTV_REQ_LINES
    /*  @RequestMapping(value = "/updateRtvLines", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> UPDATE_RTV_REQ_LINES(@RequestBody Iterable<RTVConfirmationEO> bodydtl) throws ParseException{ 
            logger.debug("Entering to RTV lines Update method; "+bodydtl.toString());
            return rtvconfirmationso.updateRtvReqLines(bodydtl);
     }*/

}