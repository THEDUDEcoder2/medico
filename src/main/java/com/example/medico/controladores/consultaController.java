package com.example.medico.controladores;

import com.example.medico.modelos.Doctor;
import com.example.medico.modelos.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class consultaController {
    @FXML private Button BCrearPerfil;
    @FXML private Button BIniciarSesion;
    @FXML private TextField TxtCedulaProfesional;
    @FXML private TextField TxtContraseña;

    // Modificar completamente el método IniciarSesion() para que quede así:
    @FXML
    void IniciarSesion(ActionEvent event) throws IOException {
        String cedula = TxtCedulaProfesional.getText();
        String contraseña = TxtContraseña.getText();
        SharedData sharedData = SharedData.getInstance();

        // Buscar el doctor en la lista por cédula
        Doctor doctor = sharedData.buscarDoctorPorCedula(cedula);

        if (doctor == null) {
            mostrarAlerta("Error", "Cédula profesional no encontrada");
            return;
        }

        if (doctor.getContraseña().equals(contraseña)) {
            sharedData.setDoctorActual(doctor); // Establecer como doctor actual
            cargarVentana(
                    "/com/example/medico/views/segunda ventana.fxml",
                    "Gestión de Pacientes"
            );
        } else {
            mostrarAlerta("Error", "Contraseña incorrecta");
        }
    }

    @FXML
    private void crearPerfil(ActionEvent event) throws IOException {
        cargarVentana(
                "/com/example/medico/views/crear perfil.fxml",
                "Crear Perfil Medico"
        );
    }

    private void cargarVentana(String fxml, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage) BIniciarSesion.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}