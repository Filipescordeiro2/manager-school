package com.maneger.school.controller;

import com.maneger.school.dto.request.SchoolClassRequest;
import com.maneger.school.dto.response.SchoolClassResponse;
import com.maneger.school.service.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schoolClass")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolClassResponse saveClass (@RequestBody SchoolClassRequest request){
        return service.saveClass(request);
    }
}
