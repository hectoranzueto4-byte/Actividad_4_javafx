package com.yazidsistems.app.tema_4.application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga la vista del menú principal
            Parent root = FXMLLoader.load(getClass().getResource("/com/yazidsistems/app/tema_4/main-view.fxml"));
            Scene scene = new Scene(root, 600, 400);

            primaryStage.setTitle("Menú Principal");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
