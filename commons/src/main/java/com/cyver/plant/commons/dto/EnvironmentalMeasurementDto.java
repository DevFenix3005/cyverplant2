package com.cyver.plant.commons.dto;

public record EnvironmentalMeasurementDto(
        float temperature,
        float humidity,
        float heatIndex,
        float light,
        float soilMoisture
) {
}
