package com.mobile.integration.grandstores.IPDetails.IPService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobile.integration.grandstores.IPDetails.IPEntity.IPEntity;
import com.mobile.integration.grandstores.IPDetails.IPRepository.IPRepo;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class IPService {

    @Autowired
    IPRepo ipRepo;

    public ResponseEntity<APIResponse> getIPDetails() {
        List<IPEntity> ls = ipRepo.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
        //return "testing success";
    }

}
