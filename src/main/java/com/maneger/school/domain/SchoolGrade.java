package com.maneger.school.domain;

import com.maneger.school.dto.request.SchoolGradeRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "school_grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolClass_name")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherSubject_id")
    private TeacherSubject teacherSubject;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean status;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public SchoolGrade(SchoolGradeRequest request) {
        this.schoolClass = new SchoolClass();
        this.schoolClass.setNameClass(request.getSchoolClassName());

        this.teacherSubject = new TeacherSubject();
        this.teacherSubject.setId(request.getTeacherSubjectId());

    }
}
