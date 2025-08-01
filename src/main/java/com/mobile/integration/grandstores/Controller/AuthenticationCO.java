package com.mobile.integration.grandstores.Controller;

import java.util.Map;

import com.mobile.integration.grandstores.Services.*;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

// import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class AuthenticationCO {
    
    @Autowired
    private AuthenticationSO authenticationso;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "/temp", method = RequestMethod.GET)
    public String getData(@RequestParam(value = "nam", required = false) String name){
           String results=null;
           if(name!=null){
               results="hello"+name;
           }else{
               results="hello";
           }
           return results;
    }

    //Authentication Services
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getAuthentication(@RequestBody  Map<String, Object> content){
    // return authenticationso.getAuthenticationView(content);
       return authenticationso.getAuthenticationPkg(content);
    }


    //Authentication Services
    @RequestMapping(value = "/authen", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getauthen(@RequestBody  Map<String, Object> content){
        return authenticationso.getauthen(content);
    }


     //Temp Services 
     @RequestMapping(value = "/sample", method = RequestMethod.GET)
     public ResponseEntity<APIResponse> getData1() {
            String results=null;
            results="Hellow World";
            APIResponse api=new APIResponse();
            api.setData(results);
            api.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(api);
     }

     

    @GetMapping("/checkdbconnection")
    public String checkDbConnection() {
        if (databaseService.checkConnection()) {
            return "Database connection is successful!";
        } else {
            return "Failed to connect to the database!";
        }
    }
    

}