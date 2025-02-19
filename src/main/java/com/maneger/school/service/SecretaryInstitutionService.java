package com.maneger.school.service;

import com.maneger.school.domain.SecretaryInstitution;
import com.maneger.school.dto.request.SecretarytInstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.SecretarytInstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.SecretaryException;
import com.maneger.school.exception.SecretaryInstitutionException;
import com.maneger.school.exception.StudentInstitutionException;
import com.maneger.school.repository.SecretaryInstitutionRepository;
import com.maneger.school.repository.SecretaryRepository;
import com.maneger.school.utils.Utilitarias.SecretaryInstitutionUtils;
import com.maneger.school.utils.Validation.SecretaryInstitutionValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecretaryInstitutionService {

    private final SecretaryInstitutionRepository secretaryInstitutionRepository;
    private final SecretaryRepository secretaryRepository;
    private final SecretaryInstitutionUtils secretaryInstitutionUtils;
    private final SecretaryInstitutionValidation validation;

    public SecretarytInstitutionResponse createLink(SecretarytInstitutionRequest request){
        log.info("Initializing [createLink] with request: {}", request);
        try{
            validation.validSecretaryInstitution(request);
            var link = new SecretaryInstitution(request);
            var linkCreated = secretaryInstitutionRepository.save(link);
            var response  = secretaryInstitutionUtils.ConvertSecretaryInstitutionResponse(linkCreated);
            log.info("Finishing [createLink] with response: {}", response);
            return response;
        }catch (Exception e){
            log.error("ERROR IN SERVICE [createLink] WITH ERROR: {}", e.getMessage());
            throw new SecretaryInstitutionException("Error for link: "+e.getMessage());
        }
    }

    public List<InstitutionResponse> findAllSecretaryInstitution(String cpf) {
        log.info("Initializing [findAllSecretaryInstitution] with cpf: {}", cpf);
        try {
            var secretary = secretaryRepository.findByCpf(cpf)
                    .orElseThrow(() -> new SecretaryException("Secretary not found"));
            var secretaryInstitutions = secretaryInstitutionRepository.findBySecretary(secretary);
            if (secretaryInstitutions.isEmpty()) {
                throw new InstitutionException("No institutions found for this secretary");
            }
            var response = secretaryInstitutionUtils.mapSecretaryInstitutions(secretaryInstitutions);
            log.info("Finishing [createLink] with response: {}", response);
            return response;
        } catch (Exception e) {
            log.error("ERROR IN SERVICE [findAllSecretaryInstitution] WITH ERROR: {}", e.getMessage());
            throw new SecretaryInstitutionException("Error finding institutions: " + e.getMessage());
        }
    }

}
