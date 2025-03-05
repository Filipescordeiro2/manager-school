package com.maneger.school.domain;

import com.maneger.school.dto.request.BulletinRequest;
import com.maneger.school.utils.Utilitarias.BulletinUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "bulletin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_class_id", nullable = false)
    private StudentClass studentClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_subject_id", nullable = false)
    private TeacherSubject teacherSubject;

    private Double noteValue;

    private String situation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Bulletin(BulletinRequest request) {
        this.studentClass = new StudentClass();
        this.studentClass.setId(request.getStudentClassId());

        this.teacherSubject = new TeacherSubject();
        this.teacherSubject.setId(request.getTeacherSubjectId());

        this.noteValue = request.getNoteValue();
        this.situation = BulletinUtils.validNoteValue(request.getNoteValue());
    }
}
