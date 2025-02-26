package com.maneger.school.utils.Validation;

import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionValidation {

    private final InstitutionRepository repository;

    public void validDuplicate(InstitutionRequest request) throws InstantiationException {
        validateDuplicateCnpj(request.getCnpj());
        validateDuplicateInstitution(request.getNameInstitution());
    }

    public void validStatusForDisable(boolean status){
        if (!status){
            throw new InstitutionException("Institution is already deactivated");
        }
    }

    private void validateDuplicateInstitution(String nameInstitution) throws InstantiationException {
        var instituition = repository.findByNameInstitutionIgnoreCase(nameInstitution);
        if (instituition.isPresent()) {
            throw new InstantiationException("There is already a registration for this nameInstitution");
        }
    }
    private void validateDuplicateCnpj(String cnpj) throws InstantiationException {
        var instituition = repository.findById(cnpj);
        if (instituition.isPresent()){
            throw new InstantiationException("There is already a registration for this cnpj");
        }
    }

}
