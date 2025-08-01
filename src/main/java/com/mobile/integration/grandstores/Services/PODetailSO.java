package com.mobile.integration.grandstores.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.PODetailPK;
import com.mobile.integration.grandstores.PackageCalling.POServicePkg;
import com.mobile.integration.grandstores.Repository.PoItemCrossRefRO;
import com.mobile.integration.grandstores.Repository.PoItemDetailRO;
import com.mobile.integration.grandstores.Repository.PoNumberRO;
import com.mobile.integration.grandstores.Repository.ReleaseNumberRO;
import com.mobile.integration.grandstores.Utils.JsonUtils.JsonUtils;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class PODetailSO  extends NamedParameterJdbcDaoSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    @Autowired
    private PODetailPK poDetailpk;
    
    @Autowired
    private JsonUtils jsonUtils;

    @Autowired
    private PoNumberRO poNumberro;

    @Autowired
    private ReleaseNumberRO releaseNumberro;

    @Autowired
    private PoItemDetailRO poItemDetailro;

    @Autowired
    private PoItemCrossRefRO poItemCrossRefro;


    @Autowired
    private POServicePkg poServicepkg;

    // po/ponumber
    public ResponseEntity<APIResponse> getPONumber(Map<String, Object> content) {
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
        String P_ORG_ID=content.get("P_ORG_ID")==null?"":content.get("P_ORG_ID").toString();
        // Package Calling
        // Map<String, Object> ls=poDetailpk.callPoNumber(P_PO_NUMBER);
        // List<PoNumberEO> ls=poNumberro.filterByPoNumber(P_PO_NUMBER);
        List<Map<String, Object>> items = null;
        Map<String, Object> params = new HashMap();
        
        String poNumberQuery=
        // "SELECT * FROM XXGS_GET_PO_V WHERE P_PO_NUMBER = :P_PO_NUMBER AND nvl(ORGANIZATION_ID, 0) = NVL(:P_ORG_ID, 0)";
        
        "SELECT DISTINCT\n" +
        "PHA.PO_HEADER_ID,\n" +
        "PHA.SEGMENT1 PO_NUMBER,\n" +
        "PHA.TYPE_LOOKUP_CODE,\n" +
        "APS.VENDOR_ID,\n" +
        "APS.SEGMENT1 VENDOR_NUMBER,\n" +
        "APS.VENDOR_NAME,\n" +
        "APSS.VENDOR_SITE_ID,\n" +
        "APSS.VENDOR_SITE_CODE,\n" +
        "PLLA.SHIP_TO_ORGANIZATION_ID ORGANIZATION_ID\n"+
        "FROM PO_HEADERS_ALL PHA, AP_SUPPLIERS APS, AP_SUPPLIER_SITES_ALL APSS, PO_LINE_LOCATIONS_ALL PLLA\n" +
        "WHERE PHA.VENDOR_ID = APS.VENDOR_ID(+)\n" +
        "AND APS.VENDOR_ID = APSS.VENDOR_ID(+)\n" +
        "AND PHA.VENDOR_SITE_ID = APSS.VENDOR_SITE_ID(+)\n" +
        "AND PLLA.PO_HEADER_ID = PHA.PO_HEADER_ID\n"+
        "AND PHA.SEGMENT1 LIKE :P_PO_NUMBER||'%'\n" +
        "AND PLLA.SHIP_TO_ORGANIZATION_ID LIKE :P_ORG_ID||'%'\n" +
        "AND UPPER(PHA.AUTHORIZATION_STATUS) = 'APPROVED'\n" +
        "AND PHA.APPROVED_FLAG = 'Y'\n" +
        "AND NVL(PHA.CANCEL_FLAG,'N') = 'N'\n" +
        "AND (UPPER (PHA.CLOSED_CODE) = 'OPEN' OR PHA.CLOSED_CODE IS NULL)\n" +
        "ORDER BY PO_NUMBER";

        try {
            params.put("P_PO_NUMBER", P_PO_NUMBER);
            params.put("P_ORG_ID", P_ORG_ID);
            
            // System.out.println("==>"+poNumberQuery);
            items = getNamedParameterJdbcTemplate().queryForList(poNumberQuery, params);
        } catch (Exception exp) {
            System.out.println(exp.toString());            
        }

        APIResponse api=new APIResponse();
        api.setData(items);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }


        // getPOReleaseNumber
        public ResponseEntity<APIResponse> getPOReleaseNumber(Map<String, Object> content) {
            String P_PO_HEADER_ID=(String) content.get("P_PO_HEADER_ID");
            // Package calling 
            // Map<String, Object> ls=poDetailpk.calPOReleaseNumber(P_PO_HEADER_ID);
            // List<ReleaseNumberEO> ls=releaseNumberro.filterByPoHdrId(P_PO_HEADER_ID); 

            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String poNumberQuery=
            "SELECT * FROM XXGS_GET_RELEASE_NUM_V WHERE PO_HEADER_ID = :P_PO_HEADER_ID";

            // "SELECT DISTINCT PHA.PO_HEADER_ID,\n" +
            // "PHA.SEGMENT1 PO_NUMBER,\n" +
            // "PRA.PO_RELEASE_ID,\n" +
            // "PRA.RELEASE_NUM,\n" +
            // "PRA.RELEASE_DATE\n" +
            // "FROM PO_RELEASES_ALL PRA, PO_HEADERS_ALL PHA\n" +
            // "WHERE PRA.PO_HEADER_ID = :P_PO_HEADER_ID\n" +
            // "AND PRA.APPROVED_FLAG = 'Y'\n" +
            // "AND NVL (PRA.CANCEL_FLAG, 'N') = 'N'\n" +
            // "AND PRA.AUTHORIZATION_STATUS = 'APPROVED'\n" +
            // "AND PRA.CLOSED_CODE = 'OPEN'\n" +
            // "AND UPPER (PHA.AUTHORIZATION_STATUS) = 'APPROVED'\n" +
            // "AND PHA.APPROVED_FLAG = 'Y'\n" +
            // "AND NVL (PHA.CANCEL_FLAG, 'N') = 'N'\n" +
            // "AND (UPPER (PHA.CLOSED_CODE) = 'OPEN' OR PHA.CLOSED_CODE IS NULL)\n" +
            // "ORDER BY PHA.SEGMENT1, PRA.RELEASE_NUM";
    
            try {
                params.put("P_PO_HEADER_ID", P_PO_HEADER_ID);
                // System.out.println("==>"+customerContactSQL);
                items = getNamedParameterJdbcTemplate().queryForList(poNumberQuery, params);
            } catch (Exception exp) {
                System.out.println(exp.toString());            
            }
    
            APIResponse api=new APIResponse();
            api.setData(items);
            api.setStatus(HttpStatus.OK.value());    
            return ResponseEntity.ok().body(api); 
        }

        // getPOItemDetails
        public ResponseEntity<APIResponse> getPOItemDetails(Map<String, Object> content) {
                    String P_PO_HEADER_ID=content.get("P_PO_HEADER_ID")==null?"":content.get("P_PO_HEADER_ID").toString();
                    String P_PO_RELEASE_ID=content.get("P_PO_RELEASE_ID")==null?"0":content.get("P_PO_RELEASE_ID").toString();
                    String P_ORG_ID=content.get("P_ORG_ID")==null?"0":content.get("P_ORG_ID").toString();
                    List<Map<String, Object>> items = null;
                    Map<String, Object> params = new HashMap();
                    /*
                     * {"P_ORG_ID":"121","P_PO_HEADER_ID":"24630","P_PO_RELEASE_ID":"421863"}
                     */
                    String poItemDtlQuery=
                    // "SELECT PHA.PO_HEADER_ID,\n" +
                    // "PHA.SEGMENT1 PO_NUMBER,\n" +
                    // "PHA.CREATION_DATE,\n" +
                    // "PRA.PO_RELEASE_ID,\n" +
                    // "PRA.RELEASE_NUM,\n" +
                    // "APS.VENDOR_ID,\n" +
                    // "APS.SEGMENT1 SUPPLIER_NUMBER,\n" +
                    // "APS.VENDOR_NAME SUPPLIER_NAME,\n" +
                    // "APSS.VENDOR_SITE_ID,\n" +
                    // "APSS.VENDOR_SITE_CODE,\n" +
                    // "APSS.VENDOR_SITE_CODE_ALT,\n" +
                    // "APSS.CITY VENDOR_SITE,\n" +
                    // "PLA.LINE_NUM,\n" +
                    // "PLA.ITEM_ID,\n" +
                    // "MSIB.SEGMENT1 || '-' || MSIB.SEGMENT2 || '-' || MSIB.SEGMENT3\n" +
                    // "ITEM_CODE,\n" +
                    // "MSIB.DESCRIPTION,\n" +
                    // "PLA.UNIT_MEAS_LOOKUP_CODE UOM,\n" +
                    // "NVL (CASE WHEN MSIB.LOT_CONTROL_CODE = '2' THEN 'Y' ELSE NULL END,MSIB.LOT_STATUS_ENABLED) LOT_STATUS_ENABLED,\n" +
                    // "NVL (PLLA.QUANTITY, 0) - NVL (PLLA.QUANTITY_RECEIVED, 0) QUANTITY\n" +
                    // "FROM PO_HEADERS_ALL PHA,\n" +
                    // "PO_LINES_ALL PLA,\n" +
                    // "PO_LINE_LOCATIONS_ALL PLLA,\n" +
                    // "PO_RELEASES_ALL PRA,\n" +
                    // "MTL_SYSTEM_ITEMS_B MSIB,\n" +
                    // "AP_SUPPLIERS APS,\n" +
                    // "AP_SUPPLIER_SITES_ALL APSS\n" +
                    // "WHERE PHA.VENDOR_ID = APS.VENDOR_ID\n" +
                    // "AND PHA.VENDOR_SITE_ID = APSS.VENDOR_SITE_ID\n" +
                    // "AND PHA.ORG_ID = PLA.ORG_ID\n" +
                    // "AND PHA.PO_HEADER_ID = PLA.PO_HEADER_ID\n" +
                    // "AND PHA.PO_HEADER_ID = PLLA.PO_HEADER_ID\n" +
                    // "AND PLA.PO_LINE_ID = PLLA.PO_LINE_ID\n" +
                    // "AND PLLA.PO_RELEASE_ID = PRA.PO_RELEASE_ID(+)\n" +
                    // "AND PHA.PO_HEADER_ID = PRA.PO_HEADER_ID(+)\n" +
                    // "AND PLA.ITEM_ID = MSIB.INVENTORY_ITEM_ID\n" +
                    // "AND MSIB.ORGANIZATION_ID = PLLA.SHIP_TO_ORGANIZATION_ID\n" +
                    // "AND UPPER (PHA.AUTHORIZATION_STATUS) = 'APPROVED'\n" +
                    // "AND PHA.APPROVED_FLAG = 'Y'\n" +
                    // "AND NVL (PHA.CANCEL_FLAG, 'N') = 'N'\n" +
                    // "AND (UPPER (PHA.CLOSED_CODE) = 'OPEN' OR PHA.CLOSED_CODE IS NULL)\n" +
                    // "AND NVL (PLLA.QUANTITY, 0) - NVL (PLLA.QUANTITY_RECEIVED, 0) > 0\n" +
                    // "AND PHA.ORG_ID = 101\n" +
                    // "AND PHA.PO_HEADER_ID = :P_PO_HEADER_ID\n" +
                    // "AND (PRA.PO_RELEASE_ID = :P_PO_RELEASE_ID\n" +
                    // "OR :P_PO_RELEASE_ID IS NULL)\n" +
                    // "ORDER BY PO_NUMBER,\n" +
                    // "SUPPLIER_NAME,\n" +
                    // "RELEASE_NUM,\n" +
                    // "LINE_NUM";

                    "SELECT * FROM XXGS_GET_PO_ITEM_DTLS_V WHERE PO_HEADER_ID = :P_PO_HEADER_ID AND nvl(PO_RELEASE_ID, 0) = NVL(:P_PO_RELEASE_ID, 0) AND nvl(ORGANIZATION_ID, 0) = NVL(:P_ORG_ID, 0) ORDER BY LINE_LOCATION_ID, LINE_NUM ASC";
            
                    try {
                        params.put("P_PO_HEADER_ID", P_PO_HEADER_ID);
                        params.put("P_PO_RELEASE_ID", P_PO_RELEASE_ID);
                        params.put("P_ORG_ID", P_ORG_ID);
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

        // getPOItemCrossRef
        public ResponseEntity<APIResponse> getPOItemCrossRef(Map<String, Object> content) {
            String P_PO_HEADER_ID=content.get("P_PO_HEADER_ID")==null?"":content.get("P_PO_HEADER_ID").toString();
            String P_PO_RELEASE_ID=content.get("P_PO_RELEASE_ID")==null?"":content.get("P_PO_RELEASE_ID").toString();
            String P_ORG_ID=content.get("P_ORG_ID")==null?"":content.get("P_ORG_ID").toString();
            // long totProcStartTime = System.currentTimeMillis();
            // Package calling 
            // Map<String, Object> ls=poDetailpk.calPOItemCrossRef(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            // List<PoItemCrossRefEO> ls= poItemCrossRefro.rawpoItemCrossQuery(P_PO_HEADER_ID, P_PO_RELEASE_ID);
            //

            List<Map<String, Object>> items = null;
            Map<String, Object> params = new HashMap();
            
            String poItemDtlQuery=

            "SELECT * FROM XXGS_GET_PO_ITEM_CRS_V WHERE PO_HEADER_ID = :P_PO_HEADER_ID AND nvl(PO_RELEASE_ID, 0) = NVL(:P_PO_RELEASE_ID, 0) AND nvl(ORGANIZATION_ID, 0) = NVL(:P_ORG_ID, 0)";

            // "SELECT PHA.PO_HEADER_ID,\n" +
            // "PHA.SEGMENT1 PO_NUMBER,\n" +
            // "PHA.CREATION_DATE,\n" +
            // "PRA.PO_RELEASE_ID,\n" +
            // "PRA.RELEASE_NUM,\n" +
            // "APS.VENDOR_ID,\n" +
            // "APS.SEGMENT1 SUPPLIER_NUMBER,\n" +
            // "APS.VENDOR_NAME SUPPLIER_NAME,\n" +
            // "APSS.VENDOR_SITE_ID,\n" +
            // "APSS.VENDOR_SITE_CODE,\n" +
            // "APSS.VENDOR_SITE_CODE_ALT,\n" +
            // "APSS.CITY VENDOR_SITE,\n" +
            // "PLA.LINE_NUM,\n" +
            // "PLA.ITEM_ID,\n" +
            // "MSIB.SEGMENT1 || '-' || MSIB.SEGMENT2 || '-' || MSIB.SEGMENT3\n" +
            // "ITEM_CODE,\n" +
            // "MSIB.DESCRIPTION,\n" +
            // "MCR.CROSS_REFERENCE,\n" +
            // "PLA.UNIT_MEAS_LOOKUP_CODE UOM,\n" +
            // "NVL (PLLA.QUANTITY, 0) - NVL (PLLA.QUANTITY_RECEIVED, 0) QUANTITY\n" +
            // "FROM PO_HEADERS_ALL PHA,\n" +
            // "PO_LINES_ALL PLA,\n" +
            // "PO_LINE_LOCATIONS_ALL PLLA,\n" +
            // "PO_RELEASES_ALL PRA,\n" +
            // "MTL_SYSTEM_ITEMS_B MSIB,\n" +
            // "MTL_CROSS_REFERENCES MCR,\n" +
            // "AP_SUPPLIERS APS,\n" +
            // "AP_SUPPLIER_SITES_ALL APSS\n" +
            // "WHERE PHA.VENDOR_ID = APS.VENDOR_ID\n" +
            // "AND PHA.VENDOR_SITE_ID = APSS.VENDOR_SITE_ID\n" +
            // "AND PHA.ORG_ID = PLA.ORG_ID\n" +
            // "AND PHA.PO_HEADER_ID = PLA.PO_HEADER_ID\n" +
            // "AND PHA.PO_HEADER_ID = PLLA.PO_HEADER_ID\n" +
            // "AND PLA.PO_LINE_ID = PLLA.PO_LINE_ID\n" +
            // "AND PLLA.PO_RELEASE_ID = PRA.PO_RELEASE_ID(+)\n" +
            // "AND PHA.PO_HEADER_ID = PRA.PO_HEADER_ID(+)\n" +
            // "AND PLA.ITEM_ID = MSIB.INVENTORY_ITEM_ID\n" +
            // "AND MSIB.ORGANIZATION_ID = PLLA.SHIP_TO_ORGANIZATION_ID\n" +
            // "AND MSIB.INVENTORY_ITEM_ID = MCR.INVENTORY_ITEM_ID\n" +
            // "AND (MCR.ORGANIZATION_ID = MSIB.ORGANIZATION_ID\n" +
            // "OR MCR.ORG_INDEPENDENT_FLAG = 'Y')\n" +
            // "AND UPPER (PHA.AUTHORIZATION_STATUS) = 'APPROVED'\n" +
            // "AND PHA.APPROVED_FLAG = 'Y'\n" +
            // "AND NVL (PHA.CANCEL_FLAG, 'N') = 'N'\n" +
            // "AND (UPPER (PHA.CLOSED_CODE) = 'OPEN' OR PHA.CLOSED_CODE IS NULL)\n" +
            // "AND NVL (PLLA.QUANTITY, 0) - NVL (PLLA.QUANTITY_RECEIVED, 0) > 0\n" +
            // "AND PHA.ORG_ID = 101\n" +
            // "AND PHA.PO_HEADER_ID = :P_PO_HEADER_ID\n" +
            // "AND (PRA.PO_RELEASE_ID = :P_PO_RELEASE_ID\n" +
            // "AND (MCR.ORGANIZATION_ID = :P_ORG_ID\n" +
            // "OR :P_PO_RELEASE_ID IS NULL)\n" +
            // "ORDER BY PO_NUMBER,\n" +
            // "SUPPLIER_NAME,\n" +
            // "RELEASE_NUM,\n" +
            // "LINE_NUM";
    
            try {
                params.put("P_PO_HEADER_ID", P_PO_HEADER_ID);
                params.put("P_PO_RELEASE_ID", P_PO_RELEASE_ID);
                params.put("P_ORG_ID", P_ORG_ID);
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


    public ResponseEntity<APIResponse> getPORCPTGen(Map<String, Object> content) {
                    //
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null ? null : String.valueOf(content.get("P_PO_NUMBER"));
        String P_RELEASE_NUM=content.get("P_RELEASE_NUM")==null ? null : String.valueOf(content.get("P_RELEASE_NUM"));
        // String P_ORG_ID=content.get("P_ORG_ID")==null ? null : String.valueOf(content.get("P_ORG_ID"));
        

        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        // 
        Map<String, Object> ls=poServicepkg.getPORCPTGen(P_PO_NUMBER, P_RELEASE_NUM);
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



}