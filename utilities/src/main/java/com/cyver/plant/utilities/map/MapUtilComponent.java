package com.cyver.plant.utilities.map;

import org.springframework.stereotype.Component;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.dto.EnvironmentalMeasurementDto;
import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;
import com.cyver.plant.commons.entities.Owner;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.commons.entities.PlantType;
import com.cyver.plant.commons.node.NodeMeasurementResponse;

@Component
public class MapUtilComponent {

    public EnvironmentalMeasurementDto toDto(EnvironmentalMeasurement environmentalMeasurement) {
        return EnvironmentalMeasurementMapper.INSTANCE.toDto(environmentalMeasurement);
    }

    public EnvironmentalMeasurement toEntity(EnvironmentalMeasurementAvro measurementAvro, Plant plant) {
        return EnvironmentalMeasurementMapper.INSTANCE.toEntity(measurementAvro, plant);
    }

    public EnvironmentalMeasurementAvro toAvroMessage(NodeMeasurementResponse nodeMeasurementResponse, String ownerId, String plantName,
            String plantType, float longitude, float latitude) {
        return EnvironmentalMeasurementMapper.INSTANCE.toAvroMessage(nodeMeasurementResponse, ownerId, plantName, plantType, longitude,
                latitude);
    }

    public PlantDto toDto(Plant plant) {
        return PlantMapper.INSTANCE.toDto(plant);
    }

    public Plant toEntity(PlantAvro plantAvro, PlantType plantType, Owner owner) {
        return PlantMapper.INSTANCE.toEntity(plantAvro, plantType, owner);
    }

}
