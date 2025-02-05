package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {


    private String teacherCpf;
    private String nameTeacher;
    private String surnameTeacher;
    private String cellFone;
    private LocalDate dateOfBirth;
    private String userAccess ;
    private String passwordAccess;
    private String email;
}
