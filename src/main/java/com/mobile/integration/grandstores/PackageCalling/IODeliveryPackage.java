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
public class IODeliveryPackage {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


  /* Calling Stored Procedure using JdbcTemplate -GET_IO_DEL_SHIPMENT_NO  */
  public Map<String, Object> getIODelShipmentNumber(
                        String P_INVENTORY_ORG_ID,
                        String P_SHIPMENT_NUM,
                        String P_LIKE_COND_FLAG
                        ) {
                            
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_IO_DEL_SHIP_NUM_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_DEL_SHIPMENT_NO(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


    /* Calling Stored Procedure using JdbcTemplate-GET_IO_DEL_SHIP_SRC_ORG */
    public Map<String, Object> getIODelShipmentScrOrg(
        String P_INVENTORY_ORG_ID,
        String P_SHIPMENT_NUM,
        String P_LIKE_COND_FLAG
        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR), 
        new SqlParameter(Types.NVARCHAR), 
        new SqlOutParameter("P_IO_DEL_SHIP_SRC_ORG_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_DEL_SHIP_SRC_ORG(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


    /* Calling Stored Procedure using JdbcTemplate-GET_IO_DEL_ITEM_DTLS */
    public Map<String, Object> getIODelItemDtls(
        String P_INVENTORY_ORG_ID,
        String P_SHIPMENT_NUM,
        String P_WITH_LOT_FLAG,
        String P_WITH_SUBINV_LOC

        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR), 
        new SqlParameter(Types.NVARCHAR),   
        new SqlOutParameter("P_IO_DEL_ITEM_RS", Types.REF_CURSOR),
        new SqlOutParameter("P_IO_DEL_SUBINV_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_DEL_ITEM_DTLS(?,?,?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_WITH_LOT_FLAG);
        cs.setString(4, P_WITH_SUBINV_LOC);
        cs.registerOutParameter(5, Types.REF_CURSOR);
        cs.registerOutParameter(6, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


    /* Calling Stored Procedure using JdbcTemplate-GET_IO_DEL_ITEM_DTLS_CR */
   public Map<String, Object> getIODelItemDtlsCr (
                String P_INVENTORY_ORG_ID,
                String P_SHIPMENT_NUM,
                String P_WITH_LOT_FLAG
            ) {
                
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR), 
            new SqlOutParameter("P_IO_DEL_ITEM_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_IO_DEL_ITEM_DTLS_CR(?,?,?,?)}");
            cs.setString(1, P_INVENTORY_ORG_ID);
            cs.setString(2, P_SHIPMENT_NUM);
            cs.setString(3, P_WITH_LOT_FLAG);
            cs.registerOutParameter(4, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }





}


