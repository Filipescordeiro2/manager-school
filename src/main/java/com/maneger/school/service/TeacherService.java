package com.maneger.school.service;

import com.maneger.school.domain.Teacher;
import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.request.TeacherRequest;

import com.maneger.school.dto.response.LoginTeacherResponse;
import com.maneger.school.dto.response.TeacherResponse;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.exception.LoginInstitutionException;
import com.maneger.school.exception.TeacherException;
import com.maneger.school.repository.TeacherRepository;
import com.maneger.school.util.TeacherValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherValidation validation;

    public TeacherResponse saveTeacher(TeacherRequest request) {
        log.info("Start of service [saveTeacher] -- Body request: " + request);
        try {
            validation.validateDuplicateTeacher(request.getTeacherCpf(),request.getUserAccess(),request.getEmail());
            validation.validatePassword(request.getPasswordAccess());
            var teacher = new Teacher(request);
            var teacherSaved = teacherRepository.save(teacher);
            var response = convertToTeacherResponse(teacherSaved);
            log.info("Created teacher -- response: " + response);
            return response;
        } catch (TeacherException e) {
            log.error("Error in validate teacher: " + e.getMessage());
            throw new TeacherException(e.getMessage());
        } catch (Exception e) {
            log.error("Error created teacher: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public LoginTeacherResponse loginteacher(LoginRequest request) {
        log.info("Start of service [loginteacher] -- Body request: " + request);
        try {
            var teacherEntity = teacherRepository.findByUserAccessAndPasswordAccess(request.getUserAccess(), request.getPasswordAccess())
                    .orElseThrow(() -> new LoginInstitutionException("User or Password is incorrect"));
            if (!teacherEntity.isStatus()) {
                throw new LoginInstitutionException("The user is deactivated -- Reason: "+teacherEntity.getReasonsForBlockingDescription()+" -- Contact the Admin");
            }
            teacherEntity.setLoginAttempts(0); // Reset login attempts on successful login
            teacherRepository.save(teacherEntity); // Save the updated student
            var teacher = convertToTeacherResponse(teacherEntity);
            var response = LoginTeacherResponse
                    .builder()
                    .message("Login successfully")
                    .teacherResponse(teacher)
                    .build();
            log.info("Login successful -- response: " + response);
            return response;
        } catch (LoginInstitutionException e) {
            log.error("Login failed: " + e.getMessage());
            handleFailedLoginAttempt(request);
            throw new LoginInstitutionException(e.getMessage());
        } catch (Exception e) {
            log.error("Error during login: " + e.getMessage());
            throw new RuntimeException("Error Login " + e.getMessage());
        }
    }

    public TeacherResponse ActivateAcessteacher(String cpf){
        var teacher = teacherRepository.findByTeacherCpf(cpf)
                .orElseThrow(()->new LoginInstitutionException("User not found"));
        if (!teacher.isStatus()){
            teacher.setStatus(true);
            teacher.setReasonsForBlocking(null);
            teacher.setReasonsForBlockingDescription(null);
            teacher.setLoginAttempts(0);
            log.info("Access enabled");
        }
        teacherRepository.save(teacher);
        var response = convertToTeacherResponse(teacher);
        return response;
    }

    public TeacherResponse DisabledAcessteacher(String cpf) {
        var teacher = teacherRepository.findByTeacherCpf(cpf)
                .orElseThrow(() -> new LoginInstitutionException("User not found"));
        if (teacher.isStatus()) {
            teacher.setStatus(false);
            teacher.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_2);
            teacher.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_2.getDescription());
            teacher.setLoginAttempts(0);
            log.info("Disabled Student Access");
        }
        teacherRepository.save(teacher);
        var response = convertToTeacherResponse(teacher);
        return response;
    }


    private void handleFailedLoginAttempt(LoginRequest request) {
        var teacher = teacherRepository.findByUserAccess(request.getUserAccess())
                .orElseThrow(() -> new LoginInstitutionException("User not found"));
        int attempts = teacher.getLoginAttempts() + 1;
        if (attempts >= 3) {
            teacher.setStatus(false);
            teacher.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_1);
            teacher.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_1.getDescription());
            log.warn(teacher.getReasonsForBlockingDescription());
        }
        teacher.setLoginAttempts(attempts);
        teacherRepository.save(teacher);
    }

    private TeacherResponse convertToTeacherResponse(Teacher teacher) {
        return TeacherResponse
                .builder()
                .nameTeacher(teacher.getNameTeacher())
                .cpf(teacher.getTeacherCpf())
                .cellPhone(teacher.getCellFone())
                .email(teacher.getEmail())
                .typeUser(teacher.getTypeUser())
                .creatAt(teacher.getCreatAt())
                .uptdateAt(teacher.getUptdateAt())
                .status(teacher.isStatus())
                .dateOfBirth(teacher.getDateOfBirth())
                .surnameTeacher(teacher.getSurnameTeacher())
                .userAccess(teacher.getUserAccess())
                .reasonsForBlocking(teacher.getReasonsForBlocking())
                .reasonsForBlockingDescription(teacher.getReasonsForBlockingDescription())
                .build();
    }
}
