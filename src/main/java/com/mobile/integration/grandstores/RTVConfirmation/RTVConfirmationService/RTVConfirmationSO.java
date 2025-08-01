package com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationService;
import java.util.Iterator;
// import java.util.Map;
import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.RtvProcessPkg;
import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationDto.RTVConfirmationDto;
import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationEntity.RTVConfirmationEO;
import com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationRepository.RTVConfirmationRO;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RTVConfirmationSO {

    private static final Logger logger = LoggerFactory.getLogger(RTVConfirmationSO.class);

    @Autowired
    private RTVConfirmationRO rtvConfirmationro;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private RtvProcessPkg rtvProcessPkg;

    public ResponseEntity<APIResponse> insertRTVConfirmation(Iterable<RTVConfirmationEO> bodydtl) {
        Iterator<RTVConfirmationEO> iterator1 = bodydtl.iterator();
        while (iterator1.hasNext()) {
            RTVConfirmationEO current = iterator1.next();
            System.out.println("current.getLineId().toString(): "+current.getLineId().toString());
            rtvConfirmationro.delete(current.getLineId().toString());
        }
        Iterable<RTVConfirmationEO> ls=rtvConfirmationro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> updateRtvReqLines(Iterable<RTVConfirmationDto> bodydtl) {
        String P_REQUEST_ID, P_LINE_ID, P_ITEM_ID, P_PICKED_QTY, P_PICKED_BY = null;
        Iterator<RTVConfirmationDto> iterator1 = bodydtl.iterator();
        System.out.println("inside UPDATE_RTV_REQ_LINES SO" +iterator1);
        while (iterator1.hasNext()) {

            RTVConfirmationDto current = iterator1.next();
            System.out.println("inside UPDATE_RTV_REQ_LINES SO: " +current.toString());
            P_REQUEST_ID = current.getRequestId().toString();
            P_LINE_ID = current.getLineId().toString();
            P_ITEM_ID = current.getItemId().toString();
            P_PICKED_QTY = current.getPickedQty().toString();
            P_PICKED_BY = current.getPickedBy().toString();
            Map<String, Object> ls = rtvProcessPkg.updateRtvReqLines(P_REQUEST_ID, P_LINE_ID, P_ITEM_ID, P_PICKED_QTY, P_PICKED_BY);
        }
        APIResponse api=new APIResponse();
        api.setData(iterator1);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);        
    }

    //UPDATE_RTV_REQ_LINES
    /*public ResponseEntity<APIResponse> updateRtvReqLines(Iterable<RTVConfirmationEO> bodydtl) {
        String P_PICKED_QTY, P_PICKED_BY, P_LINE_ID, P_ATTRIBUTE1, P_ATTRIBUTE2, P_ATTRIBUTE3, P_ATTRIBUTE4, P_ATTRIBUTE5 = null;
        Iterator<RTVConfirmationEO> iterator1 = bodydtl.iterator();
        System.out.println("inside UPDATE_RTV_REQ_LINES SO");
        while (iterator1.hasNext()) {
            RTVConfirmationEO current = iterator1.next();
            P_PICKED_QTY = current.getPickedQty().toString();
            P_PICKED_BY = current.getPickedBy().toString();
            P_LINE_ID = current.getLineId().toString();
            P_ATTRIBUTE1 = current.getAttribute1() == null ? null : current.getAttribute1().toString();
            P_ATTRIBUTE2 = current.getAttribute2() == null ? null : current.getAttribute2().toString();
            P_ATTRIBUTE3 = current.getAttribute3() == null ? null : current.getAttribute3().toString();
            P_ATTRIBUTE4 = current.getAttribute4() == null ? null : current.getAttribute4().toString();
            P_ATTRIBUTE5 = current.getAttribute5() == null ? null : current.getAttribute5().toString();
            
            System.out.println("P_PICKED_QTY: "+P_PICKED_QTY+", P_PICKED_BY: "+P_PICKED_BY+ 
            " ,P_LINE_ID"+P_LINE_ID + ", P_ATTRIBUTE1: "+P_ATTRIBUTE1);
            Map<String, Object> ls = rtvProcessPkg.updateRtvReqLines(P_PICKED_QTY, P_PICKED_BY, P_ATTRIBUTE1, P_ATTRIBUTE2, P_ATTRIBUTE3, P_ATTRIBUTE4, P_ATTRIBUTE5, P_LINE_ID);
        }
        APIResponse api=new APIResponse();
        api.setData(iterator1);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);        
    }*/

    
}