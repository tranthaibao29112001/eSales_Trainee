package com.hqsoft.esales.trainee;

import java.io.Serializable;

public class Salesperson implements Serializable {
    private String slsperID;
    private String password;
    private String fullName;
    private String address;

    public Salesperson(String slsperID, String password, String fullName, String address) {
        this.slsperID = slsperID;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
    }

    public String getSlsperID() {
        return slsperID;
    }

    public void setSlsperID(String slsperID) {
        this.slsperID = slsperID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
