package com.example.procesador.dto;

import lombok.Data; // Importante
import java.time.LocalDateTime;

@Data // Esto genera automáticamente todos los getters y setters
public class AlertaDTO {
    private String nombrePaciente;
    private String habitacion;
    private String colorAlerta;
    private String signosVitales;
    private String estado;
    private LocalDateTime fechaHoraRegistro;
}
