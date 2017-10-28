package com.company;

public class Auction {


    static int auctionID;
    String title;
    double price;
    int categoryID;
    String description;

    public Auction(String title, double price, int categoryID, String description) {
        this.title = title;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
    }


    public static int getAuctionID() {
        return auctionID;
    }

    public static void setAuctionID(int auctionID) {
        Auction.auctionID = auctionID;
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
}
