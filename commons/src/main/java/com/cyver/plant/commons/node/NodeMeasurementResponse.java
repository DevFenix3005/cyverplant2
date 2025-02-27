package com.cyver.plant.commons.node;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public final class NodeMeasurementResponse
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 8975676324202141628L;

    private final String temperatureUnit;

    private final float temperature;

    private final float humidity;

    private final float heatIndex;

    private final int light;

    private final int soilMoisture;

    @JsonCreator
    public NodeMeasurementResponse(
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
