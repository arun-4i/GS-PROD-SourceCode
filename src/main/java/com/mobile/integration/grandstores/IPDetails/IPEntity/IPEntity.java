package com.mobile.integration.grandstores.IPDetails.IPEntity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "XXGS_MOB_IP_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPEntity {

    @Id
    private BigDecimal id;

    @Column(name = "IP")
    private String ip;

    @Column(name = "PORT")
    private String port;

    @Column(name = "SID")
    private String sid;
    
    @Column(name = "CREATION_DATE")
    private Date Creation_date;

    @Column(name = "LAST_UPDATED_DATE")
    private Date last_updated_date;

    @Column(name = "INSTANCE")
    private String instance;


}
