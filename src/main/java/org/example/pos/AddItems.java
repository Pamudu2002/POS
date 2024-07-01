package org.example.pos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.pos.HelloController;
import org.example.pos.UserServices;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddItems implements Initializable {

    @FXML
    public TextField itemNameField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField quantityField;
    @FXML
    public ChoiceBox<String> unitField;

    private final String[] units = {"Kg","m","unit","inches","feet","yard"};  //if this should change, change this and "updateItemsController.java" array
    public Button addNew;
    public Button close;
    public Button updateEx;
    public TextField costField;
    public TextField itemCategoryField;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unitField.getItems().addAll(units);
    }

    public void addNew(){
        try {
            if(itemNameField.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Value Error");
                alert.setContentText("Please Enter Item Name!");
                alert.showAndWait();
                return;
            }
            UserServices.getCurrentUser().addNewItem(itemNameField.getText(),itemCategoryField.getText(), unitField.getValue(), Double.parseDouble(costField.getText()), Double.parseDouble(priceField.getText()), Double.parseDouble(quantityField.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Added!");
            alert.setContentText("Item added successfully.");
            alert.showAndWait();
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Enter Valid Values!");
            alert.showAndWait();
        }

    }

    public void close(ActionEvent event) throws IOException {
        HelloController.stage.close();
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
