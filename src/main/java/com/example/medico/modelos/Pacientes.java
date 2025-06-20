package com.example.medico.modelos;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pacientes")
public class Pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty fechaNacimiento = new SimpleStringProperty();
    private final StringProperty domicilio = new SimpleStringProperty();
    private final StringProperty numeroSeguro = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty tipoSangre = new SimpleStringProperty();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "paciente_consulta",
            joinColumns = { @JoinColumn(name = "paciente_id") },
            inverseJoinColumns = { @JoinColumn(name = "consulta_id") }
    )
    private Set<Consulta> consultas = new HashSet<>();

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


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre.get(); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public String getFechaNacimiento() { return fechaNacimiento.get(); }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento.set(fechaNacimiento); }
    public String getDomicilio() { return domicilio.get(); }
    public void setDomicilio(String domicilio) { this.domicilio.set(domicilio); }
    public String getNumeroSeguro() { return numeroSeguro.get(); }
    public void setNumeroSeguro(String numeroSeguro) { this.numeroSeguro.set(numeroSeguro); }
    public String getTelefono() { return telefono.get(); }
    public void setTelefono(String telefono) { this.telefono.set(telefono); }
    public String getTipoSangre() { return tipoSangre.get(); }
    public void setTipoSangre(String tipoSangre) { this.tipoSangre.set(tipoSangre); }
    public Set<Consulta> getConsultas() { return consultas; }
    public void setConsultas(Set<Consulta> consultas) { this.consultas = consultas; }


    public void agregarConsulta(Consulta consulta) {
        this.consultas.add(consulta);
        consulta.getPacientes().add(this);
    }

    public void removerConsulta(Consulta consulta) {
        this.consultas.remove(consulta);
        consulta.getPacientes().remove(this);
    }
}