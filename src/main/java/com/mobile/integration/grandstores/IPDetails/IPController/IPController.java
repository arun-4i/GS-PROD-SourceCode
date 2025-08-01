package com.mobile.integration.grandstores.IPDetails.IPController;

import com.mobile.integration.grandstores.IPDetails.IPService.IPService;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module")
@Api(
    tags = {"IP Details"}, 
    description = "Grandstores-IP Details",
    produces = "application/json"
    )
public class IPController {

    @Autowired
    private IPService ipservice;

    //Get PI Count Services
    @RequestMapping(value = "/getIPDetails", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getIPDetails()throws ParseException{
        return ipservice.getIPDetails();
    }

}
