package com.maneger.school.dto.response;

import com.maneger.school.domain.SchoolSubject;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record TeacherSubjectResponse(String message,
                                     TeacherResponse teacherResponse,
                                     InstitutionResponse institutionResponses,
                                     SchoolSubjectResponse schoolSubjectResponse,
                                     LocalDateTime startDate,
                                     LocalDateTime uptdateAt,
                                     boolean bond) {
}
