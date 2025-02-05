package com.maneger.school.dto.response;

import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.enums.TypeUser;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TeacherResponse(String cpf,
                              String email,
                              String nameTeacher,
                              String surnameTeacher,
                              String cellPhone,
                              LocalDate dateOfBirth,
                              TypeUser typeUser,
                              LocalDateTime creatAt,
                              LocalDateTime uptdateAt,
                              String userAccess,
                              ReasonsForBlocking reasonsForBlocking,
                              String reasonsForBlockingDescription,
                              boolean status) {
}
