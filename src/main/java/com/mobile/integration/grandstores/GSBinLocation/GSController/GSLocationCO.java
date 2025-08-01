package com.mobile.integration.grandstores.GSBinLocation.GSController;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.GSBinLocation.GSService.GSLocationSO;
import com.mobile.integration.grandstores.IOReceipt.IORecService.IOReceiptSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/gs/")
@Api(
    tags = {"GS Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class GSLocationCO {

    @Autowired
    private GSLocationSO glLocationso;

    @Autowired
    private IOReceiptSO ioReceiptSO;

    

    private static final Logger logger = LoggerFactory.getLogger(GSLocationCO.class);
   //PO Item Cross Ref Services
   @RequestMapping(value = "/invorg", method = RequestMethod.POST)
   public ResponseEntity<APIResponse> getInvOrganization(@RequestBody  Map<String, Object> content) throws ParseException{
    logger.debug("Entering the getInvOrganization method; "+content);   
    return glLocationso.getInvOrganization(content);
   }


      //PO Item Cross Ref Services
      @RequestMapping(value = "/secoundinv", method = RequestMethod.POST)
      public ResponseEntity<APIResponse> getSecondaryInv(@RequestBody  Map<String, Object> content) throws ParseException{
        logger.debug("Entering the getSecondaryInv method; "+content);     
        return glLocationso.getSecondaryInv(content);
      }


         //PO Item Cross Ref Services
    @RequestMapping(value = "/mtlsystemitem", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getMTLSystemItem(@RequestBody  Map<String, Object> content) throws ParseException{
        logger.debug("Entering the getMTLSystemItem method; "+content);   
        return glLocationso.getMTLSystemItem(content);
    }


       //PO Item Cross Ref Services
       @RequestMapping(value = "/itemcrossref", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> getItemCrossRef(@RequestBody  Map<String, Object> content) throws ParseException{
        logger.debug("Entering the getItemCrossRef method; "+content);      
        return glLocationso.getItemCrossRef(content);
       }


        //PO Item Cross Ref Services
    @RequestMapping(value = "/department", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getDepartment(@RequestBody  Map<String, Object> content) throws ParseException{
        logger.debug("Entering the getDepartment method; "+content);     
        return glLocationso.getDepartment(content);
    }

        
    //PO Item Cross Ref Services
    @RequestMapping(value = "/mtscrossref", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getmtscrossref(@RequestBody  Map<String, Object> content) throws ParseException{
        logger.debug("Entering the getmtscrossref method; "+content);   
        return glLocationso.getmtscrossref(content);
    }   
    
    @RequestMapping(value = "/itemCodelist", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getItemCodelist(@RequestBody  Map<String, Object> content) throws ParseException{
        logger.debug("Entering the getItemCodelist method; " +content);   
        return glLocationso.getItemCodelist(content);
    }

            
    //PO Item Cross Ref Services
    @RequestMapping(value = "/mtsspotcheck", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getmtsspotcheck(@RequestBody  Map<String, Object> content) throws ParseException{    
        logger.debug("Entering the getmtsspotcheck method; "+content);   
        return glLocationso.getmtsspotcheck(content);
    }      


   //MTL DESC Cross Ref Services
   @RequestMapping(value = "/mtldesccheck", method = RequestMethod.POST)
   public ResponseEntity<APIResponse> getmtldesccheck(@RequestBody  Map<String, Object> content) throws ParseException{
    logger.debug("Entering the getmtsspotcheck method; "+content);      
    return glLocationso.getmtldesccheck(content);
   }     

   
        //Get Item Cross Ref Services
        @RequestMapping(value = "/locationspot", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getlocationSpot(@RequestBody  Map<String, Object> content) throws ParseException{
            logger.debug("Entering the getlocationSpot method; "+content);   
            return ioReceiptSO.getlocationSpot(content);
        }


}
