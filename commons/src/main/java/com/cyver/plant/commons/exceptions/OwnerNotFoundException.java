package com.cyver.plant.commons.exceptions;

import java.io.Serial;
import java.util.UUID;

public class OwnerNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -6136544244454909020L;

    public OwnerNotFoundException(final UUID ownerId) {
        super("Owner with id " + ownerId + " not found");
    }

    public OwnerNotFoundException(final String email) {
        super("Owner with email " + email + " not found");
    }

}
