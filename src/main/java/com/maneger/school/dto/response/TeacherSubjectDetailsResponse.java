package com.maneger.school.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubjectDetailsResponse {

    private String nameTeacher;
    private String nameInstitution;
    private String schoolSubjectName;
    private LocalDateTime createAt;
    private LocalDateTime uptdateAt;
    private boolean bond;
}