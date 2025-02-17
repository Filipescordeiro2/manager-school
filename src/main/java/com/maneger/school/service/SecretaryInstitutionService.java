package com.maneger.school.service;

import com.maneger.school.domain.SecretaryInstitution;
import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.request.SecretarytInstitutionRequest;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.SecretarytInstitutionResponse;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.SecretaryException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.StudentInstitutionException;
import com.maneger.school.repository.SecretaryInstitutionRepository;
import com.maneger.school.repository.SecretaryRepository;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import com.maneger.school.utils.Utilitarias.SecretaryInstitutionUtils;
import com.maneger.school.utils.Utilitarias.StudentInstitutionUtils;
import com.maneger.school.utils.Validation.SecretaryInstitutionValidation;
import com.maneger.school.utils.Validation.StudentInstitutionValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretaryInstitutionService {

    private final SecretaryInstitutionRepository secretaryInstitutionRepository;
    private final SecretaryRepository secretaryRepository;
    private final SecretaryInstitutionUtils secretaryInstitutionUtils;
    private final SecretaryInstitutionValidation validation;

    public SecretarytInstitutionResponse createLink(SecretarytInstitutionRequest request){
        try{
            validation.validSecretaryInstitution(request);
            var link = new SecretaryInstitution(request);
            var linkCreated = secretaryInstitutionRepository.save(link);
            return secretaryInstitutionUtils.ConvertSecretaryInstitutionResponse(linkCreated);
        }catch (Exception e){
            throw new StudentInstitutionException("Error for link: "+e.getMessage());
        }
    }

    public List<InstitutionResponse> findAllStudentInstitution(String cpf) {
        var secretary = secretaryRepository.findByCpf(cpf)
                .orElseThrow(() -> new SecretaryException("Student not found"));
        var secretaryInstitutions = secretaryInstitutionRepository.findBySecretary(secretary);
        if (secretaryInstitutions.isEmpty()) {
            throw new InstitutionException("No institutions found for this student");
        }
        return secretaryInstitutionUtils.mapSecretaryInstitutions(secretaryInstitutions);
    }

}
