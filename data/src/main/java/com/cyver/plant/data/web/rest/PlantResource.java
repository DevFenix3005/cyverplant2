package com.cyver.plant.data.web.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyver.plant.data.service.EnvironmentalMeasurementService;
import com.cyver.plant.data.service.PlantService;
import com.cyver.plant.database.cloud.tables.dtos.EnvironmentalMeasurement;
import com.cyver.plant.database.cloud.udt.dtos.PlantWithLastEnvironmentalMeasurement;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("${api.base-path}/plant")
@AllArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class PlantResource {

    private final PlantService plantService;

    private final EnvironmentalMeasurementService environmentalMeasurementService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PlantWithLastEnvironmentalMeasurement>> getPlantsByOwner(@AuthenticationPrincipal OAuth2User principal) {
        log.info("REST request to get plants by owner : {}", principal.getName());
        return new ResponseEntity<>(plantService.getPlantsByOwner(principal.getAttribute("email")), HttpStatus.OK);
    }

    @GetMapping(path = "/{plantUuid}/environmental-measurement", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<EnvironmentalMeasurement>> getMeasurementsByPlant(@PathVariable("plantUuid") final UUID plantUuid) {
        return ResponseEntity.ok(environmentalMeasurementService.getEnvironmentalMeasurementsByPlant(plantUuid));
    }

}
