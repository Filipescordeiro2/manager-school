package com.maneger.school.repository;

import com.maneger.school.domain.Institution;
import com.maneger.school.domain.Student;
import com.maneger.school.domain.StudentInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentInstitutionRepository extends JpaRepository<StudentInstitution, UUID> {
    List<StudentInstitution> findByStudent(Student student);
    List<StudentInstitution> findByInstitution(Institution institution);
}