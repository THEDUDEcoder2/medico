package com.example.medico;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class crearPerfilController {

    @FXML
    private ChoiceBox<String> BoxEspecialidad;

    @FXML
    private Button BtnCrearPerfil;

    @FXML
    private Button BtnRegresar;

    @FXML
    private TextField TxtCedulaProfesional;

    @FXML
    private TextField TxtConfirmarContraseña;

    @FXML
    private TextField TxtContraseña;

    @FXML
    private TextField TxtNombre;

    @FXML
    public void initialize() {

        BoxEspecialidad.getItems().addAll(
                "Medicina General",
                "Cardiología",
                "Pediatría",
                "Dermatología",
                "Ortopedia",
                "Ginecología",
                "Neurología",
                "Oftalmología"
        );


        BtnCrearPerfil.setOnAction(event -> crearPerfil());
        BtnRegresar.setOnAction(event -> regresar());
    }

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
            mostrarAlerta("Error", "La cédula profesional debe tener al menos 6 caracteres");
            return;
        }


        Doctor nuevoDoctor = new Doctor(nombre, cedula, especialidad, contraseña);
        HelloApplication.setDoctorActual(nuevoDoctor);

        mostrarAlerta("Éxito", "Perfil creado correctamente");
        cerrarVentana();
    }

    private void regresar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) BtnRegresar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}