package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BulletinResponse(
        UUID id,
        UUID studentClassId,
        UUID teacherSubjectId,
        Double noteValue,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}