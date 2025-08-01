package com.mobile.integration.grandstores.PackageCalling;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

@Component
public class BinTranferTrackPkg {
 
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

        /* Calling Stored Procedure using JdbcTemplate-GET_BIN_TRANS_REF_NO */
        public Map<String, Object> GET_BIN_TRANS_REF_NO() {
            List<SqlParameter> parameters = Arrays.asList(
            new SqlOutParameter("P_BIN_TRANS_REF_NO_RS", Types.REF_CURSOR));
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_BIN_TRANS_REF_NO(?)}");
            cs.registerOutParameter(1, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }




}