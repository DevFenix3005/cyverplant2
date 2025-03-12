package com.cyver.plant.commons.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.cyver.plant.commons.shared.TemperatureUnit;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public final class EnvironmentalMeasurementDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -8104834810083092479L;

    @NotNull
    private TemperatureUnit temperatureUnit;

    @NotNull
    private float temperature;

    @NotNull
    private float humidity;

    @NotNull
    private float heatIndex;

    @NotNull
    private float light;

    @NotNull
    private float soilMoisture;

    @NotNull
    private LocalDateTime measuredAt;

    public static EnvironmentalMeasurementDto create(
            final TemperatureUnit temperatureUnit,
            final float temperature,
            final float humidity,
            final float heatIndex,
            final float light,
            final float soilMoisture,
            final LocalDateTime measuredAt
    ) {
        return new EnvironmentalMeasurementDto(temperatureUnit, temperature, humidity, heatIndex, light, soilMoisture, measuredAt);
    }

}
