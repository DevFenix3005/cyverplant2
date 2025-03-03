package com.cyver.plant.consumer.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.PlantAvro;
import com.cyver.plant.commons.entities.Plant;
import com.cyver.plant.consumer.service.PlantService;
import com.cyver.plant.database.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    public PlantServiceImpl(final PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public Plant creatIfNotExists(final PlantAvro plantAvro) {
        UUID ownerUUID = UUID.fromString(plantAvro.getOwnerId());
        return plantRepository.findPlantByNameAndOwner(plantAvro.getPlantName(), ownerUUID)
                .orElseGet(() -> plantRepository.save(new Plant(plantAvro.getPlantName(), ownerUUID)));

    }
}
