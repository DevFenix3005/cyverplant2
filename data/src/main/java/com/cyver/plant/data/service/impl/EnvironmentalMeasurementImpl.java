package com.cyver.plant.data.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.data.service.EnvironmentalMeasurementService;
import com.cyver.plant.database.domain.tables.dtos.EnvironmentalMeasurement;
import com.cyver.plant.database.domain.tables.repositories.EnvironmentalMeasurementRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class EnvironmentalMeasurementImpl implements EnvironmentalMeasurementService {

    private final EnvironmentalMeasurementRepository environmentalMeasurementRepository;

    @Override
    public List<EnvironmentalMeasurement> getEnvironmentalMeasurementsByPlant(final UUID plantId) {
        return environmentalMeasurementRepository.fetchByPlantUuid(plantId);
    }

}
