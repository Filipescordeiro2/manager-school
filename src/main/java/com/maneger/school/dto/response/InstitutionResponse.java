package com.maneger.school.dto.response;

import com.maneger.school.enums.TypeOfInstitution;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record InstitutionResponse(String nameInstitution,
                                  String cnpj,
                                  String cellPhone,
                                  String email,
                                  LocalDateTime creatAt,
                                  LocalDateTime uptdateAt,
                                  TypeOfInstitution typeOfInstitution,
                                  boolean status) {
}
