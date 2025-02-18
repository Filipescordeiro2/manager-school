package com.maneger.school.service;

import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.StudentInstitutionException;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import com.maneger.school.utils.Utilitarias.StudentInstitutionUtils;
import com.maneger.school.utils.Validation.StudentInstitutionValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentInstitutionService {

    private final StudentInstitutionRepository studentInstitutionRepository;
    private final StudentRepository studentRepository;
    private final StudentInstitutionUtils studentInstitutionUtils;
    private final StudentInstitutionValidation validation;

    public StudentInstitutionResponse createLink(StudentInstitutionRequest request){
        try{
            validation.validStudentInstitution(request);
            var link = new StudentInstitution(request);
            var linkCreated = studentInstitutionRepository.save(link);
            return studentInstitutionUtils.ConvertStudentInstitutionResponse(linkCreated);
        }catch (Exception e){
            throw new StudentInstitutionException("Error for link: "+e.getMessage());
        }
    }

    public List<InstitutionResponse> findAllStudentInstitution(String cpf) {
        var student = studentRepository.findByCpf(cpf)
                .orElseThrow(() -> new StudantException("Student not found"));
        var studentInstitutions = studentInstitutionRepository.findByStudent(student);
        if (studentInstitutions.isEmpty()) {
            throw new InstitutionException("No institutions found for this student");
        }
        return studentInstitutionUtils.mapStudentInstitutions(studentInstitutions);
    }



}