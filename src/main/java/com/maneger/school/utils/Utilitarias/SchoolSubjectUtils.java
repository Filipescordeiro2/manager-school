package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.SchoolSubject;
import com.maneger.school.dto.response.SchoolSubjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolSubjectUtils {


    public SchoolSubjectResponse convertToTeacherResponse(SchoolSubject schoolSubject) {
        return SchoolSubjectResponse
                .builder()
                .nameSubject(schoolSubject.getNameSubject())
                .build();
    }
}
