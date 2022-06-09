package com.hqsoft.esales.trainee;

import java.io.Serializable;

public class Customer implements Serializable {
    private String custID;
    private String name;
    private String address;
    private String phone;

    public Customer(String custID, String name, String address, String phone) {
        this.custID = custID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
