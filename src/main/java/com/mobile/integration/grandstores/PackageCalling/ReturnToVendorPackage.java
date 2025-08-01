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
public class ReturnToVendorPackage {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

  /* Calling Stored Procedure using JdbcTemplate  GET_RTV_RECEIPT_NUM */
  //2411054414 - p_rtv_rqst_num_rs
  public Map<String, Object> getRTVRequestNumber(
                        String P_INVENTORY_ORG_ID,
                        String P_REQUEST_NUM
                       // String P_LIKE_COND_FLAG
                        ) {
                            
    List<SqlParameter> parameters = Arrays.asList(
      new SqlParameter(Types.NVARCHAR),
      new SqlParameter(Types.NVARCHAR), 
      //new SqlParameter(Types.NVARCHAR), 
      new SqlOutParameter("P_RTV_RQST_NUM_RS", Types.REF_CURSOR)
      );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_REQUEST_NUM);
        //cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(3, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

    /* Calling Stored Procedure using JdbcTemplate  GET_RTV_SHIPMENT_NUM */

    public Map<String, Object> getRTVShipmentNumber(
        String P_INVENTORY_ORG_ID,
        String P_SHIPMENT_NUM,
        String P_LIKE_COND_FLAG
        ) {
            
        List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR), 
        new SqlParameter(Types.NVARCHAR), 
        new SqlOutParameter("P_RTV_SHIP_NUM_RS", Types.REF_CURSOR)
        );

        return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_SHIPMENT_NUM(?,?,?,?)}");
        cs.setString(1, P_INVENTORY_ORG_ID);
        cs.setString(2, P_SHIPMENT_NUM);
        cs.setString(3, P_LIKE_COND_FLAG);
        cs.registerOutParameter(4, Types.REF_CURSOR);
        return cs;
        }
        }, parameters);
    }

        /* Calling Stored Procedure using JdbcTemplate  GET_RTV_PO_NUM */

        public Map<String, Object> getRTVPONumber(
            String P_INVENTORY_ORG_ID,
            String P_PO_NUMBER,
            String P_LIKE_COND_FLAG
            ) {
                
            List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR), 
            new SqlParameter(Types.NVARCHAR), 
            new SqlOutParameter("P_RTV_PO_NUM_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_PO_NUM(?,?,?,?)}");
            cs.setString(1, P_INVENTORY_ORG_ID);
            cs.setString(2, P_PO_NUMBER);
            cs.setString(3, P_LIKE_COND_FLAG);
            cs.registerOutParameter(4, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
        }

        
        /* Calling Stored Procedure using JdbcTemplate  GET_RTV_RELEASE_NUM */

        public Map<String, Object> getRTVReleaseNumber(
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
            new SqlOutParameter("P_RTV_REL_NUM_RS", Types.REF_CURSOR)
            );
    
            return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_RELEASE_NUM(?,?,?,?,?)}");
            cs.setString(1, P_INVENTORY_ORG_ID);
            cs.setString(2, P_PO_NUMBER);
            cs.setString(3, P_RELEASE_NUM);
            cs.setString(4, P_LIKE_COND_FLAG);
            cs.registerOutParameter(5, Types.REF_CURSOR);
            return cs;
            }
            }, parameters);
        }


                /* Calling Stored Procedure using JdbcTemplate  GET_RTV_ITEM_DTLS */

                public Map<String, Object> getRTVItemDetail(
                    //String P_INVENTORY_ORG_ID,
                    //String P_RECEIPT_NUM,
                    //String P_SHIPMENT_NUM,
                    //String P_PO_NUMBER,
                    //String P_RELEASE_NUM,
                    //String P_WITH_SUBINV_LOC,
                    //String p_item_id
                    String P_REQUEST_ID
                    ) {
                        
                    List<SqlParameter> parameters = Arrays.asList(
                    new SqlParameter(Types.NVARCHAR),
                    new SqlOutParameter("P_RTV_ITEM_RS", Types.REF_CURSOR)
                    );
            
                    return jdbcTemplate.call(new CallableStatementCreator() {
                    @Override
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                    CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(?,?)}");
                    cs.setString(1, P_REQUEST_ID);
                    cs.registerOutParameter(2, Types.REF_CURSOR);
                    return cs;
                    }
                    }, parameters);
                }
        

                /* Calling Stored Procedure using JdbcTemplate  GET_RTV_ITEM_DTLS_CR */

                public Map<String, Object> getRTVItemDetailCr(
                   /* String P_INVENTORY_ORG_ID,
                    String P_RECEIPT_NUM,
                    String P_SHIPMENT_NUM,
                    String P_PO_NUMBER,
                    String P_RELEASE_NUM */
                    String P_REQUEST_ID
                    ) {
                        
                    List<SqlParameter> parameters = Arrays.asList(
                    new SqlParameter(Types.NVARCHAR), 
                    new SqlOutParameter("P_RTV_ITEM_CR_RS", Types.REF_CURSOR)
                    );
            
                    return jdbcTemplate.call(new CallableStatementCreator() {
                    @Override
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                    CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR(?,?)}");
                    cs.setString(1, P_REQUEST_ID);
                    cs.registerOutParameter(2, Types.REF_CURSOR);
                    return cs;
                    }
                    }, parameters);
                }
        


                  /* Calling Stored Procedure using JdbcTemplate  GET_RTV_RECEIPT_NUM */
  public Map<String, Object> get_rtv_item_code(
    String p_inventory_org_id
    ) {
        
List<SqlParameter> parameters = Arrays.asList(
new SqlParameter(Types.NVARCHAR),
new SqlOutParameter("p_return_itrm_dtls", Types.REF_CURSOR)
);

return jdbcTemplate.call(new CallableStatementCreator() {
@Override
public CallableStatement createCallableStatement(Connection con) throws SQLException {
CallableStatement cs = con.prepareCall("{call XXGS_MOB_UTIL_PKG.get_rtv_item_code(?,?)}");
cs.setString(1, p_inventory_org_id);
cs.registerOutParameter(2, Types.REF_CURSOR);
return cs;
}
}, parameters);
}






}