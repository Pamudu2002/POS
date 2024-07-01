package org.example.pos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.lang.StringTemplate.STR;

public class HelloController {

    public static Stage stage;
    public static Stage stage1;
    public static Stage stage2;
    public static Stage stage3;
    public static Stage stage4;
    public static Stage stage5;
    public static Stage stage10;
    public DatePicker profitDate;

    @FXML
    private Scene scene;
    private Parent root;
    public void addItems(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddItems.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Add Items");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage));
        stage.show();
    }

    public void createBill(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader5 = new FXMLLoader(getClass().getResource("InvoiceDuo.fxml"));
        Parent root5 = (Parent) fxmlLoader5.load();
        stage5 = new Stage();
        stage5.setTitle("Add Items");
        stage5.setScene(new Scene(root5));
        stage5.setResizable(false);
        stage5.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage5));
        stage5.show();
    }

    public void updateItems(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("UpdateItems.fxml"));
        Parent root4 = (Parent) fxmlLoader4.load();
        stage4 = new Stage();
        stage4.setTitle("Update Items");
        stage4.setScene(new Scene(root4));
        stage4.setResizable(false);
        stage4.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage4));
        stage4.show();
    }

    public void SavedBills(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader10 = new FXMLLoader(getClass().getResource("SavedAllInvoices.fxml"));
        Parent root10 = (Parent) fxmlLoader10.load();
        stage10 = new Stage();
        stage10.setTitle("Add Items");
        stage10.setScene(new Scene(root10));
        stage10.setResizable(false);
        stage10.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage10));
        stage10.show();
    }

    public void deleteItems(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("DeleteItem.fxml"));
        Parent root3 = (Parent) fxmlLoader3.load();
        stage3 = new Stage();
        stage3.setTitle("Delete Items");
        stage3.setScene(new Scene(root3));
        stage3.setResizable(false);
        stage3.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage3));
        stage3.show();

    }

    public void viewStock(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("ViewStock.fxml"));
        Parent root1 = (Parent) fxmlLoader1.load();
        stage1 = new Stage();
        stage1.setTitle("View Stock");
        stage1.setScene(new Scene(root1));
        stage1.setResizable(false);
        stage1.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage1));
        stage1.show();
    }

    public void updateStock(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("AddtoStock.fxml"));
        Parent root2 = (Parent) fxmlLoader2.load();
        stage2 = new Stage();
        stage2.setTitle("Add to Stock");
        stage2.setScene(new Scene(root2));
        stage2.setResizable(false);
        stage2.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage2));
        stage2.show();
    }

    public void exit(){
        System.exit(0);
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

    public void viewDailyProfit(ActionEvent event) {
        try {
            String datevalue = profitDate.getValue().toString();
            double profit = UserServices.getCurrentUser().calculateDailyProfit(datevalue);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(STR."Profit on \{datevalue}");
            alert.setContentText(STR."Your Profit: LKR \{profit}");
            alert.showAndWait();
        }catch (NullPointerException r){}
    }
}