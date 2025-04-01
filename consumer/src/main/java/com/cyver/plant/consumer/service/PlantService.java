package com.cyver.plant.consumer.service;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.database.tables.dtos.Plant;

public interface  PlantService {

    Plant creatIfNotExists(final PlantAvro plantAvro);

}
