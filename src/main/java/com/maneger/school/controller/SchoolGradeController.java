package com.maneger.school.controller;

import com.maneger.school.domain.SchoolGrade;
import com.maneger.school.dto.request.SchoolGradeRequest;
import com.maneger.school.dto.response.GradeResponse;
import com.maneger.school.dto.response.SchoolGradeResponse;
import com.maneger.school.service.SchoolGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/grades")
    public ResponseEntity<List<GradeResponse>> getGradeDetails(@RequestParam String className) {
        List<GradeResponse> gradeDetails = service.findGradeDetails(className);
        return ResponseEntity.ok(gradeDetails);
    }
}
