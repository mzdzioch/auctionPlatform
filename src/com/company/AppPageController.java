package com.company;

import com.company.model.Auction;
import com.company.model.Category;
import com.company.model.Node;
import com.company.service.CategoryBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AppPageController implements Initializable{

    private StringProperty userLogin = new SimpleStringProperty();

    TableView<Auction> table;
    TableColumn<Auction, Integer> idColumn = new TableColumn<>("ID");
    TableColumn<Auction, String> titleColumn = new TableColumn<>("Title");
    TableColumn<Auction, String> descriptionColumn = new TableColumn<>("Description");
    TableColumn<Auction, Double> priceColumn = new TableColumn<>("Price");
    TableColumn<Auction, Integer> categoryIdColumn = new TableColumn<>("CategoryID");
    TableColumn<Auction, String> ownerColumn = new TableColumn<>("Login");

    @FXML
    Label welcomeLabel;

    @FXML
    HBox manipulateBox;

    @FXML
    TextField titleInput;

    @FXML
    TextField descriptionInput;

    @FXML
    TextField priceInput;

    @FXML
    TextField categoryIdInput;

    @FXML
    Label loggedUser;

    @FXML
    VBox centralVBox;


    @FXML
    TreeView<String> categoryTreeView = new TreeView<>();


    @FXML
    Button addButton = new Button();

    @FXML
    Button deleteButton = new Button();

    @FXML
    Button quitButton = new Button();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node<Category> rootCategory = new Node<Category>(null, new Category());
        Node<Category> electronicsCategory = new Node<Category>(rootCategory, new Category(1, "Elektronika"));
        rootCategory.addChild(electronicsCategory);
        CategoryBuilder categoryBuilder = new CategoryBuilder();

        table = new TableView<>();


        idColumn.setMaxWidth(60);
        titleColumn.setMaxWidth(100);
        titleColumn.setMinWidth(99);

        idColumn.setCellValueFactory(new PropertyValueFactory<Auction, Integer>("auctionID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Auction, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Auction, String>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Auction, Double>("price"));
        categoryIdColumn.setCellValueFactory(new PropertyValueFactory<Auction, Integer>("categoryID"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Auction, String>("login"));

        loggedUser.setText(userLogin.get());

        table.setItems(getAuctions());
        table.getColumns().addAll(idColumn, titleColumn, descriptionColumn, priceColumn, categoryIdColumn, ownerColumn);


        centralVBox.getChildren().add(table);



        addButton.setOnAction(e -> addButtonClicked());
        deleteButton.setOnAction(e -> deleteButtonClicked());
    }


        private void deleteButtonClicked() {
            ObservableList<Auction> auctionSelected, allAuctions;
            allAuctions = table.getItems();
            auctionSelected = table.getSelectionModel().getSelectedItems();

            auctionSelected.forEach(allAuctions::remove);
        }


        private void addButtonClicked() {
            Auction auction = new Auction(titleInput.getText(),
                Double.parseDouble(priceInput.getText()),
                Integer.parseInt(categoryIdInput.getText()),
                descriptionInput.getText(),
                userLogin.get()
                );

            table.getItems().add(auction);
            titleInput.clear();
            descriptionInput.clear();
            priceInput.clear();
            categoryIdInput.clear();
        }


    public ObservableList<Auction> getAuctions() {
        ObservableList<Auction> auctions = FXCollections.observableArrayList();
        auctions.add(new Auction(1, "Kalkulator", 2.0, 1, "Po prostu liczydło", "romek"));
        auctions.add(new Auction(2, "Laptok", 2000.0, 1, "Zepsuty, ale działa", "Trump"));

        return auctions;
    }


    public AppPageController(String loginEntered) {
        userLogin.set(loginEntered);
    }
}
