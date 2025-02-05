package com.maneger.school.controller;

import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.request.TeacherSubjectRequest;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.dto.response.TeacherSubjectResponse;
import com.maneger.school.service.TeacherSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link2")
@RequiredArgsConstructor
public class TeacherSubjectController {

    private final TeacherSubjectService teacherSubjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherSubjectResponse createLink(@RequestBody TeacherSubjectRequest request){
        return teacherSubjectService.createLink(request);
    }
}
