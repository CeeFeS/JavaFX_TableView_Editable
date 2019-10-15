package com.company.den;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) {
        try {
            Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("main.fxml"));
            Scene scene = new Scene(root, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Wordpack Generator");
            // primaryStage.setResizable(false);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}