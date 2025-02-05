package com.maneger.school.repository;

import com.maneger.school.domain.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject,String> {
    Optional<SchoolSubject>findByNameSubject(String nameSubject);
}
