package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolGradeRequest {

    private UUID teacherSubjectId;
    private String schoolClassName;
    private String dayOfClassroom;
    private String startTime;
    private String endTime;

}
