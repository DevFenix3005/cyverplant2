package com.cyver.plant.consumer.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.database.EnvironmentalMeasurementRepository;
import com.cyver.plant.database.PlantRepository;
import com.cyver.plant.utilities.maps.EnvironmentalMeasurementEntityMapper;

@Service
public class EnvironmentalMeasurementAvroConsumerService {

    private final Logger logger = LoggerFactory.getLogger(EnvironmentalMeasurementAvroConsumerService.class);

    private final EnvironmentalMeasurementEntityMapper environmentalMeasurementEntityMapper;

    private final EnvironmentalMeasurementRepository environmentalMeasurementRepository;

    private final PlantRepository plantRepository;

    public EnvironmentalMeasurementAvroConsumerService(
            final EnvironmentalMeasurementEntityMapper environmentalMeasurementEntityMapper,
            final EnvironmentalMeasurementRepository environmentalMeasurementRepository,
            final PlantRepository plantRepository) {
        this.environmentalMeasurementEntityMapper = environmentalMeasurementEntityMapper;
        this.environmentalMeasurementRepository = environmentalMeasurementRepository;
        this.plantRepository = plantRepository;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload final EnvironmentalMeasurementAvro value,
            @Header(KafkaHeaders.RECEIVED_KEY) final String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) final String topic) {
        logger.info("Consumed event from topic {}: key = {} | value = {}", topic, key, value);

        plantRepository.findById(UUID.fromString("218a79b8-4c89-44dd-b03d-d9748f8e8310"))
                .ifPresent(plant -> {
                    EnvironmentalMeasurement environmentalMeasurement = environmentalMeasurementEntityMapper.toEntity(value);
                    environmentalMeasurement.setPlant(plant);
                    environmentalMeasurementRepository.save(environmentalMeasurement);
                    logger.info("Saved environmental measurement with UUID: {}", environmentalMeasurement.getUuid());
                });

    }
}
