package com.mobile.integration.grandstores.Services;

import java.util.HashMap;
// import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.mobile.integration.grandstores.Entity.PIItemDetailEO;
import com.mobile.integration.grandstores.Entity.PiItemCrossRefEO;
import com.mobile.integration.grandstores.Entity.PiNumberEO;
import com.mobile.integration.grandstores.Entity.PiSupplierEO;
import com.mobile.integration.grandstores.Entity.ShipmentRefEO;
import com.mobile.integration.grandstores.PackageCalling.ProformaInvoicePK;
import com.mobile.integration.grandstores.Repository.PIItemDetailRO;
import com.mobile.integration.grandstores.Repository.PiItemCrossRefRO;
import com.mobile.integration.grandstores.Repository.PiNumberRO;
import com.mobile.integration.grandstores.Repository.PiSupplierRO;
import com.mobile.integration.grandstores.Repository.ShipmentRefRO;
// import com.mobile.integration.grandstores.Utils.JwtUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProformaInvoiceSO extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    @Autowired
    private ProformaInvoicePK proformaInvoicepk;
    
    // @Autowired
    // private JsonUtils jsonUtils;

    // @Autowired
    // private JwtUtils jwtUtils;

    @Autowired
    private PIItemDetailRO piItemDetailro;

    @Autowired
    private PiItemCrossRefRO piItemCrossRefro;

    @Autowired
    private PiSupplierRO piSupplierro;
    
    @Autowired
    private ShipmentRefRO shipmentRefro;

    @Autowired
    private PiNumberRO piNumberro;


    

    // pisupplier
    public ResponseEntity<APIResponse> getPISupplier(Map<String, Object> content) {
        String P_SUPPLIER_NAME=(String) content.get("P_SUPPLIER_NAME");
        // long totProcStartTime = System.currentTimeMillis();
        // Package Calling
        // Map<String, Object> ls=proformaInvoicepk.callPISupplier();
        List<PiSupplierEO> ls= piSupplierro.filterBySupplierName(P_SUPPLIER_NAME);
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    // shipmentref XXGS_GET_SHIPMENT_REF_V
    public ResponseEntity<APIResponse> getShipmentRef(Map<String, Object> content) {
        String P_SUPPLIER_ID=(String) content.get("P_SUPPLIER_ID");
        // long totProcStartTime = System.currentTimeMillis();
        // Package calling 
        // Map<String, Object> ls=proformaInvoicepk.callShipmentRef(P_SUPPLIER_ID);

        List<ShipmentRefEO> ls=shipmentRefro.filterBySupplierRef(P_SUPPLIER_ID);

        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }


    // invoice/pinumber- XXGS_GET_PI_NUMBER_V
    public ResponseEntity<APIResponse> getPINumber(Map<String, Object> content) {
        String P_SHIPMENT_REF=(String) content.get("P_SHIPMENT_REF");
        // long totProcStartTime = System.currentTimeMillis();
        // Package calling 
        // Map<String, Object> ls=proformaInvoicepk.callPINumber(P_SHIPMENT_REF);
        List<PiNumberEO> ls =piNumberro.rawpiNumberQuery(P_SHIPMENT_REF);
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    // GET_PI_ITEM_DTLS - XXGS_GET_PI_ITEM_DTLS_V
    public ResponseEntity<APIResponse> getPIItemDetail(Map<String, Object> content) {
        String P_HEADER_ID=content.get("P_HEADER_ID")==null?"":content.get("P_HEADER_ID").toString();
        String P_DOC_NUMBER=(String) content.get("P_DOC_NUMBER")==null?"":content.get("P_DOC_NUMBER").toString();
        String P_SHIPMENT_REF=(String) content.get("P_SHIPMENT_REF")==null?"":content.get("P_SHIPMENT_REF").toString();
        String P_PI_NUMBER=(String) content.get("P_PI_NUMBER")==null?"":content.get("P_PI_NUMBER").toString();
        // Package calling 
        // Map<String, Object> ls=proformaInvoicepk.
        // callPIItemDetail(P_HEADER_ID, P_DOC_NUMBER, P_SHIPMENT_REF,P_PI_NUMBER);
        // List<PIItemDetailEO> ls=piItemDetailro.
        // findByheaderIdOrdocumentNumberOrsupplierNumberOrpiNumber
        // ( new BigDecimal(P_HEADER_ID),  
        //   new BigDecimal(P_DOC_NUMBER), 
        //   P_SHIPMENT_REF, P_PI_NUMBER);
        // level 2 
        // List<PIItemDetailEO> ls=piItemDetailro.
        // rawpiItemQuery(P_HEADER_ID,P_DOC_NUMBER, P_SHIPMENT_REF,P_PI_NUMBER);
        // level 3 
        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();

        String itemDtlQuery=
        "SELECT XPMH.HEADER_ID,\n" +
        "XPMH.INVENTORY_ORG_ID,\n" +
        "XPMH.DOCUMENT_NUMBER,\n" +
        "XPMH.SUPPLIER_ID,\n" +
        "(SELECT MAX (SEGMENT1)\n" +
        "FROM AP_SUPPLIERS\n" +
        "WHERE VENDOR_ID = XPMH.SUPPLIER_ID)\n" +
        "SUPPLIER_NUMBER,\n" +
        "(SELECT MAX (VENDOR_NAME)\n" +
        "FROM AP_SUPPLIERS\n" +
        "WHERE VENDOR_ID = XPMH.SUPPLIER_ID)\n" +
        "SUPPLIER_NAME,\n" +
        "XPMH.PI_NUMBER SHIPMENT_REF,\n" +
        "XPML.LINE_ID,\n" +
        "XPML.GROUP_CODE PI_NUMBER,\n" +
        "XPML.ITEM_ID,\n" +
        "XPML.ITEM_CODE,\n" +
        "(SELECT DESCRIPTION\n" +
        "FROM MTL_SYSTEM_ITEMS_B\n" +
        "WHERE SEGMENT1\n" +
        "|| '-'\n" +
        "|| SEGMENT2\n" +
        "|| '-'\n" +
        "|| SEGMENT3 = XPML.ITEM_CODE\n" +
        "AND ORGANIZATION_ID = XPMH.INVENTORY_ORG_ID)\n" +
        "ITEM_DESCRIPTION,\n" +
        "(SELECT NVL (CASE WHEN LOT_CONTROL_CODE = '2' THEN 'Y' ELSE NULL END,LOT_STATUS_ENABLED)\n" +
        "FROM MTL_SYSTEM_ITEMS_B\n" +
        "WHERE INVENTORY_ITEM_ID = XPML.ITEM_ID\n" +
        "AND ORGANIZATION_ID = XPMH.INVENTORY_ORG_ID) LOT_STATUS_ENABLED,\n" +
        "SUM (NVL (XPML.PI_QTY, 0)) PI_QTY,\n" +
        "SUM (XPML.PI_UNIT_PRICE) PI_UNIT_PRICE\n" +
        "FROM XXGS_PI_MATCH_HEADERS XPMH, XXGS_PI_MATCH_LINES XPML\n" +
        "WHERE XPMH.HEADER_ID = XPML.HEADER_ID\n" +
        "AND UPPER (XPMH.STATUS) <> 'C'\n" +
        "AND XPML.PI_QTY IS NOT NULL\n" +
        "AND NOT EXISTS (SELECT 1 FROM XXGS_PI_COUNT_DETAILS WHERE HEADER_ID = XPMH.HEADER_ID AND PI_NUMBER = XPML.GROUP_CODE AND ITEM_ID = XPML.ITEM_ID)\n" +
        "AND (UPPER (XPMH.PI_NUMBER) LIKE '%' || UPPER (:P_SHIPMENT_REF) || '%'OR :P_SHIPMENT_REF IS NULL)\n" +
        "AND (UPPER (XPML.GROUP_CODE) LIKE '%' || UPPER (:P_PI_NUMBER) || '%' OR :P_PI_NUMBER IS NULL)\n" +
        "AND (XPMH.HEADER_ID LIKE '%' || :P_HEADER_ID || '%' OR :P_HEADER_ID IS NULL)\n" +
        "AND (XPMH.DOCUMENT_NUMBER LIKE '%' || :P_DOC_NUMBER || '%' OR :P_DOC_NUMBER IS NULL)\n" +
        "GROUP BY XPMH.HEADER_ID,\n" +
        "XPMH.INVENTORY_ORG_ID,\n" +
        "XPML.GROUP_CODE,\n" +
        "XPML.ITEM_ID,\n" +
        "XPML.LINE_ID,\n" +
        "XPML.ITEM_CODE,\n" +
        "XPMH.DOCUMENT_NUMBER,\n" +
        "XPMH.SUPPLIER_ID,\n" +
        "XPMH.PI_NUMBER\n" +
        "ORDER BY PI_NUMBER, ITEM_DESCRIPTION";   

        try {
            params.put("P_SHIPMENT_REF", P_SHIPMENT_REF);
            params.put("P_PI_NUMBER", P_PI_NUMBER);
            params.put("P_HEADER_ID", P_HEADER_ID);
            params.put("P_DOC_NUMBER", P_DOC_NUMBER);
            // System.out.println("==>"+customerContactSQL);
            items = getNamedParameterJdbcTemplate().queryForList(itemDtlQuery, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);

    }

    //GET_PI_ITEM_CROSS_REF - XXGS_GET_PI_ITEM_CROSS_REF_V
    public ResponseEntity<APIResponse> getPIItemCrossRef(Map<String, Object> content) {
        String P_HEADER_ID=(String) content.get("P_HEADER_ID");
        String P_DOC_NUMBER=(String) content.get("P_DOC_NUMBER");
        String P_SHIPMENT_REF=(String) content.get("P_SHIPMENT_REF");
        String P_PI_NUMBER=(String) content.get("P_PI_NUMBER");
        // long totProcStartTime = System.currentTimeMillis();
        // Package calling 
        // Map<String, Object> ls=proformaInvoicepk.
        // callPIItemCrossRef(P_HEADER_ID,P_DOC_NUMBER,P_SHIPMENT_REF,P_PI_NUMBER);
        // level 2
        // List<PiItemCrossRefEO> ls= piItemCrossRefro.rawpiItemCrossRefQuery(P_HEADER_ID, P_DOC_NUMBER, P_SHIPMENT_REF, P_PI_NUMBER);
        // level 3
        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();

        String itemCrossRefQuery=
        "SELECT XPMH.HEADER_ID,\n" +
        "XPMH.INVENTORY_ORG_ID,\n" +
        "XPMH.DOCUMENT_NUMBER,\n" +
        "XPMH.SUPPLIER_ID,\n" +
        "(SELECT MAX (SEGMENT1)\n" +
        "FROM AP_SUPPLIERS\n" +
        "WHERE VENDOR_ID = XPMH.SUPPLIER_ID)\n" +
        "SUPPLIER_NUMBER,\n" +
        "(SELECT MAX (VENDOR_NAME)\n" +
        "FROM AP_SUPPLIERS\n" +
        "WHERE VENDOR_ID = XPMH.SUPPLIER_ID)\n" +
        "SUPPLIER_NAME,\n" +
        "XPMH.PI_NUMBER SHIPMENT_REF,\n" +
        "XPML.GROUP_CODE PI_NUMBER,\n" +
        "XPML.ITEM_ID,\n" +
        "XPML.ITEM_CODE,\n" +
        "(SELECT DESCRIPTION\n" +
        "FROM MTL_SYSTEM_ITEMS_B\n" +
        "WHERE INVENTORY_ITEM_ID =\n" +
        "XPML.ITEM_ID\n" +
        "AND ORGANIZATION_ID = XPMH.INVENTORY_ORG_ID)\n" +
        "ITEM_DESCRIPTION,\n" +
        "MCR.CROSS_REFERENCE,\n" +
        "SUM (XPML.PI_QTY) PI_QTY,\n" +
        "SUM (XPML.PI_UNIT_PRICE) PI_UNIT_PRICE\n" +
        "FROM XXGS_PI_MATCH_HEADERS XPMH,\n" +
        "XXGS_PI_MATCH_LINES XPML,\n" +
        "MTL_CROSS_REFERENCES MCR\n" +
        "WHERE XPMH.HEADER_ID = XPML.HEADER_ID\n" +
        "AND UPPER (XPMH.STATUS) <> 'C'\n" +
        "AND MCR.CROSS_REFERENCE_TYPE = 'Bar Code Number'\n" +
        "AND XPML.ITEM_ID = MCR.INVENTORY_ITEM_ID\n" +
        "AND (MCR.ORGANIZATION_ID = XPMH.INVENTORY_ORG_ID\n" +
        "OR MCR.ORG_INDEPENDENT_FLAG = 'Y')\n" +
        "AND NOT EXISTS (SELECT 1 FROM XXGS_PI_COUNT_DETAILS WHERE HEADER_ID = XPMH.HEADER_ID AND PI_NUMBER = XPML.GROUP_CODE AND ITEM_ID = XPML.ITEM_ID)\n" +
        "AND (UPPER (XPMH.PI_NUMBER) LIKE '%' || UPPER (:P_SHIPMENT_REF) || '%' OR :P_SHIPMENT_REF IS NULL)\n" +
        "AND (UPPER (XPML.GROUP_CODE) LIKE '%' || UPPER (:P_PI_NUMBER) || '%' OR :P_PI_NUMBER IS NULL)\n" +
        "AND (XPMH.HEADER_ID LIKE '%' || :P_HEADER_ID || '%' OR :P_HEADER_ID IS NULL)\n" +
        "AND (XPMH.DOCUMENT_NUMBER LIKE '%' || :P_DOC_NUMBER || '%' OR :P_DOC_NUMBER IS NULL)\n" +
        "GROUP BY XPMH.HEADER_ID,\n" +
        "XPMH.INVENTORY_ORG_ID,\n" +
        "XPML.GROUP_CODE,\n" +
        "XPML.ITEM_ID,\n" +
        "XPML.ITEM_CODE,\n" +
        "XPMH.DOCUMENT_NUMBER,\n" +
        "MCR.CROSS_REFERENCE,\n" +
        "XPMH.SUPPLIER_ID,\n" +
        "XPMH.PI_NUMBER\n" +
        "ORDER BY PI_NUMBER, ITEM_DESCRIPTION"; 

        // System.out.println("customerSiteSQL=>"+customerContactSQL);    
        try {
            params.put("P_SHIPMENT_REF", P_SHIPMENT_REF);
            params.put("P_PI_NUMBER", P_PI_NUMBER);
            params.put("P_HEADER_ID", P_HEADER_ID);
            params.put("P_DOC_NUMBER", P_DOC_NUMBER);
            // System.out.println("==>"+customerContactSQL);
            items = getNamedParameterJdbcTemplate().queryForList(itemCrossRefQuery, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);

    }



    

}