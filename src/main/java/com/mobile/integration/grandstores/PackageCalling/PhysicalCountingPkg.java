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
public class PhysicalCountingPkg {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

        /* Calling Stored Procedure using JdbcTemplate-GET_PHYSICAL_INVENTORIES */
        public Map<String, Object> GET_PHYSICAL_INVENTORIES(String P_INVENTORY_ORG_ID) {
            
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_PHYSICAL_INV_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PHYSICAL_INVENTORIES(?,?)}");
            cs.setString(1, P_INVENTORY_ORG_ID);
            cs.registerOutParameter(2, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


               /* Calling Stored Procedure using JdbcTemplate-GET_PHY_INV_SUBINV_DTLS */
               public Map<String, Object> GET_PHY_INV_SUBINV_DTLS(String P_INVENTORY_ORG_ID, String P_PHYSICAL_INVENTORY_ID) {
            
                List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_PHY_INV_SUBINV_RS", Types.REF_CURSOR)
                );
        
                return jdbcTemplate.call(new CallableStatementCreator() {
                @Override
                public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PHY_INV_SUBINV_DTLS(?,?,?)}");
                cs.setString(1, P_INVENTORY_ORG_ID);
                cs.setString(2, P_PHYSICAL_INVENTORY_ID);
                cs.registerOutParameter(3, Types.REF_CURSOR);
                return cs;
                }
                }, parameters);
           }

            /* Calling Stored Procedure using JdbcTemplate-GET_PHY_INV_QUERY_DTLS */
            public Map<String, Object> GET_PHY_INV_QUERY_DTLS(String P_INVENTORY_ORG_ID, String P_PHYSICAL_INVENTORY_ID) {
            
                        List<SqlParameter> parameters = Arrays.asList(
                        new SqlParameter(Types.NVARCHAR),
                        new SqlParameter(Types.NVARCHAR),
                        new SqlOutParameter("P_PHY_INV_QUERY_RS", Types.REF_CURSOR)
                        );
                
                        return jdbcTemplate.call(new CallableStatementCreator() {
                        @Override
                        public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PHY_INV_SUBINV_DTLS(?,?,?)}");
                        cs.setString(1, P_INVENTORY_ORG_ID);
                        cs.setString(2, P_PHYSICAL_INVENTORY_ID);
                        cs.registerOutParameter(3, Types.REF_CURSOR);
                        return cs;
                        }
                        }, parameters);
            }      

    /* Calling Stored Procedure using JdbcTemplate-GET_PHYINV_CNT_ITEM_DTLS */
    public Map<String, Object> GET_PHYINV_CNT_ITEM_DTLS(
                            String P_INVENTORY_ORG_ID, 
                            String P_PHYSICAL_INVENTORY_ID,
                            String P_WITH_LOT_DTLS_FLAG,
                            String P_WITH_SERIAL_DTLS_FLAG) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_PHYINV_CNT_ITEM_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PHYINV_CNT_ITEM_DTLS(?,?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_PHYSICAL_INVENTORY_ID);
        cs.setString(3, P_WITH_LOT_DTLS_FLAG);
        cs.setString(4, P_WITH_SERIAL_DTLS_FLAG);
        cs.registerOutParameter(5, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
    
    }


        /* Calling Stored Procedure using JdbcTemplate-GET_PHYINV_CNT_ITEM_CR */
        public Map<String, Object> GET_PHYINV_CNT_ITEM_CR(
            String P_INVENTORY_ORG_ID, 
            String P_PHYSICAL_INVENTORY_ID) {

            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_PHYINV_CNT_ITEM_RS", Types.REF_CURSOR)
            );

            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PHYINV_CNT_ITEM_CR(?,?,?)}");
            cs.setString(1, P_INVENTORY_ORG_ID);
            cs.setString(2, P_PHYSICAL_INVENTORY_ID);
            cs.registerOutParameter(3, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);

    }










}