package com.cyver.plant.commons.entities;

import java.io.Serial;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
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
    @Column(nullable = false, updatable = false)
    private UUID owner;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnvironmentalMeasurement> environmentalMeasurements;

}
