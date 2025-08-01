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
@Table(name = "XXGS_GET_PO_NUMBER_V")
public class PoNumberEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poid_seq")
    @SequenceGenerator(name = "poid_seq",sequenceName = "XXGS_PO_CONF_ID_S", allocationSize = 1)
    @Column(name = "PO_HEADER_ID")
    private BigDecimal poheaderId;

    @Column(name = "PO_NUMBER")
    private String poNumber;

    @Column(name = "TYPE_LOOKUP_CODE")
    private String typeLookupCode;

    @Column(name = "VENDOR_ID")
    private BigDecimal vendorId;

    @Column(name = "VENDOR_NUMBER")
    private String vendorNumber;

    @Column(name = "VENDOR_NAME")
    private String vendorName;

    @Column(name = "VENDOR_SITE_ID")
    private BigDecimal vendorSiteId;

    @Column(name = "VENDOR_SITE_CODE")
    private String vendorSiteCode;

    
}