package com.cyver.plant.data.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.dto.PlantWithMeasurementsDto;
import com.cyver.plant.data.services.PlantService;
import com.cyver.plant.database.PlantRepository;
import com.cyver.plant.utilities.maps.PlantMapper;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    private final PlantMapper plantMapper;

    public PlantServiceImpl(final PlantRepository plantRepository, final PlantMapper plantMapper) {
        this.plantRepository = plantRepository;
        this.plantMapper = plantMapper;
    }

    @Override
    public List<PlantDto> getPlantsByOwnerId(final UUID ownerId) {
        return plantRepository.findPlantsByOwner(ownerId)
                .stream()
                .map(plantMapper::toBasicPlantDto)
                .toList();
    }

    @Override
    public Optional<PlantWithMeasurementsDto> getPlantByOwnerIdAndName(final UUID ownerId, final String name) {
        return plantRepository.findPlantOptimal(name, ownerId)
                .map(plantMapper::toPlantDto);
    }
}
