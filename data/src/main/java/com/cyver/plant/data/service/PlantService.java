package com.cyver.plant.data.service;

import java.util.List;

import com.cyver.plant.commons.dto.PlantDto;

public interface PlantService {

    List<PlantDto> getPlantsByOwner(String auth0Sid);

}
