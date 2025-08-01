package com.mobile.integration.grandstores.Showroom.ShowroomService;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogEO;
import com.mobile.integration.grandstores.Logs.mobTransLog.MobTransLogSO;
import com.mobile.integration.grandstores.PackageCalling.Showroompkg;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

@Service
public class ShowroomSO extends NamedParameterJdbcDaoSupport{

    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }

    @Autowired
    private Showroompkg showroompkg;

    @Autowired
    private MobTransLogSO mobTransLogSO;

    MobTransLogEO mobTransLog = null;
    public ResponseEntity<APIResponse> getInvOrg(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getInvOrg");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        
        
        String P_USER_ID=content.get("P_USER_ID")==null?"":content.get("P_USER_ID").toString();
        String P_ORGANIZATION_CODE=content.get("P_ORGANIZATION_CODE")==null?"":content.get("P_ORGANIZATION_CODE").toString();
        String P_ORGANIZATION_NAME=content.get("P_ORGANIZATION_NAME")==null?"":content.get("P_ORGANIZATION_NAME").toString();
        
        Map<String, Object> ls=showroompkg.GET_INV_ORG(P_USER_ID, P_ORGANIZATION_CODE, P_ORGANIZATION_NAME);

        Clob clobResponse = new SerialClob(new ObjectMapper().writeValueAsString(ls).toCharArray());
        mobTransLog.setResponse(clobResponse);
        mobTransLogSO.saveMobTransLog(mobTransLog); 

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getSaleOrderNum(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
                //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getSaleOrderNum");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();
        
        Map<String, Object> ls=showroompkg.GET_SALE_ORDER_NUM(P_INVENTORY_ORG_ID, P_RESOURCE_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getSaleOrderDetails(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getSaleOrderDetails");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_ORDER_NUM=content.get("P_ORDER_NUM")==null?"":content.get("P_ORDER_NUM").toString();
        String P_MO_NUM=content.get("P_MO_NUM")==null?"":content.get("P_MO_NUM").toString();
        String P_PICKSLIP_NUM=content.get("P_PICKSLIP_NUM")==null?"":content.get("P_PICKSLIP_NUM").toString();
        String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();
        
        Map<String, Object> ls=showroompkg.GET_SALE_ORDER_DETAILS(P_INVENTORY_ORG_ID, P_ORDER_NUM, P_MO_NUM, P_PICKSLIP_NUM, P_RESOURCE_ID);

        
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getSaleOrderDetailsCr(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getSaleOrderDetailsCr");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_ORDER_NUM=content.get("P_ORDER_NUM")==null?"":content.get("P_ORDER_NUM").toString();
        String P_MO_NUM=content.get("P_MO_NUM")==null?"":content.get("P_MO_NUM").toString();
        String P_PICKSLIP_NUM=content.get("P_PICKSLIP_NUM")==null?"":content.get("P_PICKSLIP_NUM").toString();
        String P_RESOURCE_ID=content.get("P_RESOURCE_ID")==null?"":content.get("P_RESOURCE_ID").toString();
        
        Map<String, Object> ls=showroompkg.GET_SALE_ORDER_DETAILS_CR(P_INVENTORY_ORG_ID, P_ORDER_NUM, P_MO_NUM, P_PICKSLIP_NUM, P_RESOURCE_ID);

        
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getMoDetails(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
                //logging
                mobTransLog = new MobTransLogEO();   
                mobTransLog.setModuleName("Showroom - getMoDetails");
                Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
                mobTransLog.setRequest(clobRequest);
                mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
                mobTransLogSO.saveMobTransLog(mobTransLog); 
            }catch(Exception e){
                logger.info("issue in inserting the mobile request payload: "+e.getMessage());
            }
        String P_ORGANIZATION_ID=content.get("P_ORGANIZATION_ID")==null?"":content.get("P_ORGANIZATION_ID").toString();
        String P_MOVE_ORDER_NUM=content.get("P_MOVE_ORDER_NUM")==null?"":content.get("P_MOVE_ORDER_NUM").toString();
        String P_DELIVERY_NUM=content.get("P_DELIVERY_NUM")==null?"":content.get("P_DELIVERY_NUM").toString();
        
        Map<String, Object> ls=showroompkg.GET_MO_DETAILS(P_ORGANIZATION_ID, P_MOVE_ORDER_NUM, P_DELIVERY_NUM);

        
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getMoItemDetails(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getMoItemDetails");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_HEADER_ID=content.get("P_HEADER_ID")==null?"":content.get("P_HEADER_ID").toString();
    
        Map<String, Object> ls=showroompkg.GET_MO_ITEM_DETAILS(P_HEADER_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getMoItemCrossRefDtls(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getMoItemCrossRefDtls");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_HEADER_ID=content.get("P_HEADER_ID")==null?"":content.get("P_HEADER_ID").toString();
    
        Map<String, Object> ls=showroompkg.GET_MO_ITEM_CROSS_REF_DTLS(P_HEADER_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getPoNumber(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPoNumber");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch (Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
    
        Map<String, Object> ls=showroompkg.GET_PO_NUMBER(P_INVENTORY_ORG_ID, P_PO_NUMBER);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
       
    }

    public ResponseEntity<APIResponse> getReleaseNumber(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException{
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getReleaseNumber");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        String P_PO_HEADER_ID=content.get("P_PO_HEADER_ID")==null?"":content.get("P_PO_HEADER_ID").toString();
    
        Map<String, Object> ls=showroompkg.GET_RELEASE_NUM(P_PO_HEADER_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
        
    }

    public ResponseEntity<APIResponse> getPoItemDtls(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException{
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPoItemDtls");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        
        String P_PO_HEADER_ID=content.get("P_PO_HEADER_ID")==null?"":content.get("P_PO_HEADER_ID").toString();
        String P_PO_RELEASE_ID=content.get("P_PO_RELEASE_ID")==null?"":content.get("P_PO_RELEASE_ID").toString();
    
        Map<String, Object> ls=showroompkg.GET_PO_ITEM_DTLS(P_PO_HEADER_ID, P_PO_RELEASE_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
        
    }

    public ResponseEntity<APIResponse> getPoItemCrossRef(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException{
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPoItemCrossRef");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_PO_HEADER_ID=content.get("P_PO_HEADER_ID")==null?"":content.get("P_PO_HEADER_ID").toString();
        String P_PO_RELEASE_ID=content.get("P_PO_RELEASE_ID")==null?"":content.get("P_PO_RELEASE_ID").toString();
    
        Map<String, Object> ls=showroompkg.GET_PO_ITEM_CROSS_REF(P_PO_HEADER_ID, P_PO_RELEASE_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
        
    }

    public ResponseEntity<APIResponse> getRTVPoNum(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getRTVPoNum");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
        String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
        
        Map<String, Object> ls=showroompkg.GET_RTV_PO_NUM(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }


    public ResponseEntity<APIResponse> getRTVRequestNum(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getRTVRequestNum");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
        String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
        String P_ITEM_CODE=content.get("P_ITEM_CODE")==null?"":content.get("P_ITEM_CODE").toString();

        Map<String, Object> ls=showroompkg.GET_RTV_REQUEST_NUM(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM, P_ITEM_CODE);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getRTVItemDtls(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
                //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getRTVItemDtls");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
        String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
        String P_ITEM_CODE=content.get("P_ITEM_CODE")==null?"":content.get("P_ITEM_CODE").toString();

        Map<String, Object> ls=showroompkg.GET_RTV_ITEM_DTLS(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM, P_ITEM_CODE);

        //String P_REQUEST_ID=content.get("P_REQUEST_ID")==null?"":content.get("P_REQUEST_ID").toString();
        //Map<String, Object> ls=showroompkg.GET_RTV_ITEM_DTLS(P_REQUEST_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getRTVItemDtlsCr(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getRTVItemDtlsCr");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PO_NUMBER=content.get("P_PO_NUMBER")==null?"":content.get("P_PO_NUMBER").toString();
        String P_RECEIPT_NUM=content.get("P_RECEIPT_NUM")==null?"":content.get("P_RECEIPT_NUM").toString();
        String P_ITEM_CODE=content.get("P_ITEM_CODE")==null?"":content.get("P_ITEM_CODE").toString();

        Map<String, Object> ls=showroompkg.GET_RTV_ITEM_DTLS_CR(P_INVENTORY_ORG_ID, P_PO_NUMBER, P_RECEIPT_NUM, P_ITEM_CODE);


        //String P_REQUEST_ID=content.get("P_REQUEST_ID")==null?"":content.get("P_REQUEST_ID").toString();   
        //Map<String, Object> ls=showroompkg.GET_RTV_ITEM_DTLS_CR(P_REQUEST_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getPhyInvQueryDtls(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPhyInvQueryDtls");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PHYSICAL_INVENTORY=content.get("P_PHYSICAL_INVENTORY")==null?"":content.get("P_PHYSICAL_INVENTORY").toString();
        String P_SUBINVENTORY=content.get("P_SUBINVENTORY")==null?"":content.get("P_SUBINVENTORY").toString();

        Map<String, Object> ls=showroompkg.GET_PHY_INV_QUERY_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY, P_SUBINVENTORY);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getPhyInvCntItemDtls(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{
            //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPhyInvCntItemDtls");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PHYSICAL_INVENTORY=content.get("P_PHYSICAL_INVENTORY")==null?"":content.get("P_PHYSICAL_INVENTORY").toString();
        String P_SUBINVENTORY=content.get("P_SUBINVENTORY")==null?"":content.get("P_SUBINVENTORY").toString();

        Map<String, Object> ls=showroompkg.GET_PHYINV_CNT_ITEM_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY, P_SUBINVENTORY);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getPhyInvCntItemCr(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{ //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPhyInvCntItemCr");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PHYSICAL_INVENTORY=content.get("P_PHYSICAL_INVENTORY")==null?"":content.get("P_PHYSICAL_INVENTORY").toString();
        
        Map<String, Object> ls=showroompkg.GET_PHYINV_CNT_ITEM_CR(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api); 
    }

    public ResponseEntity<APIResponse> getPhysicalInventories(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPhysicalInventories");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        
        Map<String, Object> ls=showroompkg.GET_PHYSICAL_INVENTORIES(P_INVENTORY_ORG_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }




    public ResponseEntity<APIResponse> getPhyInvSubInvDtls(Map<String, Object> content) throws JsonProcessingException, SerialException, SQLException {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getPhyInvSubInvDtls");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_PHYSICAL_INVENTORY_ID=content.get("P_PHYSICAL_INVENTORY_ID")==null?"":content.get("P_PHYSICAL_INVENTORY_ID").toString();
        
        Map<String, Object> ls=showroompkg.GET_PHY_INV_SUBINV_DTLS(P_INVENTORY_ORG_ID, P_PHYSICAL_INVENTORY_ID);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getIoShipmentNo(Map<String, Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getIoShipmentNo");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
        String P_DELIVERY_NUM=content.get("P_DELIVERY_NUM")==null?"":content.get("P_DELIVERY_NUM").toString();
        
        Map<String, Object> ls=showroompkg.GET_IO_SHIPMENT_NO(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM, P_DELIVERY_NUM);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
        
    }

    public ResponseEntity<APIResponse> getIoRcptItemDtls(Map<String, Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getIoRcptItemDtls");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
        
        Map<String, Object> ls=showroompkg.GET_IO_RCPT_ITEM_DTLS(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> getIoRcptItemDtlsCr(Map<String, Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - getIoRcptItemDtlsCr");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }
        String P_INVENTORY_ORG_ID=content.get("P_INVENTORY_ORG_ID")==null?"":content.get("P_INVENTORY_ORG_ID").toString();
        String P_SHIPMENT_NUM=content.get("P_SHIPMENT_NUM")==null?"":content.get("P_SHIPMENT_NUM").toString();
       
        Map<String, Object> ls = showroompkg.GET_IO_RCPT_ITEM_DTLS_CR(P_INVENTORY_ORG_ID, P_SHIPMENT_NUM);

        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> moConfirm(Map<String, Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - moConfirm");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        /*String P_INPUT=content.get("P_INPUT")==null?"":content.get("P_INPUT").toString();
        System.out.println("P_INPUT ===> "+P_INPUT);*/
        ObjectMapper mapper = new ObjectMapper();
        String P_INPUT = "";

        if (content.get("P_INPUT") != null) {
            try {
                // Proper JSON serialization
                P_INPUT = mapper.writeValueAsString(content.get("P_INPUT"));
                System.out.println("P_INPUT ===> " + P_INPUT);
            } catch (Exception e) {
                System.err.println("Failed to convert P_INPUT to JSON: " + e.getMessage());
            }
        } else {
            System.out.println("P_INPUT is null");
        }
        
        Map<String, Object> ls=showroompkg.MO_CONFIRM(P_INPUT);
        
        APIResponse api=new APIResponse();
        if (ls.containsKey("P_MO_RESULT")) {
            Object resultObj = ls.get("P_MO_RESULT");

            if (resultObj instanceof List) {
                List<?> resultList = (List<?>) resultObj;

                if (!resultList.isEmpty() && resultList.get(0) instanceof Map) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

                    Object statusCodeObj = resultMap.get("STATUS_CODE");
                    Object messageObj = resultMap.get("MESSAGE");

                    int statusCode = 0;
                    if (statusCodeObj instanceof BigDecimal) {
                        statusCode = ((BigDecimal) statusCodeObj).intValue();
                    }

                    String message = messageObj != null ? messageObj.toString() : "";
                    api.setStatus(statusCode); 
                    api.setError(message);
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Message: " + message);
                }
            }
        
        }


        //api.setData(ls);
        //api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
        
    }

    public ResponseEntity<APIResponse> stockConfirm(Map<String,Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - stockConfirm");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        /*String P_INPUT=content.get("P_INPUT")==null?"":content.get("P_INPUT").toString();
        System.out.println("P_INPUT ===> "+P_INPUT);*/
        ObjectMapper mapper = new ObjectMapper();
        String P_INPUT = "";

        if (content.get("P_INPUT") != null) {
            try {
                // Proper JSON serialization
                P_INPUT = mapper.writeValueAsString(content.get("P_INPUT"));
                System.out.println("P_INPUT ===> " + P_INPUT);
            } catch (Exception e) {
                System.err.println("Failed to convert P_INPUT to JSON: " + e.getMessage());
            }
        } else {
            System.out.println("P_INPUT is null");
        }
        
        Map<String, Object> ls=showroompkg.STOCK_CONFIRM(P_INPUT);
        
        APIResponse api=new APIResponse();
        if (ls.containsKey("P_STOCK_RESULT")) {
            Object resultObj = ls.get("P_STOCK_RESULT");

            if (resultObj instanceof List) {
                List<?> resultList = (List<?>) resultObj;

                if (!resultList.isEmpty() && resultList.get(0) instanceof Map) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

                    Object statusCodeObj = resultMap.get("STATUS_CODE");
                    Object messageObj = resultMap.get("MESSAGE");

                    int statusCode = 0;
                    if (statusCodeObj instanceof BigDecimal) {
                        statusCode = ((BigDecimal) statusCodeObj).intValue();
                    }

                    String message = messageObj != null ? messageObj.toString() : "";
                    api.setStatus(statusCode); 
                    api.setError(message);
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Message: " + message);
                }
            }
        
        }


        //api.setData(ls);
        //api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }


    public ResponseEntity<APIResponse> rtvConfirm(Map<String,Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - rtvConfirm");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        /*String P_INPUT=content.get("P_INPUT")==null?"":content.get("P_INPUT").toString();
        System.out.println("P_INPUT ===> "+P_INPUT);*/
        ObjectMapper mapper = new ObjectMapper();
        String P_INPUT = "";

        if (content.get("P_INPUT") != null) {
            try {
                // Proper JSON serialization
                P_INPUT = mapper.writeValueAsString(content.get("P_INPUT"));
                System.out.println("P_INPUT ===> " + P_INPUT);
            } catch (Exception e) {
                System.err.println("Failed to convert P_INPUT to JSON: " + e.getMessage());
            }
        } else {
            System.out.println("P_INPUT is null");
        }
        
        Map<String, Object> ls=showroompkg.RTV_CONFIRM(P_INPUT);
        
        APIResponse api=new APIResponse();
        if (ls.containsKey("P_RTV_RESULT")) {
            Object resultObj = ls.get("P_RTV_RESULT");

            if (resultObj instanceof List) {
                List<?> resultList = (List<?>) resultObj;

                if (!resultList.isEmpty() && resultList.get(0) instanceof Map) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

                    Object statusCodeObj = resultMap.get("STATUS_CODE");
                    Object messageObj = resultMap.get("MESSAGE");

                    int statusCode = 0;
                    if (statusCodeObj instanceof BigDecimal) {
                        statusCode = ((BigDecimal) statusCodeObj).intValue();
                    }

                    String message = messageObj != null ? messageObj.toString() : "";
                    api.setStatus(statusCode); 
                    api.setError(message);
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Message: " + message);
                }
            }
        
        }


        //api.setData(ls);
        //api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> poConfirm(Map<String,Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - poConfirm");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        /*String P_INPUT=content.get("P_INPUT")==null?"":content.get("P_INPUT").toString();
        System.out.println("P_INPUT ===> "+P_INPUT);*/
        ObjectMapper mapper = new ObjectMapper();
        String P_INPUT = "";

        if (content.get("P_INPUT") != null) {
            try {
                // Proper JSON serialization
                P_INPUT = mapper.writeValueAsString(content.get("P_INPUT"));
                System.out.println("P_INPUT ===> " + P_INPUT);
            } catch (Exception e) {
                System.err.println("Failed to convert P_INPUT to JSON: " + e.getMessage());
            }
        } else {
            System.out.println("P_INPUT is null");
        }
        
        Map<String, Object> ls=showroompkg.PO_CONFIRM(P_INPUT);
        
        APIResponse api=new APIResponse();
        if (ls.containsKey("P_PO_RESULT")) {
            Object resultObj = ls.get("P_PO_RESULT");

            if (resultObj instanceof List) {
                List<?> resultList = (List<?>) resultObj;

                if (!resultList.isEmpty() && resultList.get(0) instanceof Map) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

                    Object statusCodeObj = resultMap.get("STATUS_CODE");
                    Object messageObj = resultMap.get("MESSAGE");

                    int statusCode = 0;
                    if (statusCodeObj instanceof BigDecimal) {
                        statusCode = ((BigDecimal) statusCodeObj).intValue();
                    }

                    String message = messageObj != null ? messageObj.toString() : "";
                    api.setStatus(statusCode); 
                    api.setError(message);
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Message: " + message);
                }
            }
        
        }


        //api.setData(ls);
        //api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }

    public ResponseEntity<APIResponse> ioConfirm(Map<String,Object> content) {
        try{    //logging
            mobTransLog = new MobTransLogEO();   
            mobTransLog.setModuleName("Showroom - ioConfirm");
            Clob clobRequest = new SerialClob(new ObjectMapper().writeValueAsString(content).toCharArray());
            mobTransLog.setRequest(clobRequest);
            mobTransLog.setProcessedTime(new java.sql.Date(new Date().getTime()));
            mobTransLogSO.saveMobTransLog(mobTransLog); 
        }catch(Exception e){
            logger.info("issue in inserting the mobile request payload: "+e.getMessage());
        }

        /*String P_INPUT=content.get("P_INPUT")==null?"":content.get("P_INPUT").toString();
        System.out.println("P_INPUT ===> "+P_INPUT);*/
        ObjectMapper mapper = new ObjectMapper();
        String P_INPUT = "";

        if (content.get("P_INPUT") != null) {
            try {
                // Proper JSON serialization
                P_INPUT = mapper.writeValueAsString(content.get("P_INPUT"));
                System.out.println("P_INPUT ===> " + P_INPUT);
            } catch (Exception e) {
                System.err.println("Failed to convert P_INPUT to JSON: " + e.getMessage());
            }
        } else {
            System.out.println("P_INPUT is null");
        }
        
        Map<String, Object> ls=showroompkg.IO_CONFIRM(P_INPUT);
        
        APIResponse api=new APIResponse();
        if (ls.containsKey("P_IO_RESULT")) {
            Object resultObj = ls.get("P_IO_RESULT");

            if (resultObj instanceof List) {
                List<?> resultList = (List<?>) resultObj;

                if (!resultList.isEmpty() && resultList.get(0) instanceof Map) {
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);

                    Object statusCodeObj = resultMap.get("STATUS_CODE");
                    Object messageObj = resultMap.get("MESSAGE");

                    int statusCode = 0;
                    if (statusCodeObj instanceof BigDecimal) {
                        statusCode = ((BigDecimal) statusCodeObj).intValue();
                    }

                    String message = messageObj != null ? messageObj.toString() : "";
                    api.setStatus(statusCode); 
                    api.setError(message);
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Message: " + message);
                }
            }
        
        }


        //api.setData(ls);
        //api.setStatus(HttpStatus.OK.value());    
        return ResponseEntity.ok().body(api);
    }}
