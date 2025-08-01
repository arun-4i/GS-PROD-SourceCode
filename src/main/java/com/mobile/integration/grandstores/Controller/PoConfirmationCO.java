package com.mobile.integration.grandstores.Controller;
import java.sql.SQLException;
import java.text.ParseException;
// import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.integration.grandstores.Entity.PoConfirmationEO;
import com.mobile.integration.grandstores.Services.PoConfirmationSO;
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
@RequestMapping(value = "/module/poconfirm")
@Api(
    tags = {"Po Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class PoConfirmationCO {
    
    @Autowired
    private PoConfirmationSO poConfirmationso;
    private static final Logger logger = LoggerFactory.getLogger(PoConfirmationCO.class);   

        //Insert PI Count Services
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertpoConfirm(@RequestBody Iterable<PoConfirmationEO> bodyCountDetail) throws ParseException, JsonProcessingException, SerialException, SQLException {
        logger.debug("Entering the insertpoConfirm method; "+bodyCountDetail);
        
        return poConfirmationso.insertpoConfirm(bodyCountDetail);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getconfirm", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getpoConfirm()throws ParseException{
        return poConfirmationso.getpoConfirm();
    }

}