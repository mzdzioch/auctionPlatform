package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Welcome to Allegro auction portal!");
        System.out.println("type 1 if you want to log in\ntype 2 if you want to create new account");
        Scanner input = new Scanner(System.in);
        int firstLoginOption = input.nextInt();
        switch (firstLoginOption){
            case 1: {
                System.out.println("enter you login: ");
                String login = input.next();
                System.out.println("enter your password: ");
                String password = input.next();
                User user = new User(login, password);
                break;
            }
            case 2:{
                System.out.println("enter you login: ");
                String login = input.next();
                System.out.println("enter your password: ");
                String password = input.next();
                User user = new User(login, password);
                break;
            }
        }
        input.close();
    }
}
