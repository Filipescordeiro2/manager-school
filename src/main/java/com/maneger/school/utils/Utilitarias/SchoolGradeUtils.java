package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.SchoolGrade;
import com.maneger.school.dto.response.GradeResponse;
import com.maneger.school.dto.response.SchoolGradeResponse;
import com.maneger.school.repository.SchoolGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    public GradeResponse convertToGradeResponse(GradeResponse gradeResponse) {
        return GradeResponse.builder()
                .gradeId(gradeResponse.getGradeId())
                .nameInstitution(gradeResponse.getNameInstitution())
                .schoolSubjectName(gradeResponse.getSchoolSubjectName())
                .schoolClassName(gradeResponse.getSchoolClassName())
                .nameTeacher(gradeResponse.getNameTeacher())
                .gradeOfSchedules(gradeResponse.getGradeOfSchedules())
                .startTime(gradeResponse.getStartTime())
                .endTime(gradeResponse.getEndTime())
                .build();
    }


}