package com.maneger.school.exception;

import com.maneger.school.dto.response.ResponseExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ResponseExecption> handleLoginInstitutionException(LoginException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InstitutionException.class)
    public ResponseEntity<ResponseExecption> handleInstitutionException(InstitutionException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SecretaryInstitutionException.class)
    public ResponseEntity<ResponseExecption> handleSecretaryInstitutionExceptio(SecretaryInstitutionException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SchoolClassException.class)
    public ResponseEntity<ResponseExecption> handleSchoolClassException(SchoolClassException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SchoolGradeException.class)
    public ResponseEntity<ResponseExecption> handleSchoolGradeException(SchoolGradeException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentInstitutionException.class)
    public ResponseEntity<ResponseExecption> handleStudentInstitutionException(StudentInstitutionException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SchoolSubjectException.class)
    public ResponseEntity<ResponseExecption> handleSchoolSubjectException(SchoolSubjectException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherException.class)
    public ResponseEntity<ResponseExecption> handleTeacherException(TeacherException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudantException.class)
    public ResponseEntity<ResponseExecption> handleStudantException(StudantException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SecretaryException.class)
    public ResponseEntity<ResponseExecption> handleSecretaryException(SecretaryException ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseExecption> handleGlobalException(Exception ex, WebRequest request) {
        ResponseExecption response = new ResponseExecption(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}