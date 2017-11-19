package com.company.model;

import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginNullException;

public class User {

    private String login;
    private String password;

    public User(String login, String password) throws CredentialsToShortException, LoginNullException {
        validateLoginAndPassword(login, password);
        this.login = login;
        this.password = password;
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






/*
package com.company;

import javax.security.auth.login.LoginException;

public class User {

    private String login;
    private String password;


    public User(String login, String password) throws CredentialsToShortException, LoginNullException {
        this.login = login;
        this.password = password;


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
*/

