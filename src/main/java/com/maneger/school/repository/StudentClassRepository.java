package com.maneger.school.repository;

import com.maneger.school.domain.Student;
import com.maneger.school.domain.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentClassRepository extends JpaRepository<StudentClass, UUID> {
    List<StudentClass> findByStudent(Student student);
    boolean existsByStudent_CpfAndSchoolClass_Id(String studentCpf, UUID id);
    List<StudentClass> findByStudent_Cpf(String studentCpf);


}
