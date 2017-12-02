package com.company.controller;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {

    Connection connection;
    Statement statement;

    public DatabaseConnector(Connection connection) {
        this.connection = connection;
    }

    public DatabaseConnector() {
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


    public ArrayList<String> executeSelectAllUsers(Connection connection, String sql) {
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


    public Integer getUserIdByLogin(Connection connection, String login) {

        String sql = "SELECT id FROM users WHERE login = '" + login + "';";
        System.out.println("Generated SQL for extracting user's id by login: " + sql);

        int id;

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                System.out.println("Id of requested user: " + id);

                resultSet.close();
                statement.close();
                return id;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkUserPassword(Connection connection, String login, String password) {

        PreparedStatement preparedStatement;

        String sql = "SELECT * FROM users WHERE login = ? and password = crypt(? , salt);";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                System.out.println("Login or pass does not match.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
