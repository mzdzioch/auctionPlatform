package com.company;

public class LoginExistException extends Exception {
    public LoginExistException(String message){
        super(message);
    }
}
