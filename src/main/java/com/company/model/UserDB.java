package com.company.model;

import com.company.controller.DatabaseConnector;
import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginNullException;

import java.sql.Connection;

public class UserDB {
    int userID;
    private String login;
    private String password;
    Connection connection;

    public UserDB(Connection connection, String login, String password) throws CredentialsToShortException, LoginNullException {
        validateLoginAndPassword(login, password);
        this.login = login;
        this.password = password;

        DatabaseConnector databaseConnector = new DatabaseConnector();
        //Connection connection = databaseConnector.makeDatabaseConnection();
        String sql = "INSERT INTO users (login, password) " +
                "VALUES('" + login + "', '" + password + "');";
        System.out.println("Generated SQL for adding user: " + sql);
        databaseConnector.executeInsertStatement(connection, sql);
        //databaseConnector.closeConnection(connection);

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

