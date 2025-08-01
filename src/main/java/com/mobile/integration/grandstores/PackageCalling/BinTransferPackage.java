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
public class BinTransferPackage {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> GET_ITEM_DETAILS(
                        String P_ORGANIZATION_CODE,
                        String P_DEPARTMENT,
                        String P_SUB_INV_CODE,
                        String P_WITH_LOT_FLAG
        ) {

    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR),  
      new SqlParameter(Types.NVARCHAR),
      new SqlOutParameter("P_ITEM_DTLS_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_ITEM_DETAILS(?,?,?,?,?)}");
        cs.setString(1, P_ORGANIZATION_CODE);
        cs.setString(2, P_DEPARTMENT);
        cs.setString(3, P_SUB_INV_CODE);
        cs.setString(4, P_WITH_LOT_FLAG);
        cs.registerOutParameter(5, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

    /* Calling Stored Procedure using JdbcTemplate */
    public Map<String, Object> GET_ITEM_CROSSREF_DTLS(
                    String P_ORGANIZATION_CODE,
                    String P_DEPARTMENT,
                    String P_SUB_INV_CODE
        ) {

        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),  
        new SqlOutParameter("P_ITEM_DTLS_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_ITEM_CROSSREF_DTLS(?,?,?,?)}");
        cs.setString(1, P_ORGANIZATION_CODE);
        cs.setString(2, P_DEPARTMENT);
        cs.setString(3, P_SUB_INV_CODE);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
}

}