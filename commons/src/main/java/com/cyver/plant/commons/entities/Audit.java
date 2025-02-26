package com.cyver.plant.commons.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = -4535552256578570627L;

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

}
