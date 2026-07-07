package com.example.procesador.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SenalVitalDTO {

    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String nombrePaciente;

    @NotBlank(message = "La habitación es obligatoria")
    private String habitacion;

    @NotBlank(message = "El color de alerta es obligatorio")
    private String colorAlerta;

    @NotBlank(message = "Los signos vitales son obligatorios")
    private String signosVitales;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public SenalVitalDTO() {}

    public SenalVitalDTO(String nombrePaciente, String habitacion, String colorAlerta,
            String signosVitales, String estado) {
        this.nombrePaciente = nombrePaciente;
        this.habitacion = habitacion;
        this.colorAlerta = colorAlerta;
        this.signosVitales = signosVitales;
        this.estado = estado;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getColorAlerta() {
        return colorAlerta;
    }

    public void setColorAlerta(String colorAlerta) {
        this.colorAlerta = colorAlerta;
    }

    public String getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(String signosVitales) {
        this.signosVitales = signosVitales;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SenalVitalDTO{" + "nombrePaciente='" + nombrePaciente + '\'' + ", habitacion='"
                + habitacion + '\'' + ", colorAlerta='" + colorAlerta + '\'' + ", signosVitales='"
                + signosVitales + '\'' + ", estado='" + estado + '\'' + '}';
    }

}
