package com.mobile.integration.grandstores.User.Controller;
import java.text.ParseException;
// import java.util.Map;
import java.util.Map;

import com.mobile.integration.grandstores.User.Entity.UserResponsibleGroupEO;
import com.mobile.integration.grandstores.User.Services.UserResponsibleGroupSO;
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
public class UserResponsibleGroupCO {
 
    
    @Autowired
    private UserResponsibleGroupSO userResponsiblegroupso;   

        //Insert PI Count Services
    @RequestMapping(value = "/insertuserrepo", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertuserrepo(@RequestBody Iterable<UserResponsibleGroupEO> bodydtl) throws ParseException{
        return userResponsiblegroupso.insertuserrepo(bodydtl);
    }

    //Get PI Count Services
    @RequestMapping(value = "/getuserrepo", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getUserRepo()throws ParseException{
        return userResponsiblegroupso.getuserrepo();
    }

   //get user repo name
   @RequestMapping(value = "/getuserreponame", method = RequestMethod.POST)
   public ResponseEntity<APIResponse> getuserreponame(@RequestBody  Map<String, Object> content) throws ParseException{
       return userResponsiblegroupso.getuserreponame(content);
   }     

      //Get User Repo Access
    @RequestMapping(value = "/getuserrepoaccess", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> getuserrepoaccess(@RequestBody  Map<String, Object> content) throws ParseException{
          return userResponsiblegroupso.getuserrepoaccess(content);
      }     

    //Get User Rep
    @RequestMapping(value = "/getuserrep", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getuserrep(@RequestBody  Map<String, Object> content) throws ParseException{
        return userResponsiblegroupso.getuserrep(content);
    }
    
    //Get Orga Name
    @RequestMapping(value = "/getorg", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getorg() throws ParseException{
        // @RequestBody  Map<String, Object> content
        return userResponsiblegroupso.getorg();
    }

    //Get User Rep
    @RequestMapping(value = "/getuserrepactive", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getuserrepactive() throws ParseException{
        return userResponsiblegroupso.getuserrepactive();
    }

}