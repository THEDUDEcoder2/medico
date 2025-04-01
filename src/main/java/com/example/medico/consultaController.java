package com.example.medico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class consultaController {

    @FXML private Button BCrearPerfil;
    @FXML private Button BIniciarSesion;
    @FXML private Button Bpacientes;
    @FXML private TextField TxtCedulaProfesional;
    @FXML private TextField TxtContraseña;

    @FXML
    void IniciarSesion(ActionEvent event) {
        String cedula = TxtCedulaProfesional.getText();
        String contraseña = TxtContraseña.getText();

        Doctor doctor = HelloApplication.getDoctorActual();

        if (doctor == null) {
            mostrarAlerta("Error", "No hay perfiles registrados. Cree un perfil primero.");
            return;
        }

        if (doctor.getCedula().equals(cedula)) {
            if (doctor.getContraseña().equals(contraseña)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("segunda ventana.fxml"));
                    Parent root = loader.load();

                    segundaVentanaController controller = loader.getController();
                    controller.setEspecialidad(doctor.getEspecialidad());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    ((Stage) BIniciarSesion.getScene().getWindow()).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                mostrarAlerta("Error", "Contraseña incorrecta");
            }
        } else {
            mostrarAlerta("Error", "Cédula profesional no encontrada");
        }
    }

    @FXML
    private void Doctor(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("crear perfil.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void segundaVentana(ActionEvent actionEvent) {
    }
}
