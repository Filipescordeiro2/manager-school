package com.maneger.school.repository;

import com.maneger.school.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
    Optional<Teacher> findByUserAccessAndPasswordAccess(String userAccess, String passwordAccess);
    Optional<Teacher> findByUserAccess(String userAccess);
    Optional<Teacher> findByTeacherCpf(String cpf);
    Optional<Teacher> findByEmail(String email);
    Page<Teacher> findByStatusTrue(Pageable pageable);
}
