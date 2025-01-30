package com.maneger.school.service;

import com.maneger.school.domain.Institution;
import com.maneger.school.dto.request.InstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository repository;

    public InstitutionResponse saveInstitution(InstitutionRequest request) {
        var institution = new Institution(request);
        var institutionSaved = repository.save(institution);
        return InstitutionResponse
                .builder()
                .nameInstitution(institutionSaved.getNameInstitution())
                .cnpj(institutionSaved.getCnpj())
                .cellPhone(institutionSaved.getCellPhone())
                .email(institutionSaved.getEmail())
                .userAccess(institutionSaved.getUserAccess())
                .typeOfInstitution(institutionSaved.getTypeOfInstitution())
                .creatAt(institutionSaved.getCreatAt())
                .uptdateAt(institutionSaved.getUptdateAt())
                .status(institution.isStatus())
                .build();
    }


}
