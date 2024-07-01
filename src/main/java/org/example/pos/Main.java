package org.example.pos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FirstScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("POS System");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> centerStage(stage));
        stage.show();
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

    public static void main(String[] args) {
        launch();
    }
}