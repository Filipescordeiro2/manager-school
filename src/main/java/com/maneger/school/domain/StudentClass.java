package com.maneger.school.domain;

import com.maneger.school.dto.request.StudentClassRequest;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "student_class")
@NoArgsConstructor
@AllArgsConstructor
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolClass_id")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_cpf")
    private Student student;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean registration;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.registration = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public StudentClass(StudentClassRequest request) {
        this.student = new Student();
        this.student.setCpf(request.getStudentCPF());
        this.schoolClass = new SchoolClass();
        this.schoolClass.setId(request.getSchoolClassId());
    }
}
