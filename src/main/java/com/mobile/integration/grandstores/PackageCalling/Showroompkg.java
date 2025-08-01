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
public class Showroompkg {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* Calling Stored Procedure using JdbcTemplate */

    public Map<String, Object> GET_INV_ORG(String p_USER_ID, String p_ORGANIZATION_CODE, String p_ORGANIZATION_NAME) {
       
        List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
            );
  
      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_INV_ORGANIZATIONS(?,?,?,?)}");
          cs.setString(1, p_USER_ID);
          cs.setString(2, p_ORGANIZATION_CODE);
          cs.setString(3, p_ORGANIZATION_NAME);
          cs.registerOutParameter(4, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_SALE_ORDER_NUM(String p_INVENTORY_ORG_ID, String p_RESOURCE_ID) {
        List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_PICK_ORDER_DTLS_RS", Types.REF_CURSOR)
            );
  
      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_SALE_ORDER_NUM(?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.setString(2, p_RESOURCE_ID);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
        
    }

    public Map<String, Object> GET_SALE_ORDER_DETAILS(String p_INVENTORY_ORG_ID, String p_ORDER_NUM, String p_MO_NUM,
            String p_PICKSLIP_NUM, String p_RESOURCE_ID) {
        List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_PICK_ORDERS_DTLS_RS", Types.REF_CURSOR)
            );
  
      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_SALE_ORDER_DETAILS(?,?,?,?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.setString(2, p_ORDER_NUM);
          cs.setString(3, p_MO_NUM);
          cs.setString(4, p_PICKSLIP_NUM);
          cs.setString(5, p_RESOURCE_ID);
          cs.registerOutParameter(6, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
      
    }

    public Map<String, Object> GET_SALE_ORDER_DETAILS_CR(String p_INVENTORY_ORG_ID, String p_ORDER_NUM, String p_MO_NUM,
            String p_PICKSLIP_NUM, String p_RESOURCE_ID) {
                List<SqlParameter> parameters = Arrays.asList(
                    new SqlParameter(Types.NVARCHAR),
                    new SqlParameter(Types.NVARCHAR),
                    new SqlParameter(Types.NVARCHAR),
                    new SqlParameter(Types.NVARCHAR),
                    new SqlParameter(Types.NVARCHAR),
                    new SqlOutParameter("P_PICK_CROSS_REF_DTLS_RS", Types.REF_CURSOR)
                    );
          
              return jdbcTemplate.call(new CallableStatementCreator() {
                @Override
                public CallableStatement createCallableStatement(Connection con) throws SQLException {
                  CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_SALE_ORDER_DETAIL_CR(?,?,?,?,?,?)}");
                  cs.setString(1, p_INVENTORY_ORG_ID);
                  cs.setString(2, p_ORDER_NUM);
                  cs.setString(3, p_MO_NUM);
                  cs.setString(4, p_PICKSLIP_NUM);
                  cs.setString(5, p_RESOURCE_ID);
                  cs.registerOutParameter(6, Types.REF_CURSOR);
                  return cs;
                }
              }, parameters);
    }

    public Map<String, Object> GET_MO_DETAILS(String p_ORGANIZATION_ID, String p_MOVE_ORDER_NUM,
            String p_DELIVERY_NUM) {
              List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlParameter(Types.NVARCHAR),
                new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
                );
      
          return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
              CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_MO_DETAILS(?,?,?,?)}");
              cs.setString(1, p_ORGANIZATION_ID);
              cs.setString(2, p_MOVE_ORDER_NUM);
              cs.setString(3, p_DELIVERY_NUM);
              cs.registerOutParameter(4, Types.REF_CURSOR);
              return cs;
            }
          }, parameters);
    }

    public Map<String, Object> GET_MO_ITEM_DETAILS(String p_HEADER_ID) {
        List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR),
          new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
          );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_MO_ITEM_DETAILS(?,?)}");
          cs.setString(1, p_HEADER_ID);
          cs.registerOutParameter(2, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_MO_ITEM_CROSS_REF_DTLS(String p_HEADER_ID) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        );

    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_MO_ITEM_CROSS_REF_DTLS(?,?)}");
        cs.setString(1, p_HEADER_ID);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
  }

    public Map<String, Object> GET_PO_ITEM_CROSS_REF(String p_PO_HEADER_ID, String p_PO_RELEASE_ID) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PO_ITEM_CROSS_REF(?,?,?)}");
          cs.setString(1, p_PO_HEADER_ID);
          cs.setString(2, p_PO_RELEASE_ID);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_PO_ITEM_DTLS(String p_PO_HEADER_ID, String p_PO_RELEASE_ID) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PO_ITEM_DTLS(?,?,?)}");
          cs.setString(1, p_PO_HEADER_ID);
          cs.setString(2, p_PO_RELEASE_ID);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_RELEASE_NUM(String p_PO_HEADER_ID) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_RELEASE_NUM(?,?)}");
          cs.setString(1, p_PO_HEADER_ID);
          cs.registerOutParameter(2, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

   
    public Map<String, Object> GET_PO_NUMBER(String p_INVENTORY_ORG_ID, String p_PO_NUMBER) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RECORDSET", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PO_NUMBER(?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.setString(2, p_PO_NUMBER);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    
     public Map<String, Object> GET_RTV_PO_NUM(String p_INVENTORY_ORG_ID, String p_PO_NUMBER,
            String p_RECEIPT_NUM) {
        List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlOutParameter("P_RTV_RQST_NUM_RS", Types.REF_CURSOR)
          );
  
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_RTV_PO_NUM(?,?,?,?)}");
            cs.setString(1, p_INVENTORY_ORG_ID);
            cs.setString(2, p_PO_NUMBER);
            cs.setString(3, p_RECEIPT_NUM);
            cs.registerOutParameter(4, Types.REF_CURSOR);
            return cs;
          }
        }, parameters);
    }

    public Map<String, Object> GET_RTV_REQUEST_NUM(String p_INVENTORY_ORG_ID, String p_PO_NUMBER,
            String p_RECEIPT_NUM, String p_ITEM_CODE) {
        List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),         
          new SqlParameter(Types.NVARCHAR),
          new SqlOutParameter("P_RTV_RQST_NUM_RS", Types.REF_CURSOR)
          );
  
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_RTV_REQUEST_NUM(?,?,?,?,?)}");
            cs.setString(1, p_INVENTORY_ORG_ID);
            cs.setString(2, p_PO_NUMBER);
            cs.setString(3, p_RECEIPT_NUM);
            cs.setString(4, p_ITEM_CODE);
            cs.registerOutParameter(5, Types.REF_CURSOR);
            return cs;
          }
        }, parameters);
    }

    public Map<String, Object> GET_RTV_ITEM_DTLS(String p_INVENTORY_ORG_ID, String p_PO_NUMBER,
            String p_RECEIPT_NUM, String p_ITEM_CODE) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),         
          new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RTV_ITEM_RS", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS(?,?,?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
            cs.setString(2, p_PO_NUMBER);
            cs.setString(3, p_RECEIPT_NUM);
            cs.setString(4, p_ITEM_CODE);
            cs.registerOutParameter(5, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_RTV_ITEM_DTLS_CR(String p_INVENTORY_ORG_ID, String p_PO_NUMBER,
            String p_RECEIPT_NUM, String p_ITEM_CODE) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),         
          new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_RTV_ITEM_CR_RS", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_RTV_ITEM_DTLS_CR(?,?,?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
            cs.setString(2, p_PO_NUMBER);
            cs.setString(3, p_RECEIPT_NUM);
            cs.setString(4, p_ITEM_CODE);
            cs.registerOutParameter(5, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_PHY_INV_QUERY_DTLS(String p_INVENTORY_ORG_ID, String p_PHYSICAL_INVENTORY,
            String p_SUBINVENTORY) {
        List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlOutParameter("P_PHY_INV_QUERY_RS", Types.REF_CURSOR)
          );
  
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PHY_INV_QUERY_DTLS(?,?,?,?)}");
            cs.setString(1, p_INVENTORY_ORG_ID);
            cs.setString(2, p_PHYSICAL_INVENTORY);
            cs.setString(3, p_SUBINVENTORY);
            cs.registerOutParameter(4, Types.REF_CURSOR);
            return cs;
          }
        }, parameters);
    }

    public Map<String, Object> GET_PHYINV_CNT_ITEM_DTLS(String p_INVENTORY_ORG_ID, String p_PHYSICAL_INVENTORY,
            String p_SUBINVENTORY) {
        List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlParameter(Types.NVARCHAR),
          new SqlOutParameter("P_PHYINV_CNT_ITEM_RS", Types.REF_CURSOR)
          );
  
        return jdbcTemplate.call(new CallableStatementCreator() {
          @Override
          public CallableStatement createCallableStatement(Connection con) throws SQLException {
            CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PHYINV_CNT_ITEM_DTLS(?,?,?,?)}");
            cs.setString(1, p_INVENTORY_ORG_ID);
            cs.setString(2, p_PHYSICAL_INVENTORY);
            cs.setString(3, p_SUBINVENTORY);
            cs.registerOutParameter(4, Types.REF_CURSOR);
            return cs;
          }
        }, parameters);
    }

    public Map<String, Object> GET_PHYINV_CNT_ITEM_CR(String p_INVENTORY_ORG_ID, String p_PHYSICAL_INVENTORY) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_PHYINV_CNT_ITEM_RS", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PHYINV_CNT_ITEM_CR(?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.setString(2, p_PHYSICAL_INVENTORY);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_PHYSICAL_INVENTORIES(String p_INVENTORY_ORG_ID) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_PHYSICAL_INV_RS", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PHYSICAL_INVENTORIES(?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.registerOutParameter(2, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_PHY_INV_SUBINV_DTLS(String p_INVENTORY_ORG_ID, String p_PHYSICAL_INVENTORY_ID) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_PHY_INV_SUBINV_RS", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_PHY_INV_SUBINV_DTLS(?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.setString(2, p_PHYSICAL_INVENTORY_ID);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_IO_SHIPMENT_NO(String p_INVENTORY_ORG_ID, String p_SHIPMENT_NUM,
        String p_DELIVERY_NUM) {
          List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlParameter(Types.NVARCHAR),
            new SqlOutParameter("P_IO_SHIP_NUM_RS", Types.REF_CURSOR)
            );
    
          return jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
              CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_IO_SHIPMENT_NO(?,?,?,?)}");
              cs.setString(1, p_INVENTORY_ORG_ID);
              cs.setString(2, p_SHIPMENT_NUM);
              cs.setString(3, p_DELIVERY_NUM);
              cs.registerOutParameter(4, Types.REF_CURSOR);
              return cs;
            }
          }, parameters);
    }

    public Map<String, Object> GET_IO_RCPT_ITEM_DTLS(String p_INVENTORY_ORG_ID, String p_SHIPMENT_NUM) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_IO_RCPT_ITEM_RS", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_IO_RCPT_ITEM_DTLS(?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.setString(2, p_SHIPMENT_NUM);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> GET_IO_RCPT_ITEM_DTLS_CR(String p_INVENTORY_ORG_ID, String p_SHIPMENT_NUM) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR),
        new SqlParameter(Types.NVARCHAR),
        new SqlOutParameter("P_IO_RCPT_ITEM_RS", Types.REF_CURSOR)
        );

      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_UTIL_PKG.GET_IO_RCPT_ITEM_DTLS_CR(?,?,?)}");
          cs.setString(1, p_INVENTORY_ORG_ID);
          cs.setString(2, p_SHIPMENT_NUM);
          cs.registerOutParameter(3, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> MO_CONFIRM(String content) {
        List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR), 
          new SqlOutParameter("P_MO_RESULT", Types.REF_CURSOR)
        );
      
      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_POST_UTIL_PKG.MO_CONFIRM(?,?)}");
          cs.setString(1, content);
          cs.registerOutParameter(2, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> IO_CONFIRM(String p_INPUT) {
      List<SqlParameter> parameters = Arrays.asList(
          new SqlParameter(Types.NVARCHAR), 
          new SqlOutParameter("P_IO_RESULT", Types.REF_CURSOR)
        );
      
      return jdbcTemplate.call(new CallableStatementCreator() {
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
          CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_POST_UTIL_PKG.IO_CONFIRM(?,?)}");
          cs.setString(1, p_INPUT);
          cs.registerOutParameter(2, Types.REF_CURSOR);
          return cs;
        }
      }, parameters);
    }

    public Map<String, Object> STOCK_CONFIRM(String p_INPUT) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR), 
        new SqlOutParameter("P_STOCK_RESULT", Types.REF_CURSOR)
      );
    
    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_POST_UTIL_PKG.STOCK_CONFIRM(?,?)}");
        cs.setString(1, p_INPUT);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
    }

    public Map<String, Object> RTV_CONFIRM(String p_INPUT) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR), 
        new SqlOutParameter("P_RTV_RESULT", Types.REF_CURSOR)
      );
    
    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_POST_UTIL_PKG.RTV_CONFIRM(?,?)}");
        cs.setString(1, p_INPUT);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
    }

    public Map<String, Object> PO_CONFIRM(String p_INPUT) {
      List<SqlParameter> parameters = Arrays.asList(
        new SqlParameter(Types.NVARCHAR), 
        new SqlOutParameter("P_PO_RESULT", Types.REF_CURSOR)
      );
    
    return jdbcTemplate.call(new CallableStatementCreator() {
      @Override
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{call XXGS_SRM_MOB_POST_UTIL_PKG.PO_CONFIRM(?,?)}");
        cs.setString(1, p_INPUT);
        cs.registerOutParameter(2, Types.REF_CURSOR);
        return cs;
      }
    }, parameters);
    }
    
}
