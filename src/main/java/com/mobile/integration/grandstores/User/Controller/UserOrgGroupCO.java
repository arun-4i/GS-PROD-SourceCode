package com.mobile.integration.grandstores.User.Controller;
import java.text.ParseException;
// import java.util.Map;
import java.util.Map;

import com.mobile.integration.grandstores.User.Entity.UserOrgGroupEO;
import com.mobile.integration.grandstores.User.Services.UserOrgGroupSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/userorg/")
@Api(
    tags = {"User Organization Group Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    produces = "application/json"
    )
public class UserOrgGroupCO {
    
    @Autowired
    private UserOrgGroupSO userOrgGroupso;   

    //Insert User Services
    @RequestMapping(value = "/insertorg", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertUserOrg(@RequestBody Iterable<UserOrgGroupEO> bodydtl) throws ParseException{
        return userOrgGroupso.insertUserOrg(bodydtl);
    }

    //Get User Services
    @RequestMapping(value = "/getorg", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getuserOrg()throws ParseException{
        return userOrgGroupso.getuserOrg();
    }


    //Get User 
    @RequestMapping(value = "/getuserorgaccess", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getUserOrgAccess() throws ParseException{
            return userOrgGroupso.getUserOrgAccess();
    }
        
    //Get Orga Name
    @RequestMapping(value = "/getuserorgaccessbyid", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getUserOrgAccessById(@RequestBody  Map<String, Object> content) throws ParseException{
            return userOrgGroupso.getUserOrgAccessById(content);
    }

    //Get Org inv Name
    @RequestMapping(value = "/getinvorg", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getInvOrg() throws ParseException{
        // @RequestBody  Map<String, Object> content    
        return userOrgGroupso.getInvOrg();
    }

}