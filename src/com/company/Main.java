package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Allegro auction portal!");
        System.out.println("type [1] if you want to log in\n" +
                "type [2] if you want to create new account\n" +
                "type [0] anytime you want to exit\n");
        Scanner input = new Scanner(System.in);
        int firstLoginOption = input.nextInt();
        do {
            switch (firstLoginOption) {
                case 1: {
                    System.out.println("enter you login: ");
                    String login = input.next();
                    System.out.println("enter your password: ");
                    String password = input.next();
                    User user = null;
                    try {
                        user = new User(login, password);
                    } catch (CredentialsToShortException e) {
                        e.printStackTrace();
                    } catch (LoginNullException e) {
                        e.printStackTrace();
                    }
                    UserRegistry userRegistry = new UserRegistry("users.txt");
                    try {
                        if (userRegistry.existUser(user) == true) {
                            System.out.println("Welcome " + user.getLogin());
                        } else {
                            LoginExistException exception = new LoginExistException("login not found in database");
                            System.out.println(exception.getMessage());
                            System.out.println("type [2] if you want to create new account\n" +
                                    "type [0] if you want to exit\n");
                            firstLoginOption = input.nextInt();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    System.out.println("create you login: ");
                    String login = input.next();
                    System.out.println("create your password: ");
                    String password = input.next();
                    try {
                        User user = new User(login, password);
                    } catch (CredentialsToShortException e) {
                        e.printStackTrace();
                    } catch (LoginNullException e) {
                        e.printStackTrace();
                    }
                    System.out.println("type [1] if you want to log in\n" +
                            "type [0] if you want to exit\n");
                    firstLoginOption = input.nextInt();
                    break;
                }
                case 0: {
                    System.out.println("Bye!");
                    break;
                }
                default:
                    System.out.println("incorrect option chosen");
            }
        } while (firstLoginOption != 0);
        System.out.println("Bye!");
        input.close();
    }
}
