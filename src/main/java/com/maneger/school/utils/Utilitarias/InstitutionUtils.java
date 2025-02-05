package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Institution;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.utils.Validation.InstitutionValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InstitutionUtils {

    private final InstitutionRepository repository;

    public Institution findInstitutionByName(String nameInstitution) {
        return repository.findByNameInstitutionIgnoreCase(nameInstitution)
                .orElseThrow(() -> new InstitutionException("institution not found: " + nameInstitution));
    }

    public Institution findInstitutionCnpj(String cnpj) {
        return repository.findById(cnpj)
                .orElseThrow(() -> new InstitutionException("institution not found: " + cnpj));
    }

    public InstitutionResponse convertToInstitutionResponse(Institution institution) {
        log.info("Institution before conversion: " + institution); // Verificar se creatAt e uptdateAt estão preenchidos
        return InstitutionResponse.builder()
                .nameInstitution(institution.getNameInstitution())
                .cnpj(institution.getCnpj())
                .cellPhone(institution.getCellPhone())
                .email(institution.getEmail())
                .typeOfInstitution(institution.getTypeOfInstitution())
                .creatAt(institution.getCreatAt())
                .uptdateAt(institution.getUptdateAt())
                .status(institution.isStatus())
                .build();
    }

}