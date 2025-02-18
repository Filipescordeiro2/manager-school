package com.maneger.school.utils.Validation;

import com.maneger.school.repository.SchoolGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolGradeValidation {

    private final SchoolGradeRepository repository;

}
