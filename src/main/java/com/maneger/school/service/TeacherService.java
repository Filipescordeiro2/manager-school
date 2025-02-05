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
import com.maneger.school.utils.Utilitarias.TeacherUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherUtils teacherUtils;
    private final TeacherRepository teacherRepository;

    public TeacherResponse saveTeacher(TeacherRequest request) {
        log.info("Start of service [saveTeacher] -- Body request: " + request);
        try {
            var teacher = new Teacher(request);
            teacherUtils.validateTeacher(teacher);
            var teacherSaved = teacherRepository.save(teacher);
            var response = teacherUtils.convertToTeacherResponse(teacherSaved);
            log.info("Created teacher -- response: " + response);
            return response;
        } catch (TeacherException e) {
            log.error("Error in validate teacher: " + e.getMessage());
            throw new TeacherException(e.getMessage());
        } catch (Exception e) {
            log.error("Error created teacher: " + e.getMessage());
            throw new RuntimeException("Error created teacher: "+e.getMessage());
        }
    }

    public LoginTeacherResponse loginteacher(LoginRequest request) {
        log.info("Start of service [loginteacher] -- Body request: " + request);
        try {
            var teacherEntity = teacherUtils.findTeacherByCpf(request.getUserAccess());
            if (!teacherEntity.isStatus()) {
                throw new LoginInstitutionException("The user is deactivated -- Reason: " + teacherEntity.getReasonsForBlockingDescription() + " -- Contact the Admin");
            }
            teacherEntity.setLoginAttempts(0); // Reset login attempts on successful login
            teacherUtils.validateTeacher(teacherEntity);
            var teacherSaved= teacherRepository.save(teacherEntity);
            var teacher = teacherUtils.convertToTeacherResponse(teacherSaved);
            var response = LoginTeacherResponse.builder()
                    .message("Login successfully")
                    .teacherResponse(teacher)
                    .build();
            log.info("Login successful -- response: " + response);
            return response;
        } catch (LoginInstitutionException e) {
            log.error("Login failed: " + e.getMessage());
            teacherUtils.handleFailedLoginAttempt(request);
            throw new LoginInstitutionException(e.getMessage());
        } catch (Exception e) {
            log.error("Error during login: " + e.getMessage());
            throw new RuntimeException("Error during Login " + e.getMessage());
        }
    }

    public TeacherResponse ActivateAcessteacher(String cpf) {
        try{
            var teacher = teacherUtils.findTeacherByCpf(cpf);
            if (!teacher.isStatus()) {
                teacher.setStatus(true);
                teacher.setReasonsForBlocking(null);
                teacher.setReasonsForBlockingDescription(null);
                teacher.setLoginAttempts(0);
                log.info("Access enabled");
            }
            teacherUtils.validateTeacher(teacher);
            var teacherSaved = teacherRepository.save(teacher);
            return teacherUtils.convertToTeacherResponse(teacherSaved);
        }catch (Exception e){
            throw new TeacherException("Erro activate Teacher: "+e.getMessage());
        }
    }

    public TeacherResponse DisabledAcessteacher(String cpf) {
        try{
            var teacher = teacherUtils.findTeacherByCpf(cpf);
            if (teacher.isStatus()) {
                teacher.setStatus(false);
                teacher.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_2);
                teacher.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_2.getDescription());
                teacher.setLoginAttempts(0);
                log.info("Disabled Teacher Access");
            }
            teacherUtils.validateTeacher(teacher);
            var teacherSaved = teacherRepository.save(teacher);
            return teacherUtils.convertToTeacherResponse(teacherSaved);

        }catch (Exception e){
            throw new TeacherException("Erro in Disabled Teacher: "+e.getMessage());
        }

    }
}