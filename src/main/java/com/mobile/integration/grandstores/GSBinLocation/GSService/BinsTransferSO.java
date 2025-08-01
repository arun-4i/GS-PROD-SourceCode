package com.mobile.integration.grandstores.GSBinLocation.GSService;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.GSBinLocation.GLEntity.BinsTransferEO;
import com.mobile.integration.grandstores.GSBinLocation.GSRepository.BinsTransferRO;

// import com.mobile.integration.grandstores.PackageCalling.ProformaInvoicePK;

import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BinsTransferSO {
    @Autowired
    private BinsTransferRO binTransferro;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertBinTransfer(Iterable<BinsTransferEO> bodydtl) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<BinsTransferEO> ls=binTransferro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getBinTransfer() {
        List<BinsTransferEO> ls=binTransferro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
    
}