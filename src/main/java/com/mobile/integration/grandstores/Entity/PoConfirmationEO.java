package com.mobile.integration.grandstores.Entity;
import java.math.BigDecimal;
// import java.util.Date;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "XXGS_PO_CONFIRMATION")
public class PoConfirmationEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poconf_id_seq")
    @SequenceGenerator(name = "poconf_id_seq",sequenceName = "XXGS_PO_CONF_ID_S", allocationSize = 1)
    @Column(name = "PO_CONF_ID")
    public BigDecimal poConfId;

    @Column(name = "PO_REF_NUM")
    public String poRefNum;
    @Column(name = "TRANSACTION_TYPE")
    public String transactionType;
    @Column(name = "PO_HEADER_ID")
    public BigDecimal poHeaderId;
    @Column(name = "PO_NUMBER")
    public String poNumber;
    @Column(name = "PO_RELEASE_ID")
    public BigDecimal poReleaseId;
    @Column(name = "RELEASE_NUM")
    public BigDecimal releaseNum;
    @Column(name = "PO_LINE_ID")
    public BigDecimal poLineId;
    @Column(name = "LINE_NUM")
    public BigDecimal lineNum;

    @Column(name = "VENDOR_ID")
    public BigDecimal vendorId;
    
    @Column(name = "VENDOR_NUM")
    public String vendorNum;
    
    @Column(name = "VENDOR_NAME")
    public String vendorName;

    @Column(name = "VENDOR_SITE_ID")
    public BigDecimal vendorSiteId;

    @Column(name = "VENDOR_SITE_CODE")
    public String vendorSiteCode;

    @Column(name = "ITEM_ID")
    public BigDecimal itemId;

    @Column(name = "ITEM_CODE")
    public String itemCode;

    @Column(name = "ITEM_DESCRIPTION")
    public String itemDescription;

    @Column(name = "UOM")
    public String uom;

    @Column(name = "PO_QTY")
    public BigDecimal poQty;

    @Column(name = "SUP_INV_NUM")
    public String supInvNum;

    @Column(name = "SUP_INV_DATE")
    public Date supInvDate;

    @Column(name = "SHIPMENT_HEADER_ID")
    public BigDecimal shipmentHeaderId;

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

    @Column(name = "STATUS")
    public String status;

    @Column(name = "ERROR_MESSAGE")
    public String errorMessage;

    @Column(name = "ATTRIBUTE_CATEGORY")
    public String attributecategory;
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
    
    public PoConfirmationEO() {
    }

    public PoConfirmationEO(BigDecimal poConfId, String poRefNum, String transactionType, BigDecimal poHeaderId,
            String poNumber, BigDecimal poReleaseId, BigDecimal releaseNum, BigDecimal poLineId, BigDecimal lineNum,
            BigDecimal vendorId, String vendorNum, String vendorName, BigDecimal vendorSiteId, String vendorSiteCode,
            BigDecimal itemId, String itemCode, String itemDescription, String uom, BigDecimal poQty, String supInvNum,
            Date supInvDate, BigDecimal shipmentHeaderId, String receiptNum, BigDecimal receiptQty,
            BigDecimal deliveredQty, String itemCondition, String sugSubInv, String delivSubInv, String sugLocator,
            String delivLocator, String status, String errorMessage, String attributecategory, String attribute1,
            String attribute2, String attribute3, String attribute4, String attribute5, String attribute6,
            String attribute7, String attribute8, String attribute9, String attribute10, String attribute11,
            String attribute12, String attribute13, String attribute14, String attribute15, Date lastUpdateDate,
            BigDecimal lastUpdatedBy, Date creationDate, BigDecimal createdBy, BigDecimal lastUpdateLogin) {
        this.poConfId = poConfId;
        this.poRefNum = poRefNum;
        this.transactionType = transactionType;
        this.poHeaderId = poHeaderId;
        this.poNumber = poNumber;
        this.poReleaseId = poReleaseId;
        this.releaseNum = releaseNum;
        this.poLineId = poLineId;
        this.lineNum = lineNum;
        this.vendorId = vendorId;
        this.vendorNum = vendorNum;
        this.vendorName = vendorName;
        this.vendorSiteId = vendorSiteId;
        this.vendorSiteCode = vendorSiteCode;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.uom = uom;
        this.poQty = poQty;
        this.supInvNum = supInvNum;
        this.supInvDate = supInvDate;
        this.shipmentHeaderId = shipmentHeaderId;
        this.receiptNum = receiptNum;
        this.receiptQty = receiptQty;
        this.deliveredQty = deliveredQty;
        this.itemCondition = itemCondition;
        this.sugSubInv = sugSubInv;
        this.delivSubInv = delivSubInv;
        this.sugLocator = sugLocator;
        this.delivLocator = delivLocator;
        this.status = status;
        this.errorMessage = errorMessage;
        this.attributecategory = attributecategory;
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
    }

    public BigDecimal getPoConfId() {
        return poConfId;
    }

    public void setPoConfId(BigDecimal poConfId) {
        this.poConfId = poConfId;
    }

    public String getPoRefNum() {
        return poRefNum;
    }

    public void setPoRefNum(String poRefNum) {
        this.poRefNum = poRefNum;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(BigDecimal poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public BigDecimal getPoReleaseId() {
        return poReleaseId;
    }

    public void setPoReleaseId(BigDecimal poReleaseId) {
        this.poReleaseId = poReleaseId;
    }

    public BigDecimal getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(BigDecimal releaseNum) {
        this.releaseNum = releaseNum;
    }

    public BigDecimal getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(BigDecimal poLineId) {
        this.poLineId = poLineId;
    }

    public BigDecimal getLineNum() {
        return lineNum;
    }

    public void setLineNum(BigDecimal lineNum) {
        this.lineNum = lineNum;
    }

    public BigDecimal getVendorId() {
        return vendorId;
    }

    public void setVendorId(BigDecimal vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorNum() {
        return vendorNum;
    }

    public void setVendorNum(String vendorNum) {
        this.vendorNum = vendorNum;
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

    public BigDecimal getPoQty() {
        return poQty;
    }

    public void setPoQty(BigDecimal poQty) {
        this.poQty = poQty;
    }

    public String getSupInvNum() {
        return supInvNum;
    }

    public void setSupInvNum(String supInvNum) {
        this.supInvNum = supInvNum;
    }

    public Date getSupInvDate() {
        return supInvDate;
    }

    public void setSupInvDate(Date supInvDate) {
        this.supInvDate = supInvDate;
    }

    public BigDecimal getShipmentHeaderId() {
        return shipmentHeaderId;
    }

    public void setShipmentHeaderId(BigDecimal shipmentHeaderId) {
        this.shipmentHeaderId = shipmentHeaderId;
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

    public String getAttributecategory() {
        return attributecategory;
    }

    public void setAttributecategory(String attributecategory) {
        this.attributecategory = attributecategory;
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


    






    



}