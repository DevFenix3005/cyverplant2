package com.cyver.plant.data.service;

import java.util.List;
import java.util.UUID;

import com.cyver.plant.database.cloud.tables.dtos.EnvironmentalMeasurement;

public interface EnvironmentalMeasurementService {

    List<EnvironmentalMeasurement> getEnvironmentalMeasurementsByPlant(UUID plantId);

}
