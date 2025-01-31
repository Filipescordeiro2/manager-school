package com.maneger.school.util;

import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionValidation {

    private final InstitutionRepository repository;

    public void validateDuplicateInstitution(String nameInstitution) {
        var instituition = repository.findByNameInstitution(nameInstitution);
        if (instituition.isPresent()) {
            throw new StudantException("There is already a registration for this nameInstitution");
        }

    }
}
