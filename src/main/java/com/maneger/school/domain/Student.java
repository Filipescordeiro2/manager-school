package com.maneger.school.domain;

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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "studant")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    @CPF
    private String cpf;

    @Email
    @Column(unique = true)
    private String email;
    private String nameStudent;
    private String surnameStudent;
    private String cellPhone;
    private LocalDate dateOfBirth;
    private int age;
    private LocalDateTime creatAt;
    private LocalDateTime uptdateAt;
    @Column(unique = true)
    private String userAccess ;
    private String passwordAccess;
    private int loginAttempts;
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;
    @Enumerated(EnumType.STRING)
    private ReasonsForBlocking reasonsForBlocking;
    private String reasonsForBlockingDescription;
    private boolean status;

    /*
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentInstitution> studentInstitutions;


     */


    @PrePersist
    public void prePersist(){
        this.status = true;
        this.typeUser = TypeUser.Student;
        this.creatAt = LocalDateTime.now();
        this.uptdateAt = LocalDateTime.now();
        this.age = Period.between(getDateOfBirth(), LocalDate.now()).getYears();
    }

    @PreUpdate
    public void preUpdate(){
        this.uptdateAt = LocalDateTime.now();
    }

    public Student(StudentRequest request){
        this.nameStudent = request.getNameStudent();
        this.surnameStudent = request.getSurnameStudent();
        this.cellPhone = request.getCellPhone();
        this.dateOfBirth = request.getDateOfBirth();
        this.userAccess = request.getUserAccess();
        this.passwordAccess = request.getPasswordAccess();
        this.email = request.getEmail();
        this.cpf = request.getCpf();
    }
}