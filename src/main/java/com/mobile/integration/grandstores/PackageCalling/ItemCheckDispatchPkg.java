package com.mobile.integration.grandstores.PackageCalling;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

@Component
public class ItemCheckDispatchPkg {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Calling ItemCheckDispatch Pkg
  public Map<String, Object> callItemCheckDispatchPkg(String p_dep_code, BigDecimal p_org_id) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_ITM_CHK", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.ITM_CHK_GET_DISPATCH(?,?,?)}");
        cs.setString(1, p_dep_code);
        cs.setBigDecimal(2, p_org_id);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

}
