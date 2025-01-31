package com.maneger.school.service;

import com.maneger.school.domain.SchoolSubject;
import com.maneger.school.domain.Teacher;
import com.maneger.school.dto.request.SchoolSubjectRequest;
import com.maneger.school.dto.response.SchoolSubjectResponse;
import com.maneger.school.dto.response.TeacherResponse;
import com.maneger.school.repository.SchoolSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolSubjectService {

    private final SchoolSubjectRepository repository;

    public  SchoolSubjectResponse save (SchoolSubjectRequest request){
        var schoolSubject = new SchoolSubject(request);
        var schoolSubjectSaved = repository.save(schoolSubject);
        return convertToTeacherResponse(schoolSubjectSaved);
    }

    private SchoolSubjectResponse convertToTeacherResponse(SchoolSubject schoolSubject) {
        return SchoolSubjectResponse
                .builder()
                .nameSubject(schoolSubject.getNameSubject())
                .build();
    }
}
