package com.company;

import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginExistException;
import com.company.exceptions.LoginNullException;
import com.company.model.Category;
import com.company.model.Node;
import com.company.model.User;
import com.company.service.CategoryBuilder;
import com.company.service.CategoryView;
import com.company.service.UserRegistry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class MainStage extends Application {
    Scene loginScene, appScene;
    private static User currentUser = null;
    String userLoginForMessage = "";



    @Override
    public void start(Stage primaryStage) throws Exception {
        // setting environment - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        int categoryNumber = 10;
        UserRegistry userRegistry = new UserRegistry("users.txt");
        Node<Category> rootCategory = new Node<Category>(null, new Category());
        Node<Category> electronicsCategory = new Node<Category>(rootCategory, new Category(1, "Elektronika"));
        rootCategory.addChild(electronicsCategory);
        CategoryBuilder categoryBuilder = new CategoryBuilder();



        // GUI starts here - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        primaryStage.setTitle("Orgella");


        // loginScene > - > - > - > - > - > - > - > - > - > - > - > - > - > - > - > - >
        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(10));
        loginLayout.setVgap(8);
        loginLayout.setHgap(10);

        Label usernameLabel = new Label("Username");
        GridPane.setConstraints(usernameLabel, 0,0);

        TextField usernameText = new TextField();
        GridPane.setConstraints(usernameText,1,0);

        Label passwordLabel = new Label("Password");
        GridPane.setConstraints(passwordLabel, 0,1);

        TextField passwordText = new TextField();
        GridPane.setConstraints(passwordText,1,1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton,0,3);
        loginButton.setOnAction(e -> {
            User user = null;
            try {
                user = new User(usernameText.getText(), passwordText.getText());
                if (userRegistry.existUser(user) == true) {
                    if (userRegistry.isLoginAndPasswordCorrect(user)) {
                        System.out.println("Welcome " + user.getLogin());
                        currentUser = user;
                        userLoginForMessage = currentUser.getLogin();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("appPage.fxml"));
                        AppPageController controller = new AppPageController(currentUser.getLogin());
                        loader.setController(controller);

                        Scene appPageScene = new Scene(loader.load());
                        Stage mainStage = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow();
                        mainStage.setScene(appPageScene);
                        mainStage.show();
                        //primaryStage.setScene(appScene);
                    }
                } else {
                    System.out.println("upsss... something went wrong");
                    usernameText.clear();
                    passwordText.clear();
                    LoginAlertBox.display("Login error", "Incorrect login or password, try again or register.");
                }
            } catch (CredentialsToShortException e1) {
                e1.printStackTrace();
            } catch (LoginNullException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //primaryStage.setScene(appScene);
        });

        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton,1,3);
        registerButton.setOnAction(e -> {
            try {
                User user = new User(usernameText.getText(), passwordText.getText());
                userRegistry.addUser(user);
                System.out.println("User has been created!");
                currentUser = user;
                userLoginForMessage = currentUser.getLogin();
                System.out.println("Welcome " + user.getLogin());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("appPage.fxml"));
                AppPageController controller = new AppPageController(currentUser.getLogin());
                loader.setController(controller);

                Scene appPageScene = new Scene(loader.load());

                Stage mainStage = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow();
                mainStage.setScene(appPageScene);
                mainStage.show();

                primaryStage.setScene(appScene);
            } catch (IOException | CredentialsToShortException | LoginNullException | LoginExistException e1) {
                //e.printStackTrace();
                System.out.println("No way...");
                usernameText.clear();
                passwordText.clear();
                LoginAlertBox.display("Registration error", "Incorrect login or password, try again or register.");
            }

        });

        loginLayout.getChildren().addAll(usernameLabel, usernameText, passwordLabel, passwordText, loginButton, registerButton);
        loginScene = new Scene(loginLayout, 280,150);


        // display main window
        primaryStage.setScene(loginScene);
        primaryStage.show();
        // GUI ends here - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    }



    public enum State {START, DURING_LOGIN, LOGGED_IN, DURING_REGISTRATION, EXIT}
    private static int  categoryNumber = 10;

    public static void main(String[] args) {
        launch(args);
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
        System.out.println("Auction will be created by " + currentUser.getLogin());
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
        try {
            System.out.println("create you login: ");
            String login = input.next();
            System.out.println("create your password: ");
            String password = input.next();

            User user = new User(login, password);

            userRegistry.addUser(user);

            System.out.println("User has been created!");
            currentUser = user;
            return State.LOGGED_IN;


        } catch (IOException | CredentialsToShortException | LoginNullException | LoginExistException e) {
            //e.printStackTrace();
            System.out.println("No way...");
            return State.START;
        }
    }

    /*private static State printLoginScreen(Scanner input, UserRegistry userRegistry) {
        try {
            System.out.println("enter you login: ");
            String login = input.next();
            System.out.println("enter your password: ");
            String password = input.next();
            User user = null;
            user = new User(login, password);
            if (userRegistry.existUser(user) == true) {
                if (userRegistry.isLoginAndPasswordCorrect(user)) {
                    System.out.println("Welcome " + user.getLogin());
                    currentUser = user;
                    return State.LOGGED_IN;
                }
            } else {
                    System.out.println("upsss... something went wrong");
                    return State.START;
            }

        } catch (CredentialsToShortException | LoginNullException | IOException e) {
            //e.printStackTrace();
            return State.START;
        }
        return State.START;
    }*/

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
