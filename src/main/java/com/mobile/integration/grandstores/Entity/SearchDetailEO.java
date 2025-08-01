package com.mobile.integration.grandstores.Entity;
// import java.util.Date;
// import java.sql.Date;

import java.math.BigDecimal;

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
@Table(name = "XXGS_GET_SEARCH_DETAILS_V")
public class SearchDetailEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poid_seq")
    @SequenceGenerator(name = "poid_seq",sequenceName = "XXGS_PO_CONF_ID_S", allocationSize = 1)
    @Column(name = "DOCUMENT_NUMBER")
    private BigDecimal documentNumber;

    @Column(name = "PI_NUMBER")
    private String piNumber;

    @Column(name = "SHIPMENT_REF")
    private String shipmentRef;

    
    public BigDecimal getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(BigDecimal documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPiNumber() {
        return piNumber;
    }

    public void setPiNumber(String piNumber) {
        this.piNumber = piNumber;
    }

    public String getShipmentRef() {
        return shipmentRef;
    }

    public void setShipmentRef(String shipmentRef) {
        this.shipmentRef = shipmentRef;
    }

    public SearchDetailEO(BigDecimal documentNumber, String piNumber, String shipmentRef) {
        this.documentNumber = documentNumber;
        this.piNumber = piNumber;
        this.shipmentRef = shipmentRef;
    }

    public SearchDetailEO() {
    }


    


}