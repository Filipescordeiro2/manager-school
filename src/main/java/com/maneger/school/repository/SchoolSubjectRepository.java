package com.maneger.school.repository;

import com.maneger.school.domain.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject,String> {
}
