package com.maneger.school.controller;

import com.maneger.school.dto.request.SchoolSubjectRequest;
import com.maneger.school.dto.response.SchoolSubjectResponse;
import com.maneger.school.service.SchoolSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SchoolSubject")
@RequiredArgsConstructor
public class SchoolSubjectController {

    private final SchoolSubjectService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolSubjectResponse save (@RequestBody SchoolSubjectRequest request){
        return service.save(request);
    }
}
