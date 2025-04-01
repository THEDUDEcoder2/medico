package com.example.medico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Doctor doctorActual;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("portada consultorio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("bienvenido :-)");
        stage.setScene(scene);
        stage.show();
    }

    public static void setDoctorActual(Doctor doctor) {
        doctorActual = doctor;
    }

    public static Doctor getDoctorActual() {
        return doctorActual;
    }

    public static void main(String[] args) {
        launch();
    }
}