package com.hqsoft.esales.trainee;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Inventory {
    private String invtID;
    private String name;
    private String unit;
    private String price;

    public Inventory(String invtID, String name, String unit, String price) {
        this.invtID = invtID;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Inventory inventory =(Inventory) obj;
        return inventory.getInvtID().equals(this.invtID);
    }

    @Override
    public int hashCode() {
        return invtID.hashCode() + name.hashCode() + price.hashCode();
    }

    public String getInvtID() {
        return invtID;
    }

    public void setInvtID(String invtID) {
        this.invtID = invtID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
