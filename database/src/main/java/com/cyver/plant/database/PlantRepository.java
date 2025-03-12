package com.cyver.plant.database;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, UUID> {

    Optional<Plant> findPlantByNameAndOwner(String name, UUID owner);

    @Query(value = """
            SELECT \
                p0.name AS plant_name, \
                p0.latitude, \
                p0.longitude, \
                t0.name AS type_name, \
                em0.temperature_unit, \
                em0.temperature, \
                em0.humidity, \
                em0.heat_index, \
                em0.light, \
                em0.soil_moisture, \
                em0.measured_at \
            FROM plants p0 \
            JOIN plants_types t0 \
                ON t0.plant_type_uuid = p0.type_uuid \
            LEFT JOIN LATERAL ( \
                SELECT em1.* \
                FROM environmentals_measurements em1 \
                WHERE em1.plant_uuid = p0.plant_uuid \
                ORDER BY em1.measured_at DESC \
                LIMIT 1 \
            ) em0 ON TRUE \
            WHERE p0.owner = :owner \
            ORDER BY p0.name DESC""", nativeQuery = true)
    List<PlantDto> findPlantsByOwner(@Param("owner") UUID owner);

}
