package com.maneger.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulletinRequest {
    private UUID studentClassId;

    private UUID teacherSubjectId;

    private Double noteValue;
}
