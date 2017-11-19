package com.company.model;

import java.math.BigDecimal;

public class Bid {

    private String user;
    private BigDecimal bidPrice;

    public Bid (String user, BigDecimal bidPrice) {
        this.user = user;
        this.bidPrice = bidPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }
}
