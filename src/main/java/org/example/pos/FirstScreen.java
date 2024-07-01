package org.example.pos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstScreen {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void signupButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void loginButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        //stage.centerOnScreen();
        stage.show();
    }
}
