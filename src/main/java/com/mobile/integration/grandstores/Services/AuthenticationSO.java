package com.mobile.integration.grandstores.Services;

import java.util.HashMap;
// import java.text.ParseException;
import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.AuthenticationPackage;
// import com.mobile.integration.grandstores.POJO.UserPoJo;
import com.mobile.integration.grandstores.PackageCalling.MobileUtilsPK;
import com.mobile.integration.grandstores.PackageCalling.UpdatePackage;
import com.mobile.integration.grandstores.User.Services.UserSO;
import com.mobile.integration.grandstores.Utils.JsonUtils.JsonUtils;
import com.mobile.integration.grandstores.Utils.JwtWebSecure.JwtUtil;
import com.mobile.integration.grandstores.Utils.JwtWebSecure.MyUserDetailsService;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
// -----------
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class AuthenticationSO   extends NamedParameterJdbcDaoSupport{


    @Autowired
    private MobileUtilsPK mobileUtilspk;
    
    @Autowired
    private JsonUtils jsonUtils;

    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserSO userso;

    @Autowired
    private AuthenticationPackage authPkg;

    @Autowired
    private UpdatePackage updatePkg;

    @Value("${app.instance.name}")
    private String instanceName;
    

    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }


    public ResponseEntity<APIResponse> getAuthentication(Map<String, Object> content)  {
        String P_USER_NAME=(String) content.get("P_USERNAME");
        String P_PASS_WORD=(String) content.get("P_PASSWORD");
        // System.out.println("P_USER_NAME=>"+P_USER_NAME);
        // System.out.println("P_PASS_WORD=>"+P_PASS_WORD);

        long totProcStartTime = System.currentTimeMillis();

        Map<String, Object> ls=mobileUtilspk.submitPackage(P_USER_NAME, P_PASS_WORD);

        long totProcEndTime = System.currentTimeMillis();
        System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");

        //Json Array Data retrive 
        Map<String, Object> singlels=jsonUtils.getJsonArrayChildValue(ls, "P_RECORDSET");        
        String UserId=singlels.get("USER_ID")==null ? "-1" : singlels.get("USER_ID").toString();
        String UserName=singlels.get("DISP_NAME")==null ? "-1" : singlels.get("DISP_NAME").toString();
        String PersonId=singlels.get("PERSON_ID")==null ? "-1" : singlels.get("PERSON_ID").toString();
        
        // String UserId="101";
        // String UserName="abc";
        // String PersonId="101";


        APIResponse api=new APIResponse();

        if(UserId.equalsIgnoreCase("-1")){
            ls.put("P_TOKEN", "-1"); 
            ls.put("P_REFRESH_TOKEN", "-1");
            api.setError("Invalid Token");
            api.setStatus(HttpStatus.BAD_REQUEST.value());
        }else{
                // new UserPoJo(UserName, PersonId);
                try {
                    authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(UserName,PersonId)
                );
                } catch (Exception e) {
                    ls.put("P_TOKEN", "-1"); 
                    ls.put("P_REFRESH_TOKEN", "-1"); 
                }
            // 
            final UserDetails userDetails = userDetailsService
			.loadUserByUsername(UserId);
            // 
            String TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            String REFRESH_TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            ls.put("P_TOKEN", TOKEN);
            ls.put("P_REFRESH_TOKEN", REFRESH_TOKEN);
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());
    
        }
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
                
        return ResponseEntity.ok().body(api); 
    }
    


    public ResponseEntity<APIResponse> getauthen(Map<String, Object> content)  {
        String P_USER_NAME=(String) content.get("P_USERNAME");
        String P_PASS_WORD=(String) content.get("P_PASSWORD");
        // System.out.println("P_USER_NAME=>"+P_USER_NAME);
        // System.out.println("P_PASS_WORD=>"+P_PASS_WORD);

        Map<String, Object> ls=new HashMap<String, Object>();

        long totProcStartTime = System.currentTimeMillis();
        // Map<String, Object> ls=mobileUtilspk.submitPackage(P_USER_NAME, P_PASS_WORD);

        String UserId="1";
        String UserName="abc";
        String PersonId="abc";
        
        APIResponse api=new APIResponse();

        if(UserId.equalsIgnoreCase("-1")){
            ls.put("P_TOKEN", "-1"); 
            ls.put("P_REFRESH_TOKEN", "-1");
            api.setError("Invalid Token");
            api.setStatus(HttpStatus.BAD_REQUEST.value());
        }else{
                // new UserPoJo(UserName, PersonId);
                try {
                    authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(UserName,PersonId)
                );
                } catch (Exception e) {
                    ls.put("P_TOKEN", "-1"); 
                    ls.put("P_REFRESH_TOKEN", "-1"); 
                }
            // 
            final UserDetails userDetails = userDetailsService
			.loadUserByUsername(UserId);
            // 
            String TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            String REFRESH_TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            ls.put("P_TOKEN", TOKEN);
            ls.put("P_REFRESH_TOKEN", REFRESH_TOKEN);
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());
    
        }
        long totProcEndTime = System.currentTimeMillis();
        System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
                
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getAuthenticationView(Map<String, Object> content) {

        String P_USER_NAME=(String) content.get("P_USERNAME");
        String P_PASS_WORD=(String) content.get("P_PASSWORD");
        // System.out.println("P_USER_NAME=>"+P_USER_NAME);
        // System.out.println("P_PASS_WORD=>"+P_PASS_WORD);

        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();
        
        String customerContactSQL=

        "SELECT USER_ID,\n" +
        "USER_NAME,\n" +
        "PERSON_ID,\n" +
        "BUSINESS_GROUP_ID,\n" +
        "RESP_FLAG,\n" +
        "DISP_NAME\n" +
        "FROM XXGS_MOB_USER_AUTH_V \n" +
        "WHERE user_name = :p_USER_NAME \n" +
        "AND XXGS_MOB_UTIL_PKG.EBS_AUTHENTICATE (:p_USER_NAME, :p_PASS_WORD) = 'Y'"; 

        // System.out.println("customerSiteSQL=>"+customerContactSQL);    
        try {
            params.put("p_USER_NAME", P_USER_NAME);
            params.put("p_PASS_WORD", P_PASS_WORD);
            // System.out.println("==>"+customerContactSQL);
            items = getNamedParameterJdbcTemplate().queryForList(customerContactSQL, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        // String UserId=items.get(0)==null?"0":items.get(0).toString();
        
        String UserId=items.get(0).get("USER_ID")==null ? "-1":items.get(0).get("USER_ID").toString();
        String UserName=items.get(0).get("DISP_NAME")==null ? "-1" : items.get(0).get("DISP_NAME").toString();
        String PersonId=items.get(0).get("PERSON_ID")==null ? "-1" : items.get(0).get("PERSON_ID").toString();
        // System.out.println("UserId==>"+UserId);
        // System.out.println("UserName==>"+UserName);
        // System.out.println("PersonId==>"+PersonId);

        APIResponse api=new APIResponse();
        if(UserId.equalsIgnoreCase("-1")){
            items.get(0).put("P_TOKEN", "-1"); 
            items.get(0).put("P_REFRESH_TOKEN", "-1");
            api.setError("Invalid Token");
            api.setStatus(HttpStatus.BAD_REQUEST.value());
        }else{
                // new UserPoJo(UserName, PersonId);
                try {
                    authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(UserName,PersonId)
                );
                } catch (Exception e) {
                    items.get(0).put("P_TOKEN", "-1"); 
                    items.get(0).put("P_REFRESH_TOKEN", "-1"); 
                }
            // 
            final UserDetails userDetails = userDetailsService
			.loadUserByUsername(UserId);
            //
            String res=userso.updateUserLogin(UserId); 
            // System.err.println("res==>"+res);
            String TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            String REFRESH_TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            items.get(0).put("P_TOKEN", TOKEN);
            items.get(0).put("P_REFRESH_TOKEN", REFRESH_TOKEN);
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());
    
        }
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
                
        return ResponseEntity.ok().body(api);
    }    

    
    public ResponseEntity<APIResponse> getAuthenticationPkg(Map<String, Object> content) {

        String P_USER_NAME=(String) content.get("P_USERNAME");
        String P_PASS_WORD=(String) content.get("P_PASSWORD");
        // System.out.println("P_USER_NAME=>"+P_USER_NAME);
        // System.out.println("P_PASS_WORD=>"+P_PASS_WORD);

        Map<String, Object> items=new HashMap<String, Object>();

        items=authPkg.getAuthPkg(P_USER_NAME, P_PASS_WORD);

        // System.out.println("items==>"+items);

        //Json Array Data retrive 
        Map<String, Object> singlels=jsonUtils.getJsonArrayChildValue(items, "P_GET_USER_AUTH");        
        String UserId=singlels.get("USER_ID")==null ? "-1" : singlels.get("USER_ID").toString();
        String UserName=singlels.get("DISP_NAME")==null ? "-1" : singlels.get("DISP_NAME").toString();
        String PersonId=singlels.get("USER_ID")==null ? "-1" : singlels.get("USER_ID").toString();

        // System.out.println("UserId==>"+UserId);
        // System.out.println("UserName==>"+UserName);
        // System.out.println("PersonId==>"+PersonId);

        APIResponse api=new APIResponse();
        if(UserId.equalsIgnoreCase("-1")){
            items.put("P_TOKEN", "-1"); 
            items.put("P_REFRESH_TOKEN", "-1");
            api.setError("Invalid Token");
            api.setStatus(HttpStatus.BAD_REQUEST.value());
        }else{
                // new UserPoJo(UserName, PersonId);
                try {
                    authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(UserName,PersonId)
                );
                } catch (Exception e) {
                    items.put("P_TOKEN", "-1"); 
                    items.put("P_REFRESH_TOKEN", "-1"); 
                }
        //************************* */ 
            final UserDetails userDetails = userDetailsService
			.loadUserByUsername(UserId);
        //************************* */
            String res=userso.updateUserLogin(UserId); 
            // System.err.println("res==>"+res);
            String TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            String REFRESH_TOKEN=jwtTokenUtil.generateToken(userDetails, UserName, PersonId);
            items.put("P_TOKEN", TOKEN);
            items.put("P_REFRESH_TOKEN", REFRESH_TOKEN);
            items.put("DB_INSTANCE", instanceName);
            items.put("APP_VERSION", "V5");
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());
    
        }
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api);
    }    
    





}