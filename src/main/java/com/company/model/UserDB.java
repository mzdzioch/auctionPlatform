package com.company.model;

import com.company.controller.DatabaseConnector;
import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginNullException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "INSERT INTO users (login, password, salt) VALUES(?, crypt(?), ?)";



        /*
        # select gen_salt('bf');
        gen_salt

        insert into users(login, password, salt) values('cośtam', crypt('hasło', 'salt'), 'salt');
        select * from users where login = 'cośtam' and password = crypt('hasło_podane_przy_logowaniu', salt)
        */

        DatabaseConnector databaseConnector = new DatabaseConnector();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2,password);
            statement.setString(3, "gen_salt('bf')");
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

