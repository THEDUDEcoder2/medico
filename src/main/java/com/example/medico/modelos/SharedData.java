package com.example.medico.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.stream.Collectors;

public class SharedData {
    private static SharedData instance;
    private Doctor doctorActual;
    private final ObservableList<Pacientes> pacientes = FXCollections.observableArrayList();


    private SharedData() {}


    public static synchronized SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }


    public Doctor getDoctorActual() {
        return doctorActual;
    }

    public void setDoctorActual(Doctor doctor) {
        this.doctorActual = doctor;
    }


    public ObservableList<Pacientes> getTodosLosPacientes() {
        return FXCollections.unmodifiableObservableList(pacientes);
    }

    public void agregarPaciente(Pacientes paciente) {
        if (paciente != null && !existePaciente(paciente)) {
            pacientes.add(paciente);
        }
    }

    public boolean eliminarPaciente(Pacientes paciente) {
        if (paciente == null) return false;
        return pacientes.remove(paciente);
    }


    public Pacientes buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return null;

        return pacientes.stream()
                .filter(p -> p.getNombre() != null &&
                        p.getNombre().equalsIgnoreCase(nombre.trim()))
                .findFirst()
                .orElse(null);
    }

    public Pacientes buscarPorNumeroSeguro(String numeroSeguro) {
        if (numeroSeguro == null || numeroSeguro.trim().isEmpty()) return null;

        return pacientes.stream()
                .filter(p -> p.getNumeroSeguro() != null &&
                        p.getNumeroSeguro().equals(numeroSeguro.trim()))
                .findFirst()
                .orElse(null);
    }


    private boolean existePaciente(Pacientes paciente) {
        if (paciente == null) return false;
        return pacientes.stream()
                .anyMatch(p -> p.getNumeroSeguro() != null &&
                        p.getNumeroSeguro().equals(paciente.getNumeroSeguro()));
    }

    public void limpiarDatos() {
        doctorActual = null;
        pacientes.clear();
    }


    public int cantidadPacientes() {
        return pacientes.size();
    }

    public ObservableList<Pacientes> buscarPacientes(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return FXCollections.observableArrayList(pacientes);
        }

        String busqueda = criterio.trim().toLowerCase();
        return pacientes.stream()
                .filter(p -> (p.getNombre() != null && p.getNombre().toLowerCase().contains(busqueda)) ||
                        (p.getNumeroSeguro() != null && p.getNumeroSeguro().contains(busqueda)))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}