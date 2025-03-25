package com.example.medico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class segundaVentanaController {

    @FXML private TextField txtnombre;

    @FXML private TextField txtfechaDeNacimiento;

    @FXML private TextField txtDomicilio;

    @FXML private TextField txtNumeroDeSeguro;

    @FXML private TextField txtNumeroDeTelefono;

    @FXML private TextField txtespecialidad;

    @FXML private TextField txtBuscarPaciente;


    @FXML private ComboBox<String> MBtipoDeSangre;

    @FXML private TableView<Pacientes> TablePacientes;

    @FXML private TableColumn<Pacientes, String> columnaNombre;

    @FXML private TableColumn<Pacientes, String> columnaDomicilio;

    @FXML private TableColumn<Pacientes, String> columnaNumeroDeSeguro;

    @FXML private TableColumn<Pacientes, String> columnaTipoDeSangre;

    @FXML private TableColumn<Pacientes, String> columnaTelefono;


    private ObservableList<Pacientes> listaPacientes = FXCollections.observableArrayList();
    private Pacientes pacienteSeleccionado;

    @FXML
    public void initialize() {

        MBtipoDeSangre.getItems().addAll("O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-");


        configurarColumnas();


        TablePacientes.setItems(listaPacientes);


        TablePacientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    pacienteSeleccionado = newValue;
                });
    }

    private void configurarColumnas() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        columnaNumeroDeSeguro.setCellValueFactory(new PropertyValueFactory<>("numeroDeSeguro"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaTipoDeSangre.setCellValueFactory(new PropertyValueFactory<>("tipoDeSangre"));
    }


    @FXML
    private void Bagregar(ActionEvent event) {
        if(ERRORES()) {
            if (pacienteSeleccionado != null) {

                actualizarPaciente(pacienteSeleccionado);
                pacienteSeleccionado = null;
            } else {

                listaPacientes.add(crearPacienteDesdeFormulario());
            }
            limpiarCampos();
        }
    }


    @FXML
    private void Beditar(ActionEvent event) {
        if (pacienteSeleccionado != null) {
            cargarDatosEnFormulario(pacienteSeleccionado);
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para editar");
        }
    }


    @FXML
    private void  Beliminar(ActionEvent event) {
        if (pacienteSeleccionado != null) {
            listaPacientes.remove(pacienteSeleccionado);
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para eliminar");
        }
    }

    @FXML
    private void IrpaginaSiguiente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tercera ventana.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    private Pacientes crearPacienteDesdeFormulario() {
        return new Pacientes(
                txtnombre.getText(),
                txtDomicilio.getText(),
                txtNumeroDeSeguro.getText(),
                txtNumeroDeTelefono.getText(),
                MBtipoDeSangre.getValue()
        );
    }

    private void actualizarPaciente(Pacientes paciente) {
        paciente.setNombre(txtnombre.getText());
        paciente.setDomicilio(txtDomicilio.getText());
        paciente.setNumeroDeSeguro(txtNumeroDeSeguro.getText());
        paciente.setTelefono(txtNumeroDeTelefono.getText());
        paciente.setTipoDeSangre(MBtipoDeSangre.getValue());
        TablePacientes.refresh();
    }

    private void cargarDatosEnFormulario(Pacientes paciente) {
        txtnombre.setText(paciente.getNombre());
        txtDomicilio.setText(paciente.getDomicilio());
        txtNumeroDeSeguro.setText(paciente.getNumeroDeSeguro());
        txtNumeroDeTelefono.setText(paciente.getTelefono());
        MBtipoDeSangre.setValue(paciente.getTipoDeSangre());
    }

    private boolean ERRORES() {
        if(txtnombre.getText().isEmpty()) {
            mostrarAlerta("Error", "El nombre es obligatorio");
            return false;
        }
        if(txtNumeroDeTelefono.getText().isEmpty()) {
            mostrarAlerta("Error", "El teléfono es obligatorio");
            return false;
        }
        if(MBtipoDeSangre.getValue() == null) {
            mostrarAlerta("Error", "Seleccione un tipo de sangre");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtnombre.clear();
        txtDomicilio.clear();
        txtNumeroDeSeguro.clear();
        txtNumeroDeTelefono.clear();
        txtfechaDeNacimiento.clear();
        MBtipoDeSangre.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setEspecialidad(String especialidad) {
        txtespecialidad.setText(especialidad);
    }
}