package com.maneger.school.repository;

import com.maneger.school.domain.Institution;
import com.maneger.school.domain.Secretary;
import com.maneger.school.domain.SecretaryInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SecretaryInstitutionRepository extends JpaRepository<SecretaryInstitution, UUID> {

    List<SecretaryInstitution> findBySecretary(Secretary secretary);
    List<SecretaryInstitution> findByInstitution(Institution institution);
}
