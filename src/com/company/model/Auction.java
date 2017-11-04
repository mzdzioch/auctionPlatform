package com.company.model;

import com.company.helpers.AuctionsCounter;

public class Auction {


    private final int auctionID;
    String title;
    double price;
    int categoryID;
    String description;
    String login;

    public Auction(int auctionID, String title, double price, int categoryID, String description, String login) {
        this.auctionID = auctionID;
        this.title = title;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.login = login;
    }

    public Auction(String title, double price, int categoryID, String description, String login) {
        AuctionsCounter auctionsCounter = new AuctionsCounter("acntr.txt");
        this.auctionID = auctionsCounter.readCurrentID()+1;
        this.title = title;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.login = login;

        auctionsCounter.writeCurrentID(this.auctionID);
    }


    public int getAuctionID() {
        return auctionID;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
    @Override
    public String toString() {
        return "id: " + auctionID + " | " + title + " | " + price + " | " + categoryID + " | " + description ;
    }

//    public String toString() {
//        return "Auction:   " +
//                "id: " + auctionID + " \\| " + "title: " + title + " \\| " + "price: " + price + " \\| " + "categoryID: " + categoryID
//                + " \\| " + "description: " + description + " \\| ";
//    }
}
