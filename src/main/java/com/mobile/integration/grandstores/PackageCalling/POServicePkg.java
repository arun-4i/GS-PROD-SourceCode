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

public class POServicePkg {
 
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> getPORCPTGen (
            String P_PO_NUMBER,
            String P_RELEASE_NUM) {


          List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR), 
          new SqlParameter(Types.NVARCHAR), 
          new SqlOutParameter("P_PO_RCPT_GEN_RS", Types.REF_CURSOR)
          );

          return jdbcTemplate.call(new CallableStatementCreator() {
                @Override
                public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PO_FOR_RCPT_GEN(?,?,?)}");
                cs.setString(1, P_PO_NUMBER);
                cs.setString(2, P_RELEASE_NUM);
                cs.registerOutParameter(3, Types.REF_CURSOR);
                return cs;
                }
              }, parameters);
  }

  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> validateLocPkgCall (
            String P_SUBINV,
            String P_LOC) {


          List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR), 
          new SqlParameter(Types.NVARCHAR), 
          new SqlOutParameter("P_LOC_RESULT", Types.REF_CURSOR)
          );

          return jdbcTemplate.call(new CallableStatementCreator() {
                @Override
                public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.VALIDATE_LOC(?,?,?)}");
                cs.setString(1, P_SUBINV);
                cs.setString(2, P_LOC);
                cs.registerOutParameter(3, Types.REF_CURSOR);
                return cs;
                }
              }, parameters);
  }



}