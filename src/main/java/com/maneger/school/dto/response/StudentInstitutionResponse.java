package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record StudentInstitutionResponse(UUID id,
                                         String studentCpf,
                                         String institutionCnpj,
                                         LocalDateTime startDate,
                                         LocalDateTime uptdateAt,
                                         String course,
                                         boolean status) {
}
