package com.cyver.plant.database;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyver.plant.commons.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {

    Optional<Owner> findByAuth0Sid(String auth0Sid);

}
