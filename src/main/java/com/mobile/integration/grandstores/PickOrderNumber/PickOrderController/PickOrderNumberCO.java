package com.mobile.integration.grandstores.PickOrderNumber.PickOrderController;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.PickOrderNumber.PickOrderService.PickOrderNumberSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/pickorder/")
@Api(
    tags = {"Pick Order Services"}, 
    description = "Pick Order Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class PickOrderNumberCO {
    
    @Autowired
    private PickOrderNumberSO pickOrderNumberSO;


     //UPDATE_DELIVERY_DETAILS
     @RequestMapping(value = "/updatedeliverydtl", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> UPDATE_DELIVERY_DETAILS(@RequestBody  Map<String, Object> content) throws ParseException{
            return pickOrderNumberSO.UPDATE_DELIVERY_DETAILS(content);
     }

     //GET_PICK_ORDER_NUM
     @RequestMapping(value = "/pickordernumber", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_PICK_ORDER_NUM(@RequestBody  Map<String, Object> content) throws ParseException{
            return pickOrderNumberSO.GET_PICK_ORDER_NUM(content);
     }

    //GET_PICK_MOVE_ORDER
    @RequestMapping(value = "/pickmoveorder", method = RequestMethod.POST)
          public ResponseEntity<APIResponse> GET_PICK_MOVE_ORDER(@RequestBody  Map<String, Object> content) throws ParseException{
              return pickOrderNumberSO.GET_PICK_MOVE_ORDER(content);
    }

        //GET_PICK_PICKSLIP_NUM
        @RequestMapping(value = "/pickslipnum", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_PICK_PICKSLIP_NUM(@RequestBody  Map<String, Object> content) throws ParseException{
            return pickOrderNumberSO.GET_PICK_PICKSLIP_NUM(content);
  }

          //GET_PICK_ORDER_DETAILS
          @RequestMapping(value = "/pickorderdtl", method = RequestMethod.POST)
          public ResponseEntity<APIResponse> GET_PICK_ORDER_DETAILS(@RequestBody  Map<String, Object> content) throws ParseException{
              return pickOrderNumberSO.GET_PICK_ORDER_DETAILS(content);
    }

            //GET_PICK_DETAIL_CR
              @RequestMapping(value = "/pickorderdtlcr", method = RequestMethod.POST)
              public ResponseEntity<APIResponse> GET_PICK_DETAIL_CR(@RequestBody  Map<String, Object> content) throws ParseException{
                  return pickOrderNumberSO.GET_PICK_DETAIL_CR(content);
        }









}