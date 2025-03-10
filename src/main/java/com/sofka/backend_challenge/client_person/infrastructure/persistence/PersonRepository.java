package com.sofka.backend_challenge.client_person.infrastructure.persistence;

import com.sofka.backend_challenge.client_person.domain.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByIdentification(String identification);
}
