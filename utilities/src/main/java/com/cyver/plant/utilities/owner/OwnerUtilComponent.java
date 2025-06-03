package com.cyver.plant.utilities.owner;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.cyver.plant.database.cloud.tables.dtos.Owner;
import com.cyver.plant.database.cloud.tables.repositories.OwnerRepository;

@Component
public class OwnerUtilComponent {

    public Owner getOwnerByUUIDIfExists(final OwnerRepository ownerRepository, final UUID ownerId) {
        return GetOwnerIfExistsOrThrowError.getOwnerByUUIDIfExists(ownerRepository, ownerId);
    }

    public Owner getOwnerByEmailIfExists(final OwnerRepository ownerRepository, final String email) {
        return GetOwnerByEmailIfExistsOrThrowError.getOwnerIfExists(ownerRepository, email);
    }
}
