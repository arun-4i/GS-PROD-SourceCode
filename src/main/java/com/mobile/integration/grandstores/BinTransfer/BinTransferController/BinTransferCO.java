package com.mobile.integration.grandstores.BinTransfer.BinTransferController;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.integration.grandstores.BinTransfer.BinTransferService.BinTransferSO;
import com.mobile.integration.grandstores.IOConLotDtl.IOConLotEntity.IOConLotEO;
import com.mobile.integration.grandstores.IOConLotDtl.IOConLotService.IOConLotSO;
import com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmEntity.InvCountConfirmEO;
import com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmService.InvCountConfirmSO;
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
@RequestMapping(value = "/module/bt/")
@Api(
    tags = {"Bin Transfer"}, 
    description = "Grandstores-Bin Transfer",  
    produces = "application/json"
    )
public class BinTransferCO {

    @Autowired
    private BinTransferSO binTransferso;

    @Autowired
    private IOConLotSO ioConLotSO;

    @Autowired
    private InvCountConfirmSO invcountconfirm;

    private static final Logger logger = LoggerFactory.getLogger(BinTransferCO.class);


    // GET_ITEM_DETAILS Services
    @RequestMapping(value = "/getitemdetail", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getItemDetail(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Bin Transfer getitemdetail method; "+content);
        return binTransferso.getItemDetail(content);
    }


    // GET_ITEM_CROSSREF_DTLS Services
    @RequestMapping(value = "/getitemcrossref", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getItemCrossref(@RequestBody  Map<String, Object> content) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Bin Transfer getitemcrossref method; "+content);
        return binTransferso.getItemCrossref(content);
    }


    // XXGS_CONF_LOT_DETAILS Services
   @RequestMapping(value = "/getconlotdetail", method = RequestMethod.POST)
   public ResponseEntity<APIResponse> getIOConLot() throws ParseException{
       return ioConLotSO.getIOConLot();
   }

    //Insert XXGS_CONF_LOT_DETAILS Services
    @RequestMapping(value = "/insertconlotdetail", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertIOConLot(@RequestBody Iterable<IOConLotEO> bodydtl) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Bin Transfer insertconlotdetail method; "+bodydtl);
        return ioConLotSO.insertIOConLot(bodydtl);
    }

    // XXGS_CONF_LOT_DETAILS Services
    @RequestMapping(value = "/getinvcountconfirm", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getinvCountConfirm() throws ParseException{
        return invcountconfirm.getinvCountConfirm();
    } 
    //Insert XXGS_CONF_LOT_DETAILS Services
    @RequestMapping(value = "/insertinvcountconfirm", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertInvCountConfirm(@RequestBody Iterable<InvCountConfirmEO> bodydtl) throws ParseException, JsonProcessingException, SerialException, SQLException{
        logger.debug("Entering the Bin Transfer insertinvcountconfirm method; "+bodydtl);
        return invcountconfirm.insertInvCountConfirm(bodydtl);
    }
}
