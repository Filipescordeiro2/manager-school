package com.maneger.school.service;

import com.maneger.school.domain.Institution;
import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.util.InstitutionValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository repository;
    private final InstitutionValidation validation;

    public InstitutionResponse saveInstitution(InstitutionRequest request) {
        log.info("Start of service [saveInstitution] -- Body request: " + request);
        try {
            var institution = new Institution(request);
            validation.validateDuplicateInstitution(request.getNameInstitution());
            var institutionSaved = repository.save(institution);
            var response = convertToInstitutionResponse(institutionSaved);
            log.info("Created Institution -- response: " + response);
            return response;
        } catch (Exception e) {
            log.error("Error created Institution: " + e.getMessage());
            throw new InstitutionException("Error created Institution");
        }
    }

    public InstitutionResponse findByNameInstitution(String nameInstitution) {
        log.info("Start of service [findByNameInstitution] -- Name: " + nameInstitution);
        return searchInstitutionFornameInstitution(nameInstitution);
    }

    private InstitutionResponse searchInstitutionFornameInstitution(String nameInstitution) {
        var institution = repository.findByNameInstitution(nameInstitution)
                .orElseThrow(() -> new InstitutionException("Error when searching " + nameInstitution));
        var response = convertToInstitutionResponse(institution);
        log.info("Found Institution -- response: " + response);
        return response;
    }

    private InstitutionResponse convertToInstitutionResponse(Institution institution) {
        return InstitutionResponse
                .builder()
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