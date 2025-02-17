package com.maneger.school.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretarytInstitutionRequest {

    private String secretaryCPF;
    private String institutionCNPJ;
    private String course;
}
