package com.techelevator.model;

public class TaxRate {
    private double rate;

    public double getRate() {
        return rate/100;
    }

    public void setRate(double rate) {
        this.rate = rate/100;
    }
}
