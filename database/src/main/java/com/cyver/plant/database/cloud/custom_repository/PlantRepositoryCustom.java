package com.cyver.plant.database.cloud.custom_repository;

import java.util.Optional;
import java.util.UUID;

import com.cyver.plant.database.cloud.tables.records.PlantsRecord;

public interface PlantRepositoryCustom {

    Optional<PlantsRecord> findPlantByOwnerIdAndName(final UUID ownerId, final String name);

}
