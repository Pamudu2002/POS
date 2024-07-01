package org.example.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.pos.InvoiceDuoController;
import org.example.pos.Item;
import org.example.pos.UserServices;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AddToInvoiceController implements Serializable, Initializable {

    public TextField textField;
    public static Stage stage7;

    public TextField quantity;
    public TextField discount;
    String data;
    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, Double> available_stock;

    @FXML
    private TableColumn<Item, Integer> item_code;

    @FXML
    private TableColumn<Item, String> item_name;
    @FXML
    private TableColumn<Item, String> category;

    @FXML
    private TableColumn<Item, Double> cost;

    @FXML
    private TableColumn<Item, Double> price;

    @FXML
    private TableColumn<Item, String> unit;
    ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        item_name.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
        unit.setCellValueFactory(new PropertyValueFactory<Item,String>("unit"));
        price.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        available_stock.setCellValueFactory(new PropertyValueFactory<Item,Double>("availableStock"));
        ArrayList<Item> sortedArray = new ArrayList<>(UserServices.getCurrentUser().itemArray);
        Collections.sort(sortedArray, Comparator.comparing(Item::getItemName));
        list.addAll(sortedArray);

        table.getSelectionModel().getSelectionMode();
        table.getSelectionModel().setCellSelectionEnabled(true);

        FilteredList<Item> filteredData = new FilteredList<>(list, b -> true);

        textField.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(item ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowercaseFilter = newValue.toLowerCase();

                if(item.getItemName().toLowerCase().indexOf(lowercaseFilter) != -1){
                    return true;
                }
                if(item.getCategory().toLowerCase().indexOf(lowercaseFilter) != -1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });

        SortedList<Item> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);


    }

    public void close(){
        InvoiceDuoController.stage6.close();
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


    public void addItem(ActionEvent event) {
        try {
            Item result = UserServices.getCurrentUser().findItem(data);
            if (result.getAvailableStock() < Double.parseDouble(quantity.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Not Enough Stocks!");
                alert.setContentText("Please Add Stocks First.");
                alert.showAndWait();
                return;
            }
            Item newItem = new Item(0, result.getItemName(), result.getCategory(), result.getUnit(), result.getCost(), result.getPrice(), result.getAvailableStock());
            newItem.setQuantity(Double.parseDouble(quantity.getText()));
            newItem.setDiscount(Double.parseDouble(discount.getText()));
            double x = (newItem.getPrice() - (newItem.getDiscount() * newItem.getPrice()) / 100) * newItem.getQuantity();
            newItem.setNetPrice(x);
            UserServices.getCurrentUser().currentInvoice.itemsToBill.add(newItem);
            result.setAvailableStock(result.getAvailableStock() - Double.parseDouble(quantity.getText()));
            UserServices.updateFile();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Added Successfully!");
            alert.setContentText("Item Added to Invoice");
            alert.showAndWait();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Select the Item and Invoice");
            alert.showAndWait();
        }catch (NumberFormatException k){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Enter Quantity and Discount");
            alert.showAndWait();
        }
    }

    public void viewInovoice(ActionEvent event) throws IOException {
        if(UserServices.getCurrentUser().currentInvoice==null){
            InvoiceDuoController.stage6.close();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("First,Please select an Invoice!");
            alert.showAndWait();
            return;
        }
        FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("ViewInvoice.fxml"));
        Parent root7 = (Parent) fxmlLoader7.load();
        stage7 = new Stage();
        stage7.setTitle("Invoice");
        stage7.setScene(new Scene(root7));
        stage7.setResizable(false);
        stage7.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage7));
        stage7.show();

    }

    private void centerStage(Stage stage) {
        // Get the screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calculate the center position
        double centerX = (screenBounds.getWidth() - stage.getWidth()) / 2;
        double centerY = (screenBounds.getHeight() - stage.getHeight()) / 2;

        // Set the position of the stage
        stage.setX(centerX);
        stage.setY(centerY);
    }
}
