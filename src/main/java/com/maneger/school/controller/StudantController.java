package com.maneger.school.controller;

import com.maneger.school.dto.request.LoginRequest;
import com.maneger.school.dto.request.StudentRequest;
import com.maneger.school.dto.response.LoginAlunoResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studant")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Permitir chamadas apenas do frontend
public class StudantController {

    private final StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse save(@RequestBody StudentRequest request){
        return service.saveStudant(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginAlunoResponse login(@RequestBody LoginRequest request){
        return service.loginStudant(request);
    }

    @PostMapping("/activate/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse ActivateAcessStudant (@PathVariable String cpf){
        return service.ActivateAcessStudant(cpf);
    }

    @PostMapping("/disabled/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse DisabledAcessStudant (@PathVariable String cpf){
        return service.DisabledAcessStudant(cpf);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentResponse>findAll(Pageable pageable){
        return service.findAll(pageable);
    }


}
