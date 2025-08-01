package com.mobile.integration.grandstores.User.Entity;
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
@Table(name = "XXGS_WMS_USER_RESP_GROUPS")
public class UserResponsibleGroupEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_res_id_seq")
    @SequenceGenerator(name = "user_res_id_seq",sequenceName = "XXGS_USER_RESP_GROUP_ID_S", allocationSize = 1)
    @Column(name = "USER_RESP_GROUP_ID")
    public BigDecimal userrespgroupid;

    @Column(name = "USER_ID")
    public BigDecimal userId;
    @Column(name = "RESPONSIBILITY_ID")
    public BigDecimal responsibilityId;
    @Column(name = "DESCRIPTION")
    public String description;
    @Column(name = "START_DATE")
    public Date startDate;
    @Column(name = "END_DATE")
    public Date endDate;
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
    public BigDecimal lastupdatelogin;

    
    public BigDecimal getUserrespgroupid() {
        return userrespgroupid;
    }
    public void setUserrespgroupid(BigDecimal userrespgroupid) {
        this.userrespgroupid = userrespgroupid;
    }
    public BigDecimal getUserId() {
        return userId;
    }
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }
    public BigDecimal getResponsibilityId() {
        return responsibilityId;
    }
    public void setResponsibilityId(BigDecimal responsibilityId) {
        this.responsibilityId = responsibilityId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    public BigDecimal getLastupdatelogin() {
        return lastupdatelogin;
    }
    public void setLastupdatelogin(BigDecimal lastupdatelogin) {
        this.lastupdatelogin = lastupdatelogin;
    }
    public UserResponsibleGroupEO() {
    }
    public UserResponsibleGroupEO(BigDecimal userrespgroupid, BigDecimal userId, BigDecimal responsibilityId,
            String description, Date startDate, Date endDate, String attributeCategory, String attribute1,
            String attribute2, String attribute3, String attribute4, String attribute5, String attribute6,
            String attribute7, String attribute8, String attribute9, String attribute10, String attribute11,
            String attribute12, String attribute13, String attribute14, String attribute15, Date lastUpdateDate,
            BigDecimal lastUpdatedBy, Date creationDate, BigDecimal createdBy, BigDecimal lastupdatelogin) {
        this.userrespgroupid = userrespgroupid;
        this.userId = userId;
        this.responsibilityId = responsibilityId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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
        this.lastupdatelogin = lastupdatelogin;
    }   
    
    

    

}