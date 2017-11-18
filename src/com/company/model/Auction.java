package com.company.model;

import com.company.helpers.AuctionsCounter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Auction {

    private final int auctionID;
    private boolean active;
    private String title;
    private BigDecimal price;
    private int categoryID;
    private String description;
    private String login;
    private List<Bid> listBids = new ArrayList<>();


    public Auction(int auctionID, boolean active, String title, BigDecimal price, int categoryID, String description, String login, List<Bid> listBids) {
        this.auctionID = auctionID;
        this.active = active;
        this.title = title;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.login = login;
        this.listBids = listBids;
    }

    public Auction(boolean active, String title, BigDecimal price, int categoryID, String description, String login) {
        AuctionsCounter auctionsCounter = new AuctionsCounter("acntr.txt");
        this.auctionID = auctionsCounter.readCurrentID() + 1;
        this.active = active;
        this.title = title;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.login = login;
        this.listBids = new ArrayList<>() ;

        auctionsCounter.writeCurrentID(this.auctionID);
    }

    public Auction(String tomka, boolean active, String title, BigDecimal price, int categoryID, String description, String login) {
        AuctionsCounter auctionsCounter = new AuctionsCounter(tomka);
        this.auctionID = auctionsCounter.readCurrentID() + 1;
        this.active = active;
        this.title = title;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.login = login;
        this.listBids = new ArrayList<>() ;

        auctionsCounter.writeCurrentID(this.auctionID);
    }


    public int getAuctionID() {
        return auctionID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Bid> getListBids() {
        return listBids;
    }

    public void addBidToList(Bid bid){
        listBids.add(bid);
    }

    private BigDecimal getLastPrice(){
        return getListBids().get(getListBids().size()-1).getBidPrice();
    }

    public boolean validateBid(BigDecimal bidValue) {

        if(!getListBids().isEmpty()) {
            if(bidValue.compareTo(getListBids().get(getListBids().size() - 1).getBidPrice()) == 1)
                return true;
            return false;
        } else if(bidValue.compareTo(getPrice()) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "id: " + auctionID + " | " + title + " | "
                + (getListBids().isEmpty() ? price : getLastPrice())
                + " | " + categoryID + " | " + description;
    }
}