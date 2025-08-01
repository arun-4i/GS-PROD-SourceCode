package com.mobile.integration.grandstores.MoConfirm.MoConfirmService;
import java.math.BigDecimal;
// import java.util.Map;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmRepository.MoConfirmRO;
import com.mobile.integration.grandstores.PackageCalling.MoveOrderPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MoConfirmSO {

    @Autowired
    private MoConfirmRO moConfirmro;

    @Autowired
    private MoveOrderPackage mopkg;

    public ResponseEntity<APIResponse> insertMoConfirmRO(Iterable<MoConfirmEO> bodydtl) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<MoConfirmEO> ls=moConfirmro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }
    // duplicate line filtration - 18/12/23
    public int recordCountService(String s, String d, String i, String moNumber, String moLineNumber, String status){
        System.out.println("inside recordCountService");
        int c = moConfirmro.recordCount(s, d, i, moNumber, moLineNumber, status);
        return c;
    }

    public ResponseEntity<APIResponse> getReceiptConfirm() {
        List<MoConfirmEO> ls=moConfirmro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }

    @SuppressWarnings("null")
    public ResponseEntity<APIResponse> insertJsonPost(String payload) {
        Map<String, Object> ls=mopkg.insertQuickPickJson(payload);
        //Map<String, Object> ls = mopkg.GET_DEL_INV_DTL("75288");
        APIResponse api=new APIResponse();
        int co =0;
        for (Map.Entry<String, Object> entry : ls.entrySet()) {
            
            String key = entry.getKey();
            String value = entry.getValue().toString();
            co++;
            System.out.println("co : "+co);
            System.out.println("value1: "+value);
            List<String> desc = null;
            StringBuilder errorMsg = new StringBuilder();
            String finalString = null;
            if(co == 2){
                String[] descriptions = value.substring(20, value.length() - 1).split("\\}, \\{ERROR_DESCRIPTION=");
                desc = Arrays.asList(descriptions);
                System.out.println("output: "+desc);
                for (String item : desc) {
                    errorMsg.append(item).append("~"); 
                    System.out.println("item: "+item);
                    finalString = item + "~" +finalString;
                    System.out.println("finalString: "+finalString);
                    
                }
            }
            if(entry.getValue().toString().contains("200")){
                api.setStatus(HttpStatus.OK.value());
                api.setError("Created");
            }else if(entry.getValue().toString().contains("400")){
                api.setStatus(HttpStatus.BAD_REQUEST.value());
                //api.setError(finalString);
            }
            if(api.getStatus() == 400 && co == 2){
                String f = finalString.replace("}", "").substring(0, finalString.length() - 6);
                System.out.println(" F: "+f);
                api.setError("BAD REQUEST~"+f);
            }
        }
        //api.setError(finalString);
        api.setData(ls);
        return ResponseEntity.ok().body(api); 
    }
    
}