package com.company.exceptions;

public class LoginExistException extends Exception {
    public LoginExistException(String message){
        super(message);
    }
}
