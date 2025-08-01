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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "XXGS_GET_PI_SUPPLIERS_V")
public class PiSupplierEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poid_seq")
    @SequenceGenerator(name = "poid_seq",sequenceName = "XXGS_PO_CONF_ID_S", allocationSize = 1)
    @Column(name = "SUPPLIER_ID")
    private BigDecimal supplierId;

    @Column(name = "SUPPLIER_NUMBER")
    private String supplierNumber;

    @Column(name = "SUPPLIER_NAME")
    private String supplierName;
    
}