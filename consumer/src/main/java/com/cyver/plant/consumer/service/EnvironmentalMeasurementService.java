package com.cyver.plant.consumer.service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.commons.entities.Plant;

public interface EnvironmentalMeasurementService {

    EnvironmentalMeasurement convertAndSaveEnvironmentalMeasurement(
            final Plant plant,
            final EnvironmentalMeasurementAvro environmentalMeasurementAvro);

}
