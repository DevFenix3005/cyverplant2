package com.cyver.plant.data.service;

import java.util.List;

import com.cyver.plant.database.cloud.udt.dtos.PlantWithLastEnvironmentalMeasurement;

public interface PlantService {

    List<PlantWithLastEnvironmentalMeasurement> getPlantsByOwner(String auth0Sid);

}
