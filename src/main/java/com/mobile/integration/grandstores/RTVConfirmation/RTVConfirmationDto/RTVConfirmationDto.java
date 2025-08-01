package com.mobile.integration.grandstores.RTVConfirmation.RTVConfirmationDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RTVConfirmationDto {
    @JsonProperty("ITEM_CODE")
    private String itemCode;

    @JsonProperty("ITEM_ID")
    private Long itemId;

    @JsonProperty("LINE_ID")
    private Integer lineId;

    @JsonProperty("LOCATOR_ID")
    private Long locatorId;

    @JsonProperty("ONHAND_QTY")
    private Integer onhandQty;

    @JsonProperty("ORGANIZATION_ID")
    private Integer organizationId;

    @JsonProperty("PICKED_BY")
    private String pickedBy;

    @JsonProperty("PICKED_QTY")
    private Integer pickedQty;

    @JsonProperty("PICK_LOCATOR")
    private String pickLocator;

    @JsonProperty("REQUEST_ID")
    private Integer requestId;

    @JsonProperty("REQUIRED_QTY")
    private Integer requiredQty;

    @JsonProperty("SUBINVENTORY")
    private String subinventory;

    @JsonProperty("UOM")
    private String uom;

    // Getters and Setters
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }

    public Integer getOnhandQty() {
        return onhandQty;
    }

    public void setOnhandQty(Integer onhandQty) {
        this.onhandQty = onhandQty;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getPickedBy() {
        return pickedBy;
    }

    public void setPickedBy(String pickedBy) {
        this.pickedBy = pickedBy;
    }

    public Integer getPickedQty() {
        return pickedQty;
    }

    public void setPickedQty(Integer pickedQty) {
        this.pickedQty = pickedQty;
    }

    public String getPickLocator() {
        return pickLocator;
    }

    public void setPickLocator(String pickLocator) {
        this.pickLocator = pickLocator;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(Integer requiredQty) {
        this.requiredQty = requiredQty;
    }

    public String getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(String subinventory) {
        this.subinventory = subinventory;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    @Override
    public String toString() {
        return "RTVConfirmationDto [itemCode=" + itemCode + ", itemId=" + itemId + ", lineId=" + lineId + ", locatorId="
                + locatorId + ", onhandQty=" + onhandQty + ", organizationId=" + organizationId + ", pickedBy="
                + pickedBy + ", pickedQty=" + pickedQty + ", pickLocator=" + pickLocator + ", requestId=" + requestId
                + ", requiredQty=" + requiredQty + ", subinventory=" + subinventory + ", uom=" + uom + "]";
    }

    
}
