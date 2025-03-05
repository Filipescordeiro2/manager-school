package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record SchoolGradeResponse(UUID id,
                                  String teacherSubjecName,
                                  String schoolClassName,
                                  String dayOfClassroom,
                                  String startTime,
                                  String endTime,
                                  LocalDateTime createAt,
                                  LocalDateTime updateAt,
                                  boolean status) {
}
