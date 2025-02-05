package com.maneger.school.controller;

import com.maneger.school.dto.request.SchoolGradeRequest;
import com.maneger.school.dto.response.SchoolGradeResponse;
import com.maneger.school.service.SchoolGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schoolGrade")
@RequiredArgsConstructor
public class SchoolGradeController {

    private final SchoolGradeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolGradeResponse save (@RequestBody SchoolGradeRequest request){
        return service.save(request);
    }
}
