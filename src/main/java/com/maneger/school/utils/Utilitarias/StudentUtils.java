package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Student;
import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.response.LoginAlunoResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.exception.LoginException;
import com.maneger.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentUtils {

    private final StudentRepository studentRepository;

    public LoginAlunoResponse authenticateAndBuildResponse(LoginRequest request) {
        var studentEntity = studentRepository.findByUserAccessAndPasswordAccess(request.getUserAccess(), request.getPasswordAccess())
                .orElseThrow(() -> new LoginException("User or Password is incorrect"));

        if (!studentEntity.isStatus()) {
            log.error("The user is deactivated -- Reason: " + studentEntity.getReasonsForBlockingDescription() + " -- Contact the Admin");
            throw new LoginException("User blocked");
        }
        studentEntity.setLoginAttempts(0);
        studentRepository.save(studentEntity);

        var studentResponse = convertToStudentResponse(studentEntity);
        return LoginAlunoResponse.builder()
                .message("Login successfully")
                .alunoResponse(studentResponse)
                .build();
    }

    public Student findStudentByCpf(String cpf) {
        return studentRepository.findByCpf(cpf)
                .orElseThrow(() -> new LoginException("User not found"));
    }

    public void handleFailedLoginAttempt(LoginRequest request) {
        var student = studentRepository.findByUserAccess(request.getUserAccess())
                .orElseThrow(() -> new LoginException("Username or password is incorrect"));
        int attempts = student.getLoginAttempts() + 1;
        if (attempts >= 3) {
            student.setStatus(false);
            student.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_1);
            student.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_1.getDescription());
        }
        student.setLoginAttempts(attempts);
        studentRepository.save(student);
    }

    public StudentResponse convertToStudentResponse(Student studentEntity) {
        return StudentResponse.builder()
                .nameStudent(studentEntity.getNameStudent())
                .cpf(studentEntity.getCpf())
                .cellPhone(studentEntity.getCellPhone())
                .email(studentEntity.getEmail())
                .typeUser(studentEntity.getTypeUser())
                .creatAt(studentEntity.getCreatAt())
                .uptdateAt(studentEntity.getUptdateAt())
                .status(studentEntity.isStatus())
                .dateOfBirth(studentEntity.getDateOfBirth())
                .surnameStudent(studentEntity.getSurnameStudent())
                .userAccess(studentEntity.getUserAccess())
                .reasonsForBlocking(studentEntity.getReasonsForBlocking())
                .reasonsForBlockingDescription(studentEntity.getReasonsForBlockingDescription())
                .build();
    }
}