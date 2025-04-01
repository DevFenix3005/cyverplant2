package com.cyver.plant.commons.exceptions;

import java.io.Serial;
import java.util.UUID;

public class PlantNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -521434701233677272L;

    public PlantNotFoundException(final UUID uuid, final String plantName) {
        super(String.format("Plant with name %s and owner %s not found", plantName, uuid));
    }

}
