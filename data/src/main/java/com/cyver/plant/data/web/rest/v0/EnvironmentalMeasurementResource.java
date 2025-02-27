package com.cyver.plant.data.web.rest.v0;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyver.plant.data.services.EnvironmentalMeasurementService;
import com.cyver.plant.commons.entities.EnvironmentalMeasurement;

@RestController("environmental-measurement")
@RequestMapping("/api/v0/environmental-measurement")
public class EnvironmentalMeasurementResource {

    private final EnvironmentalMeasurementService environmentalMeasurementService;

    public EnvironmentalMeasurementResource(final EnvironmentalMeasurementService environmentalMeasurementService) {
        this.environmentalMeasurementService = environmentalMeasurementService;
    }

    @GetMapping()
    public ResponseEntity<List<EnvironmentalMeasurement>> getEnvironmentalMeasurements() {
        return ResponseEntity.ok(environmentalMeasurementService.getEnvironmentalMeasurements());
    }

}
