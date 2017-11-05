package com.company;

import com.company.exceptions.CredentialsToShortException;
import com.company.exceptions.LoginExistException;
import com.company.exceptions.LoginNullException;
import com.company.model.Category;
import com.company.model.Node;
import com.company.model.User;
import com.company.helpers.CategoryBuilder;
import com.company.repository.UserRegistry;
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
        GridPane.setConstraints(usernameLabel, 0, 0);

        TextField usernameText = new TextField();
        GridPane.setConstraints(usernameText, 1, 0);

        Label passwordLabel = new Label("Password");
        GridPane.setConstraints(passwordLabel, 0, 1);

        TextField passwordText = new TextField();
        GridPane.setConstraints(passwordText, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 0, 3);
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
                        Stage mainStage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
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
        GridPane.setConstraints(registerButton, 1, 3);
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

                Stage mainStage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
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
        loginScene = new Scene(loginLayout, 280, 150);


        // display main window
        primaryStage.setScene(loginScene);
        primaryStage.show();
        // GUI ends here - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    }
}