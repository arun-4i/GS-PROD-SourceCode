package com.mobile.integration.grandstores.User.Services;

import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.http.ResponseEntity;
// import java.util.Map;
import java.util.List;
import com.mobile.integration.grandstores.User.Entity.UserOrgGroupEO;
import com.mobile.integration.grandstores.User.Repository.UserOrgGroupRO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class UserOrgGroupSO  extends NamedParameterJdbcDaoSupport{

    @Autowired
    private UserOrgGroupRO userOrgGroupro;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    public ResponseEntity<APIResponse> insertUserOrg(Iterable<UserOrgGroupEO> bodydtl) {
        //------------------ 
        // for( PiLotDetailsEO element : bodyCountDetail ){
        //     System.out.println("1===>"+element.getLotExpiryDate() );
        //     System.out.println("2===>"+element.getCreationDate() );
        //     System.out.println("3===>"+element.getLastUpdateDate() );
        // }
        //------------------
        Iterable<UserOrgGroupEO> ls=userOrgGroupro.saveAll(bodydtl);
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getuserOrg() {
        List<UserOrgGroupEO> ls=userOrgGroupro.findAll();
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);    
    }

    //get user repo Access
  public ResponseEntity<APIResponse> getUserOrgAccess() {
        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();
        String query=
        "SELECT distinct USER_ID, USER_NAME,FULL_NAME FROM XXGS_WMS_USER_ORG_ACCESS_V"; 
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
  public ResponseEntity<APIResponse> getUserOrgAccessById(Map<String, Object> content) {
        String p_USER_ID=
        content.get("USER_ID")==null?"":
        content.get("USER_ID").toString();
        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();
            
        String query=
        "SELECT * FROM XXGS_WMS_USER_ORG_ACCESS_V where USER_ID = :pUSER_ID"; 
    
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
  public ResponseEntity<APIResponse> getInvOrg() {
    // Map<String, Object> content
    // String p_ORGANIZATION_CODE=
    // content.get("p_ORGANIZATION_CODE")==null?"":
    // content.get("p_ORGANIZATION_CODE").toString();
    List<Map<String, Object>> items = null;
    Map<String, Object> params = new HashMap();
        
    String query=
    "SELECT * FROM XXGS_INV_ORGANIZATIONS_V"; 

        try {
            // params.put("pORGANIZATION_CODE", p_ORGANIZATION_CODE);
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