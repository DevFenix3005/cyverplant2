package com.cyver.plant.commons.dto;

import java.io.Serial;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class PlantWithMeasurementsDto extends PlantDto {

    @Serial
    private static final long serialVersionUID = -177224323309693172L;

    private final List<EnvironmentalMeasurementDto> environmentalMeasurements;

    public PlantWithMeasurementsDto(final String name, final String type, final double longitude, final double latitude,
            final List<EnvironmentalMeasurementDto> environmentalMeasurements) {
        super(name, type, longitude, latitude);
        this.environmentalMeasurements = environmentalMeasurements;
    }
}
