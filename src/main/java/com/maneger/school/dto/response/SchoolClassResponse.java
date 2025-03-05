package com.maneger.school.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record SchoolClassResponse(UUID id,
                                  String nameClass,
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
