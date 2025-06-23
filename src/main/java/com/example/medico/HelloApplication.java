package com.example.medico;

import com.example.medico.services.DoctorServices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import com.example.medico.modelos.Doctor;

public class HelloApplication extends Application {
    private static Doctor doctorActual;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("/com/example/medico/views/portada consultorio.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistema Médico");
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
        DoctorServices doctorServices = new DoctorServices();
        Doctor doctor1 = new Doctor("Simi", "123456", "General", "1122");
        doctorServices.addDoctor(doctor1);
        System.out.println();


        //launch();
    }
}