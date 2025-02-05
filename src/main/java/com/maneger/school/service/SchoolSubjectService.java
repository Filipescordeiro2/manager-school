package com.maneger.school.service;

import com.maneger.school.domain.SchoolSubject;
import com.maneger.school.dto.request.SchoolSubjectRequest;
import com.maneger.school.dto.response.SchoolSubjectResponse;
import com.maneger.school.exception.SchoolSubjectException;
import com.maneger.school.repository.SchoolSubjectRepository;
import com.maneger.school.utils.Utilitarias.SchoolSubjectUtils;
import com.maneger.school.utils.Validation.SchoolSubjectValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolSubjectService {

    private final SchoolSubjectRepository repository;
    private final SchoolSubjectValidation validation;
    private final SchoolSubjectUtils utils;

    public  SchoolSubjectResponse save (SchoolSubjectRequest request){
        try{
            var schoolSubject = new SchoolSubject(request);
            validation.validNameDuplicate(request.getNameSubject());
            var schoolSubjectSaved = repository.save(schoolSubject);
            return utils.convertToTeacherResponse(schoolSubjectSaved);
        }catch (Exception e){
            throw new SchoolSubjectException("Error in created Subject: "+e.getMessage());
        }
    }

}
