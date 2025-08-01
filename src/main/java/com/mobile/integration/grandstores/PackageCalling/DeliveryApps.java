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
public class DeliveryApps {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;



        /* Calling Stored Procedure using JdbcTemplate-GET_RMA_DETAILS */
        public Map<String, Object> GET_DEL_VEH_DTL() {
            List<SqlParameter> parameters = Arrays.asList(
            // new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_DEL_VEH_DTLS_RS", Types.REF_CURSOR)
            );
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_DEL_VEH_DTL(?)}");
            // cs.setString(1, P_INVENTORY_ORG_ID);
            cs.registerOutParameter(1, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }

       
        /* Calling Stored Procedure using JdbcTemplate-GET_DEL_INV_DTL */
        public Map<String, Object> GET_DEL_INV_DTL(String P_VEHICLE_NO) {
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_DEL_INV_DTLS_RS", Types.REF_CURSOR)
            );
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_DEL_INV_DTL(?,?)}");
            cs.setString(1, P_VEHICLE_NO);
            cs.registerOutParameter(2, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


       
        /* Calling Stored Procedure using JdbcTemplate-GET_DEL_LINE_DTL */
        public Map<String, Object> GET_DEL_LINE_DTL(String P_VEHICLE_NO) {
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_DEL_LINE_DTLS_RS", Types.REF_CURSOR)
            );
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_DEL_LINE_DTL(?,?)}");
            cs.setString(1, P_VEHICLE_NO);
            cs.registerOutParameter(2, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


       
        /* Calling Stored Procedure using JdbcTemplate-GET_DEL_REJCODE_DTL */
        public Map<String, Object> GET_DEL_REJCODE_DTL() {
            List<SqlParameter> parameters = Arrays.asList(
            // new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_DEL_REJ_DTLS_RS", Types.REF_CURSOR)
            );
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_DEL_REJCODE_DTL(?)}");
                // cs.setString(1, P_INVENTORY_ORG_ID);
                cs.registerOutParameter(1, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


















}