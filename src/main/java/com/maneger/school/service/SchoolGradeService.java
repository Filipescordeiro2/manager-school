package com.maneger.school.service;

import com.maneger.school.domain.SchoolGrade;
import com.maneger.school.dto.request.SchoolGradeRequest;
import com.maneger.school.dto.response.SchoolGradeResponse;
import com.maneger.school.exception.SchoolGradeException;
import com.maneger.school.utils.Utilitarias.SchoolGradeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolGradeService {

    private final SchoolGradeUtils schoolGradeUtils;

    @Transactional
    public SchoolGradeResponse save(SchoolGradeRequest request) {
        try {
            var grade = new SchoolGrade(request);
            var gradeSaved = schoolGradeUtils.saveSchoolGrade(grade);
            return schoolGradeUtils.convertToSchoolGradeResponse(gradeSaved);
        }catch (Exception e){
            throw new SchoolGradeException("Error saved School Grade: "+e.getMessage());
        }
    }
}