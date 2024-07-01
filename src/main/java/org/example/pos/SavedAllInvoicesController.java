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
import org.example.pos.UserServices;
import org.example.pos.Invoice;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class SavedAllInvoicesController implements Serializable, Initializable {

    public TextField textField;
    public static Stage stage11;
    int data;
    @FXML
    private TableView<Invoice> table;

    @FXML
    private TableColumn<Invoice, Integer> invoiceNumber;
    @FXML
    private TableColumn<Invoice, String> customerName;
    @FXML
    private TableColumn<Invoice, String> date;


    ObservableList<Invoice> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        invoiceNumber.setCellValueFactory(new PropertyValueFactory<Invoice,Integer>("invoiceNumber"));
        customerName.setCellValueFactory(new PropertyValueFactory<Invoice,String>("customerName"));
        date.setCellValueFactory(new PropertyValueFactory<Invoice,String>("date"));

        ArrayList<Invoice> sortedArray = new ArrayList<>();
        for(int j = 0; j< UserServices.getCurrentUser().invoiceArray.size(); j++){
            if(!UserServices.getCurrentUser().invoiceArray.get(j).isPending()){
                sortedArray.add(UserServices.getCurrentUser().invoiceArray.get(j));
            }
        }

        Collections.sort(sortedArray, Comparator.comparing(Invoice::getInvoiceNumber).reversed());
        list.addAll(sortedArray);

        table.getSelectionModel().getSelectionMode();
        table.getSelectionModel().setCellSelectionEnabled(true);

        FilteredList<Invoice> filteredData = new FilteredList<>(list, b -> true);

        textField.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(invoice ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowercaseFilter = newValue.toLowerCase();

                if(invoice.getCustomerName().toLowerCase().indexOf(lowercaseFilter) != -1){
                    return true;
                }
                if(String.valueOf(invoice.getInvoiceNumber()).toLowerCase().indexOf(lowercaseFilter) != -1){
                    return true;
                }
                if(invoice.getDate().toLowerCase().indexOf(lowercaseFilter) != -1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });

        SortedList<Invoice> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);


    }

    public void viewInvoice() throws IOException {

        for (int i = 0; i < UserServices.getCurrentUser().invoiceArray.size(); i++) {
            if(data==UserServices.getCurrentUser().invoiceArray.get(i).getInvoiceNumber()){
                UserServices.getCurrentUser().currentInvoice = UserServices.getCurrentUser().invoiceArray.get(i);
            }
        }
        if(UserServices.getCurrentUser().currentInvoice==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("First,Please select an Invoice Number");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader11 = new FXMLLoader(getClass().getResource("SavedInvoices.fxml"));
        Parent root11 = (Parent) fxmlLoader11.load();
        stage11 = new Stage();
        stage11.setTitle("Add Items");
        stage11.setScene(new Scene(root11));
        stage11.setResizable(false);
        stage11.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage11));
        stage11.show();
    }


    public void close(){
        HelloController.stage10.close();
    }

    public void clickedColumn(MouseEvent mouseEvent) {
        try {
            TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            Invoice invoice = table.getItems().get(row);
            TableColumn tableColumn = tablePosition.getTableColumn();
            data = (Integer) tableColumn.getCellObservableValue(invoice).getValue();
        }catch (ClassCastException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Select Invoice Number");
            alert.showAndWait();
        }catch (IndexOutOfBoundsException e){}
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
