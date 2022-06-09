package com.hqsoft.esales.trainee;

public class SalesOrdDet {
    private String orderNbr;
    private String lineRef;
    private String invtID;
    private double lineAmt;
    private double lineQty;

    public SalesOrdDet(String orderNbr, String lineRef, String invtID, double lineAmt, double lineQty) {
        this.orderNbr = orderNbr;
        this.lineRef = lineRef;
        this.invtID = invtID;
        this.lineAmt = lineAmt;
        this.lineQty = lineQty;
    }

    public String getOrderNbr() {
        return orderNbr;
    }

    public void setOrderNbr(String orderNbr) {
        this.orderNbr = orderNbr;
    }

    public String getLineRef() {
        return lineRef;
    }

    public void setLineRef(String lineRef) {
        this.lineRef = lineRef;
    }

    public String getInvtID() {
        return invtID;
    }

    public void setInvtID(String invtID) {
        this.invtID = invtID;
    }

    public double getLineAmt() {
        return lineAmt;
    }

    public void setLineAmt(double lineAmt) {
        this.lineAmt = lineAmt;
    }

    public double getLineQty() {
        return lineQty;
    }

    public void setLineQty(double lineQty) {
        this.lineQty = lineQty;
    }
}
