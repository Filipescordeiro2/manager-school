package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record StudentClassResponse(String message,
                                   LocalDateTime createAt) {
}
