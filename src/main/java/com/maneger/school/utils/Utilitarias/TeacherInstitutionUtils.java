package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.TeacherInstitution;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.TeacherInstitutionResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherInstitutionUtils {


    public TeacherInstitutionResponse ConvertTeacherInstitutionResponse(TeacherInstitution teacherInstitution) {
        return TeacherInstitutionResponse
                .builder()
                .institutionCnpj(teacherInstitution.getInstitution().getCnpj())
                .teacherCpf(teacherInstitution.getTeacher().getTeacherCpf())
                .startDate(teacherInstitution.getStartDate())
                .uptdateAt(teacherInstitution.getUptdateAt())
                .status(teacherInstitution.isRegistration())
                .build();
    }

    public List<InstitutionResponse> mapStudentInstitutions(List<TeacherInstitution> teacherInstitutions) {
        return teacherInstitutions.stream()
                .filter(TeacherInstitution ::isRegistration)
                .map(si -> {
                    var institution = si.getInstitution();
                    return InstitutionResponse.builder()
                            .nameInstitution(institution.getNameInstitution())
                            .cnpj(institution.getCnpj())
                            .cellPhone(institution.getCellPhone())
                            .email(institution.getEmail())
                            .creatAt(institution.getCreatAt())
                            .uptdateAt(institution.getUptdateAt())
                            .typeOfInstitution(institution.getTypeOfInstitution())
                            .status(institution.isStatus())
                            .build();
                })
                .toList();
    }
}
