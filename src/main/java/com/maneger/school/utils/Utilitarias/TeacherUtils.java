package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Teacher;
import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.response.TeacherResponse;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.exception.LoginException;
import com.maneger.school.repository.TeacherRepository;
import com.maneger.school.utils.Validation.TeacherValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherUtils {

    private final TeacherRepository teacherRepository;
    private final TeacherValidation validation;

    public void validateTeacher(Teacher teacher) {
        validation.validateDuplicateTeacher(teacher.getTeacherCpf(), teacher.getUserAccess(), teacher.getEmail());
        validation.validatePassword(teacher.getPasswordAccess());
    }

    public Teacher findTeacherLogin(String userAccss,String password) {
        return teacherRepository.findByUserAccessAndPasswordAccess(userAccss,password)
                .orElseThrow(() -> new LoginException("User not found"));
    }

    public Teacher findTeacherByCpf(String cpf) {
        return teacherRepository.findByTeacherCpf(cpf)
                .orElseThrow(() -> new LoginException("User not found"));
    }

    public void handleFailedLoginAttempt(LoginRequest request) {
        var teacher = teacherRepository.findByUserAccess(request.getUserAccess())
                .orElseThrow(() -> new LoginException("User not found"));
        int attempts = teacher.getLoginAttempts() + 1;
        if (attempts >= 3) {
            teacher.setStatus(false);
            teacher.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_1);
            teacher.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_1.getDescription());
        }
        teacher.setLoginAttempts(attempts);
        teacherRepository.save(teacher);
    }

    public TeacherResponse convertToTeacherResponse(Teacher teacherEntity) {
        return TeacherResponse.builder()
                .nameTeacher(teacherEntity.getNameTeacher())
                .cpf(teacherEntity.getTeacherCpf())
                .cellPhone(teacherEntity.getCellFone())
                .email(teacherEntity.getEmail())
                .typeUser(teacherEntity.getTypeUser())
                .creatAt(teacherEntity.getCreatAt())
                .uptdateAt(teacherEntity.getUptdateAt())
                .status(teacherEntity.isStatus())
                .dateOfBirth(teacherEntity.getDateOfBirth())
                .surnameTeacher(teacherEntity.getSurnameTeacher())
                .userAccess(teacherEntity.getUserAccess())
                .reasonsForBlocking(teacherEntity.getReasonsForBlocking())
                .reasonsForBlockingDescription(teacherEntity.getReasonsForBlockingDescription())
                .build();
    }
}