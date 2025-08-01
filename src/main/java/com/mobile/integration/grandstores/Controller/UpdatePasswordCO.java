package com.mobile.integration.grandstores.Controller;

import java.util.Map;

import com.mobile.integration.grandstores.Services.*;
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
@RequestMapping(value = "/module")
@Api(
    tags = {"Authentication Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class UpdatePasswordCO {
 
    @Autowired
    private UpdatePasswordSO updatePasswordSO;

    //Authentication Services
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> updatePassword(@RequestBody  Map<String, Object> content){
        return updatePasswordSO.updatePassword(content);
    }
    

    //Authentication Services
    @RequestMapping(value = "/getitemdepartment", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getitemdepartment(@RequestBody  Map<String, Object> content){
        return updatePasswordSO.getitemdepartment(content);
    }

}