package com.maneger.school.service;

import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.domain.TeacherSubject;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.request.TeacherSubjectRequest;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.dto.response.TeacherSubjectResponse;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.TeacherException;
import com.maneger.school.repository.TeacherRepository;
import com.maneger.school.repository.TeacherSubjectRepository;
import com.maneger.school.util.TeacherSubjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherSubjectService {

    private final TeacherSubjectRepository teacherSubjectRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherSubjectUtils teacherSubjectUtils;

    public TeacherSubjectResponse createLink(TeacherSubjectRequest request){
        try{
            validStatus(request.getTeacherCPF());
            var link = new TeacherSubject(request);
            var linkCreated = teacherSubjectRepository.save(link);
            return teacherSubjectUtils.convertTeacherSubjectResponse(linkCreated);
        }catch (StudantException e){
            throw  new TeacherException(e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error for link "+e.getMessage());
        }
    }

    private void validStatus(String cpf){
        var teacher =  teacherRepository.findByTeacherCpf(cpf).orElseThrow(()->new RuntimeException("Teacher not Found"));
        if (!teacher.isStatus()){
            throw new TeacherException("Teacher is Blocked");
        }
    }
}
