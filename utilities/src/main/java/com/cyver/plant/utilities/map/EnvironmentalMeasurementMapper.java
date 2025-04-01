package com.cyver.plant.utilities.map;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.avro.LocationAvro;
import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.database.tables.dtos.EnvironmentalMeasurement;
import com.cyver.plant.database.tables.dtos.Plant;

@Mapper()
interface EnvironmentalMeasurementMapper {

    EnvironmentalMeasurementMapper INSTANCE = Mappers.getMapper(EnvironmentalMeasurementMapper.class);

    @Mapping(target = "environmentalMeasurementUuid", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "unit", source = "measurementAvro.temperatureUnit")
    @Mapping(target = "measuredAt", expression = "java(timestampToLocalDateTime(measurementAvro))")
    @Mapping(target = "plantUuid", source = "plant.plantUuid")
    EnvironmentalMeasurement toEntity(EnvironmentalMeasurementAvro measurementAvro, Plant plant);

    @Mapping(target = "plantInfo", ignore = true)
    @Mapping(target = "plantInfoBuilder", expression = "java(plantInfoBuilder(ownerId, plantName, plantType, longitude, latitude))")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now().toEpochMilli())")
    EnvironmentalMeasurementAvro toAvroMessage(NodeMeasurementResponse nodeMeasurementResponse, String ownerId, String plantName,
            String plantType, double longitude, double latitude);

    default PlantAvro.Builder plantInfoBuilder(final String ownerId, final String plantName, final String plantType, Double longitude,
            Double latitude) {
        return PlantAvro.newBuilder()
                .setPlantLocation(new LocationAvro(latitude, longitude))
                .setOwnerId(ownerId)
                .setPlantName(plantName)
                .setPlantType(plantType);
    }

    default LocalDateTime timestampToLocalDateTime(EnvironmentalMeasurementAvro measurementAvro) {
        return LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(measurementAvro.getTimestamp()), java.time.ZoneId.systemDefault());
    }
}
