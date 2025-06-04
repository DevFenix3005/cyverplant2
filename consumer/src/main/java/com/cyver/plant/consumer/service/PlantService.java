package com.cyver.plant.consumer.service;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.database.cloud.tables.dtos.Plant;

public interface  PlantService {

    Plant createIfNotExists(final PlantAvro plantAvro);

}
