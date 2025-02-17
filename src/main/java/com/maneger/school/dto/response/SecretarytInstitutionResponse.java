package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SecretarytInstitutionResponse(String studentCpf,
                                            String institutionCnpj,
                                            LocalDateTime startDate,
                                            LocalDateTime uptdateAt,
                                            String course,
                                            boolean status) {
}
