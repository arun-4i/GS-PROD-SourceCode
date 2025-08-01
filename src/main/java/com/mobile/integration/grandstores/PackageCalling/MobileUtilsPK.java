package com.mobile.integration.grandstores.PackageCalling;

import java.sql.Types;
import java.util.Map;

import org.hibernate.dialect.OracleTypesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;



@Component
public class MobileUtilsPK {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MobileUtilsPK(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${spring.application.schemaName}")
    private String schemaName;


    public Map<String, Object> submitPackage(
                                String P_USER_NAME, 
                                String P_PASS_WORD
                                ){

   long totProcStartTime = System.currentTimeMillis();
    SimpleJdbcCall simpleJdbcCall;
    // Map<String, Object> inParamMap = null;        
    simpleJdbcCall=new SimpleJdbcCall(this.jdbcTemplate)
                    .withSchemaName(schemaName)                
                    .withCatalogName("XXGS_MOB_UTIL_PKG")
                    .withProcedureName("GET_USER_AUTH_P");
    simpleJdbcCall.declareParameters(
        new SqlParameter("P_USER_NAME",  Types.VARCHAR),
        new SqlParameter("P_PASSWORD",  Types.VARCHAR)
        // out param
        ,new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        // ,new SqlOutParameter("P_RECORDSET", OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType())
       ).compile();

    SqlParameterSource param=new MapSqlParameterSource()
       .addValue("P_USER_NAME", P_USER_NAME)
       .addValue("P_PASSWORD", P_PASS_WORD);

       long totProcStartTime2 = System.currentTimeMillis();
       Map<String, Object> simpleJdbcCallResult =  simpleJdbcCall.execute(param);
       long totProcEndTime = System.currentTimeMillis();
       System.out.println("total time taken all: " + (totProcEndTime - totProcStartTime) + " ms");
       System.out.println("total time taken exe: " + (totProcEndTime - totProcStartTime2) + " ms");
       return simpleJdbcCallResult;
   }


}




