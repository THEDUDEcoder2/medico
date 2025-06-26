package com.example.medico.modelos;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Doctores")
public class Doctor {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idDoctor;

    private String nombre;
    private String cedula;
    private String especialidad;
    private String contraseña;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Consulta consulta;

    public Doctor() {}

    public Doctor(String nombre, String cedula, String especialidad, String contraseña) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.contraseña = contraseña;
    }


    public int getIdDoctor() { return idDoctor; }
    public void setIdDoctor(int idDoctor) { this.idDoctor = idDoctor; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
}