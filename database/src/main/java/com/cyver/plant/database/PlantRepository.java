package com.cyver.plant.database;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyver.plant.commons.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, UUID> {
}
