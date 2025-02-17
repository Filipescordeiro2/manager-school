package com.maneger.school.service;

import com.maneger.school.domain.Secretary;
import com.maneger.school.domain.Student;
import com.maneger.school.dto.request.SecretaryRequest;
import com.maneger.school.dto.response.SecretaryResponse;
import com.maneger.school.exception.SecretaryException;
import com.maneger.school.repository.SecretaryRepository;
import com.maneger.school.utils.Utilitarias.SecretaryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecretaryService {

    private final SecretaryUtils utils;
    private final SecretaryRepository secretaryRepository;

    public SecretaryResponse saveSecretary(SecretaryRequest request){
        log.info("Start of service [saveSecretary] -- Body request: " + request);
        try{
            var secretary = new Secretary(request);
            var secretarySaved = secretaryRepository.save(secretary);
            var response = utils.convertToSecretaryResponse(secretarySaved);
            log.info("Created Secretary -- response: " + response);
            return response;
        }catch (SecretaryException e){
            log.error("Error in validate Secretary: " + e.getMessage());
            throw new SecretaryException(e.getMessage());

        }catch (Exception e){
            log.error("Error created Secretary: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
