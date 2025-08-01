package com.mobile.integration.grandstores.Services;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;


import com.mobile.integration.grandstores.Entity.DocumentNumberEO;
import com.mobile.integration.grandstores.Entity.PiCountDetailEO;
import com.mobile.integration.grandstores.Entity.SearchDetailEO;
import com.mobile.integration.grandstores.PackageCalling.ProformaInvoicePK;
import com.mobile.integration.grandstores.Repository.DocumentNumberRO;
import com.mobile.integration.grandstores.Repository.PiCountDetailRO;
import com.mobile.integration.grandstores.Repository.SearchDetailRO;
// import com.mobile.integration.grandstores.Utils.JwtUtils;
// import com.mobile.integration.grandstores.Utils.JsonUtils.JsonUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PiCountDetailSO  extends NamedParameterJdbcDaoSupport{

    @Autowired
    private PiCountDetailRO piCountDetailro;

    @Autowired
    private ProformaInvoicePK proformaInvoicepk;
    
    @Autowired
    private DocumentNumberRO documentNumberro;
    
    @Autowired
    private SearchDetailRO searchDetailro;

    // @Autowired
    // private JsonUtils jsonUtils;

    // @Autowired
    // private JwtUtils jwtUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    
    public ResponseEntity<APIResponse> insertpiDetails(Iterable<PiCountDetailEO> bodyCountDetail) {
        Iterable<PiCountDetailEO> ls=piCountDetailro.saveAll(bodyCountDetail);
        // 
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
        // return null;
    }
    
    public ResponseEntity<APIResponse> getpiDetails() {
        List<PiCountDetailEO> ls=piCountDetailro.findAll();
        // 
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    // GET_DOC_NUMBER
   public ResponseEntity<APIResponse> getDocNumber(Map<String, Object> content) {
        long totProcStartTime = System.currentTimeMillis();

        
        String docNumber=content.get("P_DOC_NUMBER")=="0"?null:content.get("P_DOC_NUMBER").toString();

        List<DocumentNumberEO> ls =documentNumberro.filterBydocNumber(docNumber);
        // Package Callingcomment  
        // Map<String, Object> ls=proformaInvoicepk.calldocumentNumber(docNumber);

        long totProcEndTime = System.currentTimeMillis();
        System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getTotalQty(Map<String, Object> content) {
        String P_DOC_NUMBER=content.get("P_DOC_NUMBER")==""?null:content.get("P_DOC_NUMBER").toString();
        String P_SHIPMENT_REF=content.get("P_SHIPMENT_REF")==""?null:content.get("P_SHIPMENT_REF").toString();
        String P_PI_NUMBER=content.get("P_PI_NUMBER")==""?null:content.get("P_PI_NUMBER").toString();
        System.out.println("P_DOC_NUMBER=>"+P_DOC_NUMBER);
        System.out.println("P_SHIPMENT_REF=>"+P_SHIPMENT_REF);
        System.out.println("P_PI_NUMBER==>"+P_PI_NUMBER);
        // long totProcStartTime = System.currentTimeMillis();
        // Package calling 
        Map<String, Object> ls=proformaInvoicepk.callGetTotalQty(P_DOC_NUMBER,P_SHIPMENT_REF,P_PI_NUMBER);
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }
    
    // pi/getsearchdetail
    // public ResponseEntity<APIResponse> getSearchDetail(Map<String, Object> content) {
    //     String P_DOC_NUMBER=content.get("P_DOC_NUMBER")==""?null:content.get("P_DOC_NUMBER").toString();
    //     String P_SHIPMENT_REF=content.get("P_SHIPMENT_REF")==""?null:content.get("P_SHIPMENT_REF").toString();
    //     String P_PI_NUMBER=content.get("P_PI_NUMBER")==""?null:content.get("P_PI_NUMBER").toString();
    //     // System.out.println("P_DOC_NUMBER=>"+P_DOC_NUMBER);
    //     // System.out.println("P_SHIPMENT_REF=>"+P_SHIPMENT_REF);
    //     // System.out.println("P_PI_NUMBER==>"+P_PI_NUMBER);
    //     // long totProcStartTime = System.currentTimeMillis();
    //     // Package calling 
    //     // Map<String, Object> ls=proformaInvoicepk.callsearchDetail(P_DOC_NUMBER,P_SHIPMENT_REF,P_PI_NUMBER);
    //     // long totProcEndTime = System.currentTimeMillis();
    //     // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");

    //     List<SearchDetailEO>  ls =searchDetailro.rawsearchDtlQuery(P_DOC_NUMBER, P_PI_NUMBER, P_SHIPMENT_REF);
    //     // System.out.println("ls==>"+ls.get(3));
        
    //     APIResponse api=new APIResponse();
    //     api.setData(ls);
    //     api.setStatus(HttpStatus.OK.value());    
    //     return ResponseEntity.ok().body(api);
    // }
 


    public ResponseEntity<APIResponse> getSearchDetail(Map<String, Object> content) {

        String P_DOC_NUMBER=content.get("P_DOC_NUMBER")==""?null:content.get("P_DOC_NUMBER").toString();
        String P_SHIPMENT_REF=content.get("P_SHIPMENT_REF")==""?null:content.get("P_SHIPMENT_REF").toString();
        String P_PI_NUMBER=content.get("P_PI_NUMBER")==""?null:content.get("P_PI_NUMBER").toString();

        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();
        
        String customerContactSQL=

        "SELECT DISTINCT\n" +
        "    xpmh.pi_number    shipment_ref,\n" +
        "    xpmh.document_number,\n" +
        "    xpml.group_code   pi_number\n" +
        "FROM\n" +
        "    xxgs_pi_match_headers   xpmh,\n" +
        "    xxgs_pi_match_lines     xpml\n" +
        "WHERE\n" +
        "    xpmh.header_id = xpml.header_id\n" +
        "    AND xpml.group_code IS NOT NULL\n" +
        "    AND upper(xpmh.status) <> 'C'\n" +
        "    AND xpmh.pi_number = nvl(:p_shipment_ref, xpmh.pi_number)\n" +
        "    AND xpml.group_code = nvl(:p_pi_number, xpml.group_code)\n" +
        "    AND xpmh.document_number = nvl(:p_doc_number, xpmh.document_number)";


        // System.out.println("customerSiteSQL=>"+customerContactSQL);    
        try {
            params.put("p_shipment_ref", P_SHIPMENT_REF);
            params.put("p_pi_number", P_PI_NUMBER);
            params.put("p_doc_number", P_DOC_NUMBER);
            // System.out.println("==>"+customerContactSQL);
            items = getNamedParameterJdbcTemplate().queryForList(customerContactSQL, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
        // return items;
    }

}