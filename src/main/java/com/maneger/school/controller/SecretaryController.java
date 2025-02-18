package com.maneger.school.controller;

import com.maneger.school.dto.request.SecretaryRequest;
import com.maneger.school.dto.request.SecretarytInstitutionRequest;
import com.maneger.school.dto.response.SecretaryResponse;
import com.maneger.school.dto.response.SecretarytInstitutionResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.service.SecretaryInstitutionService;
import com.maneger.school.service.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


}
