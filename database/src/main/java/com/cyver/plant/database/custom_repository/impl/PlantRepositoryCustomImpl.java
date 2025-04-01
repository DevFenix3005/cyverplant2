package com.cyver.plant.database.custom_repository.impl;

import java.util.Optional;
import java.util.UUID;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import com.cyver.plant.database.custom_repository.PlantRepositoryCustom;
import com.cyver.plant.database.Tables;
import com.cyver.plant.database.tables.records.PlantsRecord;

@Repository
public class PlantRepositoryCustomImpl implements PlantRepositoryCustom {

    private final DSLContext dslContext;

    public PlantRepositoryCustomImpl(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<PlantsRecord> findPlantByOwnerIdAndName(final UUID ownerId, final String name) {
        return dslContext
                .selectFrom(Tables.PLANTS)
                .where(Tables.PLANTS.PLANT_NAME.eq(name).and(Tables.PLANTS.OWNER_UUID.eq(ownerId)))
                .fetchOptional();

    }

}
