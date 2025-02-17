package com.maneger.school.domain;

import com.maneger.school.dto.request.SecretaryRequest;
import com.maneger.school.dto.request.StudentRequest;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.enums.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Secretary {

    @Id
    @CPF
    private String cpf;

    @Column(unique = true)
    private String userAccess ;
    private String passwordAccess;
    @Email
    @Column(unique = true)
    private String email;
    private String nameSecretary;
    private String surnameSecretary;
    private String cellPhone;
    private LocalDate dateOfBirth;
    private int age;
    private int loginAttempts;
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;
    @Enumerated(EnumType.STRING)
    private ReasonsForBlocking reasonsForBlocking;
    private String reasonsForBlockingDescription;
    private LocalDateTime creatAt;
    private LocalDateTime uptdateAt;
    private boolean status;

    @PrePersist
    public void prePersist(){
        this.status = true;
        this.typeUser = TypeUser.Secretary;
        this.creatAt = LocalDateTime.now();
        this.uptdateAt = LocalDateTime.now();
        this.age = Period.between(getDateOfBirth(), LocalDate.now()).getYears();
    }

    @PreUpdate
    public void preUpdate(){
        this.uptdateAt = LocalDateTime.now();
    }

    public Secretary(SecretaryRequest request){
        this.nameSecretary = request.getNameSecretary();
        this.surnameSecretary = request.getSurnameSecretary();
        this.cellPhone = request.getCellPhone();
        this.dateOfBirth = request.getDateOfBirth();
        this.userAccess = request.getUserAccess();
        this.passwordAccess = request.getPasswordAccess();
        this.email = request.getEmail();
        this.cpf = request.getCpf();
    }
}
