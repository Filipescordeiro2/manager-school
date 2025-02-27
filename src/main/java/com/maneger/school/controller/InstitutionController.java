package com.maneger.school.controller;

import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institution")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionResponse save(@RequestBody InstitutionRequest request){
        return institutionService.saveInstitution(request);
    }

    @GetMapping("/{nameInstitution}")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionResponse findByNameInstitution(@PathVariable String nameInstitution){
        return institutionService.findByNameInstitution(nameInstitution);
    }

    @PostMapping("/disabled/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionResponse DisabledAcessIntitution(@PathVariable String cnpj){
        return institutionService.disableAccessInstitution(cnpj);
    }

}
