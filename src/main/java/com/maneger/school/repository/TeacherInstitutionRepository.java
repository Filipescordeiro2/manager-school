package com.maneger.school.repository;

import com.maneger.school.domain.Institution;
import com.maneger.school.domain.Student;

import com.maneger.school.domain.Teacher;
import com.maneger.school.domain.TeacherInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherInstitutionRepository extends JpaRepository<TeacherInstitution, UUID> {

    List<TeacherInstitution> findByTeacher(Teacher teacher);
    List<TeacherInstitution> findByInstitution(Institution institution);
    Optional<TeacherInstitution> findByTeacher_TeacherCpfAndInstitution_Cnpj(String teacherCpf, String institutionCnpj);

}
