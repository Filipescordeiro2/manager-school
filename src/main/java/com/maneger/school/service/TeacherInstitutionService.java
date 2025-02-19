package com.maneger.school.service;

import com.maneger.school.domain.TeacherInstitution;
import com.maneger.school.dto.request.TeacherInstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.TeacherInstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.StudentInstitutionException;
import com.maneger.school.repository.TeacherInstitutionRepository;
import com.maneger.school.repository.TeacherRepository;
import com.maneger.school.utils.Utilitarias.TeacherInstitutionUtils;
import com.maneger.school.utils.Validation.TeacherInstitutionValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherInstitutionService {

    private final TeacherInstitutionRepository teacherInstitutionRepository;
    private final TeacherInstitutionUtils teacherInstitutionUtils;
    private final TeacherInstitutionValidation validation;
    private final TeacherRepository repository;

    public TeacherInstitutionResponse createLink(TeacherInstitutionRequest request){
        try{
            validation.validStudentInstitution(request);
            var link = new TeacherInstitution(request);
            var linkCreated = teacherInstitutionRepository.save(link);
            return teacherInstitutionUtils.ConvertTeacherInstitutionResponse(linkCreated);
        }catch (Exception e){
            throw new StudentInstitutionException("Error for link: "+e.getMessage());
        }
    }

    public List<InstitutionResponse> findAllTeacherInstitution(String cpf) {
        var teacher = repository.findByTeacherCpf(cpf)
                .orElseThrow(() -> new StudantException("teacher not found"));
        var teacherInstitutions = teacherInstitutionRepository.findByTeacher(teacher);
        if (teacherInstitutions.isEmpty()) {
            throw new InstitutionException("No institutions found for this teacher");
        }
        return teacherInstitutionUtils.mapStudentInstitutions(teacherInstitutions);
    }
}
