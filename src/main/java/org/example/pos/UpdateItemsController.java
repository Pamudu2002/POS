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
import org.example.pos.UserServices;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class UpdateItemsController implements Serializable, Initializable {
    public TextField textField;
    public TextField newName;
    public TextField newCategory;
    public TextField newPrice;
    public TextField newCost;
    public ChoiceBox<String> newUnit;
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
    private final String[] units = {"Kg","m","unit","inches","feet","yard"}; //if this should change, change this and "AddItems.java" array

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newUnit.getItems().addAll(units);
        item_code.setCellValueFactory(new PropertyValueFactory<Item,Integer>("itemCode"));
        item_name.setCellValueFactory(new PropertyValueFactory<Item,String>("itemName"));
        category.setCellValueFactory(new PropertyValueFactory<Item,String>("category"));
        unit.setCellValueFactory(new PropertyValueFactory<Item,String>("unit"));
        cost.setCellValueFactory(new PropertyValueFactory<Item,Double>("cost"));
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

    public void updateItems(){
        try {
            Item updaingItem = UserServices.getCurrentUser().findItem(data);
            if (!newName.getText().isEmpty()) {
                updaingItem.setItemName(newName.getText());
            }
            if (!newCategory.getText().isEmpty()) {
                updaingItem.setCategory(newCategory.getText());
            }
            if (newUnit.getValue() != null) {
                updaingItem.setUnit(newUnit.getValue());
            }
            if (!newCost.getText().isEmpty()) {
                updaingItem.setCost(Double.parseDouble(newCost.getText()));
            }
            if (!newPrice.getText().isEmpty()) {
                updaingItem.setPrice(Double.parseDouble(newPrice.getText()));
            }
            UserServices.updateFile();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Successful");
            alert.setContentText("Item was updated successfully!");
            alert.showAndWait();

        }catch (NullPointerException t){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Select Item Name!");
            alert.showAndWait();
        }
    }


    public void close(){
        HelloController.stage4.close();
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
