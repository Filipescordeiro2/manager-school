package com.maneger.school.domain;

import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.request.TeacherSubjectRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_cpf")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_cnpj")
    private Institution institution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolSubject_name")
    private SchoolSubject schoolSubject;

    private LocalDateTime startDate;
    private LocalDateTime uptdateAt;
    private boolean bond;

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now();
        this.uptdateAt = LocalDateTime.now();
        this.bond = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.uptdateAt = LocalDateTime.now();
    }

    public TeacherSubject(TeacherSubjectRequest request) {
        this.teacher = new Teacher();
        this.teacher.setTeacherCpf(request.getTeacherCPF());

        this.institution = new Institution();
        this.institution.setCnpj(request.getInstitutionCNPJ());

        this.schoolSubject = new SchoolSubject();
        this.schoolSubject.setNameSubject(request.getNameSchoolSubject());
    }

}
