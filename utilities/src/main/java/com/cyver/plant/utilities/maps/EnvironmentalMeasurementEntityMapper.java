package com.cyver.plant.utilities.maps;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnvironmentalMeasurementEntityMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "plant", ignore = true)
    @Mapping(target = "measuredAt",
            expression = "java(java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(measurementAvro.getTimestamp()), java.time.ZoneId.systemDefault()))")
    EnvironmentalMeasurement toEntity(EnvironmentalMeasurementAvro measurementAvro);

}
