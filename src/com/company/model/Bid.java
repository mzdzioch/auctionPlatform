package com.company.model;

public class Bid {

    private User user;
    private double bidPrice;

    public Bid(User user, double bidPrice) {
        this.user = user;
        this.bidPrice = bidPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }
}
