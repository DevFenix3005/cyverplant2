package com.cyver.plant.utilities.owner;

import com.cyver.plant.commons.exceptions.OwnerNotFoundException;
import com.cyver.plant.database.tables.dtos.Owner;
import com.cyver.plant.database.tables.repositories.OwnerRepository;

final class GetOwnerByEmailIfExistsOrThrowError {

    private GetOwnerByEmailIfExistsOrThrowError() {
        throw new IllegalStateException("Utility class");
    }

    static Owner getOwnerIfExists(final OwnerRepository ownerRepository, final String email) {
        return ownerRepository.fetchOptionalByEmail(email).orElseThrow(() -> new OwnerNotFoundException(email));
    }

}
