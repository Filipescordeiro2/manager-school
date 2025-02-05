package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.SchoolGrade;
import com.maneger.school.dto.response.SchoolGradeResponse;
import com.maneger.school.repository.SchoolGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolGradeUtils {

    private final SchoolGradeRepository repository;

    public SchoolGrade saveSchoolGrade(SchoolGrade schoolGrade) {
        return repository.save(schoolGrade);
    }

    public SchoolGradeResponse convertToSchoolGradeResponse(SchoolGrade schoolGrade) {
        return SchoolGradeResponse.builder()
                .schoolClassName(schoolGrade.getSchoolClass().getNameClass())
                .teacherSubjecName(schoolGrade.getTeacherSubject().getId().toString())
                .createAt(schoolGrade.getCreateAt())
                .updateAt(schoolGrade.getUpdateAt())
                .status(schoolGrade.isStatus())
                .build();
    }
}