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
public class RMAReceiptPkg {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /* Calling Stored Procedure using JdbcTemplate-GET_RMA_DETAILS */
    public Map<String, Object> GET_RMA_DETAILS(String P_INVENTORY_ORG_ID) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_ORDER_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_DETAILS(?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }

       /* Calling Stored Procedure using JdbcTemplate-GET_RMA_CUST_DETAILS */
       public Map<String, Object> GET_RMA_CUST_DETAILS(String P_ORDER_NUM) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_CUSTOMER_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_CUST_DETAILS(?,?)}");
        cs.setString(1, P_ORDER_NUM);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }

    /* Calling Stored Procedure using JdbcTemplate-GET_RMA_ITEM_DETAILS */
    public Map<String, Object> GET_RMA_ITEM_DETAILS(String P_INVENTORY_ORG_ID, String P_ORDER_NUM) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_ITEM_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_DETAILS(?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_ORDER_NUM);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


    /* Calling Stored Procedure using JdbcTemplate-GET_RMA_ITEM_CROSS_REF */
    public Map<String, Object> GET_RMA_ITEM_CROSS_REF(String P_INVENTORY_ORG_ID, String P_ORDER_NUM) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_CROSS_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_ITEM_CROSS_REF(?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_ORDER_NUM);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


    /* Calling Stored Procedure using JdbcTemplate-BUNDLE_ITEM */
    
    public Map<String, Object> BUNDLE_ITEM(String P_ORDER_NO) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_BUNDLE_ITEM_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.BUNDLE_ITEM(?,?)}");
        cs.setString(1, P_ORDER_NO);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }




}