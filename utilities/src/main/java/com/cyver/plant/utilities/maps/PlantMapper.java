package com.cyver.plant.utilities.maps;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.dto.PlantWithMeasurementsDto;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.commons.entities.PlantType;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlantMapper {

    @Mapping(target = "owner", expression = "java(java.util.UUID.fromString(plantAvro.getOwnerId()))")
    @Mapping(target = "environmentalMeasurements", expression = "java(java.util.Collections.emptyList())")
    @Mapping(target = "name", source = "plantAvro.plantName")
    @Mapping(target = "plantLocation", source = "plantAvro.plantLocation")
    @Mapping(target = "type", source = "plantType")
    @Mapping(target = "uuid", ignore = true)
    Plant toPlant(PlantAvro plantAvro, PlantType plantType);

    @Mapping(target = "longitude", source = "plantLocation.longitude")
    @Mapping(target = "latitude", source = "plantLocation.latitude")
    @Mapping(target = "type", source = "type.name")
    PlantWithMeasurementsDto toPlantDto(Plant plant);

    @Mapping(target = "longitude", source = "plantLocation.longitude")
    @Mapping(target = "latitude", source = "plantLocation.latitude")
    @Mapping(target = "type", source = "type.name")
    PlantDto toBasicPlantDto(Plant plant);

}
