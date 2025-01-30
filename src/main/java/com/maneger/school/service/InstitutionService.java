package com.maneger.school.service;

import com.maneger.school.domain.Institution;
import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository repository;

    public InstitutionResponse saveInstitution(InstitutionRequest request) {
        log.info("Start of service [saveInstitution] -- Body request: " + request);
        try {
            var institution = new Institution(request);
            var institutionSaved = repository.save(institution);
            var response = InstitutionResponse
                    .builder()
                    .id(institutionSaved.getId())
                    .nameInstitution(institutionSaved.getNameInstitution())
                    .cnpj(institutionSaved.getCnpj())
                    .cellPhone(institutionSaved.getCellPhone())
                    .email(institutionSaved.getEmail())
                    .typeOfInstitution(institutionSaved.getTypeOfInstitution())
                    .creatAt(institutionSaved.getCreatAt())
                    .uptdateAt(institutionSaved.getUptdateAt())
                    .status(institution.isStatus())
                    .build();
            log.info("Created Institution -- response: " + response);
            return response;
        } catch (Exception e) {
            log.error("Error created Institution: " + e.getMessage());
            throw new InstitutionException("Error created Institution");
        }
    }

    public InstitutionResponse findByNameInstitution(String nameInstitution) {
        log.info("Start of service [findByNameInstitution] -- Name: " + nameInstitution);
        var response = searchInstitution(nameInstitution);
        log.info("Found Institution -- response: " + response);
        return response;
    }

    private InstitutionResponse searchInstitution(String nameInstitution) {
        log.info("Searching for Institution by name: " + nameInstitution);
        var institution = repository.findByNameInstitution(nameInstitution)
                .orElseThrow(() -> new InstitutionException("Error when searching " + nameInstitution));
        var response = InstitutionResponse
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
        log.info("Found Institution -- response: " + response);
        return response;
    }


}