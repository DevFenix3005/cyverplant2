package com.cyver.plant.commons.node;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NodeMeasurement implements Serializable {

    @Serial
    private static final long serialVersionUID = 8975676324202141628L;

    private final int soilMoisture;

    private final float humidity;

    private final float celsius;

    private final float fahrenheit;

    private final float heatIndexCelsius;

    private final float heatIndexFahrenheit;

    @JsonCreator
    public NodeMeasurement(
            @JsonProperty("soil") final int soilMoisture,
            @JsonProperty("humidity") final float humidity,
            @JsonProperty("celsius") final float celsius,
            @JsonProperty("fahrenheit") final float fahrenheit,
            @JsonProperty("heat_index_celsius") final float heatIndexCelsius,
            @JsonProperty("heat_index_fahrenheit") final float heatIndexFahrenheit) {
        this.soilMoisture = soilMoisture;
        this.humidity = humidity;
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
        this.heatIndexCelsius = heatIndexCelsius;
        this.heatIndexFahrenheit = heatIndexFahrenheit;
    }
}
