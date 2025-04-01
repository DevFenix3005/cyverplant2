package com.cyver.plant.data.service.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.data.service.PlantService;
import com.cyver.plant.database.domain.Routines;
import com.cyver.plant.database.domain.tables.repositories.OwnerRepository;
import com.cyver.plant.database.domain.tables.dtos.Owner;
import com.cyver.plant.database.domain.udt.dtos.PlantWithLastEnvironmentalMeasurement;
import com.cyver.plant.utilities.owner.OwnerUtilComponent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class PlantServiceImpl implements PlantService {

    private final OwnerRepository ownerRepository;

    private final OwnerUtilComponent ownerUtilComponent;

    private final DSLContext dslContext;

    @Override
    public List<PlantWithLastEnvironmentalMeasurement> getPlantsByOwner(final String auth0Sid) {
        Owner owner = ownerUtilComponent.getOwnerByEmailIfExists(ownerRepository, auth0Sid);
        return Routines.getPlantWithLastEnvironmentalMeasurement(dslContext.configuration(), owner.ownerUuid())
                .into(PlantWithLastEnvironmentalMeasurement.class);
    }

}
