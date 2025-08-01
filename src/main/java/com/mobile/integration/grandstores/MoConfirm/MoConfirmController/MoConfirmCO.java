package com.mobile.integration.grandstores.MoConfirm.MoConfirmController;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogEO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogSO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoPickConfirmEOT;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.PickConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmService.MoConfirmSO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmService.PickConfirmSO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/module/mo/confirm")
@Api(
    tags = {"MO Confirm"}, 
    description = "Grandstores-Bin Transfer", 
    // value = "HRMS Module", 
    produces = "application/json"
    )
public class MoConfirmCO {
    @Autowired
    private MoConfirmSO moConfirmSO;

    @Autowired
    private PickConfirmSO pickConfirmso;  
    
    @Autowired
    private MobTransLogSO mobTransLogSO;

    MobTransLogEO mobTransLog = null;

    private static final Logger logger = LoggerFactory.getLogger(MoConfirmCO.class);

    //insertMoConfirm Services
    // duplicate line filtration - 18/12/23
    @RequestMapping(value = "/insertmo", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertMoConfirm(@RequestBody Iterable<MoConfirmEO> bodydtl) throws ParseException{
        List<String> myList = new ArrayList<>();
        String comb = "";
        List<MoConfirmEO> listFromIterator = new ArrayList<>();
        Iterator<MoConfirmEO> iterator1 = bodydtl.iterator();
        while (iterator1.hasNext()) {
            MoConfirmEO current = iterator1.next();
            comb = current.getSourceLocationId().toString() + "-" + current.getDeliveryDetailId().toString() + "-" +current.getItemId().toString() + "-" +current.getMoNumber().toString()+ "-" +current.getMoLineNumber().toString() + "-" +current.getStatus().toString();
            System.out.println("Combination: "+comb);
            if(myList.contains(comb)){
                iterator1.remove();
                System.out.println("removed");
            }
            else{
                 System.out.println("else part");
                 int countR = moConfirmSO.recordCountService(current.getSourceLocationId().toString(), 
                                        current.getDeliveryDetailId().toString(), current.getItemId().toString(),
                                        current.getMoNumber().toString(), current.getMoLineNumber().toString(),
                                        current.getStatus().toString());
                System.out.println("countR: "+countR);
                if(countR == 0){
                     System.out.println("inside the countR = 0");
                    listFromIterator.add(current);
                    myList.add(comb);
                }else{
                    mobTransLog = new MobTransLogEO();   
                    mobTransLog.setModuleName("moConfirm");
                    mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
                    mobTransLog.setAttributeCategory(comb);
                    mobTransLogSO.saveMobTransLog(mobTransLog);   
                    System.out.println("mo failed : "+comb);
                }
            }
        }               
        System.out.println("myL size: " +myList.size());
        System.out.println("listFromIterator: " +listFromIterator.size());
        return moConfirmSO.insertMoConfirmRO(listFromIterator);
    }

    //getBinTransfer Services
    @RequestMapping(value = "/getallmo", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getBinTransfer()throws ParseException{
        return moConfirmSO.getReceiptConfirm();
    }


       //insertMoConfirm Services
       @RequestMapping(value = "/insertpick", method = RequestMethod.POST)
       public ResponseEntity<APIResponse> insertPickConfirm(@RequestBody Iterable<PickConfirmEO> bodydtl) throws ParseException{
        System.out.println("insert Pick "+bodydtl);
        List<String> myPoList = new ArrayList<>();
        String poComb = "";
        List<PickConfirmEO> poListFromIterator = new ArrayList<>();
        List<String> poListFailed = new ArrayList<>();
        Iterator<PickConfirmEO> iterator2 = bodydtl.iterator();
        while (iterator2.hasNext()) {
            PickConfirmEO poCurrent = iterator2.next();
            System.out.println("current.getFromSerialNumber.toString(): "+poCurrent.getFromSerialNumber().toString());
            
            System.out.println("current.getTrans.toString(): "+poCurrent.getTransactionType().toString());
            if(poCurrent.getTransactionType().toString().equalsIgnoreCase("Out Bound Picking")){
                poComb = poCurrent.getDeliveryDetailId().toString() + "-" +poCurrent.getFromSerialNumber().toString() + "-" +poCurrent.getAttribute1().toString() +"-" 
                        +poCurrent.getAttribute2().toString() +"-" +poCurrent.getAttribute3().toString() + "-" +poCurrent.getAttribute4().toString();
                System.out.println("inside the po iterator: "+poComb);
                if(myPoList.contains(poComb)){
                    iterator2.remove();
                }
                else{
                    System.out.println("else part");
                    int countR = pickConfirmso.poRecordCountService(poCurrent.getDeliveryDetailId().toString(), 
                    poCurrent.getFromSerialNumber().toString(), poCurrent.getAttribute1().toString(),
                    poCurrent.getAttribute2().toString(), poCurrent.getAttribute3().toString(),
                    poCurrent.getAttribute4().toString());
                    System.out.println("countR: "+countR);
                    if(countR == 0){
                        System.out.println("inside the countR = 0 po");
                        poListFromIterator.add(poCurrent);
                        myPoList.add(poComb);
                    }else{
                        poListFailed.add(poComb);
                        mobTransLog = new MobTransLogEO();   
                        mobTransLog.setModuleName("poConfirm");
                        mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
                        mobTransLog.setAttributeCategory(poComb);
                        mobTransLogSO.saveMobTransLog(mobTransLog);   
                        System.out.println("po failed : "+poComb);
                    }
                }
            }
            //RMA_DELIVERY
            if(poCurrent.getTransactionType().toString().equalsIgnoreCase("RMA_DELIVERY")){
                System.out.println("inside the po RMA_DELIVERY iterator: ");
                poComb = poCurrent.getFromSerialNumber().toString() + 
                        "-" +poCurrent.getAttribute2().toString() +
                        "-" +poCurrent.getAttribute3().toString() + 
                        "-" +poCurrent.getTransactionType().toString();
                System.out.println("inside the po RMA_DELIVERY iterator: "+poComb);
                if(myPoList.contains(poComb)){
                    iterator2.remove();
                }
                else{
                    System.out.println("else part");
                    int countR = pickConfirmso.poRecordCountDelivery(
                        poCurrent.getFromSerialNumber().toString(), 
                        poCurrent.getAttribute2().toString(), 
                        poCurrent.getAttribute3().toString(),
                        poCurrent.getTransactionType().toString());
                    System.out.println("countR: "+countR);
                    if(countR == 0){
                        System.out.println("inside the countR = 0 po");
                        poListFromIterator.add(poCurrent);
                        myPoList.add(poComb);
                    }else{
                        poListFailed.add(poComb);
                        mobTransLog = new MobTransLogEO();   
                        mobTransLog.setModuleName("poConfirm");
                        mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
                        mobTransLog.setAttributeCategory(poComb);
                        mobTransLogSO.saveMobTransLog(mobTransLog);   
                        System.out.println("po failed : "+poComb);
                    }
                }
            }
            
        }   
        System.out.println("poListFromIterator: " +poListFromIterator.size());
        System.out.println("my po list failed size: " +poListFailed.size());
        
        if(poListFromIterator.size() > 0){
            return pickConfirmso.insertPickConfirm(poListFromIterator);
        }else{
            return null;
        }
         
       }


    //insertMoPickConfirm Services - New service added By Moshina 16-04-2024
    @RequestMapping(value = "/insertMoQuickPickJson", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertMoQuickPickJsonPost(@RequestBody String payload) throws ParseException{
        logger.info("Entering the insertMoQuickPickJsonPost method; "+payload);
        return moConfirmSO.insertJsonPost(payload);
    }
   

    //insertMoPickConfirm Services - New service added By Moshina 19-03-2024
    @RequestMapping(value = "/insertmopick", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> insertBothMoPick(@RequestBody MoPickConfirmEOT bodydtl) throws ParseException{
        logger.debug("Entering the insertBothMoPick method; "+bodydtl);
        List<String> myList = new ArrayList<>();
        List<String> moFailedList = new ArrayList<>();
        Map<String, BigDecimal> moMap = new HashMap<>();
        Map<String, BigDecimal> poMap = new HashMap<>();
        String comb = "", poComb = "";
        List<MoConfirmEO> listFromIterator = new ArrayList<>();

        Iterable<MoConfirmEO> moDetails = bodydtl.getMoConfirms();
        Iterator<MoConfirmEO> iterator1 = moDetails.iterator();
        while (iterator1.hasNext()) {
            MoConfirmEO current = iterator1.next();
            comb = current.getSourceLocationId().toString() + "-" +current.getDeliveryDetailId().toString() + "-" +current.getItemId().toString();
            System.out.println("inside the iterator: "+comb);
            if(current.getAttribute12().equalsIgnoreCase("Y")){
                if(moMap.containsKey(current.getDeliveryDetailId().toString())){
                    BigDecimal sum = (moMap.get(current.getDeliveryDetailId().toString())).add(current.getPickedQuantity());
                    moMap.put(current.getDeliveryDetailId().toString(),sum);
                }else{
                    moMap.put(current.getDeliveryDetailId().toString(),current.getPickedQuantity());
                }
                System.out.println("moMap: "+moMap);
            }
            if(myList.contains(comb)){
                iterator1.remove();
            }
            else{
                int countR = moConfirmSO.recordCountService(current.getSourceLocationId().toString(), 
                                        current.getDeliveryDetailId().toString(), current.getItemId().toString(),
                                        current.getMoNumber().toString(), current.getMoLineNumber().toString(),
                                        current.getStatus().toString());
                System.out.println("countR: "+countR);
                if(countR == 0){
                    System.out.println("inside the countR = 0");
                    listFromIterator.add(current);
                    myList.add(comb);
                }else{
                    moFailedList.add(comb);
                    mobTransLog = new MobTransLogEO();   
                    mobTransLog.setModuleName("moQuickConfirm");
                    mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
                    mobTransLog.setAttributeCategory(comb);
                    mobTransLogSO.saveMobTransLog(mobTransLog);   
                    System.out.println("mo failed : "+comb);
                }
            }
        }               
        System.out.println("myL size: " +myList.size());
        System.out.println("listFromIterator: " +listFromIterator.size());
        if(listFromIterator.size() > 0){
            moConfirmSO.insertMoConfirmRO(listFromIterator);
        }
        logger.info("Entering the PO filteration method;");
        //POConfirmation details insertion
        List<String> myPoList = new ArrayList<>();
        List<PickConfirmEO> poListFromIterator = new ArrayList<>();
        List<String> poListFailed = new ArrayList<>();

        Iterable<PickConfirmEO> pickDetails = bodydtl.getPickConfirmations();
        Iterator<PickConfirmEO> iterator2 = pickDetails.iterator();
        while (iterator2.hasNext()) {
            BigDecimal init = new BigDecimal(1);
            PickConfirmEO poCurrent = iterator2.next();
            System.out.println("current.getDelDetailId.toString(): "+poCurrent.getDeliveryDetailId().toString());
            
            if(poMap.containsKey(poCurrent.getDeliveryDetailId().toString())){
                BigDecimal poSum = (poMap.get(poCurrent.getDeliveryDetailId().toString())).add(init);
                poMap.put(poCurrent.getDeliveryDetailId().toString(),poSum);
            }else{
                poMap.put(poCurrent.getDeliveryDetailId().toString(),init);
            }
            System.out.println("poMap: "+poMap);  
            poComb = poCurrent.getDeliveryDetailId().toString() + "-" +poCurrent.getFromSerialNumber().toString() + "-" +poCurrent.getAttribute1().toString() +"-" 
                        +poCurrent.getAttribute2().toString() +"-" +poCurrent.getAttribute3().toString() + "-" +poCurrent.getAttribute4().toString();
            System.out.println("inside the po iterator: "+poComb);
            
            if(myPoList.contains(poComb)){
                iterator2.remove();
                System.out.println("po removed: ");
            }
            else{
                int countR = pickConfirmso.poRecordCountService(poCurrent.getDeliveryDetailId().toString(), 
                poCurrent.getFromSerialNumber().toString(), poCurrent.getAttribute1().toString(),
                poCurrent.getAttribute2().toString(), poCurrent.getAttribute3().toString(),
                poCurrent.getAttribute4().toString());
                System.out.println("countR: "+countR);
                
                if(countR == 0){
                    System.out.println("inside the countR = 0 po");
                    poListFromIterator.add(poCurrent);
                    myPoList.add(poComb);
                    System.out.println("myPoList: "+myPoList); 
                }else{
                    poListFailed.add(poComb);
                    mobTransLog = new MobTransLogEO();   
                    mobTransLog.setModuleName("poQuickConfirm");
                    mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
                    mobTransLog.setAttributeCategory(poComb);
                    mobTransLogSO.saveMobTransLog(mobTransLog);   
                }
            }
        }   
        System.out.println("poListFromIterator: " +poListFromIterator.size());
        System.out.println("my po list failed size: " +poListFailed.size());  
        if(poListFromIterator.size() > 0){
            pickConfirmso.insertPickConfirm(poListFromIterator);
        } 
        APIResponse api = new APIResponse();
        logger.info("Entering the Comparison part;");
        
        //Comparison
        int badCount = 0;
        if(!moMap.isEmpty()){
            if(poMap.isEmpty()){
                System.out.println("Either of the 2 validation is empty.");
                api.setStatus(HttpStatus.BAD_REQUEST.value());
                api.setError("Data insertion issue");
            }else{
                for (String moKey : moMap.keySet()) {
                    int num = pickConfirmso.poMatchCountService(moKey);
                    BigDecimal matchCount = new BigDecimal(num);
                    logger.info("Matching count: "+matchCount);
                    if(moMap.get(moKey).compareTo(matchCount) == 0){
                        System.out.println("inside OK content. ");
                        api.setStatus(HttpStatus.OK.value()); 
                        api.setError("Created");
                    }
                    else{
                        System.out.println("inside BAD_REQUEST. ");
                        api.setStatus(HttpStatus.BAD_REQUEST.value());
                        api.setError("Data insertion issue");
                        badCount++;
                    }
                }
            }
            if(badCount > 0){
                api.setStatus(HttpStatus.BAD_REQUEST.value());
                api.setError("Data insertion issue");
            }
        }
        else{
            api.setStatus(HttpStatus.OK.value()); 
            api.setError("Created");
        }
        
        return ResponseEntity.ok().body(api);
    }

    //getBinTransfer Services
    @RequestMapping(value = "/getallpick", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getPickConfirm()throws ParseException{
        return pickConfirmso.getPickConfirm();
    }
}