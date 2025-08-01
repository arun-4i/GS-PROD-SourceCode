package com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationEntity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XXGS_RTV_REQ_LINES")
public class RTVConfirmationEO {

    @Override
    public String toString() {
        return "RTVConfirmationEO [requestId=" + requestId + ", lineId=" + lineId + ", itemId=" + itemId + ", itemCode="
                + itemCode + ", itemDescription=" + itemDescription + ", uom=" + uom + ", subInventory=" + subInventory
                + ", locatorId=" + locatorId + ", pickLocator=" + pickLocator + ", returnQty=" + returnQty
                + ", returnReason=" + returnReason + ", returnRemarks=" + returnRemarks + ", returnValueType="
                + returnValueType + ", retrunPrice=" + retrunPrice + ", pickedQty=" + pickedQty + ", pickedBy="
                + pickedBy + ", debitNote=" + debitNote + ", intId=" + intId + ", poHeaderId=" + poHeaderId
                + ", poLineId=" + poLineId + ", poLineLocationId=" + poLineLocationId + ", submitFlag=" + submitFlag
                + ", processFalg=" + processFalg + ", approvalFlag=" + approvalFlag + ", attribute1=" + attribute1
                + ", attribute2=" + attribute2 + ", attribute3=" + attribute3 + ", attribute4=" + attribute4
                + ", attribute5=" + attribute5 + ", attribute6=" + attribute6 + ", attribute7=" + attribute7
                + ", attribute8=" + attribute8 + ", attribute9=" + attribute9 + ", attribute10=" + attribute10
                + ", attribute11=" + attribute11 + ", attribute12=" + attribute12 + ", lastUpdateDate=" + lastUpdateDate
                + ", lastUpdatedBy=" + lastUpdatedBy + ", creationDate=" + creationDate + ", createdBy=" + createdBy
                + ", lastUpdateLogin=" + lastUpdateLogin + ", remarks=" + remarks + "]";
    }

    @Column(name = "REQUEST_ID")
    public BigDecimal requestId;

    @Id
    @Column(name = "LINE_ID")
    public BigDecimal lineId;

    @Column(name = "ITEM_ID")
    public BigDecimal itemId;
    
    @Column(name = "ITEM_CODE")
    public String itemCode;
    
    @Column(name = "ITEM_DESCRIPTION")
    public String itemDescription;
    
    @Column(name = "UOM")
    public String uom;
    
    @Column(name = "SUBINVENTORY")
    public String subInventory;
    
    @Column(name = "LOCATOR_ID")
    public BigDecimal locatorId;
    
    @Column(name = "PICK_LOCATOR")
    public String pickLocator;
    
    @Column(name = "RETURN_QTY")
    public BigDecimal returnQty;
    
    @Column(name = "RETURN_REASON")
    public String returnReason;

    @Column(name = "RETURN_REMARKS")
    public String returnRemarks;
    
    @Column(name = "RETURN_VALUE_TYPE")
    public String returnValueType;
    
    @Column(name = "RETRUN_PRICE")
    public BigDecimal retrunPrice;
    
    @Column(name = "PICKED_QTY")
    public BigDecimal pickedQty;
    
    @Column(name = "PICKED_BY")
    public BigDecimal pickedBy;
    
    @Column(name = "DEBIT_NOTE")
    public String debitNote;
    
    @Column(name = "INT_ID")
    public String intId;
    
    @Column(name = "PO_HEADER_ID")
    public String poHeaderId;
    
    @Column(name = "PO_LINE_ID")
    public BigDecimal poLineId;
    
    @Column(name = "PO_LINE_LOCATION_ID")
    public BigDecimal poLineLocationId;
    
    @Column(name = "SUBMIT_FLAG")
    public String submitFlag;
    
    @Column(name = "PROCESS_FLAG")
    public String processFalg;
    
    @Column(name = "APPROVAL_FLAG")
    public String approvalFlag;
    
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
    
    @Column(name = "REMARKS")
    public String remarks;

    public RTVConfirmationEO() {
    }

    public BigDecimal getRequestId() {
        return requestId;
    }

    public void setRequestId(BigDecimal requestId) {
        this.requestId = requestId;
    }

    public BigDecimal getLineId() {
        return lineId;
    }

    public void setLineId(BigDecimal lineId) {
        this.lineId = lineId;
    }

    public BigDecimal getItemId() {
        return itemId;
    }

    public void setItemId(BigDecimal itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getSubInventory() {
        return subInventory;
    }

    public void setSubInventory(String subInventory) {
        this.subInventory = subInventory;
    }

    public BigDecimal getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(BigDecimal locatorId) {
        this.locatorId = locatorId;
    }

    public String getPickLocator() {
        return pickLocator;
    }

    public void setPickLocator(String pickLocator) {
        this.pickLocator = pickLocator;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnRemarks() {
        return returnRemarks;
    }

    public void setReturnRemarks(String returnRemarks) {
        this.returnRemarks = returnRemarks;
    }

    public String getReturnValueType() {
        return returnValueType;
    }

    public void setReturnValueType(String returnValueType) {
        this.returnValueType = returnValueType;
    }

    public BigDecimal getRetrunPrice() {
        return retrunPrice;
    }

    public void setRetrunPrice(BigDecimal retrunPrice) {
        this.retrunPrice = retrunPrice;
    }

    public BigDecimal getPickedQty() {
        return pickedQty;
    }

    public void setPickedQty(BigDecimal pickedQty) {
        this.pickedQty = pickedQty;
    }

    public BigDecimal getPickedBy() {
        return pickedBy;
    }

    public void setPickedBy(BigDecimal pickedBy) {
        this.pickedBy = pickedBy;
    }

    public String getDebitNote() {
        return debitNote;
    }

    public void setDebitNote(String debitNote) {
        this.debitNote = debitNote;
    }

    public String getIntId() {
        return intId;
    }

    public void setIntId(String intId) {
        this.intId = intId;
    }

    public String getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(String poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public BigDecimal getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(BigDecimal poLineId) {
        this.poLineId = poLineId;
    }

    public BigDecimal getPoLineLocationId() {
        return poLineLocationId;
    }

    public void setPoLineLocationId(BigDecimal poLineLocationId) {
        this.poLineLocationId = poLineLocationId;
    }

    public String getSubmitFlag() {
        return submitFlag;
    }

    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
    }

    public String getProcessFalg() {
        return processFalg;
    }

    public void setProcessFalg(String processFalg) {
        this.processFalg = processFalg;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public RTVConfirmationEO(BigDecimal requestId, BigDecimal lineId, BigDecimal itemId, String itemCode,
            String itemDescription, String uom, String subInventory, BigDecimal locatorId, String pickLocator,
            BigDecimal returnQty, String returnReason, String returnRemarks, String returnValueType,
            BigDecimal retrunPrice, BigDecimal pickedQty, BigDecimal pickedBy, String debitNote, String intId,
            String poHeaderId, BigDecimal poLineId, BigDecimal poLineLocationId, String submitFlag, String processFalg,
            String approvalFlag, String attribute1, String attribute2, String attribute3, String attribute4,
            String attribute5, String attribute6, String attribute7, String attribute8, String attribute9,
            String attribute10, String attribute11, String attribute12, Date lastUpdateDate, BigDecimal lastUpdatedBy,
            Date creationDate, BigDecimal createdBy, BigDecimal lastUpdateLogin, String remarks) {
        
        this.requestId = requestId;
        this.lineId = lineId;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.uom = uom;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.pickLocator = pickLocator;
        this.returnQty = returnQty;
        this.returnReason = returnReason;
        this.returnRemarks = returnRemarks;
        this.returnValueType = returnValueType;
        this.retrunPrice = retrunPrice;
        this.pickedQty = pickedQty;
        this.pickedBy = pickedBy;
        this.debitNote = debitNote;
        this.intId = intId;
        this.poHeaderId = poHeaderId;
        this.poLineId = poLineId;
        this.poLineLocationId = poLineLocationId;
        this.submitFlag = submitFlag;
        this.processFalg = processFalg;
        this.approvalFlag = approvalFlag;
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
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateLogin = lastUpdateLogin;
        this.remarks = remarks;
    }

    

}