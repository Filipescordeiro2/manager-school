package com.maneger.school.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretaryRequest {

    @CPF
    private String cpf;
    @Email
    private String email;
    private String nameSecretary;
    private String surnameSecretary;
    private LocalDate dateOfBirth;
    private String userAccess;
    private String passwordAccess;
    private String cellPhone;
    
}
