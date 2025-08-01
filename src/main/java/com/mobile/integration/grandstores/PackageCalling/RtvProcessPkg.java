package com.mobile.integration.grandstores.PackageCalling;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

@Component
public class RtvProcessPkg {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* Calling Stored RTV_REQUEST_NUM */
  public Map<String, Object> getRtvRequestNumPkg(String p_inventory_org_id, String p_request_num) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_RTV_DTLS_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(?,?,?)}");
        cs.setString(1, p_inventory_org_id);
        cs.setString(2, p_request_num);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


  /* Calling Stored GET_RTV_DTLS */
  public Map<String, Object> getRtvDtls(String p_request_id, String p_inventory_org_id) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR), 
	  new SqlParameter(Types.NVARCHAR),
      new SqlOutParameter("P_RTV_SUMMARY_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_DTLS(?,?,?)}");
        cs.setString(1, p_request_id);
		    cs.setString(2, p_inventory_org_id);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

  /* Calling Stored GET_RTV_ITEM_DTLS */
  public Map<String, Object> getRtvItemDtls(String p_request_id, String p_inventory_org_id) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR), 
	    new SqlParameter(Types.NVARCHAR),
      new SqlOutParameter("P_RTV_ITEM_RS", Types.REF_CURSOR)
    );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(?,?,?)}");
        cs.setString(1, p_request_id);
		    cs.setString(2, p_inventory_org_id);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

  /* Calling Stored UPDATE_RTV_REQ_LINES */
  public Map<String, Object> updateRtvReqLines(String p_request_id, String p_line_id, String p_item_id,
  String p_picked_qty, String p_user_id) {
    
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR), 
	    new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
	    new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR)
    );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_CNT_QTY_UPD(?,?,?,?,?)}");
        cs.setString(1, p_request_id);
		    cs.setString(2, p_line_id);
        cs.setString(3, p_item_id);
        cs.setString(4, p_picked_qty);
		    cs.setString(5, p_user_id);
        return cs;
      }
    }, parameters);
  }

}
