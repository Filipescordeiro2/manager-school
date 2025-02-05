package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Institution;
import com.maneger.school.domain.SchoolClass;

import com.maneger.school.dto.response.*;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolClassUtils {

    private final SchoolClassRepository repository;
    private final InstitutionRepository institutionRepository;


    public SchoolClassResponse convertTeacherSubjectResponse(SchoolClass schoolClass) {
        var institution = findInstitutionById(schoolClass.getInstitution().getCnpj());
        var responseInstitution = buildInstitutionResponse(institution);
        return buildSchoolClassResponse(schoolClass,responseInstitution);
    }

    private Institution findInstitutionById(String institutionId) {
        return institutionRepository.findById(institutionId)
                .orElseThrow(() -> new InstitutionException("Institution Not Found"));
    }
    private InstitutionResponse buildInstitutionResponse(Institution institution) {
        return InstitutionResponse.builder()
                .nameInstitution(institution.getNameInstitution())
                .cnpj(institution.getCnpj())
                .cellPhone(institution.getCellPhone())
                .email(institution.getEmail())
                .typeOfInstitution(institution.getTypeOfInstitution())
                .creatAt(institution.getCreatAt())
                .uptdateAt(institution.getUptdateAt())
                .status(institution.isStatus())
                .build();
    }

    private SchoolClassResponse buildSchoolClassResponse(SchoolClass schoolClass,
                                                                   InstitutionResponse responseInstitution) {
        return SchoolClassResponse.builder()
                .nameClass(schoolClass.getNameClass())
                .institutionResponse(responseInstitution)
                .createAt(schoolClass.getCreateAt())
                .updateAt(schoolClass.getUpdateAt())
                .dradeOfSchedules(schoolClass.getDradeOfSchedules())
                .endDate(schoolClass.getEndTime())
                .startDate(schoolClass.getStartTime())
                .period(schoolClass.getPeriod())
                .status(schoolClass.isStatus())
                .build();
    }
}
