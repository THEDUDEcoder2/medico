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
            Pacientesservices pacientesservices = new Pacientesservices();
            ConsultaServices consultaServices = new ConsultaServices();


            Doctor doctor1 = new Doctor("Simi", "123456", "General", "1122");
            doctorServices.addDoctor(doctor1);


            Paciente paciente1 = new Paciente("Eduardo", "11/06/2004", "zacatal",
                    "234023", "62343252", "A+");
            pacientesservices.addpaciente(paciente1);

            Consulta consulta1 = new Consulta(
                    LocalDate.now(), LocalTime.now(), "Simi-general",
                    "dolor de cabeza", "presion alta", "11/06/2004",
                    "85", "36", "ninguna", "90kg", "1.72",
                    "130/90", "paracetamol", "dolor de cabeza",
                    "presenta presion alta"
            );


            consulta1.setDoctor(doctor1);
            consulta1.setPaciente(paciente1);

            consultaServices.addConsulta(consulta1);

            doctor1.setConsulta(consulta1);
            doctorServices.updateDoctor(doctor1);

            System.out.println("Datos insertados correctamente");

            // launch();

            HibernateUtils.closeEntityManagerFactory();
        }
    }
