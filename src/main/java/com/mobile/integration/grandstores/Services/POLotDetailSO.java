package com.mobile.integration.grandstores.Services;

// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.Entity.POLotDetailEO;
import com.mobile.integration.grandstores.Repository.POLotDetailRO;

// import com.mobile.integration.grandstores.PackageCalling.ProformaInvoicePK;

import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class POLotDetailSO {

    @Autowired
    private POLotDetailRO pOLotDetailRO;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertpoConfirm(Iterable<POLotDetailEO> bodyCountDetail) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<POLotDetailEO> ls=pOLotDetailRO.saveAll(bodyCountDetail);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getPolotDetail() {
        List<POLotDetailEO> ls=pOLotDetailRO.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
    
}