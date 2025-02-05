package com.maneger.school.repository;

import com.maneger.school.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByUserAccessAndPasswordAccess(String userAccess, String passwordAccess);
    Optional<Student> findByUserAccess(String userAccess);
    Optional<Student> findByCpf(String cpf);
    Optional<Student> findByEmail(String email);
    Page<Student> findByStatusTrue(Pageable pageable);

}
