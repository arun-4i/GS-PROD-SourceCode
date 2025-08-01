package com.mobile.integration.grandstores.Entity;
import java.math.BigDecimal;
// import java.util.Date;
// import java.sql.Date;

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
@Table(name = "XXGS_PI_DOCUMENT_V")
public class DocumentNumberEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poid_seq")
    @SequenceGenerator(name = "poid_seq",sequenceName = "XXGS_PO_CONF_ID_S", allocationSize = 1)
    @Column(name = "HEADER_ID")
    public BigDecimal headerId;

    @Column(name = "DOCUMENT_NUMBER")
    public BigDecimal documentNumber; 
        
    @Column(name = "SHIPMENT_REF")
    public String shipmentRef;

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

    public String getShipmentRef() {
        return shipmentRef;
    }

    public void setShipmentRef(String shipmentRef) {
        this.shipmentRef = shipmentRef;
    }

    public DocumentNumberEO(BigDecimal headerId, BigDecimal documentNumber, String shipmentRef) {
        this.headerId = headerId;
        this.documentNumber = documentNumber;
        this.shipmentRef = shipmentRef;
    }

    public DocumentNumberEO() {
    }                       




    
}