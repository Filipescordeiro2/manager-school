package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record TeacherInstitutionResponse(UUID id,
                                         String teacherCpf,
                                         String institutionCnpj,
                                         LocalDateTime startDate,
                                         LocalDateTime uptdateAt,
                                         boolean status) {
}
