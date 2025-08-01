package com.mobile.integration.grandstores.InvCountConfirm.InvCountConfirmEntity;

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
@Table(name = "xxgs_inv_count_confirmations")
public class InvCountConfirmEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inv_count_seq")
    @SequenceGenerator(name = "inv_count_seq", sequenceName = "XXGS_INV_COUNT_ID_S", allocationSize = 1)
    @Column(name = "INV_COUNT_ID")
    public BigDecimal inv_count_id;

    @Column(name = "PHYSICAL_INV_NAME")
    public String physical_inv_name;
    @Column(name = "PHYSICAL_INV_ID")
    public BigDecimal physical_inv_id;
    @Column(name = "SUB_INVENTORY")
    public String sub_inventory;
    @Column(name = "LOCATORS")
    public String locators;
    @Column(name = "LOCATORS_ID")
    public BigDecimal locators_id;
    @Column(name = "TAG_NUMBER")
    public String tag_number;
    @Column(name = "TAG_ID")
    public BigDecimal tag_id;
    @Column(name = "ITEM_CODE")
    public String item_code;
    @Column(name = "ITEM_ID")
    public BigDecimal item_id;
    @Column(name = "ORGANIZATION_ID")
    public BigDecimal organization_id;
    @Column(name = "ORGANIZATION_CODE")
    public String organization_code;
    @Column(name = "STATUS")
    public String status;
    @Column(name = "UOM")
    public String uom;
    @Column(name = "LOT_NUMBER")
    public String lot_number;
    @Column(name = "LOT_EXPIRY")
    public Date lot_expires;
    @Column(name = "CURRENT_ON_HAND")
    public BigDecimal current_on_hand;
    @Column(name = "COUNT_QTY")
    public BigDecimal count_qty;
    @Column(name = "APPROVED_VARIANCE")
    public BigDecimal approved_variance;
    @Column(name = "ON_HAND_VARIANCE")
    public BigDecimal on_hand_variance;
    @Column(name = "ADJUST_VALUE")
    public BigDecimal adjust_value;
    @Column(name = "ERROR_MSG")
    public String error_msg;
    @Column(name = "PARENT_FLAG")
    public String parent_flag;
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

    public InvCountConfirmEO() {
    }

    public InvCountConfirmEO(BigDecimal inv_count_id, String physical_inv_name, BigDecimal physical_inv_id,
            String sub_inventory, String locators, BigDecimal locators_id, String tag_number, BigDecimal tag_id,
            String item_code, BigDecimal item_id, BigDecimal organization_id, String organization_code, String status,
            String uom, String lot_number, Date lot_expires, BigDecimal current_on_hand, BigDecimal count_qty,
            BigDecimal approved_variance, BigDecimal on_hand_variance, BigDecimal adjust_value, String error_msg,
            String parent_flag, String attribute_category, String attribute1, String attribute2, String attribute3,
            String attribute4, String attribute5, String attribute6, String attribute7, String attribute8,
            String attribute9, String attribute10, String attribute11, String attribute12, String attribute13,
            String attribute14, String attribute15, Date last_update_date, BigDecimal last_updated_by,
            Date creation_date, BigDecimal created_by, BigDecimal last_update_login) {
        this.inv_count_id = inv_count_id;
        this.physical_inv_name = physical_inv_name;
        this.physical_inv_id = physical_inv_id;
        this.sub_inventory = sub_inventory;
        this.locators = locators;
        this.locators_id = locators_id;
        this.tag_number = tag_number;
        this.tag_id = tag_id;
        this.item_code = item_code;
        this.item_id = item_id;
        this.organization_id = organization_id;
        this.organization_code = organization_code;
        this.status = status;
        this.uom = uom;
        this.lot_number = lot_number;
        this.lot_expires = lot_expires;
        this.current_on_hand = current_on_hand;
        this.count_qty = count_qty;
        this.approved_variance = approved_variance;
        this.on_hand_variance = on_hand_variance;
        this.adjust_value = adjust_value;
        this.error_msg = error_msg;
        this.parent_flag = parent_flag;
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

    public void setInv_count_id(BigDecimal inv_count_id) {
        this.inv_count_id = inv_count_id;
    }

    public void setPhysical_inv_name(String physical_inv_name) {
        this.physical_inv_name = physical_inv_name;
    }

    public void setPhysical_inv_id(BigDecimal physical_inv_id) {
        this.physical_inv_id = physical_inv_id;
    }

    public void setSub_inventory(String sub_inventory) {
        this.sub_inventory = sub_inventory;
    }

    public void setLocators(String locators) {
        this.locators = locators;
    }

    public void setLocators_id(BigDecimal locators_id) {
        this.locators_id = locators_id;
    }

    public void setTag_number(String tag_number) {
        this.tag_number = tag_number;
    }

    public void setTag_id(BigDecimal tag_id) {
        this.tag_id = tag_id;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public void setItem_id(BigDecimal item_id) {
        this.item_id = item_id;
    }

    public void setOrganization_id(BigDecimal organization_id) {
        this.organization_id = organization_id;
    }

    public void setOrganization_code(String organization_code) {
        this.organization_code = organization_code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public void setLot_number(String lot_number) {
        this.lot_number = lot_number;
    }

    public void setLot_expires(Date lot_expires) {
        this.lot_expires = lot_expires;
    }

    public void setCurrent_on_hand(BigDecimal current_on_hand) {
        this.current_on_hand = current_on_hand;
    }

    public void setCount_qty(BigDecimal count_qty) {
        this.count_qty = count_qty;
    }

    public void setApproved_variance(BigDecimal approved_variance) {
        this.approved_variance = approved_variance;
    }

    public void setOn_hand_variance(BigDecimal on_hand_variance) {
        this.on_hand_variance = on_hand_variance;
    }

    public void setAdjust_value(BigDecimal adjust_value) {
        this.adjust_value = adjust_value;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public void setParent_flag(String parent_flag) {
        this.parent_flag = parent_flag;
    }

    public void setAttribute_category(String attribute_category) {
        this.attribute_category = attribute_category;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLast_updated_by(BigDecimal last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public void setCreated_by(BigDecimal created_by) {
        this.created_by = created_by;
    }

    public void setLast_update_login(BigDecimal last_update_login) {
        this.last_update_login = last_update_login;
    }






    
}
