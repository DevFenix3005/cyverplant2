package com.cyver.plant.commons.entities;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.AttributeOverride;
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
@AttributeOverride(name = "uuid", column = @Column(name = "plant_type_uuid"))
@Table(name = "plants_types", indexes = { @Index(name = "plant_type_name_idx", columnList = "name", unique = true) })
public class PlantType extends Audit {

    @Serial
    private static final long serialVersionUID = -1040548205627487944L;

    @NonNull
    @Column(nullable = false, updatable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plant> plants;


}
