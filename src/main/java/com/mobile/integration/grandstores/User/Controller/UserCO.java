package com.mobile.integration.grandstores.User.Controller;
import java.text.ParseException;
// import java.util.Map;

import com.mobile.integration.grandstores.User.Entity.UserEO;
import com.mobile.integration.grandstores.User.Services.UserSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/newuser/")
@Api(
    tags = {"User Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class UserCO {
 
    @Autowired
    private UserSO userso;   

    //Insert User Services
    @RequestMapping(value = "/insertuser", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertUser(@RequestBody Iterable<UserEO> bodydtl) throws ParseException{
        return userso.insertUser(bodydtl);
    }

    //Get User Services
    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getuser()throws ParseException{
        return userso.getuser();
    }



}