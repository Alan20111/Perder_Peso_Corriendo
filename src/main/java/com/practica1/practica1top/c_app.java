package com.practica1.practica1top;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class c_app extends Application {

    @Override
    public void start(Stage vStage) throws IOException {
        FXMLLoader vFxmlLoad = new FXMLLoader(c_app.class.getResource("hello-view.fxml"));
        Scene vScene = new Scene(vFxmlLoad.load(), 950, 900);

        vStage.setTitle("Simulador Running - Práctica 1");
        vStage.setScene(vScene);
        vStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}