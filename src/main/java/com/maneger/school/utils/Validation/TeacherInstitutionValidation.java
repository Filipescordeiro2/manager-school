package com.maneger.school.utils.Validation;

import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.request.TeacherInstitutionRequest;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;

import com.maneger.school.repository.TeacherInstitutionRepository;
import com.maneger.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherInstitutionValidation {


    private final InstitutionRepository institutionRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherInstitutionRepository teacherInstitutionRepository;


    public void validStudentInstitution(TeacherInstitutionRequest request){
        validInstitution(request.getInstitutionCNPJ());
        validStudent(request.getTeacherCPF());
    }

    public void validaVinculoTeacherInstitution(String cpf,String cnpj) {
        var teacherInstitution = teacherInstitutionRepository
                .findByTeacher_TeacherCpfAndInstitution_Cnpj(cpf,cnpj) ;
        if (teacherInstitution.isEmpty()) {
            throw new InstitutionException("Teacher not registered in the institution");
        }
    }

    private void validInstitution(String cnpj){
        var institution = institutionRepository.findByCnpj(cnpj);
        if (!institution.isPresent()){
            throw new InstitutionException("Institution not registered in the system");
        }
    }

    private void validStudent(String cpf){
        var student = teacherRepository.findByTeacherCpf(cpf)
                .orElseThrow(() -> new StudantException("Student not registered in the system"));
        if (!student.isStatus()) {
            throw new StudantException("Student is Blocked");
        }
    }
}
