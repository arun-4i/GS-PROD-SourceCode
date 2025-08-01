package com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity;
import java.util.*;

public class MoPickConfirmEOT {

    private Long id;
    private Iterable<MoConfirmEO> moConfirms;
    private Iterable<PickConfirmEO> pickConfirmations;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Iterable<MoConfirmEO> getMoConfirms() {
        return moConfirms;
    }
    public void setMoConfirms(Iterable<MoConfirmEO> moConfirms) {
        this.moConfirms = moConfirms;
    }
    public Iterable<PickConfirmEO> getPickConfirmations() {
        return pickConfirmations;
    }
    public void setPickConfirmations(Iterable<PickConfirmEO> pickConfirmations) {
        this.pickConfirmations = pickConfirmations;
    }
    public MoPickConfirmEOT(Long id, Iterable<MoConfirmEO> moConfirms, Iterable<PickConfirmEO> pickConfirmations) {
        this.id = id;
        this.moConfirms = moConfirms;
        this.pickConfirmations = pickConfirmations;
    }
    
}
