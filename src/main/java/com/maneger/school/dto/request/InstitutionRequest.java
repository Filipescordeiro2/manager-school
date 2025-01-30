package com.maneger.school.dto.request;

import com.maneger.school.enums.TypeOfInstitution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionRequest {

    private String nameInstitution;
    private String cnpj;
    private String cellPhone;
    private String email;
    private String userAccess ;
    private String passwordAccess;
    private TypeOfInstitution typeOfInstitution;

}
