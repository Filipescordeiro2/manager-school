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


    public SchoolClassResponse ConvertSchoolClassResponse(SchoolClass schoolClass) {
        return SchoolClassResponse.builder()
                .nameClass(schoolClass.getNameClass())
                .institutionCnpj(schoolClass.getInstitution().getCnpj())
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
