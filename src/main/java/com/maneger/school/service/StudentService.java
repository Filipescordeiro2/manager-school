package com.maneger.school.service;

import com.maneger.school.domain.Student;
import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.request.StudentRequest;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.dto.response.LoginAlunoResponse;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.exception.LoginInstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import com.maneger.school.utils.Utilitarias.StudentUtils;
import com.maneger.school.utils.Validation.StudantValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudantValidation validation;
    private final StudentInstitutionRepository studentInstitutionRepository;
    private final StudentUtils studentUtils;

    public StudentResponse saveStudant(StudentRequest request) {
        log.info("Start of service [saveStudant] -- Body request: " + request);
        try {
            var student = new Student(request);
            validation.validateStudent(student);
            var studentSaved = studentRepository.save(student);
            var response = studentUtils.convertToStudentResponse(studentSaved);
            log.info("Created Studant -- response: " + response);
            return response;
        } catch (StudantException e) {
            log.error("Error in validate Studant: " + e.getMessage());
            throw new StudantException(e.getMessage());
        } catch (Exception e) {
            log.error("Error created Studant: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Page<StudentResponse> findAll(Pageable pageable) {
        var students = studentRepository.findByStatusTrue(pageable);
        return students.map(studentUtils::convertToStudentResponse);
    }

    public LoginAlunoResponse loginStudant(LoginRequest request) {
        log.info("Start of service [loginStudant] -- Body request: " + request);
        try {
            var response = studentUtils.authenticateAndBuildResponse(request);
            log.info("Login successful -- response: " + response);
            return response;
        } catch (LoginInstitutionException e) {
            log.error("Login failed: " + e.getMessage());
            studentUtils.handleFailedLoginAttempt(request);
            throw new LoginInstitutionException(e.getMessage());
        } catch (Exception e) {
            log.error("Error during login: " + e.getMessage());
            throw new RuntimeException("Error Login: " + e.getMessage());
        }
    }

    public StudentResponse ActivateAcessStudant(String cpf) {
        try{
            var studant = studentUtils.findStudentByCpf(cpf);
            if (!studant.isStatus()) {
                studant.setStatus(true);
                studant.setReasonsForBlocking(null);
                studant.setReasonsForBlockingDescription(null);
                studant.setLoginAttempts(0);
                log.info("Access enabled");
            }
            studentRepository.save(studant);
            return studentUtils.convertToStudentResponse(studant);
        }catch (Exception e){
            throw new StudantException("Error in activate Studant: "+e.getMessage());
        }
    }

    public StudentResponse DisabledAcessStudant(String cpf) {
        try{
            var studant = studentUtils.findStudentByCpf(cpf);
            var linkstudants = studentInstitutionRepository.findByStudent(studant);
            validation.validStatusForDisanble(studant.isStatus());
            if (studant.isStatus()) {
                studant.setStatus(false);
                studant.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_2);
                studant.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_2.getDescription());
                studant.setLoginAttempts(0);
                log.info("Disabled Student Access");
                linkstudants.forEach(linkstudant -> {
                    linkstudant.setRegistration(false);
                    studentInstitutionRepository.save(linkstudant);
                });
            }
            studentRepository.save(studant);
            return studentUtils.convertToStudentResponse(studant);
        }catch (Exception e){
            throw new StudantException("Error in Disabled for Student: "+e.getMessage());
        }
    }
}