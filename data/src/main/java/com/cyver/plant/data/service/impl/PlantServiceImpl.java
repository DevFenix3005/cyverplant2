package com.cyver.plant.data.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.entities.Owner;
import com.cyver.plant.data.service.PlantService;
import com.cyver.plant.database.OwnerRepository;
import com.cyver.plant.database.PlantRepository;
import com.cyver.plant.utilities.stream.StreamUtilComponent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    private final OwnerRepository ownerRepository;

    @Override
    public List<PlantDto> getPlantsByOwner(final String auth0Sid) {
        Owner owner = ownerRepository.findByAuth0Sid(auth0Sid).orElseThrow();
        return plantRepository.findPlantsByOwner(owner.getUuid());
    }
}
