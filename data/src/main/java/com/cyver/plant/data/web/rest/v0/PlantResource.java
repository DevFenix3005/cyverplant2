package com.cyver.plant.data.web.rest.v0;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyver.plant.commons.dto.PlantDto;
import com.cyver.plant.commons.dto.PlantWithMeasurementsDto;
import com.cyver.plant.data.services.PlantService;

@RestController("environmentalMeasurement")
@RequestMapping("/api/v0/plant")
public class PlantResource {

    private final PlantService plantService;

    public PlantResource(final PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping()
    public ResponseEntity<List<PlantDto>> findAll(@RequestParam(name = "ownerId") UUID ownerId) {
        return ResponseEntity.ok(plantService.getPlantsByOwnerId(ownerId));
    }

    @GetMapping("/{ownerId}/{name}/environmental-measurements")
    public ResponseEntity<PlantWithMeasurementsDto> getEnvironmentalMeasurements(@PathVariable UUID ownerId, @PathVariable String name) {
        return ResponseEntity.of(plantService.getPlantByOwnerIdAndName(ownerId, name));
    }

}
