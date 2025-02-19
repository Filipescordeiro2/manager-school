package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TeacherInstitutionResponse(String teacherCpf,
                                         String institutionCnpj,
                                         LocalDateTime startDate,
                                         LocalDateTime uptdateAt,
                                         boolean status) {
}
