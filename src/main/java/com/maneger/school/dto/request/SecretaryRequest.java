package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretaryRequest {

    private String cpf;
    private String email;
    private String nameSecretary;
    private String surnameSecretary;
    private LocalDate dateOfBirth;
    private String userAccess;
    private String passwordAccess;
    private String cellPhone;
    
}
