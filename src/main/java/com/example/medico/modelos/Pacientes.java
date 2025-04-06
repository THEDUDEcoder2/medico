package com.example.medico.modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pacientes{
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty fechaNacimiento = new SimpleStringProperty();
    private final StringProperty domicilio = new SimpleStringProperty();
    private final StringProperty numeroSeguro = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty tipoSangre = new SimpleStringProperty();
    private final ObservableList<Consulta> consultas = FXCollections.observableArrayList();

    public Pacientes() {}

    public Pacientes(String nombre, String fechaNacimiento, String domicilio,
                    String numeroSeguro, String telefono, String tipoSangre) {
        this.nombre.set(nombre);
        this.fechaNacimiento.set(fechaNacimiento);
        this.domicilio.set(domicilio);
        this.numeroSeguro.set(numeroSeguro);
        this.telefono.set(telefono);
        this.tipoSangre.set(tipoSangre);
    }


    public ObservableList<Consulta> getConsultas() {
        return consultas;
    }

    public void agregarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }


    public StringProperty nombreProperty() { return nombre; }
    public StringProperty fechaNacimientoProperty() { return fechaNacimiento; }
    public StringProperty domicilioProperty() { return domicilio; }
    public StringProperty numeroSeguroProperty() { return numeroSeguro; }
    public StringProperty telefonoProperty() { return telefono; }
    public StringProperty tipoSangreProperty() { return tipoSangre; }


    public String getNombre() { return nombre.get(); }
    public String getFechaNacimiento() { return fechaNacimiento.get(); }
    public String getDomicilio() { return domicilio.get(); }
    public String getNumeroSeguro() { return numeroSeguro.get(); }
    public String getTelefono() { return telefono.get(); }
    public String getTipoSangre() { return tipoSangre.get(); }


    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento.set(fechaNacimiento); }
    public void setDomicilio(String domicilio) { this.domicilio.set(domicilio); }
    public void setNumeroSeguro(String numeroSeguro) { this.numeroSeguro.set(numeroSeguro); }
    public void setTelefono(String telefono) { this.telefono.set(telefono); }
    public void setTipoSangre(String tipoSangre) { this.tipoSangre.set(tipoSangre); }
}