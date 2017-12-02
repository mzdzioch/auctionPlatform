package com.company.model;

import com.company.controller.Database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuctionDB {

    public AuctionDB(
            String title,
            BigDecimal price,
            int categoryId,
            String description,
            int user_id) {

        Connection connection = Database.getConnection();

        PreparedStatement statement;
        String sql = "INSERT INTO auctions (is_active, title, price, category_id, description, user_id) " +
                "VALUES('true', ?, ?, ?, ?, ?);";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setBigDecimal(2,price);
            statement.setInt(3, categoryId);
            statement.setString(4, description);
            statement.setInt(5, user_id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Auction getAuctionById(int auctionId) {

        Connection connection = Database.getConnection();

        int id;
        boolean isActive;
        String title;
        BigDecimal price;
        int categoryId;
        String description;
        int userId;

        PreparedStatement statement;
        String sql = "SELECT * FROM auctions WHERE id = ?;";
        System.out.println("Get auction by ID SQL: " + sql);
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, auctionId);

            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    isActive = resultSet.getBoolean("is_active");
                    title = resultSet.getString("title");
                    price = resultSet.getBigDecimal("price");
                    categoryId = resultSet.getInt("category_id");
                    description = resultSet.getString("description");
                    userId = resultSet.getInt("user_id");
                    System.out.println("Id of requested auction: " + auctionId);
                    System.out.println("Extracted from Database");
                    System.out.println("id " + id);
                    System.out.println("isActive " + isActive);
                    System.out.println("title " + title);
                    System.out.println("price " + price);
                    System.out.println("category ID" + categoryId);
                    System.out.println("description " + description);
                    System.out.println("user ID " + userId);
                    System.out.println("-'-'-'-'-'-'-'-'-'-'-");


                    Auction auction = new Auction(id, isActive, title, price, categoryId, description, userId);

                    resultSet.close();
                    statement.close();

                    return auction;

                } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

/*    public int getAuctionID() {
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
    }*/
}