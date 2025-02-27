package com.maneger.school.repository;

import com.maneger.school.domain.Institution;
import com.maneger.school.domain.SchoolClass;
import com.maneger.school.domain.Student;
import com.maneger.school.domain.StudentInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, UUID> {
Optional<SchoolClass>findByNameClass(String nameClass);
}
