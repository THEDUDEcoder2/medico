package com.example.medico;

public class Pacientes {
    private String nombre;
    private String domicilio;
    private String numeroDeSeguro;
    private String telefono;
    private String tipoDeSangre;

    public Pacientes(String nombre, String domicilio, String numeroDeSeguro, String telefono, String tipoDeSangre) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.numeroDeSeguro = numeroDeSeguro;
        this.telefono = telefono;
        this.tipoDeSangre = tipoDeSangre;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDomicilio() { return domicilio; }
    public String getNumeroDeSeguro() { return numeroDeSeguro; }
    public String getTelefono() { return telefono; }
    public String getTipoDeSangre() { return tipoDeSangre; }

    // Setters (necesarios para edición)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public void setNumeroDeSeguro(String numeroDeSeguro) { this.numeroDeSeguro = numeroDeSeguro; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setTipoDeSangre(String tipoDeSangre) { this.tipoDeSangre = tipoDeSangre; }
}