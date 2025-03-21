package com.cyver.plant.data.service;

import java.util.List;
import java.util.UUID;

import com.cyver.plant.commons.dto.EnvironmentalMeasurementDto;

public interface EnvironmentalMeasurementService {

    List<EnvironmentalMeasurementDto> getEnvironmentalMeasurementsByPlant(UUID plantId);

}
