package com.maneger.school.util;

import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class StudantValidation {

    private final StudentRepository repository;

    public void validateDuplicateStudent(String cpf, String userAccess, String email) {
        var studentByCpf = repository.findByCpf(cpf);
        if (studentByCpf.isPresent()) {
            throw new StudantException("There is already a registration for this CPF");
        }

        var studentByUserAccess = repository.findByUserAccess(userAccess);
        if (studentByUserAccess.isPresent()) {
            throw new StudantException("There is already a registration for this userAccess");
        }

        var studentByEmail = repository.findByEmail(email);
        if (studentByEmail.isPresent()) {
            throw new StudantException("There is already a registration for this Email");
        }
    }

    public void validatePassword(String password) {
        if (password.length() < 8) {
            throw new StudantException("Password must be at least 8 characters long");
        }
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            throw new StudantException("Password must contain at least one uppercase letter");
        }
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            throw new StudantException("Password must contain at least one lowercase letter");
        }
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            throw new StudantException("Password must contain at least one digit");
        }
        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) {
            throw new StudantException("Password must contain at least one special character");
        }
    }
}