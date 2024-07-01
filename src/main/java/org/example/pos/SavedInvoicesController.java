package org.example.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.pos.UserServices;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SavedInvoicesController implements Serializable, Initializable {

    public Label totalPrice;
    public Label invoiceNumberField;
    public Label customerName;
    public Label date;
    public Label balance;
    public Label cash;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, Double> quantity;

    @FXML
    private TableColumn<Item, Double> discount;

    @FXML
    private TableColumn<Item, String> item_name;

    @FXML
    private TableColumn<Item, Double> netPrice;

    @FXML
    private TableColumn<Item, Double> price;

    @FXML
    private TableColumn<Item, String> unit;
    ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            date.setText(UserServices.getCurrentUser().currentInvoice.getDate());
        }catch (NullPointerException f){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("First,Please select an Invoice Number");
            alert.showAndWait();
            return;
        }
        invoiceNumberField.setText(String.valueOf(UserServices.getCurrentUser().currentInvoice.getInvoiceNumber()));
        customerName.setText(UserServices.getCurrentUser().currentInvoice.getCustomerName());
        totalPrice.setText(String.valueOf(UserServices.getCurrentUser().currentInvoice.getTotalPrice()));
        cash.setText(String.valueOf(UserServices.getCurrentUser().currentInvoice.getCash()));
        balance.setText(String.valueOf(UserServices.getCurrentUser().currentInvoice.getBalance()));


        item_name.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
        unit.setCellValueFactory(new PropertyValueFactory<Item,String>("unit"));
        price.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item,Double>("quantity"));
        netPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("netPrice"));
        discount.setCellValueFactory(new PropertyValueFactory<Item,Double>("discount"));
        ArrayList<Item> sortedArray = new ArrayList<>(UserServices.getCurrentUser().currentInvoice.itemsToBill);
        list.addAll(sortedArray);


        table.setItems(list);


    }

    public void close(ActionEvent event) {
        SavedAllInvoicesController.stage11.close();
    }

    public void generatePDF(ActionEvent event) throws FileNotFoundException {
        UserServices.getCurrentUser().currentInvoice.generatePDF();
    }
}
