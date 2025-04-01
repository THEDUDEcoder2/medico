package com.example.medico;

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
}