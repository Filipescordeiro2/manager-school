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
                .id(teacherSubject.getId())
                .institutionCnpj(teacherSubject.getInstitution().getCnpj())
                .teacherCpf(teacherSubject.getTeacher().getTeacherCpf())
                .schoolSubjectName(teacherSubject.getSchoolSubject().getNameSubject())
                .startDate(teacherSubject.getCreateAt())
                .uptdateAt(teacherSubject.getUptdateAt())
                .bond(teacherSubject.isBond())
                .build();
    }

    public TeacherSubjectDetailsResponse convertToTeacherSubjectDetaisResponse(TeacherSubjectDetailsResponse teacherSubjectDetailsResponse) {
        return TeacherSubjectDetailsResponse.builder()
                .nameInstitution(teacherSubjectDetailsResponse.getNameInstitution())
                .nameTeacher(teacherSubjectDetailsResponse.getNameTeacher())
                .schoolSubjectName(teacherSubjectDetailsResponse.getSchoolSubjectName())
                .createAt(teacherSubjectDetailsResponse.getCreateAt())
                .uptdateAt(teacherSubjectDetailsResponse.getUptdateAt())
                .bond(teacherSubjectDetailsResponse.isBond())
                .build();
    }
}
