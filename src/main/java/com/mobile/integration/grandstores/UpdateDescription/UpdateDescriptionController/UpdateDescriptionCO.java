package com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionController;
import java.text.ParseException;
import java.util.Map;
import com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionEntity.ItemConfigEO;
import com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionService.UpdateDescriptionSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/module/updatedescription")
@Api(
    tags = {"Update Services"}, 
    description = "Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class UpdateDescriptionCO {
 
    @Autowired
    private UpdateDescriptionSO updatedescriptionso;
    private static final Logger logger = LoggerFactory.getLogger(UpdateDescriptionCO.class);


    //XXGS_MOB_UTIL_PKG.GET_ITEM_DETAILS Services
    @RequestMapping(value = "/getitemdtl", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getItemDetail(@RequestBody  Map<String, Object> content){
            return updatedescriptionso.getItemDetail(content);
    }

    //XXGS_MOB_UTIL_PKG.GET_ITEM_CROSS_REF Services
    @RequestMapping(value = "/getitemcrosref", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getItemCrossRef(@RequestBody  Map<String, Object> content){
            return updatedescriptionso.getItemCrossRef(content);
    }


    //Insert Item Config Services
    @RequestMapping(value = "/insertItemConfig", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> insertItemConfig(@RequestBody Iterable<ItemConfigEO> bodydtl) throws ParseException{
            logger.debug("Entering the insertItemConfig method; "+bodydtl);
            return updatedescriptionso.insertItemConfig(bodydtl);
        }
    
    //Get Item Config Services
    @RequestMapping(value = "/getitemconfig", method = RequestMethod.GET)
        public ResponseEntity<APIResponse> getItemConfig()throws ParseException{
            return updatedescriptionso.getItemConfig();
        }


}










