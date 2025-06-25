package com.example.medico.modelos;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table( name = "Doctores" )

public class Doctor {
    private int idDoctor;
    private String nombre;
    private String cedula;
    private String especialidad;
    private String contraseña;
    private Consulta consulta;

    public Doctor(String nombre, String cedula, String especialidad, String contraseña) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.contraseña = contraseña;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getIdDoctor() {
        return idDoctor;
    }
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getEspecialidad() { return especialidad; }
    public String getContraseña() { return contraseña; }

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    public Consulta getConsulta() {
        return consulta;
    }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }
    public void setConsulta(Consulta consulta) {this.consulta = consulta;}
}