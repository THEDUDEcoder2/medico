package com.example.medico;

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
    @FXML private TextField txtPresionArterial;
    @FXML private TextField txtPulsaciones;
    @FXML private TextField txtAlergias;
    @FXML private TextArea txtRazonConsulta;
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
    @FXML private Button btnNuevaConsulta;
    @FXML private Button btnRegresar;

    private ObservableList<Consulta> historialConsultas = FXCollections.observableArrayList();
    private Consulta consultaSeleccionada;
    private final DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        configurarTabla();
        txtFecha.setText(LocalDate.now().format(fechaFormatter));
        txtHora.setText(LocalTime.now().format(horaFormatter));
    }

    private void configurarTabla() {
        colFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFecha().format(fechaFormatter)));
        colHora.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHora().format(horaFormatter)));
        colEspecialista.setCellValueFactory(new PropertyValueFactory<>("especialista"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colDiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));

        tablaConsultas.setItems(historialConsultas);
        tablaConsultas.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    consultaSeleccionada = newSelection;
                    if (newSelection != null) {
                        cargarDatosConsulta(newSelection);
                    }
                });
    }

    public void setPaciente(String nombre, String fechaNacimiento, String especialidad, String nombreDoctor) {
        txtPaciente.setText(nombre);
        txtFechaNacimiento.setText(fechaNacimiento);
        txtEspecialista.setText(nombreDoctor + " - " + especialidad);
        txtPaciente.setEditable(false);
        txtFechaNacimiento.setEditable(false);
        txtEspecialista.setEditable(false);
        cargarHistorialPaciente(nombre);
    }

    private void cargarHistorialPaciente(String nombrePaciente) {
        historialConsultas.clear();
    }

    @FXML
    private void guardarConsulta(ActionEvent event) {
        try {
            if (validarCampos()) {
                Consulta consulta = crearConsultaDesdeFormulario();
                if (consultaSeleccionada != null) {
                    historialConsultas.set(historialConsultas.indexOf(consultaSeleccionada), consulta);
                } else {
                    historialConsultas.add(consulta);
                }
                mostrarAlerta("Éxito", "Consulta guardada correctamente");
                nuevaConsulta(null);
            }
        } catch (DateTimeParseException e) {
            mostrarAlerta("Error", "Formato de fecha/hora incorrecto. Use DD/MM/AAAA y HH:mm");
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al guardar: " + e.getMessage());
        }
    }

    private Consulta crearConsultaDesdeFormulario() throws NumberFormatException {
        return new Consulta(
                txtPaciente.getText(),
                LocalDate.parse(txtFecha.getText(), fechaFormatter),
                LocalTime.parse(txtHora.getText(), horaFormatter),
                txtEspecialista.getText(),
                txtRazonConsulta.getText(),
                txtDiagnostico.getText(),
                txtFechaNacimiento.getText(),
                Integer.parseInt(txtPulsaciones.getText()),
                Double.parseDouble(txtTemperatura.getText()),
                txtAlergias.getText(),
                Double.parseDouble(txtPeso.getText()),
                Double.parseDouble(txtAltura.getText()),
                txtPresionArterial.getText(),
                txtReceta.getText(),
                txtSintomas.getText(),
                txtObservaciones.getText()
        );
    }

    @FXML
    private void editarConsulta(ActionEvent event) {
        if (consultaSeleccionada != null) {
            habilitarCampos(true);
        } else {
            mostrarAlerta("Error", "Seleccione una consulta para editar");
        }
    }

    @FXML
    private void nuevaConsulta(ActionEvent event) {
        consultaSeleccionada = null;
        limpiarFormulario();
        habilitarCampos(true);
        txtFecha.setText(LocalDate.now().format(fechaFormatter));
        txtHora.setText(LocalTime.now().format(horaFormatter));
    }

    @FXML
    private void regresar(ActionEvent event) {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }

    private void cargarDatosConsulta(Consulta consulta) {
        txtFecha.setText(consulta.getFecha().format(fechaFormatter));
        txtHora.setText(consulta.getHora().format(horaFormatter));
        txtEspecialista.setText(consulta.getEspecialista());
        txtRazonConsulta.setText(consulta.getMotivo());
        txtDiagnostico.setText(consulta.getDiagnostico());
        txtPulsaciones.setText(String.valueOf(consulta.getPulsaciones()));
        txtTemperatura.setText(String.valueOf(consulta.getTemperatura()));
        txtAlergias.setText(consulta.getAlergias());
        txtPeso.setText(String.valueOf(consulta.getPeso()));
        txtAltura.setText(String.valueOf(consulta.getAltura()));
        txtPresionArterial.setText(consulta.getPresionArterial());
        txtReceta.setText(consulta.getReceta());
        txtSintomas.setText(consulta.getSintomas());
        txtObservaciones.setText(consulta.getObservaciones());
        habilitarCampos(false);
    }

    private boolean validarCampos() {
        if (txtRazonConsulta.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "La razón de consulta es obligatoria");
            return false;
        }
        if (txtDiagnostico.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "El diagnóstico es obligatorio");
            return false;
        }
        try {
            LocalDate.parse(txtFecha.getText(), fechaFormatter);
            LocalTime.parse(txtHora.getText(), horaFormatter);
        } catch (DateTimeParseException e) {
            mostrarAlerta("Error", "Formato de fecha/hora incorrecto. Use DD/MM/AAAA y HH:mm");
            return false;
        }
        return true;
    }

    private void limpiarFormulario() {
        txtRazonConsulta.clear();
        txtDiagnostico.clear();
        txtPeso.clear();
        txtAltura.clear();
        txtTemperatura.clear();
        txtPresionArterial.clear();
        txtPulsaciones.clear();
        txtAlergias.clear();
        txtSintomas.clear();
        txtObservaciones.clear();
        txtReceta.clear();
    }

    private void habilitarCampos(boolean habilitar) {
        txtRazonConsulta.setEditable(habilitar);
        txtDiagnostico.setEditable(habilitar);
        txtPeso.setEditable(habilitar);
        txtAltura.setEditable(habilitar);
        txtTemperatura.setEditable(habilitar);
        txtPresionArterial.setEditable(habilitar);
        txtPulsaciones.setEditable(habilitar);
        txtAlergias.setEditable(habilitar);
        txtSintomas.setEditable(habilitar);
        txtObservaciones.setEditable(habilitar);
        txtReceta.setEditable(habilitar);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}