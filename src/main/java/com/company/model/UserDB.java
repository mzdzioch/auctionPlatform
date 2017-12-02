package com.company.model;

import com.company.controller.DatabaseConnector;
import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginNullException;

import java.sql.*;

public class UserDB {
    int userID;
    private String login;
    private String password;
    Connection connection;

    public UserDB(Connection connection, String login, String password) throws CredentialsToShortException, LoginNullException {
        validateLoginAndPassword(login, password);
        this.login = login;
        this.password = password;
        PreparedStatement statement = null;
        //String sql = "INSERT INTO users (login, password) VALUES(?, ?);";
        String sql = "INSERT INTO users (login, password, salt) VALUES(?, crypt(?, ?), ?)";

        ResultSet rs=null;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        String salt="";

        try {
            Statement generateSalt;
            generateSalt = connection.createStatement();
            rs = generateSalt.executeQuery("select gen_salt('bf')");

            if (rs.next()) {
                salt = rs.getString("gen_salt");
                System.out.println("my salt is" + salt);
                System.out.println("INSERT INTO users (login, password, salt) VALUES(" + login +", crypt(" + password +" , " + salt  + ")," + salt + ")");

            }
            generateSalt.close();

            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2,password);
            statement.setString(3, salt);
            statement.setString(4, salt);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    private void validateLoginAndPassword(String login, String password) throws LoginNullException, CredentialsToShortException {
        if (login == null) {
            throw new LoginNullException("Login can't be empty");
        }

        if (password == null) {
            throw new LoginNullException("Login can't be empty");
        }

        if (login.length() < 5) {
            throw new CredentialsToShortException("Login is too short");
        }


        if (password.length() < 5) {
            throw new CredentialsToShortException("Password is too short");
        }
    }

}

