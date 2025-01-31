package com.maneger.school.service;

import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import com.maneger.school.util.StudentInstitutionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentInstitutionService {


    private final StudentInstitutionRepository studentInstitutionRepository;
    private final StudentRepository studentRepository;
    private final StudentInstitutionUtils studentInstitutionUtils;

    public StudentInstitutionResponse createLink(StudentInstitutionRequest request){
        try{
            validStatus(request.getStudentCPF());
            var link = new StudentInstitution(request);
            var linkCreated = studentInstitutionRepository.save(link);
            return studentInstitutionUtils.convertToStudentResponse(linkCreated);
        }catch (StudantException e){
            throw  new StudantException(e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error for link");
        }
    }

    private void validStatus(String cpf){
      var student =  studentRepository.findByCpf(cpf).orElseThrow(()->new RuntimeException("Student not Found"));
      if (!student.isStatus()){
          throw new StudantException("Student is Blocked");
      }
    }

}