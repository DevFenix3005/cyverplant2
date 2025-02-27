package com.cyver.plant.utilities.maps;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.avro.InnerTemperatureUnit;
import com.cyver.plant.commons.node.NodeMeasurementResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = { InnerTemperatureUnit.class })
public interface EnvironmentalMeasurementAvroMapper {

    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now().toEpochMilli())")
    EnvironmentalMeasurementAvro toAvroMessage(NodeMeasurementResponse nodeMeasurementResponse);
}
