package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInstitutionRequest {

        private String studentCPF;
        private String institutionCNPJ;
        private String course;
}
