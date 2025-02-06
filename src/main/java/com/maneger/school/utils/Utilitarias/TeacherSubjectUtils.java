package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.*;
import com.maneger.school.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherSubjectUtils {


    public TeacherSubjectResponse convertToTeacherSubjectResponse(TeacherSubject teacherSubject) {
        return TeacherSubjectResponse.builder()
                .message("Link created successfully")
                .institutionCnpj(teacherSubject.getInstitution().getCnpj())
                .teacherCpf(teacherSubject.getTeacher().getTeacherCpf())
                .schoolSubjectName(teacherSubject.getSchoolSubject().getNameSubject())
                .startDate(teacherSubject.getStartDate())
                .uptdateAt(teacherSubject.getUptdateAt())
                .bond(teacherSubject.isBond())
                .build();
    }
}
