package com.maneger.school.controller;

import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.service.StudentInstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class StudentInstitutionController {

    private final StudentInstitutionService studentInstitutionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentInstitutionResponse createLink(@RequestBody StudentInstitutionRequest request){
        return studentInstitutionService.createLink(request);
    }

}
