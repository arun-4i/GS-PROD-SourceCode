package com.mobile.integration.grandstores.RMAConfirm.RMAConfirmController;
import java.sql.SQLException;
import java.text.ParseException;

import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmEntity.RMAConfirmEO;
import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmEntity.RMAConfirmMO;
import com.mobile.integration.grandstores.RMAConfirm.RMAConfirmService.RMAConfirmSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/rma/confirm")
@Api(
    tags = {"RMA Confirm"}, 
    description = "Grandstores-RMA Confirm", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class RMAConfirmCO {
 

    @Autowired
    private RMAConfirmSO rmaConfirmso;   
    private static final Logger logger = LoggerFactory.getLogger(RMAConfirmCO.class);


    //insertRMAConfirm Services
    @RequestMapping(value = "/insertmo", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertRMAConfirm(@RequestBody Iterable<RMAConfirmEO> bodydtl) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the RMA insertRMAConfirm method; "+bodydtl);
        return rmaConfirmso.insertRMAConfirmRO(bodydtl);
    }

    //combine 2 services module/rma/confirm/insertmo and mo/confirm/insertpick 
    @RequestMapping(value = "/rmainsertmo", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertRMAandMOconfirm(@RequestBody RMAConfirmMO bodydtl)throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering insertRMAandMOconfirm method; "+bodydtl);
        return rmaConfirmso.insertRMAConfirmMO(bodydtl);
    }   



    //getBinTransfer Services
    @RequestMapping(value = "/getallmo", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransfer()throws ParseException{
        return rmaConfirmso.getRMAConfirmRO();
    }




}