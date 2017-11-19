package com.company.controller;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    Connection connection;
    Statement statement;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public Database() {
    }

    public boolean executeInsertStatement(Connection connection, String sql) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("SQL is working " + sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("We have a situation with " + sql);
            return false;
        }
    }

    public Connection makeDatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            //return;
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/orgella", "postgres",
                    "123");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            //return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> executeSelectStatementFromUsersTable(Connection connection, String sql) {
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
    }
}
