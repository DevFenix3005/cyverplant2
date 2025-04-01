package com.cyver.plant.utilities.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cyver.plant.database.tables.dtos.Owner;
import com.cyver.plant.database.tables.dtos.Plant;
import com.cyver.plant.database.tables.dtos.Type;
import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.database.tables.records.PlantsRecord;
import com.cyver.plant.database.udt.dtos.PlantWithLastEnvironmentalMeasurement;
import com.cyver.plant.database.udt.records.PlantWithLastEnvironmentalMeasurementRecord;

@Mapper(uses = { EnvironmentalMeasurementMapper.class })
interface PlantMapper {

    PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);

    @Mapping(target = "longitude", source = "plantAvro.plantLocation.longitude")
    @Mapping(target = "latitude", source = "plantAvro.plantLocation.latitude")
    @Mapping(target = "plantUuid", expression = "java(java.util.UUID.randomUUID())")
    Plant toEntity(PlantAvro plantAvro, Type plantType, Owner owner);

    PlantWithLastEnvironmentalMeasurement toPojo(PlantWithLastEnvironmentalMeasurementRecord plantWithLastEnvironmentalMeasurementRecord);

    Plant mapPlantRecordToPlant(final PlantsRecord plantsRecord);

}
