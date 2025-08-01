package com.mobile.integration.grandstores.StockUpdate.StockUpdateCO;

import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.IODelivery.IODelService.IODeliverySO;
import com.mobile.integration.grandstores.PODelivery.PODelService.PODeliverySO;
import com.mobile.integration.grandstores.StockUpdate.StockUpdateSO.StockUpdateSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/stockupdate/")
@Api(
    tags = {"Stock Update Services"}, 
    description = "Stock Update Services Grandstores-WMS Integration with LCM & Mobile App", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class StockUpdateCO {
    
    @Autowired
    private StockUpdateSO stockUpdateso;


     //GET_BIN_STOCK_UPDATE
     @RequestMapping(value = "/binstockupdate", method = RequestMethod.POST)
        public ResponseEntity<APIResponse> GET_BIN_STOCK_UPDATE(@RequestBody  Map<String, Object> content) throws ParseException{
            System.out.println("inside bin stock");
            return stockUpdateso.GET_BIN_STOCK_UPDATE(content);
     }



}