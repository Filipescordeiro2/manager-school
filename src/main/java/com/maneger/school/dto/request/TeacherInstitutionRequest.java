package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInstitutionRequest {

    private String teacherCPF;
    private String institutionCNPJ;
}
