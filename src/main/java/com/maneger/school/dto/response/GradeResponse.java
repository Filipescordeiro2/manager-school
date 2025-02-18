package com.maneger.school.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeResponse {

    private String gradeId;
    private String nameInstitution;
    private String schoolSubjectName;
    private String schoolClassName;
    private String nameTeacher;
    private String gradeOfSchedules;
    private String startTime;
    private String endTime;

}
