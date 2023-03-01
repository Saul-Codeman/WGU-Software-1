package com.example.wgusoftware1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.wgusoftware1.Library.*;

public class MainController extends Application implements Initializable {

    /**
     * Button of the GUI interface
     */
    @FXML
    private Button mainExitButton;

    /**
     * Button of the GUI interface
     */
    @FXML
    private Button mainPartAddButton;

    /**
     * Button of the GUI interface
     */
    @FXML
    private Button mainPartDeleteButton;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Part, Integer> mainPartIdCol;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Part, Integer> mainPartInventoryCol;

    /**
     * Button of the GUI interface
     */
    @FXML
    private Button mainPartModifyButton;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Part, String> mainPartNameCol;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Part, Double> mainPartPriceCol;

    /**
     * Text field of the GUI interface
     */
    @FXML
    private TextField mainPartSearchTxt;

    /**
     * Table of the GUI interface
     */
    @FXML
    private TableView<Part> mainPartTable;

    /**
     * Button of the GUI interface
     */
    @FXML
    private Button mainProductAddButton;

    /**
     * Button of the GUI interface
     */
    @FXML
    private Button mainProductDeleteButton;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Product, Integer> mainProductIdCol;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Product, Integer> mainProductInventoryCol;

    /**
     * Button of the GUI interface
     */
    @FXML
    private Button mainProductModifyButton;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Product, String> mainProductNameCol;

    /**
     * Table column of the GUI interface
     */
    @FXML
    private TableColumn<Product, Double> mainProductPriceCol;

    /**
     * Text field of the GUI interface
     */
    @FXML
    private TextField mainProductSearchTxt;

    /**
     * Table of the GUI interface
     */
    @FXML
    private TableView<Product> mainProductTable;

    //Handlers

    Stage stage;
    Parent scene;

    /**
     *
     * @param event Exit button
     */
    @FXML
    void mainExitHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close the application?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.exit(0);
        }
    }


    /**
     *
     * @param event Add a part menu
     * @throws IOException RUNTIME ERROR: Function would throw a runtime error when throws IOException was not present
     */
    @FXML
    void partAddHandler(ActionEvent event) throws IOException {
        switchScreen(event, addPartUrl);
    }

    /**
     *
     * @param event Delete a selected part
     */
    @FXML
    void partDeleteHandler(ActionEvent event) {
        if (mainPartTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to delete");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the part?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            deleteSelectedPart(mainPartTable);
        }
    }


    /**
     *
     * @param event Modify a selected part
     * @throws IOException catches RUNTIME ERROR
     */
    @FXML
    void partModifyHandler(ActionEvent event) throws IOException {
        modifySelectedPart(this, mainPartTable, event);
    }

    /**
     *
     * @param event Search for a part
     */
    @FXML
    void partSearchHandler(ActionEvent event) {
        searchPart(mainPartSearchTxt, mainPartTable);
    }

    /**
     *
     * @param event Switch to add a product menu
     * @throws IOException catches RUNTIME ERROR
     */
    @FXML
    void productAddHandler(ActionEvent event) throws IOException {
        switchScreen(event, addProductUrl);
    }

    /**
     *
     * @param event Delete the selected product
     */
    @FXML
    void productDeleteHandler(ActionEvent event) {
        if (mainProductTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product to delete");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the product?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            if(mainProductTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()) {
                deleteSelectedProduct(mainProductTable);
            }
            else{
                alert = new Alert(Alert.AlertType.WARNING, "Cannot delete a product with an associated part");
                alert.showAndWait();
            }
        }
    }

    /**
     *
     * @param event Choose a product to modify and switch scenes
     * @throws IOException catches RUNTIME ERROR
     */
    @FXML
    void productModifyHandler(ActionEvent event) throws IOException {
        modifySelectedProduct(this, mainProductTable,event);
    }

    /**
     *
     * @param event Search Products
     */
    @FXML
    void productSearchHandler(ActionEvent event) {
        searchProduct(mainProductSearchTxt, mainProductTable);
    }

    /**
     * Initialize in console
     */
    @Override
    public void init(){
        System.out.println("Initialized");
    }

    /**
     *
     * @param stage where the scene are displayed
     * @throws IOException
     * Load the page
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 500);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param args
     * Launches args and adds products and parts to lists
     */
    public static void main(String[] args){

        // Declare data for Inventory Management System
        Product product1 = new Product(autoProductGenId(),"Giant Bike", 299.99, 5, 1, 10);
        Product product2 = new Product(autoProductGenId(), "Tricycle", 99.99, 3, 1, 10);
        InHouse inPart1 = new InHouse(autoPartGenId(),"Brakes", 15.00, 10, 2, 100, 100);
        InHouse inPart2 = new InHouse(autoPartGenId(),"Wheel", 11.00, 16, 2, 200, 200);
        Outsourced outPart1 = new Outsourced(autoPartGenId(), "Seat", 15.00, 10, 2, 200, "ABC Company");


        Inventory.addPart(inPart1);
        Inventory.addPart(inPart2);
        Inventory.addPart(outPart1);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);

        product1.addAssociatedPart(inPart1);
        product1.addAssociatedPart(inPart2);
        product2.addAssociatedPart(inPart1);
        product2.addAssociatedPart(inPart2);


        launch(args);
    }

    /**
     *
     * @param url of current form
     * @param resourceBundle bundle
     * initializes the parts and products tables
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize Parts
        setPartsTable(mainPartTable, mainPartIdCol, mainPartNameCol, mainPartInventoryCol, mainPartPriceCol);

        //Initialize Products
        setProductsTable(mainProductTable, mainProductIdCol, mainProductNameCol, mainProductInventoryCol, mainProductPriceCol);

    }
}