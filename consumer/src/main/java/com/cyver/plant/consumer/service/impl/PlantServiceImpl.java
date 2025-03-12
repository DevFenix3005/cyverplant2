package com.cyver.plant.consumer.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.commons.entities.PlantType;
import com.cyver.plant.consumer.service.PlantService;
import com.cyver.plant.database.PlantRepository;
import com.cyver.plant.database.PlantTypeRepository;
import com.cyver.plant.utilities.map.MapUtilComponent;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    private final PlantTypeRepository plantTypeRepository;

    private final MapUtilComponent mapUtilComponent;

    public PlantServiceImpl(final PlantRepository plantRepository, final PlantTypeRepository plantTypeRepository,
            final MapUtilComponent mapUtilComponent) {
        this.plantRepository = plantRepository;
        this.plantTypeRepository = plantTypeRepository;
        this.mapUtilComponent = mapUtilComponent;
    }

    @Override
    public Plant creatIfNotExists(final PlantAvro plantAvro) {
        final UUID ownerUUID = UUID.fromString(plantAvro.getOwnerId());
        return plantRepository.findPlantByNameAndOwner(plantAvro.getPlantName(), ownerUUID)
                .orElseGet(() -> createAndSavePlant(plantAvro));
    }

    private Plant createAndSavePlant(PlantAvro plantAvro) {
        return plantRepository.save(mapUtilComponent.toEntity(plantAvro, findOrCreatePlantType(plantAvro)));
    }

    private PlantType findOrCreatePlantType(final PlantAvro plantAvro) {
        final String plantTypeName = plantAvro.getPlantType();
        return plantTypeRepository.findPlantTypeByName(plantTypeName)
                .orElseGet(() -> plantTypeRepository.save(new PlantType(plantTypeName)));
    }

}
