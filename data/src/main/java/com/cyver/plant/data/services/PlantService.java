package com.cyver.plant.data.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.dto.PlantWithMeasurementsDto;

public interface PlantService {

    List<PlantDto> getPlantsByOwnerId(UUID ownerId);

    Optional<PlantWithMeasurementsDto> getPlantByOwnerIdAndName(UUID ownerId, String name);

}
