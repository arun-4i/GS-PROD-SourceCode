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
public class GsLocationPackage {

    @Autowired
    private JdbcTemplate jdbcTemplate;


  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> getItemCodeList(
                        String p_org_id,
                        String p_sub_inv_code
        ) {
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_ITEM_DTLS_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_ITEM_CODE_DETAILS(?,?,?)}");
        cs.setString(1, p_org_id);
        cs.setString(2, p_sub_inv_code);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

}
