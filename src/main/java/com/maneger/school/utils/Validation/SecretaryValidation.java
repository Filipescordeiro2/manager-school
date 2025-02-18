package com.maneger.school.utils.Validation;

import com.maneger.school.domain.Secretary;
import com.maneger.school.exception.SecretaryException;
import com.maneger.school.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class SecretaryValidation {
    
    private final SecretaryRepository repository;

    public void validatesecretary(Secretary secretary) {
        validateDuplicatesecretary(secretary.getCpf(), secretary.getUserAccess(), secretary.getEmail());
        validatePassword(secretary.getPasswordAccess());
    }

    public void validStatusForDisanble(boolean status){
        if (!status){
            throw new SecretaryException("secretary is already deactivated");
        }
    }

    private void validateDuplicatesecretary(String cpf, String userAccess, String email) {
        var secretaryByCpf = repository.findByCpf(cpf);
        if (secretaryByCpf.isPresent()) {
            throw new SecretaryException("There is already a registration for this CPF");
        }

        var secretaryByUserAccess = repository.findByUserAccess(userAccess);
        if (secretaryByUserAccess.isPresent()) {
            throw new SecretaryException("There is already a registration for this userAccess");
        }

        var secretaryByEmail = repository.findByEmail(email);
        if (secretaryByEmail.isPresent()) {
            throw new SecretaryException("There is already a registration for this Email");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new SecretaryException("Password must be at least 8 characters long");
        }
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            throw new SecretaryException("Password must contain at least one uppercase letter");
        }
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            throw new SecretaryException("Password must contain at least one lowercase letter");
        }
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            throw new SecretaryException("Password must contain at least one digit");
        }
        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) {
            throw new SecretaryException("Password must contain at least one special character");
        }
    }
    
}
