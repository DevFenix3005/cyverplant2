package com.cyver.plant.commons.dto;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.cyver.plant.commons.annotations.Default;
import com.cyver.plant.commons.shared.TemperatureUnit;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@SqlResultSetMapping(
        name = "PlantWithLastMeasureMapping",
        classes = {
                @ConstructorResult(targetClass = PlantDto.class,
                        columns = {
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "latitude", type = Double.class),
                                @ColumnResult(name = "longitude", type = Double.class),
                                @ColumnResult(name = "type", type = String.class),
                                @ColumnResult(name = "temperatureUnit", type = Short.class),
                                @ColumnResult(name = "temperature", type = Float.class),
                                @ColumnResult(name = "humidity", type = Float.class),
                                @ColumnResult(name = "heatIndex", type = Float.class),
                                @ColumnResult(name = "light", type = Float.class),
                                @ColumnResult(name = "soilMoisture", type = Float.class),
                                @ColumnResult(name = "measuredAt", type = Timestamp.class)
                        }
                )
        }
)
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public final class PlantDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4382344017297767716L;

    @NotNull
    private final String name;

    @NotNull
    private final double latitude;

    @NotNull
    private final double longitude;

    @NotNull
    private final String type;

    @NotNull
    private final List<EnvironmentalMeasurementDto> environmentalMeasurement;

    @Default
    public PlantDto(final String name, final double latitude, final double longitude, final String type,
            final List<EnvironmentalMeasurementDto> environmentalMeasurement) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.environmentalMeasurement = environmentalMeasurement;
    }

    public PlantDto(
            final String name,
            final double latitude,
            final double longitude,
            final String type,
            final Short temperatureUnit,
            final float temperature,
            final float humidity,
            final float heatIndex,
            final float light,
            final float soilMoisture,
            final Timestamp measuredAt) {
        this(name, latitude, longitude, type,
                List.of(
                        EnvironmentalMeasurementDto.create(
                                temperatureUnit == 0 ? TemperatureUnit.CELSIUS : TemperatureUnit.FAHRENHEIT,
                                temperature,
                                humidity,
                                heatIndex,
                                light,
                                soilMoisture,
                                measuredAt.toLocalDateTime())
                ));
    }

}
