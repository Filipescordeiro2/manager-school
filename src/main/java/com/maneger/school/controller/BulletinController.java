package com.maneger.school.controller;

import com.maneger.school.dto.request.BulletinRequest;
import com.maneger.school.dto.response.BulletinResponse;
import com.maneger.school.dto.response.BulletinStudentResponse;
import com.maneger.school.service.BulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bulletins")
@RequiredArgsConstructor
public class BulletinController {

    private final BulletinService bulletinService;

    @PostMapping
    public ResponseEntity<BulletinResponse> createBulletin(@RequestBody BulletinRequest request) {
        var bulletin = bulletinService.createBulletin(request);
        return ResponseEntity.ok(bulletin);
    }

    @GetMapping("/student/{cpf}")
    public ResponseEntity<List<BulletinStudentResponse>> getBulletinsByStudentCpf(@PathVariable String cpf) {
        var bulletins = bulletinService.getBulletinsByStudentCpf(cpf);
        return ResponseEntity.ok(bulletins);
    }
}

