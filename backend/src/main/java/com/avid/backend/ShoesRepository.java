package com.avid.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShoesRepository extends JpaRepository<ShoesInstockEntity, UUID> {
    Optional<ShoesInstockEntity> findByNameAndSize(String name, String size);
}
