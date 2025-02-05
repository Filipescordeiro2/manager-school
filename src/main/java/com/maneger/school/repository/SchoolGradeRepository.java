package com.maneger.school.repository;

import com.maneger.school.domain.SchoolGrade;
import org.apache.kafka.common.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolGradeRepository extends JpaRepository<SchoolGrade, Uuid> {

}
