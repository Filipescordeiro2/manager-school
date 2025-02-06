package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record StudentInstitutionResponse(String studentCpf,
                                         String institutionCnpj,
                                         LocalDateTime startDate,
                                         LocalDateTime uptdateAt,
                                         String course,
                                         boolean status) {
}
