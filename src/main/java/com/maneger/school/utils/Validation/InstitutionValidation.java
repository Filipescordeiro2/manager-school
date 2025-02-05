package com.maneger.school.utils.Validation;

import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionValidation {

    private final InstitutionRepository repository;

    public void validateDuplicateInstitution(String nameInstitution) throws InstantiationException {
        var instituition = repository.findByNameInstitutionIgnoreCase(nameInstitution);
        if (instituition.isPresent()) {
            throw new InstantiationException("There is already a registration for this nameInstitution");
        }
    }
}
