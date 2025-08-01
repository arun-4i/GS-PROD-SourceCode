package com.mobile.integration.grandstores.User.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.integration.grandstores.GSBinLocation.GLEntity.LocationMappingEO;
import com.mobile.integration.grandstores.User.Entity.UserResponsibleGroupEO;

import com.mobile.integration.grandstores.Utils.Generic.DateUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;
import com.mobile.integration.grandstores.User.Repository.UserResponsibleGroupRO;

@Service
public class UserResponsibleGroupSO  extends NamedParameterJdbcDaoSupport {
        
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    @Autowired
    private UserResponsibleGroupRO userResponsibleGroupro;

    @Autowired
    private DateUtils dateUtils;

    public ResponseEntity<APIResponse> insertuserrepo(Iterable<UserResponsibleGroupEO> bodydtl) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<UserResponsibleGroupEO> ls=userResponsibleGroupro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getuserrepo() {
        List<UserResponsibleGroupEO> ls=userResponsibleGroupro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }


   //get user repo name
    public ResponseEntity<APIResponse> getuserreponame(Map<String, Object> content) {
        String p_RESPONSIBILITYNAME=
        content.get("RESPONSIBILITY_NAME")==null?"":
        content.get("RESPONSIBILITY_NAME").toString();
        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();
            
        String query=
        "SELECT * FROM XXGS_WMS_RESPONSIBILITY_V WHERE (UPPER (RESPONSIBILITY_NAME) LIKE '%' || UPPER (:pRESPONSIBILITYNAME) || '%' OR :pRESPONSIBILITYNAME IS NULL)";

            try {
                params.put("pRESPONSIBILITYNAME", p_RESPONSIBILITYNAME);
                // System.out.println("==>"+query);
                items = getNamedParameterJdbcTemplate().queryForList(query, params);
            } catch (Exception exp) {
                System.out.println(exp.toString());            
            }

            APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}  

   //get user repo Access
   public ResponseEntity<APIResponse> getuserrepoaccess(Map<String, Object> content) {
    String p_USER_ID=
    content.get("USER_ID")==null?"":
    content.get("USER_ID").toString();
    List<Map<String, Object>> items = null;
    Map<String, Object> params = new HashMap();
        
    String query=
    "SELECT * FROM XXGS_WMS_USER_RESP_ACCESS_V WHERE USER_ID = :pUSER_ID"; 

        try {
            params.put("pUSER_ID", p_USER_ID);
            // System.out.println("==>"+query);
            items = getNamedParameterJdbcTemplate().queryForList(query, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
}  


   //get user repo Access
   public ResponseEntity<APIResponse> getuserrep(Map<String, Object> content) {
    String p_USER_ID=
    content.get("USER_ID")==null?"":
    content.get("USER_ID").toString();
    List<Map<String, Object>> items = null;
    Map<String, Object> params = new HashMap();
        
    String query=
    "SELECT * FROM XXGS_WMS_ALL_USER_RESP_V WHERE USER_ID = :pUSER_ID"; 

        try {
            params.put("pUSER_ID", p_USER_ID);
            // System.out.println("==>"+query);
            items = getNamedParameterJdbcTemplate().queryForList(query, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
} 

   //get user repo Access
   public ResponseEntity<APIResponse> getorg() {
    // Map<String, Object> content
    // String p_USER_ID=
    // content.get("USER_ID")==null?"":
    // content.get("USER_ID").toString();
    List<Map<String, Object>> items = null;
    Map<String, Object> params = new HashMap();
    String query=
    "SELECT * FROM XXGS_INV_ORGANIZATIONS_V"; 
        try {
            // params.put("pUSER_ID", p_USER_ID);
            // System.out.println("==>"+query);
            items = getNamedParameterJdbcTemplate().queryForList(query, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }
        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }
    
    

       //get user repo Access
   public ResponseEntity<APIResponse> getuserrepactive() {
    // String p_USER_ID=
    // content.get("USER_ID")==null?"":
    // content.get("USER_ID").toString();
    List<Map<String, Object>> items = null;
    Map<String, Object> params = new HashMap();
        
    String query=
    "SELECT distinct USER_ID, USER_NAME FROM XXGS_WMS_ALL_USER_RESP_V"; 

        try {
            // params.put("pUSER_ID", p_USER_ID);
            // System.out.println("==>"+query);
            items = getNamedParameterJdbcTemplate().queryForList(query, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
} 



}