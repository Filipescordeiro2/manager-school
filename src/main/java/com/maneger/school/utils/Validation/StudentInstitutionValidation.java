package com.maneger.school.utils.Validation;

import com.maneger.school.domain.Student;
import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentInstitutionValidation {

    private final InstitutionRepository institutionRepository;
    private final StudentRepository studentRepository;
    private final StudentInstitutionRepository studentInstitutionRepository;


    public void validStudentInstitution(StudentInstitutionRequest request){
        validInstitution(request.getInstitutionCNPJ());
        validStudent(request.getStudentCPF());
    }

    private void validInstitution(String cnpj){
        var institution = institutionRepository.findByCnpj(cnpj);
        if (!institution.isPresent()){
            throw new InstitutionException("Institution not registered in the system");
        }
    }

    private void validStudent(String cpf){
        var student = studentRepository.findByCpf(cpf)
                .orElseThrow(() -> new StudantException("Student not registered in the system"));
        if (!student.isStatus()) {
            throw new StudantException("Student is Blocked");
        }
    }
}
