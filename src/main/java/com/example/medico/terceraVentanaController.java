package com.example.medico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class terceraVentanaController {

    @FXML
    private void abrirConsulta(ActionEvent event) {
        try {
            // Cargar el archivo consulta.fxml desde la ruta correcta
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medico/consulta.fxml"));
            Parent root = loader.load();

            // Crear nueva ventana para consulta médica
            Stage stage = new Stage();
            stage.setTitle("Consulta Médica");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void segundaVentana(ActionEvent event) {
        try {
            // Cargar segunda ventana desde segunda ventana.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medico/segunda ventana.fxml"));
            Parent root = loader.load();

            // Crear nueva ventana para la segunda ventana
            Stage stage = new Stage();
            stage.setTitle("Segunda Ventana");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

