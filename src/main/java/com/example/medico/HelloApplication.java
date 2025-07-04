package com.example.medico;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Consulta;
import com.example.medico.modelos.Paciente;
import com.example.medico.services.ConsultaServices;
import com.example.medico.services.DoctorServices;
import com.example.medico.services.Pacientesservices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

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


        //consulta2.setDoctor(doctor1);
      //  consulta2.setPaciente(paciente1);

      //  consultaServices.addConsulta(consulta2);
      //  Paciente p = pacientesservices.getpacienteById(1L);
      //  Set<Consulta> consultas= p.getConsultas();

         //   doctor1.setConsulta(consulta1);
         //  doctorServices.updateDoctor(doctor1);

        launch();

            HibernateUtils.closeEntityManagerFactory();
        }
    }
