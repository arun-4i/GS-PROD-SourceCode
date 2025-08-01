package com.mobile.integration.grandstores.PackageCalling;

import java.util.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

@Component
public class SpotCheckDirectSyncPkg {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* Calling Stored SPOT_CHECK_DIRECT_SYNC */
  public Map<String, Object> getSpotCheckDirectSyncPkg(String p_organization_id, String p_barcode,String p_bin) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
	  new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("X_RETURN_FLAG", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.SPOT_CHECK_DIRECT_SYNC(?,?,?,?)}");
        cs.setString(1, p_organization_id);
        cs.setString(2, p_barcode);
		cs.setString(3, p_bin);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

}
