package com.mobile.integration.grandstores.PackageCalling;
import java.sql.Types;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class ProformaInvoicePK {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProformaInvoicePK(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${spring.application.schemaName}")
    private String schemaName;

    public Map<String, Object> callPISupplier(){
            // System.out.println("schemaName=>"+schemaName);
            SimpleJdbcCall simpleJdbcCall;
            simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
            .withSchemaName(schemaName)                
            .withCatalogName("XXGS_MOB_UTIL_PKG")
            .withProcedureName("GET_PI_SUPPLIERS");
            simpleJdbcCall.declareParameters(
            // new SqlParameter("P_USER_NAME",  Types.VARCHAR),
            // new SqlParameter("P_PASSWORD",  Types.VARCHAR),
            // out param
            new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
            // ,new SqlOutParameter("P_ERR_MSG",        Types.VARCHAR)
            ).compile();

            SqlParameterSource param=new MapSqlParameterSource()
            // .addValue("P_USER_NAME", P_USER_NAME)
            // .addValue("P_PASSWORD", P_PASS_WORD)
            ;
            Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
            return simpleJdbcCallResult;
        }


        public Map<String, Object> callShipmentRef(String p_SUPPLIER_ID) {
        // System.out.println("schemaName=>"+schemaName);
            SimpleJdbcCall simpleJdbcCall;
            simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
            .withSchemaName(schemaName)                
            .withCatalogName("XXGS_MOB_UTIL_PKG")
            .withProcedureName("GET_SHIPMENT_REF");
            simpleJdbcCall.declareParameters(
        // In param 
            new SqlParameter("P_SUPPLIER_ID",  Types.VARCHAR),
        // Out param
            new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
            ).compile();
        // Parameters 
            SqlParameterSource param=new MapSqlParameterSource()
            .addValue("P_SUPPLIER_ID", p_SUPPLIER_ID)
            ;
        // Execute
            Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
            return simpleJdbcCallResult;  
        }


    public Map<String, Object> callPINumber(String p_SHIPMENT_REF) {
        // System.out.println("schemaName=>"+schemaName);
        SimpleJdbcCall simpleJdbcCall;
        simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
        .withSchemaName(schemaName)                
        .withCatalogName("XXGS_MOB_UTIL_PKG")
        .withProcedureName("GET_PI_NUMBER");
        simpleJdbcCall.declareParameters(
        // In param 
        new SqlParameter("P_SHIPMENT_REF",  Types.VARCHAR),
        // Out param
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        ).compile();
        // Parameters 
        SqlParameterSource param=new MapSqlParameterSource()
        .addValue("P_SHIPMENT_REF", p_SHIPMENT_REF)
        ;
        // Execute
        Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
        return simpleJdbcCallResult;
    }


    // GET_PI_ITEM_DTLS
    public Map<String, Object> callPIItemDetail(
        String p_HEADER_ID, String p_DOC_NUMBER, String  p_SHIPMENT_REF, String p_PI_NUMBER ) {
        // System.out.println("schemaName=>"+schemaName);
        SimpleJdbcCall simpleJdbcCall;
        simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
        .withSchemaName(schemaName)                
        .withCatalogName("XXGS_MOB_UTIL_PKG")
        .withProcedureName("GET_PI_ITEM_DTLS");
        simpleJdbcCall.declareParameters(
        // In param 
        new SqlParameter("P_HEADER_ID",  Types.VARCHAR),
        new SqlParameter("P_DOC_NUMBER",  Types.VARCHAR),
        new SqlParameter("P_SHIPMENT_REF",  Types.VARCHAR),
        new SqlParameter("P_PI_NUMBER",  Types.VARCHAR),
        // Out param
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        ).compile();
        // Parameters 
        SqlParameterSource param=new MapSqlParameterSource()
        .addValue("P_HEADER_ID", p_HEADER_ID)
        .addValue("P_DOC_NUMBER", p_DOC_NUMBER)
        .addValue("P_SHIPMENT_REF", p_SHIPMENT_REF)
        .addValue("P_PI_NUMBER", p_PI_NUMBER)
        ;
        // Execute
        Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
        return simpleJdbcCallResult;
    }

    //call GET_PI_ITEM_CROSS_REF
    public Map<String, Object> callPIItemCrossRef(
        String p_HEADER_ID,String p_DOC_NUMBER, String p_SHIPMENT_REF, String p_PI_NUMBER) {
        // System.out.println("schemaName=>"+schemaName);
        SimpleJdbcCall simpleJdbcCall;
        simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
        .withSchemaName(schemaName)                
        .withCatalogName("XXGS_MOB_UTIL_PKG")
        .withProcedureName("GET_PI_ITEM_CROSS_REF");
        simpleJdbcCall.declareParameters(
        // In param 
        new SqlParameter("P_HEADER_ID",  Types.VARCHAR),
        new SqlParameter("P_DOC_NUMBER",  Types.VARCHAR),
        new SqlParameter("P_SHIPMENT_REF",  Types.VARCHAR),
        new SqlParameter("P_PI_NUMBER",  Types.VARCHAR),
        // Out param
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        ).compile();
        // Parameters 
        SqlParameterSource param=new MapSqlParameterSource()
        .addValue("P_HEADER_ID", p_HEADER_ID)
        .addValue("P_DOC_NUMBER", p_DOC_NUMBER)
        .addValue("P_SHIPMENT_REF", p_SHIPMENT_REF)
        .addValue("P_PI_NUMBER", p_PI_NUMBER)
        ;
        // Execute
        Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
        return simpleJdbcCallResult;
    } 

    // get Document Number
    public Map<String, Object> calldocumentNumber(String docNumber){
        // System.out.println("schemaName=>"+schemaName);
        SimpleJdbcCall simpleJdbcCall;
        simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
        .withSchemaName(schemaName)                
        .withCatalogName("XXGS_MOB_UTIL_PKG")
        .withProcedureName("GET_DOC_NUMBER");
        simpleJdbcCall.declareParameters(
        new SqlParameter("P_DOC_NUMBER",  Types.VARCHAR),
        // new SqlParameter("P_PASSWORD",  Types.VARCHAR),
        // out param
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        // ,new SqlOutParameter("P_ERR_MSG",        Types.VARCHAR)
        ).compile();

        SqlParameterSource param=new MapSqlParameterSource()
        .addValue("P_DOC_NUMBER", docNumber)
        // .addValue("P_PASSWORD", P_PASS_WORD)
        ;
        Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
        return simpleJdbcCallResult;
    }
    

    //call PI Item Cross Ref
    public Map<String, Object> callGetTotalQty(
                                String p_DOC_NUMBER, 
                                String p_SHIPMENT_REF, 
                                String p_PI_NUMBER) {
            // System.out.println("schemaName=>"+schemaName);
            SimpleJdbcCall simpleJdbcCall;
            simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
            .withSchemaName(schemaName)                
            .withCatalogName("XXGS_MOB_UTIL_PKG")
            .withProcedureName("GET_TOTAL_QTY");
            simpleJdbcCall.declareParameters(
            // In param 
            new SqlParameter("P_DOC_NUMBER",  Types.VARCHAR),
            new SqlParameter("P_SHIPMENT_REF",  Types.VARCHAR),
            new SqlParameter("P_PI_NUMBER",  Types.VARCHAR),
            // Out param
            new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
            ).compile();
            // Parameters 
            SqlParameterSource param=new MapSqlParameterSource()
            .addValue("P_DOC_NUMBER", p_DOC_NUMBER)
            .addValue("P_SHIPMENT_REF", p_SHIPMENT_REF)
            .addValue("P_PI_NUMBER", p_PI_NUMBER)
            ;
            // Execute
            Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
            return simpleJdbcCallResult;
        } 

            //call PI Item Cross Ref
    public Map<String, Object> callsearchDetail(
                                    String p_DOC_NUMBER, 
                                    String p_SHIPMENT_REF, 
                                    String p_PI_NUMBER) {
        // System.out.println("schemaName=>"+schemaName);
        SimpleJdbcCall simpleJdbcCall;
        simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
        .withSchemaName(schemaName)                
        .withCatalogName("XXGS_MOB_UTIL_PKG")
        .withProcedureName("GET_SEARCH_DETAILS");
        simpleJdbcCall.declareParameters(
        // In param 
        new SqlParameter("P_DOC_NUMBER",  Types.VARCHAR),
        new SqlParameter("P_SHIPMENT_REF",  Types.VARCHAR),
        new SqlParameter("P_PI_NUMBER",  Types.VARCHAR),
        // Out param
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        ).compile();
        // Parameters 
        SqlParameterSource param=new MapSqlParameterSource()
        .addValue("P_DOC_NUMBER", p_DOC_NUMBER)
        .addValue("P_SHIPMENT_REF", p_SHIPMENT_REF)
        .addValue("P_PI_NUMBER", p_PI_NUMBER)
        ;
        // Execute
        Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
        return simpleJdbcCallResult;
        }


}