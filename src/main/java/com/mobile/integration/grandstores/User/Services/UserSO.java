package com.mobile.integration.grandstores.User.Services;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
// import java.util.Map;
import java.util.List;
import java.util.Optional;

import com.mobile.integration.grandstores.User.Entity.UserEO;
import com.mobile.integration.grandstores.User.Repository.UserRO;

import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserSO {
    
    @Autowired
    private UserRO userro;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertUser(Iterable<UserEO> bodydtl) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<UserEO> ls=userro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getuser() {
        List<UserEO> ls=userro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }

    public String updateUserLogin(String userId){
        Optional<UserEO> ls=userro.findById(new BigDecimal(userId));   
        // 
        UserEO isls=ls.get();
        // System.out.println("==>"+ls.isPresent());
        if(ls.isPresent()){
            isls.setLastLogonDate(new Date(System.currentTimeMillis()));
            userro.save(isls);
            // System.out.println("==>inserted==>");
            return "Y";    
        }else{
            return "N";    
        }
        
    }



}





