package org.example.pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ViewStockController implements Initializable, Serializable {

    public TextField textField;
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
        HelloController.stage1.close();
    }
}
