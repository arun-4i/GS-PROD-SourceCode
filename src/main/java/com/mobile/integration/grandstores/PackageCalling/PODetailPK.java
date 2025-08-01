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
public class PODetailPK {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PODetailPK(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${spring.application.schemaName}")
    private String schemaName;

    public Map<String, Object> callPoNumber(String P_PO_NUMBER){
            // System.out.println("schemaName=>"+schemaName);
            SimpleJdbcCall simpleJdbcCall;
            simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
            .withSchemaName(schemaName)                
            .withCatalogName("XXGS_MOB_UTIL_PKG")
            .withProcedureName("GET_PO_NUMBER");
            simpleJdbcCall.declareParameters(
            // In param 
            new SqlParameter("P_PO_NUMBER",  Types.VARCHAR),
            // out param
            new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
            ).compile();

            // Parameters 
            SqlParameterSource param=new MapSqlParameterSource()
            .addValue("P_PO_NUMBER", P_PO_NUMBER);

            Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
            return simpleJdbcCallResult;
        }


    // getPOReleaseNumber
    public Map<String, Object> calPOReleaseNumber(String p_PO_HEADER_ID) {
        // System.out.println("schemaName=>"+schemaName);
        SimpleJdbcCall simpleJdbcCall;
        simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
        .withSchemaName(schemaName)                
        .withCatalogName("XXGS_MOB_UTIL_PKG")
        .withProcedureName("GET_RELEASE_NUM");
        simpleJdbcCall.declareParameters(
        // In param 
        new SqlParameter("P_PO_HEADER_ID",  Types.VARCHAR),
        // Out param
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        ).compile();
        // Parameters 
        SqlParameterSource param=new MapSqlParameterSource()
        .addValue("P_PO_HEADER_ID", p_PO_HEADER_ID)
        ;
        // Execute
        Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
        return simpleJdbcCallResult;
    }

        // getPOItemDetails
        public Map<String, Object> calPOItemDetails(String p_PO_HEADER_ID, String p_PO_RELEASE_ID) {
            // System.out.println("schemaName=>"+schemaName);
            SimpleJdbcCall simpleJdbcCall;
            simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
            .withSchemaName(schemaName)                
            .withCatalogName("XXGS_MOB_UTIL_PKG")
            .withProcedureName("GET_PO_ITEM_DTLS");
            simpleJdbcCall.declareParameters(
            // In param 
            new SqlParameter("P_PO_HEADER_ID",  Types.VARCHAR),
            new SqlParameter("P_PO_RELEASE_ID",  Types.VARCHAR),
            // Out param
            new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
            ).compile();
            // Parameters 
            SqlParameterSource param=new MapSqlParameterSource()
            .addValue("P_PO_HEADER_ID", p_PO_HEADER_ID)
            .addValue("P_PO_RELEASE_ID", p_PO_RELEASE_ID)
            ;
            // Execute
            Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
            return simpleJdbcCallResult;
        }


        // getPOItemDetails
        public Map<String, Object> calPOItemCrossRef(String p_PO_HEADER_ID, String p_PO_RELEASE_ID) {
                    // System.out.println("schemaName=>"+schemaName);
                    SimpleJdbcCall simpleJdbcCall;
                    simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
                    .withSchemaName(schemaName)                
                    .withCatalogName("XXGS_MOB_UTIL_PKG")
                    .withProcedureName("GET_PO_ITEM_CROSS_REF");
                    simpleJdbcCall.declareParameters(
                    // In param 
                    new SqlParameter("P_PO_HEADER_ID",  Types.VARCHAR),
                    new SqlParameter("P_PO_RELEASE_ID",  Types.VARCHAR),
                    // Out param
                    new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
                    ).compile();
                    // Parameters 
                    SqlParameterSource param=new MapSqlParameterSource()
                    .addValue("P_PO_HEADER_ID", p_PO_HEADER_ID)
                    .addValue("P_PO_RELEASE_ID", p_PO_RELEASE_ID)
                    ;
                    // Execute
                    Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
                    return simpleJdbcCallResult;
        }


}