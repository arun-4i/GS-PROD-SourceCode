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
public class BinTransferPkg {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


        /* Calling Stored Procedure using JdbcTemplate-GET_RMA_DETAILS */
        public Map<String, Object> GET_BIN_TRNS_DROP_HDR(String P_INV_ORG, String P_SUB_INV) {
            
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_BIN_TRNS_HDR_DTLS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_BIN_TRNS_DROP_HDR(?,?,?)}");
            cs.setString(1, P_INV_ORG);
            cs.setString(2, P_SUB_INV);
            cs.registerOutParameter(3, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


            /* Calling Stored Procedure using JdbcTemplate-GET_BIN_TRNS_DROP_DTLS */
            public Map<String, Object> GET_BIN_TRNS_DROP_DTLS(String p_inv_org_id, String p_sub_code) {
            
                List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_BIN_DROP_DTLS", Types.REF_CURSOR)
                );
        
                return jdbcTemplate.call(new CallableStatementCreator() {
                @Override
                public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_BIN_TRNS_DROP_DTLS(?,?,?)}");
                cs.setString(1, p_inv_org_id);
                cs.setString(2, p_sub_code);
                cs.registerOutParameter(3, Types.REF_CURSOR);
                return cs;
                }
                }, parameters);
           }

                /* Calling Stored Procedure using JdbcTemplate-getBinNumber */
                public Map<String, Object> getBinNumber(String p_inv_org, String p_sub_inv) {
            
                        List<SqlParameter> parameters = Arrays.asList(
                        new SqlParameter(Types.NVARCHAR),
                        new SqlParameter(Types.NVARCHAR),
                        new SqlOutParameter("p_bin_dtls_rs", Types.REF_CURSOR)
                        );
                
                        return jdbcTemplate.call(new CallableStatementCreator() {
                        @Override
                        public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.get_bin_number(?,?,?)}");
                        cs.setString(1, p_inv_org);
                        cs.setString(2, p_sub_inv);
                        cs.registerOutParameter(3, Types.REF_CURSOR);
                        return cs;
                        }
                        }, parameters);
                }



}