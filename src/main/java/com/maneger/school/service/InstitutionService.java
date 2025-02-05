package com.maneger.school.service;

import com.maneger.school.domain.Institution;
import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.utils.Utilitarias.InstitutionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionUtils institutionUtils;

    public InstitutionResponse saveInstitution(InstitutionRequest request) {
        log.info("Start of service [saveInstitution] -- Body request: " + request);
        try {
            var institution = new Institution(request);
            institutionUtils.validateAndSaveInstitution(institution);
            institution = institutionUtils.findInstitutionByName(institution.getNameInstitution());
            var response = institutionUtils.convertToInstitutionResponse(institution);
            log.info("Created Institution -- response: " + response);
            return response;
        } catch (Exception e) {
            log.error("Error created Institution: " + e.getMessage());
            throw new InstitutionException("Error created Institution: "+e.getMessage());
        }
    }


    public InstitutionResponse findByNameInstitution(String nameInstitution) {
        try{
            log.info("Start of service [findByNameInstitution] -- Name: " + nameInstitution);
            var institution = institutionUtils.findInstitutionByName(nameInstitution);
            var response = institutionUtils.convertToInstitutionResponse(institution);
            log.info("Found Institution -- response: " + response);
            return response;
        }catch (Exception e){
            throw new InstitutionException("Error: "+e.getMessage());
        }
    }
}