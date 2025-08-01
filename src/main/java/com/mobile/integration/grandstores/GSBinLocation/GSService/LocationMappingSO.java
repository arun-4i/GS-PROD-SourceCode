package com.mobile.integration.grandstores.GSBinLocation.GSService;
// import java.util.Map;
import java.util.List;

import com.mobile.integration.grandstores.Entity.POLotDetailEO;
import com.mobile.integration.grandstores.GSBinLocation.GLEntity.LocationMappingEO;
import com.mobile.integration.grandstores.GSBinLocation.GSRepository.LocationMappingRO;
import com.mobile.integration.grandstores.Repository.POLotDetailRO;

// import com.mobile.integration.grandstores.PackageCalling.ProformaInvoicePK;

import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationMappingSO {

    
    @Autowired
    private LocationMappingRO locationmapRO;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertLocationMap(Iterable<LocationMappingEO> bodydtl) {
        Iterable<LocationMappingEO> ls=locationmapRO.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getLocationMap() {
        List<LocationMappingEO> ls=locationmapRO.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }
    
}