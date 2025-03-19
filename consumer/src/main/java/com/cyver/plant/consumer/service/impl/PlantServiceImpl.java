package com.cyver.plant.consumer.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.entities.Owner;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.commons.entities.PlantType;
import com.cyver.plant.consumer.service.PlantService;
import com.cyver.plant.database.OwnerRepository;
import com.cyver.plant.database.PlantRepository;
import com.cyver.plant.database.PlantTypeRepository;
import com.cyver.plant.utilities.map.MapUtilComponent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    private final PlantTypeRepository plantTypeRepository;

    private final OwnerRepository ownerRepository;

    private final MapUtilComponent mapUtilComponent;

    @Override
    public Plant creatIfNotExists(final PlantAvro plantAvro) {
        final UUID ownerUUID = UUID.fromString(plantAvro.getOwnerId());
        Owner owner = ownerRepository.findById(ownerUUID).orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        return plantRepository.findPlantByNameAndOwner(plantAvro.getPlantName(), owner)
                .orElseGet(() -> createAndSavePlant(plantAvro));
    }

    private Plant createAndSavePlant(PlantAvro plantAvro) {
        final UUID ownerUUID = UUID.fromString(plantAvro.getOwnerId());
        Owner owner = ownerRepository.findById(ownerUUID).orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        return plantRepository.save(mapUtilComponent.toEntity(plantAvro, findOrCreatePlantType(plantAvro), owner));
    }

    private PlantType findOrCreatePlantType(final PlantAvro plantAvro) {
        final String plantTypeName = plantAvro.getPlantType();
        return plantTypeRepository.findPlantTypeByName(plantTypeName)
                .orElseGet(() -> plantTypeRepository.save(new PlantType(plantTypeName)));
    }

}
