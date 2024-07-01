package org.example.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ViewInvoiceController implements Serializable, Initializable {

    public Label totalPrice;
    public Label invoiceNumberField;
    public Label customerName;
    public Label date;
    public Label balance;
    public TextField cash;
    String data;
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
            InvoiceDuoController.stage6.close();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("First,Please select an Invoice!");
            alert.showAndWait();
            return;
        }
        invoiceNumberField.setText(String.valueOf(UserServices.getCurrentUser().currentInvoice.getInvoiceNumber()));
        customerName.setText(UserServices.getCurrentUser().currentInvoice.getCustomerName());
        double tot = UserServices.getCurrentUser().currentInvoice.calculateTotalPrice();
        UserServices.getCurrentUser().currentInvoice.setTotalPrice(tot);
        totalPrice.setText(String.valueOf(tot));
        double startBalance = UserServices.getCurrentUser().currentInvoice.getCash()-tot;
        balance.setText(String.valueOf(startBalance));


        item_name.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
        unit.setCellValueFactory(new PropertyValueFactory<Item,String>("unit"));
        price.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item,Double>("quantity"));
        netPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("netPrice"));
        discount.setCellValueFactory(new PropertyValueFactory<Item,Double>("discount"));
        ArrayList<Item> sortedArray = new ArrayList<>(UserServices.getCurrentUser().currentInvoice.itemsToBill);
        list.addAll(sortedArray);

        table.getSelectionModel().getSelectionMode();
        table.getSelectionModel().setCellSelectionEnabled(true);

        table.setItems(list);


    }

    public void generatePDF(ActionEvent event) throws FileNotFoundException {
        UserServices.getCurrentUser().currentInvoice.generatePDF();
    }

    public void addMore(ActionEvent event) {
            AddToInvoiceController.stage7.close();
    }

    public void saveBill(ActionEvent event) {
        if(UserServices.getCurrentUser().currentInvoice.getCash()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Can not Save!");
            alert.setContentText("The Balance is less than 0. The customer indebted to Store!");
            alert.showAndWait();
            return;
        }
        if(UserServices.getCurrentUser().currentInvoice.getBalance()<0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Can not Save!");
            alert.setContentText("The Balance is less than 0. The customer indebted to Store!");
            alert.showAndWait();
            return;
        }
        UserServices.getCurrentUser().currentInvoice.setPending(false);
        InvoiceDuoController.stage6.close();
        AddToInvoiceController.stage7.close();
        UserServices.getCurrentUser().currentInvoice=null;
        UserServices.updateFile();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Successful");
        alert.setContentText("Invoice was saved successfully!");
        alert.showAndWait();


    }


    public void clickedColumn(MouseEvent mouseEvent) {
        try {
            TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            Item item = table.getItems().get(row);
            TableColumn tableColumn = tablePosition.getTableColumn();
            data = (String) tableColumn.getCellObservableValue(item).getValue();
        }catch (ClassCastException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Select Item Name!");
            alert.showAndWait();
        }catch (IndexOutOfBoundsException e){}
    }

    public void saveToPending(ActionEvent event){
        InvoiceDuoController.stage6.close();
        AddToInvoiceController.stage7.close();
        UserServices.getCurrentUser().currentInvoice=null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Successful");
        alert.setContentText("Invoice was saved to Pending Invoices");
        alert.showAndWait();
        UserServices.updateFile();
    }

    public void deleteItem(ActionEvent event) {
        if(!UserServices.getCurrentUser().currentInvoice.isPending()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Can not edit");
            alert.setContentText("This Invoice was saved!");
            alert.showAndWait();
            return;
        }
        try {
            for (int i = 0; i < UserServices.getCurrentUser().currentInvoice.itemsToBill.size(); i++) {
                if (data.equals(UserServices.getCurrentUser().currentInvoice.itemsToBill.get(i).getItemName())) {
                    Item oldItem = UserServices.getCurrentUser().findItem(data);
                    oldItem.setAvailableStock(oldItem.getAvailableStock() + UserServices.getCurrentUser().currentInvoice.itemsToBill.get(i).getQuantity());
                    UserServices.getCurrentUser().currentInvoice.itemsToBill.remove(i);
                    UserServices.updateFile();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Successful");
                    alert.setContentText("Item was deleted successfully!");
                    alert.showAndWait();
                    return;

                }
            }
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Not Selected Error");
            alert.setContentText("Please Select an Item to delete");
            alert.showAndWait();
        }

    }

    public void add(ActionEvent event) {
        try {
            if (!UserServices.getCurrentUser().currentInvoice.isPending()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Can not edit");
                alert.setContentText("This Invoice was saved!");
                alert.showAndWait();
                return;
            }
            UserServices.getCurrentUser().currentInvoice.setCash(UserServices.getCurrentUser().currentInvoice.getCash()+Double.parseDouble(cash.getText()));
            double balance1 = UserServices.getCurrentUser().currentInvoice.getCash() - UserServices.getCurrentUser().currentInvoice.getTotalPrice();
            balance.setText(String.valueOf(balance1));
            UserServices.getCurrentUser().currentInvoice.setBalance(balance1);
            UserServices.updateFile();
        }catch (NumberFormatException r){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Can not Add");
            alert.setContentText("Please Enter the Cash Value!");
            alert.showAndWait();
        }
    }
}
