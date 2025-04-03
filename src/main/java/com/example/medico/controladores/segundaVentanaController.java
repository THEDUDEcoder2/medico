package com.example.medico.controladores;

import com.example.medico.HelloApplication;
import com.example.medico.modelos.Pacientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class segundaVentanaController {

    @FXML private TextField txtNombre;
    @FXML private DatePicker txtFechaNacimiento;
    @FXML private TextField txtDomicilio;
    @FXML private TextField txtNumeroSeguro;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtBuscar;
    @FXML private ComboBox<String> comboTipoSangre;

    // Tabla y columnas (solo las que existen en el FXML)
    @FXML private TableView<Pacientes> tablaPacientes;
    @FXML private TableColumn<Pacientes, String> colNombre;
    @FXML private TableColumn<Pacientes, String> colDomicilio;
    @FXML private TableColumn<Pacientes, String> colSeguro;
    @FXML private TableColumn<Pacientes, String> colTelefono;
    @FXML private TableColumn<Pacientes, String> colSangre;

    private ObservableList<Pacientes> listaPacientes = FXCollections.observableArrayList();
    private Pacientes pacienteSeleccionado;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        // Configuración inicial
        comboTipoSangre.getItems().addAll("O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-");
        txtEspecialidad.setText(HelloApplication.getDoctorActual().getEspecialidad());
        txtEspecialidad.setEditable(false);

        // Configurar tabla
        configurarTabla();

        // Configurar búsqueda
        configurarBusqueda();

        // Listener para selección de pacientes
        tablaPacientes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    pacienteSeleccionado = newSelection;
                    if (newSelection != null) {
                        cargarDatosFormulario(newSelection);
                    }
                });
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        colSeguro.setCellValueFactory(new PropertyValueFactory<>("numeroSeguro"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colSangre.setCellValueFactory(new PropertyValueFactory<>("tipoSangre"));

        tablaPacientes.setItems(listaPacientes);
    }

    private void configurarBusqueda() {
        FilteredList<Pacientes> filtro = new FilteredList<>(listaPacientes, p -> true);
        txtBuscar.textProperty().addListener((obs, oldVal, newVal) -> {
            filtro.setPredicate(paciente -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String lowerCaseFilter = newVal.toLowerCase();
                return paciente.getNombre().toLowerCase().contains(lowerCaseFilter) ||
                        paciente.getNumeroSeguro().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Pacientes> datosOrdenados = new SortedList<>(filtro);
        datosOrdenados.comparatorProperty().bind(tablaPacientes.comparatorProperty());
        tablaPacientes.setItems(datosOrdenados);
    }

    @FXML
    private void agregarPaciente(ActionEvent event) {
        try {
            validarCampos();

            Pacientes paciente = new Pacientes(
                    txtNombre.getText(),
                    txtFechaNacimiento.getValue().format(dateFormatter), // Se guarda pero no se muestra
                    txtDomicilio.getText(),
                    txtNumeroSeguro.getText(),
                    txtTelefono.getText(),
                    comboTipoSangre.getValue()
            );

            if (pacienteSeleccionado != null) {
                actualizarPaciente(pacienteSeleccionado);
            } else {
                listaPacientes.add(paciente);
            }

            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }

    @FXML
    private void editarPaciente(ActionEvent event) {
        if (pacienteSeleccionado != null) {
            cargarDatosFormulario(pacienteSeleccionado);
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para editar");
        }
    }

    @FXML
    private void eliminarPaciente(ActionEvent event) {
        if (pacienteSeleccionado != null) {
            listaPacientes.remove(pacienteSeleccionado);
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un paciente para eliminar");
        }
    }

    @FXML
    private void abrirTerceraVentana(ActionEvent event) {
        try {
            if (pacienteSeleccionado == null) {
                mostrarAlerta("Error", "Seleccione un paciente primero");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medico/views/tercera ventana.fxml"));
            Parent root = loader.load();

            terceraVentanaController controller = loader.getController();
            controller.setPaciente(
                    pacienteSeleccionado.getNombre(),
                    pacienteSeleccionado.getFechaDeNacimiento(), // Se envía a la tercera ventana
                    txtEspecialidad.getText(),
                    HelloApplication.getDoctorActual().getNombre()
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir la ventana: " + e.getMessage());
        }
    }

    private void validarCampos() throws Exception {
        if (txtNombre.getText().isEmpty() ||
                txtFechaNacimiento.getValue() == null ||
                txtDomicilio.getText().isEmpty() ||
                txtNumeroSeguro.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() ||
                comboTipoSangre.getValue() == null) {
            throw new Exception("Todos los campos son obligatorios");
        }
    }

    private void cargarDatosFormulario(Pacientes paciente) {
        txtNombre.setText(paciente.getNombre());
        txtFechaNacimiento.setValue(LocalDate.parse(paciente.getFechaDeNacimiento(), dateFormatter));
        txtDomicilio.setText(paciente.getDomicilio());
        txtNumeroSeguro.setText(paciente.getNumeroSeguro());
        txtTelefono.setText(paciente.getTelefono());
        comboTipoSangre.setValue(paciente.getTipoSangre());
    }

    private void actualizarPaciente(Pacientes paciente) {
        paciente.setNombre(txtNombre.getText());
        paciente.setFechaDeNacimiento(txtFechaNacimiento.getValue().format(dateFormatter));
        paciente.setDomicilio(txtDomicilio.getText());
        paciente.setNumeroSeguro(txtNumeroSeguro.getText());
        paciente.setTelefono(txtTelefono.getText());
        paciente.setTipoSangre(comboTipoSangre.getValue());
        tablaPacientes.refresh();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtFechaNacimiento.setValue(null);
        txtDomicilio.clear();
        txtNumeroSeguro.clear();
        txtTelefono.clear();
        comboTipoSangre.getSelectionModel().clearSelection();
        pacienteSeleccionado = null;
        tablaPacientes.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}