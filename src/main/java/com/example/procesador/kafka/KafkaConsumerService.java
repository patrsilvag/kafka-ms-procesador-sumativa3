package com.example.procesador.kafka;

import com.example.procesador.dto.SenalVitalDTO;
import com.example.procesador.service.ProcesadorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final ProcesadorService procesadorService;

    public KafkaConsumerService(ProcesadorService procesadorService) {
        this.procesadorService = procesadorService;
    }

    // Escucha el tópico que definiste en application.properties
    @KafkaListener(topics = "${app.kafka.topic.input}", groupId = "grupo-procesador-alertas")
    public void consume(SenalVitalDTO senalVital) {
        logger.info("Señal vital recibida para el paciente: {}", senalVital.getNombrePaciente());

        // Delegamos el análisis al servicio de lógica
        procesadorService.procesar(senalVital);
    }
}
