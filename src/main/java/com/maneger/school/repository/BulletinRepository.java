package com.maneger.school.repository;

import com.maneger.school.domain.Bulletin;
import com.maneger.school.dto.response.BulletinStudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BulletinRepository extends JpaRepository<Bulletin, UUID> {
    List<Bulletin> findByStudentClassId(UUID studentClassId);
    boolean existsByStudentClassIdAndTeacherSubjectId(UUID studentClassId, UUID teacherSubjectId);
    @Query("""
        SELECT new com.maneger.school.dto.response.BulletinStudentResponse(
            b.id, s.nameStudent, t.nameTeacher, ss.nameSubject, 
            sc2.semester, sc2.yearOfSemester, 
            b.noteValue, b.createdAt, b.updatedAt
        )
        FROM Bulletin b
        JOIN b.studentClass sc
        JOIN sc.student s
        JOIN b.teacherSubject ts
        JOIN ts.teacher t
        JOIN ts.schoolSubject ss
        JOIN sc.schoolClass sc2
        WHERE s.cpf = :cpf
    """)
    List<BulletinStudentResponse> findBulletinsByStudentCpf(@Param("cpf") String cpf);

}
