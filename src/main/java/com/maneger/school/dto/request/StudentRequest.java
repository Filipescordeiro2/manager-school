package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String cpf;
    private String email;
    private String nameStudent;
    private String surnameStudent;
    private LocalDate dateOfBirth;
    private String userAccess;
    private String passwordAccess;
    private String cellPhone;
    private UUID institutionId;


}
