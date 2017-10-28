package com.company;

public class Auction {
    

    private int auctionID = 0;
    static int count = 0;
    String title;
    double price;
    int categoryID;
    String description;


    public Auction(int auctionID, String title, double price, int categoryID, String description) {
        this.auctionID = auctionID;
        this.title = title;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;

        auctionID = count++;
    }


    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
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
