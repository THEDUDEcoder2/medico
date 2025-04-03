package com.example.medico.modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pacientes {
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty fechaDeNacimiento = new SimpleStringProperty();
    private final StringProperty domicilio = new SimpleStringProperty();
    private final StringProperty numeroSeguro = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty tipoSangre = new SimpleStringProperty();

    public Pacientes() {

    }

    public Pacientes(String nombre, String fechaDeNacimiento, String domicilio,
                     String numeroSeguro, String telefono, String tipoSangre) {
        this.nombre.set(nombre);
        this.fechaDeNacimiento.set(fechaDeNacimiento);
        this.domicilio.set(domicilio);
        this.numeroSeguro.set(numeroSeguro);
        this.telefono.set(telefono);
        this.tipoSangre.set(tipoSangre);
    }

    // Métodos getter como propiedades (necesarios para JavaFX)
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty fechaDeNacimientoProperty() { return fechaDeNacimiento; }
    public StringProperty domicilioProperty() { return domicilio; }
    public StringProperty numeroSeguroProperty() { return numeroSeguro; }
    public StringProperty telefonoProperty() { return telefono; }
    public StringProperty tipoSangreProperty() { return tipoSangre; }

    // Getters y setters normales
    public String getNombre() { return nombre.get(); }
    public String getFechaDeNacimiento() { return fechaDeNacimiento.get(); }
    public String getDomicilio() { return domicilio.get(); }
    public String getNumeroSeguro() { return numeroSeguro.get(); }
    public String getTelefono() { return telefono.get(); }
    public String getTipoSangre() { return tipoSangre.get(); }

    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public void setFechaDeNacimiento(String fecha) { this.fechaDeNacimiento.set(fecha); }
    public void setDomicilio(String domicilio) { this.domicilio.set(domicilio); }
    public void setNumeroSeguro(String numero) { this.numeroSeguro.set(numero); }
    public void setTelefono(String telefono) { this.telefono.set(telefono); }
    public void setTipoSangre(String tipo) { this.tipoSangre.set(tipo); }
}