package com.maneger.school.repository;

import com.maneger.school.domain.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String> {
    Optional<Institution> findByNameInstitutionIgnoreCase(String nameInstitution);


}
