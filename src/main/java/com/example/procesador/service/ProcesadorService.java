package com.example.procesador.service;

import com.example.procesador.dto.AlertaDTO;
import com.example.procesador.dto.SenalVitalDTO;
import com.example.procesador.kafka.KafkaProducerService;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProcesadorService {

    private static final Logger logger = LoggerFactory.getLogger(ProcesadorService.class); // Declaración
                                                                                           // necesaria
    private final KafkaProducerService kafkaProducerService;

    public ProcesadorService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    private int contadorRecibidos = 0;
    private int contadorAnomalias = 0;

    public void procesar(SenalVitalDTO senal) {
        String signos = senal.getSignosVitales();
        String colorDetectado = null;

        if (signos.contains("Frecuencia cardíaca")) {
            int fc = Integer.parseInt(signos.replaceAll("[^0-9]", ""));
            if (fc >= 150)
                colorDetectado = "ROJO";
            else if (fc >= 130)
                colorDetectado = "AMARILLO";
        } else if (signos.contains("Presión arterial")) {
            int sistolica = Integer.parseInt(signos.split(":")[1].trim().split("/")[0]);
            if (sistolica > 140)
                colorDetectado = "ROJO";
            else if (sistolica > 130)
                colorDetectado = "AMARILLO";
        } else if (signos.contains("Temperatura")) {
            String tempStr = signos.replaceAll(",", ".").replaceAll("[^0-9.]", "");
            double temp = Double.parseDouble(tempStr);
            if (temp > 39.0)
                colorDetectado = "ROJO";
            else if (temp > 36.0)
                colorDetectado = "AMARILLO";
        }

        contadorRecibidos++;

        if (colorDetectado != null) {
            AlertaDTO alerta = new AlertaDTO();
            alerta.setNombrePaciente(senal.getNombrePaciente());
            alerta.setHabitacion(senal.getHabitacion());
            alerta.setColorAlerta(colorDetectado);
            alerta.setSignosVitales(signos);
            alerta.setEstado("Pendiente");
            alerta.setFechaHoraRegistro(LocalDateTime.now());

            kafkaProducerService.enviarAlerta(alerta);
            contadorAnomalias++;
        }

        
        logger.info("PROCESADO: Recibidos={}, Anomalías={}", contadorRecibidos, contadorAnomalias);
    }
}
