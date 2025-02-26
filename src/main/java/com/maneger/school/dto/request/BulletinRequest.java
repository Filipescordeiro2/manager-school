package com.maneger.school.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class BulletinRequest {
    private UUID studentClassId;

    private UUID teacherSubjectId;

    private Double noteValue;
}
