package com.maneger.school.controller;

import com.maneger.school.dto.request.SchoolClassRequest;
import com.maneger.school.dto.request.StudentClassRequest;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.SchoolClassResponse;
import com.maneger.school.dto.response.StudentClassResponse;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.service.SchoolClassService;
import com.maneger.school.service.StudentClasssService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schoolClass")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassService service;
    private final StudentClasssService studentClasssService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolClassResponse saveClass (@RequestBody SchoolClassRequest request){
        return service.saveClass(request);
    }

    @PostMapping("/link")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentClassResponse createLink(@RequestBody StudentClassRequest request){
        return studentClasssService.createLink(request);
    }

    @GetMapping("/institution/{cpf}")
    public List<SchoolClassResponse> getStudentInstitutions(@PathVariable String cpf) {
        return studentClasssService.findAllStudentClass(cpf);
    }
}
