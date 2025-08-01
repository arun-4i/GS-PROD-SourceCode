package com.mobile.integration.grandstores.GSBinLocation.GSController;
import java.text.ParseException;
// import java.util.Map;

import com.mobile.integration.grandstores.GSBinLocation.GLEntity.BinsTransferEO;
import com.mobile.integration.grandstores.GSBinLocation.GSService.BinsTransferSO;
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
public class BinsTransferCO {

    @Autowired
    private BinsTransferSO binTransferso;   

    //Insert PI Count Services
    @RequestMapping(value = "/insertbintransfer", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertBinTransfer(@RequestBody Iterable<BinsTransferEO> bodydtl) throws ParseException{
        return binTransferso.insertBinTransfer(bodydtl);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getbintransfer", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransfer()throws ParseException{
        return binTransferso.getBinTransfer();
    }



}