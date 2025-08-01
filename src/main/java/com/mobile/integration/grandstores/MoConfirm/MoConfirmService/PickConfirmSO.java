package com.mobile.integration.grandstores.MoConfirm.MoConfirmService;
import java.util.Iterator;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoPickConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.MoPickConfirmEOT;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.PickConfirmEO;
import com.mobile.integration.grandstores.MoConfirm.MoConfirmRepository.PickConfirmRO;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PickConfirmSO {
    
    @Autowired
    private PickConfirmRO pickConfirmro;


    // @Autowired
    // private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertPickConfirm(Iterable<PickConfirmEO> bodydtl) {
        Iterable<PickConfirmEO> ls=pickConfirmro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    

    public String insertPickConfirmtest(MoPickConfirmEOT bodydtl) {  
        return "updated";
    }

    public String insertPickConfirmtestArray(JSONArray bodydtl) {
        return "updated";
    }

    // duplicate line filtration - 18/12/23
    public int poRecordCountService(String delDetialId, String fromSerialNum, String att1, 
                                        String att2, String att3, String att4){
        System.out.println("inside poRecordCountService");
        int c = pickConfirmro.recordCountPickConfirm(delDetialId, fromSerialNum, att1, att2, att3, att4);
        return c;
    }

    // 
    public int poMatchCountService(String delDetialId){
        System.out.println("inside poRecordCountService");
        int c = pickConfirmro.poMatchCount(delDetialId);
        return c;
    }


    public ResponseEntity<APIResponse> getPickConfirm() {
        List<PickConfirmEO> ls=pickConfirmro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }



    public int poRecordCountDelivery(String serialNo, String att2, String att3, String trans) {
        System.out.println("inside poRecordCountDelivery");
        int c = pickConfirmro.recordCountDelivery(serialNo, att2, att3, trans);
        return c;
    }

    
}