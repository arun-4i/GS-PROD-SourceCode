package com.mobile.integration.grandstores.ItemCheckDispatch.ItemCheckDispatchCO;



import java.sql.SQLException;
import java.text.ParseException;

import java.util.Map;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.integration.grandstores.ItemCheckDispatch.ItemCheckDispatchSO.ItemCheckDispatchSO;

import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

@RestController
@RequestMapping(value = "/module/itemcheck/")
@Api(
    tags = {"Item Check Dispatch"}, 
    description = "Item Check Dispatch services Grandstores-WMS Integration with Mobile App", 
    produces = "application/json"
    )
public class ItemCheckDispatchCO {
    
    @Autowired
    private ItemCheckDispatchSO itemCheckDispatchSO;

    

    private static final Logger logger = LoggerFactory.getLogger(ItemCheckDispatchCO.class);

    //getitemcheckdispatch
     @RequestMapping(value = "/getitemcheckdispatch", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> getItemCheckDispatch(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
            logger.info("Entering the getItemCheckDispatch Controller -  "+content);  
            return itemCheckDispatchSO.getItemCheck(content);
     }
}
