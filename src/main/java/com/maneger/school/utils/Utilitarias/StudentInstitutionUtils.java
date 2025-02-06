package com.maneger.school.utils.Utilitarias;


import com.maneger.school.domain.Institution;
import com.maneger.school.domain.Student;
import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class StudentInstitutionUtils {

     public StudentInstitutionResponse ConvertStudentInstitutionResponse(StudentInstitution studentInstitution) {
        return StudentInstitutionResponse
                .builder()
                .institutionCnpj(studentInstitution.getInstitution().getCnpj())
                .studentCpf(studentInstitution.getStudent().getCpf())
                .course(studentInstitution.getCourse())
                .startDate(studentInstitution.getStartDate())
                .uptdateAt(studentInstitution.getUptdateAt())
                .status(studentInstitution.isRegistration())
                .build();
    }
}
