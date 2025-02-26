package com.maneger.school.domain;

import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.enums.TypeOfInstitution;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Institutions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Institution {

    @Id
    @CNPJ
    private String cnpj;

    private String nameInstitution;
    private String cellPhone;
    @Email
    private String email;
    private LocalDateTime creatAt;
    private LocalDateTime uptdateAt;
    @Enumerated(EnumType.STRING)
    private TypeOfInstitution typeOfInstitution;
    private boolean status;

    /*
    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentInstitution> studentInstitutions;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeacherSubject> teacherSubjects;

     */

    @PrePersist
    public void prePersist(){
        this.status = true;
        this.creatAt = LocalDateTime.now();
        this.uptdateAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.uptdateAt = LocalDateTime.now();
    }

    public Institution(InstitutionRequest request) {
        this.nameInstitution = request.getNameInstitution();
        this.email = request.getEmail();
        this.cellPhone = request.getCellPhone();
        this.cnpj = request.getCnpj();
        this.typeOfInstitution = request.getTypeOfInstitution();
    }
}