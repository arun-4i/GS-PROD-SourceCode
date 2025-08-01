package com.mobile.integration.grandstores.Services;

// import java.util.Map;
import java.util.List;

// import com.mobile.integration.grandstores.PackageCalling.ProformaInvoicePK;

import com.mobile.integration.grandstores.Entity.PiLotDetailsEO;
import com.mobile.integration.grandstores.Repository.PiLotDetailsRO;
import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PiLotDetailsSO {
    
    @Autowired
    private PiLotDetailsRO piLotDetailsro;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertpiLotDetails(Iterable<PiLotDetailsEO> bodyCountDetail) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------ 
        Iterable<PiLotDetailsEO> ls=piLotDetailsro.saveAll(bodyCountDetail);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
        // return null;
    }

    public ResponseEntity<APIResponse> getpiLotDetails() {
        List<PiLotDetailsEO> ls=piLotDetailsro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }
    


}