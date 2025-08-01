package com.mobile.integration.grandstores.Services;

import java.util.HashMap;
import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.UpdatePackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UpdatePasswordSO {
    
    @Autowired
    private UpdatePackage updatePkg;

    public ResponseEntity<APIResponse> updatePassword(Map<String, Object> content) {

        String P_USER_ID=(String) content.get("P_USER_ID");
        String P_PASSWORD=(String) content.get("P_PASSWORD");
        // System.out.println("P_USER_NAME=>"+P_USER_NAME);
        // System.out.println("P_PASS_WORD=>"+P_PASS_WORD);

        Map<String, Object> items=new HashMap<String, Object>();

        items=updatePkg.updatePassword(P_USER_ID, P_PASSWORD);

        APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());
    
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api);
    }    

    public ResponseEntity<APIResponse> getitemdepartment(Map<String, Object> content) {

        String P_PRODUCT_LINE_CODE=(String) content.get("P_PRODUCT_LINE_CODE");

        Map<String, Object> items=new HashMap<String, Object>();

        items=updatePkg.GET_ITEM_DEPARTMENT(P_PRODUCT_LINE_CODE);

        APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());
    
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api);
    }   


}