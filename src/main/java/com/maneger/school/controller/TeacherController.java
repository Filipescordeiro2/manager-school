package com.maneger.school.controller;

import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.request.StudentRequest;
import com.maneger.school.dto.request.TeacherRequest;
import com.maneger.school.dto.request.TeacherSubjectRequest;
import com.maneger.school.dto.response.*;
import com.maneger.school.service.TeacherService;
import com.maneger.school.service.TeacherSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherSubjectService teacherSubjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponse save(@RequestBody TeacherRequest request){
        return teacherService.saveTeacher(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginTeacherResponse login(@RequestBody LoginRequest request){
        return teacherService.loginteacher(request);
    }

    @PostMapping("/activate/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherResponse ActivateAcessStudant (@PathVariable String cpf){
        return teacherService.ActivateAcessteacher(cpf);
    }

    @PostMapping("/disabled/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherResponse DisabledAcessStudant (@PathVariable String cpf){
        return teacherService.DisabledAcessteacher(cpf);
    }

    @PostMapping("/link")
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherSubjectResponse createLink(@RequestBody TeacherSubjectRequest request){
        return teacherSubjectService.createLink(request);
    }
}
