package com.cyver.plant.data.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyver.plant.data.services.EnvironmentalMeasurementService;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.database.EnvironmentalMeasurementRepository;

@Service
public class EnvironmentalMeasurementServiceImpl implements EnvironmentalMeasurementService {

    private final EnvironmentalMeasurementRepository environmentalMeasurementRepository;

    public EnvironmentalMeasurementServiceImpl(final EnvironmentalMeasurementRepository environmentalMeasurementRepository) {
        this.environmentalMeasurementRepository = environmentalMeasurementRepository;
    }

    @Override
    public List<EnvironmentalMeasurement> getEnvironmentalMeasurements() {
        return environmentalMeasurementRepository.findAll();
    }
}
