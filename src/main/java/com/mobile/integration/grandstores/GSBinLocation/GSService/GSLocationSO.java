package com.mobile.integration.grandstores.GSBinLocation.GSService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.GsLocationPackage;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class GSLocationSO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    @Autowired
    GsLocationPackage gsLocationPackage;
 
    
        // getInvOrganization
        public ResponseEntity<APIResponse> getInvOrganization(Map<String, Object> content) {
            String P_USER_ID=content.get("USER_ID")==null?"":content.get("USER_ID").toString();
            String P_ORGANIZATION_CODE=content.get("ORGANIZATION_CODE")==null?"":content.get("ORGANIZATION_CODE").toString();

            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String poItemDtlQuery=
            "SELECT\n" +
            " * \n" +
            "FROM XXGS_WMS_USER_ORG_ACCESS_V \n" +
            "where\n" +
            "ORGANIZATION_CODE LIKE :P_ORGANIZATIONCODE||'%'\n" +  
            "AND USER_ID =nvl(:P_USERID, USER_ID)" ; 
            
            try {
                params.put("P_ORGANIZATIONCODE", P_ORGANIZATION_CODE);
                params.put("P_USERID", P_USER_ID);
                // params.put("P_PO_RELEASE_ID", P_PO_RELEASE_ID);
                // System.out.println("==>"+poItemDtlQuery);
                items = getNamedParameterJdbcTemplate().queryForList(poItemDtlQuery, params);
            } catch (Exception exp) {
                System.out.println(exp.toString());            
            }

            APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}   


        // getSecondaryInv
        public ResponseEntity<APIResponse> getSecondaryInv(Map<String, Object> content) {
            String P_ORG_ID=content.get("P_ORGID")==null?"":content.get("P_ORGID").toString();
            // long totProcStartTime = System.currentTimeMillis();
            // Package calling 
            // Map<String, Object> ls=poDetailpk.calPOItemCrossRef(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            // List<PoItemCrossRefEO> ls= poItemCrossRefro.rawpoItemCrossQuery(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            //

            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String poItemDtlQuery=
            "    select\n" +
            "    * \n" +
            "    from\n" +
            "    XXGS_SECONDARY_INVENTORIES_V\n" +
            "    where\n" +
            "    ORGANIZATION_ID = :P_ORGID";
            // 

    
            try {
                params.put("P_ORGID", P_ORG_ID);
                // System.out.println("==>"+customerContactSQL);
                items = getNamedParameterJdbcTemplate().queryForList(poItemDtlQuery, params);
            } catch (Exception exp) {
                System.out.println(exp.toString());            
            }

            APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}   


        // getInvOrganization
        public ResponseEntity<APIResponse> getMTLSystemItem(Map<String, Object> content) {
            String P_ORG_ID=content.get("P_ORGID")==null?"":content.get("P_ORGID").toString();
            String P_ITEM_SEGMENT=content.get("P_ITEM_SEGMENT")==null?"":content.get("P_ITEM_SEGMENT").toString();
            
            
            // long totProcStartTime = System.currentTimeMillis();
            // Package calling 
            // Map<String, Object> ls=poDetailpk.calPOItemCrossRef(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            // List<PoItemCrossRefEO> ls= poItemCrossRefro.rawpoItemCrossQuery(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            //

            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String query=

            "   SELECT\n" +
            "    * \n" +
            "    FROM\n" +
            "    XXGS_MTL_SYSYEM_ITEMS_V \n" +
            "    WHERE\n" +
            "    ORGANIZATION_ID  = :P_ORGID \n" +
            " and  item_segment1  = :P_ITEM_SEGMENT";
            
    
            try {
                params.put("P_ORGID", P_ORG_ID);
                params.put("P_ITEM_SEGMENT", P_ITEM_SEGMENT);
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


        // getInvOrganization
        public ResponseEntity<APIResponse> getItemCrossRef(Map<String, Object> content) {
            String P_ORG_ID=content.get("P_ORGID")==null?"":content.get("P_ORGID").toString();
            String P_ITEM_SEGMENT=content.get("P_ITEM_SEGMENT")==null?"":content.get("P_ITEM_SEGMENT").toString();
            // long totProcStartTime = System.currentTimeMillis();
            // Package calling 
            // Map<String, Object> ls=poDetailpk.calPOItemCrossRef(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            // List<PoItemCrossRefEO> ls= poItemCrossRefro.rawpoItemCrossQuery(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            //

            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String poItemDtlQuery=
            "    SELECT\n" +
            "    * \n" +
            "    FROM \n" +
            "    XXGS_ITEMS_CROSS_REF_V \n" +
            "    WHERE \n" +
            "    ORGANIZATION_ID  = :P_ORGID \n" +
            " and  item_segment1  = :P_ITEM_SEGMENT";

            try {
                params.put("P_ORGID", P_ORG_ID);
                params.put("P_ITEM_SEGMENT", P_ITEM_SEGMENT);
                // System.out.println("==>"+customerContactSQL);
                items = getNamedParameterJdbcTemplate().queryForList(poItemDtlQuery, params);
            } catch (Exception exp) {
                System.out.println(exp.toString());            
            }

            APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}   


        // getDepartment
        public ResponseEntity<APIResponse> getDepartment(Map<String, Object> content) {
            String P_SEGMENTONE=content.get("P_SEGMENTONE")==null?"":content.get("P_SEGMENTONE").toString();
            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String query=
            " Select distinct item_segment1 from XXGS_MTL_SYSYEM_ITEMS_V where item_segment1 LIKE :P_SEGMENTONE||'%'" ; 
    
            try {
                params.put("P_SEGMENTONE", P_SEGMENTONE);
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



        // getmtscrossref
   public ResponseEntity<APIResponse> getmtscrossref(Map<String, Object> content) {
            String P_ORGANIZATION_ID=content.get("P_ORGANIZATION_ID")==null?"":content.get("P_ORGANIZATION_ID").toString();
            String P_ITEM_SEGMENT=content.get("P_ITEM_SEGMENT")==null?"":content.get("P_ITEM_SEGMENT").toString();
            String P_ITEM_CODE = null;
            String query= null;
            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            if(content.containsKey("P_ITEM_CODE")){
                System.out.println("P_Itemcode is available");
                P_ITEM_CODE = content.get("P_ITEM_CODE")==null?"":content.get("P_ITEM_CODE").toString();
                query = "SELECT * FROM XXGS_MTL_CROSS_REF_V WHERE ORGANIZATION_ID = :p_ORGANIZATION_ID AND ITEM_SEGMENT1 = :p_ITEM_SEGMENT AND ITEM_CODE = :p_ITEM_CODE"; 
                try {
                    params.put("p_ORGANIZATION_ID", P_ORGANIZATION_ID);
                    params.put("p_ITEM_SEGMENT", P_ITEM_SEGMENT);
                    params.put("p_ITEM_CODE", P_ITEM_CODE);
                    items = getNamedParameterJdbcTemplate().queryForList(query, params);
                    System.out.println("items with item_code: "+items); 
                } catch (Exception exp) {
                    System.out.println(exp.toString());            
                }
            }else{
                query = "SELECT * FROM XXGS_MTL_CROSS_REF_V WHERE ORGANIZATION_ID = :p_ORGANIZATION_ID AND ITEM_SEGMENT1 = :p_ITEM_SEGMENT";
                try {
                    params.put("p_ORGANIZATION_ID", P_ORGANIZATION_ID);
                    params.put("p_ITEM_SEGMENT", P_ITEM_SEGMENT);
                    items = getNamedParameterJdbcTemplate().queryForList(query, params);
                    System.out.println("items without itemcode: "+items); 
                } catch (Exception exp) {
                    System.out.println(exp.toString());            
                }
            }

            APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}  

        // getmtsspotcheck
        public ResponseEntity<APIResponse> getmtsspotcheck(Map<String, Object> content) {
            String P_ORGANIZATION_ID = content.get("P_ORGANIZATION_ID")==null?"":content.get("P_ORGANIZATION_ID").toString();
            String P_ITEM_SEGMENT = content.get("P_ITEM_SEGMENT")==null?"":content.get("P_ITEM_SEGMENT").toString();
            
            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String query = "SELECT * FROM XXGS_MTL_SPOT_CHECK_V WHERE ORGANIZATION_ID = :p_ORGANIZATION_ID AND ITEM_SEGMENT1 = :p_ITEM_SEGMENT";
            try {
                params.put("p_ORGANIZATION_ID", P_ORGANIZATION_ID);
                params.put("p_ITEM_SEGMENT", P_ITEM_SEGMENT);
                items = getNamedParameterJdbcTemplate().queryForList(query, params);
            } catch (Exception exp) {
                System.out.println(exp.toString());            
            }
            
            APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
}  

        // getmtldesccheck
        public ResponseEntity<APIResponse> getmtldesccheck(Map<String, Object> content) {
            String P_ITEM_SEGMENT=content.get("P_ITEM_SEGMENT")==null?"":content.get("P_ITEM_SEGMENT").toString();
            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String query=
            "SELECT * FROM XXGS_GET_MTL_DESC_CROSS_V WHERE ITEM_SEGMENT1 = :p_ITEM_SEGMENT"; 
    
            try {
                params.put("p_ITEM_SEGMENT", P_ITEM_SEGMENT);
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


        public ResponseEntity<APIResponse> getItemCodelist(Map<String, Object> content) {         
            String p_org_id=content.get("P_ORGANIZATION_ID")==null?"":content.get("P_ORGANIZATION_ID").toString();
            String p_sub_inv_code=content.get("P_SUBINVENTORY_CODE")==null?"":content.get("P_SUBINVENTORY_CODE").toString();
            
            /*
            {
                "P_ITEM_CODE" : "",
                "P_ORGANIZATION_ID" : "121",
                "P_SUBINVENTORY_CODE" : "102"
            }
            */
            /*List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String query=
            "SELECT distinct(ITEM_CODE) FROM XXGS_MTL_CROSS_REF_V WHERE ORGANIZATION_ID = :p_org_id and ITEM_SEGMENT1 = :p_sub_inv_code "; 
    
            try {
                params.put("p_org_id", p_org_id);
                params.put("p_sub_inv_code", p_sub_inv_code);
                System.out.println("==>"+query);
                items = getNamedParameterJdbcTemplate().queryForList(query, params);
            } catch (Exception exp) {
                System.out.println(exp.toString());            
            }*/

            
            Map<String, Object> ls=gsLocationPackage.getItemCodeList(p_org_id, p_sub_inv_code);

            APIResponse api=new APIResponse();
            api.setData(ls);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }  
}