package com.mobile.integration.grandstores.RMAConfirm.RMAConfirmEntity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "XXGS_RMA_CONFIRMATIONS")
public class RMAConfirmEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rma_seq")
    @SequenceGenerator(name = "rma_seq", sequenceName = "XXGS_RMA_ID_S", allocationSize = 1)
    @Column(name = "RMA_ID")
    public BigDecimal rmaId;


    @Column(name = "TRANSACTION_TYPE")
    public String transactionType;
    @Column(name = "PARTY_ID")
    public BigDecimal partyId;
    @Column(name = "CUST_ACCOUNT_ID")
    public BigDecimal custAccountId;
    @Column(name = "BILL_TO_SITE_USE_ID")
    public BigDecimal billToSitesUseId;
    @Column(name = "SHIP_TO_SITE_USE_ID")
    public BigDecimal shipToSitesUseId;
    @Column(name = "INVENTORY_ORG_ID")
    public BigDecimal inventoryOrgId;
    @Column(name = "LINE_NUMBER")
    public BigDecimal lineNumber;
    @Column(name = "ITEM_ID")
    public BigDecimal itemId;
    @Column(name = "UOM_CODE")
    public String uomCode;
    @Column(name = "ORDER_QUANTITY")    
    public BigDecimal orderQuantity;
    @Column(name = "RECEIPT_NUMBER")
    public String receiptNumber;
    @Column(name = "RETURN_QUANTITY")
    public BigDecimal returnQuantity;
    @Column(name = "DELIVERED_QUANTITY")
    public BigDecimal deliveredQuantity;
    @Column(name = "ITEM_CONDITION")
    public String itemCondition;
    @Column(name = "SUGGESTED_SUBINVENTORY")
    public String suggestedSubinventory;
    @Column(name = "DELIVERED_SUBINVENTORY")
    public String deliveredSubinventory;
    @Column(name = "SUGGESTED_LOCATOR_ID")
    public BigDecimal suggestedLocatorId;
    @Column(name = "DELIVERED_LOCATOR_ID")
    public BigDecimal deliveredLocatorId;
    @Column(name = "PERSON_ID")
    public BigDecimal personId;
    @Column(name = "STATUS")
    public String status;
    @Column(name = "ERROR_MESSAGE")
    public String errorMessage;
    @Column(name = "ATTRIBUTE_CATEGORY")
    public String attributeCategory;
    
    @Column(name = "ATTRIBUTE1")
    public String attribute1;
    
    @Column(name = "ATTRIBUTE2")
    public String attribute2;
    
    @Column(name = "ATTRIBUTE3")
    public String attribute3;
    
    @Column(name = "ATTRIBUTE4")
    public String attribute4;
    
    @Column(name = "ATTRIBUTE5")
    public String attribute5;
    
    @Column(name = "ATTRIBUTE6")
    public String attribute6;
    
    @Column(name = "ATTRIBUTE7")
    public String attribute7;
    
    @Column(name = "ATTRIBUTE8")
    public String attribute8;
    
    @Column(name = "ATTRIBUTE9")
    public String attribute9;
    
    @Column(name = "ATTRIBUTE10")
    public String attribute10;

    @Column(name = "ATTRIBUTE11")
    public String attribute11;
    
    @Column(name = "ATTRIBUTE12")
    public String attribute12;
    
    @Column(name = "ATTRIBUTE13")
    public String attribute13;
    
    @Column(name = "ATTRIBUTE14")
    public String attribute14;
    
    @Column(name = "ATTRIBUTE15")
    public String attribute15;

    @Column(name = "LAST_UPDATE_DATE")
    public Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    public BigDecimal lastUpdatedBy;
    @Column(name = "CREATION_DATE")
    public Date creationDate;
    @Column(name = "CREATED_BY")
    public BigDecimal createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    public BigDecimal lastUpdateLogin;
    @Column(name = "ORG_ID")
    public BigDecimal orgId;
    @Column(name = "ORG_CODE")
    public String orgCode;
    @Column(name = "ORDER_HEADER_ID")
    public BigDecimal orderHeaderId;
    @Column(name = "ORDER_LINE_ID")
    public BigDecimal orderlineId;
    @Column(name = "RECEIVED_QUANTITY")
    public BigDecimal receivedQuantity;
    @Column(name = "PARENT_TRANSACTION_ID")
    public BigDecimal parentTransactionId;
    @Column(name = "PRIMARY_UNIT_OF_MEASURE")
    public String primaryUnitOfMeasurement;
    
    public RMAConfirmEO(BigDecimal rmaId, String transactionType, BigDecimal partyId, BigDecimal custAccountId,
            BigDecimal billToSitesUseId, BigDecimal shipToSitesUseId, BigDecimal inventoryOrgId, BigDecimal lineNumber,
            BigDecimal itemId, String uomCode, BigDecimal orderQuantity, String receiptNumber,
            BigDecimal returnQuantity, BigDecimal deliveredQuantity, String itemCondition, String suggestedSubinventory,
            String deliveredSubinventory, BigDecimal suggestedLocatorId, BigDecimal deliveredLocatorId,
            BigDecimal personId, String status, String errorMessage, String attributeCategory, String attribute1,
            String attribute2, String attribute3, String attribute4, String attribute5, String attribute6,
            String attribute7, String attribute8, String attribute9, String attribute10, String attribute11,
            String attribute12, String attribute13, String attribute14, String attribute15, Date lastUpdateDate,
            BigDecimal lastUpdatedBy, Date creationDate, BigDecimal createdBy, BigDecimal lastUpdateLogin,
            BigDecimal orgId, String orgCode, BigDecimal orderHeaderId, BigDecimal orderlineId,
            BigDecimal receivedQuantity, BigDecimal parentTransactionId, String primaryUnitOfMeasurement) {
        this.rmaId = rmaId;
        this.transactionType = transactionType;
        this.partyId = partyId;
        this.custAccountId = custAccountId;
        this.billToSitesUseId = billToSitesUseId;
        this.shipToSitesUseId = shipToSitesUseId;
        this.inventoryOrgId = inventoryOrgId;
        this.lineNumber = lineNumber;
        this.itemId = itemId;
        this.uomCode = uomCode;
        this.orderQuantity = orderQuantity;
        this.receiptNumber = receiptNumber;
        this.returnQuantity = returnQuantity;
        this.deliveredQuantity = deliveredQuantity;
        this.itemCondition = itemCondition;
        this.suggestedSubinventory = suggestedSubinventory;
        this.deliveredSubinventory = deliveredSubinventory;
        this.suggestedLocatorId = suggestedLocatorId;
        this.deliveredLocatorId = deliveredLocatorId;
        this.personId = personId;
        this.status = status;
        this.errorMessage = errorMessage;
        this.attributeCategory = attributeCategory;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.attribute6 = attribute6;
        this.attribute7 = attribute7;
        this.attribute8 = attribute8;
        this.attribute9 = attribute9;
        this.attribute10 = attribute10;
        this.attribute11 = attribute11;
        this.attribute12 = attribute12;
        this.attribute13 = attribute13;
        this.attribute14 = attribute14;
        this.attribute15 = attribute15;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateLogin = lastUpdateLogin;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orderHeaderId = orderHeaderId;
        this.orderlineId = orderlineId;
        this.receivedQuantity = receivedQuantity;
        this.parentTransactionId = parentTransactionId;
        this.primaryUnitOfMeasurement = primaryUnitOfMeasurement;
    }

    public BigDecimal getRmaId() {
        return rmaId;
    }

    public void setRmaId(BigDecimal rmaId) {
        this.rmaId = rmaId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getPartyId() {
        return partyId;
    }

    public void setPartyId(BigDecimal partyId) {
        this.partyId = partyId;
    }

    public BigDecimal getCustAccountId() {
        return custAccountId;
    }

    public void setCustAccountId(BigDecimal custAccountId) {
        this.custAccountId = custAccountId;
    }

    public BigDecimal getBillToSitesUseId() {
        return billToSitesUseId;
    }

    public void setBillToSitesUseId(BigDecimal billToSitesUseId) {
        this.billToSitesUseId = billToSitesUseId;
    }

    public BigDecimal getShipToSitesUseId() {
        return shipToSitesUseId;
    }

    public void setShipToSitesUseId(BigDecimal shipToSitesUseId) {
        this.shipToSitesUseId = shipToSitesUseId;
    }

    public BigDecimal getInventoryOrgId() {
        return inventoryOrgId;
    }

    public void setInventoryOrgId(BigDecimal inventoryOrgId) {
        this.inventoryOrgId = inventoryOrgId;
    }

    public BigDecimal getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(BigDecimal lineNumber) {
        this.lineNumber = lineNumber;
    }

    public BigDecimal getItemId() {
        return itemId;
    }

    public void setItemId(BigDecimal itemId) {
        this.itemId = itemId;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public BigDecimal getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(BigDecimal returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getSuggestedSubinventory() {
        return suggestedSubinventory;
    }

    public void setSuggestedSubinventory(String suggestedSubinventory) {
        this.suggestedSubinventory = suggestedSubinventory;
    }

    public String getDeliveredSubinventory() {
        return deliveredSubinventory;
    }

    public void setDeliveredSubinventory(String deliveredSubinventory) {
        this.deliveredSubinventory = deliveredSubinventory;
    }

    public BigDecimal getSuggestedLocatorId() {
        return suggestedLocatorId;
    }

    public void setSuggestedLocatorId(BigDecimal suggestedLocatorId) {
        this.suggestedLocatorId = suggestedLocatorId;
    }

    public BigDecimal getDeliveredLocatorId() {
        return deliveredLocatorId;
    }

    public void setDeliveredLocatorId(BigDecimal deliveredLocatorId) {
        this.deliveredLocatorId = deliveredLocatorId;
    }

    public BigDecimal getPersonId() {
        return personId;
    }

    public void setPersonId(BigDecimal personId) {
        this.personId = personId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigDecimal getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigDecimal lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public BigDecimal getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderHeaderId(BigDecimal orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public BigDecimal getOrderlineId() {
        return orderlineId;
    }

    public void setOrderlineId(BigDecimal orderlineId) {
        this.orderlineId = orderlineId;
    }

    public BigDecimal getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(BigDecimal receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public BigDecimal getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(BigDecimal parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public String getPrimaryUnitOfMeasurement() {
        return primaryUnitOfMeasurement;
    }

    public void setPrimaryUnitOfMeasurement(String primaryUnitOfMeasurement) {
        this.primaryUnitOfMeasurement = primaryUnitOfMeasurement;
    }

    public RMAConfirmEO() {
    }


    










    

}