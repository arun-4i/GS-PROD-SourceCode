package com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmService;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
// import java.util.Map;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmEntity.IOReceiptConfirmEO;
import com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmRepository.IOReceiptConfirmRO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogEO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogSO;
import com.mobile.integration.grandstores.PackageCalling.IOReceiptPackage;
import com.mobile.integration.grandstores.PackageCalling.POServicePkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IOReceiptConfirmSO {

    @Autowired
    private IOReceiptConfirmRO ioReceiptConfirmro;

    @Autowired
    private MobTransLogSO mobTransLogSO;

    MobTransLogEO mobTransLog = null;

    @Autowired
    private IOReceiptPackage ioReceiptPkg;

    @Autowired
    private POServicePkg poServicePkg;

    private static final Logger logger = LoggerFactory.getLogger(IOReceiptConfirmSO.class);

    public ResponseEntity<APIResponse> insertReceiptConfirm(Iterable<IOReceiptConfirmEO> bodydtl) {
        try{
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("IO Receipt Confirm Insert Header");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(bodydtl).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        //DUPALL IO receipt: Shipment Number: IS518-10229
        List<String> myList = new ArrayList<>();
        String comb = "";
        List<IOReceiptConfirmEO> listFromIterator = new ArrayList<>();
        Iterator<IOReceiptConfirmEO> iterator1 = bodydtl.iterator();
        APIResponse api=new APIResponse();
        while (iterator1.hasNext()) {
            IOReceiptConfirmEO current = iterator1.next();
            System.out.println("current.getTransactionType().toString(): "+current.getTransactionType().toString());
            if(current.getTransactionType().toString().equalsIgnoreCase("IO_RECEIPT")){
                comb = current.getLineNum().toString() +
                    "-" +current.getItemId().toString()+ 
                    "-" +current.getShipmentHeaderId().toString()+
                    "-" +current.getStatus().toString()+    
                    "-" +current.getShipmentLineId().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countD = recordCheckforIoReceipt(current.getLineNum().toString(), 
                                            current.getItemId().toString(), current.getShipmentHeaderId().toString(),
                                            current.getStatus().toString(), 
                                            current.getShipmentLineId().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        ioReceiptConfirmro.save(current);
                       // listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                api.setStatus(HttpStatus.OK.value());
                api.setError("Created");
                  
            }else if(current.getTransactionType().toString().equalsIgnoreCase("IO_DELIVERY")){
                String p_subinv = null;
                String p_loc = null;
                String resultStr = "";
                
                //Location Validation
                p_subinv = current.getDelivSubInv().toString();
                p_loc = current.getDelivLocator().toString();

                Map<String, Object> valResult = poServicePkg.validateLocPkgCall(p_subinv,p_loc);
                if (valResult.containsKey("P_LOC_RESULT")) {
                    Object resultObj = valResult.get("P_LOC_RESULT");
                    resultStr = resultObj.toString();
                    if (resultStr.contains("200")) {
                        comb = current.getLineNum().toString() +
                            "-" +current.getItemId().toString()+ 
                            "-" +current.getShipmentHeaderId().toString()+
                            "-" +current.getReceiptNum().toString()+
                            "-" +current.getStatus().toString()+    
                            "-" +current.getDelivLocator().toString()+
                            "-" +current.getShipmentLineId().toString();
                        System.out.println("inside the iterator: "+comb);
                        if(myList.contains(comb)){
                            iterator1.remove();
                            System.out.println("removed");
                        }
                        else{
                            System.out.println("else part");
                            int countD = recordCheckforIoDelivery(current.getLineNum().toString(), 
                                                    current.getItemId().toString(), current.getShipmentHeaderId().toString(),
                                                    current.getReceiptNum().toString(), current.getStatus().toString(), 
                                                    current.getDelivLocator().toString(), current.getShipmentLineId().toString());
                            System.out.println("countD: "+countD);
                            if(countD == 0){
                                System.out.println("inside the countD = 0");
                                ioReceiptConfirmro.save(current);
                                //listFromIterator.add(current);
                                myList.add(comb);
                            }
                        }
                        
                        api.setStatus(HttpStatus.OK.value());
                        api.setError("Created");
                    }else if (resultStr.contains("400")) {
                        //api.setData();
                        api.setStatus(HttpStatus.BAD_REQUEST.value()); 
                        api.setError("Invalid Locator");
                    }  
                }
                
            }    
        }               
        //Iterable<IOReceiptConfirmEO> ls=ioReceiptConfirmro.saveAll(listFromIterator);
       // api.setData(ls);
       // api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    private int recordCheckforIoReceipt(String lineNum, String itemId, String supHdrId, String status, 
                                    String supLineId) {
        System.out.println("inside IoReceipt");
        int c = ioReceiptConfirmro.recordCountIOReceiptConfirm(lineNum, itemId, supHdrId, status, supLineId);
        return c;
    }

    private int recordCheckforIoDelivery(String lineNum, String itemId, String supHdrId, String receiptNum, String status, 
                                    String delivLoc, String supLineId) {
        System.out.println("inside IO Delivery");
        int c = ioReceiptConfirmro.recordCountIODeliveryConfirm(lineNum, itemId, supHdrId, receiptNum, status, delivLoc, supLineId);
        return c;
    }

    public ResponseEntity<APIResponse> getReceiptConfirm() {
        List<IOReceiptConfirmEO> ls=ioReceiptConfirmro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }

    public ResponseEntity<APIResponse> insertSerial(String bodydtl) throws JsonProcessingException, SerialException, SQLException{
        //logging
        try{
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("IO Receipt Confirm Insert Serial");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(bodydtl).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        //ObjectMapper mapper = new ObjectMapper();
        //String reqPayload = mapper.writeValueAsString(bodydtl);
        Map<String, Object> ls = ioReceiptPkg.insertSerialForIORecCnfmPkg(bodydtl);
        APIResponse api = new APIResponse();
        //api.setData(ls);
         
        if (ls.containsKey("P_SERIAL_RESULT")) {
            Object resultObj = ls.get("P_SERIAL_RESULT");

            if (resultObj instanceof List) {
                List<?> resultList = (List<?>) resultObj;

                if (!resultList.isEmpty() && resultList.get(0) instanceof Map) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

                    Object statusCodeObj = resultMap.get("STATUS_CODE");
                    Object messageObj = resultMap.get("MESSAGE");

                    int statusCode = 0;
                    if (statusCodeObj instanceof BigDecimal) {
                        statusCode = ((BigDecimal) statusCodeObj).intValue();
                    }

                    String message = messageObj != null ? messageObj.toString() : "";
                    api.setStatus(statusCode); 
                    api.setError(message);
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Message: " + message);
                }
            }
        
        }
        return ResponseEntity.ok().body(api);
    }

        public ResponseEntity<APIResponse> insertAll(String bodydtl) {

         //logging
        try{
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("IO Receipt Confirm Insert All");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(bodydtl).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        Map<String, Object> ls = ioReceiptPkg.insertIORecCnfmPkg(bodydtl);
        APIResponse api = new APIResponse();

        if (ls.containsKey("P_SERIAL_RESULT")) {
            Object resultObj = ls.get("P_SERIAL_RESULT");

            if (resultObj instanceof List) {
                List<?> resultList = (List<?>) resultObj;

                if (!resultList.isEmpty() && resultList.get(0) instanceof Map) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

                    Object statusCodeObj = resultMap.get("STATUS_CODE");
                    Object messageObj = resultMap.get("MESSAGE");

                    int statusCode = 0;
                    if (statusCodeObj instanceof BigDecimal) {
                        statusCode = ((BigDecimal) statusCodeObj).intValue();
                    }

                    String message = messageObj != null ? messageObj.toString() : "";
                    api.setStatus(statusCode); 
                    api.setError(message);
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Message: " + message);
                }
            }
        
        }
        return ResponseEntity.ok().body(api);
    }

}