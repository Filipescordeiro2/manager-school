package com.maneger.school.domain;

import com.maneger.school.dto.request.StudentRequest;
import com.maneger.school.dto.request.TeacherRequest;
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
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @CPF
    private String teacherCpf;

    private String nameTeacher;
    private String surnameTeacher;
    @Email
    private String email;
    private String cellFone;
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

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeacherSubject> teacherSubjects;

    @PrePersist
    public void prePersist(){
        this.status = true;
        this.typeUser = TypeUser.Teacher;
        this.creatAt = LocalDateTime.now();
        this.uptdateAt = LocalDateTime.now();
        this.age = Period.between(getDateOfBirth(), LocalDate.now()).getYears();
    }

    @PreUpdate
    public void preUpdate(){
        this.uptdateAt = LocalDateTime.now();
    }

    public Teacher(TeacherRequest request){
        this.nameTeacher = request.getNameTeacher();
        this.surnameTeacher = request.getSurnameTeacher();
        this.cellFone = request.getCellFone();
        this.dateOfBirth = request.getDateOfBirth();
        this.userAccess = request.getUserAccess();
        this.passwordAccess = request.getPasswordAccess();
        this.email = request.getEmail();
        this.teacherCpf = request.getTeacherCpf();
    }

}

