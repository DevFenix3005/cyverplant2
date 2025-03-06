package com.cyver.plant.commons.entities;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@AttributeOverride(name = "latitude", column = @Column(name = "latitude", nullable = false, updatable = false))
@AttributeOverride(name = "longitude", column = @Column(name = "longitude", nullable = false, updatable = false))
public class PlantLocation implements Serializable {

    @Serial
    private static final long serialVersionUID = 7278727371813097545L;

    @NonNull
    private double latitude;

    @NonNull
    private double longitude;

}
