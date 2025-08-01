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
public class PODeliveryPackage {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> poReceiptDetails(
                        String P_INVENTORY_ORG_ID,
                        String P_RECEIPT_NUM,
                        String P_LIKE_COND_FLAG
                        ) {
                            
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_PO_DELIVERY_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RECEIPT_NUM(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_RECEIPT_NUM);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }


    /* Calling Stored Procedure using JdbcTemplate */
    public Map<String, Object> poShipmentDetails(
        String P_INVENTORY_ORG_ID,
        String P_SHIPMENT_NUM,
        String P_LIKE_COND_FLAG
        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR), 
        new SqlParameter(Types.NVARCHAR), 
        new SqlOutParameter("P_PO_DELIVERY_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_SHIPMENT_NUM(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


       /* Calling Stored Procedure using JdbcTemplate */
       public Map<String, Object> poReleaseDetails(
        String P_INVENTORY_ORG_ID,
        String P_PO_NUMBER,
        String P_LIKE_COND_FLAG

        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),  
        new SqlOutParameter("P_PO_DELIVERY_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PO_NUM(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_PO_NUMBER);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
   }


    /* Calling Stored Procedure using JdbcTemplate */
   public Map<String, Object> getPOItemDetails (
                String P_INVENTORY_ORG_ID,
                String P_RECEIPT_NUM,
                String P_SHIPMENT_NUM,
                String P_PO_NUMBER,
                String P_RELEASE_NUM,
                String P_WITH_SUBINV_LOC
            ) {
                
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR), 
            new SqlParameter(Types.NVARCHAR),  
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),    
            new SqlOutParameter("P_PO_DELIVERY_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PO_DELIV_ITEM_DTLS(?,?,?,?,?,?,?)}");
            cs.setString(1, P_INVENTORY_ORG_ID);
            cs.setString(2, P_RECEIPT_NUM);
            cs.setString(3, P_SHIPMENT_NUM);
            cs.setString(4, P_PO_NUMBER);
            cs.setString(5, P_RELEASE_NUM);
            cs.setString(6, P_WITH_SUBINV_LOC);
            cs.registerOutParameter(7, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
       }


    /* Calling Stored Procedure using JdbcTemplate */
    public Map<String, Object> getPOItemCrossRef (
            String P_INVENTORY_ORG_ID,
            String P_RECEIPT_NUM,
            String P_SHIPMENT_NUM,
            String P_PO_NUMBER,
            String P_RELEASE_NUM
        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR), 
        new SqlParameter(Types.NVARCHAR),  
        new SqlParameter(Types.NVARCHAR),  
        new SqlOutParameter("P_PO_DELIVERY_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PO_DELIV_ITEM_CR(?,?,?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_RECEIPT_NUM);
        cs.setString(3, P_SHIPMENT_NUM);
        cs.setString(4, P_PO_NUMBER);
        cs.setString(5, P_RELEASE_NUM);
        cs.registerOutParameter(6, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
        }

   
 /* Calling Stored Procedure using JdbcTemplate */
      public Map<String, Object> getPOReleaseNumber (
        String P_INVENTORY_ORG_ID,
        String P_PO_NUMBER,
        String P_RELEASE_NUM,
        String P_LIKE_COND_FLAG
    ) {
        
    List<SqlParameter> parameters = Arrays.asList(
    new SqlParameter(Types.NVARCHAR), 
    new SqlParameter(Types.NVARCHAR),  
    new SqlParameter(Types.NVARCHAR), 
    new SqlParameter(Types.NVARCHAR),   
    new SqlOutParameter("P_PO_DELIVERY_RS", Types.REF_CURSOR)
    );

    return jdbcTemplate.call(new CallableStatementCreator() {
    @Override
    public CallableStatement createCallableStatement(Connection con) throws SQLException {
    CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PO_RELEASE_NUM(?,?,?,?,?)}");
    cs.setString(1, P_INVENTORY_ORG_ID);
    cs.setString(2, P_PO_NUMBER);
    cs.setString(3, P_RELEASE_NUM);
    cs.setString(4, P_LIKE_COND_FLAG);
    cs.registerOutParameter(5, Types.REF_CURSOR);
    return cs;
    }
    }, parameters);
  }



  /* Calling Stored Procedure using JdbcTemplate */
  public Map<String, Object> getPONumberRCPT (
    String P_INVENTORY_ORG_ID,
    String P_PO_NUMBER,
    String P_LIKE_COND_FLAG
  ) {


  List<SqlParameter> parameters = Arrays.asList(
  new SqlParameter(Types.NVARCHAR), 
  new SqlParameter(Types.NVARCHAR),  
  new SqlParameter(Types.NVARCHAR), 
  new SqlOutParameter("P_PO_DELIVERY_RS", Types.REF_CURSOR)
  );

  return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
      CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_PO_NUM_WITH_RCPT(?,?,?,?)}");
      cs.setString(1, P_INVENTORY_ORG_ID);
      cs.setString(2, P_PO_NUMBER);
      cs.setString(3, P_LIKE_COND_FLAG);
      cs.registerOutParameter(4, Types.REF_CURSOR);
      return cs;
      }
      }, parameters);
  }



}