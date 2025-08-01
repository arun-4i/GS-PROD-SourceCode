package com.mobile.integration.grandstores.Entity;
// import java.util.Date;
// import java.sql.Date;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
// import javax.persistence.SequenceGenerator;
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
@Table(name = "XXGS_GET_PI_ITEM_DTLS_V")
public class PIItemDetailEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pseq")
    @SequenceGenerator(name = "pseq",sequenceName = "XXGS_PO_CONF_ID_S", allocationSize = 1)
    @Column(name = "HEADER_ID")
    private BigDecimal headerId;

    @Column(name = "INVENTORY_ORG_ID")
    private BigDecimal inventoryOrgId;

    @Column(name = "DOCUMENT_NUMBER")
    private BigDecimal documentNumber;

    @Column(name = "SUPPLIER_ID")
    private BigDecimal supplierId;

    @Column(name = "SUPPLIER_NUMBER")
    private String supplierNumber;
    
    @Column(name = "SUPPLIER_NAME")
    private String supplierName;
    
    @Column(name = "SHIPMENT_REF")
    private String shipmentRef;
    
    @Column(name = "LINE_ID")
    private BigDecimal lineId;
    
    @Column(name = "PI_NUMBER")
    private String piNumber;
    
    @Column(name = "ITEM_ID")
    private BigDecimal itemId;
    
    @Column(name = "ITEM_CODE")
    private String itemCode;
    
    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;
    
    @Column(name = "LOT_STATUS_ENABLED")
    private String lotStatusEnabled;
    
    @Column(name = "PI_QTY")
    private BigDecimal piQty;
    
    @Column(name = "PI_UNIT_PRICE")
    private BigDecimal piunitPrice;

    public PIItemDetailEO() {
    }

    public BigDecimal getHeaderId() {
        return headerId;
    }

    public void setHeaderId(BigDecimal headerId) {
        this.headerId = headerId;
    }

    public BigDecimal getInventoryOrgId() {
        return inventoryOrgId;
    }

    public void setInventoryOrgId(BigDecimal inventoryOrgId) {
        this.inventoryOrgId = inventoryOrgId;
    }

    public BigDecimal getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(BigDecimal documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BigDecimal getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(BigDecimal supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getShipmentRef() {
        return shipmentRef;
    }

    public void setShipmentRef(String shipmentRef) {
        this.shipmentRef = shipmentRef;
    }

    public BigDecimal getLineId() {
        return lineId;
    }

    public void setLineId(BigDecimal lineId) {
        this.lineId = lineId;
    }

    public String getPiNumber() {
        return piNumber;
    }

    public void setPiNumber(String piNumber) {
        this.piNumber = piNumber;
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

    public String getLotStatusEnabled() {
        return lotStatusEnabled;
    }

    public void setLotStatusEnabled(String lotStatusEnabled) {
        this.lotStatusEnabled = lotStatusEnabled;
    }

    public BigDecimal getPiQty() {
        return piQty;
    }

    public void setPiQty(BigDecimal piQty) {
        this.piQty = piQty;
    }

    public BigDecimal getPiunitPrice() {
        return piunitPrice;
    }

    public void setPiunitPrice(BigDecimal piunitPrice) {
        this.piunitPrice = piunitPrice;
    }

    public PIItemDetailEO(BigDecimal headerId, BigDecimal inventoryOrgId, BigDecimal documentNumber,
            BigDecimal supplierId, String supplierNumber, String supplierName, String shipmentRef, BigDecimal lineId,
            String piNumber, BigDecimal itemId, String itemCode, String itemDescription, String lotStatusEnabled,
            BigDecimal piQty, BigDecimal piunitPrice) {
        this.headerId = headerId;
        this.inventoryOrgId = inventoryOrgId;
        this.documentNumber = documentNumber;
        this.supplierId = supplierId;
        this.supplierNumber = supplierNumber;
        this.supplierName = supplierName;
        this.shipmentRef = shipmentRef;
        this.lineId = lineId;
        this.piNumber = piNumber;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.lotStatusEnabled = lotStatusEnabled;
        this.piQty = piQty;
        this.piunitPrice = piunitPrice;
    }
    

 
    
    
    
}