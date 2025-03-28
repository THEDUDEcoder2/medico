package com.example.medico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class consultaController {

    @FXML
    private Button BCrearPerfil;

    @FXML
    private Button BIniciarSesion;

    @FXML
    private Button Bpacientes;

    @FXML
    private TextField TxtCedulaProfesional;

    @FXML
    private TextField TxtContraseña;

    @FXML
    void IniciarSesion(ActionEvent event) {

    }
    @FXML
    private void segundaVentana(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("segunda ventana.fxml"));
        Parent root = loader.load();


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void Doctor(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("crear perfil.fxml"));
        Parent root = loader.load();


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
