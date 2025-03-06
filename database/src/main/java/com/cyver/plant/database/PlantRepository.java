package com.cyver.plant.database;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cyver.plant.commons.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, UUID> {

    Optional<Plant> findPlantByNameAndOwner(String name, UUID owner);

    @Query("SELECT p0 " +
            "FROM Plant p0 INNER JOIN FETCH p0.type t0 INNER JOIN FETCH p0.environmentalMeasurements e0 " +
            "WHERE p0.name = ?1 AND p0.owner = ?2 ORDER BY e0.measuredAt DESC LIMIT 5")
    Optional<Plant> findPlantOptimal(String name, UUID owner);

    List<Plant> findPlantsByOwner(UUID owner);


}
