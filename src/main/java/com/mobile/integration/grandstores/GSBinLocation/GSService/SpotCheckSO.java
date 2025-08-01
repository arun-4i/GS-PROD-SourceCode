package com.mobile.integration.grandstores.GSBinLocation.GSService;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.GSBinLocation.GLEntity.SpotCheckEO;
import com.mobile.integration.grandstores.GSBinLocation.GSRepository.SpotCheckRO;


import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpotCheckSO {
    

        
    @Autowired
    private SpotCheckRO spotCheckro;

    @Autowired
    private DateUtils dateUtils;


    public ResponseEntity<APIResponse> insertspot(Iterable<SpotCheckEO> bodydtl) {
                //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<SpotCheckEO> ls=spotCheckro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getSpotCheck() {
        List<SpotCheckEO> ls=spotCheckro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    

    }

}