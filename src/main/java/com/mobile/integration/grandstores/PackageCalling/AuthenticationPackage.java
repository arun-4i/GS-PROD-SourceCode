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
public class AuthenticationPackage {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> getAuthPkg(
                        String p_USER_NAME,
                        String p_PASSWORD
                        ) {

    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_GET_USER_AUTH", Types.REF_CURSOR),
      new SqlOutParameter("P_MENU", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_USER_AUTH(?,?,?,?)}");
        cs.setString(1, p_USER_NAME);
        cs.setString(2, p_PASSWORD);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


}