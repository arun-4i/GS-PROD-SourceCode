package com.mobile.integration.grandstores.Showroom.ShowroomController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mobile.integration.grandstores.Showroom.ShowroomService.ShowroomSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import io.swagger.annotations.Api;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/module/showroom/")
@Api(
    tags = {"Showroom"}, 
    description = "Grandstores-Showroom Services",  
    produces = "application/json"
    )
public class ShowroomCO {

    @Autowired
    private ShowroomSO showroomso;

    private static final Logger logger = LoggerFactory.getLogger(ShowroomCO.class);


    // GET_ITEM_DETAILS Services
    @RequestMapping(value = "/getInvOrg", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getInvOrg(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getInvOrg method; "+content);
        return showroomso.getInvOrg(content);
    }

    @RequestMapping(value = "/getSaleOrderNum", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getSaleOrderNum(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getSaleOrderNum method; "+content);
        return showroomso.getSaleOrderNum(content);
    }

    @RequestMapping(value = "/getSaleOrderDetails", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getSaleOrderDetails(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getSaleOrderDetails method; "+content);
        return showroomso.getSaleOrderDetails(content);
    }

    @RequestMapping(value = "/getSaleOrderDetailsCr", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getSaleOrderDetailsCr(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getSaleOrderDetailsCr method; "+content);
        return showroomso.getSaleOrderDetailsCr(content);
    }
    
    @RequestMapping(value = "/getMoDetails", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getMoDetails(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getMoDetails method; "+content);
        return showroomso.getMoDetails(content);
    }

    @RequestMapping(value = "/getMoItemDetails", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getMoItemDetails(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getMoItemDetails method; "+content);
        return showroomso.getMoItemDetails(content);
    }

    @RequestMapping(value = "/getMoItemCrossRefDtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getMoItemCrossRefDtls(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getMoItemCrossRefDtls method; "+content);
        return showroomso.getMoItemCrossRefDtls(content);
    }

    @RequestMapping(value = "/getPoNumber", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPoNumber(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPoNumber method; "+content);
        return showroomso.getPoNumber(content);
    }

    /*@RequestMapping(value = "/getReleaseNumber", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getReleaseNumber(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getReleaseNumber method; "+content);
        return showroomso.getReleaseNumber(content);
    }*/

    @RequestMapping(value = "/getPoItemDtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPoItemDtls(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPoItemDtls method; "+content);
        return showroomso.getPoItemDtls(content);
    }
    @RequestMapping(value = "/getPoItemCrossRef", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPoItemCrossRef(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPoItemCrossRef method; "+content);
        return showroomso.getPoItemCrossRef(content);
    }

    @RequestMapping(value = "/getRTVRequestNum", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getRTVRequestNum(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getRTVRequestNum method; "+content);
        return showroomso.getRTVRequestNum(content);
    }

    @RequestMapping(value = "/getRTVPoNum", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getRTVPoNum(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getRTVPoNum method; "+content);
        return showroomso.getRTVPoNum(content);
    }

    @RequestMapping(value = "/getRTVItemDtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getRTVItemDtls(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getRTVItemDtls method; "+content);
        return showroomso.getRTVItemDtls(content);
    }

    @RequestMapping(value = "/getRTVItemDtlsCr", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getRTVItemDtlsCr(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getRTVItemDtlsCr method; "+content);
        return showroomso.getRTVItemDtlsCr(content);
    }


    @RequestMapping(value = "/getPhyInvQueryDtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPhyInvQueryDtls(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPhyInvQueryDtls method; "+content);
        return showroomso.getPhyInvQueryDtls(content);
    }

    @RequestMapping(value = "/getPhyInvCntItemDtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPhyInvCntItemDtls(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPhyInvCntItemDtls method; "+content);
        return showroomso.getPhyInvCntItemDtls(content);
    }

    @RequestMapping(value = "/getPhyInvCntItemCr", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPhyInvCntItemCr(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPhyInvCntItemCr method; "+content);
        return showroomso.getPhyInvCntItemCr(content);
    }


    @RequestMapping(value = "/getPhysicalInventories", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPhysicalInventories(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPhysicalInventories method; "+content);
        return showroomso.getPhysicalInventories(content);
    }

    @RequestMapping(value = "/getPhyInvSubInvDtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getPhyInvSubInvDtls(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getPhyInvSubInvDtls method; "+content);
        return showroomso.getPhyInvSubInvDtls(content);
    }

    @RequestMapping(value = "/getIoShipmentNo", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getIoShipmentNo(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getIoShipmentNumber method; "+content);
        return showroomso.getIoShipmentNo(content);
    }

    @RequestMapping(value = "/getIoRcptItemDtls", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getIoRcptItemDtls(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getIoRcptItemDtls method; "+content);
        return showroomso.getIoRcptItemDtls(content);
    }

    @RequestMapping(value = "/getIoRcptItemDtlsCr", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getIoRcptItemDtlsCr(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Showroom getIoRcptItemDtlsCr method; "+content);
        return showroomso.getIoRcptItemDtlsCr(content);
    }

    @RequestMapping(value = "/moConfirm", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> moConfirm(@RequestBody Map<String, Object> content) throws ParseException{
        logger.info("Entering the moConfirm method; "+content);
        return showroomso.moConfirm(content);
    }

    @RequestMapping(value = "/ioConfirm", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> ioConfirm(@RequestBody Map<String, Object> content) throws ParseException{
        logger.info("Entering the ioConfirm method; "+content);
        return showroomso.ioConfirm(content);
    }

    @RequestMapping(value = "/poConfirm", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> poConfirm(@RequestBody Map<String, Object> content) throws ParseException{
        logger.info("Entering the poConfirm method; "+content);
        return showroomso.poConfirm(content);
    }

    @RequestMapping(value = "/rtvConfirm", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> rtvConfirm(@RequestBody Map<String, Object> content) throws ParseException{
        logger.info("Entering the rtvConfirm method; "+content);
        return showroomso.rtvConfirm(content);
    }

    @RequestMapping(value = "/stockConfirm", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> stockConfirm(@RequestBody Map<String, Object> content) throws ParseException{
        logger.info("Entering the stockConfirm method; "+content);
        return showroomso.stockConfirm(content);
    }

}
