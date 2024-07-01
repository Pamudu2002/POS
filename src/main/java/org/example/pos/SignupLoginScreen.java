package org.example.pos;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.example.pos.User;
import org.example.pos.UserServices;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupLoginScreen {

    public TextField usernameS;
    public TextField passwordS;
    public Label errmsg;
    public Label errmsg1;
    public TextField usernameL;
    public TextField passwordL;
    public Label errmsg3;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FirstScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage));
        stage.show();
    }

    public void signupButton2(ActionEvent event) throws IOException{
        UserServices.retrieveUsers();
        String usernameval = usernameS.getText();
        String passwordval = passwordS.getText();

        for(int i = 0; i < UserServices.users.size(); i++){
            if(usernameval.isEmpty()||passwordval.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Text Field Empty");
                alert.setContentText("Username or Password can not be empty!");
                alert.showAndWait();
                return;
            }
            if(UserServices.users.get(i).getUsername().equals(usernameval)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Try Again!");
                alert.setContentText("Username already exists! Try again with a different username.");
                alert.showAndWait();
                return;
            }

        }

        User user = new User(usernameval, passwordval);
        UserServices.users.add(user);
        UserServices.updateFile();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("InFormation Dialog");
        alert.setHeaderText("User File Updated!");
        alert.setContentText("User Added Successfully!");
        alert.showAndWait();


        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage));
        stage.show();

    }

    public void loginButton2(ActionEvent event) throws IOException{
        UserServices.retrieveUsers();
        String usernamevall = usernameL.getText();
        String passwordvall = passwordL.getText();

        boolean accept = false;
        for (int i = 0; i < UserServices.users.size(); i++) {
            if (UserServices.users.get(i).getUsername().equals(usernamevall) && UserServices.users.get(i).getPassword().equals(passwordvall)) {
                accept = true;
                UserServices.setCurrentUser(UserServices.users.get(i));
                UserServices.updateFile();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("InFormation Dialog");
                alert.setHeaderText("Access Granted");
                alert.setContentText("Login Successful");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage));
                stage.show();

            }
        }
        if (accept == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Try Again");
            alert.setContentText("Incorrect Username or Password! Try Again.");
            alert.showAndWait();
        }

        UserServices.updateFile();

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
