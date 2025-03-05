package com.maneger.school.dto.response;

import com.maneger.school.domain.SchoolSubject;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
public record TeacherSubjectResponse(UUID id,
                                     String message,
                                     String teacherCpf,
                                     String institutionCnpj,
                                     String schoolSubjectName,
                                     LocalDateTime startDate,
                                     LocalDateTime uptdateAt,
                                     boolean bond) {
}
