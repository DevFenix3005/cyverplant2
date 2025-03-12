package com.cyver.plant.database;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyver.plant.commons.entities.EnvironmentalMeasurement;

public interface EnvironmentalMeasurementRepository extends JpaRepository<EnvironmentalMeasurement, UUID> {

    List<EnvironmentalMeasurement> findAllByPlantUuid(final UUID uuid);

}
