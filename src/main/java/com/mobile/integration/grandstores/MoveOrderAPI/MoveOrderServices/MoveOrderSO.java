package com.mobile.integration.grandstores.MoveOrderAPI.MoveOrderServices;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
// import java.text.ParseException;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.mobile.integration.grandstores.PackageCalling.MoveOrderPackage;
// import com.mobile.integration.grandstores.POJO.UserPoJo;
import com.mobile.integration.grandstores.PackageCalling.MoveOrderPkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// -----------
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class MoveOrderSO extends NamedParameterJdbcDaoSupport{
 
    @Autowired
    private MoveOrderPkg moveOrderpkg;
    
    @Autowired
    private MoveOrderPackage mopkg;
    


    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }


    public ResponseEntity<APIResponse> getInvoiceOrganization(Map<String, Object> content)  {
        //
        String p_USER_ID=content.get("P_USER_ID")==null ? null : String.valueOf(content.get("P_USER_ID"));
        String p_ORGANIZATION_CODE=content.get("P_ORGANIZATION_CODE")==null ? null : String.valueOf(content.get("P_ORGANIZATION_CODE"));
        String p_ORGANIZATION_NAME=content.get("P_ORGANIZATION_NAME")==null ? null : String.valueOf(content.get("P_ORGANIZATION_NAME"));
        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        // 
        Map<String, Object> ls=moveOrderpkg.
        getInvoiceOrganization(p_USER_ID, p_ORGANIZATION_CODE, p_ORGANIZATION_NAME);
        // 
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("End Time :: " + totProcEndTime + " ms");
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }



    public ResponseEntity<APIResponse> getInvOrganization(Map<String, Object> content)  {
        //
        String p_USER_ID=content.get("P_USER_ID")==null ? "0" : String.valueOf(content.get("P_USER_ID"));
        String p_ORGANIZATION_CODE=content.get("P_ORGANIZATION_CODE")==null ? "" : String.valueOf(content.get("P_ORGANIZATION_CODE"));
        String p_ORGANIZATION_NAME=content.get("P_ORGANIZATION_NAME")==null ? "" : String.valueOf(content.get("P_ORGANIZATION_NAME"));
        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        // 
        Map<String, Object> ls=mopkg.
        getInvOrg( new BigDecimal(p_USER_ID),p_ORGANIZATION_CODE, p_ORGANIZATION_NAME); 
        // 
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("End Time :: " + totProcEndTime + " ms");
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getMoDetailService(Map<String, Object> content)  {
        //
        String p_ORGANIZATION_ID=content.get("P_ORGANIZATION_ID")==null ? "0" : String.valueOf(content.get("P_ORGANIZATION_ID"));
        String p_MOVE_ORDER_NUM=content.get("P_MOVE_ORDER_NUM")==null ? "" : String.valueOf(content.get("P_MOVE_ORDER_NUM"));
        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        // 
        Map<String, Object> ls=mopkg.
        getmodetailPkg(new BigDecimal(p_ORGANIZATION_ID),p_MOVE_ORDER_NUM); 
        // 
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("End Time :: " + totProcEndTime + " ms");
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getMoItemCrossRef(Map<String, Object> content)  {
        //
        String p_HEADER_ID=content.get("P_HEADER_ID")==null ? "0" : String.valueOf(content.get("P_HEADER_ID"));
        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        // 
        Map<String, Object> ls=mopkg.getmoItemCrossRefPkg(new BigDecimal(p_HEADER_ID));
        // 
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("End Time :: " + totProcEndTime + " ms");
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getMoItemLotService(Map<String, Object> content)  {
        //
        String p_HEADER_ID=content.get("P_HEADER_ID")==null ? "0" : String.valueOf(content.get("P_HEADER_ID"));
        String p_LINE_ID=content.get("P_LINE_ID")==null ? "" : String.valueOf(content.get("P_LINE_ID"));
        String p_INVENTORY_ITEM_ID=content.get("P_INVENTORY_ITEM_ID")==null ? "" : String.valueOf(content.get("P_INVENTORY_ITEM_ID"));
        
        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        // 
        Map<String, Object> ls=mopkg.
        getmoItemLotDtl(new BigDecimal(p_HEADER_ID),p_LINE_ID, p_INVENTORY_ITEM_ID); 
        // 
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("End Time :: " + totProcEndTime + " ms");
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getMoItemDetail(Map<String, Object> content)  {
        //
        String p_HEADER_ID=content.get("P_HEADER_ID")==null ? "0" : String.valueOf(content.get("P_HEADER_ID"));
        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        // 
        Map<String, Object> ls=mopkg.getmoItemDetailPkg(new BigDecimal(p_HEADER_ID));
        // 
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("End Time :: " + totProcEndTime + " ms");
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getJsonPost(String payload) {
        //
        // String p_HEADER_ID=payload;
        // 
        // System.out.println("p_HEADER_ID =>:: " + p_HEADER_ID );
        // // 
        Map<String, Object> ls=mopkg.getJsonFormPkg("MO_CONFIRMATION", payload);
        
        // // 
        // // long totProcEndTime = System.currentTimeMillis();
        // // System.out.println("End Time :: " + totProcEndTime + " ms");
        // // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }

    










}