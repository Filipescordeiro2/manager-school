package com.maneger.school.controller;

import com.maneger.school.dto.request.SecretaryRequest;
import com.maneger.school.dto.response.SecretaryResponse;
import com.maneger.school.service.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretary")
@RequiredArgsConstructor
public class SecretaryController {

    private final SecretaryService secretaryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SecretaryResponse saveSecretary(@RequestBody SecretaryRequest request){
        return secretaryService.saveSecretary(request);
    }
}
