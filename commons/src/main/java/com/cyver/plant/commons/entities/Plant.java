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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "plants", indexes = {
        @Index(name = "owner_name_idx", columnList = "name,owner", unique = true),
})
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AttributeOverrides({
        @AttributeOverride(name = "uuid", column = @Column(name = "plant_uuid")),
})
public final class Plant extends Audit {
    @Serial
    private static final long serialVersionUID = 552123024106987698L;

    private String name;

    private UUID owner;

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnvironmentalMeasurement> environmentalMeasurements;

}
