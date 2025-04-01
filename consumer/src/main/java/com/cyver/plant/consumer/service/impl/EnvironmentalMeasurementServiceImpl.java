package com.cyver.plant.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.consumer.service.EnvironmentalMeasurementService;
import com.cyver.plant.database.tables.repositories.EnvironmentalMeasurementRepository;
import com.cyver.plant.database.tables.dtos.EnvironmentalMeasurement;
import com.cyver.plant.database.tables.dtos.Plant;
import com.cyver.plant.utilities.map.MapUtilComponent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class EnvironmentalMeasurementServiceImpl implements EnvironmentalMeasurementService {

    private final MapUtilComponent mapUtilComponent;

    private final EnvironmentalMeasurementRepository environmentalMeasurementsDao;

    @Override
    public EnvironmentalMeasurement convertAndSaveEnvironmentalMeasurement(final Plant plant,
            final EnvironmentalMeasurementAvro environmentalMeasurementAvro) {
        EnvironmentalMeasurement environmentalMeasurements = mapUtilComponent.toEntity(environmentalMeasurementAvro, plant);
        environmentalMeasurementsDao.insert(environmentalMeasurements);
        return environmentalMeasurements;
    }

}
