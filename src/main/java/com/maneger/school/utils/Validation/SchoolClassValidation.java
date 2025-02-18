package com.maneger.school.utils.Validation;

import com.maneger.school.exception.SchoolClassException;
import com.maneger.school.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolClassValidation {

    private final InstitutionRepository repository;

    public void validateInstitution(String cnpj){
        var institution = repository.findByCnpj(cnpj);
        if(institution.isEmpty()){
            throw new SchoolClassException("Institution not found");
        }
    }
}
