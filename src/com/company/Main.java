package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws LoginExistException {
        // write your code here
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
                    User user = new User(login, password);
                    UserRegistry userRegistry = new UserRegistry();
                    if (userRegistry.existUser(user) == true) {
                        System.out.println("Welcome " + user.login);
                    } else {
                        LoginExistException exception = new LoginExistException("login not found in database");
                        System.out.println(exception.getMessage());
                        System.out.println("type [2] if you want to create new account\n" +
                                "type [0] if you want to exit\n");
                        firstLoginOption = input.nextInt();
                    }
                    break;
                }
                case 2: {
                    System.out.println("create you login: ");
                    String login = input.next();
                    System.out.println("create your password: ");
                    String password = input.next();
                    User user = new User(login, password);
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
