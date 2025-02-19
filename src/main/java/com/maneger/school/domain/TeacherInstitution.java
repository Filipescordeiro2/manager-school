package com.maneger.school.domain;

import com.maneger.school.dto.request.TeacherInstitutionRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "teacher_institution")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_cpf")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_cnpj", referencedColumnName = "cnpj")
    private Institution institution;

    private LocalDateTime startDate;
    private LocalDateTime uptdateAt;
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

    public TeacherInstitution(TeacherInstitutionRequest request) {
        this.teacher = new Teacher();
        this.teacher.setTeacherCpf(request.getTeacherCPF());
        this.institution = new Institution();
        this.institution.setCnpj(request.getInstitutionCNPJ());
    }
}
