package com.mobile.integration.grandstores.MoveOrderAPI.MoveOrderController;

import java.util.Map;


import com.mobile.integration.grandstores.MoveOrderAPI.MoveOrderServices.MoveOrderSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

// import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// import com.mobile.integration.grandstores.ExceptionHandler.*;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/moveorder")
@Api(
    tags = {"Move Order Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class MoveOrderCO {
    
    @Autowired
    private MoveOrderSO moveOrderso;


    //get Invoice Organization Services
    @RequestMapping(value = "/invorg", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getInvoiceOrganization(@RequestBody  Map<String, Object> content){
            return moveOrderso.getInvoiceOrganization(content);
    }

    //get Invoice Organization Services
    @RequestMapping(value = "/getinvorganization", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getInvOrganization(@RequestBody  Map<String, Object> content){
            return moveOrderso.getInvOrganization(content);
    }

    //get MO Detail Services
    @RequestMapping(value = "/getmodetail", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getMoDetailService(@RequestBody  Map<String, Object> content){
            return moveOrderso.getMoDetailService(content);
    }

    //get GET_MO_ITEM_CROSS_REF_DTLS Services
        @RequestMapping(value = "/getmoitemcrossref", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getMoItemCrossRef(@RequestBody  Map<String, Object> content){
                return moveOrderso.getMoItemCrossRef(content);
        }


    //get MO Detail Services
    @RequestMapping(value = "/getmoitemlot", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getMoItemLotService(@RequestBody  Map<String, Object> content){
            return moveOrderso.getMoItemLotService(content);
    }


    //get GET_MO_ITEM_DETAILS Services
    @RequestMapping(value = "/getmoitemdetail", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getMoItemDetail(@RequestBody  Map<String, Object> content){
            return moveOrderso.getMoItemDetail(content);
    }

    //get GET_MO_ITEM_DETAILS Services
    @RequestMapping(value = "/jsonpost", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getJsonPost(@RequestBody String payload) {
            return moveOrderso.getJsonPost(payload);
    }







}