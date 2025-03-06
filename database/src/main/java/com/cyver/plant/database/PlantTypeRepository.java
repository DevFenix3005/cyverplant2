package com.cyver.plant.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyver.plant.commons.entities.PlantType;

public interface PlantTypeRepository extends JpaRepository<PlantType, Long> {

    Optional<PlantType> findPlantTypeByName(String name);

}
