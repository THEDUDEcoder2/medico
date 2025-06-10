package com.example.medico;

import com.example.medico.modelos.Doctor;
import com.example.medico.modelos.Pacientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SharedData implements Serializable {
    private static final long serialVersionUID = 1L;
    private static SharedData instance;
    private Doctor doctorActual;
    private final ObservableList<Doctor> doctores = FXCollections.observableArrayList();
    private final ObservableList<Pacientes> pacientes = FXCollections.observableArrayList();
    private static final String ARCHIVO_DATOS = "datos_medico.ser";


    private SharedData() {
        cargarDatos();
    }

    public static synchronized SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }


    public void agregarDoctor(Doctor doctor) {
        if (doctor != null && !existeDoctor(doctor)) {
            doctores.add(doctor);
        }
    }

    public Doctor buscarDoctorPorCedula(String cedula) {
        return doctores.stream()
                .filter(d -> d.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }
    public ObservableList<Doctor> getTodosLosDoctores() {
        return FXCollections.unmodifiableObservableList(doctores);
    }


    public void agregarPaciente(Pacientes paciente) {
        if (paciente != null && !existePaciente(paciente.getNumeroSeguro())) {
            pacientes.add(paciente);
            guardarDatos();
        }
    }

    public ObservableList<Pacientes> getTodosLosPacientes() {
        return FXCollections.unmodifiableObservableList(pacientes);
    }

    public ObservableList<Pacientes> buscarPacientes(String criterio) {
        String busqueda = criterio.toLowerCase();
        return pacientes.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(busqueda) ||
                        p.getNumeroSeguro().contains(busqueda))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }


    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_DATOS))) {

            oos.writeObject(new ArrayList<>(doctores));
            oos.writeObject(new ArrayList<>(pacientes));

        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        File file = new File(ARCHIVO_DATOS);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {

                List<Doctor> doctoresCargados = (List<Doctor>) ois.readObject();
                List<Pacientes> pacientesCargados = (List<Pacientes>) ois.readObject();

                doctores.setAll(doctoresCargados);
                pacientes.setAll(pacientesCargados);

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar datos: " + e.getMessage());
            }
        }
    }


    private boolean existeDoctor(Doctor cedula) {
        return doctores.stream().anyMatch(d -> d.getCedula().equals(cedula));
    }

    public boolean existePaciente(String numeroSeguro) {
        return pacientes.stream().anyMatch(p -> p.getNumeroSeguro().equals(numeroSeguro));
    }


    public Doctor getDoctorActual() {
        return doctorActual;
    }

    public void setDoctorActual(Doctor doctor) {
        this.doctorActual = doctor;
    }
}