package com.cyver.plant.commons.entities;

import java.io.Serial;
import java.time.LocalDateTime;

import com.cyver.plant.commons.shared.TemperatureUnit;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "environmentals_measurements")
@AttributeOverride(name = "uuid", column = @Column(name = "environmental_measurement_uuid"))
public final class EnvironmentalMeasurement extends Audit {

    @Serial
    private static final long serialVersionUID = -2537457476238073598L;

    private TemperatureUnit temperatureUnit;

    private float temperature;

    private float humidity;

    private float heatIndex;

    private float light;

    private float soilMoisture;

    private LocalDateTime measuredAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plant_uuid", nullable = false, updatable = false)
    private Plant plant;

}
