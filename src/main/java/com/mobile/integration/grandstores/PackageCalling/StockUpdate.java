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
public class StockUpdate {
    

    @Autowired
    private JdbcTemplate jdbcTemplate;

        /* Calling Stored Procedure using JdbcTemplate-GET_BIN_STOCK_UPDATE */
        public Map<String, Object> GET_BIN_STOCK_UPDATE(String P_INVENTORY_ORG_ID, String P_SUB_INVENTORY) {
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_LOCATION_DTLS_RS", Types.REF_CURSOR)
            );
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_BIN_STOCK_UPDATE(?,?,?)}");
            cs.setString(1, P_INVENTORY_ORG_ID);
            cs.setString(2, P_SUB_INVENTORY);
            cs.registerOutParameter(3, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


}