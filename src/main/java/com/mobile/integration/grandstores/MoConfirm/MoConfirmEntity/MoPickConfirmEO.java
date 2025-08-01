package com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.*;

public class MoPickConfirmEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<MoConfirmEO> moConfirms;
    private List<PickConfirmEO> pickConfirmations;
    public MoPickConfirmEO(Long id, List<MoConfirmEO> moConfirms, List<PickConfirmEO> pickConfirmations) {
        this.id = id;
        this.moConfirms = moConfirms;
        this.pickConfirmations = pickConfirmations;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<MoConfirmEO> getMoConfirms() {
        return moConfirms;
    }
    public void setMoConfirms(List<MoConfirmEO> moConfirms) {
        this.moConfirms = moConfirms;
    }
    public List<PickConfirmEO> getPickConfirmations() {
        return pickConfirmations;
    }
    public void setPickConfirmations(List<PickConfirmEO> pickConfirmations) {
        this.pickConfirmations = pickConfirmations;
    }
}
