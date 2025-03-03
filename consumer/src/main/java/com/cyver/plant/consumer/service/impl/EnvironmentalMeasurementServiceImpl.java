package com.cyver.plant.consumer.service.impl;

import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.consumer.service.EnvironmentalMeasurementService;
import com.cyver.plant.database.EnvironmentalMeasurementRepository;
import com.cyver.plant.utilities.maps.EnvironmentalMeasurementEntityMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnvironmentalMeasurementServiceImpl implements EnvironmentalMeasurementService {

    private final EnvironmentalMeasurementEntityMapper environmentalMeasurementEntityMapper;

    private final EnvironmentalMeasurementRepository environmentalMeasurementRepository;

    public EnvironmentalMeasurementServiceImpl(final EnvironmentalMeasurementEntityMapper environmentalMeasurementEntityMapper,
            final EnvironmentalMeasurementRepository environmentalMeasurementRepository) {
        this.environmentalMeasurementEntityMapper = environmentalMeasurementEntityMapper;
        this.environmentalMeasurementRepository = environmentalMeasurementRepository;
    }

    @Override
    public EnvironmentalMeasurement convertAndSaveEnvironmentalMeasurement(
            final Plant plant,
            final EnvironmentalMeasurementAvro environmentalMeasurementAvro) {
        EnvironmentalMeasurement environmentalMeasurement = environmentalMeasurementEntityMapper.toEntity(environmentalMeasurementAvro);
        environmentalMeasurement.setPlant(plant);
        return environmentalMeasurementRepository.save(environmentalMeasurement);
    }
}
