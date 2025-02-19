package com.maneger.school.controller;

import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.request.SecretaryRequest;
import com.maneger.school.dto.request.SecretarytInstitutionRequest;
import com.maneger.school.dto.response.*;
import com.maneger.school.service.SecretaryInstitutionService;
import com.maneger.school.service.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretary")
@RequiredArgsConstructor
public class SecretaryController {

    private final SecretaryService secretaryService;
    private final SecretaryInstitutionService secretaryInstitutionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SecretaryResponse saveSecretary(@RequestBody SecretaryRequest request){
        return secretaryService.saveSecretary(request);
    }

    @PostMapping("/link")
    @ResponseStatus(HttpStatus.CREATED)
    public SecretarytInstitutionResponse createLink(@RequestBody SecretarytInstitutionRequest request){
        return secretaryInstitutionService.createLink(request);
    }

    @PostMapping("/activate/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public SecretaryResponse ActivateAcessStudant (@PathVariable String cpf){
        return secretaryService.ActivateAcessStudant(cpf);
    }

    @PostMapping("/disabled/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public SecretaryResponse DisabledAcessStudant (@PathVariable String cpf){
        return secretaryService.DisabledAcessStudant(cpf);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginSecretaryResponse login(@RequestBody LoginRequest request){
        return secretaryService.loginSecretary(request);
    }
    @GetMapping("/institution/{cpf}")
    public List<InstitutionResponse> getStudentInstitutions(@PathVariable String cpf) {
        return secretaryInstitutionService.findAllSecretaryInstitution(cpf);
    }
}
