package com.example.medico.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    private String paciente;
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

    public Consulta(String paciente, LocalDate fecha, LocalTime hora,
                    String especialista, String motivo, String diagnostico,
                    String fechaNacimiento, String pulsaciones, String temperatura,
                    String alergias, String peso, String altura, String presionArterial,
                    String receta, String sintomas, String observaciones) {
        this.paciente = paciente;
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

    // Getters
    public String getPaciente() { return paciente; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getEspecialista() { return especialista; }
    public String getMotivo() { return motivo; }
    public String getDiagnostico() { return diagnostico; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getPulsaciones() { return pulsaciones; }
    public String getTemperatura() { return temperatura; }
    public String getAlergias() { return alergias; }
    public String getPeso() { return peso; }
    public String getAltura() { return altura; }
    public String getPresionArterial() { return presionArterial; }
    public String getReceta() { return receta; }
    public String getSintomas() { return sintomas; }
    public String getObservaciones() { return observaciones; }

    // Setters
    public void setPaciente(String paciente) { this.paciente = paciente; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public void setEspecialista(String especialista) { this.especialista = especialista; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setPulsaciones(String pulsaciones) { this.pulsaciones = pulsaciones; }
    public void setTemperatura(String temperatura) { this.temperatura = temperatura; }
    public void setAlergias(String alergias) { this.alergias = alergias; }
    public void setPeso(String peso) { this.peso = peso; }
    public void setAltura(String altura) { this.altura = altura; }
    public void setPresionArterial(String presionArterial) { this.presionArterial = presionArterial; }
    public void setReceta(String receta) { this.receta = receta; }
    public void setSintomas(String sintomas) { this.sintomas = sintomas; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public String toString() {
        return String.format("Consulta de %s - %s %s - Motivo: %s",
                paciente, fecha, hora, motivo);
    }
}