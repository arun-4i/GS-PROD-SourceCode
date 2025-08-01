package com.mobile.integration.grandstores.GSBinLocation.GLEntity;

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
@Table(name = "XXGS_BIN_TRANSFER")
public class BinsTransferEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "XXGS_BIN_TRANSFER_ID_S", allocationSize = 1)
    @Column(name = "BIN_TRANSFER_ID")
    public BigDecimal binTransferId;

    @Column(name = "ORGANIZATION_ID")
    public BigDecimal organizationId;
    
    @Column(name = "ORGANIZATION_CODE")
    public String organizationCode;;
    
    @Column(name = "ORGANIZATION_NAME")
    public String organizationName;;
    
    @Column(name = "INVENTORY_ITEM_ID")
    public BigDecimal inventoryItemId;
    
    @Column(name = "ITEM_CODE")
    public String itemCode;
    
    @Column(name = "UOM_CODE")
    public String uomCode;;
    
    @Column(name = "UOM")
    public String uom;
    
    @Column(name = "DEPARTMENT")
    public String department;
    
    @Column(name = "BRAND")
    public String brand;;
    
    @Column(name = "LOCATOR_ID")
    public BigDecimal locatorId;
    
    @Column(name = "LOCATOR_QUANTITY")
    public BigDecimal locatorQuantity;
    
    @Column(name = "SUBINVENTORY_CODE")
    public String subinventoryCode;
    
    @Column(name = "LOCATOR")
    public String locator;
    
    @Column(name = "SECONDARY_INVENTORY_NAME")
    public String secondaryInventoryName;
    
    @Column(name = "ONHAND_TOTAL")
    public BigDecimal onhandTotal;
    
    @Column(name = "TOTAL_AVL_RESERVE")
    public BigDecimal totalAvlReserve;
    
    @Column(name = "SOURCE_BIN")
    public String sourceBin;;
    
    @Column(name = "SOURCE_SUB_INVENTORY")
    public String sourceSubInventory;;
    
    @Column(name = "DESTINATION_BIN")
    public String destinationBin;
    
    @Column(name = "DESTINATION_SUB_INVENTORY")
    public String destinationSubInventory;
    
    @Column(name = "COUNT_QUANTITY")
    public BigDecimal countQuantity;
    
    @Column(name = "ATTRIBUTE_CATEGORY")
    public String attributeCategory;;
    
    @Column(name = "ATTRIBUTE1")
    public String attribute1;;
    
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
    public BigDecimal lastUpdatedBy;;
    
    @Column(name = "CREATION_DATE")
    public Date creationDate;;
    
    @Column(name = "CREATED_BY")
    public BigDecimal createdBy;
    
    @Column(name = "LAST_UPDATE_LOGIN")
    public BigDecimal lastUpdateLogin;

    public BinsTransferEO() {
    }

    public BinsTransferEO(BigDecimal binTransferId, BigDecimal organizationId, String organizationCode,
            String organizationName, BigDecimal inventoryItemId, String itemCode, String uomCode, String uom,
            String department, String brand, BigDecimal locatorId, BigDecimal locatorQuantity, String subinventoryCode,
            String locator, String secondaryInventoryName, BigDecimal onhandTotal, BigDecimal totalAvlReserve,
            String sourceBin, String sourceSubInventory, String destinationBin, String destinationSubInventory,
            BigDecimal countQuantity, String attributeCategory, String attribute1, String attribute2, String attribute3,
            String attribute4, String attribute5, String attribute6, String attribute7, String attribute8,
            String attribute9, String attribute10, String attribute11, String attribute12, String attribute13,
            String attribute14, String attribute15, Date lastUpdateDate, BigDecimal lastUpdatedBy, Date creationDate,
            BigDecimal createdBy, BigDecimal lastUpdateLogin) {
        this.binTransferId = binTransferId;
        this.organizationId = organizationId;
        this.organizationCode = organizationCode;
        this.organizationName = organizationName;
        this.inventoryItemId = inventoryItemId;
        this.itemCode = itemCode;
        this.uomCode = uomCode;
        this.uom = uom;
        this.department = department;
        this.brand = brand;
        this.locatorId = locatorId;
        this.locatorQuantity = locatorQuantity;
        this.subinventoryCode = subinventoryCode;
        this.locator = locator;
        this.secondaryInventoryName = secondaryInventoryName;
        this.onhandTotal = onhandTotal;
        this.totalAvlReserve = totalAvlReserve;
        this.sourceBin = sourceBin;
        this.sourceSubInventory = sourceSubInventory;
        this.destinationBin = destinationBin;
        this.destinationSubInventory = destinationSubInventory;
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
        this.createdBy = createdBy;
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public BigDecimal getBinTransferId() {
        return binTransferId;
    }

    public void setBinTransferId(BigDecimal binTransferId) {
        this.binTransferId = binTransferId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public BigDecimal getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(BigDecimal inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(BigDecimal locatorId) {
        this.locatorId = locatorId;
    }

    public BigDecimal getLocatorQuantity() {
        return locatorQuantity;
    }

    public void setLocatorQuantity(BigDecimal locatorQuantity) {
        this.locatorQuantity = locatorQuantity;
    }

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getSecondaryInventoryName() {
        return secondaryInventoryName;
    }

    public void setSecondaryInventoryName(String secondaryInventoryName) {
        this.secondaryInventoryName = secondaryInventoryName;
    }

    public BigDecimal getOnhandTotal() {
        return onhandTotal;
    }

    public void setOnhandTotal(BigDecimal onhandTotal) {
        this.onhandTotal = onhandTotal;
    }

    public BigDecimal getTotalAvlReserve() {
        return totalAvlReserve;
    }

    public void setTotalAvlReserve(BigDecimal totalAvlReserve) {
        this.totalAvlReserve = totalAvlReserve;
    }

    public String getSourceBin() {
        return sourceBin;
    }

    public void setSourceBin(String sourceBin) {
        this.sourceBin = sourceBin;
    }

    public String getSourceSubInventory() {
        return sourceSubInventory;
    }

    public void setSourceSubInventory(String sourceSubInventory) {
        this.sourceSubInventory = sourceSubInventory;
    }

    public String getDestinationBin() {
        return destinationBin;
    }

    public void setDestinationBin(String destinationBin) {
        this.destinationBin = destinationBin;
    }

    public String getDestinationSubInventory() {
        return destinationSubInventory;
    }

    public void setDestinationSubInventory(String destinationSubInventory) {
        this.destinationSubInventory = destinationSubInventory;
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