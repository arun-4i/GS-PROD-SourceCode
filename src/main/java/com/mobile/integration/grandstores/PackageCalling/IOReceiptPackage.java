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
public class IOReceiptPackage {
 
    @Autowired
    private JdbcTemplate jdbcTemplate;



  /* Calling Stored Procedure using JdbcTemplate  GET_IO_SHIPMENT_NO */
  public Map<String, Object> getIoShipmentNumber(
                        String P_INVENTORY_ORG_ID,
                        String P_SHIPMENT_NUM,
                        String P_LIKE_COND_FLAG
                        ) {
                            
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_IO_SHIP_NUM_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_SHIPMENT_NO(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

  /* Calling Stored Procedure using JdbcTemplate  GET_IO_SHIP_SRC_ORG */
  public Map<String, Object> getIoShipSrc(
                        String P_INVENTORY_ORG_ID,
                        String P_SHIPMENT_NUM,
                        String P_LIKE_COND_FLAG
                        ) {
                            
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_IO_SHIP_SRC_ORG_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_SHIP_SRC_ORG(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


  /* Calling Stored Procedure using JdbcTemplate  GET_IO_RCPT_ITEM_DTLS */
  public Map<String, Object> getIoReceiptItem(
                        String P_INVENTORY_ORG_ID,
                        String P_SHIPMENT_NUM,
                        String P_WITH_LOT_FLAG
                        ) {
                            
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_IO_RCPT_ITEM_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_RCPT_ITEM_DTLS(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_WITH_LOT_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


  /* Calling Stored Procedure using JdbcTemplate  GET_IO_RCPT_ITEM_DTLS_CR */
  public Map<String, Object> getIoReceiptItemDtlCr(
                        String P_INVENTORY_ORG_ID,
                        String P_SHIPMENT_NUM,
                        String P_WITH_LOT_FLAG
                        ) {
                            
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_IO_RCPT_ITEM_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_RCPT_ITEM_DTLS_CR(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_WITH_LOT_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


 /* Calling Stored Procedure using JdbcTemplate  GET_LOCATOR_SPOT_CHK */
  public Map<String, Object> getLocatorSpotChk(
              String P_INVENTORY_ORG_ID
      ) {
      List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlOutParameter("P_LOC_SPOT_CHK_RS", Types.REF_CURSOR)
      );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_LOCATOR_SPOT_CHK(?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
        }
      }, parameters);

    }
    public Map<String, Object> insertSerialForIORecCnfmPkg(
            String p_serial_data
    ) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_SERIAL_RESULT", Types.REF_CURSOR)
      );
//P_SERIAL_RESULT
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_MOBILE_POST_UTIL_PKG.IO_PICK_CONFIRM(?,?)}");
          cs.setString(1, p_serial_data);
          cs.registerOutParameter(2, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> insertIORecCnfmPkg(
            String p_serial_data
    ) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_SERIAL_RESULT", Types.REF_CURSOR)
      );
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_MOBILE_POST_UTIL_PKG.IO_PICK_CONFIRM(?,?)}");
          cs.setString(1, p_serial_data);
          cs.registerOutParameter(2, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

}