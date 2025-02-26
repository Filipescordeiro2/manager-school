package com.maneger.school.service;

import com.maneger.school.domain.Institution;
import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.utils.Utilitarias.InstitutionUtils;
import com.maneger.school.utils.Validation.InstitutionValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionUtils institutionUtils;
    private final InstitutionRepository institutionRepository;
    private final InstitutionValidation validation;
    private final StudentInstitutionRepository studentInstitutionRepository;

    public InstitutionResponse saveInstitution(InstitutionRequest request) {
        log.info("Start of service [saveInstitution] -- Body request: " + request);
        try {
            var institution = new Institution(request);
            validation.validDuplicate(request);
            var institutionSaved = institutionRepository.save(institution);
            var response = institutionUtils.convertToInstitutionResponse(institutionSaved);
            log.info("Created Institution -- response: " + response);
            return response;
        } catch (Exception e) {
            log.error("Error created Institution: " + e.getMessage());
            throw new InstitutionException("Error created Institution: " + e.getMessage());
        }
    }

    public InstitutionResponse disableAccessInstitution(String cnpj) {
        try {
            var institution = institutionUtils.findInstitutionCnpj(cnpj);
            var linksInstitution = studentInstitutionRepository.findByInstitution(institution);
            validation.validStatusForDisable(institution.isStatus());
            if (institution.isStatus()) {
                institution.setStatus(false);
                log.info("Disabled Student Access");
                linksInstitution.forEach(links -> {
                    links.setRegistration(false);
                    studentInstitutionRepository.save(links);
                });
            }
            institutionRepository.save(institution);
            return institutionUtils.convertToInstitutionResponse(institution);
        } catch (Exception e) {
            throw new InstitutionException("Error in Disabled for Institution: " + e.getMessage());
        }
    }

    public InstitutionResponse findByNameInstitution(String nameInstitution) {
        try {
            log.info("Start of service [findByNameInstitution] -- Name: " + nameInstitution);
            var institution = institutionUtils.findInstitutionByName(nameInstitution);
            var response = institutionUtils.convertToInstitutionResponse(institution);
            log.info("Found Institution -- response: " + response);
            return response;
        } catch (Exception e) {
            throw new InstitutionException("Error: " + e.getMessage());
        }
    }


}