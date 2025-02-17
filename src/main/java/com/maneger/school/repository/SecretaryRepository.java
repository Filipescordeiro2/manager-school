package com.maneger.school.repository;

import com.maneger.school.domain.Secretary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary,String> {
    Optional<Secretary> findByUserAccessAndPasswordAccess(String userAccess, String passwordAccess);
    Optional<Secretary> findByUserAccess(String userAccess);
    Optional<Secretary> findByCpf(String cpf);
    Optional<Secretary> findByEmail(String email);
    Page<Secretary> findByStatusTrue(Pageable pageable);
}
