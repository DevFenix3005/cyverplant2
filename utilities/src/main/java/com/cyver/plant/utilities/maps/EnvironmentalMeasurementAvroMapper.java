package com.cyver.plant.utilities.maps;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.avro.InnerTemperatureUnit;
import com.cyver.plant.commons.node.NodeMeasurement;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = { InnerTemperatureUnit.class })
public interface EnvironmentalMeasurementAvroMapper {

    @Mapping(target = "temperatureUnit", expression = "java(InnerTemperatureUnit.CELSIUS)")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now().toEpochMilli())")
    @Mapping(target = "temperature", source = "celsius")
    @Mapping(target = "heatIndex", source = "heatIndexCelsius")
    EnvironmentalMeasurementAvro toAvroMessage(NodeMeasurement nodeMeasurement);
}
