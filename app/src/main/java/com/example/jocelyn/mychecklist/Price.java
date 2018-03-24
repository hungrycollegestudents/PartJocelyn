package com.example.jocelyn.mychecklist;

public class Price {

    private double amount;
    private Location location;

    public Price(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}