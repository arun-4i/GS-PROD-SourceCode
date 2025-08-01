package com.mobile.integration.grandstores.PackageCalling;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Clob;
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
public class MoveOrderPackage {

    @Autowired
    private JdbcTemplate jdbcTemplate;



  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> getInvOrg(BigDecimal p_userId, String P_orgcode,String P_orgname) {
    
    // List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NUMERIC), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_INV_ORGANIZATIONS(?,?,?,?)}");
        cs.setBigDecimal(1, p_userId);
        cs.setString(2, P_orgcode);
        cs.setString(3, P_orgname);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


  /* Calling Stored GET_MO_DETAILS */
  public Map<String, Object> getmodetailPkg(BigDecimal p_ORGANIZATION_ID, String p_MOVE_ORDER_NUM) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NUMERIC), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_MO_DETAILS(?,?,?)}");
        cs.setBigDecimal(1, p_ORGANIZATION_ID);
        cs.setString(2, p_MOVE_ORDER_NUM);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

  /* Calling Stored GET_MO_ITEM_CROSS_REF_DTLS */
  public Map<String, Object> getmoItemCrossRefPkg(BigDecimal p_HEADER_ID) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NUMERIC), 
      new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_MO_ITEM_CROSS_REF_DTLS(?,?)}");
        cs.setBigDecimal(1, p_HEADER_ID);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

  /* Calling Stored Procedure GET_MO_ITEM_LOT_DTLS */
  public Map<String, Object> getmoItemLotDtl(
    BigDecimal p_HEADER_ID, 
    String p_LINE_ID,
    String p_INVENTORY_ITEM_ID) {
    
    // List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NUMERIC), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_MO_ITEM_LOT_DTLS(?,?,?,?)}");
        cs.setBigDecimal(1, p_HEADER_ID);
        cs.setString(2, p_LINE_ID);
        cs.setString(3, p_INVENTORY_ITEM_ID);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


  /* Calling Stored GET_MO_ITEM_DETAILS */
  public Map<String, Object> getmoItemDetailPkg(BigDecimal p_HEADER_ID) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NUMERIC), 
      new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_MO_ITEM_DETAILS(?,?)}");
        cs.setBigDecimal(1, p_HEADER_ID);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


/* Calling Stored Json Form PKG */
public Map<String, Object> getJsonFormPkg(String methodName, String jsonData) {
    
    // Clob clob =jsonData;  
    // Clob(jsonData.toCharArray());
  // (oracle.jdbc.internal.OracleClob) jsonData.toCharArray();   


  List<SqlParameter> parameters = Arrays.asList(
    new SqlParameter(Types.NVARCHAR),   
    new SqlParameter(Types.CLOB), 
    new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
    );

  return jdbcTemplate.call(new CallableStatementCreator() {
    @Override
    public CallableStatement createCallableStatement(Connection con) throws SQLException {

      // Clob myClob = con.createClob();
      // myClob.setString(2, jsonData);
      
      Clob myClob =con.createClob();
      myClob.setString(2, jsonData);

      CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.JSON_POST_METHOD(?,?,?)}");
      cs.setString(1, methodName);
      cs.setClob(2, myClob);
      cs.registerOutParameter(3, Types.REF_CURSOR);
      return cs;
    }
  }, parameters);
}


/* Calling Stored insertQuickPickJson */
public Map<String, Object> insertQuickPickJson(String jsonData) {   
  List<SqlParameter> parameters = Arrays.asList(
    new SqlParameter(Types.NVARCHAR), 
    new SqlOutParameter("P_MES", Types.REF_CURSOR),
    new SqlOutParameter("P_MES2", Types.REF_CURSOR)
  );

return jdbcTemplate.call(new CallableStatementCreator() {
  @Override
  public CallableStatement createCallableStatement(Connection con) throws SQLException {
    CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.MAIN(?,?,?)}");
    cs.setString(1, jsonData);
    cs.registerOutParameter(2, Types.REF_CURSOR);
    cs.registerOutParameter(3, Types.REF_CURSOR);
    return cs;
  }
}, parameters);
}


//   public void moveToHistoryTable(String a) {
//     List<SqlParameter> parameters = Arrays.asList(
//             new SqlParameter(Types.BIGINT), new SqlOutParameter("status_out", Types.BOOLEAN));

//     Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator() {
//         @Override
//         public CallableStatement createCallableStatement(Connection con) throws SQLException {
//             CallableStatement callableStatement = con.prepareCall("{call MOVE_TO_HISTORY (?, ?)}");
//             callableStatement.setLong(1, person.getId());
//             callableStatement.registerOutParameter(2, Types.BOOLEAN);
//             return callableStatement;
//         }
//     }, parameters);
// }


/* Calling Stored Procedure using JdbcTemplate-GET_DEL_INV_DTL */
public Map<String, Object> GET_DEL_INV_DTL(String P_VEHICLE_NO) {
  List<SqlParameter> parameters = Arrays.asList(
  new SqlParameter(Types.NVARCHAR),
  new SqlOutParameter("P_DEL_INV_DTLS_RS", Types.REF_CURSOR)
  );
  return jdbcTemplate.call(new CallableStatementCreator() {
  @Override
  public CallableStatement createCallableStatement(Connection con) throws SQLException {
  CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_DEL_INV_DTL(?,?)}");
  cs.setString(1, P_VEHICLE_NO);
  cs.registerOutParameter(2, Types.REF_CURSOR);
  return cs;
  }
  }, parameters);
}

}