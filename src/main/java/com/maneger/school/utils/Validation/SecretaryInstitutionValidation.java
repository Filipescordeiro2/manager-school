package com.maneger.school.utils.Validation;


import com.maneger.school.dto.request.SecretarytInstitutionRequest;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.SecretaryRepository;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretaryInstitutionValidation {

    private final InstitutionRepository institutionRepository;
    private final SecretaryRepository secretaryRepository;
    private final StudentInstitutionRepository studentInstitutionRepository;


    public void validSecretaryInstitution(SecretarytInstitutionRequest request){
        validInstitution(request.getInstitutionCNPJ());
        validStudent(request.getSecretaryCPF());
    }

    private void validInstitution(String cnpj){
        var institution = institutionRepository.findByCnpj(cnpj);
        if (!institution.isPresent()){
            throw new InstitutionException("Institution not registered in the system");
        }
    }

    private void validStudent(String cpf){
        var secretary = secretaryRepository.findByCpf(cpf)
                .orElseThrow(() -> new StudantException("secretary not registered in the system"));
        if (!secretary.isStatus()) {
            throw new StudantException("secretary is Blocked");
        }
    }
}
