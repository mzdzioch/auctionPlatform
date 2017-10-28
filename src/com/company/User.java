package com.company;

import javax.security.auth.login.LoginException;

public class User {

    private String login;
    private String password;


    public User(String login, String password) throws CredentialsToShortException, LoginNullException {
        this.login = login;
        this.password = password;

        if (login.length() < 5) {
            throw new CredentialsToShortException("Login is too short");
        } else System.out.println("Login successful");


        if (password.length() < 5) {
            throw new CredentialsToShortException("Password is too short");
        } else System.out.println("Password set successfully");


        if (login == null) {
            throw new LoginNullException("Login can't be empty");
        } else System.out.println("Login set successfully");

    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
