package com.maneger.school.service;

import com.maneger.school.domain.TeacherSubject;
import com.maneger.school.dto.request.TeacherSubjectRequest;
import com.maneger.school.dto.response.TeacherSubjectResponse;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.TeacherException;
import com.maneger.school.repository.TeacherSubjectRepository;
import com.maneger.school.utils.Utilitarias.TeacherSubjectUtils;
import com.maneger.school.utils.Validation.TeacherSubjectValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherSubjectService {

    private final TeacherSubjectRepository teacherSubjectRepository;
    private final TeacherSubjectUtils teacherSubjectUtils;
    private final TeacherSubjectValidation validation;

    public TeacherSubjectResponse createLink(TeacherSubjectRequest request){
        try{
           validation.validTeacherSubject(request);
            var link = new TeacherSubject(request);
            var linkCreated = teacherSubjectRepository.save(link);
            return teacherSubjectUtils.convertToTeacherSubjectResponse(linkCreated);
        }catch (StudantException e){
            throw  new TeacherException(e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error for link "+e.getMessage());
        }
    }

}