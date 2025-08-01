package com.mobile.integration.grandstores.GSBinLocation.GLEntity;

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
@Table(name = "XXGS_SPOT_CHECK_DTL")
public class SpotCheckEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spot_id_seq")
    @SequenceGenerator(name = "spot_id_seq", sequenceName = "XXGS_SPOT_CHECK_ID_S", allocationSize = 1)
    @Column(name = "SPOT_CHECK_ID")
    public BigDecimal spotCheckId;

    @Column(name = "ORGANIZATION_ID")
    public BigDecimal organizationId;

    @Column(name = "INVENTORY_ITEM_ID")
    public BigDecimal inventory_item_id;
    @Column(name = "ITEM_CODE")
    public String item_code;

    @Column(name = "ITEM_DESCRIPTION")
    public String item_description;

    @Column(name = "SUBINVENTORY_CODE")
    public String subinventoryCode;

    @Column(name = "LOCATOR_ID")
    public BigDecimal locatorId;

    @Column(name = "LOCATOR")
    public String locator;

    @Column(name = "MISPLC_ITEM_CODE")
    public String misplcItemCode;

    @Column(name = "MISPLC_ITEM_DESCRIPTION")
    public String misplcItemDescription;


    @Column(name = "SYSTEM_STOCK")
    public BigDecimal system_stock;

    @Column(name = "ATTRIBUTE_CATEGORY")
    public String attribute_category;
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
    public Date last_update_date;
    @Column(name = "LAST_UPDATED_BY")
    public BigDecimal last_updated_by;
    @Column(name = "CREATION_DATE")
    public Date creation_date;
    @Column(name = "CREATED_BY")
    public BigDecimal created_by;
    @Column(name = "LAST_UPDATE_LOGIN")
    public BigDecimal last_update_login;
//==================================================================

    public SpotCheckEO() {
    }

    public BigDecimal getSpotCheckId() {
        return spotCheckId;
    }

    public void setSpotCheckId(BigDecimal spotCheckId) {
        this.spotCheckId = spotCheckId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public BigDecimal getInventory_item_id() {
        return inventory_item_id;
    }

    public void setInventory_item_id(BigDecimal inventory_item_id) {
        this.inventory_item_id = inventory_item_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public BigDecimal getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(BigDecimal locatorId) {
        this.locatorId = locatorId;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getMisplcItemCode() {
        return misplcItemCode;
    }

    public void setMisplcItemCode(String misplcItemCode) {
        this.misplcItemCode = misplcItemCode;
    }

    public String getMisplcItemDescription() {
        return misplcItemDescription;
    }

    public void setMisplcItemDescription(String misplcItemDescription) {
        this.misplcItemDescription = misplcItemDescription;
    }

    public BigDecimal getSystem_stock() {
        return system_stock;
    }

    public void setSystem_stock(BigDecimal system_stock) {
        this.system_stock = system_stock;
    }

    public String getAttribute_category() {
        return attribute_category;
    }

    public void setAttribute_category(String attribute_category) {
        this.attribute_category = attribute_category;
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

    public Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public BigDecimal getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(BigDecimal last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public BigDecimal getCreated_by() {
        return created_by;
    }

    public void setCreated_by(BigDecimal created_by) {
        this.created_by = created_by;
    }

    public BigDecimal getLast_update_login() {
        return last_update_login;
    }

    public void setLast_update_login(BigDecimal last_update_login) {
        this.last_update_login = last_update_login;
    }

    public SpotCheckEO(BigDecimal spotCheckId, BigDecimal organizationId, BigDecimal inventory_item_id,
            String item_code, String item_description, String subinventoryCode, BigDecimal locatorId, String locator,
            String misplcItemCode, String misplcItemDescription, BigDecimal system_stock, String attribute_category,
            String attribute1, String attribute2, String attribute3, String attribute4, String attribute5,
            String attribute6, String attribute7, String attribute8, String attribute9, String attribute10,
            String attribute11, String attribute12, String attribute13, String attribute14, String attribute15,
            Date last_update_date, BigDecimal last_updated_by, Date creation_date, BigDecimal created_by,
            BigDecimal last_update_login) {
        this.spotCheckId = spotCheckId;
        this.organizationId = organizationId;
        this.inventory_item_id = inventory_item_id;
        this.item_code = item_code;
        this.item_description = item_description;
        this.subinventoryCode = subinventoryCode;
        this.locatorId = locatorId;
        this.locator = locator;
        this.misplcItemCode = misplcItemCode;
        this.misplcItemDescription = misplcItemDescription;
        this.system_stock = system_stock;
        this.attribute_category = attribute_category;
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
        this.last_update_date = last_update_date;
        this.last_updated_by = last_updated_by;
        this.creation_date = creation_date;
        this.created_by = created_by;
        this.last_update_login = last_update_login;
    }

    




    
}