package com.maneger.school.utils.Validation;

import com.maneger.school.exception.SchoolSubjectException;
import com.maneger.school.repository.SchoolSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolSubjectValidation {

    private final SchoolSubjectRepository repository;

    public void validNameDuplicate(String nameSubject){
        var subject = repository.findByNameSubject(nameSubject);
        if (subject.isPresent()){
            throw  new SchoolSubjectException("nameSubject duplicate");
        }
    }
}
