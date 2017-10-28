package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws LoginExistException {
        // write your code here
        System.out.println("Welcome to Allegro auction portal!");
        System.out.println("type 1 if you want to log in\ntype 2 if you want to create new account");
        Scanner input = new Scanner(System.in);
        int firstLoginOption = input.nextInt();
        switch (firstLoginOption) {
            case 1: {
                System.out.println("enter you login: ");
                String login = input.next();
                System.out.println("enter your password: ");
                String password = input.next();
                User user = new User(login, password);
                UserRegistry userRegistry = new UserRegistry();
                if (userRegistry.existUser(user) == true) {
                    System.out.println("Welcome " + user.login);
                } else
                    throw new LoginExistException("login not found in database");
                    break;
            }
            case 2: {
                System.out.println("enter you login: ");
                String login = input.next();
                System.out.println("enter your password: ");
                String password = input.next();
                User user = new User(login, password);
                break;
            }
            default:
                System.out.println("incorrect option chosen");
        }
        input.close();
    }
}
