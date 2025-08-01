package com.mobile.integration.grandstores.RTVProcess.RTVProcessCO;


import java.text.ParseException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import com.mobile.integration.grandstores.RTVProcess.RTVProcessSO.RTVProcessSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

@RestController
@RequestMapping(value = "/module/rtvprocess/")
@Api(
    tags = {"RTV Process"}, 
    description = "RTV Porcess services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class RTVProcessCO {
    
    @Autowired
    private RTVProcessSO rtvProcessSO;

    //GET_RTV_REQUEST_NUM
     @RequestMapping(value = "/getrtvrequestnum", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_RTV_REQUEST_NUM(@RequestBody  Map<String, Object> content) throws ParseException{
            System.out.println("inside getrtvrequestnum co");
            return rtvProcessSO.getRTVRequestNum(content);
     }

     //GET_RTV_DTLS
     @RequestMapping(value = "/getrtcdtls", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_RTV_DTLS(@RequestBody  Map<String, Object> content) throws ParseException{
            System.out.println("inside GET_RTV_DTLS co");
            return rtvProcessSO.getRTVDtls(content);
     }

     //GET_RTV_ITEM_DTLS
     @RequestMapping(value = "/getrtcitemdtls", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_RTV_ITEMS_DTLS(@RequestBody  Map<String, Object> content) throws ParseException{
            System.out.println("inside GET_RTV_ITEMS_DTLS co");
            return rtvProcessSO.getRTVItemDtls(content);
     }
}
