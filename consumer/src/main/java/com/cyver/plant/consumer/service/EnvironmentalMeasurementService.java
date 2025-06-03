package com.cyver.plant.consumer.service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.database.cloud.tables.dtos.EnvironmentalMeasurement;
import com.cyver.plant.database.cloud.tables.dtos.Plant;

public interface EnvironmentalMeasurementService {

    EnvironmentalMeasurement convertAndSaveEnvironmentalMeasurement(
            final Plant plant,
            final EnvironmentalMeasurementAvro environmentalMeasurementAvro);

}
