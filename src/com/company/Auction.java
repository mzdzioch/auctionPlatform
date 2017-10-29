package com.company;

public class Auction {


    private final int auctionID;
    static int count = 0;
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

        auctionID = count++;
    }
//

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
}
