package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public enum State {START, DURING_LOGIN, LOGGED_IN, DURING_REGISTRATION, EXIT}
    private static int  categoryNumber = 10;

    public static void main(String[] args) {
        Node<Category> rootCategory = new Node<Category>(null, new Category());
        Node<Category> electronicsCategory = new Node<Category>(rootCategory, new Category(1, "Elektronika"));
        rootCategory.addChild(electronicsCategory);
        State state = State.START;
        int categoryNumber = 10;


        Auction auction0 = new Auction("Title", 20.00, 5, "Description", "romek");
        Auction auction1 = new Auction("Title1", 20.00, 5, "Description1", "romek");

        System.out.println("auction 0 - id " + auction0.getAuctionID());
        System.out.println("auction 1 - id " + auction1.getAuctionID());


        AuctionsRegistry auctionsRegistry = new AuctionsRegistry("testowo.txt");
        auctionsRegistry.writeAuction(auction0);
        auctionsRegistry.writeAuction(auction1);
        System.out.println(auctionsRegistry.getListOfAuctions().get(14).categoryID);


        Scanner input = new Scanner(System.in);
        UserRegistry userRegistry = new UserRegistry("users.txt");

        CategoryBuilder categoryBuilder = new CategoryBuilder();

        while(state != State.EXIT) {
            switch(state) {
                case START:
                    state = printStartScreen(input, userRegistry);
                    break;
                case DURING_LOGIN:
                    state = printLoginScreen(input, userRegistry);
                    break;
                case DURING_REGISTRATION:
                    state = printRegistrationScreen(input, userRegistry);
                    break;
                case LOGGED_IN:
                    state = printLoggedInScreen(input, userRegistry);
                    break;
            }
        }
        System.out.println("Bye");
        input.close();
    }

    private static State printLoggedInScreen(Scanner input, UserRegistry userRegistry) {
        System.out.println("[3] display auctions category tree");
        System.out.println("[4] display your auctions");
        System.out.println("[5] create auction");
        System.out.println("[6] make a bid");
        System.out.println("[category number] to display auctions in this category");
        System.out.println("[0] Exit ");
        int numberEntered = input.nextInt();
        switch (numberEntered) {
            case 0: return State.EXIT;
            case 3:
                displayAuctionsCategoryTree();
                return State.LOGGED_IN;
            case 4:
                displayUsersAuctions();// user????
                return State.LOGGED_IN;
            case 5:
                CreateAuction();
                return State.LOGGED_IN;
            case 6:
                MakeBid();
                return State.LOGGED_IN;
            default:
                if (numberEntered>6 && numberEntered < categoryNumber) {
                    displayCategoryAuctions(numberEntered);
                } else {
                    System.out.println("Sorry mate, no such category");
                }
                return State.LOGGED_IN;
        }
    }

    private static void displayCategoryAuctions(int numberEntered) {
        System.out.println("- - - - - - - - - - - ");
        System.out.println("Display auctions in category number " + numberEntered);
        System.out.println("- - - - - - - - - - - ");
    }

    private static void MakeBid() {
        System.out.println("- - - - - - - - - - - ");
        System.out.println("Make a bid");
        System.out.println("- - - - - - - - - - - ");
    }

    private static void CreateAuction() {
        System.out.println("- - - - - - - - - - - ");
        displayAuctionsCategoryTree();
        System.out.println("Create new auction");
        System.out.println("- - - - - - - - - - - ");
    }

    private static void displayUsersAuctions() {
        System.out.println("- - - - - - - - - - - ");
        System.out.println("Display all auctions of user");
        System.out.println("- - - - - - - - - - - ");
    }

    private static void displayAuctionsCategoryTree() {
        System.out.println("- - - - - - - - - - - ");
        System.out.println("Display auctions category tree");
        CategoryView categoryView = new CategoryView();
        categoryView.display();
        System.out.println("- - - - - - - - - - - ");
    }

    private static State printRegistrationScreen(Scanner input, UserRegistry userRegistry) {
        System.out.println("create you login: ");
        String login = input.next();
        System.out.println("create your password: ");
        String password = input.next();

        try {
            User user = new User(login, password);
            try {
                if (userRegistry.addUser(user)) {
                    System.out.println("User has been created!");
                } else {
                    System.out.println("I couldn't create user.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (CredentialsToShortException e) {
            e.printStackTrace();
        } catch (LoginNullException e) {
            e.printStackTrace();
            //  } catch (LoginExistException e) {
            e.printStackTrace();
            //  } catch (IOException e) {
            e.printStackTrace();
        }
        return State.START;

    }

    private static State printLoginScreen(Scanner input, UserRegistry userRegistry) {
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
        try {
            if (userRegistry.existUser(user) == true) {
                System.out.println("Welcome " + user.getLogin());
                return State.LOGGED_IN;
            } else {
                LoginExistException exception = new LoginExistException("login not found in database");
                System.out.println(exception.getMessage());
//                            System.out.println("type [2] if you want to create new account\n" +
//                                    "type [0] if you want to exit\n");
//                            firstLoginOption = input.nextInt();
                return State.START;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return State.START;

/*        System.out.println("type [2] if you want to create new account\n" +
                "type [0] if you want to exit\n");
        firstLoginOption = input.nextInt();*/

    }

    private static State printStartScreen(Scanner input, UserRegistry userRegistry) {
        System.out.println("Welcome to Allegro auction portal!");
        System.out.println("type [1] if you want to log in\n" +
                "type [2] if you want to create new account\n" +
                "type [0] anytime you want to exit\n");
        int numberEntered = input.nextInt();
        switch (numberEntered) {
            case 0: return State.EXIT;
            case 1: return State.DURING_LOGIN;
            case 2: return State.DURING_REGISTRATION;
            default:
                System.out.println("Sorry mate, wrong number");
                return State.START;
        }
    }
}
