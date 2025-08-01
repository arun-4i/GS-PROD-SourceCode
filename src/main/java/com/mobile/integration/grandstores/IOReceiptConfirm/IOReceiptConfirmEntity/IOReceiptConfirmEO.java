package com.mobile.integration.grandstores.IOReceiptConfirm.IOReceiptConfirmEntity;

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
@Table(name = "XXGS_IO_RECEIPT_CONFIRM")
public class IOReceiptConfirmEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idreceipt_seq")
    @SequenceGenerator(name = "idreceipt_seq", sequenceName = "XXGS_IO_RECEIPT_ID_S", allocationSize = 1)

    @Column(name = "IO_CONF_ID")
    public BigDecimal ioConConf;

    @Column(name = "IO_CONF_NUM")
    public BigDecimal ioConfNum;
    
    @Column(name = "ORG_ID")
    public BigDecimal orgId;
    
    @Column(name = "TRANSACTION_TYPE")
    public String transactionType;
    
    @Column(name = "TRANSACTION_DATE")
    public Date transactionDate;
    
    @Column(name = "REQUISITION_NUMBER")
    public String requisitionNumber;
    
    @Column(name = "REQUISITION_HEADER_ID")
    public BigDecimal requisitionHeaderId;

    @Column(name = "REQUISITION_LINE_ID")
    public BigDecimal requsitionLineId;

    @Column(name = "LINE_NUM")
    public BigDecimal lineNum;

    @Column(name = "INV_ORG_ID")
    public BigDecimal invOrgId;

    @Column(name = "ITEM_ID")
    public BigDecimal itemId;

    @Column(name = "ITEM_CODE")
    public String itemCode;

    @Column(name = "ITEM_DESCRIPTION")
    public String itemDescription;

    @Column(name = "UOM")
    public String uom;

    @Column(name = "ORDER_QUANTITY")
    public BigDecimal orderQuantity;

    @Column(name = "VENDOR_ID")
    public BigDecimal vendorId;
 
    @Column(name = "VENDOR_NAME")
    public String vendorName;
 
    @Column(name = "VENDOR_SITE_ID")
    public BigDecimal vendorSiteId;
 
    @Column(name = "VENDOR_SITE_CODE")
    public String vendorSiteCode;
 
    @Column(name = "SUP_INV_NUM")
    public String superInvNum;
 
    @Column(name = "SUP_INV_DATE")
    public Date sup_inv_date;
 
    @Column(name = "SHIPMENT_HEADER_ID")
    public BigDecimal shipmentHeaderId;
 
    @Column(name = "SHIPMENT_NUM")
    public String shipmentNum;
 
    @Column(name = "RECEIPT_NUM")
    public String receiptNum;
 
    @Column(name = "RECEIPT_QTY")
    public BigDecimal receiptQty;
 
    @Column(name = "DELIVERED_QTY")
    public BigDecimal deliveredQty;
 
    @Column(name = "ITEM_CONDITION")
    public String itemCondition;
 
    @Column(name = "SUG_SUB_INV")
    public String sugSubInv;
 
    @Column(name = "DELIV_SUB_INV")
    public String delivSubInv;
 
    @Column(name = "SUG_LOCATOR")
    public String sugLocator;
 
    @Column(name = "DELIV_LOCATOR")
    public String delivLocator;
 
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
    public String attribute8 ;
 
    @Column(name = "ATTRIBUTE9")
    public String attribute9;
 
    @Column(name = "ATTRIBUTE10")
    public String attribute10;
 
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
 
    @Column(name = "UPDATE_FLAG")
    public String updateFlag;
 
    @Column(name = "SHIPMENT_LINE_ID")
    public BigDecimal shipmentLineId;
 
    @Column(name = "SHIP_TO_LOCATION_ID")
    public BigDecimal shipToLocationId;
 
    @Column(name = "SOURCE_ORGANIZATION_ID")
    public BigDecimal sourceOrganizationId;
 
    @Column(name = "DEST_ORGANIZATION_ID")
    public BigDecimal destOrganizationId;

    

    public BigDecimal getIoConConf() {
        return ioConConf;
    }

    public void setIoConConf(BigDecimal ioConConf) {
        this.ioConConf = ioConConf;
    }

    public BigDecimal getIoConfNum() {
        return ioConfNum;
    }

    public void setIoConfNum(BigDecimal ioConfNum) {
        this.ioConfNum = ioConfNum;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public BigDecimal getRequisitionHeaderId() {
        return requisitionHeaderId;
    }

    public void setRequisitionHeaderId(BigDecimal requisitionHeaderId) {
        this.requisitionHeaderId = requisitionHeaderId;
    }

    public BigDecimal getRequsitionLineId() {
        return requsitionLineId;
    }

    public void setRequsitionLineId(BigDecimal requsitionLineId) {
        this.requsitionLineId = requsitionLineId;
    }

    public BigDecimal getLineNum() {
        return lineNum;
    }

    public void setLineNum(BigDecimal lineNum) {
        this.lineNum = lineNum;
    }

    public BigDecimal getInvOrgId() {
        return invOrgId;
    }

    public void setInvOrgId(BigDecimal invOrgId) {
        this.invOrgId = invOrgId;
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

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getVendorId() {
        return vendorId;
    }

    public void setVendorId(BigDecimal vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public BigDecimal getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(BigDecimal vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public String getSuperInvNum() {
        return superInvNum;
    }

    public void setSuperInvNum(String superInvNum) {
        this.superInvNum = superInvNum;
    }

    public Date getSup_inv_date() {
        return sup_inv_date;
    }

    public void setSup_inv_date(Date sup_inv_date) {
        this.sup_inv_date = sup_inv_date;
    }

    public BigDecimal getShipmentHeaderId() {
        return shipmentHeaderId;
    }

    public void setShipmentHeaderId(BigDecimal shipmentHeaderId) {
        this.shipmentHeaderId = shipmentHeaderId;
    }

    public String getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(String shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public BigDecimal getReceiptQty() {
        return receiptQty;
    }

    public void setReceiptQty(BigDecimal receiptQty) {
        this.receiptQty = receiptQty;
    }

    public BigDecimal getDeliveredQty() {
        return deliveredQty;
    }

    public void setDeliveredQty(BigDecimal deliveredQty) {
        this.deliveredQty = deliveredQty;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getSugSubInv() {
        return sugSubInv;
    }

    public void setSugSubInv(String sugSubInv) {
        this.sugSubInv = sugSubInv;
    }

    public String getDelivSubInv() {
        return delivSubInv;
    }

    public void setDelivSubInv(String delivSubInv) {
        this.delivSubInv = delivSubInv;
    }

    public String getSugLocator() {
        return sugLocator;
    }

    public void setSugLocator(String sugLocator) {
        this.sugLocator = sugLocator;
    }

    public String getDelivLocator() {
        return delivLocator;
    }

    public void setDelivLocator(String delivLocator) {
        this.delivLocator = delivLocator;
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

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public BigDecimal getShipmentLineId() {
        return shipmentLineId;
    }

    public void setShipmentLineId(BigDecimal shipmentLineId) {
        this.shipmentLineId = shipmentLineId;
    }

    public BigDecimal getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(BigDecimal shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public BigDecimal getSourceOrganizationId() {
        return sourceOrganizationId;
    }

    public void setSourceOrganizationId(BigDecimal sourceOrganizationId) {
        this.sourceOrganizationId = sourceOrganizationId;
    }

    public BigDecimal getDestOrganizationId() {
        return destOrganizationId;
    }

    public void setDestOrganizationId(BigDecimal destOrganizationId) {
        this.destOrganizationId = destOrganizationId;
    }

    public IOReceiptConfirmEO(BigDecimal ioConConf, BigDecimal ioConfNum, BigDecimal orgId, String transactionType,
            Date transactionDate, String requisitionNumber, BigDecimal requisitionHeaderId, BigDecimal requsitionLineId,
            BigDecimal lineNum, BigDecimal invOrgId, BigDecimal itemId, String itemCode, String itemDescription,
            String uom, BigDecimal orderQuantity, BigDecimal vendorId, String vendorName, BigDecimal vendorSiteId,
            String vendorSiteCode, String superInvNum, Date sup_inv_date, BigDecimal shipmentHeaderId,
            String shipmentNum, String receiptNum, BigDecimal receiptQty, BigDecimal deliveredQty, String itemCondition,
            String sugSubInv, String delivSubInv, String sugLocator, String delivLocator, BigDecimal personId,
            String status, String errorMessage, String attributeCategory, String attribute1, String attribute2,
            String attribute3, String attribute4, String attribute5, String attribute6, String attribute7,
            String attribute8, String attribute9, String attribute10, Date lastUpdateDate, BigDecimal lastUpdatedBy,
            Date creationDate, BigDecimal createdBy, BigDecimal lastUpdateLogin, String updateFlag,
            BigDecimal shipmentLineId, BigDecimal shipToLocationId, BigDecimal sourceOrganizationId,
            BigDecimal destOrganizationId) {
        this.ioConConf = ioConConf;
        this.ioConfNum = ioConfNum;
        this.orgId = orgId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.requisitionNumber = requisitionNumber;
        this.requisitionHeaderId = requisitionHeaderId;
        this.requsitionLineId = requsitionLineId;
        this.lineNum = lineNum;
        this.invOrgId = invOrgId;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.uom = uom;
        this.orderQuantity = orderQuantity;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorSiteId = vendorSiteId;
        this.vendorSiteCode = vendorSiteCode;
        this.superInvNum = superInvNum;
        this.sup_inv_date = sup_inv_date;
        this.shipmentHeaderId = shipmentHeaderId;
        this.shipmentNum = shipmentNum;
        this.receiptNum = receiptNum;
        this.receiptQty = receiptQty;
        this.deliveredQty = deliveredQty;
        this.itemCondition = itemCondition;
        this.sugSubInv = sugSubInv;
        this.delivSubInv = delivSubInv;
        this.sugLocator = sugLocator;
        this.delivLocator = delivLocator;
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
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateLogin = lastUpdateLogin;
        this.updateFlag = updateFlag;
        this.shipmentLineId = shipmentLineId;
        this.shipToLocationId = shipToLocationId;
        this.sourceOrganizationId = sourceOrganizationId;
        this.destOrganizationId = destOrganizationId;
    }

    public IOReceiptConfirmEO() {
    }


    


    

}