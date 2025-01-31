package com.maneger.school.service;

import com.maneger.school.domain.Institution;
import com.maneger.school.domain.Student;
import com.maneger.school.dto.request.LoginAlunoRequest;
import com.maneger.school.dto.request.StudentRequest;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.dto.response.LoginAlunoResponse;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.exception.LoginInstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import com.maneger.school.util.StudantValidation;
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
    private final InstitutionRepository institutionRepository;
    private final StudentInstitutionRepository studentInstitutionRepository;


    public StudentResponse saveStudant(StudentRequest request) {
        log.info("Start of service [saveStudant] -- Body request: " + request);
        try {
            validation.validateDuplicateStudent(request.getCpf(), request.getUserAccess(), request.getEmail());
            validation.validatePassword(request.getPasswordAccess());
            var student = new Student(request);
            var studentSaved = studentRepository.save(student);
            var response = convertToStudentResponse(studentSaved);
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

    public Page<StudentResponse>findAll(Pageable pageable){
        var students = studentRepository.findByStatusTrue(pageable);
        return students.map(this::convertToStudentResponse);
    }

    public LoginAlunoResponse loginStudant(LoginAlunoRequest request) {
        log.info("Start of service [loginStudant] -- Body request: " + request);
        try {
            var studentEntity = studentRepository.findByUserAccessAndPasswordAccess(request.getUserAccess(), request.getPasswordAccess())
                    .orElseThrow(() -> new LoginInstitutionException("User or Password is incorrect"));
            if (!studentEntity.isStatus()) {
                throw new LoginInstitutionException("The user is deactivated -- Reason: "+studentEntity.getReasonsForBlockingDescription()+" -- Contact the Admin");
            }
            studentEntity.setLoginAttempts(0); // Reset login attempts on successful login
            studentRepository.save(studentEntity); // Save the updated student
            var studant = convertToStudentResponse(studentEntity);
            var response = LoginAlunoResponse
                    .builder()
                    .message("Login successfully")
                    .alunoResponse(studant)
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

    public StudentResponse ActivateAcessStudant(String cpf){
        var studant = studentRepository.findByCpf(cpf)
                .orElseThrow(()->new LoginInstitutionException("User not found"));
        if (!studant.isStatus()){
            studant.setStatus(true);
            studant.setReasonsForBlocking(null);
            studant.setReasonsForBlockingDescription(null);
            studant.setLoginAttempts(0);
            log.info("Access enabled");
        }
        studentRepository.save(studant);
        var response = convertToStudentResponse(studant);
        return response;
    }

    public StudentResponse DisabledAcessStudant(String cpf) {
        var studant = studentRepository.findByCpf(cpf)
                .orElseThrow(() -> new LoginInstitutionException("User not found"));
        var linkstudants = studentInstitutionRepository.findByStudent(studant);
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
        var response = convertToStudentResponse(studant);
        return response;
    }

    private Institution searchInstitution(String id){
        return institutionRepository.findById(id)
                .orElseThrow(() -> new StudantException("Institution not found"));
    }

    private void handleFailedLoginAttempt(LoginAlunoRequest request) {
        var studant = studentRepository.findByUserAccess(request.getUserAccess())
                .orElseThrow(() -> new LoginInstitutionException("User not found"));
        int attempts = studant.getLoginAttempts() + 1;
        if (attempts >= 3) {
            studant.setStatus(false);
            studant.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_1);
            studant.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_1.getDescription());
            log.warn(studant.getReasonsForBlockingDescription());
        }
        studant.setLoginAttempts(attempts);
        studentRepository.save(studant);
    }

    private StudentResponse convertToStudentResponse(Student studentEntity) {
        return StudentResponse
                .builder()
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