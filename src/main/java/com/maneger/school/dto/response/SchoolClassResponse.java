package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SchoolClassResponse(String nameClass,
                                  String dradeOfSchedules,
                                  String period,
                                  String yearOfSemester,
                                  String semester,
                                  String startDate,
                                  String endDate,
                                  LocalDateTime createAt,
                                  LocalDateTime updateAt,
                                  boolean status,
                                  String institutionCnpj) {
}
