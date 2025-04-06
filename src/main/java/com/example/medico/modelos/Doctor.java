package com.example.medico.modelos;

public class Doctor {
    private String nombre;
    private String cedula;
    private String especialidad;
    private String contraseña;

    public Doctor(String nombre, String cedula, String especialidad, String contraseña) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.contraseña = contraseña;
    }


    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getEspecialidad() { return especialidad; }
    public String getContraseña() { return contraseña; }


    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}