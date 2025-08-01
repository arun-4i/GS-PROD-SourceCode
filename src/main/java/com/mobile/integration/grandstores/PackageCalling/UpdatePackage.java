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
public class UpdatePackage {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> updatePassword(
                        String P_USER_ID,
                        String P_PASSWORD
        ) {
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("ERRCODE", Types.NVARCHAR),
      new SqlOutParameter("ERRMSG", Types.NVARCHAR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.PASSWORD_RESET(?,?,?,?)}");
        cs.setString(1, P_USER_ID);
        cs.setString(2, P_PASSWORD);
        cs.registerOutParameter(3, Types.NVARCHAR);
        cs.registerOutParameter(4, Types.NVARCHAR);
        return cs;
      }
    }, parameters);
  }

    /* Calling Stored Procedure using JdbcTemplate */
    public Map<String, Object> GET_ITEM_DEPARTMENT(
      String P_PRODUCT_LINE_CODE
        ) {
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR), 
        new SqlOutParameter("P_ITEM_DEPT_RECORDSET", Types.NVARCHAR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_ITEM_DEPARTMENT(?,?)}");
        cs.setString(1, P_PRODUCT_LINE_CODE);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
        }

}