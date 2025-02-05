package com.maneger.school.domain;

import com.maneger.school.dto.request.StudentInstitutionRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "student_institution")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_cpf")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_cnpj", referencedColumnName = "cnpj")
    private Institution institution;

    private LocalDateTime startDate;
    private LocalDateTime uptdateAt;
    private String course;
    private boolean registration;

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now();
        this.uptdateAt = LocalDateTime.now();
        this.registration = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.uptdateAt = LocalDateTime.now();
    }

    public StudentInstitution(StudentInstitutionRequest request) {
        this.student = new Student();
        this.student.setCpf(request.getStudentCPF());
        this.institution = new Institution();
        this.institution.setCnpj(request.getInstitutionCNPJ());
        this.course = request.getCourse();
    }
}