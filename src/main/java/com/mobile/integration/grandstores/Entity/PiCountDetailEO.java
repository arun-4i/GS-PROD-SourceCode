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


@Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
@Table(name = "XXGS_PI_COUNT_DETAILS")

public class PiCountDetailEO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countdtl_id_seq")
    @SequenceGenerator(name = "countdtl_id_seq",sequenceName = "XXGS_COUNT_DETAIL_ID_S", allocationSize = 1)
    @Column(name = "COUNT_DETAIL_ID")
    public BigDecimal countDetailID;

    @Column(name = "HEADER_ID")
    public BigDecimal headerId; 
        
    @Column(name = "DOCUMENT_NUMBER")
    public BigDecimal documentNumber;                       

    @Column(name = "VENDOR_ID")
    public BigDecimal vendorID;

    @Column(name = "VENDOR_NUMBER")
    public String vendorNumber;

    @Column(name = "VENDOR_NAME")
    public String vendorName;

    @Column(name = "SHIPMENT_REF")
    public String shipmentRef;

    @Column(name = "PI_NUMBER")
    public String piNumber;

    @Column(name = "UPDATE_FLAG")
    public String updateFlag;

    @Column(name = "ITEM_ID")
    public BigDecimal itemId;

    @Column(name = "ITEM_CODE")                     
    public String itemCode;

    @Column(name = "ITEM_DESCRIPTION")                   
    public String itemDescription;

    @Column(name = "QUANTITY")            
    public BigDecimal quantity;

    @Column(name = "NO_OF_PALLETS")                    
    public BigDecimal noOfPallets;

    @Column(name = "COUNT_PER_PALLETS")               
    public BigDecimal countPerPallets;

    @Column(name = "COUNT_QUANTITY")           
    public BigDecimal countQuantity;

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
    // @JsonFormat(pattern="dd-MM-YYYY")
    public Date lastUpdateDate;

    @Column(name = "LAST_UPDATED_BY")            
    public BigDecimal lastUpdatedBy;
            
    @Column(name = "CREATION_DATE")             
    // @JsonFormat(pattern="dd-MM-YYYY")
    public Date creationDate;

    @Column(name = "CREATED_BY")               
    public BigDecimal created;

    @Column(name = "LAST_UPDATE_LOGIN")                  
    public BigDecimal lastUpdateLogin;

    @Column(name = "LINE_NO")                  
    public BigDecimal lineNo;
    @Column(name = "MULTIPLE_OF_PALLET")
    public BigDecimal multipleOfPallet;
    @Column(name = "LOT_NUMBER")
    public String lotNumber;
    @Column(name = "LOT_QTY")
    public BigDecimal lotQty;
    @Column(name = "SERIAL_NUMBER")
    public String serialNumber;


    public BigDecimal getCountDetailID() {
        return countDetailID;
    }

    public void setCountDetailID(BigDecimal countDetailID) {
        this.countDetailID = countDetailID;
    }

    public BigDecimal getHeaderId() {
        return headerId;
    }

    public void setHeaderId(BigDecimal headerId) {
        this.headerId = headerId;
    }

    public BigDecimal getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(BigDecimal documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BigDecimal getVendorID() {
        return vendorID;
    }

    public void setVendorID(BigDecimal vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getShipmentRef() {
        return shipmentRef;
    }

    public void setShipmentRef(String shipmentRef) {
        this.shipmentRef = shipmentRef;
    }

    public String getPiNumber() {
        return piNumber;
    }

    public void setPiNumber(String piNumber) {
        this.piNumber = piNumber;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getNoOfPallets() {
        return noOfPallets;
    }

    public void setNoOfPallets(BigDecimal noOfPallets) {
        this.noOfPallets = noOfPallets;
    }

    public BigDecimal getCountPerPallets() {
        return countPerPallets;
    }

    public void setCountPerPallets(BigDecimal countPerPallets) {
        this.countPerPallets = countPerPallets;
    }

    public BigDecimal getCountQuantity() {
        return countQuantity;
    }

    public void setCountQuantity(BigDecimal countQuantity) {
        this.countQuantity = countQuantity;
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

    public BigDecimal getCreated() {
        return created;
    }

    public void setCreated(BigDecimal created) {
        this.created = created;
    }

    public BigDecimal getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigDecimal lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public PiCountDetailEO() {
    }

    public PiCountDetailEO(BigDecimal countDetailID, BigDecimal headerId, BigDecimal documentNumber,
            BigDecimal vendorID, String vendorNumber, String vendorName, String shipmentRef, String piNumber,
            String updateFlag, BigDecimal itemId, String itemCode, String itemDescription, BigDecimal quantity,
            BigDecimal noOfPallets, BigDecimal countPerPallets, BigDecimal countQuantity, String attributeCategory,
            String attribute1, String attribute2, String attribute3, String attribute4, String attribute5,
            String attribute6, String attribute7, String attribute8, String attribute9, String attribute10,
            String attribute11, String attribute12, String attribute13, String attribute14, String attribute15,
            Date lastUpdateDate, BigDecimal lastUpdatedBy, Date creationDate, BigDecimal created,
            BigDecimal lastUpdateLogin, BigDecimal lineNo, BigDecimal multipleOfPallet, String lotNumber,
            BigDecimal lotQty, String serialNumber) {
        this.countDetailID = countDetailID;
        this.headerId = headerId;
        this.documentNumber = documentNumber;
        this.vendorID = vendorID;
        this.vendorNumber = vendorNumber;
        this.vendorName = vendorName;
        this.shipmentRef = shipmentRef;
        this.piNumber = piNumber;
        this.updateFlag = updateFlag;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.noOfPallets = noOfPallets;
        this.countPerPallets = countPerPallets;
        this.countQuantity = countQuantity;
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
        this.created = created;
        this.lastUpdateLogin = lastUpdateLogin;
        this.lineNo = lineNo;
        this.multipleOfPallet = multipleOfPallet;
        this.lotNumber = lotNumber;
        this.lotQty = lotQty;
        this.serialNumber = serialNumber;
    }

    



}