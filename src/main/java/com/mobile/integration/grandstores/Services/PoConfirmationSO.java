package com.mobile.integration.grandstores.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.integration.grandstores.Entity.PoConfirmationEO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogEO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogSO;
import com.mobile.integration.grandstores.PackageCalling.POServicePkg;
import com.mobile.integration.grandstores.Repository.PoConfirmationRO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.http.ResponseEntity;

import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Iterator;
// import java.util.Map;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

// import com.mobile.integration.grandstores.PackageCalling.ProformaInvoicePK;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class PoConfirmationSO {



    @Autowired
    private PoConfirmationRO poConfirmationro;

    @Autowired
    private POServicePkg poServicePkg;

    @Autowired
    private MobTransLogSO mobTransLogSO;

    MobTransLogEO mobTransLog = null;

    private static final Logger logger = LoggerFactory.getLogger(PoConfirmationSO.class);  
    
        public ResponseEntity<APIResponse> insertpoConfirm(Iterable<PoConfirmationEO> bodyCountDetail) throws JsonProcessingException, SerialException, SQLException {
        //logging
        mobTransLog = new MobTransLogEO();   
        mobTransLog.setModuleName("Bin Transfer - getItemCrossref");
        Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(bodyCountDetail).toCharArray());
        mobTransLog.setRequest(clobRequest);
        //mobTransLog.setProcessedTime(new Date());
        mobTransLogSO.saveMobTransLog(mobTransLog);

        List<String> myList = new ArrayList<>();
        String comb = "";
		int countD = 0;
        List<PoConfirmationEO> listFromIterator = new ArrayList<>();
        Iterator<PoConfirmationEO> iterator1 = bodyCountDetail.iterator();

        String resultStr = "";
        APIResponse api=new APIResponse();

        while (iterator1.hasNext()) {
            
            PoConfirmationEO current = iterator1.next();
            logger.info("Transaction Type is: "+current.getTransactionType().toString());
            
            if(current.getTransactionType().toString().equalsIgnoreCase("PO_RECEIPT")){
                comb = current.getPoHeaderId().toString() + 
                    "-" +current.getReleaseNum().toString() + 
                    "-" +current.getItemId().toString()+ 
                    "-" +current.getSupInvNum().toString()+
                    "-" +current.getSupInvDate().toString()+
                    "-" +current.getStatus().toString()+
                    "-" +current.getAttribute8().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                }
                else{
                    countD = recordCheckforReceipt(current.getPoHeaderId().toString(), 
                                            current.getReleaseNum().toString(), current.getItemId().toString(),
                                            current.getSupInvNum().toString(), current.getSupInvDate(),
                                            current.getStatus().toString(), current.getAttribute8().toString());
                    if(countD == 0){
                        poConfirmationro.save(current);
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                api.setStatus(HttpStatus.OK.value());
                api.setError("Created");
                  
            }else if(current.getTransactionType().toString().equalsIgnoreCase("PO_DELIVERY_VIRTUAL")){
                
                String p_subinv = current.getDelivSubInv().toString();
                String p_loc = current.getDelivLocator().toString();
                Map<String, Object> valResult = poServicePkg.validateLocPkgCall(p_subinv,p_loc);
                logger.info("p_subinv: "+p_subinv + ", p_loc: "+p_loc +", valResult "+valResult);
                
                if (valResult.containsKey("P_LOC_RESULT")) {
                    Object resultObj = valResult.get("P_LOC_RESULT");
                    resultStr = resultObj.toString();
                    if (resultStr.contains("200")) {
                        /* Commented on 2025-02-01, duplicate filtration logic changed*/
                        comb = current.getPoHeaderId().toString()+ 
                            "-" +current.getReleaseNum().toString()+ 
                            "-" +current.getItemId().toString()+ 
                            "-" +current.getDelivLocator().toString()+
                            "-" +current.getStatus().toString()+
                            "-" +current.getReceiptNum().toString()+
                            "-" +current.getAttribute8().toString();
                        if(myList.contains(comb)){
                            iterator1.remove();
                        }else{
                            // Ensure totalQuantity is always a valid integer
                            String totalQuan = getTotalQuantityCount(
                                current.getPoHeaderId() != null ? current.getPoHeaderId().toString() : "0", 
                                current.getAttribute8() != null ? current.getAttribute8().toString() : "0"
                            );

                            int totalQuantity = (totalQuan != null) ? Integer.parseInt(totalQuan) : 0;
                            String deliveredQuan = getDelvQuantityCount(current.getAttribute8() != null ? current.getAttribute8().toString() : "0");
                            int deliveredQuantity = (deliveredQuan != null) ? Integer.parseInt(deliveredQuan) : 0;
                
                            if(deliveredQuantity+current.getDeliveredQty().intValueExact()<=totalQuantity){
                                current.setTransactionType("PO_DELIVERY");
                                logger.info("Transaction Type while inserting : "+current.getTransactionType().toString());
                                poConfirmationro.save(current);
                                listFromIterator.add(current);
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
            }else if(current.getTransactionType().toString().equalsIgnoreCase("PO_DELIVERY")){
				/* Commented on 2025-02-01, duplicate filtration logic changed*/
				comb = current.getPoHeaderId().toString()+ 
					"-" +current.getReleaseNum().toString()+ 
					"-" +current.getItemId().toString()+ 
					"-" +current.getDelivLocator().toString()+
					"-" +current.getStatus().toString()+
					"-" +current.getReceiptNum().toString()+
					"-" +current.getAttribute8().toString();
				if(myList.contains(comb)){
					iterator1.remove();
				}else{
					//Ensure totalQuantity is always a valid integer
					String totalQuan = getTotalQuantityCount(
						current.getPoHeaderId() != null ? current.getPoHeaderId().toString() : "0", 
						current.getAttribute8() != null ? current.getAttribute8().toString() : "0"
					);

					int totalQuantity = (totalQuan != null) ? Integer.parseInt(totalQuan) : 0;
					String deliveredQuan = getDelvQuantityCount(current.getAttribute8() != null ? current.getAttribute8().toString() : "0");
					int deliveredQuantity = (deliveredQuan != null) ? Integer.parseInt(deliveredQuan) : 0;
		
					if(deliveredQuantity + current.getDeliveredQty().intValueExact()<=totalQuantity){
						poConfirmationro.save(current);
						listFromIterator.add(current);
						myList.add(comb);
					}
				}
				api.setStatus(HttpStatus.OK.value());
				api.setError("Created");
            }
        }               
       
        //Iterable<PoConfirmationEO> ls=poConfirmationro.saveAll(listFromIterator);
         
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> insertpoConfirmBKP(Iterable<PoConfirmationEO> bodyCountDetail) {
        
        List<String> myList = new ArrayList<>();
        String comb = "";
        List<PoConfirmationEO> listFromIterator = new ArrayList<>();
        Iterator<PoConfirmationEO> iterator1 = bodyCountDetail.iterator();
        while (iterator1.hasNext()) {
            PoConfirmationEO current = iterator1.next();
            System.out.println("current.getTransactionType().toString(): "+current.getTransactionType().toString());
            if(current.getTransactionType().toString().equalsIgnoreCase("PO_RECEIPT")){
                comb = current.getPoHeaderId().toString() + 
                    "-" +current.getReleaseNum().toString() + 
                    "-" +current.getItemId().toString()+ 
                    "-" +current.getSupInvNum().toString()+
                    "-" +current.getSupInvDate().toString()+
                    "-" +current.getStatus().toString()+
                    "-" +current.getAttribute8().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countD = recordCheckforReceipt(current.getPoHeaderId().toString(), 
                                            current.getReleaseNum().toString(), current.getItemId().toString(),
                                            current.getSupInvNum().toString(), current.getSupInvDate(),
                                            current.getStatus().toString(), current.getAttribute8().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                  
            }else if(current.getTransactionType().toString().equalsIgnoreCase("PO_DELIVERY")){
                /* Commented on 2025-02-01, duplicate filtration logic changed*/
                comb = current.getPoHeaderId().toString() + 
                    "-" +current.getReleaseNum().toString() + 
                    "-" +current.getItemId().toString()+ 
                    "-" +current.getDelivLocator().toString()+
                    "-" +current.getStatus().toString()+
                    "-" +current.getReceiptNum().toString()+
                    "-" +current.getAttribute8().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    /*int countD = recordCheckforDelivery(current.getPoHeaderId().toString(), 
                                            current.getReleaseNum().toString(), current.getItemId().toString(),
                                            current.getDelivLocator().toString(), current.getStatus().toString(),
                                            current.getReceiptNum().toString(), current.getAttribute8().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }*/
                    // Ensure totalQuantity is always a valid integer
                    String totalQuan = getTotalQuantityCount(
                        current.getPoHeaderId() != null ? current.getPoHeaderId().toString() : "0", 
                        current.getAttribute8() != null ? current.getAttribute8().toString() : "0"
                    );

                    int totalQuantity = (totalQuan != null) ? Integer.parseInt(totalQuan) : 0;

                    String deliveredQuan = getDelvQuantityCount(
                        current.getAttribute8() != null ? current.getAttribute8().toString() : "0"
                    );
                    int deliveredQuantity = (deliveredQuan != null) ? Integer.parseInt(deliveredQuan) : 0;
                    
                    if(deliveredQuantity+current.getDeliveredQty().intValueExact()<=totalQuantity){
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                  
            }
                
        }               
        System.out.println("myL size: " +myList.size());
        System.out.println("listFromIterator: " +listFromIterator.size());
        Iterable<PoConfirmationEO> ls=poConfirmationro.saveAll(listFromIterator);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    private String getDelvQuantityCount(String attr8) {
        System.out.println("inside getDelvQuantityCount service");
        String c = poConfirmationro.getDelvQuantityCount(attr8);
        return c;  
    }

    private String getTotalQuantityCount(String hdrId, String attr8) {
        System.out.println("inside getTotalQuantityCount service");
        String c = poConfirmationro.getTotalQuantityCount(hdrId, attr8);
        return c;  
    }

    private int recordCheckforDelivery(String hdrId, String relNum, String itemId, String delivloc, String status,
            String receiptNum, String attr8) {
        System.out.println("inside recordCheckforDelivery service");
        int c = poConfirmationro.recordCountPODelivery(hdrId, relNum, itemId, delivloc, status, receiptNum, attr8);
        return c;  
    }

    private int recordCheckforReceipt(String hdrId, String relNum, String itemId, String supInvNum, Date supInvDate,
            String status, String att8) {
        System.out.println("inside recordCheckforReceipt");
        int c = poConfirmationro.recordCountPOReceipt(hdrId, relNum, itemId, supInvNum, supInvDate, status, att8);
        return c;  
    }

    public ResponseEntity<APIResponse> getpoConfirm() {
        List<PoConfirmationEO> ls=poConfirmationro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }
    
}