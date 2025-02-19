package com.maneger.school.service;

import com.maneger.school.domain.Secretary;
import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.request.SecretaryRequest;
import com.maneger.school.dto.response.LoginSecretaryResponse;
import com.maneger.school.dto.response.SecretaryResponse;
import com.maneger.school.enums.ReasonsForBlocking;
import com.maneger.school.exception.LoginException;
import com.maneger.school.exception.SecretaryException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.SecretaryInstitutionRepository;
import com.maneger.school.repository.SecretaryRepository;
import com.maneger.school.utils.Utilitarias.SecretaryUtils;
import com.maneger.school.utils.Validation.SecretaryValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecretaryService {

    private final SecretaryUtils utils;
    private final SecretaryRepository secretaryRepository;
    private final SecretaryValidation validation;
    private final SecretaryInstitutionRepository secretaryInstitutionRepository;

    public SecretaryResponse saveSecretary(SecretaryRequest request){
        log.info("Start of service [saveSecretary] -- Body request: " + request);
        try{
            var secretary = new Secretary(request);
            validation.validatesecretary(secretary);
            var secretarySaved = secretaryRepository.save(secretary);
            var response = utils.convertToSecretaryResponse(secretarySaved);
            log.info("Created Secretary -- response: " + response);
            return response;
        }catch (SecretaryException e){
            log.error("Error in validate Secretary: " + e.getMessage());
            throw new SecretaryException(e.getMessage());
        }catch (Exception e){
            log.error("Error created Secretary: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public LoginSecretaryResponse loginSecretary(LoginRequest request) {
        log.info("Start of service [loginStudant] -- Body request: " + request);
        try {
            var response = utils.authenticateAndBuildResponse(request);
            log.info("Login successful -- response: " + response);
            return response;
        } catch (LoginException e) {
            log.error("Login failed: " + e.getMessage());
            utils.handleFailedLoginAttempt(request);
            throw new LoginException(e.getMessage());
        } catch (Exception e) {
            log.error("Error during login: " + e.getMessage());
            throw new RuntimeException("Error Login: " + e.getMessage());
        }
    }


    public SecretaryResponse ActivateAcessStudant(String cpf) {
        try{
            var secretary = utils.findStudentByCpf(cpf);
            if (!secretary.isStatus()) {
                secretary.setStatus(true);
                secretary.setReasonsForBlocking(null);
                secretary.setReasonsForBlockingDescription(null);
                secretary.setLoginAttempts(0);
                log.info("Access enabled");
            }
            secretaryRepository.save(secretary);
            return utils.convertToSecretaryResponse(secretary);
        }catch (Exception e){
            throw new StudantException("Error in activate Secretary: "+e.getMessage());
        }
    }

    public SecretaryResponse DisabledAcessStudant(String cpf) {
        try{
            var secretary = utils.findStudentByCpf(cpf);
            var linkstudants = secretaryInstitutionRepository.findBySecretary(secretary);
            validation.validStatusForDisanble(secretary.isStatus());
            if (secretary.isStatus()) {
                secretary.setStatus(false);
                secretary.setReasonsForBlocking(ReasonsForBlocking.BLOCKED_2);
                secretary.setReasonsForBlockingDescription(ReasonsForBlocking.BLOCKED_2.getDescription());
                secretary.setLoginAttempts(0);
                log.info("Disabled Student Access");
                linkstudants.forEach(linkstudant -> {
                    linkstudant.setRegistration(false);
                    secretaryInstitutionRepository.save(linkstudant);
                });
            }
            secretaryRepository.save(secretary);
            return utils.convertToSecretaryResponse(secretary);
        }catch (Exception e){
            throw new StudantException("Error in Disabled for Secretary: "+e.getMessage());
        }
    }

}
