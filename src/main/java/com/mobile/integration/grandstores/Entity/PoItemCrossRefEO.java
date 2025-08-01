package com.mobile.integration.grandstores.Entity;
// import java.util.Date;
import java.sql.Date;

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
@Table(name = "XXGS_GET_PO_ITEM_CROSS_REF_V")
public class PoItemCrossRefEO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poid_seq")
    @SequenceGenerator(name = "poid_seq",sequenceName = "XXGS_PO_CONF_ID_S", allocationSize = 1)
    @Column(name = "PO_HEADER_ID")
    private BigDecimal poheaderId;

    @Column(name = "PO_NUMBER")
    private String poNumber;

    @Column(name = "CREATION_DATE")
    private Date creationDate;
 
    @Column(name = "PO_RELEASE_ID")
    private BigDecimal poReleaseId;

    @Column(name = "RELEASE_NUM")
    private BigDecimal poReleaseNum;
    
    @Column(name = "VENDOR_ID")
    private BigDecimal vendorId;

    @Column(name = "SUPPLIER_NUMBER")
    private String supplierNumber;
    
    @Column(name = "SUPPLIER_NAME")
    private String supplierName;

    @Column(name = "VENDOR_SITE_ID")
    private BigDecimal vendorSiteId;

    @Column(name = "VENDOR_SITE_CODE")
    private String vendorSiteCode;
    
    @Column(name = "VENDOR_SITE_CODE_ALT")
    private String vendorSiteCodeAlt;
    
    @Column(name = "VENDOR_SITE")
    private String vendorSite;
    
    @Column(name = "LINE_NUM")
    private BigDecimal lineNum;
    
    @Column(name = "ITEM_ID")
    private BigDecimal itemId;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "CROSS_REFERENCE")
    private String crossReference;

    @Column(name = "UOM")
    private String uom;    
 
    @Column(name = "QUANTITY")
    private BigDecimal quantity;
    
}