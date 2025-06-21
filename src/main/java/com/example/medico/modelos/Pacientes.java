package com.example.medico.modelos;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pacientes")
public class Pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fechaNacimiento;
    private String domicilio;
    private String numeroSeguro;
    private String telefono;
    private String tipoSangre;

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
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.numeroSeguro = numeroSeguro;
        this.telefono = telefono;
        this.tipoSangre = tipoSangre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public String getNumeroSeguro() { return numeroSeguro; }
    public void setNumeroSeguro(String numeroSeguro) { this.numeroSeguro = numeroSeguro; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTipoSangre() { return tipoSangre; }
    public void setTipoSangre(String tipoSangre) { this.tipoSangre = tipoSangre; }

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
