package com.maneger.school.utils.Utilitarias;


import com.maneger.school.domain.SecretaryInstitution;
import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.SecretarytInstitutionResponse;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecretaryInstitutionUtils {

    public SecretarytInstitutionResponse ConvertSecretaryInstitutionResponse(SecretaryInstitution secretaryInstitution) {
        return SecretarytInstitutionResponse
                .builder()
                .institutionCnpj(secretaryInstitution.getInstitution().getCnpj())
                .studentCpf(secretaryInstitution.getSecretary().getCpf())
                .startDate(secretaryInstitution.getStartDate())
                .uptdateAt(secretaryInstitution.getUptdateAt())
                .status(secretaryInstitution.isRegistration())
                .build();
    }

    public List<InstitutionResponse> mapSecretaryInstitutions(List<SecretaryInstitution> secretaryInstitutions) {
        return secretaryInstitutions.stream()
                .filter(SecretaryInstitution ::isRegistration)
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
