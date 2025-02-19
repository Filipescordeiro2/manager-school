package com.maneger.school.utils.Validation;

import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.request.TeacherInstitutionRequest;
import com.maneger.school.dto.request.TeacherSubjectRequest;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.SchoolSubjectException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.TeacherException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.SchoolSubjectRepository;
import com.maneger.school.repository.TeacherInstitutionRepository;
import com.maneger.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherSubjectValidation {

    private final TeacherRepository teacherRepository;
    private final InstitutionRepository institutionRepository;
    private final SchoolSubjectRepository schoolSubjectRepository;

    public void validTeacherSubject(TeacherSubjectRequest request){
        validInstitution(request.getInstitutionCNPJ());
        validTeacher(request.getTeacherCPF());
        validSchoolSubject(request.getNameSchoolSubject());

    }


    private void validInstitution(String cnpj){
        var institution = institutionRepository.findByCnpj(cnpj);
        if (!institution.isPresent()){
            throw new InstitutionException("Institution not registered in the system");
        }
    }

    private void validSchoolSubject(String nameSubject){
        var subjetc = schoolSubjectRepository.findByNameSubject(nameSubject);
        if(!subjetc.isPresent()){
            throw  new SchoolSubjectException("School Subject not registered in the system");
        }
    }

    private void validTeacher(String cpf){
        var teacher = teacherRepository.findByTeacherCpf(cpf)
                .orElseThrow(() -> new StudantException("Teacher not registered in the system"));
        if (!teacher.isStatus()) {
            throw new StudantException("Teacher is Blocked");
        }
    }
}
