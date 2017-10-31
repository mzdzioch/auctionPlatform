package com.company.service;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
    /**
     * A JavaFX Hello World
     * @author carldea
     */
    public class LoginVIew extends Application {
        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            Application.launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Login Pane ");
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 380, 150, Color.WHITE);
            GridPane gridpane = new GridPane();
            //gridpane.setGridLinesVisible(true);
            gridpane.setPadding(new Insets(5));
            gridpane.setHgap(5);
            gridpane.setVgap(5);
            ColumnConstraints column1 = new ColumnConstraints(100);
            ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
            column2.setHgrow(Priority.ALWAYS);
            gridpane.getColumnConstraints().addAll(column1, column2);
            Label loginLbl = new Label("Login");
            TextField loginFld = new TextField();
            Label passwordLbl = new Label("Password");
            TextField passwordFld = new TextField();
            Button loginButt = new Button("Login");
            loginButt.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    System.out.println("Login pressed...");
                    System.out.println("Login entered: " + loginFld.getText());
                    System.out.println("Password entered: " + passwordFld.getText());
                } });
            Button registerButt = new Button("Register");
            registerButt.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    System.out.println("Register pressed...");
                } });
            // First name label
            GridPane.setHalignment(loginLbl, HPos.RIGHT);
            gridpane.add(loginLbl, 0, 0);
            // Last name label
            GridPane.setHalignment(passwordLbl, HPos.RIGHT);
            gridpane.add(passwordLbl, 0, 1);
            // First name field
            GridPane.setHalignment(loginFld, HPos.LEFT);
            gridpane.add(loginFld, 1, 0);
            // Last name field
            GridPane.setHalignment(passwordFld, HPos.LEFT);
            gridpane.add(passwordFld, 1, 1);
            // Save button
            GridPane.setHalignment(loginButt, HPos.RIGHT);
            gridpane.add(loginButt, 1, 2);
            GridPane.setHalignment(registerButt, HPos.CENTER);
            gridpane.add(registerButt, 1, 2);
            // Build top banner area
            FlowPane topBanner = new FlowPane();
            topBanner.setAlignment(Pos.TOP_LEFT);
            topBanner.setPrefHeight(40);
            String backgroundStyle =
                    "-fx-background-color: lightblue;"
                            + "-fx-background-radius: 3px;"
                            + "-fx-background-inset: 5px;";
            topBanner.setStyle(backgroundStyle);

            Text loginText = new Text(" Enter the world of auctions");
            loginText.setFill(Color.WHITE);
            Font serif = Font.font("Dialog", 30);
            loginText.setFont(serif);
            topBanner.getChildren().addAll(loginText);
            root.setTop(topBanner);
            root.setCenter(gridpane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

