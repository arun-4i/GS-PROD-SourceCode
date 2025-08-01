package com.mobile.integration.grandstores.BinTransferTrack.BinTransferTrackEntity;

public class QuickDropEO {

    private Long id;
    private Iterable<BinTransferTrackHdrEO> header;
    private Iterable<BinTransferTrackPickEO> pick;
    private Iterable<BinTransferTrackDropEO> drop;
    public QuickDropEO(Long id, Iterable<BinTransferTrackHdrEO> header, Iterable<BinTransferTrackPickEO> pick,
            Iterable<BinTransferTrackDropEO> drop) {
        this.id = id;
        this.header = header;
        this.pick = pick;
        this.drop = drop;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Iterable<BinTransferTrackHdrEO> getHeader() {
        return header;
    }
    public void setHeader(Iterable<BinTransferTrackHdrEO> header) {
        this.header = header;
    }
    public Iterable<BinTransferTrackPickEO> getPick() {
        return pick;
    }
    public void setPick(Iterable<BinTransferTrackPickEO> pick) {
        this.pick = pick;
    }
    public Iterable<BinTransferTrackDropEO> getDrop() {
        return drop;
    }
    public void setDrop(Iterable<BinTransferTrackDropEO> drop) {
        this.drop = drop;
    }

    
    
}
