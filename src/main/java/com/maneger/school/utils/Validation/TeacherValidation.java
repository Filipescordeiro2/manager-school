package com.maneger.school.utils.Validation;

import com.maneger.school.exception.TeacherException;
import com.maneger.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class TeacherValidation {

    private final TeacherRepository repository;

    public void validateDuplicateTeacher(String cpf, String userAccess, String email) {
        var studentByCpf = repository.findByTeacherCpf(cpf);
        if (studentByCpf.isPresent()) {
            throw new TeacherException("There is already a registration for this CPF");
        }

        var studentByUserAccess = repository.findByUserAccess(userAccess);
        if (studentByUserAccess.isPresent()) {
            throw new TeacherException("There is already a registration for this userAccess");
        }

        var studentByEmail = repository.findByEmail(email);
        if (studentByEmail.isPresent()) {
            throw new TeacherException("There is already a registration for this Email");
        }
    }

    public void validatePassword(String password) {
        if (password.length() < 8) {
            throw new TeacherException("Password must be at least 8 characters long");
        }
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            throw new TeacherException("Password must contain at least one uppercase letter");
        }
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            throw new TeacherException("Password must contain at least one lowercase letter");
        }
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            throw new TeacherException("Password must contain at least one digit");
        }
        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) {
            throw new TeacherException("Password must contain at least one special character");
        }
    }
}
