package org.example.pos;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.pos.UserServices;

import java.io.IOException;

public class InvoiceDuoController {


    public DatePicker pickedDate;
    public TextField customerName;
    public static Stage stage6;
    public static Stage stage12;

    public void close(ActionEvent event) {
        HelloController.stage5.close();
    }

    public void createNewInvoice(ActionEvent event) throws IOException {
        try {
            if(customerName.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Value Error");
                alert.setContentText("Please Enter Customer Name");
                alert.showAndWait();
                return;
            }
            UserServices.getCurrentUser().createNewBill(customerName.getText(), pickedDate.getValue().toString());
            UserServices.getCurrentUser().currentInvoice = UserServices.getCurrentUser().invoiceArray.get(UserServices.getCurrentUser().invoiceArray.size()-1);
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Value Error");
            alert.setContentText("Please Enter Date");
            alert.showAndWait();
            return;
        }
        HelloController.stage5.close();
        FXMLLoader fxmlLoader6 = new FXMLLoader(getClass().getResource("AddToInvoice.fxml"));
        Parent root6 = (Parent) fxmlLoader6.load();
        stage6 = new Stage();
        stage6.setTitle("Add to Invoice");
        stage6.setScene(new Scene(root6));
        stage6.setResizable(false);
        stage6.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage6));
        stage6.show();
    }

    public void viewPendingInvoices(ActionEvent event) throws IOException {
        HelloController.stage5.close();
        FXMLLoader fxmlLoader12 = new FXMLLoader(getClass().getResource("PendingAllInvoices.fxml"));
        Parent root12 = (Parent) fxmlLoader12.load();
        stage12 = new Stage();
        stage12.setTitle("Pending Invoices");
        stage12.setScene(new Scene(root12));
        stage12.setResizable(false);
        stage12.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage12));
        stage12.show();
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
