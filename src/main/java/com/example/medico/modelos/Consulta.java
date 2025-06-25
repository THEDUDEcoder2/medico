package com.example.medico.modelos;
import com.example.medico.services.ConsultaServices;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "consultas")
public class Consulta {


    private Long id;


    private LocalDate fecha;
    private LocalTime hora;
    private String especialista;
    private String motivo;
    private String diagnostico;
    private String fechaNacimiento;
    private String pulsaciones;
    private String temperatura;
    private String alergias;
    private String peso;
    private String altura;
    private String presionArterial;
    private String receta;
    private String sintomas;
    private String observaciones;
    private Doctor doctor;

    public Consulta() {}


    public Consulta( LocalDate fecha, LocalTime hora,
                    String especialista, String motivo, String diagnostico,
                    String fechaNacimiento, String pulsaciones, String temperatura,
                    String alergias, String peso, String altura, String presionArterial,
                    String receta, String sintomas, String observaciones) {

        this.fecha = fecha;
        this.hora = hora;
        this.especialista = especialista;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.fechaNacimiento = fechaNacimiento;
        this.pulsaciones = pulsaciones;
        this.temperatura = temperatura;
        this.alergias = alergias;
        this.peso = peso;
        this.altura = altura;
        this.presionArterial = presionArterial;
        this.receta = receta;
        this.sintomas = sintomas;
        this.observaciones = observaciones;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEspecialista() { return especialista; }
    public void setEspecialista(String especialista) { this.especialista = especialista; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getPulsaciones() { return pulsaciones; }
    public void setPulsaciones(String pulsaciones) { this.pulsaciones = pulsaciones; }

    public String getTemperatura() { return temperatura; }
    public void setTemperatura(String temperatura) { this.temperatura = temperatura; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }

    public String getAltura() { return altura; }
    public void setAltura(String altura) { this.altura = altura; }

    public String getPresionArterial() { return presionArterial; }
    public void setPresionArterial(String presionArterial) { this.presionArterial = presionArterial; }

    public String getReceta() { return receta; }
    public void setReceta(String receta) { this.receta = receta; }

    public String getSintomas() { return sintomas; }
    public void setSintomas(String sintomas) { this.sintomas = sintomas; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    @OneToOne
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() { return doctor ;}
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        if (doctor != null && doctor.getConsulta() != this) {
            doctor.setConsulta(this);
        }
        }
    @Override
    public String toString() {
        return String.format("Consulta de %s - %s %s - Motivo: %s",
                paciente != null ? paciente.getNombre() : "N/A",
                fecha, hora, motivo);
    }
}
