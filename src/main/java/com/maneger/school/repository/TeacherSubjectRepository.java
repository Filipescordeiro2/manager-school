package com.maneger.school.repository;


import com.maneger.school.domain.Teacher;
import com.maneger.school.domain.TeacherSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, UUID> {
    List<TeacherSubject> findByTeacher(Teacher teacher);
}