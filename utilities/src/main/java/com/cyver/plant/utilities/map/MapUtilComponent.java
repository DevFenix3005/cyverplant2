package com.cyver.plant.utilities.map;

import org.springframework.stereotype.Component;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.database.cloud.tables.dtos.EnvironmentalMeasurement;
import com.cyver.plant.database.cloud.tables.dtos.Owner;
import com.cyver.plant.database.cloud.tables.dtos.Plant;
import com.cyver.plant.database.cloud.tables.dtos.Type;
import com.cyver.plant.database.cloud.tables.records.PlantsRecord;
import com.cyver.plant.database.cloud.udt.dtos.PlantWithLastEnvironmentalMeasurement;
import com.cyver.plant.database.cloud.udt.records.PlantWithLastEnvironmentalMeasurementRecord;

@Component
public class MapUtilComponent {

    public EnvironmentalMeasurement toEntity(EnvironmentalMeasurementAvro measurementAvro, Plant plant) {
        return EnvironmentalMeasurementMapper.INSTANCE.toEntity(measurementAvro, plant);
    }

    public EnvironmentalMeasurementAvro toAvroMessage(NodeMeasurementResponse nodeMeasurementResponse, String ownerId, String plantName,
            String plantType, double longitude, double latitude) {
        return EnvironmentalMeasurementMapper.INSTANCE.toAvroMessage(nodeMeasurementResponse, ownerId, plantName, plantType, longitude,
                latitude);
    }

    public Plant toEntity(PlantAvro plantAvro, Type plantType, Owner owner) {
        return PlantMapper.INSTANCE.toEntity(plantAvro, plantType, owner);
    }

    public PlantWithLastEnvironmentalMeasurement toPojo(
            PlantWithLastEnvironmentalMeasurementRecord plantWithLastEnvironmentalMeasurementRecord) {
        return PlantMapper.INSTANCE.toPojo(plantWithLastEnvironmentalMeasurementRecord);
    }

    public Plant mapPlantRecordToPlant(final PlantsRecord plantsRecord) {
        return PlantMapper.INSTANCE.mapPlantRecordToPlant(plantsRecord);
    }
}
