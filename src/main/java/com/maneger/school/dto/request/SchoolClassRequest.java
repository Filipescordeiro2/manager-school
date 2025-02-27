package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolClassRequest {

    private String nameClass;
    private String dradeOfSchedules;
    private String period;
    private String yearOfSemester;
    private String semester;
    private String startTime;
    private String endTime;
    private String institutionCNPJ;

}
