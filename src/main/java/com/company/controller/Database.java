package com.company.controller;

import java.sql.*;

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

    public ResultSet executeSelectStatement(Connection connection, String sql) {
        Statement statement;
        String result="";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.close();
            statement.close();
            System.out.println("SQL is working " + sql);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
