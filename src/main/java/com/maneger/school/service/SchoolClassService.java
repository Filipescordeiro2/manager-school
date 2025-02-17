package com.maneger.school.service;


import com.maneger.school.domain.SchoolClass;
import com.maneger.school.dto.request.SchoolClassRequest;
import com.maneger.school.dto.response.SchoolClassResponse;
import com.maneger.school.exception.SchoolClassException;
import com.maneger.school.repository.SchoolClassRepository;
import com.maneger.school.utils.Utilitarias.SchoolClassUtils;
import com.maneger.school.utils.Validation.SchoolClassValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolClassService {

    private final SchoolClassRepository repository;
    private final SchoolClassUtils utils;
    private final SchoolClassValidation validation;

    public SchoolClassResponse saveClass (SchoolClassRequest request){
        validation.validateInstitution(request.getInstitutionCNPJ());
        try {
            var schoolClass = new SchoolClass(request);
            var schoolClassSaved = repository.save(schoolClass);
            return utils.ConvertSchoolClassResponse(schoolClassSaved);
        }catch (Exception e){
            throw  new SchoolClassException("Error in saved School Class: "+e.getMessage());
        }
    }


}
