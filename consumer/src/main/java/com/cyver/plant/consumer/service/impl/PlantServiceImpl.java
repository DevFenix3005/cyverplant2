package com.cyver.plant.consumer.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.consumer.service.PlantService;
import com.cyver.plant.database.cloud.custom_repository.PlantRepositoryCustom;
import com.cyver.plant.database.cloud.Tables;
import com.cyver.plant.database.cloud.tables.dtos.Plant;
import com.cyver.plant.database.cloud.tables.repositories.OwnerRepository;
import com.cyver.plant.database.cloud.tables.repositories.PlantRepository;
import com.cyver.plant.database.cloud.tables.repositories.TypeRepository;
import com.cyver.plant.database.cloud.tables.dtos.Owner;
import com.cyver.plant.database.cloud.tables.dtos.Type;
import com.cyver.plant.utilities.map.MapUtilComponent;
import com.cyver.plant.utilities.owner.OwnerUtilComponent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    private final PlantRepositoryCustom plantRepositoryCustom;

    private final TypeRepository typeRepository;

    private final OwnerRepository ownerRepository;

    private final MapUtilComponent mapUtilComponent;

    private final OwnerUtilComponent ownerUtilComponent;

    @Override
    public Plant createIfNotExists(final PlantAvro plantAvro) {
        final Owner owner = ownerUtilComponent.getOwnerByUUIDIfExists(ownerRepository, UUID.fromString(plantAvro.getOwnerId()));
        return fetchPlantByNameAndOwner(owner, plantAvro.getPlantName())
                .orElseGet(() -> createAndSavePlant(plantAvro, owner));
    }

    private Optional<Plant> fetchPlantByNameAndOwner(final Owner owner, final String plantName) {
        return plantRepositoryCustom.findPlantByOwnerIdAndName(owner.ownerUuid(), plantName)
                .map(mapUtilComponent::mapPlantRecordToPlant);
    }

    private Plant createAndSavePlant(final PlantAvro plantAvro, final Owner owner) {
        final Plant newPlant = mapUtilComponent.toEntity(plantAvro, findOrCreatePlantType(plantAvro), owner);
        plantRepository.insert(newPlant);
        return newPlant;
    }

    private Type findOrCreatePlantType(final PlantAvro plantAvro) {
        final String plantTypeName = plantAvro.getPlantType();
        return typeRepository.fetchOptional(Tables.TYPES.TYPE_NAME, plantTypeName)
                .orElseGet(() -> {
                    final Type newType = new Type(UUID.randomUUID(), plantTypeName);
                    typeRepository.insert(newType);
                    return newType;
                });
    }

}
