package com.company;

import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginExistException;
import com.company.exceptions.LoginNullException;
import com.company.model.Auction;
import com.company.model.Category;
import com.company.model.Node;
import com.company.model.User;
import com.company.repository.AuctionsRegistry;
import com.company.service.AuctionService;
import com.company.service.CategoryBuilder;
import com.company.service.CategoryView;
import com.company.service.UserRegistry;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public enum State {
        START,
        DURING_LOGIN,
        LOGGED_IN,
        DURING_REGISTRATION,
        DURING_ADDING_AUCTION,
        DURING_DELETING_AUCTION,
        DURING_DISPLAYING_FINISHED_AUCTIONS,
        EXIT}

    private static User currentUser;
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
       // System.out.println(auctionsRegistry.getListOfAuctions().get(14).categoryID);


        Scanner input = new Scanner(System.in);
        UserRegistry userRegistry = new UserRegistry("users.txt");

        CategoryBuilder categoryBuilder = new CategoryBuilder();
        User user = null;

        try {
            user = new User("fakeUser", "fakePassword");
        } catch (CredentialsToShortException e) {
            e.printStackTrace();
        } catch (LoginNullException e) {
            e.printStackTrace();
        }

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
                    state = printLoggedInScreen(input, userRegistry, currentUser);
                    break;
                case DURING_ADDING_AUCTION:
                    state = printAddAuctionScreen(input, userRegistry, auctionsRegistry);
                    break;
                case DURING_DELETING_AUCTION:
                    state = printDeleteAuctionScreen(input, userRegistry, auctionsRegistry);
                    break;
                case DURING_DISPLAYING_FINISHED_AUCTIONS:
                    state = printFinishedAuctionScreen(input, userRegistry, auctionsRegistry);
            }
        }
        System.out.println("Bye");
        input.close();
    }

    private static State printFinishedAuctionScreen(Scanner input, UserRegistry userRegistry, AuctionsRegistry auctionsRegistry) {
        return State.LOGGED_IN;
    }

    private static State printDeleteAuctionScreen(Scanner input, UserRegistry userRegistry, AuctionsRegistry auctionsRegistry) {
        AuctionService auctionService = new AuctionService(auctionsRegistry);
        auctionService.printAuctions(currentUser);
        System.out.println("Enter id number of auction you wish to delete.");
        int auctionIdToDelete = Integer.parseInt(input.next());
        auctionService.removeAuction(auctionIdToDelete);
        System.out.println("Your auction " + auctionIdToDelete + " was deleted.");
        return State.LOGGED_IN;
    }

    private static State printAddAuctionScreen(Scanner input, UserRegistry userRegistry, AuctionsRegistry auctionsRegistry) {
        System.out.println("Select category ID for your new auction");
        int categoryNumber = Integer.parseInt(input.next());
        System.out.println("Enter auction title");
        String auctionTitle = input.next();
        System.out.println("Enter description");
        String auctionDescription = input.next();
        System.out.println("Enter price");
        Double auctionPrice = Double.parseDouble(input.next());
        AuctionService auctionService = new AuctionService(auctionsRegistry);
        auctionService.addAuction(auctionTitle, auctionPrice, categoryNumber, auctionDescription, currentUser.getLogin());
        System.out.println("Your auction was added.");
        return State.LOGGED_IN;
    }


    private static State printLoggedInScreen(Scanner input, UserRegistry userRegistry, User user) {
        System.out.println("[3] add auction");
        System.out.println("[4] display your auctions");
        System.out.println("[5] delete auction");
        System.out.println("[6] make a bid");
        System.out.println("[category number] to display auctions in this category");
        System.out.println("[0] Exit ");
        int numberEntered = input.nextInt();
        switch (numberEntered) {
            case 0: return State.EXIT;
            case 3:
                displayAuctionsCategoryTree();
                return State.DURING_ADDING_AUCTION;
            case 4:
                displayUsersAuctions(user);// user????
                return State.LOGGED_IN;
            case 5:
                return State.DURING_DELETING_AUCTION;
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

    private static void displayUsersAuctions(User user) {
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
            userRegistry.addUser(user);
            System.out.println("User has been created!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LoginExistException e) {
                e.printStackTrace();
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
            if (userRegistry.existUser(user) == true) {
                System.out.println("Welcome " + user.getLogin());
                currentUser = user;
                return State.LOGGED_IN;
            } else {
                LoginExistException exception = new LoginExistException("login not found in database");
                System.out.println(exception.getMessage());
//                            System.out.println("type [2] if you want to create new account\n" +
//                                    "type [0] if you want to exit\n");
//                            firstLoginOption = input.nextInt();
                return State.START;
            }
        } catch (CredentialsToShortException e) {
            e.printStackTrace();
        } catch (LoginNullException e) {
            e.printStackTrace();
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