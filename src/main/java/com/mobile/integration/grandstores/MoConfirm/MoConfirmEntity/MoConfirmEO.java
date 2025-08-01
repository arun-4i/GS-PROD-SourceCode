package com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity;

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
@Table(name = "XXGS_MO_CONFIRMATIONS")
public class MoConfirmEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mo_seq")
    @SequenceGenerator(name = "mo_seq", sequenceName = "XXGS_MO_ID_S", allocationSize = 1)
    @Column(name = "MO_ID")
    public BigDecimal moid;

    @Column(name = "TRANSACTION_TYPE")
    public String transactionType;
    
    @Column(name = "MO_NUMBER")
    public BigDecimal moNumber;
    
    @Column(name = "MO_LINE_NUMBER")
    public BigDecimal moLineNumber;
    
    @Column(name = "PICKSLIP_NUMBER")
    public BigDecimal pickSlipNumber;
    
    @Column(name = "ITEM_ID")
    public BigDecimal itemId;
    
    @Column(name = "UOM_CODE")
    public String uomCode;
    
    @Column(name = "REQUIRED_QUANTITY")
    public BigDecimal requiredQuantity;
    
    @Column(name = "PICKED_QUANTITY")
    public BigDecimal pickedQuantity;
    
    @Column(name = "TRANSFER_QUANTITY")
    public BigDecimal transferQuantity;
    
    @Column(name = "SOURCE_SUB_INVENTORY")
    public String sourceSubInventory;
    
    @Column(name = "DESTINATION_SUB_INVENTORY")
    public String destinationSubInventory;
    
    @Column(name = "SOURCE_LOCATOR_ID")
    public BigDecimal sourceLocationId;
    
    @Column(name = "DESINATION_LOCATOR_ID")
    public BigDecimal destinationLocationId;
    
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
    
    @Column(name = "ITEM_CODE")
    public String itemCode;
    
    @Column(name = "ORDER_NUMBER")
    public BigDecimal orderNumber;
    
    @Column(name = "DELIVERY_DETAIL_ID")
    public BigDecimal deliveryDetailId;
    
    @Column(name = "CUSTOMER_NAME")
    public String customerName;
    
    @Column(name = "CUST_ACCOUNT_ID")
    public BigDecimal customerAccountId;

    public MoConfirmEO(BigDecimal moid, String transactionType, BigDecimal moNumber, BigDecimal moLineNumber,
            BigDecimal pickSlipNumber, BigDecimal itemId, String uomCode, BigDecimal requiredQuantity,
            BigDecimal pickedQuantity, BigDecimal transferQuantity, String sourceSubInventory,
            String destinationSubInventory, BigDecimal sourceLocationId, BigDecimal destinationLocationId,
            BigDecimal personId, String status, String errorMessage, String attributeCategory, String attribute1,
            String attribute2, String attribute3, String attribute4, String attribute5, String attribute6,
            String attribute7, String attribute8, String attribute9, String attribute10, String attribute11,
            String attribute12, String attribute13, String attribute14, String attribute15, Date lastUpdateDate,
            BigDecimal lastUpdatedBy, Date creationDate, BigDecimal createdBy, BigDecimal lastUpdateLogin,
            BigDecimal orgId, String orgCode, String itemCode, BigDecimal orderNumber, BigDecimal deliveryDetailId,
            String customerName, BigDecimal customerAccountId) {
        this.moid = moid;
        this.transactionType = transactionType;
        this.moNumber = moNumber;
        this.moLineNumber = moLineNumber;
        this.pickSlipNumber = pickSlipNumber;
        this.itemId = itemId;
        this.uomCode = uomCode;
        this.requiredQuantity = requiredQuantity;
        this.pickedQuantity = pickedQuantity;
        this.transferQuantity = transferQuantity;
        this.sourceSubInventory = sourceSubInventory;
        this.destinationSubInventory = destinationSubInventory;
        this.sourceLocationId = sourceLocationId;
        this.destinationLocationId = destinationLocationId;
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
        this.itemCode = itemCode;
        this.orderNumber = orderNumber;
        this.deliveryDetailId = deliveryDetailId;
        this.customerName = customerName;
        this.customerAccountId = customerAccountId;
    }

    public MoConfirmEO() {
    }

    public BigDecimal getMoid() {
        return moid;
    }

    public void setMoid(BigDecimal moid) {
        this.moid = moid;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getMoNumber() {
        return moNumber;
    }

    public void setMoNumber(BigDecimal moNumber) {
        this.moNumber = moNumber;
    }

    public BigDecimal getMoLineNumber() {
        return moLineNumber;
    }

    public void setMoLineNumber(BigDecimal moLineNumber) {
        this.moLineNumber = moLineNumber;
    }

    public BigDecimal getPickSlipNumber() {
        return pickSlipNumber;
    }

    public void setPickSlipNumber(BigDecimal pickSlipNumber) {
        this.pickSlipNumber = pickSlipNumber;
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

    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public BigDecimal getPickedQuantity() {
        return pickedQuantity;
    }

    public void setPickedQuantity(BigDecimal pickedQuantity) {
        this.pickedQuantity = pickedQuantity;
    }

    public BigDecimal getTransferQuantity() {
        return transferQuantity;
    }

    public void setTransferQuantity(BigDecimal transferQuantity) {
        this.transferQuantity = transferQuantity;
    }

    public String getSourceSubInventory() {
        return sourceSubInventory;
    }

    public void setSourceSubInventory(String sourceSubInventory) {
        this.sourceSubInventory = sourceSubInventory;
    }

    public String getDestinationSubInventory() {
        return destinationSubInventory;
    }

    public void setDestinationSubInventory(String destinationSubInventory) {
        this.destinationSubInventory = destinationSubInventory;
    }

    public BigDecimal getSourceLocationId() {
        return sourceLocationId;
    }

    public void setSourceLocationId(BigDecimal sourceLocationId) {
        this.sourceLocationId = sourceLocationId;
    }

    public BigDecimal getDestinationLocationId() {
        return destinationLocationId;
    }

    public void setDestinationLocationId(BigDecimal destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(BigDecimal orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getDeliveryDetailId() {
        return deliveryDetailId;
    }

    public void setDeliveryDetailId(BigDecimal deliveryDetailId) {
        this.deliveryDetailId = deliveryDetailId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(BigDecimal customerAccountId) {
        this.customerAccountId = customerAccountId;
    }















    

}