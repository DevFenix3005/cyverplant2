package com.cyver.plant.commons.entities;

import java.io.Serial;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "plants", indexes = {
        @Index(name = "owner_name_idx", columnList = "name,owner", unique = true),
})
@AttributeOverride(name = "uuid", column = @Column(name = "plant_uuid"))
public final class Plant extends Audit {

    @Serial
    private static final long serialVersionUID = 552123024106987698L;

    @NonNull
    @Column(nullable = false, updatable = false)
    private String name;

    @NonNull
    @Embedded
    private PlantLocation plantLocation;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner", nullable = false, updatable = false)
    private Owner owner;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_uuid", nullable = false, updatable = false)
    private PlantType type;

    @ToString.Exclude
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EnvironmentalMeasurement> environmentalMeasurements;

}
