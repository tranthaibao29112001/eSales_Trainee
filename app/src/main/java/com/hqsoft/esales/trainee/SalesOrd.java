package com.hqsoft.esales.trainee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesOrd {
    private String OrderNbr;
    private String slsperID;
    private String custID;
    private double ordAmt;
    private double ordQty;
    private String orderDate;
    private String remark;

    public SalesOrd() {
    }

    public SalesOrd(String orderNbr, String slsperID, String custID, double ordAmt, double ordQty, String orderDate, String remark) {
        OrderNbr = orderNbr;
        this.slsperID = slsperID;
        this.custID = custID;
        this.ordAmt = ordAmt;
        this.ordQty = ordQty;
        this.orderDate = orderDate;
        this.remark = remark;
    }

    public String getOrderNbr() {
        return OrderNbr;
    }

    public void setOrderNbr(String orderNbr) {
        OrderNbr = orderNbr;
    }

    public String getSlsperID() {
        return slsperID;
    }

    public void setSlsperID(String slsperID) {
        this.slsperID = slsperID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public double getOrdAmt() {
        return ordAmt;
    }

    public void setOrdAmt(double ordAmt) {
        this.ordAmt = ordAmt;
    }

    public double getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(double ordQty) {
        this.ordQty = ordQty;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
