package com.maneger.school.controller;

import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.request.StudentRequest;
import com.maneger.school.dto.request.TeacherRequest;
import com.maneger.school.dto.response.LoginAlunoResponse;
import com.maneger.school.dto.response.LoginTeacherResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.dto.response.TeacherResponse;
import com.maneger.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

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
}
