package com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionEntity;

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
@Table(name = "XXGS_INV_ITEM_CONF")
public class ItemConfigEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemcros_id_seq")
    @SequenceGenerator(name = "itemcros_id_seq", sequenceName = "XXGS_ITEM_CONF_ID_S", allocationSize = 1)
    @Column(name = "ITEM_CONF_ID")
    public BigDecimal itemconfid;
    
    @Column(name = "ITEM_CONF_NUM")
    public BigDecimal itemconfnum;
    
    @Column(name = "ORG_ID")
    public BigDecimal orgId;
    
    @Column(name = "TRANSACTION_TYPE")
    public String transactionType;
    
    @Column(name = "ORGANIZATION_ID")
    public BigDecimal organizationId;
    
    @Column(name = "ITEM_ID")
    public BigDecimal itemId;
    
    @Column(name = "ITEM_CODE")
    public String itemCode;
    
    @Column(name = "ITEM_DESCRIPTION")
    public String itemDescription;
    
    @Column(name = "UOM")
    public String uom;
    
    @Column(name = "CROSS_REFERENCE")
    public String crossReference;
    
    @Column(name = "NEW_DESCRIPTION")
    public String newDescription;
    
    @Column(name = "STATUS")
    public String status;
    
    @Column(name = "UPDATE_FLAG")
    public String updateFlag;
    
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
    public Date lastUpdatedDate;
    
    @Column(name = "LAST_UPDATED_BY")
    public BigDecimal lastUpdatedBy;
    
    @Column(name = "CREATION_DATE")
    public Date createdDate;
    
    @Column(name = "CREATED_BY")
    public BigDecimal createdBy;
    
    @Column(name = "LAST_UPDATE_LOGIN")
    public BigDecimal lastUpdaelogin;


    public ItemConfigEO() {
    }

    public ItemConfigEO(BigDecimal itemconfid, BigDecimal itemconfnum, BigDecimal orgId, String transactionType,
            BigDecimal organizationId, BigDecimal itemId, String itemCode, String itemDescription, String uom,
            String crossReference, String newDescription, String status, String updateFlag, String errorMessage,
            String attributeCategory, String attribute1, String attribute2, String attribute3, String attribute4,
            String attribute5, String attribute6, String attribute7, String attribute8, String attribute9,
            String attribute10, String attribute11, String attribute12, String attribute13, String attribute14,
            String attribute15, Date lastUpdatedDate, BigDecimal lastUpdatedBy, Date createdDate, BigDecimal createdBy,
            BigDecimal lastUpdaelogin) {
        this.itemconfid = itemconfid;
        this.itemconfnum = itemconfnum;
        this.orgId = orgId;
        this.transactionType = transactionType;
        this.organizationId = organizationId;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.uom = uom;
        this.crossReference = crossReference;
        this.newDescription = newDescription;
        this.status = status;
        this.updateFlag = updateFlag;
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
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdaelogin = lastUpdaelogin;
    }

    public BigDecimal getItemconfid() {
        return itemconfid;
    }

    public void setItemconfid(BigDecimal itemconfid) {
        this.itemconfid = itemconfid;
    }

    public BigDecimal getItemconfnum() {
        return itemconfnum;
    }

    public void setItemconfnum(BigDecimal itemconfnum) {
        this.itemconfnum = itemconfnum;
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

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
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

    public String getCrossReference() {
        return crossReference;
    }

    public void setCrossReference(String crossReference) {
        this.crossReference = crossReference;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
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

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public BigDecimal getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getLastUpdaelogin() {
        return lastUpdaelogin;
    }

    public void setLastUpdaelogin(BigDecimal lastUpdaelogin) {
        this.lastUpdaelogin = lastUpdaelogin;
    }


    




}