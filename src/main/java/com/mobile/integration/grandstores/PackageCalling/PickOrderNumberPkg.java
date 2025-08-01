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
public class PickOrderNumberPkg {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

        /* Calling Stored Procedure using JdbcTemplate-UPDATE_DELIVERY_DETAILS */
        public Map<String, Object> UPDATE_DELIVERY_DETAILS(
            String P_DEL_LINE_ID
            ,String P_ACTUAL_DATE
            ,String P_ACTUAL_TIME
            ,String P_STATUS
            ,String P_REJ_CODE
            ,String P_REJ_MARKS
            ) {
            
            List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.UPDATE_DELIVERY_DETAILS(?,?,?,?,?,?)}");
                cs.setString(1, P_DEL_LINE_ID);
                cs.setString(2, P_ACTUAL_DATE);
                cs.setString(3, P_ACTUAL_TIME);
                cs.setString(4, P_STATUS);
                cs.setString(5, P_REJ_CODE);
                cs.setString(6, P_REJ_MARKS);
            return cs;
            }
            }, parameters);
       }

// GET_PICK_ORDER_NUM

        /* Calling Stored Procedure using JdbcTemplate-GET_PICK_ORDER_NUM */
        public Map<String, Object> GET_PICK_ORDER_NUM(
            String  P_INVENTORY_ORG_ID
            ,String P_RESOURCE_ID
            ) {
            
            List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_PICK_ORDER_DTLS_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PICK_ORDER_NUM(?,?,?)}");
                cs.setString(1, P_INVENTORY_ORG_ID);
                cs.setString(2, P_RESOURCE_ID);
                cs.registerOutParameter(3, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }

// GET_PICK_MOVE_ORDER

        /* Calling Stored Procedure using JdbcTemplate-GET_PICK_MOVE_ORDER */
        public Map<String, Object> GET_PICK_MOVE_ORDER(
            String  P_INVENTORY_ORG_ID
            ,String P_RESOURCE_ID
            ) {
            
            List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_PICK_MO_DTLS_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PICK_MOVE_ORDER(?,?,?)}");
                cs.setString(1, P_INVENTORY_ORG_ID);
                cs.setString(2, P_RESOURCE_ID);
                cs.registerOutParameter(3, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }

// GET_PICK_PICKSLIP_NUM

        /* Calling Stored Procedure using JdbcTemplate-GET_PICK_PICKSLIP_NUM */
        
        public Map<String, Object> GET_PICK_PICKSLIP_NUM(
            String  P_INVENTORY_ORG_ID
            ,String P_RESOURCE_ID
            ) {
            
            List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_PICK_PICKSLIP_DTLS_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PICK_PICKSLIP_NUM(?,?,?)}");
                cs.setString(1, P_INVENTORY_ORG_ID);
                cs.setString(2, P_RESOURCE_ID);
                cs.registerOutParameter(3, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


// GET_PICK_ORDER_DETAILS

        /* Calling Stored Procedure using JdbcTemplate-GET_PICK_ORDER_DETAILS */
        
        public Map<String, Object> GET_PICK_ORDER_DETAILS(
            String  P_INVENTORY_ORG_ID
            ,String P_ORDER_NUM
            ,String P_MO_NUM
            ,String P_PICKSLIP_NUM
            ,String P_RESOURCE_ID
            ) {
            
            List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_PICK_ORDERS_DTLS_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PICK_ORDER_DETAILS(?,?,?,?,?,?)}");
                cs.setString(1, P_INVENTORY_ORG_ID);
                cs.setString(2, P_ORDER_NUM);
                cs.setString(3, P_MO_NUM);
                cs.setString(4, P_PICKSLIP_NUM);
                cs.setString(5, P_RESOURCE_ID);
                cs.registerOutParameter(6, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


// GET_PICK_DETAIL_CR

        /* Calling Stored Procedure using JdbcTemplate-GET_PICK_DETAIL_CR */
        
        public Map<String, Object> GET_PICK_DETAIL_CR(
            String  P_INVENTORY_ORG_ID
            ,String P_ORDER_NUM
            ,String P_MO_NUM
            ,String P_PICKSLIP_NUM
            ,String P_RESOURCE_ID
            ) {
            
            List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_PICK_CROSS_REF_DTLS_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PICK_DETAIL_CR(?,?,?,?,?,?)}");
                cs.setString(1, P_INVENTORY_ORG_ID);
                cs.setString(2, P_ORDER_NUM);
                cs.setString(3, P_MO_NUM);
                cs.setString(4, P_PICKSLIP_NUM);
                cs.setString(5, P_RESOURCE_ID);
                cs.registerOutParameter(6, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


}