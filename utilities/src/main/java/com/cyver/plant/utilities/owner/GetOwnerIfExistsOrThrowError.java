package com.cyver.plant.utilities.owner;

import java.util.UUID;

import com.cyver.plant.commons.exceptions.OwnerNotFoundException;
import com.cyver.plant.database.cloud.tables.dtos.Owner;
import com.cyver.plant.database.cloud.tables.repositories.OwnerRepository;

final class GetOwnerIfExistsOrThrowError {

    private GetOwnerIfExistsOrThrowError() {
        throw new IllegalStateException("Utility class");
    }

    static Owner getOwnerByUUIDIfExists(final OwnerRepository ownerRepository, final UUID ownerId) {
        return ownerRepository.findOptionalById(ownerId).orElseThrow(() -> new OwnerNotFoundException(ownerId));
    }

}
