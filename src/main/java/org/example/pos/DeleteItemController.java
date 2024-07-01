package org.example.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.pos.HelloController;
import org.example.pos.Item;
import org.example.pos.UserServices;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class DeleteItemController implements Serializable, Initializable {
    public TextField textField;
    public TextField newStockField;
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
    String data;
    ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        item_name.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
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
                else{
                    return false;
                }
            });
        });

        SortedList<Item> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);


    }

    public void  deleteItemButton(){
        try {
            UserServices.getCurrentUser().deleteItem(data);
        }catch (NullPointerException p){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Select an Item to delete");
            alert.showAndWait();
        }
    }
    public void close(){
        HelloController.stage3.close();
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
}
