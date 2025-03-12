package com.cyver.plant.data.service;

import java.util.List;
import java.util.UUID;

import com.cyver.plant.commons.dto.PlantDto;

public interface PlantService {

    List<PlantDto> getPlantsByOwner(UUID owner);

}
