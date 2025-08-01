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
public class UpdateDescriptionPkg {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> getItemDetailPackage(
                        String p_INVENTORY_ITEM_ID,
                        String p_ITEM_CODE,
                        String p_ITEM_TYPE,
                        String p_INV_ITEM_STATUS,
                        String p_BUSINESS_UNIT_CODE,
                        String p_PRODUCT_TYPE_CODE,
                        String p_BRAND_CODE,
                        String p_CATEGORY_CODE
        ) {
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR),  
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR),  
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_ITEM_RECORDSET", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_ITEM_DETAILS(?,?,?,?,?,?,?,?,?)}");
        cs.setString(1, p_INVENTORY_ITEM_ID);
        cs.setString(2, p_ITEM_CODE);
        cs.setString(3, p_ITEM_TYPE);
        cs.setString(4, p_INV_ITEM_STATUS);
        cs.setString(5, p_BUSINESS_UNIT_CODE);
        cs.setString(6, p_PRODUCT_TYPE_CODE);
        cs.setString(7, p_BRAND_CODE);
        cs.setString(8, p_CATEGORY_CODE);
        cs.registerOutParameter(9, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> getItemCrossRefPackage(
                        String p_INVENTORY_ITEM_ID,
                        String p_ITEM_CODE,
                        String p_ITEM_TYPE,
                        String p_INV_ITEM_STATUS,
                        String p_BUSINESS_UNIT_CODE,
                        String p_PRODUCT_TYPE_CODE,
                        String p_BRAND_CODE,
                        String p_CATEGORY_CODE
        ) {
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR),  
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR),  
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_ITEM_CR_RECORDSET", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_ITEM_CROSS_REF(?,?,?,?,?,?,?,?,?)}");
        cs.setString(1, p_INVENTORY_ITEM_ID);
        cs.setString(2, p_ITEM_CODE);
        cs.setString(3, p_ITEM_TYPE);
        cs.setString(4, p_INV_ITEM_STATUS);
        cs.setString(5, p_BUSINESS_UNIT_CODE);
        cs.setString(6, p_PRODUCT_TYPE_CODE);
        cs.setString(7, p_BRAND_CODE);
        cs.setString(8, p_CATEGORY_CODE);
        cs.registerOutParameter(9, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }



}