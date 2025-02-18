package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Secretary;
import com.maneger.school.domain.Student;
import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.response.SecretaryResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.exception.LoginException;
import com.maneger.school.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecretaryUtils{

    private final SecretaryRepository secretaryRepository;

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

    public Secretary findStudentByCpf(String cpf) {
        return secretaryRepository.findByCpf(cpf)
                .orElseThrow(() -> new LoginException("User not found"));
    }


    public void handleFailedLoginAttempt(LoginRequest request) {
        var secretary = secretaryRepository.findByUserAccess(request.getUserAccess())
                .orElseThrow(() -> new LoginException("Username or password is incorrect"));
        int attempts = secretary.getLoginAttempts() + 1;
        if (attempts >= 3) {
            secretary.setStatus(false);
            secretary.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_1);
            secretary.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_1.getDescription());
        }
        secretary.setLoginAttempts(attempts);
        secretaryRepository.save(secretary);
    }
}
