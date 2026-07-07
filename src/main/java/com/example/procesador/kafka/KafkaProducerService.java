package com.example.procesador.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.procesador.dto.AlertaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    // Cambiamos el tipo de dato que maneja el template a AlertaDTO
    private final KafkaTemplate<String, AlertaDTO> kafkaTemplate;

    // Apuntamos a la propiedad que definimos para el tópico de salida
    @Value("${app.kafka.topic.output}")
    private String topic;

    public KafkaProducerService(KafkaTemplate<String, AlertaDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publica una alerta en el tópico 'alertas' de Kafka.
     *
     * @param alertaDTO Alerta generada tras detectar anomalía.
     */
    public void enviarAlerta(AlertaDTO alertaDTO) {

        kafkaTemplate.send(topic, alertaDTO);

        logger.info("========================================");
        logger.info("Alerta publicada en Kafka");
        logger.info("Topic      : {}", topic);
        logger.info("Paciente   : {}", alertaDTO.getNombrePaciente());
        logger.info("Tipo       : {}", alertaDTO.getEstado());
        logger.info("========================================");
    }
}
