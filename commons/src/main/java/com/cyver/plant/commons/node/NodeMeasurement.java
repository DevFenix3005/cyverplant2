package com.cyver.plant.commons.node;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record NodeMeasurement(String temperatureUnit, float temperature, float humidity, float heatIndex, int light, int soilMoisture)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 8975676324202141628L;

    @JsonCreator
    public NodeMeasurement(
            @JsonProperty("temperature_unit") final String temperatureUnit,
            @JsonProperty("temperature") final float temperature,
            @JsonProperty("humidity") final float humidity,
            @JsonProperty("heat_index") final float heatIndex,
            @JsonProperty("light") final int light,
            @JsonProperty("soil") final int soilMoisture) {
        this.temperatureUnit = temperatureUnit;
        this.temperature = temperature;
        this.humidity = humidity;
        this.heatIndex = heatIndex;
        this.light = light;
        this.soilMoisture = soilMoisture;
    }
}
