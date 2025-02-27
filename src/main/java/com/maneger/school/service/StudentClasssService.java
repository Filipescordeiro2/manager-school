package com.maneger.school.service;

import com.maneger.school.domain.StudentClass;
import com.maneger.school.dto.request.StudentClassRequest;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.SchoolClassResponse;
import com.maneger.school.dto.response.StudentClassResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.StudentClassException;

import com.maneger.school.repository.StudentClassRepository;

import com.maneger.school.repository.StudentRepository;
import com.maneger.school.utils.Utilitarias.StudentClassUtils;
import com.maneger.school.utils.Validation.StudentClassValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClasssService {

    private final StudentClassValidation validation;
    private final StudentClassRepository studentClassRepository;
    private final StudentClassUtils studentClassUtils;
    private final StudentRepository studentRepository;


    @Transactional
    public StudentClassResponse createLink(StudentClassRequest request){
        try{
            validation.validateStudentClassLink(request);
            validation.validateScheduleOverlap(request);
            validation.validStudentClass(request);
            validation.validStudentInstitution(request);
            var link = new StudentClass(request);
            var linkCreated = studentClassRepository.save(link);
            return studentClassUtils.ConvertStudentClassResponse(linkCreated);
        }catch (Exception e){
            throw new StudentClassException("Error for link: " + e.getMessage());
        }
    }

    public List<SchoolClassResponse> findAllStudentClass(String cpf) {
        var student = studentRepository.findByCpf(cpf)
                .orElseThrow(() -> new StudantException("Student not found"));
        var studentClass = studentClassRepository.findByStudent(student);
        if (studentClass.isEmpty()) {
            throw new InstitutionException("No institutions found for this student");
        }
        return studentClassUtils.mapStudentClasses(studentClass);
    }

}
