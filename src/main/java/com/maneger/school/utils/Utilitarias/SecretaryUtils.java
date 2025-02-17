package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Secretary;
import com.maneger.school.domain.Student;
import com.maneger.school.dto.response.SecretaryResponse;
import com.maneger.school.dto.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecretaryUtils{

    public SecretaryResponse convertToSecretaryResponse(Secretary secretary) {
        return SecretaryResponse.builder()
                .nameSecretary(secretary.getNameSecretary())
                .cpf(secretary.getCpf())
                .cellPhone(secretary.getCellPhone())
                .email(secretary.getEmail())
                .typeUser(secretary.getTypeUser())
                .creatAt(secretary.getCreatAt())
                .uptdateAt(secretary.getUptdateAt())
                .status(secretary.isStatus())
                .dateOfBirth(secretary.getDateOfBirth())
                .surnameSecretary(secretary.getSurnameSecretary())
                .userAccess(secretary.getUserAccess())
                .reasonsForBlocking(secretary.getReasonsForBlocking())
                .reasonsForBlockingDescription(secretary.getReasonsForBlockingDescription())
                .build();
    }
}
