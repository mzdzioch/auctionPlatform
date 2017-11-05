package com.company.model;

public class Bid {

    private String user;
    private double bidPrice;

    public Bid (String user, double bidPrice) {
        this.user = user;
        this.bidPrice = bidPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }
}
