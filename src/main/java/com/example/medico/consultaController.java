package com.example.medico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class consultaController {

    @FXML
    private MenuButton MBespecialidad;

    private static String especialidadSeleccionada;

    @FXML
    public void initialize() {

        for (MenuItem item : MBespecialidad.getItems()) {
            item.setOnAction(event -> {
                especialidadSeleccionada = item.getText();
                MBespecialidad.setText(especialidadSeleccionada);
            });
        }
    }

    @FXML
    private void elegirEspecialidad(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        especialidadSeleccionada = item.getText();
        MBespecialidad.setText(especialidadSeleccionada);
    }

    @FXML
    private void segundaVentana(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("segunda ventana.fxml"));
        Parent root = loader.load();


        segundaVentanaController controller = loader.getController();
        controller.setEspecialidad(especialidadSeleccionada);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
