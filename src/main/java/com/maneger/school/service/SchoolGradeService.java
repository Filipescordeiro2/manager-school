package com.maneger.school.service;

import com.maneger.school.domain.SchoolGrade;
import com.maneger.school.dto.request.SchoolGradeRequest;
import com.maneger.school.dto.response.GradeResponse;
import com.maneger.school.dto.response.SchoolGradeResponse;
import com.maneger.school.exception.SchoolGradeException;
import com.maneger.school.repository.SchoolClassRepository;
import com.maneger.school.repository.SchoolGradeRepository;
import com.maneger.school.utils.Utilitarias.SchoolGradeUtils;
import com.maneger.school.utils.Validation.SchoolGradeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolGradeService {

    private final SchoolGradeUtils schoolGradeUtils;
    private final SchoolGradeRepository repository;
    private final SchoolGradeValidation schoolGradeValidation;

    @Transactional
    public SchoolGradeResponse save(SchoolGradeRequest request) {
        try {
            var grade = new SchoolGrade(request);
            schoolGradeValidation.validDays(request);
            var gradeSaved = schoolGradeUtils.saveSchoolGrade(grade);
            return schoolGradeUtils.convertToSchoolGradeResponse(gradeSaved);
        }catch (SchoolGradeException e){
            throw new SchoolGradeException(e.getMessage());
        }catch (Exception e){
            throw new SchoolGradeException("Error saved School Grade: "+e.getMessage());
        }
    }



    @Transactional(readOnly = true)
    public List<GradeResponse> findGradeDetails(String className) {
        var schoolGrades = repository.findSchoolGradeDetails(className);
        if (schoolGrades.isEmpty()) {
            throw new SchoolGradeException("No grade found for this class");
        }
        return schoolGrades.stream()
                .map(schoolGradeUtils::convertToGradeResponse)
                .toList();
    }
}