package com.cyver.plant.utilities.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.entities.Owner;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.commons.entities.PlantType;

@Mapper(uses = { EnvironmentalMeasurementMapper.class })
interface PlantMapper {

    PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);

    @Mapping(target = "environmentalMeasurement", ignore = true)
    @Mapping(target = "latitude", source = "plantLocation.latitude")
    @Mapping(target = "longitude", source = "plantLocation.longitude")
    @Mapping(target = "type", source = "type.name")
    PlantDto toDto(Plant plant);

    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "environmentalMeasurements", expression = "java(java.util.Collections.emptyList())")
    @Mapping(target = "name", source = "plantAvro.plantName")
    @Mapping(target = "plantLocation", source = "plantAvro.plantLocation")
    @Mapping(target = "type", source = "plantType")
    @Mapping(target = "uuid", ignore = true)
    Plant toEntity(PlantAvro plantAvro, PlantType plantType, Owner owner);

}
