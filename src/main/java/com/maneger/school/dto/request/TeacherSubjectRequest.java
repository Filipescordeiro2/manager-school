package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubjectRequest {

    private String teacherCPF;
    private String institutionCNPJ;
    private String nameSchoolSubject;
}