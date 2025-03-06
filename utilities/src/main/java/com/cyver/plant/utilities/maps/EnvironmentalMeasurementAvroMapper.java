package com.cyver.plant.utilities.maps;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.avro.LocationAvro;
import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.avro.TemperatureUnit;
import com.cyver.plant.commons.node.NodeMeasurementResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = { TemperatureUnit.class })
public interface EnvironmentalMeasurementAvroMapper {

    @Mapping(target = "plantInfo", ignore = true)
    @Mapping(target = "plantInfoBuilder", expression = "java(plantInfoBuilder(ownerId, plantName, plantType, longitude, latitude))")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now().toEpochMilli())")
    EnvironmentalMeasurementAvro toAvroMessage(NodeMeasurementResponse nodeMeasurementResponse, String ownerId, String plantName,
            String plantType, float longitude, float latitude);

    default PlantAvro.Builder plantInfoBuilder(final String ownerId, final String plantName, final String plantType, float longitude,
            float latitude) {
        return PlantAvro.newBuilder()
                .setPlantLocation(new LocationAvro(latitude, longitude))
                .setOwnerId(ownerId)
                .setPlantName(plantName)
                .setPlantType(plantType);
    }

}
