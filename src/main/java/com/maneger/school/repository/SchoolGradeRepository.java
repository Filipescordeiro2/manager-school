package com.maneger.school.repository;

import com.maneger.school.domain.SchoolGrade;
import com.maneger.school.dto.response.GradeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchoolGradeRepository extends JpaRepository<SchoolGrade, UUID> {
    @Query(value = """
        SELECT sg.id AS grade_id,
               i.name_institution AS name_institution,
               ts.school_subject_name AS school_subject_name,
               sg.school_class_name AS school_class_name,
               t.name_teacher AS name_teacher,
               sc.drade_of_schedules,
               sc.start_time,
               sc.end_time 
        FROM school_grade sg 
        JOIN teacher_subject ts ON sg.teacher_subject_id = ts.id 
        JOIN institutions i ON i.cnpj = ts.institution_cnpj 
        JOIN teachers t ON t.teacher_cpf = ts.teacher_cpf
        JOIN school_class sc ON sc.institution_cnpj = i.cnpj 
        WHERE sg.school_class_name = :className
        """, nativeQuery = true)
    List<GradeResponse> findSchoolGradeDetails(@Param("className") String className);
}