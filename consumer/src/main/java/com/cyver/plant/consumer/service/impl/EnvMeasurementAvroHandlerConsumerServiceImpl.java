package com.cyver.plant.consumer.service.impl;

import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.consumer.service.EnvMeasurementAvroHandlerConsumerService;
import com.cyver.plant.consumer.service.EnvironmentalMeasurementService;
import com.cyver.plant.consumer.service.PlantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnvMeasurementAvroHandlerConsumerServiceImpl implements EnvMeasurementAvroHandlerConsumerService {

    private final EnvironmentalMeasurementService environmentalMeasurementService;

    private final PlantService plantService;

    public EnvMeasurementAvroHandlerConsumerServiceImpl(
            final EnvironmentalMeasurementService environmentalMeasurementService,
            final PlantService plantService) {
        this.environmentalMeasurementService = environmentalMeasurementService;
        this.plantService = plantService;
    }

    @Override
    public void handler(final EnvironmentalMeasurementAvro value, final String key, final String topic) {
        log.info("Received plant: {}", value.getPlantInfo());
        log.info("Consumed event from topic {}: key = {} | value = {}", topic, key, value);

        Plant plant = plantService.creatIfNotExists(value.getPlantInfo());
        EnvironmentalMeasurement environmentalMeasurement =
                environmentalMeasurementService.convertAndSaveEnvironmentalMeasurement(plant, value);
        log.info("Saved environmental measurement with UUID: {}", environmentalMeasurement.getUuid());

    }
}
