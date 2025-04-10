package com.example.medico.controladores;

import com.example.medico.modelos.Doctor;
import com.example.medico.modelos.SharedData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class crearPerfilController {
    @FXML private ChoiceBox<String> BoxEspecialidad;
    @FXML private Button BtnCrearPerfil;
    @FXML private Button BtnRegresar;
    @FXML private TextField TxtCedulaProfesional;
    @FXML private TextField TxtConfirmarContraseña;
    @FXML private TextField TxtContraseña;
    @FXML private TextField TxtNombre;

    private final SharedData sharedData = SharedData.getInstance();

    @FXML
    public void initialize() {
        BoxEspecialidad.getItems().addAll(
                "Medicina General", "Cardiología", "Pediatría", "Dermatología",
                "Ortopedia", "Ginecología", "Neurología", "Oftalmología"
        );
    }

    @FXML
    private void crearPerfil() {
        String nombre = TxtNombre.getText().trim();
        String cedula = TxtCedulaProfesional.getText().trim();
        String especialidad = BoxEspecialidad.getValue();
        String contraseña = TxtContraseña.getText().trim();
        String confirmacion = TxtConfirmarContraseña.getText().trim();

        if (nombre.isEmpty() || cedula.isEmpty() || especialidad == null || contraseña.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios");
            return;
        }

        if (!contraseña.equals(confirmacion)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden");
            return;
        }

        if (cedula.length() < 6) {
            mostrarAlerta("Error", "La cedula debe tener al menos 6 caracteres");
            return;
        }

        Doctor nuevoDoctor = new Doctor(nombre, cedula, especialidad, contraseña);
        sharedData.setDoctorActual(nuevoDoctor);
        mostrarAlerta("Exito", "Perfil creado correctamente");

        cerrarVentana();
        abrirVentanaPrincipal();
    }

    @FXML
    private void regresar() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medico/views/portada consultorio.fxml"));
            Parent root = loader.load();
            cerrarVentana();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) BtnRegresar.getScene().getWindow();
        stage.close();
    }

    private void abrirVentanaPrincipal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/medico/views/portada consultorio.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Sistema Medico");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana principal");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}