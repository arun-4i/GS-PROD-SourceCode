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
public class RMADeliveryPkg {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /* Calling Stored Procedure using JdbcTemplate-GET_RMA_DEL_RECEIPT_NUM */
    public Map<String, Object> GET_RMA_DEL_RECEIPT_NUM(String P_INVENTORY_ORG_ID) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RMA_RECEIPT_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_DEL_RECEIPT_NUM(?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }

    /* Calling Stored Procedure using JdbcTemplate-GET_RMA_DEL_ORDER_NUM */
    public Map<String, Object> GET_RMA_DEL_ORDER_NUM(String P_INVENTORY_ORG_ID) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RMA_ORDER_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ORDER_NUM(?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


    /* Calling Stored Procedure using JdbcTemplate-GET_RMA_DEL_ITEM_DTLS */
    public Map<String, Object> GET_RMA_DEL_ITEM_DTLS(
        String P_INVENTORY_ORG_ID, 
        String P_ORDER_NUMBER,
        String P_RECEIPT_NUMBER,
        String P_WITH_SUBINV_LOC
        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RMA_DEL_ITEM_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_DTLS(?,?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_ORDER_NUMBER);
        cs.setString(3, P_RECEIPT_NUMBER);
        cs.setString(4, P_WITH_SUBINV_LOC);
        cs.registerOutParameter(5, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


       /* Calling Stored Procedure using JdbcTemplate-GET_RMA_DEL_ITEM_CROSS */
       public Map<String, Object> GET_RMA_DEL_ITEM_CROSS(
        String P_INVENTORY_ORG_ID, 
        String P_ORDER_NUMBER,
        String P_RECEIPT_NUMBER
        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RMA_DEL_CROSS_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RMA_DEL_ITEM_CROSS(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_ORDER_NUMBER);
        cs.setString(3, P_RECEIPT_NUMBER);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }

}