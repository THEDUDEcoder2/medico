package com.example.medico.controladores;

import com.example.medico.modelos.Consulta;
import com.example.medico.modelos.Pacientes;
import com.example.medico.modelos.SharedData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class terceraVentanaController {
    @FXML private TextField txtPaciente;
    @FXML private TextField txtFechaNacimiento;
    @FXML private TextField txtPeso;
    @FXML private TextField txtAltura;
    @FXML private TextField txtTemperatura;
    @FXML private TextField txtPresion;
    @FXML private TextField txtPulsaciones;
    @FXML private TextField txtAlergias;
    @FXML private TextArea txtRazon;
    @FXML private TextArea txtSintomas;
    @FXML private TextArea txtObservaciones;
    @FXML private TextArea txtDiagnostico;
    @FXML private TextField txtFecha;
    @FXML private TextField txtHora;
    @FXML private TextArea txtReceta;
    @FXML private TextField txtEspecialista;
    @FXML private TableView<Consulta> tablaConsultas;
    @FXML private TableColumn<Consulta, String> colFecha;
    @FXML private TableColumn<Consulta, String> colHora;
    @FXML private TableColumn<Consulta, String> colEspecialista;
    @FXML private TableColumn<Consulta, String> colMotivo;
    @FXML private TableColumn<Consulta, String> colDiagnostico;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnNueva;
    @FXML private Button btnRegresar;

    private final SharedData sharedData = SharedData.getInstance();
    private ObservableList<Consulta> historial = FXCollections.observableArrayList();
    private Consulta consultaSeleccionada;
    private Pacientes paciente;
    private final DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        configurarTabla();
        txtFecha.setText(LocalDate.now().format(fechaFormatter));
        txtHora.setText(LocalTime.now().format(horaFormatter));
    }

    public void setPaciente(Pacientes paciente, String text, String nombre) {
        this.paciente = paciente;
        txtPaciente.setText(paciente.getNombre());
        txtFechaNacimiento.setText(paciente.getFechaNacimiento());
        txtEspecialista.setText(
                sharedData.getDoctorActual().getNombre() + " - " +
                        sharedData.getDoctorActual().getEspecialidad()
        );
        bloquearCamposPaciente();
        historial.setAll(paciente.getConsultas());
    }

    private void configurarTabla() {
        colFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFecha().format(fechaFormatter)));
        colHora.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHora().format(horaFormatter)));
        colEspecialista.setCellValueFactory(new PropertyValueFactory<>("especialista"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colDiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));


        tablaConsultas.setItems(historial);
        tablaConsultas.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    consultaSeleccionada = newVal;
                    if (newVal != null) cargarDatosConsulta(newVal);
                });
    }

    @FXML
    private void guardarConsulta(ActionEvent event) {
        try {
            validarCampos();

            Consulta consulta = new Consulta(
                    txtPaciente.getText(),
                    LocalDate.parse(txtFecha.getText(), fechaFormatter),
                    LocalTime.parse(txtHora.getText(), horaFormatter),
                    txtEspecialista.getText(),
                    txtRazon.getText(),
                    txtDiagnostico.getText(),
                    txtFechaNacimiento.getText(),
                    txtPulsaciones.getText(),
                    txtTemperatura.getText(),
                    txtAlergias.getText(),
                    txtPeso.getText(),
                    txtAltura.getText(),
                    txtPresion.getText(),
                    txtReceta.getText(),
                    txtSintomas.getText(),
                    txtObservaciones.getText()
            );

            if (consultaSeleccionada != null) {
                actualizarConsulta(consulta);
            } else {
                paciente.agregarConsulta(consulta);
                historial.add(consulta);
            }

            mostrarAlerta("Exito", "Consulta guardada correctamente", Alert.AlertType.INFORMATION);
            nuevaConsulta();
        } catch (DateTimeParseException e) {
            mostrarAlerta("Error", "Formato de fecha/hora incorrecto (DD/MM/AAAA HH:MM)", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void editarConsulta(ActionEvent event) {
        if (consultaSeleccionada != null) {
            habilitarCampos(true);
        } else {
            mostrarAlerta("Error", "Seleccione una consulta para editar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void nuevaConsulta(ActionEvent event) {
        nuevaConsulta();
    }

    @FXML
    private void regresar(ActionEvent event) {
        ((Stage) btnRegresar.getScene().getWindow()).close();
    }

    private void cargarDatosConsulta(Consulta consulta) {
        txtFecha.setText(consulta.getFecha().format(fechaFormatter));
        txtHora.setText(consulta.getHora().format(horaFormatter));
        txtRazon.setText(consulta.getMotivo());
        txtDiagnostico.setText(consulta.getDiagnostico());
        txtPeso.setText(consulta.getPeso());
        txtAltura.setText(consulta.getAltura());
        txtTemperatura.setText(consulta.getTemperatura());
        txtPresion.setText(consulta.getPresionArterial());
        txtPulsaciones.setText(consulta.getPulsaciones());
        txtAlergias.setText(consulta.getAlergias());
        txtSintomas.setText(consulta.getSintomas());
        txtObservaciones.setText(consulta.getObservaciones());
        txtReceta.setText(consulta.getReceta());
        habilitarCampos(false);
    }

    private void actualizarConsulta(Consulta nuevaConsulta) {
        consultaSeleccionada.setFecha(nuevaConsulta.getFecha());
        consultaSeleccionada.setHora(nuevaConsulta.getHora());
        consultaSeleccionada.setMotivo(nuevaConsulta.getMotivo());
        consultaSeleccionada.setDiagnostico(nuevaConsulta.getDiagnostico());
        consultaSeleccionada.setPeso(nuevaConsulta.getPeso());
        consultaSeleccionada.setAltura(nuevaConsulta.getAltura());
        consultaSeleccionada.setTemperatura(nuevaConsulta.getTemperatura());
        consultaSeleccionada.setPresionArterial(nuevaConsulta.getPresionArterial());
        consultaSeleccionada.setPulsaciones(nuevaConsulta.getPulsaciones());
        consultaSeleccionada.setAlergias(nuevaConsulta.getAlergias());
        consultaSeleccionada.setSintomas(nuevaConsulta.getSintomas());
        consultaSeleccionada.setObservaciones(nuevaConsulta.getObservaciones());
        consultaSeleccionada.setReceta(nuevaConsulta.getReceta());
        tablaConsultas.refresh();
    }

    private void nuevaConsulta() {
        consultaSeleccionada = null;
        limpiarFormulario();
        habilitarCampos(true);
        txtFecha.setText(LocalDate.now().format(fechaFormatter));
        txtHora.setText(LocalTime.now().format(horaFormatter));
    }

    private void validarCampos() throws Exception {
        if (txtRazon.getText().isEmpty() || txtDiagnostico.getText().isEmpty()|| txtPeso.getText().isEmpty() || txtAltura.getText().isEmpty()
                || txtTemperatura.getText().isEmpty() || txtPresion.getText().isEmpty() || txtPulsaciones.getText().isEmpty()
                || txtAlergias.getText().isEmpty() || txtSintomas.getText().isEmpty() || txtObservaciones.getText().isEmpty()
                || txtReceta.getText().isEmpty()) {
            throw new Exception("todos los campos son obligatorios");
        }
    }

    private void limpiarFormulario() {
        txtRazon.clear();
        txtDiagnostico.clear();
        txtPeso.clear();
        txtAltura.clear();
        txtTemperatura.clear();
        txtPresion.clear();
        txtPulsaciones.clear();
        txtAlergias.clear();
        txtSintomas.clear();
        txtObservaciones.clear();
        txtReceta.clear();
    }

    private void habilitarCampos(boolean habilitar) {
        txtRazon.setEditable(habilitar);
        txtDiagnostico.setEditable(habilitar);
        txtPeso.setEditable(habilitar);
        txtAltura.setEditable(habilitar);
        txtTemperatura.setEditable(habilitar);
        txtPresion.setEditable(habilitar);
        txtPulsaciones.setEditable(habilitar);
        txtAlergias.setEditable(habilitar);
        txtSintomas.setEditable(habilitar);
        txtObservaciones.setEditable(habilitar);
        txtReceta.setEditable(habilitar);
    }

    private void bloquearCamposPaciente() {
        txtPaciente.setEditable(false);
        txtFechaNacimiento.setEditable(false);
        txtEspecialista.setEditable(false);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}