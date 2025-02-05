package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SchoolGradeResponse(String teacherSubjecName,
                                  String schoolClassName,
                                  LocalDateTime createAt,
                                  LocalDateTime updateAt,
                                  boolean status) {
}
