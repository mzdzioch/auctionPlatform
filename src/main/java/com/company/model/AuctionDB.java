package com.company.model;

import com.company.controller.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuctionDB {


    public AuctionDB(
            String title,
            BigDecimal price,
            int categoryId,
            String description,
            int user_id) {

        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.makeDatabaseConnection();
        String sql = "INSERT INTO auctions (is_active, title, price, category_id, description, user_id) " +
                "VALUES('true', '" + title + "', '" + price + "', '" + categoryId + "', '" + description + "', '" + user_id + "');";
        System.out.println("Generated SQL for adding auction: " + sql);
        databaseConnector.executeInsertStatement(connection, sql);
        databaseConnector.closeConnection(connection);
    }

    // should replace other AuctionDB constructors just after cleaning "files driven" code
    public AuctionDB(
            Connection connection,
            String title,
            BigDecimal price,
            int categoryId,
            String description,
            int user_id) {

        DatabaseConnector databaseConnector = new DatabaseConnector(connection);
        String sql = "INSERT INTO auctions (is_active, title, price, category_id, description, user_id) " +
                "VALUES('true', '" + title + "', '" + price + "', '" + categoryId + "', '" + description + "', '" + user_id + "');";
        System.out.println("Generated SQL for adding auction: " + sql);
        databaseConnector.executeInsertStatement(connection, sql);
        databaseConnector.closeConnection(connection);
    }

    public Auction getAuctionById(Connection connection, int auctionId) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        //Connection connection = databaseConnector.makeDatabaseConnection();

        int id;
        boolean isActive;
        String title;
        BigDecimal price;
        int categoryId;
        String description;
        int userId;

        Statement statement;
        String sql = "SELECT * FROM auctions WHERE id = '"+ auctionId +"';";
        System.out.println("Get auction by ID SQL: " + sql);
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            String result = "";
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


    /*public ArrayList<String> executeSelectStatementFromUsersTable(Connection connection, String sql) {
        Statement statement;
        String result="";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<String> resultToArrayList= new ArrayList<>();
            try {
                while (resultSet.next()) {
                    //int id = resultSet.getInt("id");
                    String id = resultSet.getString("id");

                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    System.out.println("id " + id);
                    System.out.println("login " + login);
                    System.out.println("password " + password);
                    String resultLine = id + "|"+login+"|"+password;
                    System.out.println("resultLine= " + resultLine);
                    resultToArrayList.add(resultLine);

                    //System.out.println(resultSet.toString());
                }
                resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet.close();
            statement.close();
            System.out.println("SQL is working " + sql);
            return resultToArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

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