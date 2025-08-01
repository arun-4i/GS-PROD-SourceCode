package com.mobile.integration.grandstores.RMAConfirm.RMAConfirmEntity;

import com.mobile.integration.grandstores.MoConfirm.MoConfirmEntity.PickConfirmEO;

public class RMAConfirmMO {

    private Long id;
    private Iterable<RMAConfirmEO> rmaConfirm;
    private Iterable<PickConfirmEO> pickConfirm;
    public RMAConfirmMO(Long id, Iterable<RMAConfirmEO> rmaConfirm, Iterable<PickConfirmEO> pickConfirm) {
        this.id = id;
        this.rmaConfirm = rmaConfirm;
        this.pickConfirm = pickConfirm;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Iterable<RMAConfirmEO> getRmaConfirm() {
        return rmaConfirm;
    }
    public void setRmaConfirm(Iterable<RMAConfirmEO> rmaConfirm) {
        this.rmaConfirm = rmaConfirm;
    }
    public Iterable<PickConfirmEO> getPickConfirm() {
        return pickConfirm;
    }
    public void setPickConfirm(Iterable<PickConfirmEO> pickConfirm) {
        this.pickConfirm = pickConfirm;
    }

    
    
}
